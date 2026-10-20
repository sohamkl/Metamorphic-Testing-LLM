import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private void exercise(double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl sourceRectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = new PointImpl(pointX, pointY, context);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Rotating the geographic rectangle and point by the same longitude must preserve the relation: "
                            + sourceOutput + " versus " + followUpOutput);
        }
    }

    @Test
    public void ORDINARY_INTERIOR_SHORT_CIRCUIT_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 5.0, 5.0);
    }

    @Test
    public void ORDINARY_MIN_X_BOUNDARY_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 0.0, 5.0);
    }

    @Test
    public void ORDINARY_MAX_X_BOUNDARY_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 10.0, 5.0);
    }

    @Test
    public void ORDINARY_MIN_Y_BOUNDARY_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 5.0, 0.0);
    }

    @Test
    public void ORDINARY_MAX_Y_BOUNDARY_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 5.0, 10.0);
    }

    @Test
    public void ORDINARY_LOWER_LEFT_CORNER_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void ORDINARY_UPPER_RIGHT_CORNER_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 10.0, 10.0);
    }

    @Test
    public void ORDINARY_LONGITUDE_LEFT_DISJOINT_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, -1.0, 5.0);
    }

    @Test
    public void ORDINARY_LONGITUDE_RIGHT_DISJOINT_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 11.0, 5.0);
    }

    @Test
    public void LATITUDE_BELOW_MINIMUM_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 5.0, -1.0);
    }

    @Test
    public void LATITUDE_ABOVE_MAXIMUM_variation1() {
        exercise(0.0, 10.0, 0.0, 10.0, 5.0, 11.0);
    }

    @Test
    public void ZERO_WIDTH_CONTAINED_variation1() {
        exercise(5.0, 5.0, 0.0, 10.0, 5.0, 5.0);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_DISJOINT_variation1() {
        exercise(5.0, 5.0, 0.0, 10.0, 4.0, 5.0);
    }

    @Test
    public void ZERO_HEIGHT_CONTAINED_variation1() {
        exercise(0.0, 10.0, 5.0, 5.0, 5.0, 5.0);
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_DISJOINT_variation1() {
        exercise(0.0, 10.0, 5.0, 5.0, 5.0, 6.0);
    }

    @Test
    public void POINT_RECTANGLE_CONTAINED_variation1() {
        exercise(5.0, 5.0, 2.0, 2.0, 5.0, 2.0);
    }

    @Test
    public void POINT_RECTANGLE_LONGITUDE_DISJOINT_variation1() {
        exercise(5.0, 5.0, 2.0, 2.0, 6.0, 2.0);
    }

    @Test
    public void POINT_RECTANGLE_LATITUDE_DISJOINT_variation1() {
        exercise(5.0, 5.0, 2.0, 2.0, 5.0, 3.0);
    }

    @Test
    public void WIDE_ORDINARY_EAST_ANTIMERIDIAN_DISJOINT_variation1() {
        exercise(-170.0, 170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void WIDE_ORDINARY_WEST_ANTIMERIDIAN_DISJOINT_variation1() {
        exercise(-170.0, 170.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void ORDINARY_ROTATES_TO_WRAPPING_INTERIOR_variation1() {
        exercise(20.0, 40.0, -5.0, 5.0, 30.0, 0.0);
    }

    @Test
    public void ORDINARY_ROTATES_TO_WRAPPING_MIN_X_variation1() {
        exercise(20.0, 40.0, -5.0, 5.0, 20.0, 0.0);
    }

    @Test
    public void ORDINARY_ROTATES_TO_WRAPPING_MAX_X_variation1() {
        exercise(20.0, 40.0, -5.0, 5.0, 40.0, 0.0);
    }

    @Test
    public void ORDINARY_ROTATES_TO_WRAPPING_OUTSIDE_variation1() {
        exercise(20.0, 40.0, -5.0, 5.0, 10.0, 0.0);
    }

    @Test
    public void ORDINARY_ENDPOINT_ROTATES_TO_PLUS_180_variation1() {
        exercise(0.0, 30.0, -5.0, 5.0, 30.0, 0.0);
    }

    @Test
    public void WRAPPING_DIRECT_INTERIOR_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    public void WRAPPING_SHIFTED_WEST_INTERIOR_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -175.0, 0.0);
    }

    @Test
    public void WRAPPING_CENTRAL_GAP_DISJOINT_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void WRAPPING_MIN_X_BOUNDARY_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    public void WRAPPING_MAX_X_BOUNDARY_AFTER_SHIFT_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    public void WRAPPING_PLUS_180_INTERIOR_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void WRAPPING_MINUS_180_INTERIOR_AFTER_SHIFT_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void WRAPPING_LATITUDE_BELOW_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 175.0, -11.0);
    }

    @Test
    public void WRAPPING_LATITUDE_ABOVE_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -175.0, 11.0);
    }

    @Test
    public void NARROW_WRAPPING_DIRECT_INTERIOR_variation1() {
        exercise(179.0, -179.0, -1.0, 1.0, 179.5, 0.0);
    }

    @Test
    public void NARROW_WRAPPING_SHIFTED_INTERIOR_variation1() {
        exercise(179.0, -179.0, -1.0, 1.0, -179.5, 0.0);
    }

    @Test
    public void NARROW_WRAPPING_GAP_DISJOINT_variation1() {
        exercise(179.0, -179.0, -1.0, 1.0, 0.0, 0.0);
    }

    @Test
    public void WIDE_WRAPPING_DIRECT_INTERIOR_variation1() {
        exercise(10.0, -10.0, -5.0, 5.0, 20.0, 0.0);
    }

    @Test
    public void WIDE_WRAPPING_SHIFTED_INTERIOR_variation1() {
        exercise(10.0, -10.0, -5.0, 5.0, -20.0, 0.0);
    }

    @Test
    public void WIDE_WRAPPING_SMALL_GAP_DISJOINT_variation1() {
        exercise(10.0, -10.0, -5.0, 5.0, 0.0, 0.0);
    }

    @Test
    public void WIDE_ORDINARY_ROTATES_TO_WRAPPING_INTERIOR_variation1() {
        exercise(-170.0, 170.0, -5.0, 5.0, 0.0, 0.0);
    }

    @Test
    public void WIDE_ORDINARY_ROTATES_TO_WRAPPING_ANTIMERIDIAN_GAP_variation1() {
        exercise(-170.0, 170.0, -5.0, 5.0, 180.0, 0.0);
    }

    @Test
    public void WIDE_ORDINARY_ROTATES_TO_WRAPPING_MAX_BOUNDARY_variation1() {
        exercise(-170.0, 170.0, -5.0, 5.0, 170.0, 0.0);
    }

    @Test
    public void PLUS_180_ZERO_WIDTH_CONTAINS_MINUS_180_variation1() {
        exercise(180.0, 180.0, -5.0, 5.0, -180.0, 0.0);
    }

    @Test
    public void MINUS_180_ZERO_WIDTH_CONTAINS_PLUS_180_variation1() {
        exercise(-180.0, -180.0, -5.0, 5.0, 180.0, 0.0);
    }

    @Test
    public void PLUS_180_ZERO_WIDTH_INTERIOR_LATITUDE_variation1() {
        exercise(180.0, 180.0, -5.0, 5.0, 180.0, 1.0);
    }

    @Test
    public void MINUS_180_ZERO_WIDTH_INTERIOR_LATITUDE_variation1() {
        exercise(-180.0, -180.0, -5.0, 5.0, -180.0, -1.0);
    }

    @Test
    public void RAW_MINUS_360_DEGENERATE_CONTAINS_PLUS_180_variation1() {
        exercise(180.0, -180.0, -5.0, 5.0, 180.0, 0.0);
    }

    @Test
    public void RAW_MINUS_360_DEGENERATE_CONTAINS_MINUS_180_variation1() {
        exercise(180.0, -180.0, -5.0, 5.0, -180.0, 0.0);
    }

    @Test
    public void RAW_MINUS_360_DEGENERATE_CENTRAL_DISJOINT_variation1() {
        exercise(180.0, -180.0, -5.0, 5.0, 0.0, 0.0);
    }
}
