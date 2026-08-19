package mtllm.runner;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import mtllm.config.InputGenerator;
import mtllm.config.PromptConfig;
import mtllm.config.ScenarioRequirement;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/** Static quality checks applied before generated JUnit is compiled and executed. */
final class GeneratedTestQualityGate {
    private GeneratedTestQualityGate() {
    }

    static String validate(Path generatedTestFile, PromptConfig config) {
        ValidationResult result = validateDetailed(generatedTestFile, config);
        return result.passed() ? null : result.error();
    }

    static ValidationResult validateDetailed(Path generatedTestFile, PromptConfig config) {
        CompilationUnit unit;
        try {
            unit = StaticJavaParser.parse(generatedTestFile);
        } catch (Exception failure) {
            return ValidationResult.failed("Generated Java could not be parsed: " + failure.getMessage());
        }

        List<MethodDeclaration> tests = unit.findAll(MethodDeclaration.class).stream()
                .filter(method -> method.getAnnotations().stream()
                        .anyMatch(annotation -> annotation.getNameAsString().equals("Test")))
                .toList();
        if (config.testSuiteRequired() && tests.isEmpty()) {
            return ValidationResult.failed("Generated class contains no JUnit @Test methods.");
        }
        if (tests.size() > config.count()) {
            return ValidationResult.failed(
                    "Expected at most " + config.count() + " JUnit test methods, found " + tests.size() + ".");
        }

        Set<String> bodies = new HashSet<>();
        for (MethodDeclaration test : tests) {
            String body = test.getBody().map(Object::toString).orElse("").replaceAll("\\s+", " ").trim();
            if (!bodies.add(body)) {
                return ValidationResult.failed(
                        "Duplicate generated @Test body found at method " + test.getNameAsString() + ".");
            }
        }

        List<MissingScenario> missingScenarios = List.of();
        if (config.inputGenerator() != InputGenerator.HYBRID) {
            missingScenarios = missingScenarioCoverage(tests, config);
        }

        Map<String, MethodDeclaration> methods = new HashMap<>();
        unit.findAll(MethodDeclaration.class).forEach(method -> methods.put(method.getNameAsString(), method));
        for (MethodDeclaration test : tests) {
            if (!reachesAssertion(test, methods, new HashSet<>(), developerAssertName(config))) {
                return ValidationResult.failed("Generated @Test method " + test.getNameAsString()
                        + " does not call an assertion directly or through a local helper.");
            }
        }
        if (!missingScenarios.isEmpty()) {
            String message = missingScenarios.stream()
                    .map(missing -> "Scenario " + missing.id() + " requires " + missing.required()
                            + " test method(s), but only " + missing.present()
                            + " method name(s) cover its ID.")
                    .collect(java.util.stream.Collectors.joining("\n"));
            return new ValidationResult(message, missingScenarios);
        }
        return ValidationResult.valid();
    }

    private static List<MissingScenario> missingScenarioCoverage(
            List<MethodDeclaration> tests, PromptConfig config) {
        if (!config.inputDomainRequirements().isStructured()) {
            return List.of();
        }
        List<MissingScenario> missing = new java.util.ArrayList<>();
        for (ScenarioRequirement scenario : config.inputDomainRequirements().scenarios()) {
            String expected = normalized(scenario.id());
            long matches = tests.stream()
                    .map(MethodDeclaration::getNameAsString)
                    .map(GeneratedTestQualityGate::normalized)
                    .filter(name -> name.contains(expected))
                    .count();
            if (matches < scenario.targetCases()) {
                missing.add(new MissingScenario(scenario.id(), scenario.targetCases(), (int) matches));
            }
        }
        return List.copyOf(missing);
    }

    private static boolean reachesAssertion(
            MethodDeclaration method,
            Map<String, MethodDeclaration> methods,
            Set<String> visited,
            String developerAssertName) {
        if (!visited.add(method.getNameAsString())) {
            return false;
        }
        for (MethodCallExpr call : method.findAll(MethodCallExpr.class)) {
            String name = call.getNameAsString();
            if (name.startsWith("assert") || name.equals("fail")
                    || (!developerAssertName.isBlank() && name.equals(developerAssertName))) {
                return true;
            }
            MethodDeclaration helper = methods.get(name);
            if (helper != null && reachesAssertion(helper, methods, visited, developerAssertName)) {
                return true;
            }
        }
        if (method.findAll(com.github.javaparser.ast.stmt.ThrowStmt.class).stream()
                .anyMatch(statement -> statement.toString().contains("AssertionError"))) {
            return true;
        }
        return false;
    }

    private static String developerAssertName(PromptConfig config) {
        String qualified = config.developerAssertMethod();
        int dot = qualified.lastIndexOf('.');
        return dot >= 0 ? qualified.substring(dot + 1) : qualified;
    }

    private static String normalized(String value) {
        return value.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
    }

    record MissingScenario(String id, int required, int present) {
        int needed() {
            return required - present;
        }
    }

    record ValidationResult(String error, List<MissingScenario> missingScenarios) {
        ValidationResult {
            error = error == null ? "" : error;
            missingScenarios = missingScenarios == null ? List.of() : List.copyOf(missingScenarios);
        }

        static ValidationResult valid() {
            return new ValidationResult("", List.of());
        }

        static ValidationResult failed(String error) {
            return new ValidationResult(error, List.of());
        }

        boolean passed() {
            return error.isBlank();
        }

        boolean onlyMissingScenarios() {
            return !passed() && !missingScenarios.isEmpty();
        }
    }
}
