import org.junit.jupiter.api.Test;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final org.locationtech.spatial4j.context.SpatialContext CONTEXT = createContext();

    private static org.locationtech.spatial4j.context.SpatialContext createContext() {
        org.locationtech.spatial4j.context.SpatialContextFactory factory =
                new org.locationtech.spatial4j.context.SpatialContextFactory();
        factory.geo = false;
        return new org.locationtech.spatial4j.context.SpatialContext(factory);
    }

    private static org.locationtech.spatial4j.shape.impl.CircleImpl circle(
            double x, double y, double radius) {
        return new org.locationtech.spatial4j.shape.impl.CircleImpl(
                CONTEXT.makePoint(x, y), radius, CONTEXT);
    }

    private static org.locationtech.spatial4j.shape.impl.CircleImpl emptyCircle() {
        return new org.locationtech.spatial4j.shape.impl.CircleImpl(
                CONTEXT.makePoint(Double.NaN, Double.NaN), Double.NaN, CONTEXT);
    }

    private static org.locationtech.spatial4j.shape.impl.RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                minX, maxX, minY, maxY, CONTEXT);
    }

    private static org.locationtech.spatial4j.shape.impl.RectangleImpl emptyRectangle() {
        return rectangle(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    private static void exercise(
            org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle,
            org.locationtech.spatial4j.shape.Rectangle sourceRectangle) {
        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followUpCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followUpRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
