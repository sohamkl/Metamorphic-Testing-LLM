package mtllm.config;

/**
 * Describes how much code the LLM should generate.
 *
 * <p>Modes are ordered by increasing LLM responsibility. Mode 1 and Mode 3
 * generate data-producing Java classes. Mode 2 and Mode 4 generate JUnit tests.</p>
 */
public enum GenerationMode {
    DEVELOPER_MR_DATA(1),
    DEVELOPER_MR_JUNIT(2),
    INPUTS_AND_FOLLOWUP(3),
    FULL_JUNIT(4);

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
        return this == DEVELOPER_MR_JUNIT || this == DEVELOPER_MR_DATA;
    }

    public boolean generatesFollowUpData() {
        return this == INPUTS_AND_FOLLOWUP;
    }

    public boolean generatesExecutedMtData() {
        return this == INPUTS_AND_FOLLOWUP || this == DEVELOPER_MR_DATA;
    }

    public boolean usesDeveloperMrDataHelpers() {
        return this == DEVELOPER_MR_DATA;
    }

    public static GenerationMode fromConfig(String modeValue, String levelValue) {
        String value = firstNonBlank(modeValue, levelValue, "3").trim().toLowerCase();
        switch (value) {
            case "1":
            case "mode 1":
            case "developer-mr-data":
            case "hybrid-data":
            case "executed-mt-data":
                return DEVELOPER_MR_DATA;
            case "2":
            case "mode 2":
            case "developer-mr-junit":
            case "developer-mr":
            case "hybrid-junit":
                return DEVELOPER_MR_JUNIT;
            case "3":
            case "mode 3":
            case "inputs-and-followup":
            case "source-and-followup":
                return INPUTS_AND_FOLLOWUP;
            case "4":
            case "mode 4":
            case "full-junit":
            case "junit":
                return FULL_JUNIT;
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
