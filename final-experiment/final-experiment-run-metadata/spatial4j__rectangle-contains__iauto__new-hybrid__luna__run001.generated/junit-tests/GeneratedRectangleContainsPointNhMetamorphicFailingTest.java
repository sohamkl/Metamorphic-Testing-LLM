import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static SpatialContext geo() {
        return new SpatialContext(true);
    }

    private static RectangleImpl rectangle(SpatialContext context,
                                           double minX, double maxX,
                                           double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static Point point(SpatialContext context, double x, double y) {
        return new PointImpl(x, y, context);
    }

    private static void verify(RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialRelation sourceOutput =
                sourceRectangle.relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);

        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput =
                followUpRectangle.relate(followUpPoint);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                                   SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "The point/box relation changed from " + sourceOutput
                            + " to " + followUpOutput + ".");
        }
    }

}
