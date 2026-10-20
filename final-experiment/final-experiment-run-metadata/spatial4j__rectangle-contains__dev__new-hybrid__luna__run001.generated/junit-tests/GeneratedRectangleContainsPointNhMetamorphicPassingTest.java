import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

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
    public void LATITUDE_BELOW_REJECTS_BEFORE_LONGITUDE_1() {
        check(rectangle(-40.0, 40.0, 10.0, 30.0), point(0.0, 0.0));
    }

    @Test
    public void LATITUDE_BELOW_REJECTS_BEFORE_LONGITUDE_2() {
        Point lowerLeft = point(170.0, 10.0);
        Point upperRight = point(-170.0, 30.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(175.0, 0.0));
    }

    @Test
    public void LATITUDE_ABOVE_REJECTS_BEFORE_LONGITUDE_1() {
        RectangleImpl source = rectangle(5.0, 5.0, -30.0, 10.0);
        check(new RectangleImpl(source, GEO), point(5.0, 20.0));
    }

    @Test
    public void LATITUDE_ABOVE_REJECTS_BEFORE_LONGITUDE_2() {
        check(rectangle(-180.0, 180.0, -30.0, 10.0), point(0.0, 20.0));
    }

    @Test
    public void ORDINARY_INTERIOR_DIRECT_CONTAINS_1() {
        Point lowerLeft = point(-80.0, -30.0);
        Point upperRight = point(80.0, 30.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(20.0, 10.0));
    }

    @Test
    public void ORDINARY_INTERIOR_DIRECT_CONTAINS_2() {
        check(rectangle(150.0, -150.0, -20.0, 20.0), point(175.0, 0.0));
    }

    @Test
    public void ORDINARY_INTERIOR_DIRECT_CONTAINS_3() {
        check(rectangle(25.0, 25.0, -10.0, 10.0), point(25.0, 0.0));
    }

    @Test
    public void ORDINARY_MINX_BOUNDARY_CONTAINS_1() {
        check(rectangle(-120.0, 120.0, -20.0, 20.0), point(-120.0, 0.0));
    }

    @Test
    public void ORDINARY_MINX_BOUNDARY_CONTAINS_2() {
        RectangleImpl source = rectangle(-80.0, 80.0, -20.0, 20.0);
        check(new RectangleImpl(source, GEO), point(-80.0, 20.0));
    }

    @Test
    public void ORDINARY_MAXX_BOUNDARY_CONTAINS_1() {
        check(rectangle(150.0, -150.0, -20.0, 20.0), point(-150.0, 0.0));
    }

    @Test
    public void ORDINARY_MAXX_BOUNDARY_CONTAINS_2() {
        Point lowerLeft = point(30.0, -20.0);
        Point upperRight = point(30.0, 20.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(30.0, -10.0));
    }

    @Test
    public void ORDINARY_LONGITUDE_DISJOINT_LEFT_1() {
        RectangleImpl source = rectangle(-180.0, 180.0, -20.0, 20.0);
        check(new RectangleImpl(source, GEO), point(-180.0, 0.0));
    }

    @Test
    public void ORDINARY_LONGITUDE_DISJOINT_LEFT_2() {
        check(rectangle(-100.0, -40.0, -20.0, 20.0), point(-120.0, 0.0));
    }

    @Test
    public void ORDINARY_LONGITUDE_DISJOINT_RIGHT_1() {
        Point lowerLeft = point(160.0, -20.0);
        Point upperRight = point(-160.0, 20.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(0.0, 0.0));
    }

    @Test
    public void ORDINARY_LONGITUDE_DISJOINT_RIGHT_2() {
        RectangleImpl source = rectangle(40.0, 40.0, -20.0, 20.0);
        check(new RectangleImpl(source, GEO), point(60.0, 0.0));
    }

    @Test
    public void DATELINE_INTERIOR_DIRECT_CONTAINS_1() {
        check(rectangle(170.0, -170.0, -20.0, 20.0), point(175.0, 0.0));
    }

    @Test
    public void DATELINE_INTERIOR_DIRECT_CONTAINS_2() {
        Point lowerLeft = point(160.0, -20.0);
        Point upperRight = point(-160.0, 20.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(170.0, 10.0));
    }

    @Test
    public void DATELINE_SHIFTED_WEST_CONTAINS_1() {
        RectangleImpl source = rectangle(170.0, -170.0, -20.0, 20.0);
        check(new RectangleImpl(source, GEO), point(-175.0, 0.0));
    }

    @Test
    public void DATELINE_SHIFTED_WEST_CONTAINS_2() {
        check(rectangle(180.0, -180.0, -20.0, 20.0), point(-180.0, 10.0));
    }

    @Test
    public void DATELINE_SHIFTED_EAST_DISJOINT_1() {
        Point lowerLeft = point(150.0, -20.0);
        Point upperRight = point(-150.0, 20.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(0.0, 0.0));
    }

    @Test
    public void DATELINE_SHIFTED_EAST_DISJOINT_2() {
        check(rectangle(100.0, 120.0, -20.0, 20.0), point(130.0, -10.0));
    }

    @Test
    public void DATELINE_ENDPOINT_CONTAINS_1() {
        check(rectangle(180.0, -180.0, -20.0, 20.0), point(180.0, 0.0));
    }

    @Test
    public void DATELINE_ENDPOINT_CONTAINS_2() {
        check(rectangle(179.0, -179.0, -20.0, 20.0), point(-179.0, 5.0));
    }

    @Test
    public void LATITUDE_MIN_BOUNDARY_CONTAINS_1() {
        check(rectangle(-80.0, 80.0, -20.0, 20.0), point(0.0, -20.0));
    }

    @Test
    public void LATITUDE_MAX_BOUNDARY_CONTAINS_1() {
        check(rectangle(-80.0, 80.0, -20.0, 20.0), point(0.0, 20.0));
    }

    @Test
    public void SOUTH_POLE_LATITUDE_BOUNDARY_1() {
        Point lowerLeft = point(-80.0, -90.0);
        Point upperRight = point(80.0, -60.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(0.0, -90.0));
    }

    @Test
    public void NORTH_POLE_LATITUDE_BOUNDARY_1() {
        check(rectangle(-80.0, 80.0, -60.0, 90.0), point(0.0, 90.0));
    }

    @Test
    public void ZERO_WIDTH_VERTICAL_LINE_CONTAINS_1() {
        check(rectangle(40.0, 40.0, -20.0, 20.0), point(40.0, 0.0));
    }

    @Test
    public void ZERO_WIDTH_VERTICAL_LINE_CONTAINS_2() {
        Point lowerLeft = point(-30.0, -20.0);
        Point upperRight = point(-30.0, 20.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(-30.0, 20.0));
    }

    @Test
    public void ZERO_WIDTH_VERTICAL_LINE_DISJOINT_1() {
        RectangleImpl source = rectangle(40.0, 40.0, -20.0, 20.0);
        check(new RectangleImpl(source, GEO), point(50.0, 0.0));
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_LINE_CONTAINS_1() {
        check(rectangle(-60.0, 60.0, 10.0, 10.0), point(0.0, 10.0));
    }

    @Test
    public void ROTATION_CROSSES_DATELINE_1() {
        Point lowerLeft = point(20.0, -20.0);
        Point upperRight = point(80.0, 20.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(50.0, 0.0));
    }

    @Test
    public void ROTATION_CROSSES_DATELINE_2() {
        RectangleImpl source = rectangle(30.0, 70.0, -30.0, 30.0);
        check(new RectangleImpl(source, GEO), point(40.0, 10.0));
    }

    @Test
    public void ROTATION_REMAINS_NON_WRAPPING_1() {
        check(rectangle(-170.0, -100.0, -20.0, 20.0), point(-130.0, 0.0));
    }

    @Test
    public void EMPTY_MINX_NAN_SENTINEL_1() {
        check(rectangle(Double.NaN, 100.0, -20.0, 20.0), point(0.0, 0.0));
    }

    @Test
    public void EMPTY_MINX_NAN_LATITUDE_REJECTION_1() {
        check(rectangle(Double.NaN, 100.0, -20.0, 20.0), point(0.0, 30.0));
    }

    @Test
    public void POINT_PAIR_CONSTRUCTOR_INTERIOR_1() {
        Point lowerLeft = point(-30.0, -20.0);
        Point upperRight = point(30.0, 20.0);
        check(new RectangleImpl(lowerLeft, upperRight, GEO), point(0.0, 0.0));
    }

    @Test
    public void RECTANGLE_COPY_CONSTRUCTOR_DATELINE_1() {
        RectangleImpl original = rectangle(170.0, -170.0, -20.0, 20.0);
        RectangleImpl source = new RectangleImpl(original, GEO);
        check(source, point(-175.0, 0.0));
    }
}
