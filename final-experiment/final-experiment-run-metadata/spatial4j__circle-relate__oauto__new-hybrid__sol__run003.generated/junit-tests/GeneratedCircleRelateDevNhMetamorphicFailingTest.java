import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);
    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private static CircleImpl circle(double x, double y, double radius) {
        return new CircleImpl(CONTEXT.makePoint(x, y), radius, CONTEXT);
    }

    private static RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        Point emptyPoint = CONTEXT.makePoint(Double.NaN, Double.NaN);
        return new CircleImpl(emptyPoint, 1.0, CONTEXT);
    }

    private static RectangleImpl emptyRectangle() {
        return rectangle(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    private static Object[] generateFollowUp(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialContext context = sourceCircle.getContext();
        Point shiftedCenter = context.makePoint(
                sourceCircle.getCenter().getX() + SHIFT_X,
                sourceCircle.getCenter().getY() + SHIFT_Y);
        CircleImpl shiftedCircle =
                new CircleImpl(shiftedCenter, sourceCircle.getRadius(), context);
        RectangleImpl shiftedRectangle = new RectangleImpl(
                sourceRectangle.getMinX() + SHIFT_X,
                sourceRectangle.getMaxX() + SHIFT_X,
                sourceRectangle.getMinY() + SHIFT_Y,
                sourceRectangle.getMaxY() + SHIFT_Y,
                context);
        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    private static void verify(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);

        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
