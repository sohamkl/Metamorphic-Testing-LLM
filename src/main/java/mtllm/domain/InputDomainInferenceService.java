package mtllm.domain;

import mtllm.config.InputDomainRequirements;
import mtllm.config.InputDomainRequirementsParser;
import mtllm.config.PromptConfig;
import mtllm.llm.LlmClient;
import mtllm.sut.SutContext;
import mtllm.util.CodeFence;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

/** Infers a validated structured input domain when prompt.yaml does not provide one. */
public final class InputDomainInferenceService {
    private static final int MAX_ATTEMPTS = 2;

    private final LlmClient llmClient;

    public InputDomainInferenceService(LlmClient llmClient) {
        this.llmClient = llmClient;
    }

    public InferenceResult infer(PromptConfig config, SutContext context) throws Exception {
        String prompt = buildPrompt(config, context);
        Exception lastFailure = null;
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            String response = llmClient.complete(prompt);
            try {
                InputDomainRequirements requirements = parse(response, config.count());
                Path artifact = writeArtifact(config.outputRoot(), requirements);
                return new InferenceResult(requirements, artifact);
            } catch (RuntimeException failure) {
                lastFailure = failure;
                prompt = buildRepairPrompt(prompt, response, failure.getMessage());
            }
        }
        throw new IllegalStateException(
                "Could not infer a valid structured InputDomain after " + MAX_ATTEMPTS + " attempts.",
                lastFailure);
    }

    static String buildPrompt(PromptConfig config, SutContext context) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Infer a concise, measurable source-input domain for metamorphic testing.\n");
        prompt.append("Ground every constraint and scenario in the supplied Java API, source, and metamorphic relation.\n");
        prompt.append("Do not invent constructors, methods, fields, business rules, or invalid-input behavior.\n");
        prompt.append("Count is an upper limit; scenario targetCases must total no more than ")
                .append(config.count()).append(".\n\n");
        if (!config.sutDescription().isBlank()) {
            prompt.append("SUT description:\n").append(config.sutDescription()).append("\n\n");
        }
        prompt.append("Target method:\n").append(config.targetFunction()).append("\n\n");
        prompt.append("Metamorphic relation:\n")
                .append(config.metamorphicRelationStatement()).append("\n\n");
        if (!context.apiDescription().isBlank()) {
            prompt.append("Discovered API and construction metadata:\n")
                    .append(context.apiDescription()).append("\n\n");
        }
        prompt.append("SUT source:\n```java\n")
                .append(context.classSource()).append("\n```\n\n");
        prompt.append("Return only YAML in exactly this shape:\n")
                .append("InputDomain:\n")
                .append("  summary: concise domain summary\n")
                .append("  globalConstraints:\n")
                .append("    - measurable validity constraint\n")
                .append("  diversity:\n")
                .append("    dimensionName: [distinct, measurable, values]\n")
                .append("  scenarios:\n")
                .append("    - id: UPPER_SNAKE_CASE_ID\n")
                .append("      category: NORMAL\n")
                .append("      description: observable source-input situation\n")
                .append("      preconditions:\n")
                .append("        - measurable condition on the source input\n")
                .append("      expectedSourceBehavior:\n")
                .append("        - observable behavior useful for avoiding vacuous tests\n")
                .append("      targetCases: 1\n")
                .append("      emptyOutputAllowed: false\n\n")
                .append("Use only NORMAL, BOUNDARY, EDGE, or INVALID categories. Include INVALID only when the MR requests invalid inputs. ")
                .append("Prefer several behaviorally distinct scenarios over cosmetic value changes.");
        return prompt.toString();
    }

    private static String buildRepairPrompt(String original, String response, String error) {
        return original + "\n\nThe previous YAML was invalid:\n" + response
                + "\n\nValidation error: " + error
                + "\nReturn a corrected YAML document only.";
    }

    static InputDomainRequirements parse(String response, int count) {
        Object loaded = new Yaml().load(CodeFence.strip(response));
        if (!(loaded instanceof Map<?, ?> root)) {
            throw new IllegalArgumentException("The inferred domain must be a YAML mapping.");
        }
        if (root.containsKey("InputDomain") && root.size() != 1) {
            throw new IllegalArgumentException("The inferred YAML may contain only the InputDomain field.");
        }
        Object rawDomain = root.containsKey("InputDomain") ? root.get("InputDomain") : root;
        InputDomainRequirements requirements = InputDomainRequirementsParser.parse(rawDomain, "", count);
        if (!requirements.isStructured() || requirements.scenarios().isEmpty()) {
            throw new IllegalArgumentException("The inferred domain must contain at least one scenario.");
        }
        return requirements;
    }

    public static InputDomainRequirements readArtifact(Path artifact, int count) throws Exception {
        return parse(Files.readString(artifact, StandardCharsets.UTF_8), count);
    }

    private static Path writeArtifact(Path outputRoot, InputDomainRequirements requirements) throws Exception {
        Path directory = outputRoot.resolve("input-domain");
        Files.createDirectories(directory);
        Path artifact = directory.resolve("inferred-input-domain.yaml");

        Map<String, Object> domain = new LinkedHashMap<>();
        domain.put("summary", requirements.summary());
        domain.put("globalConstraints", requirements.globalConstraints());
        domain.put("diversity", requirements.diversityDimensions());
        domain.put("scenarios", requirements.scenarios().stream().map(scenario -> {
            Map<String, Object> value = new LinkedHashMap<>();
            value.put("id", scenario.id());
            value.put("category", scenario.category().name());
            value.put("description", scenario.description());
            value.put("preconditions", scenario.preconditions());
            value.put("expectedSourceBehavior", scenario.expectedSourceBehavior());
            value.put("targetCases", scenario.targetCases());
            value.put("emptyOutputAllowed", scenario.emptyOutputAllowed());
            return value;
        }).toList());
        Map<String, Object> document = Map.of("InputDomain", domain);

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        String yaml = new Yaml(options).dump(document);
        Files.writeString(artifact, yaml, StandardCharsets.UTF_8);
        return artifact;
    }

    public record InferenceResult(InputDomainRequirements requirements, Path artifact) {
    }
}
