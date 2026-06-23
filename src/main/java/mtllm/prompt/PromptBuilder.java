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
            prompt.append("- Preserve the selected generation behavior.\n");
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

            if (config.mode().usesDeveloperMrHelpers()) {
                prompt.append("Developer-provided metamorphic helper file: ")
                        .append(config.developerMrFile() == null ? "not provided" : config.developerMrFile())
                        .append("\n");
                prompt.append("Developer follow-up method to call: ")
                        .append(config.developerFollowUpMethod())
                        .append("\n");
                prompt.append("Developer assertion method to call: ")
                        .append(config.developerAssertMethod())
                        .append("\n");
                prompt.append("Developer MR helper source:\n");
                prompt.append("```java\n").append(config.developerMrSource()).append("\n```\n\n");
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

        if (config.mode().usesDeveloperMrDataHelpers()) {
            appendDeveloperMrExecutedMtDataTask(prompt, config);
            return;
        }

        if (config.mode().generatesExecutedMtData()) {
            appendLlmExecutedMtDataTask(prompt, config);
            return;
        }

        if (config.mode().usesDeveloperMrHelpers()) {
            appendDeveloperMrJUnitTask(prompt, config);
        } else {
            appendJUnitTask(prompt, config);
        }
    }

    private static void appendDeveloperMrJUnitTask(StringBuilder prompt, PromptConfig config) {
        prompt.append("Generate a complete JUnit 5 test class with this exact public class name: ")
                .append(config.generatedClassName()).append(".\n");
        prompt.append("Selected workflow: JUnit test-suite generation with developer-defined MR helpers.\n");
        prompt.append("The developer has already written the follow-up transformation and assertion logic.\n");
        prompt.append("You must call these exact methods:\n");
        prompt.append("- SUT method: ").append(targetMethodCallName(config)).append("\n");
        prompt.append("- Follow-up transformation: ").append(config.developerFollowUpMethod()).append("\n");
        prompt.append("- Output relation assertion: ").append(config.developerAssertMethod()).append("\n\n");

        prompt.append("The class must contain:\n");
        prompt.append("- Individual JUnit 5 @Test methods with diverse deterministic candidate source inputs.\n");
        prompt.append("- Each @Test method must construct one concrete source input directly inside the test body or through small source-construction helpers.\n");
        prompt.append("- Each test must run the target SUT method on the source input.\n");
        prompt.append("- Each test must call the developer follow-up method to create the follow-up input.\n");
        prompt.append("- Each test must run the target SUT method on the follow-up input.\n");
        prompt.append("- Each test must call the developer assertion method with the source output and follow-up output.\n");
        prompt.append("- At least ").append(config.count()).append(" source-input test methods unless the input domain makes that impossible.\n");
        prompt.append("- JUnit 5 imports from org.junit.jupiter.api.Test only, unless another JUnit import is genuinely needed.\n");
        prompt.append("- No package declaration; import public SUT/helper classes by package name when needed.\n\n");

        prompt.append("Strict developer-MR JUnit rules:\n");
        prompt.append("- Always call the SUT method with its owning class name, for example ")
                .append(targetMethodCallName(config)).append("(source), not a bare method call.\n");
        prompt.append("- Always call the developer MR methods with their owning class names, for example ")
                .append(config.developerFollowUpMethod()).append("(source) and ")
                .append(config.developerAssertMethod()).append("(sourceOutput, followUpOutput).\n");
        prompt.append("- Do not use static imports for the SUT method or developer MR methods.\n");
        prompt.append("- Do not generate a generateFollowUp method.\n");
        prompt.append("- Do not generate an assertMetamorphicRelation or assertRelation method.\n");
        prompt.append("- Do not rewrite, duplicate, reinterpret, or inline the developer-provided MR helper logic.\n");
        prompt.append("- Do not use assertEquals, assertNotEquals, or other assertion calls directly unless they are inside the developer-provided assertion method already.\n");
        prompt.append("- The generated JUnit class should be only candidate source-input construction plus calls to the SUT and developer helper methods.\n");
        prompt.append("- Do not try to decide which tests pass or fail. The backend will run the candidates and split actual passing/failing results into separate files.\n");
        prompt.append("- Do not add inline comments that state computed totals, expected outputs, or follow-up outputs; they can be misleading when the SUT is buggy.\n\n");

        prompt.append("Generation criteria:\n");
        prompt.append("- Infer Java input and output types from the target method signature and SUT source.\n");
        prompt.append("- Source inputs should follow the input domain and include normal cases, boundary cases, and edge cases.\n");
        prompt.append("- Do not generate invalid inputs unless the input domain explicitly asks for invalid cases.\n");
        prompt.append("- Make object inputs by using visible constructors, builders, factories, or simple helper methods.\n");
        prompt.append("- Prefer readable deterministic fixtures over unseeded randomness.\n");
        prompt.append("- Output only Java code. No markdown fences and no explanation.\n");
    }

    private static void appendJUnitTask(StringBuilder prompt, PromptConfig config) {
        prompt.append("Generate a complete JUnit 5 test class with this exact public class name: ")
                .append(config.generatedClassName()).append(".\n");
        prompt.append("Selected workflow: full JUnit metamorphic candidate test generation with LLM-generated MR helpers.\n");
        prompt.append("The class must contain:\n");
        prompt.append("- Individual JUnit 5 @Test methods for diverse candidate source inputs.\n");
        prompt.append("- Do not use @ParameterizedTest, @MethodSource, @TestFactory, DynamicTest, or a candidateSources() provider in the final class.\n");
        prompt.append("- Each @Test method must construct one concrete source input directly inside the test body or through small source-construction helpers.\n");
        prompt.append("- A follow-up-input helper, for example generateFollowUp(source).\n");
        prompt.append("- A small helper that runs the SUT on the source and follow-up input, for example assertMetamorphicRelationFor(source).\n");
        prompt.append("- A relation assertion helper, for example assertMetamorphicRelation(sourceOutput, followUpOutput).\n");
        prompt.append("- At least ").append(config.count()).append(" diverse deterministic candidate test methods unless the input domain makes that impossible.\n");
        prompt.append("- JUnit 5 imports from org.junit.jupiter.api.Test and org.junit.jupiter.api.Assertions.\n");
        prompt.append("- No package declaration; import public SUT classes by package name when needed.\n\n");

        prompt.append("Generation criteria:\n");
        prompt.append("- Infer Java input and output types from the target method signature and SUT source.\n");
        prompt.append("- Generate candidate tests; do not try to decide which tests pass or fail.\n");
        prompt.append("- The backend will run the candidate class with JUnit and split actual passing/failing test methods into separate files.\n");
        prompt.append("- Include both normal cases and edge cases when they are valid under the input domain.\n");
        prompt.append("- Important: assertMetamorphicRelation must assert that the stated MR output relation holds, for example assertEquals(expected, actual). Do not use assertNotEquals to make violating cases pass.\n");
        prompt.append("- Each emitted @Test method must assert the original MR normally; do not invert assertions to make failures pass.\n");
        prompt.append("- Prefer readable deterministic fixtures over unseeded randomness.\n");
        prompt.append("- Include normal cases, boundary cases, and edge cases that make the MR meaningful.\n");
        prompt.append("- Do not add inline comments that state computed totals, expected outputs, or follow-up outputs; they can be misleading when the SUT is buggy.\n");
        prompt.append("- Do not generate invalid inputs unless the input domain explicitly asks for invalid cases.\n");
        prompt.append("- Make object inputs by using visible constructors, builders, factories, or simple helper methods.\n");
        prompt.append("- Do not test MR quality; implement the MR as stated.\n");
        prompt.append("- Output only Java code. No markdown fences and no explanation.\n");
    }

    private static void appendDeveloperMrExecutedMtDataTask(StringBuilder prompt, PromptConfig config) {
        prompt.append("Generate a complete, compilable Java data-generator class with this exact public class name: ")
                .append(config.generatedClassName()).append(".\n");
        prompt.append("Selected workflow: executed JSON data generation with developer-defined MR helpers.\n");
        prompt.append("This class generates candidate SOURCE INPUTS, then computes follow-up inputs and outputs by running real Java code.\n\n");

        prompt.append("Developer-owned helper methods that must be called exactly:\n");
        prompt.append("- Follow-up transformation: ").append(config.developerFollowUpMethod()).append("\n");
        prompt.append("- Relation assertion, available if you need to classify pass/fail: ")
                .append(config.developerAssertMethod()).append("\n\n");

        prompt.append("The class must contain:\n");
        prompt.append("- A public static generateSources() method that returns a List of source inputs.\n");
        prompt.append("- generateSources() must generate at least ").append(config.count()).append(" diverse source inputs.\n");
        appendSourceCountRules(prompt, config);
        prompt.append("- A main(String[] args) method that prints valid JSON to stdout only.\n");
        prompt.append("- The main method must, for each source input:\n");
        prompt.append("  1. call the target SUT method on the source input to compute sourceOutput\n");
        prompt.append("  2. call ").append(config.developerFollowUpMethod()).append(" to compute followUp\n");
        prompt.append("  3. call the target SUT method on followUp to compute followUpOutput\n");
        prompt.append("  4. call ").append(config.developerAssertMethod()).append(" to compute whether the MR passed\n");
        prompt.append("  5. print source, followUp, sourceOutput, followUpOutput, and passed in JSON\n\n");

        prompt.append("Strict developer-MR JSON rules:\n");
        prompt.append("- Do not invent sourceOutput or followUpOutput values manually.\n");
        prompt.append("- Do not generate your own follow-up transformation; call the developer method.\n");
        prompt.append("- Do not generate JUnit tests or assertions.\n");
        prompt.append("- The passed field must be computed by calling the developer assertion method and catching AssertionError.\n");
        prompt.append("- Do not print logs, explanations, markdown, or extra text to stdout.\n");
        prompt.append("- Use only the Java standard library and the SUT/support/helper classes included above.\n");
        prompt.append("- Do not import third-party JSON libraries such as Jackson, Gson, org.json, or JSON-P.\n");
        prompt.append("- Build JSON manually with StringBuilder and small helper methods.\n\n");

        prompt.append("JSON output rules:\n");
        prompt.append("- Print a JSON array in this exact shape: ");
        prompt.append("[{\"source\": <value>, \"followUp\": <value>, \"sourceOutput\": <value>, \"followUpOutput\": <value>, \"passed\": <boolean>}].\n");
        prompt.append("- Every top-level array element must include all five keys: \"source\", \"followUp\", \"sourceOutput\", \"followUpOutput\", and \"passed\".\n");
        prompt.append("- The array must contain at least ").append(config.count()).append(" top-level entries.\n");
        prompt.append("- If inputs are objects, serialize them as JSON objects matching their visible fields/getters.\n");
        prompt.append("- Serialize numeric outputs as JSON numbers when possible; serialize complex outputs as JSON objects or strings.\n\n");

        prompt.append("Generation criteria:\n");
        prompt.append("- Infer Java input and output types from the target method signature and SUT source.\n");
        prompt.append("- Source inputs should follow the input domain and include normal cases, boundary cases, and edge cases.\n");
        prompt.append("- Do not generate invalid inputs unless the input domain explicitly asks for invalid cases.\n");
        prompt.append("- Make object inputs by using visible constructors, builders, factories, or simple helper methods.\n");
        prompt.append("- Prefer readable deterministic fixtures over unseeded randomness.\n");
        prompt.append("- Output only Java code. No markdown fences and no explanation.\n");
    }

    private static void appendLlmExecutedMtDataTask(StringBuilder prompt, PromptConfig config) {
        prompt.append("Generate a complete, compilable Java data-generator class with this exact public class name: ")
                .append(config.generatedClassName()).append(".\n");
        prompt.append("Selected workflow: executed JSON data generation with LLM-generated MR helpers.\n");
        prompt.append("This class generates SOURCE INPUTS and FOLLOW-UP INPUTS, then computes outputs by running real Java code.\n\n");

        prompt.append("The class must contain:\n");
        prompt.append("- A public static generateSources() method that returns a List of source inputs.\n");
        prompt.append("- generateSources() must generate at least ").append(config.count()).append(" diverse source inputs.\n");
        appendSourceCountRules(prompt, config);
        prompt.append("- A generateFollowUp(source) method that transforms each source input according to MRInput.\n");
        prompt.append("- An assertMetamorphicRelation(sourceOutput, followUpOutput) method that checks MROutput.\n");
        prompt.append("- A main(String[] args) method that prints valid JSON to stdout only.\n");
        prompt.append("- The main method must, for each source input:\n");
        prompt.append("  1. call generateFollowUp(source) to compute followUp\n");
        prompt.append("  2. call the target SUT method on source to compute sourceOutput\n");
        prompt.append("  3. call the target SUT method on followUp to compute followUpOutput\n");
        prompt.append("  4. call assertMetamorphicRelation(sourceOutput, followUpOutput) to compute whether the MR passed\n");
        prompt.append("  5. print source, followUp, sourceOutput, followUpOutput, and passed in JSON\n\n");

        prompt.append("Strict LLM-MR JSON rules:\n");
        prompt.append("- Do not invent sourceOutput or followUpOutput values manually.\n");
        prompt.append("- Do not generate JUnit tests.\n");
        prompt.append("- The passed field must be computed by calling assertMetamorphicRelation and catching AssertionError.\n");
        prompt.append("- Do not use assertNotEquals or inverted logic to make violating cases pass.\n");
        prompt.append("- Do not print logs, explanations, markdown, or extra text to stdout.\n");
        prompt.append("- Use only the Java standard library and the SUT/support classes included above.\n");
        prompt.append("- Do not import third-party JSON libraries such as Jackson, Gson, org.json, or JSON-P.\n");
        prompt.append("- Build JSON manually with StringBuilder and small helper methods.\n\n");

        prompt.append("JSON output rules:\n");
        prompt.append("- Print a JSON array in this exact shape: ");
        prompt.append("[{\"source\": <value>, \"followUp\": <value>, \"sourceOutput\": <value>, \"followUpOutput\": <value>, \"passed\": <boolean>}].\n");
        prompt.append("- Every top-level array element must include all five keys: \"source\", \"followUp\", \"sourceOutput\", \"followUpOutput\", and \"passed\".\n");
        prompt.append("- The array must contain at least ").append(config.count()).append(" top-level entries.\n");
        prompt.append("- If inputs are objects, serialize them as JSON objects matching their visible fields/getters.\n");
        prompt.append("- Serialize numeric outputs as JSON numbers when possible; serialize complex outputs as JSON objects or strings.\n\n");

        prompt.append("Generation criteria:\n");
        prompt.append("- Infer Java input and output types from the target method signature and SUT source.\n");
        prompt.append("- Source inputs should follow the input domain and include normal cases, boundary cases, and edge cases.\n");
        prompt.append("- Follow-up inputs must follow MRInput and must actually transform the source input unless the MR allows identity cases.\n");
        prompt.append("- assertMetamorphicRelation must implement MROutput as stated.\n");
        prompt.append("- Do not generate invalid inputs unless the input domain explicitly asks for invalid cases.\n");
        prompt.append("- Make object inputs by using visible constructors, builders, factories, or simple helper methods.\n");
        prompt.append("- Prefer readable deterministic fixtures over unseeded randomness.\n");
        prompt.append("- Output only Java code. No markdown fences and no explanation.\n");
    }

    private static void appendSourceCountRules(StringBuilder prompt, PromptConfig config) {
        prompt.append("- The generated code must not stop below the requested count. If Count is large, use deterministic loops, helper factories, or small parameter grids instead of hand-writing every case.\n");
        prompt.append("- Before returning or printing results, make sure the source list contains at least ")
                .append(config.count())
                .append(" entries; add more valid varied cases if needed.\n");
        prompt.append("- It is acceptable to generate more than ")
                .append(config.count())
                .append(" entries, but never fewer.\n");
    }

    private static String targetMethodCallName(PromptConfig config) {
        String targetFunction = config.targetFunction().trim();
        if (targetFunction.isBlank()) {
            return "the target SUT method";
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

        String className = classNameFromPath(config.sutClassFile());
        if (className.isBlank()) {
            return methodReference;
        }
        return className + "." + methodReference;
    }

    private static String classNameFromPath(java.nio.file.Path path) {
        if (path == null) {
            return "";
        }
        String fileName = path.getFileName().toString();
        if (fileName.endsWith(".java")) {
            return fileName.substring(0, fileName.length() - ".java".length());
        }
        return fileName;
    }

}
