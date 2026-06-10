package mtllm.config;

/**
 * Describes the internal generation strategy derived from prompt.txt fields.
 *
 * <p>In simple terms, this is no longer exposed directly to users. The loader
 * derives it from JsonRequired, TestSuiteRequired, and MRProvider.</p>
 */
public enum GenerationMode {
    DEVELOPER_MR_DATA,
    DEVELOPER_MR_JUNIT,
    INPUTS_AND_FOLLOWUP,
    FULL_JUNIT;

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
}
