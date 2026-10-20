/** Framework-generated typed invocation boundary for Randoop. */
public final class MtllmGeneratedCircleImplRelateInvocation1qssgw4 {
    private MtllmGeneratedCircleImplRelateInvocation1qssgw4() {}

    public static final class Input {
        private final org.locationtech.spatial4j.shape.impl.CircleImpl receiver;
        private final org.locationtech.spatial4j.shape.Rectangle arg0;

        public Input(org.locationtech.spatial4j.shape.impl.CircleImpl receiver, org.locationtech.spatial4j.shape.Rectangle arg0) {
            this.receiver = receiver;
            this.arg0 = arg0;
        }

        public org.locationtech.spatial4j.shape.impl.CircleImpl receiver() { return receiver; }

        public org.locationtech.spatial4j.shape.Rectangle arg0() { return arg0; }
    }

    public static org.locationtech.spatial4j.shape.SpatialRelation invoke(Input source) {
        try {
            return source.receiver().relate(source.arg0());
        } catch (RuntimeException | Error failure) {
            throw failure;
        } catch (Throwable failure) {
            throw new IllegalStateException("SUT invocation failed", failure);
        }
    }

    public static Input generateFollowUp(Input source) {
        Object[] values = CircleRelateDevMetamorphicSpec.generateFollowUp(source.receiver(), source.arg0());
        if (values == null || values.length != 2) {
            throw new IllegalArgumentException("Developer follow-up must return exactly 2 values");
        }
        return new Input((org.locationtech.spatial4j.shape.impl.CircleImpl) values[0], (org.locationtech.spatial4j.shape.Rectangle) values[1]);
    }
}
