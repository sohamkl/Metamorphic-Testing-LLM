import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        SpatialContext context = rectangle.getContext();
        RectangleImpl rotatedRectangle = new RectangleImpl(
                DistanceUtils.normLonDEG(rectangle.getMinX() + 150.0),
                DistanceUtils.normLonDEG(rectangle.getMaxX() + 150.0),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);
        Point rotatedPoint = context.makePoint(
                DistanceUtils.normLonDEG(point.getX() + 150.0),
                point.getY());
        return new Object[]{rotatedRectangle, rotatedPoint};
    }

}
