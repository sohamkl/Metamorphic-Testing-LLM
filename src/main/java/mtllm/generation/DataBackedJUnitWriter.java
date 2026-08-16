package mtllm.generation;

import mtllm.config.PromptConfig;
import mtllm.runner.DataGeneratorRunner;
import mtllm.sut.JavaSourceNames;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes JUnit tests that reuse the generated Java data class as their source of inputs.
 *
 * <p>In simple terms, this keeps JSON and JUnit consistent: both outputs use the same
 * {@code generateSources()} method instead of asking the LLM to invent two separate test sets.</p>
 */
public final class DataBackedJUnitWriter {
    private DataBackedJUnitWriter() {
    }

    public static Path write(
            Path outputDir,
            PromptConfig junitConfig,
            String dataClassName,
            DataGeneratorRunner.ExecutedDataSummary dataSummary) throws IOException {
        Files.createDirectories(outputDir);
        Path outputFile = outputDir.resolve(junitConfig.generatedClassName() + ".java");
        Files.writeString(
                outputFile,
                render(junitConfig, dataClassName, dataSummary),
                StandardCharsets.UTF_8);
        return outputFile;
    }

    private static String render(
            PromptConfig config,
            String dataClassName,
            DataGeneratorRunner.ExecutedDataSummary dataSummary) throws IOException {
        StringBuilder out = new StringBuilder();
        out.append("import org.junit.jupiter.api.Test;\n\n");
        out.append("public class ").append(config.generatedClassName()).append(" {\n");

        for (int index : dataSummary.allIndexes()) {
            appendTestMethod(out, config, dataClassName, index);
        }

        out.append("}\n");
        return out.toString();
    }

    private static void appendTestMethod(
            StringBuilder out,
            PromptConfig config,
            String dataClassName,
            int sourceIndex) throws IOException {
        out.append("\n    @Test\n");
        out.append("    public void testGeneratedCase")
                .append(String.format("%03d", sourceIndex + 1))
                .append("() {\n");
        out.append("        var source = ")
                .append(dataClassName)
                .append(".generateSources().get(")
                .append(sourceIndex)
                .append(");\n");
        out.append("        var sourceOutput = ")
                .append(targetMethodCallName(config))
                .append("(source);\n");
        out.append("        var followUp = ")
                .append(followUpCallName(config, dataClassName))
                .append("(source);\n");
        out.append("        var followUpOutput = ")
                .append(targetMethodCallName(config))
                .append("(followUp);\n");
        out.append("        ")
                .append(assertCallName(config, dataClassName))
                .append("(sourceOutput, followUpOutput);\n");
        out.append("    }\n");
    }

    private static String followUpCallName(PromptConfig config, String dataClassName) {
        if (config.mode().usesDeveloperMrHelpers()) {
            return config.developerFollowUpMethod();
        }
        return dataClassName + ".generateFollowUp";
    }

    private static String assertCallName(PromptConfig config, String dataClassName) {
        if (config.mode().usesDeveloperMrHelpers()) {
            return config.developerAssertMethod();
        }
        return dataClassName + ".assertMetamorphicRelation";
    }

    private static String targetMethodCallName(PromptConfig config) throws IOException {
        String targetFunction = config.targetFunction().trim();
        if (targetFunction.isBlank()) {
            return "/* missing target method */";
        }

        String withoutParameters = targetFunction;
        int openParen = withoutParameters.indexOf('(');
        if (openParen >= 0) {
            withoutParameters = withoutParameters.substring(0, openParen).trim();
        }

        String[] tokens = withoutParameters.split("\\s+");
        String methodReference = tokens[tokens.length - 1].trim();
        if (methodReference.contains(".")) {
            return methodReference;
        }

        String className = JavaSourceNames.qualifiedName(config.sutClassFile());
        if (className.isBlank()) {
            return methodReference;
        }
        return className + "." + methodReference;
    }

}
