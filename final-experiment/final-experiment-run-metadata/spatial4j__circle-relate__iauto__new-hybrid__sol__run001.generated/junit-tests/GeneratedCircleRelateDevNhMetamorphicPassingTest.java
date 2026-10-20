import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private void exercise(
            double cx, double cy, double radius,
            double minX, double maxX, double minY, double maxY) {

        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle =
                new CircleImpl(context.makePoint(cx, cy), radius, context);
        Rectangle sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);

        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "A common translation changed the spatial relation from "
                            + sourceOutput + " to " + followUpOutput);
        }
    }

    @Test
    public void BBOX_DISJOINT_RIGHT_variation1() {
        exercise(0, 0, 10, 11, 14, -2, 3);
    }

    @Test
    public void CIRCLE_STRICTLY_WITHIN_RECTANGLE_variation1() {
        exercise(1, 2, 6, -7, 9, -6, 10);
    }

    @Test
    public void CIRCLE_WITHIN_RECTANGLE_SHARED_EDGE_variation1() {
        exercise(0, 0, 10, -10, 13, -12, 14);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BBOX_variation1() {
        exercise(0, 0, 10, -10, 10, -10, 10);
    }

    @Test
    public void NORTHEAST_CORNER_GAP_variation1() {
        exercise(0, 0, 10, 8, 9, 8, 9);
    }

    @Test
    public void NORTHWEST_CORNER_GAP_variation1() {
        exercise(0, 0, 10, -9, -8, 8, 9);
    }

    @Test
    public void SOUTHEAST_CORNER_GAP_variation1() {
        exercise(0, 0, 10, 8, 9, -9, -8);
    }

    @Test
    public void SOUTHWEST_CORNER_GAP_variation1() {
        exercise(0, 0, 10, -9, -8, -9, -8);
    }

    @Test
    public void NORTHEAST_CORNER_OVERLAP_variation1() {
        exercise(0, 0, 10, 6, 9, 6, 9);
    }

    @Test
    public void NORTHWEST_CORNER_OVERLAP_variation1() {
        exercise(0, 0, 10, -9, -6, 6, 9);
    }

    @Test
    public void SOUTHEAST_CORNER_OVERLAP_variation1() {
        exercise(0, 0, 10, 6, 9, -9, -6);
    }

    @Test
    public void SOUTHWEST_CORNER_OVERLAP_variation1() {
        exercise(0, 0, 10, -9, -6, -9, -6);
    }

    @Test
    public void RIGHT_SIDE_INTERSECTION_variation1() {
        exercise(0, 0, 10, 9, 12, -1, 1);
    }

    @Test
    public void LEFT_SIDE_INTERSECTION_variation1() {
        exercise(0, 0, 10, -12, -9, -1, 1);
    }

    @Test
    public void TOP_SIDE_INTERSECTION_variation1() {
        exercise(0, 0, 10, -1, 1, 9, 12);
    }

    @Test
    public void BOTTOM_SIDE_INTERSECTION_variation1() {
        exercise(0, 0, 10, -1, 1, -12, -9);
    }

    @Test
    public void RIGHT_AXIS_TANGENT_AREA_variation1() {
        exercise(0, 0, 10, 10, 13, -2, 2);
    }

    @Test
    public void TOP_AXIS_TANGENT_AREA_variation1() {
        exercise(0, 0, 10, -2, 2, 10, 13);
    }

    @Test
    public void EXTERNAL_CORNER_TANGENCY_variation1() {
        exercise(0, 0, 10, 6, 12, 8, 13);
    }

    @Test
    public void RIGHT_TANGENT_POINT_RECTANGLE_variation1() {
        exercise(0, 0, 10, 10, 10, 0, 0);
    }

    @Test
    public void THREE_FOUR_FIVE_TANGENT_POINT_variation1() {
        exercise(0, 0, 5, 3, 3, 4, 4);
    }

    @Test
    public void CENTER_POINT_RECTANGLE_variation1() {
        exercise(2, -3, 8, 2, 2, -3, -3);
    }

    @Test
    public void STRICT_INTERIOR_POINT_RECTANGLE_variation1() {
        exercise(0, 0, 10, 3, 3, 4, 4);
    }

    @Test
    public void CENTERED_INTERIOR_RECTANGLE_variation1() {
        exercise(0, 0, 10, -2, 2, -2, 2);
    }

    @Test
    public void NORTHEAST_INTERIOR_RECTANGLE_variation1() {
        exercise(0, 0, 10, 2, 4, 1, 3);
    }

    @Test
    public void NORTHWEST_INTERIOR_RECTANGLE_variation1() {
        exercise(0, 0, 10, -4, -2, 1, 3);
    }

    @Test
    public void SOUTHEAST_INTERIOR_RECTANGLE_variation1() {
        exercise(0, 0, 10, 2, 4, -3, -1);
    }

    @Test
    public void SOUTHWEST_INTERIOR_RECTANGLE_variation1() {
        exercise(0, 0, 10, -4, -2, -3, -1);
    }

    @Test
    public void RECTANGLE_STRADDLES_X_AXIS_CONTAINED_variation1() {
        exercise(0, 0, 10, -2, 3, 6, 8);
    }

    @Test
    public void RECTANGLE_STRADDLES_Y_AXIS_CONTAINED_variation1() {
        exercise(0, 0, 10, 6, 8, -2, 3);
    }

    @Test
    public void ASYMMETRIC_RECTANGLE_STRADDLES_BOTH_AXES_variation1() {
        exercise(0, 0, 10, -2, 4, -3, 1);
    }

    @Test
    public void X_FARTHEST_EDGE_TIE_variation1() {
        exercise(0, 0, 10, -3, 3, 1, 2);
    }

    @Test
    public void Y_FARTHEST_EDGE_TIE_variation1() {
        exercise(0, 0, 10, 1, 2, -3, 3);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_DOES_NOT_variation1() {
        exercise(0, 0, 10, 8, 9, 4, 5);
    }

    @Test
    public void BBOX_INTERSECTS_WITH_CLOSE_CORNER_INSIDE_variation1() {
        exercise(0, 0, 10, 8, 12, 4, 5);
    }

    @Test
    public void VERTICAL_DIAMETER_SEGMENT_variation1() {
        exercise(0, 0, 10, 0, 0, -10, 10);
    }

    @Test
    public void HORIZONTAL_INTERIOR_CHORD_variation1() {
        exercise(0, 0, 10, -7, 6, 0, 0);
    }

    @Test
    public void HORIZONTAL_DIAMETER_BOUNDARY_ENDPOINTS_variation1() {
        exercise(0, 0, 10, -10, 10, 0, 0);
    }

    @Test
    public void LINE_CROSSES_AND_EXTENDS_OUTSIDE_variation1() {
        exercise(0, 0, 10, -13, 14, 0, 0);
    }

    @Test
    public void ZERO_RADIUS_WITHIN_AREA_RECTANGLE_variation1() {
        exercise(0, 0, 0, -3, 4, -2, 5);
    }

    @Test
    public void ZERO_RADIUS_EXACT_POINT_RECTANGLE_variation1() {
        exercise(3, 4, 0, 3, 3, 4, 4);
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_POINT_variation1() {
        exercise(0, 0, 0, 2, 2, -3, -3);
    }

    @Test
    public void ZERO_RADIUS_ON_RECTANGLE_EDGE_variation1() {
        exercise(0, 0, 0, 0, 5, -3, 4);
    }

    @Test
    public void CIRCLE_BBOX_WITHIN_RECTANGLE_AT_TWO_EDGES_variation1() {
        exercise(0, 0, 10, -10, 14, -10, 13);
    }

    @Test
    public void RECTANGLE_INSCRIBED_CORNERS_ON_CIRCLE_variation1() {
        exercise(0, 0, 5, -3, 3, -4, 4);
    }

    @Test
    public void RECTANGLE_JUST_BEYOND_INSCRIBED_CORNERS_variation1() {
        exercise(0, 0, 10, -8, 8, -7, 7);
    }

    @Test
    public void RECTANGLE_SHARES_FULL_RIGHT_BBOX_EDGE_variation1() {
        exercise(0, 0, 10, 10, 10, -10, 10);
    }

    @Test
    public void RECTANGLE_TOUCHES_BBOX_BUT_MISSES_CIRCLE_variation1() {
        exercise(0, 0, 10, 10, 13, 10, 14);
    }

    @Test
    public void RECTANGLE_TOUCHES_BBOX_AND_CIRCLE_AT_AXIS_variation1() {
        exercise(0, 0, 10, 0, 0, 10, 10);
    }

    @Test
    public void THIN_RECTANGLE_CROSSES_BOUNDARY_variation1() {
        exercise(0, 0, 10, 9, 11, -0.5, 0.5);
    }
}
