import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final SpatialContext GEO = new SpatialContext(true);
    private static final double ROTATION_DEGREES = 150.0;

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {
        RectangleImpl rectangle =
                new RectangleImpl(minX, maxX, minY, maxY, GEO);
        Point point = new PointImpl(pointX, pointY, GEO);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rectangle, point);
    }

    private static double wrapLongitude(double longitude) {
        double wrapped = ((longitude + 180.0) % 360.0 + 360.0) % 360.0 - 180.0;
        return wrapped == 0.0 ? 0.0 : wrapped;
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl sourceRectangle = source.receiver();
        Point sourcePoint = source.arg0();

        RectangleImpl rotatedRectangle = new RectangleImpl(
                wrapLongitude(sourceRectangle.getMinX() + ROTATION_DEGREES),
                wrapLongitude(sourceRectangle.getMaxX() + ROTATION_DEGREES),
                sourceRectangle.getMinY(),
                sourceRectangle.getMaxY(),
                GEO);

        Point rotatedPoint = new PointImpl(
                wrapLongitude(sourcePoint.getX() + ROTATION_DEGREES),
                sourcePoint.getY(),
                GEO);

        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rotatedRectangle, rotatedPoint);
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
    public void ORDINARY_STRICT_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, 0));
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
    public void MIN_Y_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, -10));
    }

    @Test
    public void MAX_Y_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, 10));
    }

    @Test
    public void ORDINARY_CORNER_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, -20, -10));
    }

    @Test
    public void ABOVE_MAX_Y_EARLY_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, 10.001));
    }

    @Test
    public void BELOW_MIN_Y_EARLY_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 0, -10.001));
    }

    @Test
    public void EAST_OF_ORDINARY_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, 21, 0));
    }

    @Test
    public void WEST_OF_ORDINARY_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -10, 10, -21, 0));
    }

    @Test
    public void ZERO_WIDTH_POINT_ON_MERIDIAN_variation1() {
        assertMetamorphicRelationFor(source(10, 10, -10, 10, 10, 0));
    }

    @Test
    public void ZERO_WIDTH_POINT_OFF_MERIDIAN_variation1() {
        assertMetamorphicRelationFor(source(10, 10, -10, 10, 10.001, 0));
    }

    @Test
    public void ZERO_HEIGHT_POINT_ON_LATITUDE_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, 5, 5, 0, 5));
    }

    @Test
    public void ZERO_HEIGHT_POINT_OFF_LATITUDE_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, 5, 5, 0, 5.001));
    }

    @Test
    public void NORTH_POLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -90, 90, 0, 90));
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -90, 90, 0, -90));
    }

    @Test
    public void FULL_LATITUDE_LONGITUDE_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(-20, 20, -90, 90, 30, 90));
    }

    @Test
    public void NARROW_WRAP_EAST_LOBE_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, 175, 0));
    }

    @Test
    public void NARROW_WRAP_WEST_LOBE_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, -175, 0));
    }

    @Test
    public void NARROW_WRAP_MIN_X_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, 170, 0));
    }

    @Test
    public void NARROW_WRAP_MAX_X_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, -170, 0));
    }

    @Test
    public void NARROW_WRAP_CENTRAL_GAP_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, 0, 0));
    }

    @Test
    public void NARROW_WRAP_JUST_OUTSIDE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, 169.999, 0));
    }

    @Test
    public void POSITIVE_DATELINE_POINT_IN_WRAP_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, 180, 0));
    }

    @Test
    public void NEGATIVE_DATELINE_POINT_IN_WRAP_variation1() {
        assertMetamorphicRelationFor(source(170, -170, -10, 10, -180, 0));
    }

    @Test
    public void WIDE_WRAP_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(-10, -20, -10, 10, 0, 0));
    }

    @Test
    public void WIDE_WRAP_NARROW_GAP_variation1() {
        assertMetamorphicRelationFor(source(-10, -20, -10, 10, -15, 0));
    }

    @Test
    public void WIDE_WRAP_NEGATIVE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-10, -20, -10, 10, -20, 0));
    }

    @Test
    public void NONWRAP_HALF_WORLD_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(-180, 0, -10, 10, 0, 0));
    }

    @Test
    public void WRAP_HALF_WORLD_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(0, -180, -10, 10, 90, 0));
    }

    @Test
    public void WRAP_HALF_WORLD_DISJOINT_HALF_variation1() {
        assertMetamorphicRelationFor(source(0, -180, -10, 10, -90, 0));
    }

    @Test
    public void ROTATION_NONWRAP_TO_WRAP_CONTAINS_variation1() {
        assertMetamorphicRelationFor(source(-30, 30, -10, 10, 25, 0));
    }

    @Test
    public void ROTATION_NONWRAP_TO_WRAP_DISJOINT_variation1() {
        assertMetamorphicRelationFor(source(-30, 30, -10, 10, 31, 0));
    }

    @Test
    public void ROTATION_WRAP_TO_NONWRAP_variation1() {
        assertMetamorphicRelationFor(source(100, -100, -10, 10, 150, 0));
    }

    @Test
    public void ROTATION_WRAP_TO_WRAP_variation1() {
        assertMetamorphicRelationFor(source(100, 50, -10, 10, 0, 0));
    }

    @Test
    public void ROTATION_NONWRAP_TO_NONWRAP_variation1() {
        assertMetamorphicRelationFor(source(-100, -50, -10, 10, -75, 0));
    }

    @Test
    public void ROTATION_CUT_AT_MIN_ENDPOINT_variation1() {
        assertMetamorphicRelationFor(source(30, 60, -10, 10, 30, 0));
    }

    @Test
    public void ZERO_WIDTH_DATELINE_POSITIVE_POINT_variation1() {
        assertMetamorphicRelationFor(source(180, -180, -10, 10, 180, 0));
    }

    @Test
    public void ZERO_WIDTH_DATELINE_NEGATIVE_POINT_variation1() {
        assertMetamorphicRelationFor(source(180, -180, -10, 10, -180, 0));
    }

    @Test
    public void ZERO_WIDTH_DATELINE_OFF_LINE_variation1() {
        assertMetamorphicRelationFor(source(180, -180, -10, 10, 179, 0));
    }

    @Test
    public void NEAR_GLOBAL_WRAP_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(-179, -180, -10, 10, 0, 0));
    }

    @Test
    public void NEAR_GLOBAL_WRAP_GAP_variation1() {
        assertMetamorphicRelationFor(source(-179, -180, -10, 10, -179.5, 0));
    }

    @Test
    public void NEAR_GLOBAL_NONWRAP_INTERIOR_variation1() {
        assertMetamorphicRelationFor(source(-179, 180, -10, 10, 0, 0));
    }

    @Test
    public void NEAR_GLOBAL_NONWRAP_GAP_variation1() {
        assertMetamorphicRelationFor(source(-179, 180, -10, 10, -179.5, 0));
    }

    @Test
    public void TINY_WRAP_INTERIOR_variation1() {
        assertMetamorphicRelationFor(
                source(180, -179.999, -10, 10, -179.9995, 0));
    }

    @Test
    public void TINY_WRAP_JUST_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(
                source(180, -179.999, -10, 10, 179.999, 0));
    }
}
