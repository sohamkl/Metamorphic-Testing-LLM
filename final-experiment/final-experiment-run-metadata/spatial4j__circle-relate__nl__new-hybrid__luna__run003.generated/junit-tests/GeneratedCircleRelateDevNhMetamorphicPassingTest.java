import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final class Source {
        final SpatialContext context;
        final CircleImpl circle;
        final Rectangle rectangle;

        Source(SpatialContext context, CircleImpl circle, Rectangle rectangle) {
            this.context = context;
            this.circle = circle;
            this.rectangle = rectangle;
        }
    }

    private Source source(double centerX, double centerY, double radius,
                          double minX, double maxX,
                          double minY, double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(centerX, centerY, context), radius, context);
        Rectangle rectangle = new RectangleImpl(
                centerX + minX, centerX + maxX,
                centerY + minY, centerY + maxY, context);
        return new Source(context, circle, rectangle);
    }

    private Source emptyCircleSource() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(Double.NaN, Double.NaN, context), 0, context);
        Rectangle rectangle = new RectangleImpl(
                10, 12, -1, 1, context);
        return new Source(context, circle, rectangle);
    }

    private Source emptyRectangleSource() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0, 0, context), 5, context);
        Rectangle rectangle = context.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);
        return new Source(context, circle, rectangle);
    }

    private void assertMetamorphicRelationFor(Source source, double dx, double dy) {
        SpatialRelation sourceOutput = source.circle.relate(source.rectangle);

        CircleImpl followUpCircle = new CircleImpl(
                new PointImpl(
                        source.circle.getCenter().getX() + dx,
                        source.circle.getCenter().getY() + dy,
                        source.context),
                source.circle.getRadius(),
                source.context);

        Rectangle followUpRectangle = new RectangleImpl(
                source.rectangle.getMinX() + dx,
                source.rectangle.getMaxX() + dx,
                source.rectangle.getMinY() + dy,
                source.rectangle.getMaxY() + dy,
                source.context);

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_CIRCLE_SENTINEL_variation1() {
        Source source = emptyCircleSource();
        assertEquals(SpatialRelation.DISJOINT,
                source.circle.relate(source.rectangle));
    }

    @Test
    void EMPTY_RECTANGLE_SENTINEL_variation1() {
        Source source = emptyRectangleSource();
        assertEquals(SpatialRelation.DISJOINT,
                source.circle.relate(source.rectangle));
    }

    @Test
    void BBOX_DISJOINT_HORIZONTAL_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 10, 12, -1, 1), 100, -200);
    }

    @Test
    void BBOX_DISJOINT_HORIZONTAL_variation2() {
        assertMetamorphicRelationFor(
                source(20, -15, 5, 10, 12, -1, 1), -250, 125);
    }

    @Test
    void BBOX_DISJOINT_VERTICAL_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -1, 1, 10, 12), -37, 61);
    }

    @Test
    void BBOX_DISJOINT_VERTICAL_variation2() {
        assertMetamorphicRelationFor(
                source(-20, 15, 5, -1, 1, 10, 12), 13, -29);
    }

    @Test
    void CIRCLE_WITHIN_LARGER_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -6, 6, -7, 7), 1000, 1000);
    }

    @Test
    void CIRCLE_WITHIN_LARGER_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(
                source(17, -23, 5, -6, 6, -7, 7), -37, 61);
    }

    @Test
    void EXACT_ENCLOSING_BOX_IDENTITY_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -5, 5, -5, 5), -250, 125);
    }

    @Test
    void EXACT_ENCLOSING_BOX_IDENTITY_variation2() {
        assertMetamorphicRelationFor(
                source(21, -14, 5, -5, 5, -5, 5), 13, -29);
    }

    @Test
    void RECTANGLE_STRICTLY_CONTAINED_BY_CIRCLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -2, 2, -2, 2), 13, -29);
    }

    @Test
    void RECTANGLE_STRICTLY_CONTAINED_BY_CIRCLE_variation2() {
        assertMetamorphicRelationFor(
                source(-18, 27, 5, -2, 2, -2, 2), 100, -200);
    }

    @Test
    void BBOX_CONTAINS_BUT_CORNER_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -4, 4, -1, 1), 100, -200);
    }

    @Test
    void BBOX_CONTAINS_BUT_CORNER_OUTSIDE_variation2() {
        assertMetamorphicRelationFor(
                source(24, -19, 5, -4, 4, -1, 1), 1000, 1000);
    }

    @Test
    void BBOX_INTERSECTION_ACTUAL_DISJOINT_CORNER_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 4, 6, 4, 6), 13, -29);
    }

    @Test
    void BBOX_INTERSECTION_ACTUAL_DISJOINT_CORNER_variation2() {
        assertMetamorphicRelationFor(
                source(-22, 18, 5, 4, 6, 4, 6), -37, 61);
    }

    @Test
    void BBOX_INTERSECTION_ACTUAL_INTERSECTION_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 4, 6, -1, 1), 1000, 1000);
    }

    @Test
    void BBOX_INTERSECTION_ACTUAL_INTERSECTION_variation2() {
        assertMetamorphicRelationFor(
                source(16, -12, 5, 4, 6, -1, 1), -250, 125);
    }

    @Test
    void AXIS_RIGHT_OF_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -6, -4, -1, 1), 13, -29);
    }

    @Test
    void AXIS_RIGHT_OF_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(
                source(-14, 22, 5, -6, -4, -1, 1), 100, -200);
    }

    @Test
    void AXIS_BELOW_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -1, 1, 4, 6), -250, 125);
    }

    @Test
    void AXIS_BELOW_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(
                source(19, -16, 5, -1, 1, 4, 6), -37, 61);
    }

    @Test
    void AXIS_ABOVE_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -1, 1, -6, -4), 100, -200);
    }

    @Test
    void AXIS_ABOVE_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(
                source(-11, 26, 5, -1, 1, -6, -4), 1000, 1000);
    }

    @Test
    void AXIS_ON_RECTANGLE_BOUNDARIES_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 0, 2, 0, 2), -37, 61);
    }

    @Test
    void AXIS_ON_RECTANGLE_BOUNDARIES_variation2() {
        assertMetamorphicRelationFor(
                source(28, -21, 5, 0, 2, 0, 2), 13, -29);
    }

    @Test
    void TANGENT_CLOSEST_POINT_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 5, 6, -1, 1), 1000, 1000);
    }

    @Test
    void TANGENT_CLOSEST_POINT_variation2() {
        assertMetamorphicRelationFor(
                source(-25, 17, 5, 5, 6, -1, 1), -250, 125);
    }

    @Test
    void FARTHEST_CORNER_ON_CIRCLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 0, 3, 0, 4), 13, -29);
    }

    @Test
    void FARTHEST_CORNER_ON_CIRCLE_variation2() {
        assertMetamorphicRelationFor(
                source(23, -18, 5, 0, 3, 0, 4), 100, -200);
    }

    @Test
    void ZERO_RADIUS_POINT_RECTANGLE_INSIDE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 0, -1, 1, -1, 1), -250, 125);
    }

    @Test
    void ZERO_RADIUS_POINT_RECTANGLE_INSIDE_variation2() {
        assertMetamorphicRelationFor(
                source(-17, 24, 0, -1, 1, -1, 1), 13, -29);
    }

    @Test
    void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 0, 0, 0, 0, 0), 100, -200);
    }

    @Test
    void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(
                source(31, -27, 0, 0, 0, 0, 0), -37, 61);
    }

    @Test
    void CIRCLE_CONTAINS_ZERO_WIDTH_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 1, 1, -2, 2), -37, 61);
    }

    @Test
    void CIRCLE_CONTAINS_ZERO_WIDTH_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(
                source(-19, 16, 5, 1, 1, -2, 2), 100, -200);
    }

    @Test
    void CIRCLE_CONTAINS_ZERO_HEIGHT_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, -2, 2, 1, 1), 1000, 1000);
    }

    @Test
    void CIRCLE_CONTAINS_ZERO_HEIGHT_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(
                source(14, -26, 5, -2, 2, 1, 1), -250, 125);
    }

    @Test
    void ZERO_AREA_RECTANGLE_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 10, 10, 10, 10), -250, 125);
    }

    @Test
    void ZERO_AREA_RECTANGLE_OUTSIDE_variation2() {
        assertMetamorphicRelationFor(
                source(-13, 29, 5, 10, 10, 10, 10), 13, -29);
    }
}
