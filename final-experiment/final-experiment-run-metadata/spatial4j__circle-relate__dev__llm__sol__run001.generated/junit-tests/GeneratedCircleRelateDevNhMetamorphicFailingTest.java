import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CONTEXT = createContext();

    private static SpatialContext createContext() {
        SpatialContextFactory factory = new SpatialContextFactory();
        factory.geo = false;
        return factory.newSpatialContext();
    }

    private static CircleImpl circle(double x, double y, double radius) {
        Point center = CONTEXT.makePoint(x, y);
        return new CircleImpl(center, radius, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        return new CircleImpl(CONTEXT.makePoint(Double.NaN, Double.NaN), 0.0, CONTEXT);
    }

    private static Rectangle rectangle(double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static Rectangle emptyRectangle() {
        return new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, CONTEXT);
    }

    private static void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TINY_SCALE_RELATIVE_TO_FIXED_SHIFT_variation1() {
        double radius = Math.scalb(1.0, -50);
        exercise(circle(0.0, 0.0, radius), rectangle(radius, 2.0 * radius, 0.0, 0.0));
    }
}
