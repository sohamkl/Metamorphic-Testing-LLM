import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static void check(double x, double y, double radius,
                              double minX, double maxX,
                              double minY, double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(x, y, context), radius, context);
        RectangleImpl rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);

        Object[] followUpValues =
                CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        org.locationtech.spatial4j.shape.Rectangle followUpRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUpValues[1];

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
