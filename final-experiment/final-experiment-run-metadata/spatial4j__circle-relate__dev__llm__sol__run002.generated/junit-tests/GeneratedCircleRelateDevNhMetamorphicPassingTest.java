import org.junit.jupiter.api.Test;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final org.locationtech.spatial4j.context.SpatialContext CONTEXT = createContext();

    private static org.locationtech.spatial4j.context.SpatialContext createContext() {
        org.locationtech.spatial4j.context.SpatialContextFactory factory =
                new org.locationtech.spatial4j.context.SpatialContextFactory();
        factory.geo = false;
        return new org.locationtech.spatial4j.context.SpatialContext(factory);
    }

    private static org.locationtech.spatial4j.shape.impl.CircleImpl circle(
            double x, double y, double radius) {
        return new org.locationtech.spatial4j.shape.impl.CircleImpl(
                CONTEXT.makePoint(x, y), radius, CONTEXT);
    }

    private static org.locationtech.spatial4j.shape.impl.CircleImpl emptyCircle() {
        return new org.locationtech.spatial4j.shape.impl.CircleImpl(
                CONTEXT.makePoint(Double.NaN, Double.NaN), Double.NaN, CONTEXT);
    }

    private static org.locationtech.spatial4j.shape.impl.RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                minX, maxX, minY, maxY, CONTEXT);
    }

    private static org.locationtech.spatial4j.shape.impl.RectangleImpl emptyRectangle() {
        return rectangle(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    private static void exercise(
            org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle,
            org.locationtech.spatial4j.shape.Rectangle sourceRectangle) {
        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followUpCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followUpRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_SENTINEL_variation1() {
        exercise(emptyCircle(), rectangle(2.0, 6.0, 3.0, 7.0));
    }

    @Test
    public void EMPTY_RECTANGLE_SENTINEL_variation1() {
        exercise(circle(-17.5, 9.25, 0.0), emptyRectangle());
    }

    @Test
    public void BBOX_DISJOINT_HORIZONTAL_GAP_variation1() {
        exercise(circle(-10.0, 5.0, 4.0), rectangle(-5.0, -2.0, 5.0, 5.0));
    }

    @Test
    public void BBOX_DISJOINT_VERTICAL_GAP_variation1() {
        exercise(circle(4.0, -3.0, 5.0), rectangle(4.0, 4.0, 3.0, 8.0));
    }

    @Test
    public void CIRCLE_STRICTLY_WITHIN_RECTANGLE_variation1() {
        exercise(circle(-17.5, 9.25, 4.0), rectangle(-23.0, -12.0, 4.0, 15.0));
    }

    @Test
    public void CIRCLE_WITHIN_RECTANGLE_SHARING_BOUNDARY_variation1() {
        exercise(circle(-8.0, 4.0, 5.0), rectangle(-13.0, -1.0, -2.0, 10.0));
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1() {
        exercise(circle(3.0, 4.0, 5.0), rectangle(-2.0, 8.0, -1.0, 9.0));
    }

    @Test
    public void LOWER_LEFT_CORNER_GAP_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-9.5, -5.5, 17.25, 21.25));
    }

    @Test
    public void UPPER_LEFT_CORNER_GAP_variation1() {
        exercise(circle(-10.0, 5.0, 10.0), rectangle(-2.0, 2.0, -7.0, -3.0));
    }

    @Test
    public void LOWER_RIGHT_CORNER_GAP_variation1() {
        exercise(circle(6.0, -4.0, 10.0), rectangle(-6.0, -2.0, 4.0, 8.0));
    }

    @Test
    public void UPPER_RIGHT_CORNER_GAP_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-29.5, -25.5, -2.75, 1.25));
    }

    @Test
    public void DIAGONAL_CORNER_TANGENCY_variation1() {
        exercise(circle(-10.0, 5.0, 5.0), rectangle(-7.0, -2.0, 9.0, 14.0));
    }

    @Test
    public void DIAGONAL_CORNER_PENETRATION_variation1() {
        exercise(circle(2.0, 3.0, 5.0), rectangle(5.0, 10.0, 6.0, 11.0));
    }

    @Test
    public void RECTANGLE_RIGHT_OF_CENTER_SPANS_Y_AXIS_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-9.5, -5.5, 7.25, 11.25));
    }

    @Test
    public void RECTANGLE_LEFT_OF_CENTER_SPANS_Y_AXIS_variation1() {
        exercise(circle(-10.0, 5.0, 10.0), rectangle(-22.0, -18.0, 2.0, 8.0));
    }

    @Test
    public void RECTANGLE_ABOVE_CENTER_SPANS_X_AXIS_variation1() {
        exercise(circle(4.0, -3.0, 10.0), rectangle(1.0, 7.0, 5.0, 9.0));
    }

    @Test
    public void RECTANGLE_BELOW_CENTER_SPANS_X_AXIS_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-20.5, -14.5, -2.75, 1.25));
    }

    @Test
    public void RECTANGLE_SPANS_BOTH_CENTER_AXES_variation1() {
        exercise(circle(-10.0, 5.0, 10.0), rectangle(-12.0, 2.0, 3.0, 7.0));
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_ONLY_INTERSECTS_variation1() {
        exercise(circle(2.0, 3.0, 10.0), rectangle(2.0, 11.0, 3.0, 12.0));
    }

    @Test
    public void CIRCLE_STRICTLY_CONTAINS_AREA_RECTANGLE_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-19.5, -14.5, 8.25, 11.25));
    }

    @Test
    public void FARTHEST_CORNER_TANGENT_CONTAINMENT_variation1() {
        exercise(circle(-10.0, 5.0, 5.0), rectangle(-10.0, -7.0, 5.0, 9.0));
    }

    @Test
    public void SYMMETRIC_FARTHEST_TIE_variation1() {
        exercise(circle(3.0, 4.0, 6.0), rectangle(0.0, 6.0, 0.0, 8.0));
    }

    @Test
    public void FARTHEST_MAX_X_SELECTION_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-18.5, -11.5, 8.25, 11.25));
    }

    @Test
    public void FARTHEST_MIN_X_SELECTION_variation1() {
        exercise(circle(-10.0, 5.0, 10.0), rectangle(-17.0, -8.0, 3.0, 7.0));
    }

    @Test
    public void FARTHEST_MAX_Y_SELECTION_variation1() {
        exercise(circle(2.0, 3.0, 10.0), rectangle(0.0, 4.0, 2.0, 10.0));
    }

    @Test
    public void FARTHEST_MIN_Y_SELECTION_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-19.5, -15.5, 2.25, 11.25));
    }

    @Test
    public void CENTER_ON_RECTANGLE_MIN_X_variation1() {
        exercise(circle(-10.0, 5.0, 10.0), rectangle(-10.0, -4.0, 2.0, 8.0));
    }

    @Test
    public void CENTER_ON_RECTANGLE_MAX_X_variation1() {
        exercise(circle(4.0, -3.0, 10.0), rectangle(-2.0, 4.0, -6.0, 0.0));
    }

    @Test
    public void CENTER_ON_RECTANGLE_MIN_Y_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-20.5, -14.5, 9.25, 15.25));
    }

    @Test
    public void CENTER_ON_RECTANGLE_MAX_Y_variation1() {
        exercise(circle(-10.0, 5.0, 10.0), rectangle(-13.0, -7.0, -1.0, 5.0));
    }

    @Test
    public void ZERO_RADIUS_STRICTLY_WITHIN_RECTANGLE_variation1() {
        exercise(circle(2.0, 3.0, 0.0), rectangle(1.0, 3.0, 2.0, 4.0));
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1() {
        exercise(circle(-17.5, 9.25, 0.0), rectangle(-17.5, -17.5, 9.25, 9.25));
    }

    @Test
    public void POSITIVE_CIRCLE_CONTAINS_CENTER_POINT_RECTANGLE_variation1() {
        exercise(circle(-10.0, 5.0, 6.0), rectangle(-10.0, -10.0, 5.0, 5.0));
    }

    @Test
    public void CIRCLE_CONTAINS_BOUNDARY_POINT_RECTANGLE_variation1() {
        exercise(circle(4.0, -3.0, 5.0), rectangle(7.0, 7.0, 1.0, 1.0));
    }

    @Test
    public void POINT_RECTANGLE_INSIDE_BBOX_OUTSIDE_CIRCLE_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-8.5, -8.5, 18.25, 18.25));
    }

    @Test
    public void CIRCLE_CONTAINS_HORIZONTAL_LINE_RECTANGLE_variation1() {
        exercise(circle(-10.0, 5.0, 10.0), rectangle(-16.0, -4.0, 5.0, 5.0));
    }

    @Test
    public void HORIZONTAL_LINE_CROSSES_CIRCLE_variation1() {
        exercise(circle(2.0, 3.0, 5.0), rectangle(-4.0, 8.0, 3.0, 3.0));
    }

    @Test
    public void CIRCLE_CONTAINS_VERTICAL_LINE_RECTANGLE_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-17.5, -17.5, 3.25, 15.25));
    }

    @Test
    public void VERTICAL_LINE_TANGENT_TO_CIRCLE_variation1() {
        exercise(circle(-10.0, 5.0, 5.0), rectangle(-5.0, -5.0, 3.0, 7.0));
    }

    @Test
    public void VERY_SMALL_POSITIVE_RADIUS_variation1() {
        double radius = 0x1.0p-40;
        double halfExtent = 0x1.0p-42;
        exercise(
                circle(0.0, 0.0, radius),
                rectangle(-halfExtent, halfExtent, -halfExtent, halfExtent));
    }

    @Test
    public void SHIFTED_CENTER_EXACTLY_ORIGIN_variation1() {
        exercise(
                circle(-17.5, 9.25, 5.0),
                rectangle(-18.5, -16.5, 8.25, 10.25));
    }

    @Test
    public void SHIFT_CROSSES_BOTH_COORDINATE_AXES_variation1() {
        exercise(circle(-10.0, 5.0, 5.0), rectangle(-7.0, -4.0, 4.0, 6.0));
    }

    @Test
    public void RECTANGLE_ENCLOSES_CIRCLE_WITH_SINGLE_TANGENT_EDGE_variation1() {
        exercise(circle(3.0, 4.0, 5.0), rectangle(-2.0, 9.0, -2.0, 10.0));
    }

    @Test
    public void RECTANGLE_TOUCHES_CIRCLE_AT_AXIS_EXTREME_variation1() {
        exercise(circle(-17.5, 9.25, 10.0), rectangle(-7.5, -4.5, 7.25, 11.25));
    }

    @Test
    public void JUST_OUTSIDE_AXIS_EXTREME_variation1() {
        exercise(circle(-10.0, 5.0, 10.0), rectangle(0.25, 3.0, 3.0, 7.0));
    }

    @Test
    public void JUST_INSIDE_AXIS_EXTREME_variation1() {
        exercise(circle(2.0, 3.0, 10.0), rectangle(11.75, 15.0, 1.0, 5.0));
    }
}
