import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static CircleImpl circle(SpatialContext context, double x, double y, double radius) {
        return new CircleImpl(context.makePoint(x, y), radius, context);
    }

    private static Rectangle rectangle(SpatialContext context,
                                       double minX, double maxX,
                                       double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                                  SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Joint translation changed the spatial relation from "
                            + sourceOutput + " to " + followUpOutput);
        }
    }

}
