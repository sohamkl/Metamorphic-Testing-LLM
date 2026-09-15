import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static SpatialContext context() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(double x, double y, double radius) {
        SpatialContext context = context();
        return new CircleImpl(context.makePoint(x, y), radius, context);
    }

    private static CircleImpl emptyCircle() {
        SpatialContext context = context();
        return new CircleImpl(context.makePoint(Double.NaN, Double.NaN), 0.0, context);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context());
    }

    private static void check(CircleImpl source, Rectangle rectangle) {
        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                source.relate(rectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate(
                        (Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
