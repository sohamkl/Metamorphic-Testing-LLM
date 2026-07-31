package mtllm.config;

/**
 * Where the source inputs come from.
 *
 * <p>In simple terms: {@code LLM} = the model generates the inputs; {@code RANDOOP} = raw Randoop
 * (feedback-directed random generation, default value pool only); {@code HYBRID} = the LLM suggests
 * domain-relevant seed values and Randoop builds objects from them; {@code NEW_HYBRID} reverses
 * that collaboration, using Randoop examples to ground the LLM's final input generation. This is
 * orthogonal to who owns the metamorphic relation ({@link MRProvider}) and to the JSON/test-suite
 * output choices.</p>
 */
public enum InputGenerator {
    LLM,
    RANDOOP,
    HYBRID,
    NEW_HYBRID;

    public static InputGenerator fromConfig(String raw) {
        String value = raw == null ? "" : raw.trim();
        if (value.isEmpty()) {
            return LLM;
        }
        switch (value.toUpperCase()) {
            case "LLM":
                return LLM;
            case "RANDOOP":
                return RANDOOP;
            case "HYBRID":
                return HYBRID;
            case "NEW_HYBRID":
                return NEW_HYBRID;
            default:
                throw new IllegalArgumentException(
                        "Invalid InputGenerator: " + raw
                                + ". Use LLM, RANDOOP, HYBRID, or NEW_HYBRID.");
        }
    }

    /** True when Randoop itself produces the final inputs (raw or LLM-seeded). */
    public boolean usesRandoop() {
        return this == RANDOOP || this == HYBRID;
    }

    /** True when the LLM should seed Randoop's value pool (HYBRID only). */
    public boolean seedsWithLlm() {
        return this == HYBRID;
    }

    /** True when Randoop examples should ground the LLM's final source-input generation. */
    public boolean randoopSeedsLlm() {
        return this == NEW_HYBRID;
    }
}
