package mtllm.randoop.fixture;

public final class MultiArgumentSpec {
    private MultiArgumentSpec() {
    }

    public static Object[] increaseBoth(int left, int right) {
        return new Object[]{left + 1, right + 1};
    }

    public static void assertRelation(int sourceOutput, int followUpOutput) {
        if (followUpOutput != sourceOutput + 2) {
            throw new AssertionError("Expected both arguments to increase by one");
        }
    }
}
