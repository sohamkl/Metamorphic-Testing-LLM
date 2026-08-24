package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.domain.InputDomainInferenceService;
import mtllm.llm.LlmClient;
import mtllm.llm.OpenAiClient;
import mtllm.sut.ConstructionGraphDiscoverer;
import mtllm.sut.JavaSourceNames;
import mtllm.sut.TargetMethodResolver;
import mtllm.util.DotEnv;
import mtllm.util.GeneratedNames;

import randoop.sequence.Variable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
 * the SUT method from its complete signature. Zero- and multi-argument methods are presented to
 * Randoop through a framework-generated typed invocation wrapper.</p>
 *
 * <p><b>Classpath requirement:</b> the SUT classes and the developer-MR class must be loadable
 * (on the context/system classloader) when {@link #generate} runs, because Randoop reflects them
 * by name. Wiring that up inside the pipeline (the SUT is compiled to a separate dir) is the
 * caller's responsibility.</p>
 */
public final class RandoopDataGenerator {

    private final int timeLimitMillis;
    private final String invocationClassName;
    // Keep harvesting deterministic and bounded. A single Randoop run already explores thousands
    // of sequences; additional seeds can enter long-running third-party operations and discard the
    // useful inputs harvested by earlier seeds when the subprocess timeout is reached.
    private static final long[] RANDOM_SEEDS = {0L};

    public RandoopDataGenerator(int timeLimitMillis, String invocationClassName) {
        this.timeLimitMillis = timeLimitMillis;
        this.invocationClassName = invocationClassName == null ? "" : invocationClassName;
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
        String inputDomainFile = optionValue(args, "--input-domain-file=");
        if (inputDomainFile != null && !inputDomainFile.isBlank()) {
            config = config.withInputDomainRequirements(InputDomainInferenceService.readArtifact(
                    Path.of(inputDomainFile), config.count()));
        }
        String invocationClassName = optionValue(args, "--invocation-class=");
        RandoopDataGenerator generator = new RandoopDataGenerator(15000, invocationClassName);
        if (hasFlag(args, "--seeds-only")) {
            String seedExamples = generator
                    .generateSeedExamples(config);
            Files.writeString(outJson, seedExamples, StandardCharsets.UTF_8);
            return;
        }

        boolean seeded = config.inputGenerator().seedsWithLlm();

        LlmClient seederClient = null;
        if (seeded) {
            Map<String, String> env = DotEnv.load(repoRoot.resolve(".env"));
            String apiKey = DotEnv.firstNonBlank(
                    System.getenv("OPENAI_API_KEY_OPENAI"),
                    env.get("OPENAI_API_KEY_OPENAI"));
            String model = DotEnv.firstNonBlank(System.getenv("OPENAI_MODEL"), env.get("OPENAI_MODEL"), "gpt-4o-mini");
            String baseUrl = DotEnv.firstNonBlank(
                    System.getenv("OPENAI_BASE_URL"), env.get("OPENAI_BASE_URL"), "https://api.openai.com/v1");
            seederClient = new OpenAiClient(apiKey, model, baseUrl);
        }

        if (hasFlag(args, "--seeded-sources-only")) {
            String sourceExamples = generator
                    .generateSeedExamples(config, repoRoot, seederClient, true);
            Files.writeString(outJson, sourceExamples, StandardCharsets.UTF_8);
            return;
        }

        Result result = generator.generateAll(config, repoRoot, seederClient, seeded);
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

    /** Harvest raw Randoop examples for NEW_HYBRID before the LLM creates the final input set. */
    @SuppressWarnings("unchecked")
    public String generateSeedExamples(PromptConfig config) throws Exception {
        return generateSeedExamples(config, null, null, false);
    }

    /** Harvest executable source examples, optionally using LLM values to seed Randoop. */
    @SuppressWarnings("unchecked")
    public String generateSeedExamples(
            PromptConfig config, Path repoRoot, LlmClient seederClient, boolean seeded) throws Exception {
        SutInvocation invocation = resolveSutInvocation(config);
        Method sutMethod = invocation.method;
        Class<Object> inputType = (Class<Object>) invocation.inputType;
        Object sutReceiver = invocation.receiver;
        RandoopHarvester<Object> harvester = new RandoopHarvester<>(
                inputType,
                randoopClassNames(config, inputType));
        List<RandoopHarvester.Harvested<Object>> harvested;
        if (seeded && seederClient != null) {
            List<Object> seeds;
            String inputDomain = config.inputDomain();
            if (inputDomain != null && !inputDomain.isBlank()) {
                seeds = LlmValueSeeder.generateSeedsFromDomain(seederClient, inputDomain);
            } else {
                seeds = LlmValueSeeder.generateSeedsFromCode(seederClient, readSutSources(repoRoot, config));
            }
            int perSeedBudget = Math.max(2000, timeLimitMillis / RANDOM_SEEDS.length);
            harvested = harvester.harvestSequencesMultiSeed(perSeedBudget, seeds, RANDOM_SEEDS);
        } else {
            harvested = harvester.harvestSequences(timeLimitMillis, null, 0L);
        }
        List<RandoopHarvester.Harvested<Object>> executable = new ArrayList<>();
        for (RandoopHarvester.Harvested<Object> candidate : harvested) {
            try {
                if (!invocation.isUsable(candidate.value())) {
                    continue;
                }
                invoke(sutMethod, sutReceiver, candidate.value());
                executable.add(candidate);
            } catch (Throwable invalidSource) {
                // Seed examples should demonstrate inputs accepted by the target method.
            }
        }
        if (executable.isEmpty() && (seeded || config.inputGenerator().name().equals("NEW_HYBRID"))) {
            return emitInstancioSeedExamples(inputType, sutMethod, sutReceiver, invocation, config.count());
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

        String base = GeneratedNames.baseName(config.generatedClassName());
        String sutCallee = sutCallee(bundle);
        String specClassName = sourceClassName(bundle.specClass);
        String followUpCallee = sourceClassName(bundle.followUpMethod.getDeclaringClass())
                + "." + bundle.followUpMethod.getName();
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
        SutInvocation invocation = resolveSutInvocation(config);
        Class<?> sutClass = invocation.sutClass;
        Class<?> specClass = Class.forName(JavaSourceNames.qualifiedName(config.developerMrFile()));

        Method sutMethod = invocation.method;
        Class<?> inputType = invocation.inputType;
        Class<?> outputType = sutMethod.getReturnType();
        Object sutReceiver = invocation.receiver;

        Method followUpMethod = invocation.wrapped
                ? sutClass.getMethod("generateFollowUp", inputType)
                : specClass.getMethod(simpleName(config.developerFollowUpMethod()), inputType);
        Method assertMethod = findAssertMethod(specClass, simpleName(config.developerAssertMethod()), outputType);
        return new MethodBundle(sutClass, specClass, sutMethod, sutReceiver, followUpMethod, assertMethod, inputType);
    }

    /** Harvest structurally-distinct sources (raw, or LLM-seeded multi-seed) paired with sequences. */
    @SuppressWarnings("unchecked")
    private List<RandoopHarvester.Harvested<Object>> harvestSources(
            PromptConfig config, Path repoRoot, LlmClient seederClient, boolean seeded, MethodBundle bundle)
            throws Exception {
        RandoopHarvester<Object> harvester =
                new RandoopHarvester<>((Class<Object>) bundle.inputType,
                        randoopClassNames(config, bundle.inputType));

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

    private Set<String> randoopClassNames(PromptConfig config, Class<?> inputType) throws Exception {
        Set<String> classNames = new LinkedHashSet<>();
        ConstructionGraphDiscoverer.Result discovery = ConstructionGraphDiscoverer.discover(config, inputType);
        discovery.runtimeClassNames().stream()
                .filter(RandoopDataGenerator::isLoadableClass)
                .forEach(classNames::add);
        if (!config.randoopTargetClasses().isEmpty()) {
            classNames.addAll(config.randoopTargetClasses());
        } else {
            classNames.add(JavaSourceNames.qualifiedName(config.sutClassFile()));
            for (Path support : config.sutSupportFiles()) {
                classNames.add(JavaSourceNames.qualifiedName(support));
            }
        }
        System.err.println("[discovery] Randoop construction graph: " + classNames.size() + " classes");
        discovery.evidence().stream().limit(20)
                .forEach(item -> System.err.println("[discovery]   " + item));
        return classNames;
    }

    private static boolean isLoadableClass(String className) {
        try {
            Class.forName(className, false, Thread.currentThread().getContextClassLoader());
            return true;
        } catch (ClassNotFoundException | LinkageError unavailable) {
            return false;
        }
    }

    /**
     * Last-resort seed generation for hybrid modes. Raw RANDOOP remains pure Randoop; when a
     * hybrid harvest is empty, Instancio gets a small deterministic chance to construct the root
     * graph. Every candidate is executed through the SUT before it is exposed to the LLM.
     */
    private String emitInstancioSeedExamples(
            Class<?> inputType, Method sutMethod, Object sutReceiver, SutInvocation invocation, int limit) {
        StringBuilder json = new StringBuilder("[");
        Set<String> signatures = new LinkedHashSet<>();
        int emitted = 0;
        int attempts = Math.max(12, limit * 4);
        for (long seed = 0; seed < attempts && emitted < limit; seed++) {
            Object value;
            try {
                value = org.instancio.Instancio.of(inputType).withSeed(seed).create();
                if (!invocation.isUsable(value)) {
                    continue;
                }
                invoke(sutMethod, sutReceiver, value);
            } catch (Throwable invalid) {
                continue;
            }
            String serialized = JsonSerializer.toJson(value);
            if (!signatures.add(serialized)) {
                continue;
            }
            if (emitted++ > 0) {
                json.append(',');
            }
            String sourceType = sourceClassName(inputType);
            String constructionCode = sourceType + " sourceInput = org.instancio.Instancio.of("
                    + sourceType + ".class).withSeed(" + seed + "L).create();\n"
                    + "// source input variable: sourceInput";
            json.append("{\"value\":").append(serialized)
                    .append(",\"constructionCode\":")
                    .append(JsonSerializer.toJson(constructionCode))
                    .append('}');
        }
        if (emitted > 0) {
            System.err.println("[discovery] Randoop produced no executable hybrid seeds; Instancio supplied "
                    + emitted + " deterministic fallback seed(s).");
        }
        return json.append(']').toString();
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
            Construction construction = construction(candidate);
            String constructionCode = construction == null
                    ? ""
                    : construction.code() + "// source input variable: " + construction.variableName();
            json.append("{\"value\":")
                    .append(JsonSerializer.toJson(candidate.value()))
                    .append(",\"constructionCode\":")
                    .append(JsonSerializer.toJson(constructionCode))
                    .append('}');
            emitted++;
        }
        return json.append(']').toString();
    }

    private Construction construction(RandoopHarvester.Harvested<Object> harvested) {
        Variable variable = harvested.variable();
        if (variable == null) {
            return null;
        }
        Set<Integer> requiredStatements = new TreeSet<>();
        collectRequiredStatements(harvested.sequence().sequence, variable.getDeclIndex(), requiredStatements);
        StringBuilder code = new StringBuilder();
        for (int statementIndex : requiredStatements) {
            code.append(harvested.sequence().statementToCodeString(statementIndex)).append('\n');
        }
        return new Construction(code.toString(), variable.getName());
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
            followUp = invoke(b.followUpMethod, null, source);
            sourceOutput = invoke(b.sutMethod, b.sutReceiver, source);
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

    /** Render only the statements required to construct the harvested target variable. */
    private RandoopJUnitEmitter.Case toCase(RandoopHarvester.Harvested<Object> h) {
        Construction construction = construction(h);
        if (construction == null) {
            return null;
        }
        return new RandoopJUnitEmitter.Case(construction.code(), construction.variableName());
    }

    /** Randoop invokes either a static unary SUT directly or a generated static wrapper. */
    private static String sutCallee(MethodBundle b) {
        if (!Modifier.isStatic(b.sutMethod.getModifiers())) {
            throw new IllegalStateException("Randoop instance invocation requires a generated wrapper: "
                    + b.sutMethod);
        }
        return sourceClassName(b.sutClass) + "." + b.sutMethod.getName();
    }

    private static String sourceClassName(Class<?> type) {
        String canonicalName = type.getCanonicalName();
        return canonicalName != null ? canonicalName : type.getName().replace('$', '.');
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

    private SutInvocation resolveSutInvocation(PromptConfig config) throws Exception {
        if (!invocationClassName.isBlank()) {
            Class<?> wrapperClass = Class.forName(invocationClassName);
            Method wrapperMethod = Arrays.stream(wrapperClass.getMethods())
                    .filter(method -> method.getName().equals("invoke"))
                    .filter(method -> Modifier.isStatic(method.getModifiers()))
                    .filter(method -> method.getParameterCount() == 1)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchMethodException(
                            "Generated invocation class has no public static invoke(Input): " + invocationClassName));
            Method usabilityMethod = Arrays.stream(wrapperClass.getMethods())
                    .filter(method -> method.getName().equals("isUsable"))
                    .filter(method -> Modifier.isStatic(method.getModifiers()))
                    .filter(method -> method.getParameterCount() == 1)
                    .findFirst()
                    .orElse(null);
            return new SutInvocation(
                    wrapperClass, wrapperMethod, null, wrapperMethod.getParameterTypes()[0], true,
                    usabilityMethod);
        }

        Class<?> sutClass = Class.forName(JavaSourceNames.qualifiedName(config.sutClassFile()));
        Method sutMethod = requireSingleArgument(TargetMethodResolver.resolve(sutClass, config.targetFunction()));
        if (!Modifier.isStatic(sutMethod.getModifiers())) {
            throw new IllegalStateException("Randoop instance invocation requires --invocation-class: "
                    + sutMethod.toGenericString());
        }
        return new SutInvocation(sutClass, sutMethod, null, sutMethod.getParameterTypes()[0], false, null);
    }

    private static Method requireSingleArgument(Method method) {
        if (method.getParameterCount() != 1) {
            throw new IllegalArgumentException("Randoop input harvesting currently requires exactly one target "
                    + "argument, but " + method.toGenericString() + " has " + method.getParameterCount()
                    + ". LLM generation can use this signature; Randoop requires a generated invocation wrapper.");
        }
        return method;
    }

    private static boolean hasFlag(String[] args, String flag) {
        return Arrays.asList(args).contains(flag);
    }

    private static String optionValue(String[] args, String prefix) {
        return Arrays.stream(args)
                .filter(arg -> arg.startsWith(prefix))
                .map(arg -> arg.substring(prefix.length()))
                .findFirst()
                .orElse("");
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

    private static final class SutInvocation {
        private final Class<?> sutClass;
        private final Method method;
        private final Object receiver;
        private final Class<?> inputType;
        private final boolean wrapped;
        private final Method usabilityMethod;

        private SutInvocation(Class<?> sutClass, Method method, Object receiver, Class<?> inputType,
                              boolean wrapped, Method usabilityMethod) {
            this.sutClass = sutClass;
            this.method = method;
            this.receiver = receiver;
            this.inputType = inputType;
            this.wrapped = wrapped;
            this.usabilityMethod = usabilityMethod;
        }

        private boolean isUsable(Object source) {
            if (usabilityMethod == null) {
                return true;
            }
            return Boolean.TRUE.equals(invoke(usabilityMethod, null, source));
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

    private record Construction(String code, String variableName) {
    }
}
