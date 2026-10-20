import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final org.locationtech.spatial4j.context.SpatialContext GEO =
            new org.locationtech.spatial4j.context.SpatialContext(true);

    private static void verifyRotationInvariant(
            double minX,
            double maxX,
            double minY,
            double maxY,
            double pointX,
            double pointY) {

        org.locationtech.spatial4j.shape.impl.RectangleImpl sourceRectangle =
                new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                        minX, maxX, minY, maxY, GEO);
        org.locationtech.spatial4j.shape.Point sourcePoint =
                GEO.makePoint(pointX, pointY);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceRectangle.relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);
        org.locationtech.spatial4j.shape.impl.RectangleImpl followUpRectangle =
                (org.locationtech.spatial4j.shape.impl.RectangleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Point followUpPoint =
                (org.locationtech.spatial4j.shape.Point) followUp[1];

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followUpRectangle.relate(followUpPoint);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            org.locationtech.spatial4j.shape.SpatialRelation sourceOutput,
            org.locationtech.spatial4j.shape.SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Joint longitude rotation changed the point-in-rectangle relation: "
                            + sourceOutput + " -> " + followUpOutput);
        }
    }

}
