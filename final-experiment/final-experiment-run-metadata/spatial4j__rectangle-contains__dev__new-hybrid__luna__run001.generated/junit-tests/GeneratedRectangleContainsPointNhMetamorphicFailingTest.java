import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final SpatialContext GEO = SpatialContext.GEO;

    private static RectangleImpl rectangle(double minX, double maxX,
                                           double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, GEO);
    }

    private static Point point(double x, double y) {
        return GEO.makePoint(x, y);
    }

    private static void check(RectangleImpl source, Point probe) {
        SpatialRelation sourceOutput = source.relate(probe);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(source, probe);

        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void FULL_WORLD_WIDTH_CONTAINS_1() {
        Point lowerLeft = point(-180.0, -30.0);
        Point upperRight = point(180.0, 30.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(100.0, 0.0));
    }

    @Test
    public void FULL_WORLD_WIDTH_CONTAINS_2() {
        RectangleImpl source = rectangle(-180.0, 180.0, -30.0, 30.0);
        check(new RectangleImpl(source, GEO), point(-100.0, 0.0));
    }
}
