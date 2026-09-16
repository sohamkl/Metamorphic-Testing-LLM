import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final SpatialContext GEO = new SpatialContext(true);

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        RectangleImpl rectangle = new RectangleImpl(minX, maxX, minY, maxY, GEO);
        Point point = new PointImpl(pointX, pointY, GEO);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangle, point);
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl original = source.receiver();
        Point originalPoint = source.arg0();

        RectangleImpl rotatedRectangle = new RectangleImpl(
                rotateLongitude(original.getMinX()),
                rotateLongitude(original.getMaxX()),
                original.getMinY(),
                original.getMaxY(),
                GEO);
        Point rotatedPoint = new PointImpl(
                rotateLongitude(originalPoint.getX()),
                originalPoint.getY(),
                GEO);

        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rotatedRectangle, rotatedPoint);
    }

    private static double rotateLongitude(double longitude) {
        double rotated = (longitude + 150.0) % 360.0;
        if (rotated >= 180.0) {
            rotated -= 360.0;
        } else if (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        SpatialRelation sourceOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    void testINTERIOR_ORDINARY_BECOMES_WRAPPING_rotation150() {
        assertMetamorphicRelationFor(source(-100.0, 100.0, -20.0, 20.0, 0.0, 0.0));
    }

    @Test
    void testINTERIOR_ORDINARY_STAYS_ORDINARY_rotation150() {
        assertMetamorphicRelationFor(source(-20.0, 20.0, -10.0, 10.0, 0.0, 0.0));
    }

    @Test
    void testPOINT_ON_MIN_X_EDGE_rotation150() {
        assertMetamorphicRelationFor(source(-40.0, 30.0, -10.0, 10.0, -40.0, 0.0));
    }

    @Test
    void testPOINT_ON_MAX_X_EDGE_rotation150() {
        assertMetamorphicRelationFor(source(-40.0, 30.0, -10.0, 10.0, 30.0, 0.0));
    }

    @Test
    void testPOINT_ON_MIN_Y_EDGE_rotation150() {
        assertMetamorphicRelationFor(source(-40.0, 30.0, -10.0, 10.0, 0.0, -10.0));
    }

    @Test
    void testPOINT_ON_MAX_Y_EDGE_rotation150() {
        assertMetamorphicRelationFor(source(-40.0, 30.0, -10.0, 10.0, 0.0, 10.0));
    }

    @Test
    void testPOINT_ABOVE_MAX_Y_rotation150() {
        assertMetamorphicRelationFor(source(-40.0, 30.0, -10.0, 10.0, 0.0, 10.1));
    }

    @Test
    void testPOINT_BELOW_MIN_Y_rotation150() {
        assertMetamorphicRelationFor(source(-40.0, 30.0, -10.0, 10.0, 0.0, -10.1));
    }

    @Test
    void testORDINARY_LEFT_OF_INTERVAL_DISJOINT_rotation150() {
        assertMetamorphicRelationFor(source(-100.0, -50.0, -10.0, 10.0, -120.0, 0.0));
    }

    @Test
    void testORDINARY_RIGHT_OF_INTERVAL_DISJOINT_rotation150() {
        assertMetamorphicRelationFor(source(50.0, 100.0, -10.0, 10.0, 120.0, 0.0));
    }

    @Test
    void testWRAPPING_DIRECT_SIDE_CONTAINS_rotation150() {
        assertMetamorphicRelationFor(source(150.0, -150.0, -20.0, 20.0, 170.0, 0.0));
    }

    @Test
    void testWRAPPING_SHIFTED_SIDE_CONTAINS_rotation150() {
        assertMetamorphicRelationFor(source(150.0, -150.0, -20.0, 20.0, -170.0, 0.0));
    }

    @Test
    void testWRAPPING_SHIFTED_SIDE_DISJOINT_rotation150() {
        assertMetamorphicRelationFor(source(150.0, -150.0, -20.0, 20.0, -140.0, 0.0));
    }

    @Test
    void testWRAPPING_MIN_X_BOUNDARY_rotation150() {
        assertMetamorphicRelationFor(source(150.0, -150.0, -20.0, 20.0, 150.0, 0.0));
    }

    @Test
    void testWRAPPING_MAX_X_BOUNDARY_rotation150() {
        assertMetamorphicRelationFor(source(150.0, -150.0, -20.0, 20.0, -150.0, 0.0));
    }

    @Test
    void testEAST_DATELINE_ALIAS_CONTAINS_rotation150() {
        assertMetamorphicRelationFor(source(-180.0, -170.0, -10.0, 10.0, 180.0, 0.0));
    }

    @Test
    void testWEST_DATELINE_ALIAS_CONTAINS_rotation150() {
        assertMetamorphicRelationFor(source(170.0, 180.0, -10.0, 10.0, -180.0, 0.0));
    }

    @Test
    void testNEGATIVE_DATELINE_DIRECT_BOUNDARY_rotation150() {
        assertMetamorphicRelationFor(source(-180.0, -170.0, -10.0, 10.0, -180.0, 0.0));
    }

    @Test
    void testPOSITIVE_DATELINE_DIRECT_BOUNDARY_rotation150() {
        assertMetamorphicRelationFor(source(170.0, 180.0, -10.0, 10.0, 180.0, 0.0));
    }

    @Test
    void testZERO_WIDTH_VERTICAL_LINE_CONTAINS_rotation150() {
        assertMetamorphicRelationFor(source(20.0, 20.0, -10.0, 10.0, 20.0, 0.0));
    }

    @Test
    void testZERO_WIDTH_VERTICAL_LINE_DISJOINT_rotation150() {
        assertMetamorphicRelationFor(source(20.0, 20.0, -10.0, 10.0, 20.1, 0.0));
    }

    @Test
    void testZERO_HEIGHT_HORIZONTAL_LINE_CONTAINS_rotation150() {
        assertMetamorphicRelationFor(source(-20.0, 20.0, 5.0, 5.0, 0.0, 5.0));
    }

    @Test
    void testZERO_HEIGHT_HORIZONTAL_LINE_DISJOINT_rotation150() {
        assertMetamorphicRelationFor(source(-20.0, 20.0, 5.0, 5.0, 0.0, 5.1));
    }
}
