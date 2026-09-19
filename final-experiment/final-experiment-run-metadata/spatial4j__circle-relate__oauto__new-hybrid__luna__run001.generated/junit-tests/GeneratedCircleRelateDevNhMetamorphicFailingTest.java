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

    private static void check(
            double centerX,
            double centerY,
            double radius,
            double minX,
            double maxX,
            double minY,
            double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                context.makePoint(centerX, centerY), radius, context);
        Rectangle rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);
        checkRelation(circle, rectangle);
    }

    private static void checkEmpty(
            double minX,
            double maxX,
            double minY,
            double maxY) {
        SpatialContext context = new SpatialContext(false);
        Point empty = new PointImpl(Double.NaN, Double.NaN, context);
        CircleImpl circle = new CircleImpl(empty, 0.0, context);
        Rectangle rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);
        checkRelation(circle, rectangle);
    }

    private static void checkRelation(
            CircleImpl sourceCircle,
            Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        SpatialContext context = sourceCircle.getContext();
        Point sourceCenter = sourceCircle.getCenter();
        Point shiftedCenter = context.makePoint(
                sourceCenter.getX() + SHIFT_X,
                sourceCenter.getY() + SHIFT_Y);
        CircleImpl shiftedCircle = new CircleImpl(
                shiftedCenter, sourceCircle.getRadius(), context);

        Rectangle shiftedRectangle = new RectangleImpl(
                sourceRectangle.getMinX() + SHIFT_X,
                sourceRectangle.getMaxX() + SHIFT_X,
                sourceRectangle.getMinY() + SHIFT_Y,
                sourceRectangle.getMaxY() + SHIFT_Y,
                context);

        SpatialRelation followUpOutput =
                shiftedCircle.relate(shiftedRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
