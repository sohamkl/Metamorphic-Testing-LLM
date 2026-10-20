import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final SpatialContext GEO = new SpatialContext(true);
    private static final double ROTATION_DEG = 150.0;

    private static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        SpatialContext context = rectangle.getContext();

        RectangleImpl rotatedRectangle = new RectangleImpl(
                DistanceUtils.normLonDEG(rectangle.getMinX() + ROTATION_DEG),
                DistanceUtils.normLonDEG(rectangle.getMaxX() + ROTATION_DEG),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);

        Point rotatedPoint = context.makePoint(
                DistanceUtils.normLonDEG(point.getX() + ROTATION_DEG),
                point.getY());

        return new Object[]{rotatedRectangle, rotatedPoint};
    }

    private static void exercise(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {

        RectangleImpl sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, GEO);
        Point sourcePoint =
                sourceRectangle.getContext().makePoint(pointX, pointY);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput =
                followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void UNWRAPPED_INTERIOR_ROTATES_UNWRAPPED_variation1() {
        exercise(-100.0, -40.0, -20.0, 30.0, -70.0, 0.0);
    }

    @Test
    public void UNWRAPPED_INTERIOR_ROTATES_WRAPPED_variation1() {
        exercise(0.0, 60.0, -20.0, 30.0, 30.0, 0.0);
    }

    @Test
    public void WRAPPED_DIRECT_SEGMENT_ROTATES_WRAPPED_variation1() {
        exercise(20.0, -100.0, -20.0, 30.0, 100.0, 0.0);
    }

    @Test
    public void WRAPPED_SHIFTED_SEGMENT_ROTATES_UNWRAPPED_variation1() {
        exercise(140.0, -100.0, -20.0, 30.0, -120.0, 0.0);
    }

    @Test
    public void NARROW_WRAP_EASTERN_INTERIOR_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    public void NARROW_WRAP_WESTERN_INTERIOR_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -175.0, 0.0);
    }

    @Test
    public void UNWRAPPED_MIN_X_BOUNDARY_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, -40.0, 0.0);
    }

    @Test
    public void UNWRAPPED_MAX_X_BOUNDARY_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 20.0, 0.0);
    }

    @Test
    public void WRAPPED_MIN_X_BOUNDARY_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    public void WRAPPED_MAX_X_BOUNDARY_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    public void MIN_Y_BOUNDARY_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 0.0, -20.0);
    }

    @Test
    public void MAX_Y_BOUNDARY_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 0.0, 30.0);
    }

    @Test
    public void SOUTHWEST_CORNER_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, -40.0, -20.0);
    }

    @Test
    public void WRAPPED_NORTHWEST_ENDPOINT_CORNER_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -170.0, 10.0);
    }

    @Test
    public void LATITUDE_ABOVE_UNWRAPPED_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 0.0, 31.0);
    }

    @Test
    public void LATITUDE_BELOW_UNWRAPPED_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 0.0, -21.0);
    }

    @Test
    public void LATITUDE_ABOVE_WRAPPED_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -175.0, 11.0);
    }

    @Test
    public void LATITUDE_BELOW_WRAPPED_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 175.0, -11.0);
    }

    @Test
    public void LONGITUDE_WEST_OF_UNWRAPPED_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, -41.0, 0.0);
    }

    @Test
    public void LONGITUDE_EAST_OF_UNWRAPPED_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 21.0, 0.0);
    }

    @Test
    public void NARROW_WRAP_CENTRAL_GAP_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void OFFSET_WRAP_GAP_variation1() {
        exercise(-20.0, -100.0, -20.0, 30.0, -50.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_LINE_CONTAINS_POINT_variation1() {
        exercise(10.0, 10.0, -20.0, 30.0, 10.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_LINE_REJECTS_WEST_POINT_variation1() {
        exercise(10.0, 10.0, -20.0, 30.0, 9.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_LINE_REJECTS_EAST_POINT_variation1() {
        exercise(10.0, 10.0, -20.0, 30.0, 11.0, 0.0);
    }

    @Test
    public void DATELINE_ZERO_WIDTH_CONTAINS_POSITIVE_180_variation1() {
        exercise(180.0, -180.0, -20.0, 30.0, 180.0, 0.0);
    }

    @Test
    public void DATELINE_ZERO_WIDTH_CONTAINS_NEGATIVE_180_variation1() {
        exercise(180.0, -180.0, -20.0, 30.0, -180.0, 0.0);
    }

    @Test
    public void DATELINE_ZERO_WIDTH_REJECTS_ZERO_variation1() {
        exercise(180.0, -180.0, -20.0, 30.0, 0.0, 0.0);
    }

    @Test
    public void ZERO_HEIGHT_LINE_CONTAINS_POINT_variation1() {
        exercise(-40.0, 20.0, 5.0, 5.0, 0.0, 5.0);
    }

    @Test
    public void ZERO_HEIGHT_LINE_REJECTS_POINT_variation1() {
        exercise(-40.0, 20.0, 5.0, 5.0, 0.0, 6.0);
    }

    @Test
    public void POINT_RECTANGLE_EXACT_MATCH_variation1() {
        exercise(10.0, 10.0, 5.0, 5.0, 10.0, 5.0);
    }

    @Test
    public void POINT_RECTANGLE_LONGITUDE_MISMATCH_variation1() {
        exercise(10.0, 10.0, 5.0, 5.0, 11.0, 5.0);
    }

    @Test
    public void POINT_RECTANGLE_LATITUDE_MISMATCH_variation1() {
        exercise(10.0, 10.0, 5.0, 5.0, 10.0, 6.0);
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_variation1() {
        exercise(-40.0, 20.0, -90.0, -70.0, 0.0, -90.0);
    }

    @Test
    public void NORTH_POLE_BOUNDARY_variation1() {
        exercise(-40.0, 20.0, 70.0, 90.0, 0.0, 90.0);
    }

    @Test
    public void NEGATIVE_180_RECTANGLE_ENDPOINT_WITH_POSITIVE_180_POINT_variation1() {
        exercise(-180.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void POSITIVE_180_RECTANGLE_ENDPOINT_WITH_NEGATIVE_180_POINT_variation1() {
        exercise(170.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void VERY_WIDE_UNWRAPPED_INTERIOR_variation1() {
        exercise(-170.0, 170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void VERY_WIDE_UNWRAPPED_DATELINE_GAP_variation1() {
        exercise(-170.0, 170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void VERY_WIDE_WRAPPED_INTERIOR_variation1() {
        exercise(170.0, 160.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void VERY_WIDE_WRAPPED_NARROW_GAP_variation1() {
        exercise(170.0, 160.0, -10.0, 10.0, 165.0, 0.0);
    }

    @Test
    public void ROTATION_CUT_ENDPOINT_AT_180_variation1() {
        exercise(-30.0, 30.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void ROTATION_CUT_STARTS_AT_180_variation1() {
        exercise(30.0, 60.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void JUST_INSIDE_MIN_X_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, -39.999999999, 0.0);
    }

    @Test
    public void JUST_OUTSIDE_MIN_X_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, -40.000000001, 0.0);
    }

    @Test
    public void JUST_INSIDE_MAX_X_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 19.999999999, 0.0);
    }

    @Test
    public void JUST_OUTSIDE_MAX_X_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 20.000000001, 0.0);
    }

    @Test
    public void JUST_INSIDE_MAX_Y_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 0.0, 29.999999999);
    }

    @Test
    public void JUST_OUTSIDE_MAX_Y_variation1() {
        exercise(-40.0, 20.0, -20.0, 30.0, 0.0, 30.000000001);
    }
}
