/** Framework-generated typed invocation boundary for Randoop. */
public final class MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f {
    private MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f() {}

    public static final class Input {
        private final org.ta4j.core.aggregator.RenkoBarAggregator receiver;
        private final java.util.List<org.ta4j.core.Bar> arg0;

        private Input(org.ta4j.core.aggregator.RenkoBarAggregator receiver, java.util.List<org.ta4j.core.Bar> arg0) {
            this.receiver = receiver;
            this.arg0 = arg0;
        }

        public Input(org.ta4j.core.aggregator.RenkoBarAggregator receiver, org.ta4j.core.Bar arg0Element0) {
            this(receiver, java.util.List.of(arg0Element0));
        }

        public Input(org.ta4j.core.aggregator.RenkoBarAggregator receiver, org.ta4j.core.Bar arg0Element0, org.ta4j.core.Bar arg0Element1) {
            this(receiver, java.util.List.of(arg0Element0, arg0Element1));
        }

        public Input(org.ta4j.core.aggregator.RenkoBarAggregator receiver, org.ta4j.core.Bar arg0Element0, org.ta4j.core.Bar arg0Element1, org.ta4j.core.Bar arg0Element2) {
            this(receiver, java.util.List.of(arg0Element0, arg0Element1, arg0Element2));
        }

        public Input(org.ta4j.core.aggregator.RenkoBarAggregator receiver, org.ta4j.core.Bar arg0Element0, org.ta4j.core.Bar arg0Element1, org.ta4j.core.Bar arg0Element2, org.ta4j.core.Bar arg0Element3) {
            this(receiver, java.util.List.of(arg0Element0, arg0Element1, arg0Element2, arg0Element3));
        }

        public Input(org.ta4j.core.aggregator.RenkoBarAggregator receiver, org.ta4j.core.Bar arg0Element0, org.ta4j.core.Bar arg0Element1, org.ta4j.core.Bar arg0Element2, org.ta4j.core.Bar arg0Element3, org.ta4j.core.Bar arg0Element4) {
            this(receiver, java.util.List.of(arg0Element0, arg0Element1, arg0Element2, arg0Element3, arg0Element4));
        }

        public Input(org.ta4j.core.aggregator.RenkoBarAggregator receiver, org.ta4j.core.Bar arg0Element0, org.ta4j.core.Bar arg0Element1, org.ta4j.core.Bar arg0Element2, org.ta4j.core.Bar arg0Element3, org.ta4j.core.Bar arg0Element4, org.ta4j.core.Bar arg0Element5) {
            this(receiver, java.util.List.of(arg0Element0, arg0Element1, arg0Element2, arg0Element3, arg0Element4, arg0Element5));
        }

        public org.ta4j.core.aggregator.RenkoBarAggregator receiver() { return receiver; }

        public java.util.List<org.ta4j.core.Bar> arg0() { return arg0; }
    }

    public static java.util.List<org.ta4j.core.Bar> invoke(Input source) {
        try {
            return source.receiver().aggregate(source.arg0());
        } catch (RuntimeException | Error failure) {
            throw failure;
        } catch (Throwable failure) {
            throw new IllegalStateException("SUT invocation failed", failure);
        }
    }

    public static boolean isUsable(Input source) {
        return source.receiver() != null && source.arg0() != null && !source.arg0().isEmpty() && source.arg0().stream().allMatch(value -> value instanceof org.ta4j.core.Bar);
    }

    public static Input generateFollowUp(Input source) {
        Object[] values = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source.receiver(), source.arg0());
        if (values == null || values.length != 2) {
            throw new IllegalArgumentException("Developer follow-up must return exactly 2 values");
        }
        return new Input((org.ta4j.core.aggregator.RenkoBarAggregator) values[0], (java.util.List) values[1]);
    }
}
