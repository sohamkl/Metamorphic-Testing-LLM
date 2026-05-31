package mtllm.util;

/**
 * Removes markdown code fences from LLM output when the model includes them.
 *
 * <p>In simple terms, this helps turn ```java blocks into plain Java source code before writing
 * the generated test file.</p>
 */
public final class CodeFence {
    private CodeFence() {
    }

    public static String strip(String value) {
        String text = value == null ? "" : value.trim();
        if (!text.startsWith("```")) {
            return text;
        }
        int firstNewline = text.indexOf('\n');
        int lastFence = text.lastIndexOf("```");
        if (firstNewline >= 0 && lastFence > firstNewline) {
            return text.substring(firstNewline + 1, lastFence).trim();
        }
        return text;
    }
}
