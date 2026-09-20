import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static void verify(String scenario,
                               double minX, double maxX, double minY, double maxY,
                               double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl sourceRectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        SpatialContext context = rectangle.getContext();
        double rotation = 150.0;

        RectangleImpl rotatedRectangle = new RectangleImpl(
                DistanceUtils.normLonDEG(rectangle.getMinX() + rotation),
                DistanceUtils.normLonDEG(rectangle.getMaxX() + rotation),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);

        Point rotatedPoint = context.makePoint(
                DistanceUtils.normLonDEG(point.getX() + rotation),
                point.getY());

        return new Object[] {rotatedRectangle, rotatedPoint};
    }

}
