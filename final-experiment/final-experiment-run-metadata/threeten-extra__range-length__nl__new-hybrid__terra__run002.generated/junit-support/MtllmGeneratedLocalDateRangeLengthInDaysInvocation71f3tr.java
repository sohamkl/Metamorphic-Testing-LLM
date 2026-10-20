/** Framework-generated typed invocation boundary for Randoop. */
public final class MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr {
    private MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr() {}

    public static final class Input {
        private final org.threeten.extra.LocalDateRange receiver;

        public Input(org.threeten.extra.LocalDateRange receiver) {
            this.receiver = receiver;
        }

        public org.threeten.extra.LocalDateRange receiver() { return receiver; }
    }

    public static int invoke(Input source) {
        try {
            return source.receiver().lengthInDays();
        } catch (RuntimeException | Error failure) {
            throw failure;
        } catch (Throwable failure) {
            throw new IllegalStateException("SUT invocation failed", failure);
        }
    }
}
