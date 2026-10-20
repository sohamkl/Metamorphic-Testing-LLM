import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private void exercise(double radius, double minXOffset, double maxXOffset,
                          double minYOffset, double maxYOffset) {
        SpatialContext context = new SpatialContext(false);
        double cx = 100.0;
        double cy = 200.0;
        Point center = context.makePoint(cx, cy);
        CircleImpl circle = new CircleImpl(center, radius, context);
        Rectangle rectangle = new RectangleImpl(
                cx + minXOffset, cx + maxXOffset,
                cy + minYOffset, cy + maxYOffset,
                context);

        SpatialRelation sourceOutput = circle.relate(rectangle);

        Object[] followUpValues =
                CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];

        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                           SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Translating the circle and rectangle together must preserve their spatial relation: "
                            + sourceOutput + " versus " + followUpOutput);
        }
    }

    @Test
    public void BBOX_DISJOINT_HORIZONTAL_GAP_rightSide() {
        exercise(5.0, 6.0, 7.0, -1.0, 1.0);
    }

    @Test
    public void BBOX_DISJOINT_HORIZONTAL_GAP_leftSide() {
        exercise(5.0, -7.0, -6.0, -1.0, 1.0);
    }

    @Test
    public void BBOX_DISJOINT_VERTICAL_GAP_upperSide() {
        exercise(5.0, -1.0, 1.0, 6.0, 7.0);
    }

    @Test
    public void BBOX_DISJOINT_VERTICAL_GAP_lowerSide() {
        exercise(5.0, -1.0, 1.0, -7.0, -6.0);
    }

    @Test
    public void BBOX_DISJOINT_DIAGONAL_GAP_northEast() {
        exercise(5.0, 6.0, 7.0, 6.0, 7.0);
    }

    @Test
    public void BBOX_DISJOINT_DIAGONAL_GAP_southWest() {
        exercise(5.0, -7.0, -6.0, -7.0, -6.0);
    }

    @Test
    public void PHASE2_DIAGONAL_DISJOINT_WITH_BBOX_CONTAINS_northEastCorner() {
        exercise(5.0, 4.0, 5.0, 4.0, 5.0);
    }

    @Test
    public void PHASE2_DIAGONAL_DISJOINT_WITH_BBOX_CONTAINS_southWestCorner() {
        exercise(5.0, -5.0, -4.0, -5.0, -4.0);
    }

    @Test
    public void PHASE2_DIAGONAL_DISJOINT_WITH_BBOX_INTERSECTS_northEastOverlap() {
        exercise(5.0, 4.0, 6.0, 4.0, 6.0);
    }

    @Test
    public void PHASE2_DIAGONAL_DISJOINT_WITH_BBOX_INTERSECTS_southWestOverlap() {
        exercise(5.0, -6.0, -4.0, -6.0, -4.0);
    }

    @Test
    public void STRICT_CIRCLE_WITHIN_RECTANGLE_expandedAllSides() {
        exercise(5.0, -6.0, 6.0, -6.0, 6.0);
    }

    @Test
    public void CIRCLE_WITHIN_RECTANGLE_AT_BBOX_BOUNDARY_leftEdgeShared() {
        exercise(5.0, -5.0, 6.0, -6.0, 6.0);
    }

    @Test
    public void CIRCLE_WITHIN_RECTANGLE_AT_BBOX_BOUNDARY_topEdgeShared() {
        exercise(5.0, -6.0, 6.0, -6.0, 5.0);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_ENCLOSING_BOX_equalBounds() {
        exercise(5.0, -5.0, 5.0, -5.0, 5.0);
    }

    @Test
    public void BBOX_INTERSECTS_RECTANGLE_SPANNING_Y_AXIS_rightSide() {
        exercise(5.0, 4.0, 6.0, -1.0, 1.0);
    }

    @Test
    public void BBOX_INTERSECTS_RECTANGLE_SPANNING_Y_AXIS_leftSide() {
        exercise(5.0, -6.0, -4.0, -1.0, 1.0);
    }

    @Test
    public void BBOX_INTERSECTS_RECTANGLE_SPANNING_X_AXIS_upperSide() {
        exercise(5.0, -1.0, 1.0, 4.0, 6.0);
    }

    @Test
    public void BBOX_INTERSECTS_RECTANGLE_SPANNING_X_AXIS_lowerSide() {
        exercise(5.0, -1.0, 1.0, -6.0, -4.0);
    }

    @Test
    public void CIRCLE_CONTAINS_STRICT_INTERIOR_RECTANGLE_centeredSquare() {
        exercise(5.0, -1.0, 1.0, -1.0, 1.0);
    }

    @Test
    public void BBOX_CONTAINS_BUT_CIRCLE_INTERSECTS_RECTANGLE_largeCenteredSquare() {
        exercise(5.0, -4.0, 4.0, -4.0, 4.0);
    }

    @Test
    public void FARTHEST_CORNER_ON_CIRCLE_BOUNDARY_threeFourRectangle() {
        exercise(5.0, -3.0, 3.0, -4.0, 4.0);
    }

    @Test
    public void FARTHEST_CORNER_ON_CIRCLE_BOUNDARY_fourThreeRectangle() {
        exercise(5.0, -4.0, 4.0, -3.0, 3.0);
    }

    @Test
    public void CLOSEST_CORNER_ON_CIRCLE_BOUNDARY_northEastTangent() {
        exercise(5.0, 3.0, 4.0, 4.0, 5.0);
    }

    @Test
    public void CLOSEST_CORNER_ON_CIRCLE_BOUNDARY_southWestTangent() {
        exercise(5.0, -4.0, -3.0, -5.0, -4.0);
    }

    @Test
    public void RECTANGLE_EDGE_TANGENT_TO_CIRCLE_rightExtremum() {
        exercise(5.0, 5.0, 6.0, -1.0, 1.0);
    }

    @Test
    public void RECTANGLE_EDGE_TANGENT_TO_CIRCLE_leftExtremum() {
        exercise(5.0, -6.0, -5.0, -1.0, 1.0);
    }

    @Test
    public void VERTICAL_LINE_INSIDE_CIRCLE_centerLine() {
        exercise(5.0, 0.0, 0.0, -1.0, 1.0);
    }

    @Test
    public void HORIZONTAL_LINE_CROSSING_CIRCLE_diameterExtension() {
        exercise(5.0, -6.0, 6.0, 0.0, 0.0);
    }

    @Test
    public void DEGENERATE_POINT_INSIDE_CIRCLE_northEastInterior() {
        exercise(5.0, 1.0, 1.0, 1.0, 1.0);
    }

    @Test
    public void DEGENERATE_POINT_INSIDE_CIRCLE_southWestInterior() {
        exercise(5.0, -2.0, -2.0, -1.0, -1.0);
    }

    @Test
    public void DEGENERATE_POINT_ON_CIRCLE_BOUNDARY_eastExtremum() {
        exercise(5.0, 5.0, 5.0, 0.0, 0.0);
    }

    @Test
    public void DEGENERATE_POINT_ON_CIRCLE_BOUNDARY_northExtremum() {
        exercise(5.0, 0.0, 0.0, 5.0, 5.0);
    }

    @Test
    public void DEGENERATE_POINT_OUTSIDE_CIRCLE_BBOX_eastPoint() {
        exercise(5.0, 6.0, 6.0, 0.0, 0.0);
    }

    @Test
    public void DEGENERATE_POINT_OUTSIDE_CIRCLE_BBOX_southPoint() {
        exercise(5.0, 0.0, 0.0, -6.0, -6.0);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_RECTANGLE_centeredArea() {
        exercise(0.0, -1.0, 1.0, -1.0, 1.0);
    }

    @Test
    public void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_samePoint() {
        exercise(0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_eastPoint() {
        exercise(0.0, 1.0, 1.0, 0.0, 0.0);
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_northPoint() {
        exercise(0.0, 0.0, 0.0, 1.0, 1.0);
    }
}
