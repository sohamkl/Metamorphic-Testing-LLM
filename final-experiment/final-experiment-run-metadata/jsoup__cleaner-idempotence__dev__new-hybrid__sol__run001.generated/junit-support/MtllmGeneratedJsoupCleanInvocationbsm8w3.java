/** Framework-generated typed invocation boundary for Randoop. */
public final class MtllmGeneratedJsoupCleanInvocationbsm8w3 {
    private MtllmGeneratedJsoupCleanInvocationbsm8w3() {}

    public static final class Input {
        private final java.lang.String arg0;
        private final java.lang.String arg1;
        private final org.jsoup.safety.Safelist arg2;

        public Input(java.lang.String arg0, java.lang.String arg1, org.jsoup.safety.Safelist arg2) {
            this.arg0 = arg0;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        public java.lang.String arg0() { return arg0; }

        public java.lang.String arg1() { return arg1; }

        public org.jsoup.safety.Safelist arg2() { return arg2; }
    }

    public static java.lang.String invoke(Input source) {
        try {
            return org.jsoup.Jsoup.clean(source.arg0(), source.arg1(), source.arg2());
        } catch (RuntimeException | Error failure) {
            throw failure;
        } catch (Throwable failure) {
            throw new IllegalStateException("SUT invocation failed", failure);
        }
    }

    public static Input generateFollowUp(Input source) {
        Object[] values = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(source.arg0(), source.arg1(), source.arg2());
        if (values == null || values.length != 3) {
            throw new IllegalArgumentException("Developer follow-up must return exactly 3 values");
        }
        return new Input((java.lang.String) values[0], (java.lang.String) values[1], (org.jsoup.safety.Safelist) values[2]);
    }
}
