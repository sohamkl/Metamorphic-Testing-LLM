package mtllm.runner;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/** Merges additive missing-scenario tests into an existing generated JUnit class. */
final class GeneratedJUnitScenarioMerger {
    private GeneratedJUnitScenarioMerger() {
    }

    static String merge(String existingCode, String additionCode, String className, Map<String, Integer> missing) {
        CompilationUnit existing = StaticJavaParser.parse(existingCode);
        CompilationUnit addition = StaticJavaParser.parse(additionCode);
        ClassOrInterfaceDeclaration existingClass = findClass(existing, className, "existing");
        ClassOrInterfaceDeclaration additionClass = findClass(addition, className, "addition");

        Set<String> imports = new HashSet<>();
        existing.getImports().forEach(value -> imports.add(value.toString()));
        for (ImportDeclaration value : addition.getImports()) {
            if (imports.add(value.toString())) {
                existing.addImport(value.clone());
            }
        }

        Set<String> methodSignatures = new HashSet<>();
        existingClass.getMethods().forEach(method -> methodSignatures.add(method.getSignature().asString()));
        Map<String, Integer> addedByScenario = new java.util.LinkedHashMap<>();
        missing.keySet().forEach(id -> addedByScenario.put(id, 0));

        for (MethodDeclaration method : additionClass.getMethods()) {
            if (isTest(method)) {
                String scenario = matchingScenario(method.getNameAsString(), missing);
                if (scenario == null) {
                    continue;
                }
                if (addedByScenario.getOrDefault(scenario, 0) >= missing.get(scenario)) {
                    continue;
                }
                if (!methodSignatures.add(method.getSignature().asString())) {
                    throw new IllegalArgumentException(
                            "Missing-scenario patch repeats method " + method.getNameAsString() + ".");
                }
                existingClass.addMember(method.clone());
                addedByScenario.computeIfPresent(scenario, (ignored, count) -> count + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : missing.entrySet()) {
            int added = addedByScenario.getOrDefault(entry.getKey(), 0);
            if (added < entry.getValue()) {
                throw new IllegalArgumentException("Missing-scenario patch supplied " + added + " of "
                        + entry.getValue() + " required test(s) for " + entry.getKey() + ".");
            }
        }

        Set<String> existingMembers = new HashSet<>();
        existingClass.getMembers().forEach(member -> existingMembers.add(member.toString()));
        for (BodyDeclaration<?> member : additionClass.getMembers()) {
            if (member instanceof MethodDeclaration method && isTest(method)) {
                continue;
            }
            if (member instanceof MethodDeclaration method
                    && methodSignatures.contains(method.getSignature().asString())) {
                continue;
            }
            if (existingMembers.add(member.toString())) {
                if (member instanceof MethodDeclaration method) {
                    methodSignatures.add(method.getSignature().asString());
                }
                existingClass.addMember(member.clone());
            }
        }
        return existing.toString();
    }

    private static ClassOrInterfaceDeclaration findClass(
            CompilationUnit unit, String className, String sourceName) {
        return unit.getClassByName(className)
                .orElseThrow(() -> new IllegalArgumentException(
                        "The " + sourceName + " Java does not contain class " + className + "."));
    }

    private static boolean isTest(MethodDeclaration method) {
        return method.getAnnotations().stream()
                .anyMatch(annotation -> annotation.getNameAsString().equals("Test"));
    }

    private static String matchingScenario(String methodName, Map<String, Integer> missing) {
        String normalizedName = normalized(methodName);
        return missing.keySet().stream()
                .filter(id -> normalizedName.contains(normalized(id)))
                .findFirst()
                .orElse(null);
    }

    private static String normalized(String value) {
        return value.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
    }
}
