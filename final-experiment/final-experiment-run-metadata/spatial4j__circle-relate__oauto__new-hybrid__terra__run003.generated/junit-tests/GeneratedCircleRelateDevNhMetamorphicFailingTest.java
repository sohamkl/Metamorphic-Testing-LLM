import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();

        CircleImpl shiftedCircle = new CircleImpl(
                context.makePoint(center.getX() + 17.5, center.getY() - 9.25),
                circle.getRadius(),
                context);

        Rectangle shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + 17.5,
                rectangle.getMaxX() + 17.5,
                rectangle.getMinY() - 9.25,
                rectangle.getMaxY() - 9.25,
                context);

        return new Object[] { shiftedCircle, shiftedRectangle };
    }

}
