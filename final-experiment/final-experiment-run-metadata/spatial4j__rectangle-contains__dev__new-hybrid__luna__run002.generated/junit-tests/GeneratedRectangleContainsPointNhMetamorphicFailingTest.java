import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static SpatialContext geo() {
        return new SpatialContext(true);
    }

    private static Point point(SpatialContext context, double x, double y) {
        return context.makePoint(x, y);
    }

}
