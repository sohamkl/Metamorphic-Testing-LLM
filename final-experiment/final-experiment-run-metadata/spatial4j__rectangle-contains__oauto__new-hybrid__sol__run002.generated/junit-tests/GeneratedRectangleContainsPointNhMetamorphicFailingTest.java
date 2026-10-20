import org.junit.jupiter.api.Test;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        SpatialContext context = rectangle.getContext();
        double rotatedMinX = DistanceUtils.normLonDEG(rectangle.getMinX() + 150.0);
        double rotatedMaxX = DistanceUtils.normLonDEG(rectangle.getMaxX() + 150.0);
        double rotatedPointX = DistanceUtils.normLonDEG(point.getX() + 150.0);

        RectangleImpl followUpRectangle = new RectangleImpl(
                rotatedMinX,
                rotatedMaxX,
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);
        Point followUpPoint = context.makePoint(rotatedPointX, point.getY());

        return new Object[]{followUpRectangle, followUpPoint};
    }

}
