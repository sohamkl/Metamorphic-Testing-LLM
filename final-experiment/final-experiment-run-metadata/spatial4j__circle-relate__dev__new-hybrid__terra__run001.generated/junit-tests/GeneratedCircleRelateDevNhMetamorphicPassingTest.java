import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);

    private CircleImpl circle(double x, double y, double radius) {
        return new CircleImpl(new PointImpl(x, y, CONTEXT), radius, CONTEXT);
    }

    private Rectangle rectangle(double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    @Test
    public void BBOX_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(20.0, 25.0, 20.0, 25.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_WITHIN_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(2.0, 3.0, 0.0);
        Rectangle sourceRectangle = rectangle(0.0, 10.0, 0.0, 10.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_EQUALS_RECTANGLE_IDENTITY_EXCEPTION_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(-10.0, 10.0, -10.0, 10.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_FULLY_INSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(-2.0, 2.0, -2.0, 2.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_FARTHEST_CORNER_OUTSIDE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(-9.0, 9.0, -9.0, 9.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_CORNER_RECTANGLE_OUTSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(8.0, 9.0, 8.0, 9.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_INTERSECTS_CORNER_RECTANGLE_OUTSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(9.0, 12.0, 9.0, 12.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_INTERSECTS_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(3.0, 6.0, 4.0, 7.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_INTERSECTS_RECTANGLE_SPANS_X_AXIS_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(-3.0, 3.0, 8.0, 12.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_INTERSECTS_RECTANGLE_SPANS_Y_AXIS_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(8.0, 12.0, -3.0, 3.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_INTERSECTS_RECTANGLE_CONTAINS_CENTER_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(-2.0, 2.0, -2.0, 12.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FARTHEST_X_DISTANCE_TIE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(-3.0, 3.0, -2.0, 2.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FARTHEST_Y_DISTANCE_TIE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(-2.0, 2.0, -3.0, 3.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FARTHEST_CORNER_CIRCLE_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(0.0, 3.0, 0.0, 4.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_RECTANGLE_INSIDE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(1.0, 1.0, 1.0, 1.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_RECTANGLE_OUTSIDE_INSIDE_BBOX_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(4.0, 4.0, 4.0, 4.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_RECTANGLE_TANGENT_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(5.0, 5.0, 0.0, 0.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void VERTICAL_LINE_RECTANGLE_CROSSES_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(3.0, 3.0, -10.0, 10.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HORIZONTAL_LINE_RECTANGLE_CROSSES_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(-10.0, 10.0, 3.0, 3.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(4.0, -3.0, 0.0);
        Rectangle sourceRectangle = rectangle(4.0, 4.0, -3.0, -3.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_INSIDE_AREA_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(1.0, 1.0, 0.0);
        Rectangle sourceRectangle = rectangle(-2.0, 5.0, -4.0, 6.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(1.0, 4.0, 1.0, 4.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_BOUNDARY_OF_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(0.0, 5.0, -2.0, 2.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_RECONSTRUCTIBLE_variation1() {
        CircleImpl sourceCircle = new CircleImpl(
                new PointImpl(Double.NaN, Double.NaN, CONTEXT), 5.0, CONTEXT);
        Rectangle sourceRectangle = rectangle(-2.0, 2.0, -2.0, 2.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_RECTANGLE_RECONSTRUCTIBLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
