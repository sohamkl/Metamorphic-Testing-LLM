import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private void verify(double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point point = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_WRAPPING_STRICT_INTERIOR_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void NON_WRAPPING_MIN_X_BOUNDARY_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 20.0, 0.0);
    }

    @Test
    void NON_WRAPPING_MAX_X_BOUNDARY_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 40.0, 0.0);
    }

    @Test
    void LOWER_LATITUDE_BOUNDARY_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 30.0, -10.0);
    }

    @Test
    void UPPER_LATITUDE_BOUNDARY_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 30.0, 10.0);
    }

    @Test
    void BELOW_MIN_Y_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 30.0, -11.0);
    }

    @Test
    void ABOVE_MAX_Y_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 30.0, 11.0);
    }

    @Test
    void NON_WRAPPING_WEST_SHIFTED_DISJOINT_variation1() {
        verify(100.0, 170.0, -10.0, 10.0, -175.0, 0.0);
    }

    @Test
    void NON_WRAPPING_EAST_SHIFTED_DISJOINT_variation1() {
        verify(-170.0, -100.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    void DATELINE_WRAP_EASTERN_LOBE_DIRECT_CONTAINS_variation1() {
        verify(170.0, -170.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    void DATELINE_WRAP_WESTERN_LOBE_SHIFTED_CONTAINS_variation1() {
        verify(170.0, -170.0, -10.0, 10.0, -175.0, 0.0);
    }

    @Test
    void DATELINE_WRAP_CENTRAL_GAP_DISJOINT_variation1() {
        verify(170.0, -170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void DATELINE_WRAP_MIN_X_BOUNDARY_variation1() {
        verify(170.0, -170.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    void DATELINE_WRAP_MAX_X_BOUNDARY_variation1() {
        verify(170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_DATELINE_WRAP_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 30.0, 1.0);
    }

    @Test
    void ROTATION_REMOVES_DATELINE_WRAP_variation1() {
        verify(100.0, -100.0, -10.0, 10.0, 150.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_VERTICAL_LINE_CONTAINS_variation1() {
        verify(30.0, 30.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_VERTICAL_LINE_DISJOINT_variation1() {
        verify(30.0, 30.0, -10.0, 10.0, 31.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_HORIZONTAL_LINE_CONTAINS_variation1() {
        verify(20.0, 40.0, 5.0, 5.0, 30.0, 5.0);
    }

    @Test
    void ZERO_HEIGHT_HORIZONTAL_LINE_DISJOINT_variation1() {
        verify(20.0, 40.0, 5.0, 5.0, 30.0, 6.0);
    }

    @Test
    void POSITIVE_DATELINE_ENDPOINT_EQUIVALENCE_variation1() {
        verify(-180.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void NEGATIVE_DATELINE_ENDPOINT_EQUIVALENCE_variation1() {
        verify(170.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    void EMPTY_MIN_X_NAN_SENTINEL_variation1() {
        verify(Double.NaN, 10.0, -10.0, 10.0, 0.0, 0.0);
    }
}
