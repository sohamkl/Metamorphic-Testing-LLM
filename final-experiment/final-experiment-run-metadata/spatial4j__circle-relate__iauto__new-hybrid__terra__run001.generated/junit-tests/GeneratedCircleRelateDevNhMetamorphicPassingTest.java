import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static CircleImpl circle(SpatialContext context, double x, double y, double radius) {
        return new CircleImpl(context.makePoint(x, y), radius, context);
    }

    private static Rectangle rectangle(SpatialContext context,
                                       double minX, double maxX,
                                       double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                                  SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Joint translation changed the spatial relation from "
                            + sourceOutput + " to " + followUpOutput);
        }
    }

    @Test
    public void BBOX_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 10.0, 12.0, 10.0, 12.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_STRICTLY_WITHIN_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, -10.0, 10.0, -10.0, 10.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_WITHIN_TOUCHING_RECTANGLE_BOUNDARY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, -5.0, 10.0, -8.0, 8.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_EQUALS_RECTANGLE_IDENTITY_EDGE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, -5.0, 5.0, -5.0, 5.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_CORNER_OUTSIDE_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 4.0, 6.0, 4.0, 6.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_DIAGONAL_CORNER_TOUCH_OR_OVERLAP_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 3.0, 6.0, 4.0, 6.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_X_OUTSIDE_Y_SPANNED_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 4.0, 7.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_Y_OUTSIDE_X_SPANNED_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 1.0, 4.0, 7.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PHASE2_CENTER_ON_RECTANGLE_EDGE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 0.0, 7.0, -2.0, 2.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_FULLY_INSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 2.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_FARTHEST_POINT_ON_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 0.0, 3.0, 0.0, 4.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_CORNER_OUTSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 3.0, 5.0, 3.0, 5.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_X_AXIS_OUTSIDE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 1.0, 2.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_Y_AXIS_OUTSIDE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 1.0, 1.0, 2.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_CIRCLE_POINT_RECTANGLE_INSIDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, 1.0, 1.0, 1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_CIRCLE_LINE_RECTANGLE_CROSSING_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(context, -10.0, 10.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(context, 0.0, 0.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_AREA_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(context, -1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_POINT_DISJOINT_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = circle(context, 0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(context, 1.0, 2.0, 1.0, 2.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
