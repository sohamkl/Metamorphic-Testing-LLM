package mtllm.util;

/** Shared naming rules for generated data, test, and report artifacts. */
public final class GeneratedNames {
    private GeneratedNames() {
    }

    public static String baseName(String generatedClassName) {
        if (generatedClassName.endsWith("Data")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Data".length());
        }
        if (generatedClassName.endsWith("Test")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Test".length());
        }
        return generatedClassName;
    }
}
