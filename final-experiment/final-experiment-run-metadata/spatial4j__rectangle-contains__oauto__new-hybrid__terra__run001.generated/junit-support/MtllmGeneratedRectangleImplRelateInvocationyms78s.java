/** Framework-generated typed invocation boundary for Randoop. */
public final class MtllmGeneratedRectangleImplRelateInvocationyms78s {
    private MtllmGeneratedRectangleImplRelateInvocationyms78s() {}

    public static final class Input {
        private final org.locationtech.spatial4j.shape.impl.RectangleImpl receiver;
        private final org.locationtech.spatial4j.shape.Point arg0;

        public Input(org.locationtech.spatial4j.shape.impl.RectangleImpl receiver, org.locationtech.spatial4j.shape.Point arg0) {
            this.receiver = receiver;
            this.arg0 = arg0;
        }

        public org.locationtech.spatial4j.shape.impl.RectangleImpl receiver() { return receiver; }

        public org.locationtech.spatial4j.shape.Point arg0() { return arg0; }
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
}
