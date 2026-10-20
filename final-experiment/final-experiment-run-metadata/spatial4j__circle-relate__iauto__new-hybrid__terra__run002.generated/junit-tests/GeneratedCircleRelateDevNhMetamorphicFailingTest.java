import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private void exercise(double radius, double minXOffset, double maxXOffset,
                          double minYOffset, double maxYOffset) {
        SpatialContext context = new SpatialContext(false);
        double cx = 100.0;
        double cy = 200.0;
        Point center = context.makePoint(cx, cy);
        CircleImpl circle = new CircleImpl(center, radius, context);
        Rectangle rectangle = new RectangleImpl(
                cx + minXOffset, cx + maxXOffset,
                cy + minYOffset, cy + maxYOffset,
                context);

        SpatialRelation sourceOutput = circle.relate(rectangle);

        Object[] followUpValues =
                CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];

        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                           SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Translating the circle and rectangle together must preserve their spatial relation: "
                            + sourceOutput + " versus " + followUpOutput);
        }
    }

}
