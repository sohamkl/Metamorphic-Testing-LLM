import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final org.locationtech.spatial4j.context.SpatialContext GEO =
            new org.locationtech.spatial4j.context.SpatialContext(true);

    private static org.locationtech.spatial4j.shape.impl.RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                minX, maxX, minY, maxY, GEO);
    }

    private static org.locationtech.spatial4j.shape.Point point(double x, double y) {
        return GEO.makePoint(x, y);
    }

    private static void verify(
            org.locationtech.spatial4j.shape.impl.RectangleImpl sourceRectangle,
            org.locationtech.spatial4j.shape.Point sourcePoint) {

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.class
                        .cast(sourceRectangle)
                        .relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);

        org.locationtech.spatial4j.shape.impl.RectangleImpl followUpRectangle =
                (org.locationtech.spatial4j.shape.impl.RectangleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Point followUpPoint =
                (org.locationtech.spatial4j.shape.Point) followUp[1];

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.class
                        .cast(followUpRectangle)
                        .relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
