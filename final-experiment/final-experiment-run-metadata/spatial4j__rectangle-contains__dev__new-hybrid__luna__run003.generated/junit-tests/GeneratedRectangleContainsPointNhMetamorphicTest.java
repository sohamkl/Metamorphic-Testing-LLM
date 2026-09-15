import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicTest {

    private static SpatialContext geo() {
        return new SpatialContext(true);
    }

    private static RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY,
            SpatialContext context) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static Point point(double x, double y, SpatialContext context) {
        return new PointImpl(x, y, context);
    }

    private static void verify(RectangleImpl source, Point sourcePoint) {
        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_INTERIOR_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void WEST_LONGITUDE_SHIFT_BRANCH_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(-100.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void EAST_LONGITUDE_SHIFT_BRANCH_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(100.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void MIN_X_INCLUSIVE_BOUNDARY_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(-40.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void MAX_X_INCLUSIVE_BOUNDARY_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(40.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void BELOW_MIN_Y_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, -20.000001, context);
        verify(source, sourcePoint);
    }

    @Test
    public void ABOVE_MAX_Y_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, 20.000001, context);
        verify(source, sourcePoint);
    }

    @Test
    public void MIN_Y_INCLUSIVE_BOUNDARY_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, -20.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void MAX_Y_INCLUSIVE_BOUNDARY_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, 20.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void CROSSING_EAST_LOBE_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(170.0, -170.0, -20.0, 20.0, context);
        Point sourcePoint = point(175.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void CROSSING_WEST_LOBE_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(170.0, -170.0, -20.0, 20.0, context);
        Point sourcePoint = point(-175.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void CROSSING_GAP_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(170.0, -170.0, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void CROSSING_LONGITUDE_BOUNDARIES_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(170.0, -170.0, -20.0, 20.0, context);
        Point sourcePoint = point(170.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void CROSSING_LONGITUDE_BOUNDARIES_variation2() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(170.0, -170.0, -20.0, 20.0, context);
        Point sourcePoint = point(-170.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void ROTATION_CREATES_DATELINE_WRAP_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(20.0, 80.0, -20.0, 20.0, context);
        Point sourcePoint = point(50.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void ROTATION_REMOVES_DATELINE_WRAP_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(170.0, -170.0, -20.0, 20.0, context);
        Point sourcePoint = point(175.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_LINE_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(30.0, 30.0, -20.0, 20.0, context);
        Point sourcePoint = point(30.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_MISS_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(30.0, 30.0, -20.0, 20.0, context);
        Point sourcePoint = point(31.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_LINE_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, 10.0, 10.0, context);
        Point sourcePoint = point(0.0, 10.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void DATELINE_ENDPOINT_NORMALIZATION_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(120.0, 180.0, -20.0, 20.0, context);
        Point sourcePoint = point(150.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void MIN_X_NAN_SENTINEL_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(Double.NaN, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void MAX_X_NAN_SENTINEL_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, Double.NaN, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void MIN_Y_NAN_SENTINEL_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, Double.NaN, 20.0, context);
        Point sourcePoint = point(0.0, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void POINT_X_NAN_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(Double.NaN, 0.0, context);
        verify(source, sourcePoint);
    }

    @Test
    public void POINT_Y_NAN_variation1() {
        SpatialContext context = geo();
        RectangleImpl source = rectangle(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(0.0, Double.NaN, context);
        verify(source, sourcePoint);
    }
}
