import org.junit.jupiter.api.Test;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final org.locationtech.spatial4j.context.SpatialContext CONTEXT =
            new org.locationtech.spatial4j.context.SpatialContext(false);

    private static Object[] generateFollowUp(
            org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle,
            org.locationtech.spatial4j.shape.Rectangle sourceRectangle) {

        org.locationtech.spatial4j.context.SpatialContext context = sourceCircle.getContext();
        org.locationtech.spatial4j.shape.Point shiftedCenter = context.makePoint(
                sourceCircle.getCenter().getX() + 17.5,
                sourceCircle.getCenter().getY() - 9.25);

        org.locationtech.spatial4j.shape.impl.CircleImpl shiftedCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        shiftedCenter, sourceCircle.getRadius(), context);

        org.locationtech.spatial4j.shape.Rectangle shiftedRectangle =
                new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                        sourceRectangle.getMinX() + 17.5,
                        sourceRectangle.getMaxX() + 17.5,
                        sourceRectangle.getMinY() - 9.25,
                        sourceRectangle.getMaxY() - 9.25,
                        context);

        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    @Test
    public void BBOX_DISJOINT_LEFT_variation1() {
        double cx = 10.0, cy = 20.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 9.0, cx - 6.0, cy - 1.0, cy + 1.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_ABOVE_variation1() {
        double cx = -12.0, cy = 8.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 2.0, cx + 2.0, cy + 6.0, cy + 9.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_STRICTLY_WITHIN_RECTANGLE_variation1() {
        double cx = 35.0, cy = -14.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 6.0, cx + 6.0, cy - 7.0, cy + 7.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_WITHIN_RECTANGLE_SHARING_BOX_EDGE_variation1() {
        double cx = -30.0, cy = -22.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 5.0, cx + 6.0, cy - 6.0, cy + 6.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1() {
        double cx = 4.0, cy = -7.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 5.0, cx + 5.0, cy - 5.0, cy + 5.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHEAST_CORNER_OVERLAP_BUT_DISJOINT_variation1() {
        double cx = 18.0, cy = 11.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 4.0, cx + 5.0, cy + 4.0, cy + 5.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHWEST_CORNER_OVERLAP_BUT_DISJOINT_variation1() {
        double cx = -24.0, cy = 31.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 5.0, cx - 4.0, cy + 4.0, cy + 5.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHEAST_CORNER_OVERLAP_BUT_DISJOINT_variation1() {
        double cx = 42.0, cy = 13.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 4.0, cx + 5.0, cy - 5.0, cy - 4.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHWEST_CORNER_OVERLAP_BUT_DISJOINT_variation1() {
        double cx = -15.0, cy = -37.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 5.0, cx - 4.0, cy - 5.0, cy - 4.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSEST_CORNER_EXACTLY_TANGENT_variation1() {
        double cx = 6.0, cy = 19.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 3.0, cx + 6.0, cy + 4.0, cy + 6.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RIGHT_SIDE_EXTERNAL_TANGENCY_variation1() {
        double cx = 27.0, cy = -3.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 5.0, cx + 7.0, cy - 1.0, cy + 1.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TOP_SIDE_EXTERNAL_TANGENCY_variation1() {
        double cx = -9.0, cy = 44.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 1.0, cx + 1.0, cy + 5.0, cy + 7.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void VERTICAL_AXIS_OVERLAP_variation1() {
        double cx = 13.0, cy = -28.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 1.0, cx + 1.0, cy + 4.0, cy + 6.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HORIZONTAL_AXIS_OVERLAP_variation1() {
        double cx = -41.0, cy = 5.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 4.0, cx + 6.0, cy - 1.0, cy + 1.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CENTRAL_RECTANGLE_CONTAINED_variation1() {
        double cx = 3.0, cy = 2.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 1.0, cx + 1.0, cy - 1.0, cy + 1.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHEAST_RECTANGLE_CONTAINED_variation1() {
        double cx = 22.0, cy = 36.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 1.0, cx + 2.0, cy + 1.0, cy + 2.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHWEST_RECTANGLE_CONTAINED_variation1() {
        double cx = -33.0, cy = 17.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 2.0, cx - 1.0, cy - 2.0, cy - 1.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ASYMMETRIC_CROSS_AXIS_RECTANGLE_CONTAINED_variation1() {
        double cx = 39.0, cy = -31.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 1.0, cx + 2.0, cy - 2.0, cy + 1.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SYMMETRIC_FARTHEST_CORNER_TIES_variation1() {
        double cx = -6.0, cy = -11.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 2.0, cx + 2.0, cy - 2.0, cy + 2.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FARTHEST_CORNER_EXACTLY_ON_CIRCLE_variation1() {
        double cx = 8.0, cy = 26.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx, cx + 3.0, cy, cy + 4.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_BUT_CIRCLE_ONLY_INTERSECTS_variation1() {
        double cx = -20.0, cy = 9.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx, cx + 4.0, cy, cy + 4.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RIGHT_EDGE_POSITIVE_OVERLAP_variation1() {
        double cx = 31.0, cy = 7.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 4.0, cx + 6.0, cy - 2.0, cy + 2.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEFT_EDGE_POSITIVE_OVERLAP_variation1() {
        double cx = -27.0, cy = -5.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 6.0, cx - 4.0, cy - 2.0, cy + 2.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTTOM_EDGE_POSITIVE_OVERLAP_variation1() {
        double cx = 16.0, cy = 40.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 2.0, cx + 2.0, cy - 6.0, cy - 4.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERIOR_POINT_RECTANGLE_variation1() {
        double cx = -2.0, cy = 14.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 1.0, cx + 1.0, cy + 2.0, cy + 2.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDARY_POINT_RECTANGLE_variation1() {
        double cx = 46.0, cy = -18.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 3.0, cx + 3.0, cy + 4.0, cy + 4.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_INTERIOR_POINT_OUTSIDE_CIRCLE_variation1() {
        double cx = -38.0, cy = 24.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 4.0, cx + 4.0, cy + 4.0, cy + 4.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERIOR_VERTICAL_LINE_RECTANGLE_variation1() {
        double cx = 11.0, cy = -42.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 1.0, cx + 1.0, cy - 2.0, cy + 2.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAMETER_LINE_CROSSES_CIRCLE_variation1() {
        double cx = -17.0, cy = 3.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx, cx, cy - 6.0, cy + 6.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TANGENT_VERTICAL_LINE_variation1() {
        double cx = 29.0, cy = 21.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 5.0, cx + 5.0, cy - 2.0, cy + 2.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_RECTANGLE_variation1() {
        double cx = 7.0, cy = -16.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 0.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx - 1.0, cx + 1.0, cy - 1.0, cy + 1.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_FROM_RECTANGLE_variation1() {
        double cx = -44.0, cy = -9.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 0.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx + 1.0, cx + 2.0, cy + 1.0, cy + 2.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1() {
        double cx = 25.0, cy = -34.0;
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(cx, cy), 0.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(cx, cx, cy, cy);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_NONEMPTY_RECTANGLE_variation1() {
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(Double.NaN, Double.NaN), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(-3.0, 4.0, -2.0, 6.0);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONEMPTY_CIRCLE_EMPTY_RECTANGLE_variation1() {
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(12.0, 15.0), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(
                        Double.NaN, Double.NaN, Double.NaN, Double.NaN);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1() {
        org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        CONTEXT.makePoint(Double.NaN, Double.NaN), 5.0, CONTEXT);
        org.locationtech.spatial4j.shape.Rectangle sourceRectangle =
                CONTEXT.makeRectangle(
                        Double.NaN, Double.NaN, Double.NaN, Double.NaN);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        org.locationtech.spatial4j.shape.impl.CircleImpl followCircle =
                (org.locationtech.spatial4j.shape.impl.CircleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Rectangle followRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUp[1];
        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followCircle.relate(followRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
