package mtllm.llm;

/**
 * Tokens billed for LLM work.
 *
 * <p>Input and output are kept apart because providers charge very differently for them, so a
 * single total cannot be turned into a cost.</p>
 */
public record TokenUsage(int promptTokens, int completionTokens, int totalTokens) {

    public static final TokenUsage EMPTY = new TokenUsage(0, 0, 0);

    /** Running total across several calls, so repair attempts are counted alongside the first try. */
    public TokenUsage plus(TokenUsage other) {
        return new TokenUsage(
                promptTokens + other.promptTokens,
                completionTokens + other.completionTokens,
                totalTokens + other.totalTokens);
    }
}
