import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final SpatialContext GEO_CONTEXT = new SpatialContext(true);

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {
        RectangleImpl rectangle =
                new RectangleImpl(minX, maxX, minY, maxY, GEO_CONTEXT);
        Point point = new PointImpl(pointX, pointY, GEO_CONTEXT);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rectangle, point);
    }

    private static double wrap150(double longitude) {
        double rotated = longitude + 150.0;
        while (rotated > 180.0) {
            rotated -= 360.0;
        }
        while (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl sourceRectangle = source.receiver();
        Point sourcePoint = source.arg0();

        RectangleImpl followUpRectangle = new RectangleImpl(
                wrap150(sourceRectangle.getMinX()),
                wrap150(sourceRectangle.getMaxX()),
                sourceRectangle.getMinY(),
                sourceRectangle.getMaxY(),
                GEO_CONTEXT);

        Point followUpPoint = new PointImpl(
                wrap150(sourcePoint.getX()),
                sourcePoint.getY(),
                GEO_CONTEXT);

        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                followUpRectangle, followUpPoint);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        SpatialRelation sourceOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(
                        generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_ABOVE_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, 11));
    }

    @Test
    public void LATITUDE_BELOW_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -5, 5, 175, -6));
    }

    @Test
    public void LATITUDE_MIN_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-80, -20, -30, 15, -50, -30));
    }

    @Test
    public void LATITUDE_MAX_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(45, 100, -25, 35, 70, 35));
    }

    @Test
    public void LATITUDE_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(-120, -40, -60, 20, -75, -10));
    }

    @Test
    public void ZERO_HEIGHT_ON_LINE_variation1() {
        assertMetamorphicRelationFor(source(160, -150, 25, 25, 175, 25));
    }

    @Test
    public void NORTH_POLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-60, 10, 45, 90, -20, 90));
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(110, -130, -90, -35, 150, -90));
    }

    @Test
    public void ORDINARY_LONGITUDE_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(-15, 55, -20, 20, 12, 0));
    }

    @Test
    public void ORDINARY_MIN_X_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-90, -30, -15, 25, -90, 4));
    }

    @Test
    public void ORDINARY_MAX_X_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(40, 125, -30, 30, 125, 8));
    }

    @Test
    public void ORDINARY_WEST_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(-50, 10, -10, 10, -100, 0));
    }

    @Test
    public void ORDINARY_EAST_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(20, 75, -12, 12, 120, 1));
    }

    @Test
    public void ZERO_WIDTH_NON_DATELINE_HIT_variation1() {
        assertMetamorphicRelationFor(source(-35, -35, -20, 20, -35, 7));
    }

    @Test
    public void ZERO_WIDTH_NON_DATELINE_WEST_MISS_variation1() {
        assertMetamorphicRelationFor(source(15, 15, -5, 5, -10, 0));
    }

    @Test
    public void ZERO_WIDTH_NON_DATELINE_EAST_MISS_variation1() {
        assertMetamorphicRelationFor(source(-25, -25, -8, 8, 40, 2));
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_MISS_variation1() {
        assertMetamorphicRelationFor(source(-70, 30, 12, 12, -20, 12.5));
    }

    @Test
    public void ORDINARY_NARROW_BOX_INTERIOR_variation1() {
        assertMetamorphicRelationFor(
                source(10.0, 10.0005, -2, 2, 10.00025, 0));
    }

    @Test
    public void ORDINARY_NEAR_WORLD_WIDTH_OUTSIDE_GAP_variation1() {
        assertMetamorphicRelationFor(
                source(-179.75, 179.75, -45, 45, 180, 0));
    }

    @Test
    public void WRAPPING_EASTERN_LOBE_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(160, -150, -20, 20, 170, 0));
    }

    @Test
    public void WRAPPING_WESTERN_LOBE_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(160, -150, -20, 20, -170, 0));
    }

    @Test
    public void WRAPPING_EAST_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(160, -150, -10, 10, 160, 1));
    }

    @Test
    public void WRAPPING_WEST_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(160, -150, -10, 10, -150, -1));
    }

    @Test
    public void WRAPPING_GAP_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(130, -120, -30, 30, 0, 3));
    }

    @Test
    public void WRAPPING_TINY_DATELINE_SPAN_EAST_variation1() {
        assertMetamorphicRelationFor(
                source(179.9998, -179.9998, -5, 5, 179.9999, 0));
    }

    @Test
    public void WRAPPING_TINY_DATELINE_SPAN_WEST_variation1() {
        assertMetamorphicRelationFor(
                source(179.9998, -179.9998, -5, 5, -179.9999, 0));
    }

    @Test
    public void WRAPPING_NEAR_WORLD_GAP_variation1() {
        assertMetamorphicRelationFor(source(0.25, -0.25, -40, 40, 0, 7));
    }

    @Test
    public void WRAPPING_JUST_OUTSIDE_EAST_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(
                source(100, -100, -10, 10, 99.9995, 0));
    }

    @Test
    public void WRAPPING_JUST_OUTSIDE_WEST_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(
                source(100, -100, -10, 10, -99.9995, 0));
    }

    @Test
    public void MINUS_180_BOX_ALIASES_PLUS_180_POINT_variation1() {
        assertMetamorphicRelationFor(source(-180, -100, -15, 15, 180, 0));
    }

    @Test
    public void PLUS_180_BOX_ALIASES_MINUS_180_POINT_variation1() {
        assertMetamorphicRelationFor(source(100, 180, -15, 15, -180, 0));
    }

    @Test
    public void VERTICAL_MINUS_180_ALIASES_PLUS_180_variation1() {
        assertMetamorphicRelationFor(source(-180, -180, -25, 25, 180, 5));
    }

    @Test
    public void VERTICAL_PLUS_180_ALIASES_MINUS_180_variation1() {
        assertMetamorphicRelationFor(source(180, 180, -25, 25, -180, -5));
    }

    @Test
    public void WRAPPING_CONTAINS_PLUS_180_variation1() {
        assertMetamorphicRelationFor(source(170, -160, -30, 30, 180, 4));
    }

    @Test
    public void OPPOSITE_DATELINE_ENDPOINT_ZERO_WIDTH_variation1() {
        assertMetamorphicRelationFor(source(180, -180, -12, 12, -180, 0));
    }

    @Test
    public void ROTATION_ORDINARY_TO_WRAPPING_CONTAINS_variation1() {
        assertMetamorphicRelationFor(source(-20, 80, -20, 20, 40, 0));
    }

    @Test
    public void ROTATION_ORDINARY_TO_WRAPPING_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(-20, 80, -20, 20, 100, 0));
    }

    @Test
    public void ROTATION_ORDINARY_BOTH_ENDPOINTS_WRAP_variation1() {
        assertMetamorphicRelationFor(source(40, 100, -10, 10, 70, 0));
    }

    @Test
    public void ROTATION_ORDINARY_NO_ENDPOINT_WRAP_variation1() {
        assertMetamorphicRelationFor(source(-100, 0, -10, 10, -50, 0));
    }

    @Test
    public void ROTATION_WRAPPING_TO_ORDINARY_variation1() {
        assertMetamorphicRelationFor(source(100, -100, -15, 15, -150, 0));
    }

    @Test
    public void ROTATION_WRAPPING_STAYS_WRAPPING_NO_ENDPOINT_WRAP_variation1() {
        assertMetamorphicRelationFor(source(20, -100, -15, 15, 25, 0));
    }

    @Test
    public void ROTATION_WRAPPING_STAYS_WRAPPING_BOTH_ENDPOINTS_WRAP_variation1() {
        assertMetamorphicRelationFor(source(150, 50, -15, 15, 0, 0));
    }

    @Test
    public void ROTATED_POINT_EXACTLY_PLUS_180_variation1() {
        assertMetamorphicRelationFor(source(0, 60, -20, 20, 30, 0));
    }

    @Test
    public void ROTATED_MIN_ENDPOINT_EXACTLY_PLUS_180_variation1() {
        assertMetamorphicRelationFor(source(30, 80, -20, 20, 30, 0));
    }

    @Test
    public void ORDINARY_LOWER_LEFT_CORNER_variation1() {
        assertMetamorphicRelationFor(source(-40, 20, -10, 30, -40, -10));
    }

    @Test
    public void WRAPPING_WESTERN_UPPER_CORNER_variation1() {
        assertMetamorphicRelationFor(source(140, -120, -20, 40, -120, 40));
    }

    @Test
    public void FULL_LATITUDE_SPAN_INTERIOR_LONGITUDE_variation1() {
        assertMetamorphicRelationFor(source(-70, 10, -90, 90, -20, 73));
    }

    @Test
    public void POINT_RECTANGLE_EXACT_MATCH_variation1() {
        assertMetamorphicRelationFor(source(25, 25, 12, 12, 25, 12));
    }

    @Test
    public void LATITUDE_REJECTION_PRECEDES_DATELINE_ALIAS_variation1() {
        assertMetamorphicRelationFor(source(-180, -180, -10, 10, 180, 30));
    }
}
