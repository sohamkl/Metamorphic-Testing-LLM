import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicFailingTest {

    private static final SpatialContext GEO = SpatialContext.GEO;

    private void runCase(RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(
                sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
