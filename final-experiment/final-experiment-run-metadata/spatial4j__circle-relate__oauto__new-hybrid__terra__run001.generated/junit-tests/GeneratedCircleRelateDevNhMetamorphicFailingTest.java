import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private static CircleImpl circle(double x, double y, double radius) {
        SpatialContext context = new SpatialContext(false);
        Point point = new PointImpl(x, y, context);
        return new CircleImpl(point, radius, context);
    }

    private static CircleImpl emptyCircle() {
        SpatialContext context = new SpatialContext(false);
        Point point = new PointImpl(Double.NaN, Double.NaN, context);
        return new CircleImpl(point, 10.0, context);
    }

    private static Rectangle rectangle(CircleImpl circle, double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, circle.getContext());
    }

    private static Rectangle emptyRectangle(CircleImpl circle) {
        return new RectangleImpl(Double.NaN, Double.NaN, Double.NaN, Double.NaN, circle.getContext());
    }

    private static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();
        Point shiftedCenter = context.makePoint(center.getX() + SHIFT_X, center.getY() + SHIFT_Y);
        CircleImpl shiftedCircle = new CircleImpl(shiftedCenter, circle.getRadius(), context);
        Rectangle shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + SHIFT_X,
                rectangle.getMaxX() + SHIFT_X,
                rectangle.getMinY() + SHIFT_Y,
                rectangle.getMaxY() + SHIFT_Y,
                context);
        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    @Test
    public void farthest_corner_tangent_contains_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        double halfDiagonal = 10.0 / Math.sqrt(2.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -halfDiagonal, halfDiagonal, -halfDiagonal, halfDiagonal);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
