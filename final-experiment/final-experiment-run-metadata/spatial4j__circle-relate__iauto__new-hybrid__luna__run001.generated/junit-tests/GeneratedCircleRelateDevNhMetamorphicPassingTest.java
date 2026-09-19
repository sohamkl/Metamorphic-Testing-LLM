import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CTX = new SpatialContext(false);

    private static void run(double x, double y, double radius,
                            double minX, double maxX,
                            double minY, double maxY) {
        CircleImpl sourceCircle =
                new CircleImpl(CTX.makePoint(x, y), radius, CTX);
        Rectangle sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, CTX);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);

        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void runEmpty(double minX, double maxX,
                                 double minY, double maxY) {
        Point emptyPoint = CTX.makePoint(Double.NaN, Double.NaN);
        CircleImpl sourceCircle = new CircleImpl(emptyPoint, 1.0, CTX);
        Rectangle sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, CTX);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);

        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "The spatial relation changed after the follow-up transformation");
        }
    }

    @Test
    void EMPTY_POINT_SENTINEL_1() {
        runEmpty(-2, 3, -1, 4);
    }

    @Test
    void ZERO_RADIUS_POINT_CONTAINED_1() {
        run(0, 0, 0, -2, 2, -3, 3);
    }

    @Test
    void ZERO_RADIUS_POINT_CONTAINED_2() {
        run(10, -4, 0, 8, 12, -6, -2);
    }

    @Test
    void ZERO_RADIUS_POINT_CONTAINED_3() {
        run(-7, 5, 0, -9, -5, 3, 7);
    }

    @Test
    void BBOX_DISJOINT_X_1() {
        run(0, 0, 5, 7, 9, -1, 1);
    }

    @Test
    void BBOX_DISJOINT_X_2() {
        run(10, -4, 5, 3, 5, -6, -2);
    }

    @Test
    void BBOX_DISJOINT_X_3() {
        run(-10, 6, 5, -15, -13, 5, 7);
    }

    @Test
    void BBOX_DISJOINT_Y_1() {
        run(0, 0, 5, -1, 1, 7, 9);
    }

    @Test
    void BBOX_DISJOINT_Y_2() {
        run(4, 4, 5, 3, 5, 11, 13);
    }

    @Test
    void BBOX_DISJOINT_Y_3() {
        run(-6, -3, 5, -8, -4, -10, -8);
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_1() {
        run(0, 0, 5, -6, 6, -7, 7);
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_2() {
        run(20, -10, 5, 13, 27, -17, -3);
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_3() {
        run(-12, 8, 5, -19, -5, 1, 15);
    }

    @Test
    void BBOX_EQUAL_RECTANGLE_IDENTITY_1() {
        run(0, 0, 5, -5, 5, -5, 5);
    }

    @Test
    void BBOX_EQUAL_RECTANGLE_IDENTITY_2() {
        run(11, -6, 5, 6, 16, -11, -1);
    }

    @Test
    void BBOX_EQUAL_RECTANGLE_IDENTITY_3() {
        run(-8, 9, 5, -13, -3, 4, 14);
    }

    @Test
    void PHASE_TWO_X_LEFT_Y_INSIDE_1() {
        run(0, 0, 5, 4, 6, -1, 1);
    }

    @Test
    void PHASE_TWO_X_LEFT_Y_INSIDE_2() {
        run(10, 3, 5, 14, 17, 1, 5);
    }

    @Test
    void PHASE_TWO_X_LEFT_Y_INSIDE_3() {
        run(-9, -4, 5, -5, -2, -6, -2);
    }

    @Test
    void PHASE_TWO_X_RIGHT_Y_INSIDE_1() {
        run(0, 0, 5, -6, -4, -1, 1);
    }

    @Test
    void PHASE_TWO_X_RIGHT_Y_INSIDE_2() {
        run(10, 3, 5, 3, 6, 1, 5);
    }

    @Test
    void PHASE_TWO_X_RIGHT_Y_INSIDE_3() {
        run(-9, -4, 5, -16, -13, -6, -2);
    }

    @Test
    void PHASE_TWO_X_INSIDE_Y_BELOW_1() {
        run(0, 0, 5, -1, 1, 4, 6);
    }

    @Test
    void PHASE_TWO_X_INSIDE_Y_BELOW_2() {
        run(7, -2, 5, 6, 8, 2, 5);
    }

    @Test
    void PHASE_TWO_X_INSIDE_Y_BELOW_3() {
        run(-5, 9, 5, -7, -3, 13, 15);
    }

    @Test
    void PHASE_TWO_X_INSIDE_Y_ABOVE_1() {
        run(0, 0, 5, -1, 1, -6, -4);
    }

    @Test
    void PHASE_TWO_X_INSIDE_Y_ABOVE_2() {
        run(7, -2, 5, 6, 8, -8, -5);
    }

    @Test
    void PHASE_TWO_X_INSIDE_Y_ABOVE_3() {
        run(-5, 9, 5, -7, -3, 3, 5);
    }

    @Test
    void PHASE_TWO_DIAGONAL_CLOSEST_DISJOINT_1() {
        run(0, 0, 5, 4, 6, 4, 6);
    }

    @Test
    void PHASE_TWO_DIAGONAL_CLOSEST_DISJOINT_2() {
        run(10, -4, 5, 14, 16, 0, 2);
    }

    @Test
    void PHASE_TWO_DIAGONAL_CLOSEST_DISJOINT_3() {
        run(-7, 8, 5, -3, -1, 12, 14);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_CIRCLE_CONTAINS_1() {
        run(0, 0, 5, -1, 1, -1, 1);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_CIRCLE_CONTAINS_2() {
        run(12, -3, 5, 10, 14, -4, -2);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_CIRCLE_CONTAINS_3() {
        run(-8, 7, 5, -9, -7, 6, 8);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_FARTHEST_OUTSIDE_1() {
        run(0, 0, 5, -4, 4, -4, 4);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_FARTHEST_OUTSIDE_2() {
        run(12, -3, 5, 8, 16, -7, 1);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_FARTHEST_OUTSIDE_3() {
        run(-8, 7, 5, -12, -4, 3, 11);
    }

    @Test
    void CLOSEST_POINT_ON_CIRCLE_BOUNDARY_1() {
        run(0, 0, 5, 3, 6, 4, 6);
    }

    @Test
    void CLOSEST_POINT_ON_CIRCLE_BOUNDARY_2() {
        run(10, -4, 5, 13, -1, -1, 2);
    }

    @Test
    void CLOSEST_POINT_ON_CIRCLE_BOUNDARY_3() {
        run(-7, 6, 5, -4, -1, 9, 12);
    }

    @Test
    void FARTHEST_CORNER_ON_CIRCLE_BOUNDARY_1() {
        run(0, 0, 5, -3, 3, -4, 4);
    }

    @Test
    void FARTHEST_CORNER_ON_CIRCLE_BOUNDARY_2() {
        run(9, 2, 5, 6, 12, -2, 6);
    }

    @Test
    void FARTHEST_CORNER_ON_CIRCLE_BOUNDARY_3() {
        run(-11, -5, 5, -14, -8, -9, -1);
    }

    @Test
    void DEGENERATE_RECTANGLE_BOUNDARIES_1() {
        run(0, 0, 5, 1, 1, -2, 2);
    }

    @Test
    void DEGENERATE_RECTANGLE_BOUNDARIES_2() {
        run(10, -4, 5, 7, 13, -4, -4);
    }

    @Test
    void DEGENERATE_RECTANGLE_BOUNDARIES_3() {
        run(-7, 6, 5, -7, -7, 6, 6);
    }
}
