import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static void verify(String scenario,
                               double minX, double maxX, double minY, double maxY,
                               double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl sourceRectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        SpatialContext context = rectangle.getContext();
        double rotation = 150.0;

        RectangleImpl rotatedRectangle = new RectangleImpl(
                DistanceUtils.normLonDEG(rectangle.getMinX() + rotation),
                DistanceUtils.normLonDEG(rectangle.getMaxX() + rotation),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);

        Point rotatedPoint = context.makePoint(
                DistanceUtils.normLonDEG(point.getX() + rotation),
                point.getY());

        return new Object[] {rotatedRectangle, rotatedPoint};
    }

    @Test
    public void testLATITUDE_ABOVE_MAX_EARLY_DISJOINT_variation1() {
        verify("LATITUDE_ABOVE_MAX_EARLY_DISJOINT",
                -100.0, -50.0, -10.0, 10.0, -75.0, 10.000001);
    }

    @Test
    public void testLATITUDE_BELOW_MIN_EARLY_DISJOINT_variation1() {
        verify("LATITUDE_BELOW_MIN_EARLY_DISJOINT",
                -100.0, -50.0, -10.0, 10.0, -75.0, -10.000001);
    }

    @Test
    public void testNONWRAP_INTERIOR_DIRECT_CONTAINS_variation1() {
        verify("NONWRAP_INTERIOR_DIRECT_CONTAINS",
                -100.0, -50.0, -10.0, 10.0, -75.0, 0.0);
    }

    @Test
    public void testMIN_Y_BOUNDARY_CONTAINS_variation1() {
        verify("MIN_Y_BOUNDARY_CONTAINS",
                -100.0, -50.0, -10.0, 10.0, -75.0, -10.0);
    }

    @Test
    public void testMAX_Y_BOUNDARY_CONTAINS_variation1() {
        verify("MAX_Y_BOUNDARY_CONTAINS",
                -100.0, -50.0, -10.0, 10.0, -75.0, 10.0);
    }

    @Test
    public void testLOWER_LEFT_CORNER_CONTAINS_variation1() {
        verify("LOWER_LEFT_CORNER_CONTAINS",
                -100.0, -50.0, -10.0, 10.0, -100.0, -10.0);
    }

    @Test
    public void testUPPER_RIGHT_CORNER_CONTAINS_variation1() {
        verify("UPPER_RIGHT_CORNER_CONTAINS",
                -100.0, -50.0, -10.0, 10.0, -50.0, 10.0);
    }

    @Test
    public void testNONWRAP_WEST_OF_INTERVAL_SHIFT_DISJOINT_variation1() {
        verify("NONWRAP_WEST_OF_INTERVAL_SHIFT_DISJOINT",
                10.0, 20.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void testNONWRAP_EAST_OF_INTERVAL_SHIFT_DISJOINT_variation1() {
        verify("NONWRAP_EAST_OF_INTERVAL_SHIFT_DISJOINT",
                10.0, 20.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void testEAST_DATELINE_ALIAS_CONTAINS_variation1() {
        verify("EAST_DATELINE_ALIAS_CONTAINS",
                170.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void testWEST_DATELINE_ALIAS_CONTAINS_variation1() {
        verify("WEST_DATELINE_ALIAS_CONTAINS",
                -180.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void testZERO_WIDTH_ORDINARY_LONGITUDE_LINE_CONTAINS_variation1() {
        verify("ZERO_WIDTH_ORDINARY_LONGITUDE_LINE_CONTAINS",
                25.0, 25.0, -10.0, 10.0, 25.0, 0.0);
    }

    @Test
    public void testZERO_WIDTH_ORDINARY_LONGITUDE_LINE_DISJOINT_variation1() {
        verify("ZERO_WIDTH_ORDINARY_LONGITUDE_LINE_DISJOINT",
                25.0, 25.0, -10.0, 10.0, 24.0, 0.0);
    }

    @Test
    public void testZERO_WIDTH_DATELINE_ALIAS_CONTAINS_variation1() {
        verify("ZERO_WIDTH_DATELINE_ALIAS_CONTAINS",
                180.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void testZERO_HEIGHT_HORIZONTAL_LINE_CONTAINS_variation1() {
        verify("ZERO_HEIGHT_HORIZONTAL_LINE_CONTAINS",
                -100.0, -50.0, 5.0, 5.0, -75.0, 5.0);
    }

    @Test
    public void testZERO_HEIGHT_HORIZONTAL_LINE_DISJOINT_variation1() {
        verify("ZERO_HEIGHT_HORIZONTAL_LINE_DISJOINT",
                -100.0, -50.0, 5.0, 5.0, -75.0, 5.000001);
    }

    @Test
    public void testWRAP_DIRECT_EAST_SIDE_CONTAINS_variation1() {
        verify("WRAP_DIRECT_EAST_SIDE_CONTAINS",
                170.0, -170.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    public void testWRAP_SHIFTED_WEST_SIDE_CONTAINS_variation1() {
        verify("WRAP_SHIFTED_WEST_SIDE_CONTAINS",
                170.0, -170.0, -10.0, 10.0, -175.0, 0.0);
    }

    @Test
    public void testWRAP_CENTRAL_GAP_DISJOINT_variation1() {
        verify("WRAP_CENTRAL_GAP_DISJOINT",
                170.0, -170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void testWRAP_DATELINE_POSITIVE_BOUNDARY_CONTAINS_variation1() {
        verify("WRAP_DATELINE_POSITIVE_BOUNDARY_CONTAINS",
                170.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void testWRAP_DATELINE_NEGATIVE_BOUNDARY_CONTAINS_variation1() {
        verify("WRAP_DATELINE_NEGATIVE_BOUNDARY_CONTAINS",
                170.0, -170.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void testWIDE_WRAP_INTERIOR_CONTAINS_variation1() {
        verify("WIDE_WRAP_INTERIOR_CONTAINS",
                170.0, 50.0, -10.0, 10.0, -100.0, 0.0);
    }

    @Test
    public void testWIDE_WRAP_COMPLEMENT_GAP_DISJOINT_variation1() {
        verify("WIDE_WRAP_COMPLEMENT_GAP_DISJOINT",
                170.0, 50.0, -10.0, 10.0, 100.0, 0.0);
    }

    @Test
    public void testNONWRAP_TO_WRAP_FOLLOWUP_CONTAINS_variation1() {
        verify("NONWRAP_TO_WRAP_FOLLOWUP_CONTAINS",
                20.0, 40.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void testNONWRAP_TO_WRAP_FOLLOWUP_DISJOINT_variation1() {
        verify("NONWRAP_TO_WRAP_FOLLOWUP_DISJOINT",
                20.0, 40.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void testWRAP_TO_NONWRAP_FOLLOWUP_CONTAINS_variation1() {
        verify("WRAP_TO_NONWRAP_FOLLOWUP_CONTAINS",
                170.0, -170.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    public void testWRAP_TO_NONWRAP_FOLLOWUP_DISJOINT_variation1() {
        verify("WRAP_TO_NONWRAP_FOLLOWUP_DISJOINT",
                170.0, -170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void testWRAP_TO_WRAP_FOLLOWUP_CONTAINS_variation1() {
        verify("WRAP_TO_WRAP_FOLLOWUP_CONTAINS",
                170.0, 50.0, -10.0, 10.0, -100.0, 0.0);
    }

    @Test
    public void testWRAP_TO_WRAP_FOLLOWUP_DISJOINT_variation1() {
        verify("WRAP_TO_WRAP_FOLLOWUP_DISJOINT",
                170.0, 50.0, -10.0, 10.0, 100.0, 0.0);
    }

    @Test
    public void testNORTH_POLE_BOUNDARY_CONTAINS_variation1() {
        verify("NORTH_POLE_BOUNDARY_CONTAINS",
                -20.0, 20.0, 80.0, 90.0, 0.0, 90.0);
    }

    @Test
    public void testSOUTH_POLE_BOUNDARY_CONTAINS_variation1() {
        verify("SOUTH_POLE_BOUNDARY_CONTAINS",
                -20.0, 20.0, -90.0, -80.0, 0.0, -90.0);
    }
}
