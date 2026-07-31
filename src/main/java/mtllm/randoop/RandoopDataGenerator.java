package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.llm.LlmClient;
import mtllm.llm.OpenAiClient;
import mtllm.util.DotEnv;

import randoop.sequence.Variable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RANDOOP input-generation mode: harvests source inputs with Randoop instead of the LLM, then
 * produces the same executed-MT JSON the LLM data-generator path produces, so the rest of the
 * pipeline (split passing/failing + HTML report) is unchanged.
 *
 * <p>For each structurally-distinct harvested source it runs the SUT, applies the developer's
 * follow-up transform, runs the SUT again, and invokes the developer's assertion -- an
 * AssertionError marks a bug-revealing (failing) case. Source and follow-up objects are serialized
 * generically with {@link JsonSerializer}, so no per-SUT serialization code is needed.</p>
 *
 * <p>The developer MR is resolved <em>by method name</em> (the self-contained-spec contract), and
 * the SUT method by name + single reference-type parameter -- mirroring the standalone experiment
 * runner, but reading everything from {@link PromptConfig}.</p>
 *
 * <p><b>Classpath requirement:</b> the SUT classes and the developer-MR class must be loadable
 * (on the context/system classloader) when {@link #generate} runs, because Randoop reflects them
 * by name. Wiring that up inside the pipeline (the SUT is compiled to a separate dir) is the
 * caller's responsibility.</p>
 */
public final class RandoopDataGenerator {

    private final int timeLimitMillis;
    private static final long[] RANDOM_SEEDS = {0L, 1L, 2L, 3L, 4L};
    private static final Pattern PACKAGE_DECLARATION =
            Pattern.compile("(?m)^\\s*package\\s+([A-Za-z_$][\\w$]*(?:\\.[A-Za-z_$][\\w$]*)*)\\s*;");

    public RandoopDataGenerator(int timeLimitMillis) {
        this.timeLimitMillis = timeLimitMillis;
    }

    /**
     * Subprocess entry point: {@code RandoopDataGenerator <prompt.yaml> <out.json>}.
     *
     * <p>Run in a separate JVM by the pipeline so the SUT classes are genuinely on the classpath
     * (Randoop reflects them by name). Loads the config, builds an LLM client from {@code .env} when
     * the mode is HYBRID, generates the executed-MT JSON, and writes it to the output file (a file,
     * not stdout, to keep it clean of Randoop's console output).</p>
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: RandoopDataGenerator <prompt.yaml> <out.json>");
        }
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path promptPath = Path.of(args[0]).toAbsolutePath().normalize();
        Path outJson = Path.of(args[1]).toAbsolutePath().normalize();

        PromptConfig config = PromptConfigLoader.load(promptPath, repoRoot);
        if (args.length >= 3 && args[2].equals("--seeds-only")) {
            String seedExamples = new RandoopDataGenerator(15000)
                    .generateSeedExamples(config);
            Files.writeString(outJson, seedExamples, StandardCharsets.UTF_8);
            return;
        }

        boolean seeded = config.inputGenerator().seedsWithLlm();

        LlmClient seederClient = null;
        if (seeded) {
            Map<String, String> env = DotEnv.load(repoRoot.resolve(".env"));
            String apiKey = DotEnv.firstNonBlank(System.getenv("OPENAI_API_KEY"), env.get("OPENAI_API_KEY"));
            String model = DotEnv.firstNonBlank(System.getenv("OPENAI_MODEL"), env.get("OPENAI_MODEL"), "gpt-4o-mini");
            String baseUrl = DotEnv.firstNonBlank(
                    System.getenv("OPENAI_BASE_URL"), env.get("OPENAI_BASE_URL"), "https://api.openai.com/v1");
            seederClient = new OpenAiClient(apiKey, model, baseUrl);
        }

        Result result = new RandoopDataGenerator(15000).generateAll(config, repoRoot, seederClient, seeded);
        Files.writeString(outJson, result.json(), StandardCharsets.UTF_8);

        // Object JUnit suite (approach C): emit passing/failing classes directly from the in-process
        // MR verdicts -- Randoop + the developer MR decide pass/fail deterministically, so there is no
        // guess to verify by re-running (unlike the LLM path). The classes land in the example's
        // registered junit-tests source dir so `mvn test` picks them up.
        if (config.testSuiteRequired()) {
            Path junitDir = config.outputRoot().resolve("junit-tests");
            Files.createDirectories(junitDir);
            Files.writeString(junitDir.resolve(result.passingClassName() + ".java"),
                    result.passingSource(), StandardCharsets.UTF_8);
            Files.writeString(junitDir.resolve(result.failingClassName() + ".java"),
                    result.failingSource(), StandardCharsets.UTF_8);
            System.err.println("[randoop] wrote JUnit suite: "
                    + result.passingClassName() + " (" + result.passingCount() + " passing), "
                    + result.failingClassName() + " (" + result.failingCount() + " failing)");
        }
    }

    /**
     * Backward-compatible JSON-only entry: harvest, evaluate, and return the executed-MT JSON array.
     *
     * @param seeded       if true, ask the LLM (via {@code seederClient}) for domain seed values and
     *                     run multi-seed harvesting; if false, raw Randoop.
     * @param seederClient LLM client for the hybrid seeding step; ignored when {@code seeded} is false.
     */
    public String generate(PromptConfig config, Path repoRoot, LlmClient seederClient, boolean seeded)
            throws Exception {
        return generateAll(config, repoRoot, seederClient, seeded).json();
    }

    /** Harvest raw Randoop examples for NEW_HYBRID before the LLM creates the final input set. */
    @SuppressWarnings("unchecked")
    public String generateSeedExamples(PromptConfig config) throws Exception {
        Class<?> sutClass = Class.forName(classNameOf(config.sutClassFile()));
        Method sutMethod = singleArgMethod(sutClass, methodName(config.targetFunction()));
        Class<Object> inputType = (Class<Object>) sutMethod.getParameterTypes()[0];
        Object sutReceiver = Modifier.isStatic(sutMethod.getModifiers())
                ? null
                : sutClass.getDeclaredConstructor().newInstance();
        RandoopHarvester<Object> harvester = new RandoopHarvester<>(
                inputType,
                randoopClassNames(config));
        List<RandoopHarvester.Harvested<Object>> harvested =
                harvester.harvestSequences(timeLimitMillis, null, 0L);
        List<RandoopHarvester.Harvested<Object>> executable = new ArrayList<>();
        for (RandoopHarvester.Harvested<Object> candidate : harvested) {
            try {
                invoke(sutMethod, sutReceiver, candidate.value());
                executable.add(candidate);
            } catch (Throwable invalidSource) {
                // Seed examples should demonstrate inputs accepted by the target method.
            }
        }
        return emitSeedExamples(executable, config.count());
    }

    /**
     * Harvest once and produce every requested artifact from the same evaluated cases: the
     * executed-MT JSON array always, and the rendered passing/failing JUnit test-class sources for
     * the object suite (approach C). Sharing one harvest+evaluate pass keeps the JSON entries and the
     * JUnit {@code @Test} methods in lock-step -- same distinct shapes, same pass/fail verdicts.
     */
    public Result generateAll(PromptConfig config, Path repoRoot, LlmClient seederClient, boolean seeded)
            throws Exception {
        MethodBundle bundle = resolveMethods(config);
        List<RandoopHarvester.Harvested<Object>> harvested =
                harvestSources(config, repoRoot, seederClient, seeded, bundle);

        List<Evaluated> kept = new ArrayList<>();
        for (RandoopHarvester.Harvested<Object> h : harvested) {
            Evaluated evaluated = evaluate(h, bundle);
            if (evaluated != null) {
                kept.add(evaluated);
                if (kept.size() >= config.count()) {
                    break;
                }
            }
        }

        String json = emitJson(kept);

        String base = baseName(config.generatedClassName());
        String sutCallee = sutCallee(bundle);
        String specClassName = sourceClassName(bundle.specClass);
        String followUpCallee = specClassName + "." + bundle.followUpMethod.getName();
        String assertCallee = specClassName + "." + bundle.assertMethod.getName();

        List<RandoopJUnitEmitter.Case> passingCases = new ArrayList<>();
        List<RandoopJUnitEmitter.Case> failingCases = new ArrayList<>();
        for (Evaluated e : kept) {
            RandoopJUnitEmitter.Case testCase = toCase(e.harvested);
            if (testCase == null) {
                continue;   // no usable construction variable; cannot render this shape as a test
            }
            (e.passed ? passingCases : failingCases).add(testCase);
        }

        String passingClass = base + "PassingTest";
        String failingClass = base + "FailingTest";
        String passingSource = RandoopJUnitEmitter.renderClass(
                passingClass, passingCases, sutCallee, followUpCallee, assertCallee);
        String failingSource = RandoopJUnitEmitter.renderClass(
                failingClass, failingCases, sutCallee, followUpCallee, assertCallee);

        return new Result(json, passingClass, failingClass, passingSource, failingSource,
                passingCases.size(), failingCases.size());
    }

    /** Reflect the SUT method (+ receiver) and the developer MR follow-up/assert methods by name. */
    private MethodBundle resolveMethods(PromptConfig config) throws Exception {
        Class<?> sutClass = Class.forName(classNameOf(config.sutClassFile()));
        Class<?> specClass = Class.forName(classNameOf(config.developerMrFile()));

        Method sutMethod = singleArgMethod(sutClass, methodName(config.targetFunction()));
        Class<?> inputType = sutMethod.getParameterTypes()[0];
        Class<?> outputType = sutMethod.getReturnType();
        Object sutReceiver = Modifier.isStatic(sutMethod.getModifiers())
                ? null
                : sutClass.getDeclaredConstructor().newInstance();

        Method followUpMethod = specClass.getMethod(simpleName(config.developerFollowUpMethod()), inputType);
        Method assertMethod = findAssertMethod(specClass, simpleName(config.developerAssertMethod()), outputType);
        return new MethodBundle(sutClass, specClass, sutMethod, sutReceiver, followUpMethod, assertMethod, inputType);
    }

    /** Harvest structurally-distinct sources (raw, or LLM-seeded multi-seed) paired with sequences. */
    @SuppressWarnings("unchecked")
    private List<RandoopHarvester.Harvested<Object>> harvestSources(
            PromptConfig config, Path repoRoot, LlmClient seederClient, boolean seeded, MethodBundle bundle)
            throws Exception {
        RandoopHarvester<Object> harvester =
                new RandoopHarvester<>((Class<Object>) bundle.inputType, randoopClassNames(config));

        if (seeded && seederClient != null) {
            // Prefer the developer's InputDomain description (concise, authoritative, scales to any
            // SUT size); fall back to reading the SUT source code when no domain is provided.
            List<Object> seeds;
            String inputDomain = config.inputDomain();
            if (inputDomain != null && !inputDomain.isBlank()) {
                seeds = LlmValueSeeder.generateSeedsFromDomain(seederClient, inputDomain);
            } else {
                seeds = LlmValueSeeder.generateSeedsFromCode(seederClient, readSutSources(repoRoot, config));
            }
            int perSeedBudget = Math.max(2000, timeLimitMillis / RANDOM_SEEDS.length);
            return harvester.harvestSequencesMultiSeed(perSeedBudget, seeds, RANDOM_SEEDS);
        }
        return harvester.harvestSequences(timeLimitMillis, null, 0L);
    }

    private Set<String> randoopClassNames(PromptConfig config) throws Exception {
        Set<String> classNames = new LinkedHashSet<>();
        if (!config.randoopTargetClasses().isEmpty()) {
            classNames.addAll(config.randoopTargetClasses());
        } else {
            classNames.add(classNameOf(config.sutClassFile()));
            for (Path support : config.sutSupportFiles()) {
                classNames.add(classNameOf(support));
            }
        }
        return classNames;
    }

    private String emitSeedExamples(List<RandoopHarvester.Harvested<Object>> harvested, int limit) {
        StringBuilder json = new StringBuilder("[");
        int emitted = 0;
        for (RandoopHarvester.Harvested<Object> candidate : harvested) {
            if (emitted >= limit) {
                break;
            }
            if (emitted > 0) {
                json.append(',');
            }
            String constructionCode = constructionCode(candidate);
            json.append("{\"value\":")
                    .append(JsonSerializer.toJson(candidate.value()))
                    .append(",\"constructionCode\":")
                    .append(JsonSerializer.toJson(constructionCode))
                    .append('}');
            emitted++;
        }
        return json.append(']').toString();
    }

    private String constructionCode(RandoopHarvester.Harvested<Object> harvested) {
        Variable variable = harvested.variable();
        if (variable == null) {
            return "";
        }
        Set<Integer> requiredStatements = new TreeSet<>();
        collectRequiredStatements(harvested.sequence().sequence, variable.getDeclIndex(), requiredStatements);
        StringBuilder code = new StringBuilder();
        for (int statementIndex : requiredStatements) {
            code.append(harvested.sequence().statementToCodeString(statementIndex)).append('\n');
        }
        code.append("// source input variable: ").append(variable.getName());
        return code.toString();
    }

    private void collectRequiredStatements(
            randoop.sequence.Sequence sequence, int statementIndex, Set<Integer> requiredStatements) {
        if (!requiredStatements.add(statementIndex)) {
            return;
        }
        for (Variable input : sequence.getInputs(statementIndex)) {
            collectRequiredStatements(sequence, input.getDeclIndex(), requiredStatements);
        }
    }

    /**
     * Run the SUT + developer MR on one harvested source. Returns null (skip) when the SUT or
     * transform throws or the assertion errors unexpectedly; an {@code AssertionError} from the
     * assertion is the MR-violated (failing) verdict, not a skip.
     */
    private Evaluated evaluate(RandoopHarvester.Harvested<Object> h, MethodBundle b) {
        Object source = h.value();
        Object sourceOutput;
        Object followUp;
        Object followUpOutput;
        try {
            sourceOutput = invoke(b.sutMethod, b.sutReceiver, source);
            followUp = invoke(b.followUpMethod, null, source);
            followUpOutput = invoke(b.sutMethod, b.sutReceiver, followUp);
        } catch (Throwable transformOrSutError) {
            return null;
        }
        boolean passed;
        try {
            invoke(b.assertMethod, null, sourceOutput, followUpOutput);
            passed = true;
        } catch (AssertionError relationViolated) {
            passed = false;
        } catch (Throwable unexpected) {
            return null;
        }
        return new Evaluated(h, source, followUp, sourceOutput, followUpOutput, passed);
    }

    private String emitJson(List<Evaluated> cases) {
        StringBuilder json = new StringBuilder("[");
        boolean first = true;
        for (Evaluated e : cases) {
            if (!first) {
                json.append(',');
            }
            first = false;
            json.append("{\"source\":").append(JsonSerializer.toJson(e.source))
                    .append(",\"followUp\":").append(JsonSerializer.toJson(e.followUp))
                    .append(",\"sourceOutput\":").append(JsonSerializer.toJson(e.sourceOutput))
                    .append(",\"followUpOutput\":").append(JsonSerializer.toJson(e.followUpOutput))
                    .append(",\"passed\":").append(e.passed)
                    .append('}');
        }
        json.append(']');
        return json.toString();
    }

    /**
     * Render the truncated Randoop construction code for a harvested shape: statements
     * {@code 0..declIndex} of the variable that holds the object, which drops the trailing
     * exploration statements Randoop appended after building it. Returns null when no variable was
     * captured (cannot render the shape as a test).
     */
    private RandoopJUnitEmitter.Case toCase(RandoopHarvester.Harvested<Object> h) {
        Variable v = h.variable();
        if (v == null) {
            return null;
        }
        int declIndex = v.getDeclIndex();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i <= declIndex; i++) {
            code.append(h.sequence().statementToCodeString(i)).append('\n');
        }
        return new RandoopJUnitEmitter.Case(code.toString(), v.getName());
    }

    /** Static SUT -> {@code Class.method}; instance SUT -> {@code new Class().method}. */
    private static String sutCallee(MethodBundle b) {
        String method = b.sutMethod.getName();
        String className = sourceClassName(b.sutClass);
        if (Modifier.isStatic(b.sutMethod.getModifiers())) {
            return className + "." + method;
        }
        return "new " + className + "()." + method;
    }

    private static String sourceClassName(Class<?> type) {
        String canonicalName = type.getCanonicalName();
        return canonicalName != null ? canonicalName : type.getName().replace('$', '.');
    }

    private static String baseName(String generatedClassName) {
        if (generatedClassName.endsWith("Data")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Data".length());
        }
        if (generatedClassName.endsWith("Test")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Test".length());
        }
        return generatedClassName;
    }

    private List<String> readSutSources(Path repoRoot, PromptConfig config) throws Exception {
        List<String> sources = new ArrayList<>();
        if (config.sutClassFile() != null) {
            sources.add(Files.readString(repoRoot.resolve(config.sutClassFile()), StandardCharsets.UTF_8));
        }
        for (Path support : config.sutSupportFiles()) {
            sources.add(Files.readString(repoRoot.resolve(support), StandardCharsets.UTF_8));
        }
        return sources;
    }

    /** Invoke a reflected method (static when receiver==null), unwrapping InvocationTargetException
     *  so the real cause -- including the AssertionError signalling an MR violation -- propagates. */
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

    private static Method singleArgMethod(Class<?> c, String name) throws NoSuchMethodException {
        for (Method m : c.getMethods()) {
            if (m.getName().equals(name) && m.getParameterCount() == 1) {
                return m;
            }
        }
        throw new NoSuchMethodException(
                "No single-argument public method '" + name + "' on " + c.getName());
    }

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

    /** Resolve a source file to its runtime name, including a declared Java package when present. */
    private static String classNameOf(Path file) throws java.io.IOException {
        String name = file.getFileName().toString();
        String simpleName = name.endsWith(".java") ? name.substring(0, name.length() - ".java".length()) : name;
        Matcher packageMatcher = PACKAGE_DECLARATION.matcher(Files.readString(file, StandardCharsets.UTF_8));
        return packageMatcher.find() ? packageMatcher.group(1) + "." + simpleName : simpleName;
    }

    /** "public static double calculateTotal(Order o)" / "Cls.foo" / "computeRank" -> "calculateTotal"/"foo"/"computeRank". */
    private static String methodName(String targetFunction) {
        String s = targetFunction.trim();
        int paren = s.indexOf('(');
        if (paren >= 0) s = s.substring(0, paren).trim();
        int space = s.lastIndexOf(' ');
        if (space >= 0) s = s.substring(space + 1);
        int dot = s.lastIndexOf('.');
        if (dot >= 0) s = s.substring(dot + 1);
        return s.trim();
    }

    /** "OrderMetamorphicSpec.generateFollowUp" / "X.INSTANCE.foo" -> simple method name. */
    private static String simpleName(String qualified) {
        String s = qualified.trim();
        int dot = s.lastIndexOf('.');
        return dot >= 0 ? s.substring(dot + 1) : s;
    }

    /** All artifacts produced from one harvest pass: JSON plus the two JUnit class sources. */
    public record Result(String json, String passingClassName, String failingClassName,
                         String passingSource, String failingSource,
                         int passingCount, int failingCount) {
    }

    /** Reflected SUT + developer-MR methods (resolved once, reused for JSON and JUnit emission). */
    private static final class MethodBundle {
        private final Class<?> sutClass;
        private final Class<?> specClass;
        private final Method sutMethod;
        private final Object sutReceiver;
        private final Method followUpMethod;
        private final Method assertMethod;
        private final Class<?> inputType;

        private MethodBundle(Class<?> sutClass, Class<?> specClass, Method sutMethod, Object sutReceiver,
                             Method followUpMethod, Method assertMethod, Class<?> inputType) {
            this.sutClass = sutClass;
            this.specClass = specClass;
            this.sutMethod = sutMethod;
            this.sutReceiver = sutReceiver;
            this.followUpMethod = followUpMethod;
            this.assertMethod = assertMethod;
            this.inputType = inputType;
        }
    }

    /** One harvested source after running the SUT + developer MR: outputs and the pass/fail verdict. */
    private static final class Evaluated {
        private final RandoopHarvester.Harvested<Object> harvested;
        private final Object source;
        private final Object followUp;
        private final Object sourceOutput;
        private final Object followUpOutput;
        private final boolean passed;

        private Evaluated(RandoopHarvester.Harvested<Object> harvested, Object source, Object followUp,
                          Object sourceOutput, Object followUpOutput, boolean passed) {
            this.harvested = harvested;
            this.source = source;
            this.followUp = followUp;
            this.sourceOutput = sourceOutput;
            this.followUpOutput = followUpOutput;
            this.passed = passed;
        }
    }
}
