import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CTX = new SpatialContext(false);

    private static CircleImpl circle(double x, double y, double radius) {
        return new CircleImpl(new PointImpl(x, y, CTX), radius, CTX);
    }

    private static Rectangle rectangle(double minX, double maxX,
                                       double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CTX);
    }

    private static MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input
    generateFollowUp(MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source,
                     double dx, double dy) {
        CircleImpl sourceCircle = source.receiver();
        Rectangle sourceRectangle = source.arg0();

        CircleImpl translatedCircle = circle(
                sourceCircle.getCenter().getX() + dx,
                sourceCircle.getCenter().getY() + dy,
                sourceCircle.getRadius());

        Rectangle translatedRectangle = rectangle(
                sourceRectangle.getMinX() + dx,
                sourceRectangle.getMaxX() + dx,
                sourceRectangle.getMinY() + dy,
                sourceRectangle.getMaxY() + dy);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle, translatedRectangle);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source,
            double dx, double dy) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(
                        generateFollowUp(source, dx, dy));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_FAR_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(20, 22, 20, 22)),
                100, 200);
    }

    @Test
    void BBOX_DISJOINT_FAR_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 0),
                        rectangle(20, 22, 20, 22)),
                -150, 75);
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(-10, 10, -10, 10)),
                100, 200);
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 3),
                        rectangle(-10, 10, -10, 10)),
                -150, 75);
    }

    @Test
    void BBOX_EXACT_RECTANGLE_IDENTITY_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(-5, 5, -5, 5)),
                100, 200);
    }

    @Test
    void BBOX_EXACT_RECTANGLE_IDENTITY_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 10),
                        rectangle(-10, 10, -10, 10)),
                -150, 75);
    }

    @Test
    void CENTER_LEFT_AXIS_SPAN_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(4, 6, -1, 1)),
                100, 200);
    }

    @Test
    void CENTER_LEFT_AXIS_SPAN_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 6),
                        rectangle(4, 6, -1, 1)),
                -150, 75);
    }

    @Test
    void CENTER_RIGHT_AXIS_SPAN_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(-6, -4, -1, 1)),
                100, 200);
    }

    @Test
    void CENTER_RIGHT_AXIS_SPAN_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 7),
                        rectangle(-6, -4, -1, 1)),
                -150, 75);
    }

    @Test
    void CENTER_BELOW_AXIS_SPAN_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(-1, 1, 4, 6)),
                100, 200);
    }

    @Test
    void CENTER_BELOW_AXIS_SPAN_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 8),
                        rectangle(-1, 1, 4, 6)),
                -150, 75);
    }

    @Test
    void CENTER_ABOVE_AXIS_SPAN_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(-1, 1, -6, -4)),
                100, 200);
    }

    @Test
    void CENTER_ABOVE_AXIS_SPAN_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 9),
                        rectangle(-1, 1, -6, -4)),
                -150, 75);
    }

    @Test
    void CLOSEST_CORNER_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(4, 6, 4, 6)),
                100, 200);
    }

    @Test
    void CLOSEST_CORNER_OUTSIDE_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 4),
                        rectangle(4, 6, 4, 6)),
                -150, 75);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_PARTIAL_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(-4, 4, 3, 4)),
                100, 200);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_PARTIAL_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 6),
                        rectangle(-4, 4, 3, 4)),
                -150, 75);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_FULL_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(-2, 2, -2, 2)),
                100, 200);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_FULL_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 10),
                        rectangle(-3, 3, -4, 4)),
                -150, 75);
    }

    @Test
    void FARTHEST_POINT_TIE_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 10),
                        rectangle(-3, 3, -4, 4)),
                100, 200);
    }

    @Test
    void FARTHEST_POINT_TIE_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 8),
                        rectangle(-3, 3, -4, 4)),
                -150, 75);
    }

    @Test
    void ZERO_RADIUS_WITHIN_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 0),
                        rectangle(-1, 1, -1, 1)),
                100, 200);
    }

    @Test
    void ZERO_RADIUS_WITHIN_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 0),
                        rectangle(-2, 2, -2, 2)),
                -150, 75);
    }

    @Test
    void ZERO_RADIUS_DISJOINT_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 0),
                        rectangle(2, 3, 2, 3)),
                100, 200);
    }

    @Test
    void ZERO_RADIUS_DISJOINT_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 0),
                        rectangle(5, 6, 5, 6)),
                -150, 75);
    }

    @Test
    void CLOSEST_POINT_ON_CIRCLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(5, 7, -1, 1)),
                100, 200);
    }

    @Test
    void CLOSEST_POINT_ON_CIRCLE_BOUNDARY_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 8),
                        rectangle(8, 10, -2, 2)),
                -150, 75);
    }

    @Test
    void RECTANGLE_CORNER_ON_CIRCLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 5),
                        rectangle(3, 4, 0, 3)),
                100, 200);
    }

    @Test
    void RECTANGLE_CORNER_ON_CIRCLE_BOUNDARY_variation2() {
        assertMetamorphicRelationFor(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle(0, 0, 10),
                        rectangle(6, 8, 0, 6)),
                -150, 75);
    }
}
