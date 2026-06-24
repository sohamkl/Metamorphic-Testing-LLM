import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Config-driven Mode 4 Randoop runner -- the developer changes ONLY {@code prompt.txt}
 * (plus their self-contained developer-MR file, which is where the domain logic lives).
 * No Java edits are needed to point the harvester/evaluator/LLM-seeder at a different object SUT.
 *
 * <p>Everything is derived from the same {@code prompt.txt} keys Soham's pipeline already uses:</p>
 * <ul>
 *   <li>{@code SUTClassFile} + {@code SUTSupportFiles} -&gt; the class set Randoop builds with, and
 *       the sources shown to the LLM seeder.</li>
 *   <li>{@code TargetFunction} -&gt; the SUT method name + input parameter type (and static-ness).</li>
 *   <li>{@code DeveloperMrFile} + {@code DeveloperFollowUpMethod} / {@code DeveloperAssertMethod}
 *       -&gt; the developer-MR class and the names of its follow-up transform and assertion methods.</li>
 * </ul>
 *
 * <p>The developer-MR is resolved <em>by method name via reflection</em> (matching Soham's
 * name-based contract), not through a framework interface -- so the spec is a plain self-contained
 * class with static {@code generateFollowUp}/{@code assertRelation} methods, and the same file
 * compiles standalone in Soham's pipeline. The de-dup signature uses the reflection
 * {@link StructuralSignature} auto-default, so no signature is configured.</p>
 *
 * <p>Scope: object SUTs whose target method takes a single reference-type argument and is public.
 * Primitive/array input types and multi-arg target methods are out of scope for this runner.</p>
 *
 * <p>Usage: {@code java ... RandoopMode4Config [timeLimitMillis] [configPath]}</p>
 */
public final class RandoopMode4Config {

    private RandoopMode4Config() {
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        int timeLimitMillis = args.length > 0 ? Integer.parseInt(args[0]) : 15000;

        // 1. Locate the config: explicit path if given, else find prompt.txt by walking up from
        //    CWD (handles running from a worktree subdir).
        Path promptPath = args.length > 1 ? Path.of(args[1]).toAbsolutePath().normalize()
                                          : findUp("prompt.txt");
        if (promptPath == null || !Files.exists(promptPath)) {
            System.out.println("FAILED: config not found ("
                    + (args.length > 1 ? args[1] : "prompt.txt") + ")");
            return;
        }
        Path repoRoot = promptPath.getParent();
        Map<String, String> cfg = parseConfig(promptPath);

        // 2. Derive the SUT wiring from config -- the ONLY inputs; no hardcoded types.
        String sutClassFile = require(cfg, "SUTClassFile");
        String targetFunction = require(cfg, "TargetFunction");
        String developerMrFile = require(cfg, "DeveloperMrFile");
        List<String> supportFiles = splitCsv(cfg.getOrDefault("SUTSupportFiles", ""));
        String followUpName = simpleMethodName(cfg.getOrDefault("DeveloperFollowUpMethod", "generateFollowUp"));
        String assertName = simpleMethodName(cfg.getOrDefault("DeveloperAssertMethod", "assertRelation"));

        String declaringClassName = classNameOf(sutClassFile);   // e.g. OrderUtil
        String specClassName = classNameOf(developerMrFile);      // e.g. OrderMetamorphicSpec
        TargetSig sig = parseTargetFunction(targetFunction);      // name + input type + static

        // Class set Randoop may build with = SUT class + every support class.
        Set<String> sutClasses = new LinkedHashSet<>();
        sutClasses.add(declaringClassName);
        for (String f : supportFiles) {
            sutClasses.add(classNameOf(f));
        }

        // 3. Resolve everything by reflection.
        Class<?> inputClass = Class.forName(sig.inputType);
        Class<?> declaringClass = Class.forName(declaringClassName);
        Method sutMethod = declaringClass.getMethod(sig.methodName, inputClass);
        Object receiver = sig.isStatic ? null : declaringClass.getDeclaredConstructor().newInstance();

        // Developer MR resolved by NAME (method references via reflection), not via a framework
        // interface -- so the spec stays a plain self-contained class (Soham's contract). One
        // self-contained spec therefore satisfies both Soham's pipeline and this harness.
        Class<?> specClass = Class.forName(specClassName);
        Class<?> outputType = sutMethod.getReturnType();
        Method followUpMethod = specClass.getMethod(followUpName, inputClass);
        Method assertMethod = findAssertMethod(specClass, assertName, outputType);
        Function<Object, Object> followUp = src -> invoke(followUpMethod, null, src);
        BiConsumer<Object, Object> assertRelation = (a, b) -> invoke(assertMethod, null, a, b);

        // SUT as a Function<I,O>; invoke() unwraps InvocationTargetException so the SUT's real
        // exception (not the reflection wrapper) reaches the evaluator's pass/error classification.
        Function<Object, Object> sut = in -> invoke(sutMethod, receiver, in);

        RandoopHarvester<Object> harvester =
                new RandoopHarvester<>((Class<Object>) inputClass, sutClasses);
        Mode4Evaluator<Object, Object> evaluator =
                new Mode4Evaluator<>(sut, followUp, assertRelation, harvester::signatureOf, null);

        System.out.println("=== Config-driven Mode 4 (from " + promptPath + ") ===");
        System.out.println("input type     : " + inputClass.getName());
        System.out.println("SUT method     : " + declaringClassName + "." + sig.methodName
                + (sig.isStatic ? " (static)" : " (instance)"));
        System.out.println("spec           : " + specClassName);
        System.out.println("Randoop classes: " + sutClasses);
        System.out.println();

        // 4. Arm: raw Randoop.
        System.out.println("Harvesting (raw Randoop, " + timeLimitMillis + "ms)...");
        List<Object> raw = harvester.harvest(timeLimitMillis);
        Mode4Evaluator.printReport("RAW Randoop", evaluator.evaluate(raw));

        // 5. Arm: LLM-seeded Randoop (skipped gracefully if no API key).
        String apiKey = "";
        try {
            apiKey = LlmValueSeeder.readEnvValue("OPENAI_API_KEY");
        } catch (Exception ignored) {
        }
        if (apiKey.isEmpty()) {
            System.out.println("LLM-seeded arm: skipped -- no OPENAI_API_KEY found in .env");
            return;
        }

        // Sources shown to the LLM = SUT class + support files (comments stripped inside the seeder).
        List<String> sutSources = new ArrayList<>();
        sutSources.add(Files.readString(repoRoot.resolve(sutClassFile), StandardCharsets.UTF_8));
        for (String f : supportFiles) {
            sutSources.add(Files.readString(repoRoot.resolve(f), StandardCharsets.UTF_8));
        }
        System.out.println("Asking LLM for seed values...");
        List<Object> llmSeeds = LlmValueSeeder.generateSeeds(apiKey, "gpt-4o-mini", sutSources);

        long[] randomSeeds = {0L, 1L, 2L, 3L, 4L};
        int perSeedBudget = Math.max(2000, timeLimitMillis / randomSeeds.length);
        System.out.println("Harvesting (LLM-seeded + " + randomSeeds.length
                + " random seeds, " + perSeedBudget + "ms each)...");
        List<Object> llm = harvester.harvestMultiSeed(perSeedBudget, llmSeeds, randomSeeds);
        Mode4Evaluator.printReport("LLM-SEEDED Randoop", evaluator.evaluate(llm));
    }

    /**
     * Invoke a reflected method (static when receiver==null), unwrapping InvocationTargetException
     * so the real cause -- including the AssertionError that signals an MR violation -- propagates
     * to the evaluator's pass/bug/error classification rather than the reflection wrapper.
     */
    private static Object invoke(Method m, Object receiver, Object... args) {
        try {
            return m.invoke(receiver, args);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            if (cause instanceof RuntimeException re) throw re;
            if (cause instanceof Error err) throw err;   // AssertionError lands here
            throw new RuntimeException(cause);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find the developer's assertRelation(outputType, outputType). The assertion's parameter type
     * must match the SUT's return type; we also try the primitive/boxed counterpart so a spec
     * written with {@code double} params still resolves against a {@code double}-returning SUT
     * (and vice versa) -- the boxing gotcha when going name-based instead of interface-based.
     */
    private static Method findAssertMethod(Class<?> specClass, String name, Class<?> outputType)
            throws NoSuchMethodException {
        try {
            return specClass.getMethod(name, outputType, outputType);
        } catch (NoSuchMethodException e) {
            Class<?> alt = altType(outputType);
            if (alt != null) {
                return specClass.getMethod(name, alt, alt);
            }
            throw e;
        }
    }

    /** Primitive <-> wrapper counterpart for the common numeric/boolean output types, else null. */
    private static Class<?> altType(Class<?> t) {
        if (t == double.class) return Double.class;
        if (t == Double.class) return double.class;
        if (t == int.class) return Integer.class;
        if (t == Integer.class) return int.class;
        if (t == long.class) return Long.class;
        if (t == Long.class) return long.class;
        if (t == boolean.class) return Boolean.class;
        if (t == Boolean.class) return boolean.class;
        return null;
    }

    /** "OrderMetamorphicSpec.generateFollowUp" or "X.INSTANCE.foo" -> simple method name "foo". */
    private static String simpleMethodName(String configured) {
        String s = configured.trim();
        int dot = s.lastIndexOf('.');
        return dot >= 0 ? s.substring(dot + 1) : s;
    }

    // ---- config parsing (mirrors mtllm.config.PromptConfigLoader's key:value format) ----

    private static Map<String, String> parseConfig(Path promptPath) throws Exception {
        Map<String, String> values = new LinkedHashMap<>();
        for (String raw : Files.readAllLines(promptPath, StandardCharsets.UTF_8)) {
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            int idx = line.indexOf(':');
            if (idx <= 0) {
                continue;
            }
            values.put(line.substring(0, idx).trim(), line.substring(idx + 1).trim());
        }
        return values;
    }

    private static String require(Map<String, String> cfg, String key) {
        String v = cfg.get(key);
        if (v == null || v.isBlank()) {
            throw new IllegalArgumentException("prompt.txt missing required key: " + key);
        }
        return v.trim();
    }

    private static List<String> splitCsv(String raw) {
        List<String> out = new ArrayList<>();
        if (raw == null || raw.isBlank()) {
            return out;
        }
        for (String part : raw.split(",")) {
            if (!part.isBlank()) {
                out.add(part.trim());
            }
        }
        return out;
    }

    /** "src/main/java/OrderUtil.java" -> "OrderUtil" (default-package simple class name). */
    private static String classNameOf(String filePath) {
        String name = filePath.replace('\\', '/');
        name = name.substring(name.lastIndexOf('/') + 1);
        if (name.endsWith(".java")) {
            name = name.substring(0, name.length() - ".java".length());
        }
        return name;
    }

    /** Parse "public static double calculateTotal(Order order)" -> name/inputType/static. */
    static TargetSig parseTargetFunction(String tf) {
        int open = tf.indexOf('(');
        int close = tf.indexOf(')');
        if (open < 0 || close < 0 || close < open) {
            throw new IllegalArgumentException("Cannot parse TargetFunction: " + tf);
        }
        String beforeParen = tf.substring(0, open).trim();
        String[] head = beforeParen.split("\\s+");
        String methodName = head[head.length - 1];
        boolean isStatic = beforeParen.matches(".*\\bstatic\\b.*");

        String inside = tf.substring(open + 1, close).trim();
        String firstParam = inside.split(",")[0].trim();
        String inputType = firstParam.split("\\s+")[0].trim();
        if (inputType.isEmpty()) {
            throw new IllegalArgumentException("TargetFunction has no parameter type: " + tf);
        }
        return new TargetSig(methodName, inputType, isStatic);
    }

    private static Path findUp(String fileName) {
        Path dir = Path.of(".").toAbsolutePath().normalize();
        for (int i = 0; i < 8; i++) {
            Path candidate = dir.resolve(fileName);
            if (Files.exists(candidate)) {
                return candidate;
            }
            Path parent = dir.getParent();
            if (parent == null) {
                break;
            }
            dir = parent;
        }
        return null;
    }

    static final class TargetSig {
        final String methodName;
        final String inputType;
        final boolean isStatic;

        TargetSig(String methodName, String inputType, boolean isStatic) {
            this.methodName = methodName;
            this.inputType = inputType;
            this.isStatic = isStatic;
        }
    }
}
