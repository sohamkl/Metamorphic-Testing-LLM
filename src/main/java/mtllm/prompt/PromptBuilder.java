package mtllm.prompt;

import mtllm.config.PromptConfig;
import mtllm.runner.TestRunResult;
import mtllm.sut.SutContext;

/**
 * Builds the text prompts sent to the LLM.
 *
 * <p>In simple terms, this class explains the SUT, MR, and output requirements to the model,
 * including the repair prompt used after compile or test failures.</p>
 */
public final class PromptBuilder {
    private PromptBuilder() {
    }

    public static String buildInitialPrompt(PromptConfig config, SutContext sutContext) {
        StringBuilder prompt = new StringBuilder();
        appendSutSection(prompt, config, sutContext);
        appendTaskSection(prompt, config);
        return prompt.toString();
    }

    public static String buildRepairPrompt(
            PromptConfig config,
            SutContext sutContext,
            String previousCode,
            TestRunResult failure) {
        StringBuilder prompt = new StringBuilder();
        appendSutSection(prompt, config, sutContext);
        if (config.mode().generatesJUnit()) {
            prompt.append("Previous generated JUnit 5 test class:\n");
        } else {
            prompt.append("Previous generated Java data-generator class:\n");
        }
        prompt.append("```java\n").append(previousCode).append("\n```\n\n");
        prompt.append("The generated code did not compile, run, or validate successfully.\n");
        prompt.append("Failure output:\n");
        prompt.append("```text\n").append(failure.output()).append("\n```\n\n");
        prompt.append("Original task requirements that the corrected code must satisfy:\n");
        appendTaskSection(prompt, config);
        prompt.append("\n");
        prompt.append("Repair task:\n");
        if (config.mode().generatesJUnit()) {
            prompt.append("- Return a complete corrected JUnit 5 test class named ")
                    .append(config.generatedClassName()).append(".\n");
            prompt.append("- Preserve the intended metamorphic relation.\n");
            prompt.append("- Fix compilation errors, missing imports, invalid types, and failing test-run issues.\n");
        } else {
            prompt.append("- Return a complete corrected Java data-generator class named ")
                    .append(config.generatedClassName()).append(".\n");
            prompt.append("- Preserve the selected Mode ").append(config.mode().number()).append(" behavior.\n");
            prompt.append("- Keep main(String[] args) printing valid JSON to stdout only.\n");
            prompt.append("- Fix compilation errors, invalid types, runtime errors, and JSON validation issues.\n");
        }
        prompt.append("- Output only Java code. No markdown fences and no explanation.\n");
        return prompt.toString();
    }

    private static void appendSutSection(StringBuilder prompt, PromptConfig config, SutContext sutContext) {
        prompt.append("You are generating developer-reviewable JUnit 5 metamorphic tests for a Java SUT.\n\n");
        if (sutContext.classFile() != null) {
            prompt.append("System Under Test class file: ").append(sutContext.classFile()).append("\n");
            if (!config.targetFunction().isBlank()) {
                prompt.append("Target method under test: ").append(config.targetFunction()).append("\n");
            }
            prompt.append("SUT source:\n");
            prompt.append("```java\n").append(sutContext.classSource()).append("\n```\n\n");

            for (SutContext.SourceFile supportFile : sutContext.supportFiles()) {
                prompt.append("First-level dependency/support file: ").append(supportFile.path()).append("\n");
                prompt.append("```java\n").append(supportFile.source()).append("\n```\n\n");
            }
        } else {
            prompt.append("System Under Test description:\n");
            prompt.append(config.sutDescription().isBlank()
                    ? "No SUT class file was provided."
                    : config.sutDescription());
            prompt.append("\n\n");
        }
    }

    private static void appendTaskSection(StringBuilder prompt, PromptConfig config) {
        prompt.append("Metamorphic relation:\n");
        prompt.append(config.metamorphicRelationStatement()).append("\n\n");

        if (!config.inputDomain().isBlank()) {
            prompt.append("Input domain and constraints:\n");
            prompt.append(config.inputDomain()).append("\n\n");
        }

        if (!config.mode().generatesJUnit()) {
            appendDataGeneratorTask(prompt, config);
            return;
        }

        appendJUnitTask(prompt, config);
    }

    private static void appendJUnitTask(StringBuilder prompt, PromptConfig config) {
        prompt.append("Generate a complete JUnit 5 test class with this exact public class name: ")
                .append(config.generatedClassName()).append(".\n");
        prompt.append("Selected mode: Mode 3 - full JUnit metamorphic test generation.\n");
        prompt.append("The class must contain:\n");
        prompt.append("- A JUnit 5 @ParameterizedTest method that runs once for each source input.\n");
        prompt.append("- A @MethodSource provider named generateSources().\n");
        prompt.append("- A source-input generator helper, for example generateSources().\n");
        prompt.append("- A follow-up-input helper, for example generateFollowUp(source).\n");
        prompt.append("- A relation assertion helper, for example assertMetamorphicRelation(sourceOutput, followUpOutput).\n");
        prompt.append("- At least ").append(config.count()).append(" diverse deterministic source inputs.\n");
        prompt.append("- JUnit 5 imports from org.junit.jupiter.params.ParameterizedTest, org.junit.jupiter.params.provider.MethodSource, and org.junit.jupiter.api.Assertions.\n");
        prompt.append("- No package declaration; import public SUT classes by package name when needed.\n\n");

        prompt.append("Generation criteria:\n");
        prompt.append("- Infer Java input and output types from the target method signature and SUT source.\n");
        prompt.append("- The generated test report should show each source input as a separate parameterized test invocation.\n");
        prompt.append("- Prefer readable deterministic fixtures over unseeded randomness.\n");
        prompt.append("- Include normal cases, boundary cases, and edge cases that make the MR meaningful.\n");
        prompt.append("- Do not generate invalid inputs unless the input domain explicitly asks for invalid cases.\n");
        prompt.append("- Make object inputs by using visible constructors, builders, factories, or simple helper methods.\n");
        prompt.append("- Do not test MR quality; implement the MR as stated.\n");
        prompt.append("- Output only Java code. No markdown fences and no explanation.\n");
    }

    private static void appendDataGeneratorTask(StringBuilder prompt, PromptConfig config) {
        prompt.append("Generate a complete, compilable Java class with this exact public class name: ")
                .append(config.generatedClassName()).append(".\n");
        prompt.append("Selected mode: Mode ").append(config.mode().number()).append(" - ");
        if (config.mode().generatesFollowUpData()) {
            prompt.append("source and follow-up input data generation.\n");
        } else {
            prompt.append("source input data generation only.\n");
        }
        prompt.append("This class generates TEST DATA only. It must not run JUnit tests or assert anything.\n\n");

        prompt.append("The class must contain:\n");
        prompt.append("- A generateSources() method that returns a List of source test inputs.\n");
        prompt.append("- generateSources() must generate at least ").append(config.count()).append(" diverse source inputs.\n");
        prompt.append("- Source inputs must follow the input domain and include meaningful edge cases.\n");
        if (config.mode().generatesFollowUpData()) {
            prompt.append("- A generateFollowUp(source) method that transforms each source input according to the MR input relation.\n");
            prompt.append("- generateFollowUp(source) must actually transform the source input, not simply return it unchanged.\n");
        }
        prompt.append("- A main(String[] args) method that prints valid JSON to stdout only.\n\n");

        prompt.append("Dependency rules:\n");
        prompt.append("- Use only the Java standard library and the SUT/support classes included above.\n");
        prompt.append("- Do not import third-party JSON libraries such as Jackson, Gson, org.json, or JSON-P.\n");
        prompt.append("- Build JSON manually with StringBuilder and small helper methods.\n\n");

        prompt.append("JSON output rules:\n");
        if (config.mode().generatesFollowUpData()) {
            prompt.append("- Print a JSON array in this exact shape: [{\"source\": <value>, \"followUp\": <value>}].\n");
            prompt.append("- Every top-level array element must be a wrapper object with both \"source\" and \"followUp\" keys.\n");
            prompt.append("- Each source object must have exactly one matching followUp object.\n");
        } else {
            prompt.append("- Print a JSON array in this exact shape: [{\"source\": <value>}].\n");
            prompt.append("- Every top-level array element must be a wrapper object with a \"source\" key.\n");
            prompt.append("- Do not include followUp fields in Mode 1.\n");
        }
        prompt.append("- Do not print raw inputs directly. For example, use {\"source\":{\"items\":[]}}, not {\"items\":[]}.\n");
        prompt.append("- The array must contain at least ").append(config.count()).append(" top-level entries.\n");
        prompt.append("- If inputs are objects, serialize them as JSON objects matching their visible fields/getters.\n");
        prompt.append("- Do not print logs, explanations, markdown, or extra text to stdout.\n\n");

        prompt.append("Generation criteria:\n");
        prompt.append("- Infer Java input types from the target method signature and SUT source.\n");
        prompt.append("- You may import or construct SUT input types, but do not call the target method under test.\n");
        prompt.append("- Do not introduce dependencies that are not already visible in the SUT/support source.\n");
        prompt.append("- Prefer readable deterministic generation logic over unseeded randomness.\n");
        prompt.append("- Use loops, helper methods, or fixed-seed Random where useful; avoid fragile one-off code.\n");
        prompt.append("- Output only Java code. No markdown fences and no explanation.\n");
    }
}
