import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private void verify(double minX, double maxX, double minY, double maxY,
                        double pointX, double pointY) {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUpValues =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUpValues[0];
        Point followUpPoint = (Point) followUpValues[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_INTERIOR_CONTAINS_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -60.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_X_BOUNDARY_CONTAINS_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -100.0, 0.0);
    }

    @Test
    void ORDINARY_MAX_X_BOUNDARY_CONTAINS_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -20.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_Y_BOUNDARY_CONTAINS_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -60.0, -10.0);
    }

    @Test
    void ORDINARY_MAX_Y_BOUNDARY_CONTAINS_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -60.0, 10.0);
    }

    @Test
    void ORDINARY_BELOW_MIN_Y_DISJOINT_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -60.0, -10.001);
    }

    @Test
    void ORDINARY_ABOVE_MAX_Y_DISJOINT_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -60.0, 10.001);
    }

    @Test
    void ORDINARY_LEFT_LONGITUDE_DISJOINT_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -110.0, 0.0);
    }

    @Test
    void ORDINARY_RIGHT_LONGITUDE_DISJOINT_variation1() {
        verify(-100.0, -20.0, -10.0, 10.0, -10.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_WRAPPED_RECTANGLE_INTERIOR_variation1() {
        verify(-20.0, 60.0, -10.0, 10.0, 20.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_WRAPPED_MIN_X_EDGE_variation1() {
        verify(-20.0, 60.0, -10.0, 10.0, -20.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_WRAPPED_MAX_X_EDGE_variation1() {
        verify(-20.0, 60.0, -10.0, 10.0, 60.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_WRAPPED_LEFT_DISJOINT_variation1() {
        verify(-20.0, 60.0, -10.0, 10.0, -21.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_WRAPPED_RIGHT_DISJOINT_variation1() {
        verify(-20.0, 60.0, -10.0, 10.0, 61.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_WRAPPED_LATITUDE_REJECTION_variation1() {
        verify(-20.0, 60.0, -10.0, 10.0, 20.0, 10.001);
    }

    @Test
    void WRAPPED_DIRECT_CONTAINS_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, 150.0, 0.0);
    }

    @Test
    void WRAPPED_SHIFTED_CONTAINS_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, -150.0, 0.0);
    }

    @Test
    void WRAPPED_MIN_X_BOUNDARY_CONTAINS_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, 120.0, 0.0);
    }

    @Test
    void WRAPPED_MAX_X_BOUNDARY_CONTAINS_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, -120.0, 0.0);
    }

    @Test
    void WRAPPED_GAP_DISJOINT_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void WRAPPED_MIN_Y_BOUNDARY_CONTAINS_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, -150.0, -10.0);
    }

    @Test
    void WRAPPED_MAX_Y_BOUNDARY_CONTAINS_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, -150.0, 10.0);
    }

    @Test
    void WRAPPED_BELOW_LATITUDE_DISJOINT_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, -150.0, -10.001);
    }

    @Test
    void WRAPPED_ABOVE_LATITUDE_DISJOINT_variation1() {
        verify(120.0, -120.0, -10.0, 10.0, -150.0, 10.001);
    }

    @Test
    void WRAPPED_REMAINS_WRAPPED_AFTER_ROTATION_variation1() {
        verify(100.0, 50.0, -10.0, 10.0, 120.0, 0.0);
    }

    @Test
    void WRAPPED_REMAINS_WRAPPED_SHIFTED_CONTAINS_variation1() {
        verify(100.0, 50.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void WRAPPED_REMAINS_WRAPPED_GAP_DISJOINT_variation1() {
        verify(100.0, 50.0, -10.0, 10.0, 75.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_VERTICAL_LINE_CONTAINS_variation1() {
        verify(20.0, 20.0, -10.0, 10.0, 20.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_VERTICAL_LINE_DISJOINT_variation1() {
        verify(20.0, 20.0, -10.0, 10.0, 19.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_HORIZONTAL_LINE_CONTAINS_variation1() {
        verify(-20.0, 20.0, 5.0, 5.0, 0.0, 5.0);
    }

    @Test
    void ZERO_HEIGHT_HORIZONTAL_LINE_DISJOINT_variation1() {
        verify(-20.0, 20.0, 5.0, 5.0, 0.0, 4.999);
    }

    @Test
    void POINT_RECTANGLE_CONTAINS_variation1() {
        verify(30.0, 30.0, 5.0, 5.0, 30.0, 5.0);
    }

    @Test
    void DATELINE_VERTICAL_EQUIVALENT_POINT_CONTAINS_variation1() {
        verify(180.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    void OPPOSITE_DATELINE_ENDPOINTS_ZERO_WIDTH_variation1() {
        verify(180.0, -180.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void NORMALIZATION_ENDPOINT_TO_MINUS_180_variation1() {
        verify(30.0, 31.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void NORMALIZATION_CUT_AT_SOURCE_MAX_X_variation1() {
        verify(29.0, 30.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void NARROW_INTERVAL_STRADDLING_ROTATION_CUT_variation1() {
        verify(29.999, 30.001, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void BROAD_ORDINARY_INTERVAL_CONTAINS_variation1() {
        verify(-170.0, 170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void BROAD_ORDINARY_GAP_DISJOINT_variation1() {
        verify(-170.0, 170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void BROAD_WRAPPED_INTERVAL_CONTAINS_variation1() {
        verify(170.0, 150.0, -10.0, 10.0, 0.0, 0.0);
    }
}
