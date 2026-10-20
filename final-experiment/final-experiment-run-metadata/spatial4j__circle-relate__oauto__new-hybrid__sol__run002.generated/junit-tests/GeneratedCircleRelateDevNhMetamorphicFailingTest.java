import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);
    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private static CircleImpl circle(double x, double y, double radius) {
        return new CircleImpl(CONTEXT.makePoint(x, y), radius, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        Point emptyPoint = new PointImpl(Double.NaN, Double.NaN, CONTEXT);
        return new CircleImpl(emptyPoint, 0.0, CONTEXT);
    }

    private static Rectangle rectangle(double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static Rectangle emptyRectangle() {
        return new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, CONTEXT);
    }

    private static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();
        Point shiftedCenter = context.makePoint(
                center.getX() + SHIFT_X,
                center.getY() + SHIFT_Y);

        CircleImpl shiftedCircle =
                new CircleImpl(shiftedCenter, circle.getRadius(), context);
        Rectangle shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + SHIFT_X,
                rectangle.getMaxX() + SHIFT_X,
                rectangle.getMinY() + SHIFT_Y,
                rectangle.getMaxY() + SHIFT_Y,
                context);

        return new Object[]{shiftedCircle, shiftedRectangle};
    }

}
