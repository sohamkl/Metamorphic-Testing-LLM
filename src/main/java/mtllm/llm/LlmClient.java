package mtllm.llm;

/**
 * Small interface for anything that can send a prompt to an LLM and return text.
 *
 * <p>In simple terms, this lets the rest of the backend use OpenAI today and another provider
 * later without changing the orchestration code.</p>
 */
public interface LlmClient {
    String complete(String prompt) throws Exception;

    /**
     * Tokens billed across every call this client has made, including repair attempts.
     *
     * <p>Defaults to empty so test fakes and any non-OpenAI client keep working unchanged.</p>
     */
    default TokenUsage tokenUsage() {
        return TokenUsage.EMPTY;
    }
}
