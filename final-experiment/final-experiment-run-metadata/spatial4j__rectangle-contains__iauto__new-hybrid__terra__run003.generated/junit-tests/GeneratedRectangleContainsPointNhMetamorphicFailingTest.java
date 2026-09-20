import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private void exercise(double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl sourceRectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = new PointImpl(pointX, pointY, context);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Rotating the geographic rectangle and point by the same longitude must preserve the relation: "
                            + sourceOutput + " versus " + followUpOutput);
        }
    }

}
