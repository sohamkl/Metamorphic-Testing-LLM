import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);

    private static CircleImpl circle(double radius) {
        return new CircleImpl(CONTEXT.makePoint(0.0, 0.0), radius, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        return new CircleImpl(
                CONTEXT.makePoint(Double.NaN, Double.NaN),
                10.0,
                CONTEXT);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static Rectangle emptyRectangle() {
        return rectangle(
                Double.NaN, Double.NaN,
                Double.NaN, Double.NaN);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Translating the circle and rectangle together changed their spatial relation: "
                            + sourceOutput + " -> " + followUpOutput);
        }
    }

}
