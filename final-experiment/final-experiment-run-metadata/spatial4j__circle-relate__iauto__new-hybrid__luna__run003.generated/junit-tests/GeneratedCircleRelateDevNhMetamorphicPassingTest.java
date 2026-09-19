import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static SpatialContext context() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(double x, double y, double radius) {
        SpatialContext context = context();
        Point point = new PointImpl(x, y, context);
        return new CircleImpl(point, radius, context);
    }

    private static CircleImpl emptyCircle() {
        SpatialContext context = context();
        Point point = new PointImpl(Double.NaN, Double.NaN, context);
        return new CircleImpl(point, 0.0, context);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY, SpatialContext context) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static Rectangle emptyRectangle(SpatialContext context) {
        return new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, context);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        org.junit.jupiter.api.Assertions.assertSame(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_variation1() {
        CircleImpl source = emptyCircle();
        Rectangle rectangle = rectangle(
                10.0, 20.0, -5.0, 5.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_RECTANGLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 3.0);
        Rectangle rectangle = emptyRectangle(source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENCLOSING_BOX_DISJOINT_ON_X_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                6.0, 8.0, -1.0, 1.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENCLOSING_BOX_DISJOINT_ON_Y_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -1.0, 1.0, 6.0, 8.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENCLOSING_BOX_WITHIN_LARGE_RECTANGLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -6.0, 6.0, -7.0, 7.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENCLOSING_BOX_EQUALS_RECTANGLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -5.0, 5.0, -5.0, 5.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGLE_STRICTLY_CONTAINED_BY_CIRCLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -3.0, 3.0, -3.0, 3.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGLE_CORNER_OUTSIDE_CIRCLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -4.0, 4.0, -4.0, 4.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE_TWO_DISJOINT_FROM_LOWER_LEFT_CORNER_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                4.0, 6.0, 4.0, 6.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE_TWO_INTERSECTS_X_OUTSIDE_Y_INSIDE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                4.0, 6.0, -1.0, 1.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE_TWO_INTERSECTS_X_INSIDE_Y_OUTSIDE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -1.0, 1.0, 4.0, 6.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE_TWO_INTERSECTS_BOTH_AXES_INSIDE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -6.0, 3.0, -1.0, 1.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXTERNAL_CORNER_TANGENCY_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                3.0, 5.0, 4.0, 6.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERNAL_CORNER_TANGENCY_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -3.0, 3.0, -4.0, 4.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EDGE_TANGENCY_ON_RECTANGLE_SIDE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                5.0, 7.0, -5.0, 5.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_WIDTH_RECTANGLE_INSIDE_CIRCLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                1.0, 1.0, -2.0, 2.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_HEIGHT_RECTANGLE_INSIDE_CIRCLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                -2.0, 2.0, 1.0, 1.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_POINT_RECTANGLE_INSIDE_CIRCLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(
                1.0, 1.0, 1.0, 1.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_RECTANGLE_variation1() {
        CircleImpl source = circle(0.0, 0.0, 0.0);
        Rectangle rectangle = rectangle(
                -1.0, 1.0, -2.0, 2.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_FROM_RECTANGLE_POINT_variation1() {
        CircleImpl source = circle(0.0, 0.0, 0.0);
        Rectangle rectangle = rectangle(
                1.0, 1.0, 1.0, 1.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_CIRCLE_CONTAINS_RECTANGLE_variation1() {
        CircleImpl source = circle(10.0, -8.0, 20.0);
        Rectangle rectangle = rectangle(
                4.0, 16.0, -14.0, -2.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGLE_PARTIALLY_OVERLAPS_CIRCLE_BOUNDING_BOX_variation1() {
        CircleImpl source = circle(10.0, -8.0, 4.0);
        Rectangle rectangle = rectangle(
                6.0, 14.0, -10.0, -4.0, source.getContext());

        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
