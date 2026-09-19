import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CTX = new SpatialContext(false);

    private static void run(double x, double y, double radius,
                            double minX, double maxX,
                            double minY, double maxY) {
        CircleImpl sourceCircle =
                new CircleImpl(CTX.makePoint(x, y), radius, CTX);
        Rectangle sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, CTX);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);

        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void runEmpty(double minX, double maxX,
                                 double minY, double maxY) {
        Point emptyPoint = CTX.makePoint(Double.NaN, Double.NaN);
        CircleImpl sourceCircle = new CircleImpl(emptyPoint, 1.0, CTX);
        Rectangle sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, CTX);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);

        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "The spatial relation changed after the follow-up transformation");
        }
    }

}
