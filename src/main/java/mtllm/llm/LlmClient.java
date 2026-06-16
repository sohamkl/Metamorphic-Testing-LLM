package mtllm.llm;

/**
 * Small interface for anything that can send a prompt to an LLM and return text.
 *
 * <p>In simple terms, this lets the rest of the backend use OpenAI today and another provider
 * later without changing the orchestration code.</p>
 */
public interface LlmClient {
    String complete(String prompt) throws Exception;
}
