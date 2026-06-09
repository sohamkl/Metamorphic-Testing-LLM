import mtllm.spec.MetamorphicSpec;

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
import java.util.function.Function;

/**
 * Config-driven Mode 4 Randoop runner -- the developer changes ONLY {@code prompt.txt}
 * (plus their {@link MetamorphicSpec} implementation file, which is where the domain logic lives).
 * No Java edits are needed to point the harvester/evaluator/LLM-seeder at a different object SUT.
 *
 * <p>Everything is derived from the same {@code prompt.txt} keys Soham's pipeline already uses:</p>
 * <ul>
 *   <li>{@code SUTClassFile} + {@code SUTSupportFiles} -&gt; the class set Randoop builds with, and
 *       the sources shown to the LLM seeder.</li>
 *   <li>{@code TargetFunction} -&gt; the SUT method name + input parameter type (and static-ness).</li>
 *   <li>{@code DeveloperMrFile} -&gt; the {@link MetamorphicSpec} implementation class (its
 *       {@code INSTANCE} field if present, else a no-arg constructor).</li>
 * </ul>
 *
 * <p>The de-dup signature uses the reflection {@link StructuralSignature} auto-default, so no
 * signature is configured. Generics are erased at runtime; the spec is used through the raw
 * {@link MetamorphicSpec} interface, whose synthetic bridge methods cast back to the concrete
 * types -- so reflection + erasure compose cleanly.</p>
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

        Class<?> specClass = Class.forName(specClassName);
        MetamorphicSpec<Object, Object> spec = (MetamorphicSpec<Object, Object>) loadSpec(specClass);

        // SUT as a Function<I,O>; unwrap InvocationTargetException so the SUT's real exception
        // (not the reflection wrapper) reaches the evaluator's pass/error classification.
        Function<Object, Object> sut = in -> {
            try {
                return sutMethod.invoke(receiver, in);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause() != null ? e.getCause() : e;
                if (cause instanceof RuntimeException re) throw re;
                if (cause instanceof Error err) throw err;
                throw new RuntimeException(cause);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };

        RandoopHarvester<Object> harvester =
                new RandoopHarvester<>((Class<Object>) inputClass, sutClasses);
        Mode4Evaluator<Object, Object> evaluator =
                new Mode4Evaluator<>(sut, spec, harvester::signatureOf, null);

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

    /** Load a spec instance: prefer a public static INSTANCE field, else a no-arg constructor. */
    private static Object loadSpec(Class<?> specClass) throws Exception {
        try {
            return specClass.getField("INSTANCE").get(null);
        } catch (NoSuchFieldException e) {
            return specClass.getDeclaredConstructor().newInstance();
        }
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
