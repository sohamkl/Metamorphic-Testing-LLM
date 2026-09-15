import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointMetamorphicFailingTest {

    private void exercise(double minX, double maxX, double minY, double maxY,
                          double pointX, double pointY) {
        org.locationtech.spatial4j.context.SpatialContext context =
                org.locationtech.spatial4j.context.SpatialContext.GEO;

        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangle =
                new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                        minX, maxX, minY, maxY, context);

        org.locationtech.spatial4j.shape.Point point =
                context.makePoint(pointX, pointY);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.RectangleImpl) rectangle).relate(point);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);

        org.locationtech.spatial4j.shape.impl.RectangleImpl followUpRectangle =
                (org.locationtech.spatial4j.shape.impl.RectangleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Point followUpPoint =
                (org.locationtech.spatial4j.shape.Point) followUp[1];

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.RectangleImpl) followUpRectangle)
                        .relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
