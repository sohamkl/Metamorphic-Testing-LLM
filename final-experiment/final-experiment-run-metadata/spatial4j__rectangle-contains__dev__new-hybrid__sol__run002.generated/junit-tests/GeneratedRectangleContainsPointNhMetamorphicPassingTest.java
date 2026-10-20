import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final SpatialContext GEO = new SpatialContext(true);

    private void exercise(RectangleImpl rectangle, Point point) {
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_INTERIOR_NON_WRAPPING_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(0.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void LATITUDE_STRICTLY_ABOVE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-20.5, 20.25, -10.5, 10.25, GEO);
        Point point = rectangle.getContext().makePoint(0.5, 10.250001);
        exercise(rectangle, point);
    }

    @Test
    public void LATITUDE_STRICTLY_BELOW_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-30.0, 30.0, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(-0.0, -10.000001);
        exercise(rectangle, point);
    }

    @Test
    public void POINT_ON_MIN_LATITUDE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -45.0, 25.0, GEO);
        Point point = rectangle.getContext().makePoint(-180.0, -45.0);
        exercise(rectangle, point);
    }

    @Test
    public void POINT_ON_MAX_LATITUDE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-60.0, 80.0, -25.0, 40.0, GEO);
        Point point = rectangle.getContext().makePoint(10.0, 40.0);
        exercise(rectangle, point);
    }

    @Test
    public void ZERO_HEIGHT_MATCH_variation1() {
        RectangleImpl rectangle = new RectangleImpl(160.0, -160.0, 90.0, 90.0, GEO);
        Point point = rectangle.getContext().makePoint(175.0, 90.0);
        exercise(rectangle, point);
    }

    @Test
    public void ZERO_HEIGHT_MISMATCH_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-25.0, 25.0, 5.0, 5.0, GEO);
        Point point = rectangle.getContext().makePoint(0.0, 6.0);
        exercise(rectangle, point);
    }

    @Test
    public void NORTH_POLE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(150.5, -170.25, 70.5, 90.0, GEO);
        Point point = rectangle.getContext().makePoint(175.75, 90.0);
        exercise(rectangle, point);
    }

    @Test
    public void SOUTH_POLE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-45.0, 45.0, -90.0, -60.0, GEO);
        Point point = rectangle.getContext().makePoint(0.0, -90.0);
        exercise(rectangle, point);
    }

    @Test
    public void NON_WRAPPING_MIN_LONGITUDE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-180.0, -120.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(-180.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void NON_WRAPPING_MAX_LONGITUDE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(100.0, 180.0, -30.0, 30.0, GEO);
        Point point = rectangle.getContext().makePoint(180.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void NON_WRAPPING_WEST_OUTSIDE_AFTER_SHIFT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-40.0, 40.0, -15.0, 15.0, GEO);
        Point point = rectangle.getContext().makePoint(-100.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void NON_WRAPPING_EAST_OUTSIDE_AFTER_SHIFT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-40.0, 40.0, -15.0, 15.0, GEO);
        Point point = rectangle.getContext().makePoint(100.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void NEGATIVE_DATELINE_ALIAS_TO_POSITIVE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(120.25, 180.0, -10.5, 10.5, GEO);
        Point point = rectangle.getContext().makePoint(-180.0, 0.25);
        exercise(rectangle, point);
    }

    @Test
    public void POSITIVE_DATELINE_ALIAS_TO_NEGATIVE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-180.0, -120.0, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(180.0, -0.0);
        exercise(rectangle, point);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_MATCH_variation1() {
        RectangleImpl rectangle = new RectangleImpl(35.0, 35.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(35.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_WEST_MISS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(45.0, 45.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(44.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_EAST_MISS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-45.0, -45.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(-44.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void NEGATIVE_DATELINE_ZERO_WIDTH_ALIAS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-180.0, -180.0, -30.0, 30.0, GEO);
        Point point = rectangle.getContext().makePoint(180.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void POSITIVE_DATELINE_ZERO_WIDTH_ALIAS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(180.0, 180.0, -30.0, 30.0, GEO);
        Point point = rectangle.getContext().makePoint(-180.0, 0.5);
        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_EAST_SEGMENT_INTERIOR_variation1() {
        RectangleImpl rectangle = new RectangleImpl(150.0, -140.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(170.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_WEST_SEGMENT_INTERIOR_variation1() {
        RectangleImpl rectangle = new RectangleImpl(150.0, -140.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(-160.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_MIN_LONGITUDE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(125.0, -155.0, -15.0, 15.0, GEO);
        Point point = rectangle.getContext().makePoint(125.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_MAX_LONGITUDE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(125.0, -155.0, -15.0, 15.0, GEO);
        Point point = rectangle.getContext().makePoint(-155.0, 15.0);
        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_GAP_INTERIOR_variation1() {
        RectangleImpl rectangle = new RectangleImpl(100.0, -100.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(0.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_GAP_JUST_BELOW_MIN_variation1() {
        RectangleImpl rectangle = new RectangleImpl(50.0, -120.0, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(49.9999995, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_GAP_JUST_ABOVE_MAX_variation1() {
        RectangleImpl rectangle = new RectangleImpl(50.0, -120.0, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(-119.9999995, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void NARROW_WRAPPED_RECTANGLE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(160.0, -150.0, -25.0, 25.0, GEO);
        Point point = rectangle.getContext().makePoint(175.0, 5.0);
        exercise(rectangle, point);
    }

    @Test
    public void SEMICIRCLE_WRAPPED_RECTANGLE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(90.0, -90.0, -30.0, 30.0, GEO);
        Point point = rectangle.getContext().makePoint(180.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void WIDE_WRAPPED_RECTANGLE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(30.0, -100.0, -40.0, 40.0, GEO);
        Point point = rectangle.getContext().makePoint(120.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void ALMOST_WORLD_WRAPPED_GAP_variation1() {
        RectangleImpl rectangle = new RectangleImpl(0.5, 0.0, -45.0, 45.0, GEO);
        Point point = rectangle.getContext().makePoint(0.25, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void DATELINE_ENDPOINT_PAIR_ZERO_WIDTH_variation1() {
        RectangleImpl rectangle = new RectangleImpl(180.0, -180.0, -12.5, 12.5, GEO);
        Point point = rectangle.getContext().makePoint(-180.0, -12.5);
        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_CREATES_WRAPPED_CONTAINS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 60.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(40.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_CREATES_WRAPPED_DISJOINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 60.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(-50.0, 20.0);
        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_REMOVES_WRAP_EAST_CONTAINS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(100.0, 0.0, -30.0, 30.0, GEO);
        Point point = rectangle.getContext().makePoint(120.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_REMOVES_WRAP_WEST_CONTAINS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(100.0, 0.0, -30.0, 30.0, GEO);
        Point point = rectangle.getContext().makePoint(-20.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_REMOVES_WRAP_GAP_DISJOINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(100.0, 0.0, -30.0, 30.0, GEO);
        Point point = rectangle.getContext().makePoint(50.0, -30.0);
        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_PRESERVES_NON_WRAP_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-100.0, 0.0, -25.0, 25.0, GEO);
        Point point = rectangle.getContext().makePoint(-50.0, 5.0);
        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_PRESERVES_WRAP_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, -100.0, -25.0, 25.0, GEO);
        Point point = rectangle.getContext().makePoint(100.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void SOURCE_DIRECT_ROTATED_SHIFTED_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 60.0, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(40.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void SOURCE_SHIFTED_ROTATED_DIRECT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-180.0, -100.0, -15.0, 15.0, GEO);
        Point point = rectangle.getContext().makePoint(180.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void POINT_ROTATION_CROSSES_DATELINE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(40.0, 80.0, -20.0, 20.0, GEO);
        Point point = rectangle.getContext().makePoint(55.0, -20.0);
        exercise(rectangle, point);
    }

    @Test
    public void SIGNED_ZERO_LONGITUDE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-0.0, 10.0, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(+0.0, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void FRACTIONAL_INTERIOR_COORDINATES_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-12.75, 48.125, -7.5, 19.875, GEO);
        Point point = rectangle.getContext().makePoint(3.625, 4.25);
        exercise(rectangle, point);
    }

    @Test
    public void TINY_POSITIVE_NON_WRAPPED_WIDTH_variation1() {
        RectangleImpl rectangle = new RectangleImpl(10.0, 10.0000008, -5.0, 5.0, GEO);
        Point point = rectangle.getContext().makePoint(10.0000004, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void TINY_WRAPPED_GAP_variation1() {
        RectangleImpl rectangle = new RectangleImpl(10.0000008, 10.0, -5.0, 5.0, GEO);
        Point point = rectangle.getContext().makePoint(10.0000004, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void NEAR_POSITIVE_DATELINE_INTERIOR_variation1() {
        RectangleImpl rectangle = new RectangleImpl(179.9999980, 179.9999995, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(179.9999990, -10.0);
        exercise(rectangle, point);
    }

    @Test
    public void NEAR_NEGATIVE_DATELINE_INTERIOR_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-179.9999995, -179.9999980, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(-179.9999990, 0.0);
        exercise(rectangle, point);
    }

    @Test
    public void LONGITUDE_CONTAINED_LATITUDE_ABOVE_AFTER_ROTATION_CROSSING_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 60.0, -10.0, 10.0, GEO);
        Point point = rectangle.getContext().makePoint(40.0, 11.0);
        exercise(rectangle, point);
    }

    @Test
    public void LONGITUDE_CONTAINED_LATITUDE_BELOW_AFTER_ROTATION_CROSSING_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.25, 60.75, -10.5, 10.5, GEO);
        Point point = rectangle.getContext().makePoint(40.5, -10.500001);
        exercise(rectangle, point);
    }
}
