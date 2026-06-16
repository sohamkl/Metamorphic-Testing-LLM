package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.llm.LlmClient;
import mtllm.llm.OpenAiClient;
import mtllm.util.DotEnv;

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

    public RandoopDataGenerator(int timeLimitMillis) {
        this.timeLimitMillis = timeLimitMillis;
    }

    /**
     * Subprocess entry point: {@code RandoopDataGenerator <prompt.txt> <out.json>}.
     *
     * <p>Run in a separate JVM by the pipeline so the SUT classes are genuinely on the classpath
     * (Randoop reflects them by name). Loads the config, builds an LLM client from {@code .env} when
     * the mode is HYBRID, generates the executed-MT JSON, and writes it to the output file (a file,
     * not stdout, to keep it clean of Randoop's console output).</p>
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: RandoopDataGenerator <prompt.txt> <out.json>");
        }
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path promptPath = Path.of(args[0]).toAbsolutePath().normalize();
        Path outJson = Path.of(args[1]).toAbsolutePath().normalize();

        PromptConfig config = PromptConfigLoader.load(promptPath, repoRoot);
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

        String json = new RandoopDataGenerator(15000).generate(config, repoRoot, seederClient, seeded);
        Files.writeString(outJson, json, StandardCharsets.UTF_8);
    }

    /**
     * Harvest sources and emit the executed-MT JSON array.
     *
     * @param seeded       if true, ask the LLM (via {@code seederClient}) for domain seed values and
     *                     run multi-seed harvesting; if false, raw Randoop.
     * @param seederClient LLM client for the hybrid seeding step; ignored when {@code seeded} is false.
     */
    @SuppressWarnings("unchecked")
    public String generate(PromptConfig config, Path repoRoot, LlmClient seederClient, boolean seeded)
            throws Exception {
        String sutClassName = classNameOf(config.sutClassFile());
        String specClassName = classNameOf(config.developerMrFile());

        Class<?> sutClass = Class.forName(sutClassName);
        Class<?> specClass = Class.forName(specClassName);

        Method sutMethod = singleArgMethod(sutClass, methodName(config.targetFunction()));
        Class<?> inputType = sutMethod.getParameterTypes()[0];
        Class<?> outputType = sutMethod.getReturnType();
        Object sutReceiver = Modifier.isStatic(sutMethod.getModifiers())
                ? null
                : sutClass.getDeclaredConstructor().newInstance();

        Method followUpMethod = specClass.getMethod(simpleName(config.developerFollowUpMethod()), inputType);
        Method assertMethod = findAssertMethod(specClass, simpleName(config.developerAssertMethod()), outputType);

        Set<String> classNames = new LinkedHashSet<>();
        classNames.add(sutClassName);
        for (Path support : config.sutSupportFiles()) {
            classNames.add(classNameOf(support));
        }

        RandoopHarvester<Object> harvester =
                new RandoopHarvester<>((Class<Object>) inputType, classNames);

        List<Object> sources;
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
            sources = harvester.harvestMultiSeed(perSeedBudget, seeds, RANDOM_SEEDS);
        } else {
            sources = harvester.harvest(timeLimitMillis);
        }

        return emitJson(sources, sutMethod, sutReceiver, followUpMethod, assertMethod);
    }

    private String emitJson(List<Object> sources, Method sutMethod, Object sutReceiver,
                            Method followUpMethod, Method assertMethod) {
        StringBuilder json = new StringBuilder("[");
        boolean first = true;
        for (Object source : sources) {
            Object sourceOutput;
            Object followUp;
            Object followUpOutput;
            boolean passed;
            try {
                sourceOutput = invoke(sutMethod, sutReceiver, source);
                followUp = invoke(followUpMethod, null, source);
                followUpOutput = invoke(sutMethod, sutReceiver, followUp);
            } catch (Throwable transformOrSutError) {
                // A source the SUT or transform can't handle is not a metamorphic result; skip it.
                continue;
            }
            try {
                invoke(assertMethod, null, sourceOutput, followUpOutput);
                passed = true;
            } catch (AssertionError relationViolated) {
                passed = false;
            } catch (Throwable unexpected) {
                continue;
            }

            if (!first) {
                json.append(',');
            }
            first = false;
            json.append("{\"source\":").append(JsonSerializer.toJson(source))
                    .append(",\"followUp\":").append(JsonSerializer.toJson(followUp))
                    .append(",\"sourceOutput\":").append(JsonSerializer.toJson(sourceOutput))
                    .append(",\"followUpOutput\":").append(JsonSerializer.toJson(followUpOutput))
                    .append(",\"passed\":").append(passed)
                    .append('}');
        }
        json.append(']');
        return json.toString();
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

    /** "examples/order/src/OrderUtil.java" -> "OrderUtil" (default-package simple class name). */
    private static String classNameOf(Path file) {
        String name = file.getFileName().toString();
        return name.endsWith(".java") ? name.substring(0, name.length() - ".java".length()) : name;
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
}
