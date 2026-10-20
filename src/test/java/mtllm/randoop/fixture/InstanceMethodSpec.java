package mtllm.randoop.fixture;

public final class InstanceMethodSpec {
    private InstanceMethodSpec() {
    }

    public static Object[] increaseBoth(InstanceMethodSut receiver, int value) {
        return new Object[]{new InstanceMethodSut(4), value + 1};
    }

    public static void assertRelation(int sourceOutput, int followUpOutput) {
        if (followUpOutput <= sourceOutput) {
            throw new AssertionError("Expected the transformed invocation to increase the output");
        }
    }
}
