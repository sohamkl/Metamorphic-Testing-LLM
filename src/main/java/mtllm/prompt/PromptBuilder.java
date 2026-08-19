package mtllm.prompt;

import mtllm.config.PromptConfig;
import mtllm.domain.SourceScenarioPlanner;
import mtllm.sut.JavaSourceNames;
import mtllm.runner.TestRunResult;
import mtllm.sut.SutContext;

import java.util.Map;

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

    public static String buildMissingScenarioRepairPrompt(
            PromptConfig config,
            SutContext sutContext,
            String existingCode,
            Map<String, Integer> missingScenarios) {
        return buildMissingScenarioRepairPrompt(
                config, sutContext, existingCode, missingScenarios, "", null);
    }

    public static String buildMissingScenarioRepairPrompt(
            PromptConfig config,
            SutContext sutContext,
            String existingCode,
            Map<String, Integer> missingScenarios,
            String previousAddition,
            TestRunResult previousFailure) {
        StringBuilder prompt = new StringBuilder();
        appendSutSection(prompt, config, sutContext);
        prompt.append("The following generated JUnit class already contains valid candidate tests.\n")
                .append("Do not rewrite, remove, rename, or repeat any existing test or helper.\n")
                .append("```java\n").append(existingCode).append("\n```\n\n")
                .append("Metamorphic relation:\n")
                .append(config.metamorphicRelationStatement()).append("\n\n");
        if (!config.inputDomain().isBlank()) {
            prompt.append("Structured input domain and scenario requirements:\n")
                    .append(config.inputDomain()).append("\n\n");
        }
        prompt.append("Add only these missing source scenarios:\n");
        missingScenarios.forEach((id, needed) -> prompt.append("- ")
                .append(id).append(": ").append(needed).append(" additional @Test method(s)\n"));
        if (!previousAddition.isBlank()) {
            prompt.append("\nPrevious additive class that must be replaced:\n")
                    .append("```java\n").append(previousAddition).append("\n```\n");
        }
        if (previousFailure != null && !previousFailure.output().isBlank()) {
            prompt.append("\nValidation, compilation, or execution failure to correct:\n")
                    .append("```text\n").append(previousFailure.output()).append("\n```\n");
        }
        prompt.append("\nReturn a Java class named ").append(config.generatedClassName()).append(" containing only:\n")
                .append("- the requested new @Test methods, with the exact scenario ID in each method name\n")
                .append("- imports and genuinely new helper members required by those methods\n")
                .append("The addition class may call helpers shown in the existing class without repeating them.\n")
                .append("Every added test must construct a distinct valid source input and reach the same MR assertion path.\n")
                .append("Do not include unrelated scenarios or copies of existing methods.\n")
                .append("The backend will merge this addition into the existing class and revalidate the combined suite.\n")
                .append("Output only Java code. No markdown fences and no explanation.\n");
        return prompt.toString();
    }

    private static void appendSutSection(StringBuilder prompt, PromptConfig config, SutContext sutContext) {
        prompt.append("You are generating developer-reviewable JUnit 5 metamorphic tests for a Java SUT.\n\n");

        if (!config.sutDescription().isBlank()) {
            prompt.append("System Under Test description:\n");
            prompt.append(config.sutDescription()).append("\n\n");
        }

        if (sutContext.classFile() != null) {
            prompt.append("System Under Test class file: ").append(sutContext.classFile()).append("\n");
            if (!config.targetFunction().isBlank()) {
                prompt.append("Target method under test: ").append(config.targetFunction()).append("\n");
            }
            prompt.append("SUT source:\n");
            prompt.append("```java\n").append(sutContext.classSource()).append("\n```\n\n");

            if (!sutContext.apiDescription().isBlank()) {
                prompt.append("Framework-discovered API and construction metadata:\n");
                prompt.append("```text\n").append(sutContext.apiDescription()).append("\n```\n\n");
            }

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
        } else if (config.sutDescription().isBlank()) {
            prompt.append("System Under Test description:\n");
            prompt.append("No SUT class file was provided.\n\n");
        }
    }

    private static void appendTaskSection(StringBuilder prompt, PromptConfig config) {
        prompt.append("Metamorphic relation:\n");
        prompt.append(config.metamorphicRelationStatement()).append("\n\n");

        if (!config.inputDomain().isBlank()) {
            prompt.append(config.inputDomainRequirements().isStructured()
                    ? "Structured input domain and scenario requirements:\n"
                    : "Input domain and constraints:\n");
            prompt.append(config.inputDomain()).append("\n\n");
            appendStructuredScenarioRules(prompt, config);
        }

        if (!config.randoopSeedExamples().isBlank()) {
            if (config.inputGenerator().seedsWithLlm()) {
                appendHybridSourceExamples(prompt, config);
            } else if (config.inputGenerator().randoopSeedsLlm()) {
                appendRandoopSeedExamples(prompt, config);
            }
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

    private static void appendStructuredScenarioRules(StringBuilder prompt, PromptConfig config) {
        if (!config.inputDomainRequirements().isStructured()
                || config.inputDomainRequirements().scenarios().isEmpty()) {
            return;
        }
        prompt.append("Structured-scenario generation rules:\n");
        prompt.append("- Treat every scenario ID as a source-input coverage requirement, not as optional prose.\n");
        prompt.append("- Attempt the stated target case count for each scenario without exceeding Count overall.\n");
        prompt.append("- Include the scenario ID in every generated test method name, followed by a concise variation name.\n");
        prompt.append("- A scenario with 'Empty source output allowed: no' must be constructed to activate meaningful SUT output behavior.\n");
        prompt.append("- Vary the listed diversity dimensions across cases instead of changing only test names.\n");
        prompt.append("- Do not emit a detached inventory of scenario comments at class level.\n");
        prompt.append(config.inputGenerator().seedsWithLlm()
                ? "- For fixed HYBRID sources, use scenarios to validate and name the harvested cases; do not invent replacements.\n\n"
                : "- Scenario descriptions guide source-input construction and expected source behavior.\n\n");
        var sourcePlan = SourceScenarioPlanner.plan(config.inputDomainRequirements(), config.count());
        if (!sourcePlan.isEmpty()) {
            prompt.append("Backend-allocated source scenario plan:\n");
            sourcePlan.forEach(slot -> prompt.append("- ").append(slot).append("\n"));
            prompt.append("Generate one distinct source case for each listed slot. Treat dimension values as coverage goals, ")
                    .append("while satisfying the scenario preconditions and discovered Java API.\n\n");
        }
    }

    private static void appendHybridSourceExamples(StringBuilder prompt, PromptConfig config) {
        prompt.append("HYBRID final source inputs harvested by LLM-seeded Randoop:\n");
        prompt.append("```json\n").append(config.randoopSeedExamples()).append("\n```\n");
        prompt.append("These are the final source fixtures for this run, not examples for further generation.\n");
        prompt.append("Generate one candidate test for each distinct harvested source, up to Count.\n");
        prompt.append("Recreate each source from its constructionCode, or an exactly equivalent deterministic helper.\n");
        prompt.append("Do not invent, replace, or randomly vary the source inputs; the LLM owns only the MR code and test structure.\n");
        prompt.append("Use actual source behavior and the structured scenarios to choose accurate test names.\n");
        prompt.append("Skip a harvested source only when its constructionCode cannot produce a valid source object.\n\n");
    }

    private static void appendRandoopSeedExamples(StringBuilder prompt, PromptConfig config) {
        prompt.append("Randoop-generated source-input seed examples:\n");
        if (config.randoopSeedExamples().isBlank() || config.randoopSeedExamples().equals("[]")) {
            prompt.append("Randoop did not discover a usable source input in its bounded run.\n");
        } else {
            prompt.append("```json\n").append(config.randoopSeedExamples()).append("\n```\n");
        }
        prompt.append("These are API-grounding examples, not the complete final test set.\n");
        prompt.append("Use their runtime values and Java construction snippets to learn how valid source objects are built.\n");
        prompt.append("Generate additional diverse source inputs up to Count by varying values according to InputDomain.\n");
        prompt.append("Do not blindly copy invalid, exceptional, duplicate, or overly long Randoop sequences.\n");
        prompt.append("InputDomain remains authoritative when a seed conflicts with a stated constraint.\n\n");
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
        prompt.append("- At most ").append(config.count()).append(" source-input test methods.\n");
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
        prompt.append("- Never use Instant.now(), the current date/time, random values, or other runtime-dependent values in test fixtures; use fixed literals and derive related timestamps from the same fixed base value.\n");
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
        prompt.append("- At most ").append(config.count()).append(" diverse deterministic candidate test methods.\n");
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
        prompt.append("- Never use Instant.now(), the current date/time, random values, or other runtime-dependent values in test fixtures; use fixed literals and derive related timestamps from the same fixed base value.\n");
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
        prompt.append("- A public static method named generateSources() that returns a List of source inputs.\n");
        prompt.append("- generateSources() must generate at most ").append(config.count()).append(" diverse source inputs.\n");
        appendSourceCountRules(prompt, config);
        appendDeterministicSourceRules(prompt);
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
        prompt.append("- The array must contain no more than ").append(config.count()).append(" top-level entries.\n");
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
        prompt.append("- A public static method named generateSources() that returns a List of source inputs.\n");
        prompt.append("- generateSources() must generate at most ").append(config.count()).append(" diverse source inputs.\n");
        appendSourceCountRules(prompt, config);
        appendDeterministicSourceRules(prompt);
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
        prompt.append("- The array must contain no more than ").append(config.count()).append(" top-level entries.\n");
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
        prompt.append("- Treat Count as an upper limit, not a target or minimum.\n");
        prompt.append("- Before returning or printing results, make sure the source list contains no more than ")
                .append(config.count())
                .append(" entries.\n");
        prompt.append("- It is acceptable to generate fewer than ")
                .append(config.count())
                .append(" entries when the input domain is narrow, but never more.\n");
    }

    private static void appendDeterministicSourceRules(StringBuilder prompt) {
        prompt.append("- generateSources() must return equivalent values in the same order on every call and every JVM run.\n");
        prompt.append("- Use explicit, readable fixtures or immutable value tables for source inputs.\n");
        prompt.append("- Do not use Random, Math.random(), ThreadLocalRandom, SecureRandom, random UUIDs, current time, or other mutable/nondeterministic input generation.\n");
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

        String className = JavaSourceNames.qualifiedName(config.sutClassFile());
        if (className.isBlank()) {
            return methodReference;
        }
        return className + "." + methodReference;
    }

}
