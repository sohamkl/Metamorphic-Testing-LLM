package mtllm.config;

/**
 * Describes how much code the LLM should generate.
 *
 * <p>Mode 1 and Mode 2 generate data-producing Java classes. Mode 3 generates a
 * complete JUnit 5 metamorphic test class. Mode 4 generates JUnit tests that call
 * developer-defined MR helper methods.</p>
 */
public enum GenerationMode {
    INPUTS_ONLY(1),
    INPUTS_AND_FOLLOWUP(2),
    FULL_JUNIT(3),
    DEVELOPER_MR_JUNIT(4);

    private final int number;

    GenerationMode(int number) {
        this.number = number;
    }

    public int number() {
        return number;
    }

    public boolean generatesJUnit() {
        return this == FULL_JUNIT || this == DEVELOPER_MR_JUNIT;
    }

    public boolean usesDeveloperMrHelpers() {
        return this == DEVELOPER_MR_JUNIT;
    }

    public boolean generatesFollowUpData() {
        return this == INPUTS_AND_FOLLOWUP;
    }

    public static GenerationMode fromConfig(String modeValue, String levelValue) {
        String value = firstNonBlank(modeValue, levelValue, "3").trim().toLowerCase();
        switch (value) {
            case "1":
            case "mode 1":
            case "inputs-only":
            case "source-inputs-only":
                return INPUTS_ONLY;
            case "2":
            case "mode 2":
            case "inputs-and-followup":
            case "source-and-followup":
                return INPUTS_AND_FOLLOWUP;
            case "3":
            case "mode 3":
            case "full-junit":
            case "junit":
                return FULL_JUNIT;
            case "4":
            case "mode 4":
            case "developer-mr-junit":
            case "developer-mr":
            case "hybrid-junit":
                return DEVELOPER_MR_JUNIT;
            default:
                throw new IllegalArgumentException(
                        "Invalid Mode/Level: " + value
                                + ". Use Mode: 1, Mode: 2, Mode: 3, or Mode: 4.");
        }
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value;
            }
        }
        return "";
    }
}
