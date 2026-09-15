import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicTest {

    private static void verify(double cx, double cy, double radius,
                               double minX, double maxX,
                               double minY, double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle =
                new CircleImpl(context.makePoint(cx, cy), radius, context);
        Rectangle sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);

        SpatialRelation sourceOutput =
                org.locationtech.spatial4j.shape.impl.CircleImpl.relate(
                        sourceCircle, sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);

        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.CircleImpl.relate(
                        followUpCircle, followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static void verifyEmptyCircle(double minX, double maxX,
                                           double minY, double maxY) {
        SpatialContext context = new SpatialContext(false);
        Point emptyPoint = context.makePoint(Double.NaN, Double.NaN);
        CircleImpl sourceCircle = new CircleImpl(emptyPoint, 1.0, context);
        Rectangle sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);

        SpatialRelation sourceOutput =
                org.locationtech.spatial4j.shape.impl.CircleImpl.relate(
                        sourceCircle, sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);

        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.CircleImpl.relate(
                        (CircleImpl) followUp[0], (Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static void verifyEmptyRectangle(double cx, double cy,
                                              double radius) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle =
                new CircleImpl(context.makePoint(cx, cy), radius, context);
        Rectangle sourceRectangle =
                context.makeRectangle(Double.NaN, Double.NaN,
                        Double.NaN, Double.NaN);

        SpatialRelation sourceOutput =
                org.locationtech.spatial4j.shape.impl.CircleImpl.relate(
                        sourceCircle, sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);

        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.CircleImpl.relate(
                        (CircleImpl) followUp[0], (Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static void verifyIdentity(double cx, double cy, double radius) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle =
                new CircleImpl(context.makePoint(cx, cy), radius, context);
        Rectangle box = sourceCircle.getBoundingBox();
        Rectangle sourceRectangle =
                new RectangleImpl(box.getMinX(), box.getMaxX(),
                        box.getMinY(), box.getMaxY(), context);

        SpatialRelation sourceOutput =
                org.locationtech.spatial4j.shape.impl.CircleImpl.relate(
                        sourceCircle, sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);

        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.CircleImpl.relate(
                        (CircleImpl) followUp[0], (Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_CIRCLE_SENTINEL_variation1() {
        verifyEmptyCircle(2.0, 3.0, 2.0, 3.0);
    }

    @Test
    void EMPTY_RECTANGLE_SENTINEL_variation1() {
        verifyEmptyRectangle(0.0, 0.0, 1.0);
    }

    @Test
    void ZERO_RADIUS_DISJOINT_variation1() {
        verify(0.0, 0.0, 0.0, 2.0, 3.0, 2.0, 3.0);
    }

    @Test
    void ZERO_RADIUS_DISJOINT_variation2() {
        verify(0.0, 0.0, 0.0, -3.0, -2.0, -3.0, -2.0);
    }

    @Test
    void ZERO_RADIUS_WITHIN_RECTANGLE_variation1() {
        verify(0.0, 0.0, 0.0, -2.0, 2.0, -2.0, 2.0);
    }

    @Test
    void ZERO_RADIUS_WITHIN_RECTANGLE_variation2() {
        verify(0.0, 0.0, 0.0, -1.0, 1.0, -3.0, 3.0);
    }

    @Test
    void BBOX_DISJOINT_LEFT_BELOW_variation1() {
        verify(0.0, 0.0, 1.0, 3.0, 4.0, 3.0, 4.0);
    }

    @Test
    void BBOX_DISJOINT_LEFT_BELOW_variation2() {
        verify(0.0, 0.0, 1.0, 4.0, 5.0, 1.5, 2.5);
    }

    @Test
    void BBOX_DISJOINT_RIGHT_ABOVE_variation1() {
        verify(0.0, 0.0, 1.0, -4.0, -3.0, -4.0, -3.0);
    }

    @Test
    void BBOX_DISJOINT_RIGHT_ABOVE_variation2() {
        verify(0.0, 0.0, 1.0, -5.0, -4.0, -2.5, -1.5);
    }

    @Test
    void BBOX_WITHIN_STRICT_variation1() {
        verify(0.0, 0.0, 2.0, -3.0, 3.0, -3.0, 3.0);
    }

    @Test
    void BBOX_WITHIN_STRICT_variation2() {
        verify(0.0, 0.0, 2.0, -4.0, 4.0, -2.5, 2.5);
    }

    @Test
    void BBOX_EQUALS_RECTANGLE_IDENTITY_variation1() {
        verifyIdentity(0.0, 0.0, 1.0);
    }

    @Test
    void BBOX_EQUALS_RECTANGLE_IDENTITY_variation2() {
        verifyIdentity(3.0, -4.0, 2.0);
    }

    @Test
    void PHASE2_DISJOINT_CORNER_variation1() {
        verify(0.0, 0.0, 2.0, 2.0, 3.0, 2.0, 3.0);
    }

    @Test
    void PHASE2_DISJOINT_CORNER_variation2() {
        verify(0.0, 0.0, 2.0, -3.0, -2.0, 2.0, 3.0);
    }

    @Test
    void PHASE2_INTERSECTS_X_AXIS_SPANNED_variation1() {
        verify(0.0, 0.0, 2.0, -1.0, 3.0, 1.5, 3.0);
    }

    @Test
    void PHASE2_INTERSECTS_X_AXIS_SPANNED_variation2() {
        verify(0.0, 0.0, 2.0, -3.0, 1.0, -3.0, -1.5);
    }

    @Test
    void PHASE2_INTERSECTS_Y_AXIS_SPANNED_variation1() {
        verify(0.0, 0.0, 2.0, 1.5, 3.0, -1.0, 3.0);
    }

    @Test
    void PHASE2_INTERSECTS_Y_AXIS_SPANNED_variation2() {
        verify(0.0, 0.0, 2.0, -3.0, -1.5, -3.0, 1.0);
    }

    @Test
    void TANGENT_TO_RECTANGLE_CORNER_variation1() {
        verify(0.0, 0.0, 1.0, 0.6, 1.0, 0.8, 1.2);
    }

    @Test
    void TANGENT_TO_RECTANGLE_CORNER_variation2() {
        verify(0.0, 0.0, 1.0, -1.0, -0.6, -1.2, -0.8);
    }

    @Test
    void RECTANGLE_CONTAINED_BY_CIRCLE_variation1() {
        verify(0.0, 0.0, 5.0, -2.0, 2.0, -2.0, 2.0);
    }

    @Test
    void RECTANGLE_CONTAINED_BY_CIRCLE_variation2() {
        verify(0.0, 0.0, 5.0, -3.0, 1.0, -1.0, 3.0);
    }

    @Test
    void BBOX_CONTAINS_BUT_CORNER_ESCAPES_variation1() {
        verify(0.0, 0.0, 2.0, -1.5, 1.5, -1.5, 1.5);
    }

    @Test
    void BBOX_CONTAINS_BUT_CORNER_ESCAPES_variation2() {
        verify(0.0, 0.0, 2.0, -1.8, 1.0, -1.0, 1.8);
    }

    @Test
    void CENTER_ON_RECTANGLE_MIN_X_variation1() {
        verify(0.0, 0.0, 2.0, 0.0, 2.0, -1.0, 1.0);
    }

    @Test
    void CENTER_ON_RECTANGLE_MIN_X_variation2() {
        verify(0.0, 0.0, 2.0, 0.0, 3.0, -0.5, 0.5);
    }

    @Test
    void CENTER_ON_RECTANGLE_MAX_X_variation1() {
        verify(0.0, 0.0, 2.0, -2.0, 0.0, -1.0, 1.0);
    }

    @Test
    void CENTER_ON_RECTANGLE_MAX_X_variation2() {
        verify(0.0, 0.0, 2.0, -3.0, 0.0, -0.5, 0.5);
    }

    @Test
    void CENTER_ON_RECTANGLE_MIN_Y_variation1() {
        verify(0.0, 0.0, 2.0, -1.0, 1.0, 0.0, 2.0);
    }

    @Test
    void CENTER_ON_RECTANGLE_MIN_Y_variation2() {
        verify(0.0, 0.0, 2.0, -0.5, 0.5, 0.0, 3.0);
    }

    @Test
    void CENTER_ON_RECTANGLE_MAX_Y_variation1() {
        verify(0.0, 0.0, 2.0, -1.0, 1.0, -2.0, 0.0);
    }

    @Test
    void CENTER_ON_RECTANGLE_MAX_Y_variation2() {
        verify(0.0, 0.0, 2.0, -0.5, 0.5, -3.0, 0.0);
    }

    @Test
    void VERTICAL_DEGENERATE_RECTANGLE_INSIDE_variation1() {
        verify(0.0, 0.0, 3.0, 1.0, 1.0, -1.0, 1.0);
    }

    @Test
    void VERTICAL_DEGENERATE_RECTANGLE_INSIDE_variation2() {
        verify(0.0, 0.0, 3.0, -2.0, -2.0, -1.0, 1.0);
    }

    @Test
    void HORIZONTAL_DEGENERATE_RECTANGLE_INTERSECTS_variation1() {
        verify(0.0, 0.0, 2.0, -2.5, 2.5, 0.5, 0.5);
    }

    @Test
    void HORIZONTAL_DEGENERATE_RECTANGLE_INTERSECTS_variation2() {
        verify(0.0, 0.0, 2.0, -1.0, 3.0, -0.5, -0.5);
    }

    @Test
    void POINT_DEGENERATE_RECTANGLE_AT_CENTER_variation1() {
        verify(0.0, 0.0, 2.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Test
    void POINT_DEGENERATE_RECTANGLE_OUTSIDE_variation1() {
        verify(0.0, 0.0, 2.0, 2.0, 2.0, 2.0, 2.0);
    }

    @Test
    void RECTANGLE_STRADDLING_CIRCLE_AXIS_variation1() {
        verify(0.0, 0.0, 3.0, -1.0, 1.0, -4.0, 4.0);
    }

    @Test
    void RECTANGLE_STRADDLING_CIRCLE_AXIS_variation2() {
        verify(0.0, 0.0, 3.0, -4.0, 4.0, -1.0, 1.0);
    }

    @Test
    void LARGE_COORDINATE_TRANSLATION_variation1() {
        verify(1000000.0, -1000000.0, 10.0,
                999980.0, 1000020.0, -1000020.0, -999980.0);
    }

    @Test
    void LARGE_COORDINATE_TRANSLATION_variation2() {
        verify(1000000.0, -1000000.0, 10.0,
                999995.0, 1000005.0, -1000005.0, -999995.0);
    }

    @Test
    void NEAR_ZERO_FINITE_COORDINATES_variation1() {
        verify(1.0e-12, -1.0e-12, 1.0e-13,
                1.0e-9, 2.0e-9, 1.0e-9, 2.0e-9);
    }

    @Test
    void NEAR_ZERO_FINITE_COORDINATES_variation2() {
        verify(1.0e-12, -1.0e-12, 1.0e-12,
                -2.0e-12, 2.0e-12, -2.0e-12, 2.0e-12);
    }

    @Test
    void ALL_RELATION_RESULTS_TRANSLATION_CHECK_variation1() {
        verify(0.0, 0.0, 2.0, -1.0, 3.0, 1.5, 3.0);
    }

    @Test
    void ALL_RELATION_RESULTS_TRANSLATION_CHECK_variation2() {
        verify(0.0, 0.0, 5.0, -2.0, 2.0, -2.0, 2.0);
    }

    @Test
    void ALL_RELATION_RESULTS_TRANSLATION_CHECK_variation3() {
        verify(0.0, 0.0, 0.0, 2.0, 3.0, 2.0, 3.0);
    }

    @Test
    void ALL_RELATION_RESULTS_TRANSLATION_CHECK_variation4() {
        verify(0.0, 0.0, 0.0, -2.0, 2.0, -2.0, 2.0);
    }
}
