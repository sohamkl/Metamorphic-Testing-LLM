import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final double ROTATION_DEGREES = 150.0;

    private MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);
        Point point = context.makePoint(pointX, pointY);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rectangle, point);
    }

    private MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl sourceRectangle = source.receiver();
        Point sourcePoint = source.arg0();

        SpatialContext context = new SpatialContext(true);
        RectangleImpl rotatedRectangle = new RectangleImpl(
                normalizeLongitude(sourceRectangle.getMinX() + ROTATION_DEGREES),
                normalizeLongitude(sourceRectangle.getMaxX() + ROTATION_DEGREES),
                sourceRectangle.getMinY(),
                sourceRectangle.getMaxY(),
                context);
        Point rotatedPoint = context.makePoint(
                normalizeLongitude(sourcePoint.getX() + ROTATION_DEGREES),
                sourcePoint.getY());

        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rotatedRectangle, rotatedPoint);
    }

    private double normalizeLongitude(double longitude) {
        double normalized = longitude % 360.0;
        if (normalized < -180.0) {
            normalized += 360.0;
        } else if (normalized >= 180.0) {
            normalized -= 360.0;
        }
        return normalized == 0.0 ? 0.0 : normalized;
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        SpatialRelation sourceOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(
                        generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(
                sourceOutput,
                followUpOutput,
                "Rotating the rectangle and point east by 150 degrees must preserve relate(Point)");
    }

    @Test
    public void LATITUDE_ABOVE_MAX_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, 11));
    }

    @Test
    public void LATITUDE_BELOW_MIN_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, -11));
    }

    @Test
    public void LATITUDE_AT_MIN_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, -10));
    }

    @Test
    public void LATITUDE_AT_MAX_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, 10));
    }

    @Test
    public void ZERO_HEIGHT_ON_LINE_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, 5, 5, 0, 5));
    }

    @Test
    public void ZERO_HEIGHT_OFF_LINE_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, 5, 5, 0, 6));
    }

    @Test
    public void NORTH_POLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-40, 40, 80, 90, 0, 90));
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-40, 40, -90, -80, 0, -90));
    }

    @Test
    public void FULL_LATITUDE_SPAN_variation1() {
        assertMetamorphicRelationFor(source(-10, 10, -90, 90, 0, 45));
    }

    @Test
    public void ORDINARY_LONGITUDE_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 5, 0));
    }

    @Test
    public void ORDINARY_MIN_X_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, -20, 0));
    }

    @Test
    public void ORDINARY_MAX_X_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 20, 0));
    }

    @Test
    public void ORDINARY_LEFT_MISS_AFTER_PLUS_SHIFT_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, -21, 0));
    }

    @Test
    public void ORDINARY_RIGHT_MISS_AFTER_MINUS_SHIFT_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 21, 0));
    }

    @Test
    public void ZERO_WIDTH_ON_MERIDIAN_variation1() {
        assertMetamorphicRelationFor(source(12, 12, -10, 10, 12, 0));
    }

    @Test
    public void ZERO_WIDTH_OFF_MERIDIAN_variation1() {
        assertMetamorphicRelationFor(source(12, 12, -10, 10, 13, 0));
    }

    @Test
    public void WRAPPED_WESTERN_LOBE_DIRECT_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, 175, 0));
    }

    @Test
    public void WRAPPED_EASTERN_LOBE_PLUS_SHIFT_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, -175, 0));
    }

    @Test
    public void WRAPPED_CENTRAL_GAP_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, 0, 0));
    }

    @Test
    public void WRAPPED_MIN_X_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, 170, 0));
    }

    @Test
    public void WRAPPED_MAX_X_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, -170, 0));
    }

    @Test
    public void BROAD_WRAPPED_INTERIOR_DIRECT_variation1() {
        assertMetamorphicRelationFor(source(-10, -20, -10, 10, 0, 0));
    }

    @Test
    public void BROAD_WRAPPED_NARROW_GAP_variation1() {
        assertMetamorphicRelationFor(source(-10, -20, -10, 10, -15, 0));
    }

    @Test
    public void HALF_WORLD_MAX_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-90, 90, -10, 10, 90, 0));
    }

    @Test
    public void WIDER_THAN_HALF_WORLD_MISS_variation1() {
        assertMetamorphicRelationFor(source(-120, 120, -10, 10, 150, 0));
    }

    @Test
    public void NEAR_WORLD_WRAPPED_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(1, 0, -10, 10, -100, 0));
    }

    @Test
    public void NEAR_WORLD_WRAPPED_GAP_variation1() {
        assertMetamorphicRelationFor(source(1, 0, -10, 10, 0.5, 0));
    }

    @Test
    public void POSITIVE_180_EQUIVALENT_INSIDE_variation1() {
        assertMetamorphicRelationFor(source(-180, -160, -10, 10, 180, 0));
    }

    @Test
    public void NEGATIVE_180_EQUIVALENT_INSIDE_variation1() {
        assertMetamorphicRelationFor(source(160, 180, -10, 10, -180, 0));
    }

    @Test
    public void VERTICAL_MINUS_180_WITH_PLUS_180_POINT_variation1() {
        assertMetamorphicRelationFor(source(-180, -180, -10, 10, 180, 0));
    }

    @Test
    public void VERTICAL_PLUS_180_WITH_MINUS_180_POINT_variation1() {
        assertMetamorphicRelationFor(source(180, 180, -10, 10, -180, 0));
    }

    @Test
    public void DATELINE_EQUIVALENT_JUST_OUTSIDE_WEST_variation1() {
        assertMetamorphicRelationFor(source(-179, -170, -10, 10, 180, 0));
    }

    @Test
    public void DATELINE_EQUIVALENT_JUST_OUTSIDE_EAST_variation1() {
        assertMetamorphicRelationFor(source(170, 179, -10, 10, -180, 0));
    }

    @Test
    public void ROTATION_NONWRAP_TO_NONWRAP_CONTAINS_variation1() {
        assertMetamorphicRelationFor(source(-100, 0, -10, 10, -50, 0));
    }

    @Test
    public void ROTATION_NONWRAP_TO_NONWRAP_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(-100, 0, -10, 10, 20, 0));
    }

    @Test
    public void ROTATION_NONWRAP_TO_WRAP_CONTAINS_variation1() {
        assertMetamorphicRelationFor(source(-100, 50, -10, 10, 0, 0));
    }

    @Test
    public void ROTATION_NONWRAP_TO_WRAP_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(-100, 50, -10, 10, 100, 0));
    }

    @Test
    public void ROTATION_BOTH_ENDPOINTS_WRAP_CONTAINS_variation1() {
        assertMetamorphicRelationFor(source(100, 150, -10, 10, 125, 0));
    }

    @Test
    public void ROTATION_BOTH_ENDPOINTS_WRAP_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(100, 150, -10, 10, 0, 0));
    }

    @Test
    public void ROTATION_WRAP_TO_NONWRAP_CONTAINS_variation1() {
        assertMetamorphicRelationFor(source(100, 20, -10, 10, 150, 0));
    }

    @Test
    public void ROTATION_WRAP_TO_NONWRAP_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(100, 20, -10, 10, 50, 0));
    }

    @Test
    public void ROTATION_WRAP_TO_WRAP_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(-100, -120, -10, 10, 0, 0));
    }

    @Test
    public void ROTATION_WRAP_TO_WRAP_GAP_variation1() {
        assertMetamorphicRelationFor(source(-100, -120, -10, 10, -110, 0));
    }

    @Test
    public void ROTATION_WRAPPED_BOTH_ENDPOINTS_GAP_variation1() {
        assertMetamorphicRelationFor(source(100, 50, -10, 10, 75, 0));
    }

    @Test
    public void ROTATION_WRAPPED_BOTH_ENDPOINTS_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(100, 50, -10, 10, 0, 0));
    }

    @Test
    public void POINT_ROTATES_EXACTLY_TO_MINUS_180_INSIDE_variation1() {
        assertMetamorphicRelationFor(source(20, 40, -10, 10, 30, 0));
    }

    @Test
    public void POINT_ROTATES_EXACTLY_TO_MINUS_180_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 30, 0));
    }

    @Test
    public void MIN_ENDPOINT_ROTATES_TO_MINUS_180_variation1() {
        assertMetamorphicRelationFor(source(30, 60, -10, 10, 30, 0));
    }

    @Test
    public void POINT_RECTANGLE_EXACT_MATCH_variation1() {
        assertMetamorphicRelationFor(source(7, 7, 3, 3, 7, 3));
    }
}
