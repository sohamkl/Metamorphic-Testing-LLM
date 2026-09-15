import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private void exercise(double minX, double maxX, double minY, double maxY,
                          double pointX, double pointY) {
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
    void ORDINARY_INTERIOR_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_LONGITUDE_BOUNDARY_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, -20.0, 0.0);
    }

    @Test
    void ORDINARY_MAX_LONGITUDE_BOUNDARY_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, 20.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_LATITUDE_BOUNDARY_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, 0.0, -10.0);
    }

    @Test
    void ORDINARY_MAX_LATITUDE_BOUNDARY_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, 0.0, 10.0);
    }

    @Test
    void LATITUDE_BELOW_MINIMUM_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, 0.0, -10.1);
    }

    @Test
    void LATITUDE_ABOVE_MAXIMUM_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, 0.0, 10.1);
    }

    @Test
    void ORDINARY_WEST_LONGITUDE_DISJOINT_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, -21.0, 0.0);
    }

    @Test
    void ORDINARY_EAST_LONGITUDE_DISJOINT_variation1() {
        exercise(-20.0, 20.0, -10.0, 10.0, 21.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_variation1() {
        exercise(10.0, 10.0, -5.0, 5.0, 10.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_LATITUDE_variation1() {
        exercise(-5.0, 5.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Test
    void DATELINE_EQUIVALENT_POSITIVE_180_POINT_variation1() {
        exercise(-180.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void DATELINE_EQUIVALENT_NEGATIVE_180_POINT_variation1() {
        exercise(170.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    void WRAPPING_DIRECT_EAST_SEGMENT_INTERIOR_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    void WRAPPING_SHIFTED_WEST_SEGMENT_INTERIOR_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -175.0, 0.0);
    }

    @Test
    void WRAPPING_SHIFTED_MAX_BOUNDARY_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    void WRAPPING_LONGITUDE_GAP_DISJOINT_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void WRAPPING_JUST_OUTSIDE_WEST_SEGMENT_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -169.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_WRAPPING_FOLLOW_UP_variation1() {
        exercise(20.0, 40.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void ROTATION_REMOVES_WRAPPING_FOLLOW_UP_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -175.0, -0.0);
    }

    @Test
    void NORTH_POLE_LATITUDE_BOUNDARY_variation1() {
        exercise(-30.0, 30.0, 89.0, 90.0, 0.0, 90.0);
    }
}
