import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final double ROTATION_DEGREES = 150.0;

    private static RectangleImpl rectangle(double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, new SpatialContext(true));
    }

    private static Point point(RectangleImpl rectangle, double x, double y) {
        return rectangle.getContext().makePoint(x, y);
    }

    private static double rotateLongitude(double longitude) {
        return DistanceUtils.normLonDEG(longitude + ROTATION_DEGREES);
    }

    private static Object[] generateFollowUp(RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialContext context = sourceRectangle.getContext();
        RectangleImpl followUpRectangle = new RectangleImpl(
                rotateLongitude(sourceRectangle.getMinX()),
                rotateLongitude(sourceRectangle.getMaxX()),
                sourceRectangle.getMinY(),
                sourceRectangle.getMaxY(),
                context);
        Point followUpPoint = context.makePoint(
                rotateLongitude(sourcePoint.getX()),
                sourcePoint.getY());
        return new Object[]{followUpRectangle, followUpPoint};
    }

    private static void verify(RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
