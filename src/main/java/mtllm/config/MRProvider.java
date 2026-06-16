package mtllm.config;

/**
 * Describes who provides the metamorphic relation implementation.
 *
 * <p>In simple terms, DEV means the developer supplies Java MR helper methods.
 * LLM means the model writes the follow-up transformation and assertion code.</p>
 */
public enum MRProvider {
    DEV,
    LLM;

    public static MRProvider fromConfig(String raw) {
        String value = raw == null ? "LLM" : raw.trim();
        if (value.equals("DEV")) {
            return DEV;
        }
        if (value.equals("LLM")) {
            return LLM;
        }
        throw new IllegalArgumentException("Invalid MRProvider: " + raw + ". Use MRProvider: DEV or MRProvider: LLM.");
    }
}
