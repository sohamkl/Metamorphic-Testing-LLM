package mtllm.generation;

import mtllm.config.PromptConfig;
import mtllm.sut.JavaSourceNames;

import java.util.regex.Pattern;

/**
 * Normalizes generated developer-MR JUnit code so method calls point to their owning classes.
 *
 * <p>In simple terms, this fixes generated tests that call {@code generateFollowUp(source)}
 * when they should call {@code SomeMetamorphicSpec.generateFollowUp(source)}.</p>
 */
public final class GeneratedJUnitCallQualifier {
    private GeneratedJUnitCallQualifier() {
    }

    public static String qualifyDeveloperMrCalls(String javaCode, PromptConfig config) {
        if (!config.mode().generatesJUnit() || !config.mode().usesDeveloperMrHelpers()) {
            return javaCode;
        }

        String normalized = javaCode;
        normalized = qualifyCall(normalized, sutCall(config));
        normalized = qualifyCall(normalized, MethodCall.fromQualifiedName(config.developerFollowUpMethod()));
        normalized = qualifyCall(normalized, MethodCall.fromQualifiedName(config.developerAssertMethod()));
        return normalized;
    }

    private static MethodCall sutCall(PromptConfig config) {
        String targetFunction = config.targetFunction().trim();
        if (targetFunction.isBlank()) {
            return MethodCall.empty();
        }

        String withoutParameters = targetFunction;
        int openParen = withoutParameters.indexOf('(');
        if (openParen >= 0) {
            withoutParameters = withoutParameters.substring(0, openParen).trim();
        }

        String[] tokens = withoutParameters.split("\\s+");
        String methodReference = tokens[tokens.length - 1].trim();
        if (methodReference.contains(".")) {
            return MethodCall.fromQualifiedName(methodReference);
        }

        return new MethodCall(JavaSourceNames.qualifiedName(config.sutClassFile()), methodReference);
    }

    private static String qualifyCall(String javaCode, MethodCall call) {
        if (call.isEmpty()) {
            return javaCode;
        }

        String normalized = removeStaticImport(javaCode, call);
        String bareCallPattern = "(?<![A-Za-z0-9_\\.])" + Pattern.quote(call.methodName()) + "\\s*\\(";
        return normalized.replaceAll(bareCallPattern, call.className() + "." + call.methodName() + "(");
    }

    private static String removeStaticImport(String javaCode, MethodCall call) {
        String importPattern = "(?m)^\\s*import\\s+static\\s+"
                + Pattern.quote(call.className() + "." + call.methodName())
                + "\\s*;\\s*\\R?";
        return javaCode.replaceAll(importPattern, "");
    }

    private record MethodCall(String className, String methodName) {
        static MethodCall empty() {
            return new MethodCall("", "");
        }

        static MethodCall fromQualifiedName(String qualifiedName) {
            String value = qualifiedName == null ? "" : qualifiedName.trim();
            int lastDot = value.lastIndexOf('.');
            if (lastDot <= 0 || lastDot == value.length() - 1) {
                return empty();
            }
            return new MethodCall(value.substring(0, lastDot), value.substring(lastDot + 1));
        }

        boolean isEmpty() {
            return className.isBlank() || methodName.isBlank();
        }
    }
}
