import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static SpatialContext cartesianContext() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(SpatialContext context, double radius) {
        return new CircleImpl(context.makePoint(0.0, 0.0), radius, context);
    }

    private static Rectangle rectangle(
            SpatialContext context, double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

}
