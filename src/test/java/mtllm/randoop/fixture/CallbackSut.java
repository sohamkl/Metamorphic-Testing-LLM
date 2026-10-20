package mtllm.randoop.fixture;

public final class CallbackSut {
    private CallbackSut() {
    }

    public enum Decision {
        CONTINUE,
        STOP,
        REMOVE
    }

    public interface DecisionCallback {
        Decision head(String value, int depth);

        default Decision tail(String value, int depth) {
            return Decision.CONTINUE;
        }
    }

    public static Decision apply(DecisionCallback callback, String value) {
        return callback.head(value, value.length());
    }
}
