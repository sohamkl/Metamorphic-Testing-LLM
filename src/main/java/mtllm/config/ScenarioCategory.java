package mtllm.config;

/** Broad purpose of a required source-input scenario. */
public enum ScenarioCategory {
    NORMAL,
    BOUNDARY,
    EDGE,
    INVALID;

    static ScenarioCategory fromConfig(String raw, String fieldPath) {
        String value = raw == null ? "" : raw.trim();
        if (value.isEmpty()) {
            return NORMAL;
        }
        try {
            return valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    fieldPath + " must be NORMAL, BOUNDARY, EDGE, or INVALID: " + raw);
        }
    }
}
