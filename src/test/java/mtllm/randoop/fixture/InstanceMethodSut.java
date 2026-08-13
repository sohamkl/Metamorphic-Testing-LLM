package mtllm.randoop.fixture;

public final class InstanceMethodSut {
    private final int base;

    public InstanceMethodSut(int base) {
        this.base = base;
    }

    public int combine(int value) {
        return base + value;
    }
}
