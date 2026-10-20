/** Framework-generated typed invocation boundary for Randoop. */
public final class MtllmGeneratedDaysBetweenInvocation1uknttg {
    private MtllmGeneratedDaysBetweenInvocation1uknttg() {}

    public static final class Input {
        private final java.time.temporal.Temporal arg0;
        private final java.time.temporal.Temporal arg1;

        public Input(java.time.temporal.Temporal arg0, java.time.temporal.Temporal arg1) {
            this.arg0 = arg0;
            this.arg1 = arg1;
        }

        public java.time.temporal.Temporal arg0() { return arg0; }

        public java.time.temporal.Temporal arg1() { return arg1; }
    }

    public static org.threeten.extra.Days invoke(Input source) {
        try {
            return org.threeten.extra.Days.between(source.arg0(), source.arg1());
        } catch (RuntimeException | Error failure) {
            throw failure;
        } catch (Throwable failure) {
            throw new IllegalStateException("SUT invocation failed", failure);
        }
    }
}
