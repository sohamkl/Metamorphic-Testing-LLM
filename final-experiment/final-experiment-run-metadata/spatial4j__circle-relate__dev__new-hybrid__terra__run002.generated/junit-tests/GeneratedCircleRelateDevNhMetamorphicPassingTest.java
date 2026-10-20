import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static SpatialContext cartesianContext() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(SpatialContext context, double radius) {
        return new CircleImpl(context.makePoint(0.0, 0.0), radius, context);
    }

    private static Rectangle rectangle(
            SpatialContext context, double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    @Test
    void BBOX_DISJOINT_HORIZONTAL_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 6.0, 7.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_VERTICAL_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 1.0, 6.0, 7.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PHASE2_CORNER_DISJOINT_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 4.0, 5.0, 4.0, 5.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -6.0, 6.0, -6.0, 6.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_ENCLOSING_BOX_EQUALITY_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -5.0, 5.0, -5.0, 5.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_X_AXIS_SPANNING_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 1.0, 4.0, 6.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_Y_AXIS_SPANNING_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 4.0, 6.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_BOTH_AXES_SPANNING_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -6.0, 6.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_CENTERED_SMALL_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_OFF_CENTER_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 1.0, 2.0, 1.0, 2.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_TIED_FARTHEST_X_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -4.0, 4.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_FARTHEST_CORNER_OUTSIDE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 3.0, 4.0, 3.0, 4.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_CLOSEST_CORNER_TANGENCY_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 3.0, 4.0, 4.0, 4.5);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_ONE_AXIS_OUTSIDE_X_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 1.0, 2.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_ONE_AXIS_OUTSIDE_Y_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 1.0, 1.0, 2.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_EXACT_POINT_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 0.0);
        Rectangle sourceRectangle = rectangle(context, 0.0, 0.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_WITHIN_POSITIVE_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 0.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_DISJOINT_RECTANGLE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 0.0);
        Rectangle sourceRectangle = rectangle(context, 1.0, 2.0, 1.0, 2.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POINT_RECTANGLE_STRICTLY_INSIDE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 1.0, 1.0, 1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POINT_RECTANGLE_ON_CIRCUMFERENCE_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 3.0, 3.0, 4.0, 4.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HORIZONTAL_LINE_CONTAINED_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -3.0, 3.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HORIZONTAL_DIAMETER_LINE_INTERSECTS_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, -6.0, 6.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void VERTICAL_TANGENT_LINE_INTERSECTS_variation1() {
        SpatialContext context = cartesianContext();
        CircleImpl sourceCircle = circle(context, 5.0);
        Rectangle sourceRectangle = rectangle(context, 5.0, 5.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
