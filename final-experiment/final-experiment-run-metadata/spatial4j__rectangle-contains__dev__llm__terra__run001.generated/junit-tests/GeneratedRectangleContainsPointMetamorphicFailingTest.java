import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicFailingTest {

    private static final SpatialContext GEO = SpatialContext.GEO;

    private void check(RectangleImpl rectangle, Point point) {
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void fullWorldLongitudeInterior_variation1() {
        check(new RectangleImpl(-180.0, 180.0, -10.0, 10.0, GEO), GEO.makePoint(0.0, 0.0));
    }
}
