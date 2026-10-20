import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final SpatialContext GEO = new SpatialContext(true);

    private static double rotate(double longitude) {
        double rotated = longitude + 150.0;
        if (rotated > 180.0) {
            rotated -= 360.0;
        } else if (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static Point point(double x, double y) {
        return new PointImpl(x, y, GEO);
    }

    private static RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY, int constructorPath) {
        if (constructorPath == 1) {
            return new RectangleImpl(
                    point(minX, minY), point(maxX, maxY), GEO);
        }
        if (constructorPath == 2) {
            Rectangle source = new RectangleImpl(minX, maxX, minY, maxY, GEO);
            return new RectangleImpl(source, GEO);
        }
        return new RectangleImpl(minX, maxX, minY, maxY, GEO);
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY, int constructorPath) {
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rectangle(minX, maxX, minY, maxY, constructorPath),
                point(pointX, pointY));
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl rectangle = source.receiver();
        Point originalPoint = source.arg0();
        RectangleImpl rotatedRectangle = new RectangleImpl(
                rotate(rectangle.getMinX()),
                rotate(rectangle.getMaxX()),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                GEO);
        Point rotatedPoint = point(
                rotate(originalPoint.getX()), originalPoint.getY());
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
    public void LATITUDE_BELOW_MINIMUM_1() {
        assertMetamorphicRelationFor(source(-40, 40, 10, 30, 0, 0, 0));
    }

    @Test
    public void LATITUDE_ABOVE_MAXIMUM_1() {
        assertMetamorphicRelationFor(source(-40, 40, -30, -10, 0, 0, 1));
    }

    @Test
    public void LATITUDE_ON_MINIMUM_1() {
        assertMetamorphicRelationFor(source(-40, 40, 10, 30, 0, 10, 2));
    }

    @Test
    public void LATITUDE_ON_MAXIMUM_1() {
        assertMetamorphicRelationFor(source(-40, 40, -30, -10, 0, -10, 0));
    }

    @Test
    public void NORMAL_INTERIOR_DIRECT_CONTAINS_1() {
        assertMetamorphicRelationFor(source(-80, 20, -20, 20, -10, 5, 1));
    }

    @Test
    public void NORMAL_EQUAL_MIN_X_1() {
        assertMetamorphicRelationFor(source(-80, 20, -20, 20, -80, 0, 2));
    }

    @Test
    public void NORMAL_EQUAL_MAX_X_1() {
        assertMetamorphicRelationFor(source(-80, 20, -20, 20, 20, 0, 0));
    }

    @Test
    public void NORMAL_LONGITUDE_DISJOINT_LEFT_1() {
        assertMetamorphicRelationFor(source(-20, 40, -20, 20, -100, 0, 1));
    }

    @Test
    public void NORMAL_LONGITUDE_DISJOINT_RIGHT_1() {
        assertMetamorphicRelationFor(source(-40, 20, -20, 20, 100, 0, 2));
    }

    @Test
    public void DATELINE_EASTERN_SEGMENT_1() {
        assertMetamorphicRelationFor(source(170, -170, -20, 20, 175, 0, 0));
    }

    @Test
    public void DATELINE_WESTERN_SEGMENT_SHIFT_1() {
        assertMetamorphicRelationFor(source(170, -170, -20, 20, -175, 0, 1));
    }

    @Test
    public void DATELINE_GAP_DISJOINT_1() {
        assertMetamorphicRelationFor(source(170, -170, -20, 20, 0, 0, 2));
    }

    @Test
    public void DATELINE_EQUAL_MIN_X_1() {
        assertMetamorphicRelationFor(source(170, -170, -20, 20, 170, 0, 0));
    }

    @Test
    public void DATELINE_EQUAL_MAX_X_1() {
        assertMetamorphicRelationFor(source(170, -170, -20, 20, -170, 0, 1));
    }

    @Test
    public void ZERO_WIDTH_ON_LONGITUDE_1() {
        assertMetamorphicRelationFor(source(30, 30, -20, 20, 30, 0, 2));
    }

    @Test
    public void ZERO_WIDTH_OFF_LONGITUDE_1() {
        assertMetamorphicRelationFor(source(30, 30, -20, 20, 31, 0, 0));
    }

    @Test
    public void ZERO_HEIGHT_ON_LATITUDE_1() {
        assertMetamorphicRelationFor(source(-40, 40, 15, 15, 0, 15, 1));
    }

    @Test
    public void ZERO_HEIGHT_OFF_LATITUDE_1() {
        assertMetamorphicRelationFor(source(-40, 40, 15, 15, 0, 15.000001, 2));
    }

    @Test
    public void DATELINE_MINUS_180_ENDPOINT_1() {
        assertMetamorphicRelationFor(source(170, -180, -20, 20, -180, 0, 0));
    }

    @Test
    public void DATELINE_PLUS_180_ENDPOINT_1() {
        assertMetamorphicRelationFor(source(170, 180, -20, 20, 180, 0, 1));
    }

    @Test
    public void NEAR_WORLD_WIDTH_INTERIOR_1() {
        assertMetamorphicRelationFor(source(-179, 179, -10, 10, 0, 0, 2));
    }

    @Test
    public void NEAR_WORLD_WIDTH_EXCLUDED_GAP_1() {
        assertMetamorphicRelationFor(source(-179, 179, -10, 10, 179.5, 0, 0));
    }

    @Test
    public void NORTH_POLE_LATITUDE_BOUNDARY_1() {
        assertMetamorphicRelationFor(source(-40, 40, 60, 90, 0, 90, 1));
    }

    @Test
    public void SOUTH_POLE_LATITUDE_BOUNDARY_1() {
        assertMetamorphicRelationFor(source(-40, 40, -90, -60, 0, -90, 2));
    }

    @Test
    public void POINT_ENDPOINT_CONSTRUCTOR_1() {
        assertMetamorphicRelationFor(source(-60, 60, -15, 25, 10, 5, 1));
    }

    @Test
    public void RECTANGLE_COPY_CONSTRUCTOR_DATELINE_GAP_1() {
        assertMetamorphicRelationFor(source(160, -160, -25, 25, 0, 0, 2));
    }
}
