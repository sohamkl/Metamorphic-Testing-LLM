import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);

    private static CircleImpl circle(double cx, double cy, double radius) {
        return new CircleImpl(CONTEXT.makePoint(cx, cy), radius, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        return new CircleImpl(
                CONTEXT.makePoint(Double.NaN, Double.NaN),
                5.0,
                CONTEXT);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY) {
        return CONTEXT.makeRectangle(minX, maxX, minY, maxY);
    }

    private static Rectangle emptyRectangle() {
        return CONTEXT.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

}
