package mtllm.config;

/**
 * Where the source inputs come from.
 *
 * <p>In simple terms: {@code LLM} = the model generates the inputs; {@code RANDOOP} = raw Randoop
 * (feedback-directed random generation, default value pool only); {@code HYBRID} = the LLM suggests
 * domain-relevant seed values and Randoop builds objects from them. This is orthogonal to who owns
 * the metamorphic relation ({@link MRProvider}) and to the JSON/test-suite output choices.</p>
 */
public enum InputGenerator {
    LLM,
    RANDOOP,
    HYBRID;

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
            default:
                throw new IllegalArgumentException(
                        "Invalid InputGenerator: " + raw + ". Use LLM, RANDOOP, or HYBRID.");
        }
    }

    /** True for the Randoop-backed modes (RANDOOP = raw, HYBRID = LLM-seeded). */
    public boolean usesRandoop() {
        return this == RANDOOP || this == HYBRID;
    }

    /** True when the LLM should seed Randoop's value pool (HYBRID only). */
    public boolean seedsWithLlm() {
        return this == HYBRID;
    }
}
