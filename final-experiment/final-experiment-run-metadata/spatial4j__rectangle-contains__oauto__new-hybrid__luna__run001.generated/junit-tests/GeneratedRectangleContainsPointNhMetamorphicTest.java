import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicTest {

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

        return new Object[] {rotatedRectangle, rotatedPoint};
    }

    private static void exercise(RectangleImpl rectangle, Point point) {
        SpatialRelation sourceOutput = rectangle.relate(point);

        Object[] followUp = generateFollowUp(rectangle, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_BELOW_EARLY_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-100, 100, -20, 20, context);
        Point point = new PointImpl(0, -21, context);
        exercise(rectangle, point);
    }

    @Test
    public void LATITUDE_ABOVE_EARLY_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(170, -170, -20, 20, context);
        Point point = new PointImpl(170, 21, context);
        exercise(rectangle, point);
    }

    @Test
    public void LATITUDE_MINIMUM_INCLUSIVE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(30, 30, -20, 20, context);
        Point point = new PointImpl(30, -5, context);
        exercise(rectangle, point);
    }

    @Test
    public void LATITUDE_MAXIMUM_INCLUSIVE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-180, 180, -20, 20, context);
        Point point = new PointImpl(-170, 20, context);
        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_LONGITUDE_STRICTLY_INSIDE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(
                Double.NaN, 180, -20, 20, context);
        Point point = new PointImpl(0.0, 21.0, context);
        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_MIN_LONGITUDE_INCLUSIVE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-100, 100, -20, 20, context);
        Point point = new PointImpl(-100, -21, context);
        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_MAX_LONGITUDE_INCLUSIVE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(170, -170, -20, 20, context);
        Point point = new PointImpl(-170, -20, context);
        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_POINT_BELOW_LONGITUDE_SHIFT_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(30, 30, -20, 20, context);
        Point point = new PointImpl(30.0, 0.0, context);
        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_POINT_ABOVE_LONGITUDE_SHIFT_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-180, 180, -20, 20, context);
        Point point = new PointImpl(180, 20, context);
        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_LONGITUDE_OUTSIDE_NO_OVERLAP_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(
                Double.NaN, 180, -20, 20, context);
        Point point = new PointImpl(-179.0, 21.0, context);
        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_ROTATION_CREATES_DATELINE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-100.0, 100.0, -20, 20, context);
        Point point = new PointImpl(0.0 + 0.0, 0.0, context);
        exercise(rectangle, point);
    }

    @Test
    public void DATELINE_POINT_IN_EASTERN_LOBE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(170, -170, -20, 20, context);
        Point point = new PointImpl(175, -20, context);
        exercise(rectangle, point);
    }

    @Test
    public void DATELINE_POINT_IN_WESTERN_LOBE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(170, -170, -20, 20, context);
        Point point = new PointImpl(-175, 0.0 + 0.0, context);
        exercise(rectangle, point);
    }

    @Test
    public void DATELINE_GAP_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-180, 180, -20, 20, context);
        Point point = new PointImpl(180, 20, context);
        exercise(rectangle, point);
    }

    @Test
    public void DATELINE_BOUNDARY_AT_MINIMUM_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(
                Double.NaN, 180, -20, 20, context);
        Point point = new PointImpl(180.0, 21.0, context);
        exercise(rectangle, point);
    }

    @Test
    public void DATELINE_BOUNDARY_AT_MAXIMUM_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-100, 100, -20, 20, context);
        Point point = new PointImpl(-170, -21, context);
        exercise(rectangle, point);
    }

    @Test
    public void DATELINE_ROTATION_BECOMES_ORDINARY_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -20, 20, context);
        Point point = new PointImpl(-175.0, -20.0, context);
        exercise(rectangle, point);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_CONTAINS_ENDPOINT_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(30, 30, -10, 10, context);
        Point point = new PointImpl(31, 0, context);
        exercise(rectangle, point);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-180, 180, -20, 20, context);
        Point point = new PointImpl(0.0, 20.0, context);
        exercise(rectangle, point);
    }

    @Test
    public void WORLD_WIDTH_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(
                Double.NaN, 180, -20, 20, context);
        Point point = new PointImpl(-180, 21, context);
        exercise(rectangle, point);
    }

    @Test
    public void WIDE_ORDINARY_INTERVAL_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(-170, 170, -20, 20, context);
        Point point = new PointImpl(170, -21, context);
        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_NORMALIZATION_ENDPOINT_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(30, 60, -20, 20, context);
        Point point = new PointImpl(30, -20, context);
        exercise(rectangle, point);
    }

    @Test
    public void EMPTY_RECTANGLE_SENTINEL_variation1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(
                Double.NaN, 180, -20, 20, context);
        Point point = new PointImpl(0, 0, context);
        exercise(rectangle, point);
    }
}
