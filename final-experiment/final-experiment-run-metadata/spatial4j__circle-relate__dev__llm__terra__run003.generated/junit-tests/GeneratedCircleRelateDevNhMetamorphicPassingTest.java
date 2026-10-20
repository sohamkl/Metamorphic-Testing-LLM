import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private SpatialContext cartesianContext() {
        SpatialContextFactory factory = new SpatialContextFactory();
        factory.geo = false;
        return factory.newSpatialContext();
    }

    private CircleImpl circle(SpatialContext context, double radius) {
        return new CircleImpl(context.makePoint(0.0, 0.0), radius, context);
    }

    @Test
    public void BBOX_DISJOINT_LEFT_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(-20.0, -6.0, -1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_ABOVE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(-1.0, 1.0, 6.0, 20.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_WITHIN_LARGE_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(-6.0, 6.0, -6.0, 6.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_EQUALS_RECTANGLE_IDENTITY_EXCEPTION_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(-5.0, 5.0, -5.0, 5.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_AXIS_SPAN_X_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(-10.0, 10.0, 4.0, 6.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_AXIS_SPAN_Y_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(4.0, 6.0, -10.0, 10.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_DIAGONAL_DISJOINT_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(4.0, 6.0, 4.0, 6.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_DIAGONAL_TANGENCY_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(3.0, 6.0, 4.0, 6.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_FARTHEST_CORNER_OUTSIDE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(3.0, 5.0, 3.0, 5.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_FARTHEST_CORNER_INSIDE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(-2.0, 2.0, -2.0, 2.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_OFF_AXIS_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(3.0, 4.0, -1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_WITHIN_AREA_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 0.0);
        Rectangle rectangle = context.makeRectangle(-1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 0.0);
        Rectangle rectangle = context.makeRectangle(0.0, 0.0, 0.0, 0.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_VERTICAL_LINE_CONTAINMENT_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 0.0);
        Rectangle rectangle = context.makeRectangle(0.0, 0.0, -2.0, 2.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_RADIUS_BOUNDARY_POINT_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(5.0, 5.0, 0.0, 0.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_RADIUS_TANGENT_VERTICAL_LINE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(5.0, 5.0, -1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_RADIUS_CONTAINS_HORIZONTAL_LINE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(-3.0, 3.0, 0.0, 0.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_SENTINEL_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = new CircleImpl(
                context.makePoint(Double.NaN, Double.NaN),
                5.0,
                context);
        Rectangle rectangle = context.makeRectangle(-1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_RECTANGLE_SENTINEL_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl circle = circle(context, 5.0);
        Rectangle rectangle = context.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
