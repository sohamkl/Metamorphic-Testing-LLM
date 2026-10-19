package mtllm.config;

/**
 * Where the source inputs come from.
 *
 * <p>In simple terms: {@code LLM} = the model generates the inputs; {@code RANDOOP} = raw Randoop
 * (feedback-directed random generation, default value pool only); {@code NEW_HYBRID} = Randoop
 * examples ground the LLM's final input generation. This is orthogonal to who owns the metamorphic
 * relation ({@link MRProvider}) and to the JSON/test-suite output choices.</p>
 */
public enum InputGenerator {
    LLM,
    RANDOOP,
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
            case "NEW_HYBRID":
                return NEW_HYBRID;
            case "HYBRID":
                throw new IllegalArgumentException(
                        "InputGenerator HYBRID (LLM-seeded Randoop) has been removed. Use NEW_HYBRID.");
            default:
                throw new IllegalArgumentException(
                        "Invalid InputGenerator: " + raw + ". Use LLM, RANDOOP, or NEW_HYBRID.");
        }
    }

    /** True when Randoop itself produces the final inputs. */
    public boolean usesRandoop() {
        return this == RANDOOP;
    }

    /** True when Randoop examples should ground the LLM's final source-input generation. */
    public boolean randoopSeedsLlm() {
        return this == NEW_HYBRID;
    }
}
