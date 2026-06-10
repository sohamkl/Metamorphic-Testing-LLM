package mtllm;

/**
 * Compatibility command-line entry point for starting the MT-LLM backend.
 *
 * <p>In simple terms, this class exists so users can run the tool through a small runner class
 * instead of calling {@link App} directly.</p>
 */
public final class OpenaiRunner {
    private OpenaiRunner() {
    }

    public static void main(String[] args) {
        App.main(args);
    }
}
