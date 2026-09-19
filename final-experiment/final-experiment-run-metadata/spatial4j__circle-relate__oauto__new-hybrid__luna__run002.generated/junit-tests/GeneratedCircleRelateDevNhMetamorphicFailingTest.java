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

    private static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();

        Point shiftedCenter = context.makePoint(
                center.getX() + SHIFT_X,
                center.getY() + SHIFT_Y);

        CircleImpl shiftedCircle = new CircleImpl(
                shiftedCenter,
                circle.getRadius(),
                context);

        RectangleImpl shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + SHIFT_X,
                rectangle.getMaxX() + SHIFT_X,
                rectangle.getMinY() + SHIFT_Y,
                rectangle.getMaxY() + SHIFT_Y,
                context);

        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    private static void verifyTranslated(
            CircleImpl source,
            Rectangle rectangle) {
        SpatialRelation sourceOutput = source.relate(rectangle);

        Object[] followUp = generateFollowUp(source, rectangle);
        CircleImpl shiftedCircle = (CircleImpl) followUp[0];
        Rectangle shiftedRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                shiftedCircle.relate(shiftedRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput,
                followUpOutput);
    }

    private static CircleImpl circle(
            SpatialContext context,
            double x,
            double y,
            double radius) {
        return new CircleImpl(context.makePoint(x, y), radius, context);
    }

    private static Rectangle rectangle(
            SpatialContext context,
            double minX,
            double maxX,
            double minY,
            double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

}
