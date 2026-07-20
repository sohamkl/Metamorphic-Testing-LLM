package mtllm.config;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds all settings from prompt.yaml after they have been parsed.
 *
 * <p>In simple terms, this class is the typed version of the user's test-generation request:
 * which SUT to test, which method to focus on, what MR to use, and how many examples to ask for.</p>
 */
public final class PromptConfig {
    private final Path sutClassFile;
    private final String targetFunction;
    private final List<Path> sutSupportFiles;
    private final String sutDescription;
    private final String mrInput;
    private final String mrOutput;
    private final String mr;
    private final int count;
    private final String inputDomain;
    private final String generatedClassName;
    private final GenerationMode mode;
    private final boolean jsonRequired;
    private final boolean testSuiteRequired;
    private final MRProvider mrProvider;
    private final Path developerMrFile;
    private final String developerMrSource;
    private final String developerFollowUpMethod;
    private final String developerAssertMethod;
    private final Path outputRoot;
    private final int maxRepairAttempts;
    private final InputGenerator inputGenerator;
    private final List<String> randoopTargetClasses;

    public PromptConfig(
            Path sutClassFile,
            String targetFunction,
            List<Path> sutSupportFiles,
            String sutDescription,
            String mrInput,
            String mrOutput,
            String mr,
            int count,
            String inputDomain,
            String generatedClassName,
            GenerationMode mode,
            boolean jsonRequired,
            boolean testSuiteRequired,
            MRProvider mrProvider,
            Path developerMrFile,
            String developerMrSource,
            String developerFollowUpMethod,
            String developerAssertMethod,
            Path outputRoot,
            int maxRepairAttempts,
            InputGenerator inputGenerator,
            List<String> randoopTargetClasses) {
        this.sutClassFile = sutClassFile;
        this.targetFunction = valueOrEmpty(targetFunction);
        this.sutSupportFiles = Collections.unmodifiableList(new ArrayList<>(sutSupportFiles));
        this.sutDescription = valueOrEmpty(sutDescription);
        this.mrInput = valueOrEmpty(mrInput);
        this.mrOutput = valueOrEmpty(mrOutput);
        this.mr = valueOrEmpty(mr);
        this.count = count;
        this.inputDomain = valueOrEmpty(inputDomain);
        this.generatedClassName = valueOrEmpty(generatedClassName).isEmpty()
                ? "GeneratedMetamorphicTest"
                : generatedClassName.trim();
        this.mode = mode == null ? GenerationMode.FULL_JUNIT : mode;
        this.jsonRequired = jsonRequired;
        this.testSuiteRequired = testSuiteRequired;
        this.mrProvider = mrProvider == null ? MRProvider.LLM : mrProvider;
        this.developerMrFile = developerMrFile;
        this.developerMrSource = valueOrEmpty(developerMrSource);
        this.developerFollowUpMethod = valueOrEmpty(developerFollowUpMethod);
        this.developerAssertMethod = valueOrEmpty(developerAssertMethod);
        this.outputRoot = outputRoot;
        this.maxRepairAttempts = maxRepairAttempts;
        this.inputGenerator = inputGenerator == null ? InputGenerator.LLM : inputGenerator;
        this.randoopTargetClasses = Collections.unmodifiableList(new ArrayList<>(randoopTargetClasses));
    }

    public Path sutClassFile() {
        return sutClassFile;
    }

    public String targetFunction() {
        return targetFunction;
    }

    public List<Path> sutSupportFiles() {
        return sutSupportFiles;
    }

    public String sutDescription() {
        return sutDescription;
    }

    public String metamorphicRelationStatement() {
        if (!mrInput.isBlank() && !mrOutput.isBlank()) {
            return "If " + mrInput + ", then " + mrOutput + ".";
        }
        if (!mr.isBlank()) {
            return mr;
        }
        return "No metamorphic relation was specified.";
    }

    public int count() {
        return count;
    }

    public String inputDomain() {
        return inputDomain;
    }

    public String generatedClassName() {
        return generatedClassName;
    }

    public GenerationMode mode() {
        return mode;
    }

    public boolean jsonRequired() {
        return jsonRequired;
    }

    public boolean testSuiteRequired() {
        return testSuiteRequired;
    }

    public MRProvider mrProvider() {
        return mrProvider;
    }

    public Path developerMrFile() {
        return developerMrFile;
    }

    public String developerMrSource() {
        return developerMrSource;
    }

    public String developerFollowUpMethod() {
        return developerFollowUpMethod;
    }

    public String developerAssertMethod() {
        return developerAssertMethod;
    }

    public Path outputRoot() {
        return outputRoot;
    }

    public int maxRepairAttempts() {
        return maxRepairAttempts;
    }

    public InputGenerator inputGenerator() {
        return inputGenerator;
    }

    public List<String> randoopTargetClasses() {
        return randoopTargetClasses;
    }

    public PromptConfig withOutputMode(GenerationMode newMode, boolean newJsonRequired, boolean newTestSuiteRequired, String newGeneratedClassName) {
        return new PromptConfig(
                sutClassFile,
                targetFunction,
                sutSupportFiles,
                sutDescription,
                mrInput,
                mrOutput,
                mr,
                count,
                inputDomain,
                newGeneratedClassName,
                newMode,
                newJsonRequired,
                newTestSuiteRequired,
                mrProvider,
                developerMrFile,
                developerMrSource,
                developerFollowUpMethod,
                developerAssertMethod,
                outputRoot,
                maxRepairAttempts,
                inputGenerator,
                randoopTargetClasses);
    }

    private static String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}
