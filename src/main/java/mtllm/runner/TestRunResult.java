package mtllm.runner;

/**
 * Represents the outcome of compiling/running a generated test.
 *
 * <p>In simple terms, this is a small result object that says whether the generated test passed,
 * failed, or was skipped, plus the command output to show or send back to the LLM.</p>
 */
public final class TestRunResult {
    public enum Status {
        PASSED,
        FAILED,
        SKIPPED
    }

    private final Status status;
    private final String output;

    private TestRunResult(Status status, String output) {
        this.status = status;
        this.output = output == null ? "" : output;
    }

    public static TestRunResult passed(String output) {
        return new TestRunResult(Status.PASSED, output);
    }

    public static TestRunResult failed(String output) {
        return new TestRunResult(Status.FAILED, output);
    }

    public static TestRunResult skipped(String output) {
        return new TestRunResult(Status.SKIPPED, output);
    }

    public Status status() {
        return status;
    }

    public String output() {
        return output;
    }

    public boolean passed() {
        return status == Status.PASSED;
    }

    public boolean failed() {
        return status == Status.FAILED;
    }
}
