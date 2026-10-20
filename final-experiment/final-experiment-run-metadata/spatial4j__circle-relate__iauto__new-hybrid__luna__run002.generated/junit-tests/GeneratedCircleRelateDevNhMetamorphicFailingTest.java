import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static SpatialContext context() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(double x, double y, double radius, SpatialContext context) {
        Point point = new PointImpl(x, y, context);
        return new CircleImpl(point, radius, context);
    }

    private static CircleImpl emptyCircle(SpatialContext context) {
        Point point = new PointImpl(Double.NaN, Double.NaN, context);
        return new CircleImpl(point, 0.0, context);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY, SpatialContext context) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "The spatial relation changed after translating both shapes: "
                            + sourceOutput + " -> " + followUpOutput);
        }
    }

}
