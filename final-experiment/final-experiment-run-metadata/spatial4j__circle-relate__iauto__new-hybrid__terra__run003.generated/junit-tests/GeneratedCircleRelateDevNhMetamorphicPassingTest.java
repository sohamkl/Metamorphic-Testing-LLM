import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Translation changed the spatial relation from "
                            + sourceOutput + " to " + followUpOutput);
        }
    }

    @Test
    public void testBBOX_STRICTLY_WITHIN_LARGE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(-2.0, 2.0, -2.0, 2.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testBBOX_EQUALITY_IDENTITY_EDGE_CASE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(-1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testBBOX_STRICTLY_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(2.0, 3.0, 2.0, 3.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDIAGONAL_BBOX_OVERLAP_BUT_CIRCLE_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(0.8, 1.2, 0.8, 1.2);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDIAGONAL_OVERLAP_WITH_OUTSIDE_FARTHEST_CORNER_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(0.6, 0.8, 0.6, 0.8);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testRECTANGLE_STRICTLY_CONTAINED_BY_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(-0.25, 0.75, -0.25, 0.25);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHORIZONTAL_EDGE_TANGENCY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(1.0, 2.0, -0.2, 0.2);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testVERTICAL_EDGE_TANGENCY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(-0.2, 0.2, 1.0, 2.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testRECTANGLE_SPANS_BOTH_AXES_BUT_CUTS_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(-2.0, 2.0, -0.2, 0.2);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOINT_RECTANGLE_INSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(0.5, 0.5, 0.5, 0.5);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOINT_RECTANGLE_IN_BBOX_BUT_OUTSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(0.9, 0.9, 0.9, 0.9);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 0.0, context);
        Rectangle rectangle = context.makeRectangle(0.0, 0.0, 0.0, 0.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_RADIUS_INSIDE_AREA_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 0.0, context);
        Rectangle rectangle = context.makeRectangle(-1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_RADIUS_SEPARATE_POINT_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 0.0, context);
        Rectangle rectangle = context.makeRectangle(1.0, 1.0, 1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testEMPTY_CIRCLE_WITH_NONEMPTY_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                context.makePoint(Double.NaN, Double.NaN), 1.0, context);
        Rectangle rectangle = context.makeRectangle(-1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNONEMPTY_CIRCLE_WITH_EMPTY_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testEMPTY_CIRCLE_WITH_EMPTY_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                context.makePoint(Double.NaN, Double.NaN), 1.0, context);
        Rectangle rectangle = context.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
