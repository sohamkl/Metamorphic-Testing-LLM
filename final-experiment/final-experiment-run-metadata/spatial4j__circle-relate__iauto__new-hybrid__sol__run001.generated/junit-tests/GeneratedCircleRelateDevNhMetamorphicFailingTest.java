import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private void exercise(
            double cx, double cy, double radius,
            double minX, double maxX, double minY, double maxY) {

        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle =
                new CircleImpl(context.makePoint(cx, cy), radius, context);
        Rectangle sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);

        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "A common translation changed the spatial relation from "
                            + sourceOutput + " to " + followUpOutput);
        }
    }

}
