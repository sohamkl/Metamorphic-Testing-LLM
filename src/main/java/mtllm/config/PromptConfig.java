package mtllm.config;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds all settings from prompt.txt after they have been parsed.
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
    private final int maxRepairAttempts;

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
            int maxRepairAttempts) {
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
        this.maxRepairAttempts = maxRepairAttempts;
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

    public int maxRepairAttempts() {
        return maxRepairAttempts;
    }

    private static String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}
