/** Framework-generated typed invocation boundary for Randoop. */
public final class MtllmGeneratedSlopeChangeSwingDetectorDetectInvocation1gxlgzg {
    private MtllmGeneratedSlopeChangeSwingDetectorDetectInvocation1gxlgzg() {}

    public static final class Input {
        private final org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector receiver;
        private final org.ta4j.core.BarSeries arg0;
        private final int arg1;
        private final org.ta4j.core.indicators.elliott.ElliottDegree arg2;

        public Input(org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector receiver, org.ta4j.core.BarSeries arg0, int arg1, org.ta4j.core.indicators.elliott.ElliottDegree arg2) {
            this.receiver = receiver;
            this.arg0 = arg0;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        public org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector receiver() { return receiver; }

        public org.ta4j.core.BarSeries arg0() { return arg0; }

        public int arg1() { return arg1; }

        public org.ta4j.core.indicators.elliott.ElliottDegree arg2() { return arg2; }
    }

    public static org.ta4j.core.analysis.elliott.swing.SwingDetectorResult invoke(Input source) {
        try {
            return source.receiver().detect(source.arg0(), source.arg1(), source.arg2());
        } catch (RuntimeException | Error failure) {
            throw failure;
        } catch (Throwable failure) {
            throw new IllegalStateException("SUT invocation failed", failure);
        }
    }
}
