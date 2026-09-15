import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicTest {

    private static final SpatialContext CONTEXT = SpatialContext.CARTESIAN;

    @Test
    void BBOX_DISJOINT_FAR_RECTANGLE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(20, 22, 20, 22, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_FAR_RECTANGLE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-3, 4), 2, CONTEXT);
        Rectangle rectangle = new RectangleImpl(15, 18, 15, 18, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_FAR_RECTANGLE_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(2, -2), 7, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-20, -18, 15, 18, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_WITHIN_LARGE_RECTANGLE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-10, 10, -10, 10, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_WITHIN_LARGE_RECTANGLE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(1, -1), 4, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-12, 12, -11, 11, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_WITHIN_LARGE_RECTANGLE_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-2, 3), 0, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-8, 9, -7, 10, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_IDENTITY_EDGE_CASE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-5, 5, -5, 5, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_IDENTITY_EDGE_CASE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 0, CONTEXT);
        Rectangle rectangle = new RectangleImpl(0, 0, 0, 0, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_IDENTITY_EDGE_CASE_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(2, -3), 4, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-2, 6, -7, 1, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_IDENTITY_EDGE_CASE_4() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-1, 2), 3, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-4, 2, -1, 5, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_CENTERED_RECTANGLE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-2, 2, -2, 2, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_CENTERED_RECTANGLE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(1, -1), 6, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-1, 3, -3, 1, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_CENTERED_RECTANGLE_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-2, 2), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-4, 0, 0, 4, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_CENTERED_RECTANGLE_4() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-1.5, 1.5, -2.5, 2.5, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_RECTANGLE_WITH_BOUNDARY_CORNER_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-3, 3, -4, 4, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_RECTANGLE_WITH_BOUNDARY_CORNER_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(1, 1), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-2, 4, -3, 5, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_RECTANGLE_WITH_BOUNDARY_CORNER_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-1, 2), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-4, 2, -2, 6, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_RECTANGLE_WITH_BOUNDARY_CORNER_4() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-4, 4, -3, 3, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_CORNER_OUTSIDE_INSIDE_BBOX_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-4, 4, -4, 4, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_CORNER_OUTSIDE_INSIDE_BBOX_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(1, -1), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-3, 5, -5, 3, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_CORNER_OUTSIDE_INSIDE_BBOX_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-2, 2), 6, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-6, 2, -2, 6, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_CORNER_OUTSIDE_INSIDE_BBOX_4() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 3, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-2.5, 2.5, -2.5, 2.5, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PHASE2_DISJOINT_CLOSEST_CORNER_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(4, 6, 4, 6, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PHASE2_DISJOINT_CLOSEST_CORNER_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(1, -1), 4, CONTEXT);
        Rectangle rectangle = new RectangleImpl(4, 6, 2, 4, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PHASE2_DISJOINT_CLOSEST_CORNER_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-2, 3), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(1, 3, 7, 9, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PHASE2_DISJOINT_CLOSEST_CORNER_4() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 2, CONTEXT);
        Rectangle rectangle = new RectangleImpl(1.5, 3, 1.5, 3, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_X_OUTSIDE_Y_INSIDE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(4, 6, -1, 1, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_X_OUTSIDE_Y_INSIDE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-2, 1), 4, CONTEXT);
        Rectangle rectangle = new RectangleImpl(1, 5, 0, 2, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_X_OUTSIDE_Y_INSIDE_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(2, -2), 6, CONTEXT);
        Rectangle rectangle = new RectangleImpl(6, 8, -4, 0, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_X_INSIDE_Y_OUTSIDE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-1, 1, 4, 6, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_X_INSIDE_Y_OUTSIDE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(1, -1), 6, CONTEXT);
        Rectangle rectangle = new RectangleImpl(0, 2, 3, 7, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSECTS_X_INSIDE_Y_OUTSIDE_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-2, 1), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-4, 0, 4, 6, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_BOTH_AXES_OUTSIDE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(1, 2, 1, 2, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_BOTH_AXES_OUTSIDE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-1, -1), 6, CONTEXT);
        Rectangle rectangle = new RectangleImpl(0, 1, 0, 1, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_BOTH_AXES_OUTSIDE_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(1.25, 2.25, 1.25, 2.25, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TANGENT_RECTANGLE_AT_X_BOUNDARY_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 5, CONTEXT);
        Rectangle rectangle = new RectangleImpl(5, 6, -1, 1, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TANGENT_RECTANGLE_AT_X_BOUNDARY_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(1, -1), 4, CONTEXT);
        Rectangle rectangle = new RectangleImpl(5, 7, -2, 0, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TANGENT_RECTANGLE_AT_X_BOUNDARY_3() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-2, 2), 6, CONTEXT);
        Rectangle rectangle = new RectangleImpl(4, 6, 1, 3, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_FAR_RECTANGLE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 0, CONTEXT);
        Rectangle rectangle = new RectangleImpl(10, 12, 10, 12, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_FAR_RECTANGLE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-3, 4), 0, CONTEXT);
        Rectangle rectangle = new RectangleImpl(8, 10, 8, 10, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_TOUCHING_RECTANGLE_1() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(0, 0), 0, CONTEXT);
        Rectangle rectangle = new RectangleImpl(0, 1, -1, 1, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_TOUCHING_RECTANGLE_2() {
        CircleImpl source = new CircleImpl(CONTEXT.makePoint(-2, 3), 0, CONTEXT);
        Rectangle rectangle = new RectangleImpl(-2, 0, 2, 5, CONTEXT);
        SpatialRelation sourceOutput = source.relate(rectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
