import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private void runCase(
            double minX,
            double maxX,
            double minY,
            double maxY,
            double pointX,
            double pointY) {

        org.locationtech.spatial4j.context.SpatialContext context =
                new org.locationtech.spatial4j.context.SpatialContext(true);

        org.locationtech.spatial4j.shape.impl.RectangleImpl sourceRectangle =
                new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                        minX, maxX, minY, maxY, context);

        org.locationtech.spatial4j.shape.Point sourcePoint =
                context.makePoint(pointX, pointY);

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

    private void assertMetamorphicRelation(
            org.locationtech.spatial4j.shape.SpatialRelation sourceOutput,
            org.locationtech.spatial4j.shape.SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Simultaneously rotating the rectangle and point changed their spatial relation");
        }
    }

}
