import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private SpatialContext flatContext() {
        SpatialContextFactory factory = new SpatialContextFactory();
        factory.geo = false;
        return factory.newSpatialContext();
    }

    private CircleImpl circle(SpatialContext context, double x, double y, double radius) {
        Point center = context.makePoint(x, y);
        return new CircleImpl(center, radius, context);
    }

    private CircleImpl emptyCircle(SpatialContext context) {
        return new CircleImpl(context.makePoint(Double.NaN, Double.NaN), 0.0, context);
    }

    private Rectangle rectangle(SpatialContext context,
                                double minX, double maxX,
                                double minY, double maxY) {
        return context.makeRectangle(minX, maxX, minY, maxY);
    }

    private void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_NONEMPTY_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(emptyCircle(context), rectangle(context, -1, 1, -1, 1));
    }

    @Test
    public void NONEMPTY_CIRCLE_EMPTY_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4),
                new RectangleImpl(Double.NaN, Double.NaN, Double.NaN, Double.NaN, context));
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1() {
        SpatialContext context = flatContext();
        exercise(emptyCircle(context),
                new RectangleImpl(Double.NaN, Double.NaN, Double.NaN, Double.NaN, context));
    }

    @Test
    public void BBOX_DISJOINT_ON_X_AXIS_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, 5, 6, -1, 1));
    }

    @Test
    public void BBOX_DISJOINT_ON_Y_AXIS_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, -1, 1, 5, 6));
    }

    @Test
    public void CIRCLE_BBOX_STRICTLY_WITHIN_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, -5, 5, -5, 5));
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_ENCLOSING_BOX_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, -4, 4, -4, 4));
    }

    @Test
    public void PHASE2_DIAGONAL_CLOSEST_CORNER_DISJOINT_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, 3, 5, 3, 5));
    }

    @Test
    public void PHASE2_X_SEPARATED_Y_SPANNING_INTERSECTS_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, 3, 5, -1, 1));
    }

    @Test
    public void PHASE2_Y_SEPARATED_X_SPANNING_INTERSECTS_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, -1, 1, 3, 5));
    }

    @Test
    public void PHASE2_RECTANGLE_SPANS_BOTH_AXES_variation1() {
        SpatialContext context = flatContext();
        CircleImpl sourceCircle = circle(context, 0, 0, 4);
        Rectangle sourceRectangle = rectangle(context, -3, 3, -3, 3);
        exercise(sourceCircle, sourceRectangle);
    }

    @Test
    public void PHASE2_BBOX_CONTAINS_RECTANGLE_BUT_CORNER_OUTSIDE_variation1() {
        SpatialContext context = flatContext();
        CircleImpl sourceCircle = circle(context, 0, 0, 4);
        Rectangle sourceRectangle = new RectangleImpl(-3, 3, -3, 3, context);
        exercise(sourceCircle, sourceRectangle);
    }

    @Test
    public void PHASE2_CIRCLE_CONTAINS_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, -2, 2, -2, 2));
    }

    @Test
    public void SIDE_TANGENCY_INTERSECTS_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, 4, 6, -1, 1));
    }

    @Test
    public void CORNER_TANGENCY_INTERSECTS_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 5), rectangle(context, 3, 6, 4, 6));
    }

    @Test
    public void ZERO_RADIUS_WITH_CENTER_STRICTLY_INSIDE_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 0), rectangle(context, -1, 1, -1, 1));
    }

    @Test
    public void ZERO_RADIUS_CENTER_ON_RECTANGLE_BOUNDARY_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 0), rectangle(context, 0, 1, -1, 1));
    }

    @Test
    public void ZERO_RADIUS_RECTANGLE_EQUALS_DEGENERATE_BBOX_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 0), rectangle(context, 0, 0, 0, 0));
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 0), rectangle(context, 1, 1, 0, 0));
    }

    @Test
    public void POINT_RECTANGLE_STRICTLY_INSIDE_CIRCLE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, 1, 1, 1, 1));
    }

    @Test
    public void POINT_RECTANGLE_ON_CIRCLE_BOUNDARY_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, 4, 4, 0, 0));
    }

    @Test
    public void HORIZONTAL_TANGENT_LINE_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, -1, 1, 4, 4));
    }

    @Test
    public void VERTICAL_CHORD_LINE_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, 0, 0, -3, 3));
    }

    @Test
    public void HORIZONTAL_LINE_CROSSES_CIRCLE_AND_EXTENDS_OUTSIDE_variation1() {
        SpatialContext context = flatContext();
        exercise(circle(context, 0, 0, 4), rectangle(context, -5, 5, 0, 0));
    }
}
