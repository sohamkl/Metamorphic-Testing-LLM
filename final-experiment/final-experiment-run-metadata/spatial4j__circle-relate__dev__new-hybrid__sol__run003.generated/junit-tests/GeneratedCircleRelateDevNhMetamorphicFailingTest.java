import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static SpatialContext flatContext() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(
            SpatialContext context, double centerX, double centerY, double radius) {
        return new CircleImpl(context.makePoint(centerX, centerY), radius, context);
    }

    private static CircleImpl emptyCircle(SpatialContext context) {
        return new CircleImpl(
                context.makePoint(Double.NaN, Double.NaN),
                0.0,
                context);
    }

    private static Rectangle rectangle(
            SpatialContext context,
            double minX,
            double maxX,
            double minY,
            double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static Rectangle emptyRectangle(SpatialContext context) {
        return context.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    private static void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        Object[] followUpValues =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
