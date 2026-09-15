import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicPassingTest {

    private void exercise(
            int slot,
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {

        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.RectangleImpl) sourceRectangle)
                        .relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.RectangleImpl) followUpRectangle)
                        .relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_INTERIOR_REMAINS_ORDINARY_variation1_westernRectangle() {
        exercise(1, -100.0, -60.0, -20.0, 20.0, -80.0, 0.0);
    }

    @Test
    public void ORDINARY_INTERIOR_REMAINS_ORDINARY_variation2_easternRectangle() {
        exercise(2, 40.0, 80.0, -20.0, 20.0, 60.0, 0.0);
    }

    @Test
    public void ORDINARY_INTERIOR_ROTATES_TO_WRAPPED_variation1_wideRectangle() {
        exercise(3, 0.0, 60.0, -20.0, 20.0, 20.0, 0.0);
    }

    @Test
    public void ORDINARY_INTERIOR_ROTATES_TO_WRAPPED_variation2_narrowRectangle() {
        exercise(4, 20.0, 40.0, -20.0, 20.0, 35.0, 0.0);
    }

    @Test
    public void WRAPPED_POSITIVE_LONGITUDE_LOBE_variation1_nearDateline() {
        exercise(5, 170.0, -170.0, -20.0, 20.0, 175.0, 0.0);
    }

    @Test
    public void WRAPPED_POSITIVE_LONGITUDE_LOBE_variation2_wideWrappedArc() {
        exercise(6, 20.0, -20.0, -20.0, 20.0, 30.0, 0.0);
    }

    @Test
    public void WRAPPED_NEGATIVE_LONGITUDE_LOBE_variation1_nearDateline() {
        exercise(7, 170.0, -170.0, -20.0, 20.0, -175.0, 0.0);
    }

    @Test
    public void WRAPPED_NEGATIVE_LONGITUDE_LOBE_variation2_wideWrappedArc() {
        exercise(8, 100.0, -100.0, -20.0, 20.0, -120.0, 0.0);
    }

    @Test
    public void WRAPPED_EXCLUDED_MIDDLE_GAP_variation1_narrowWrappedArc() {
        exercise(9, 170.0, -170.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    public void WRAPPED_EXCLUDED_MIDDLE_GAP_variation2_widerWrappedArc() {
        exercise(10, 100.0, -100.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    public void WRAPPED_SOURCE_ROTATES_TO_ORDINARY_variation1_datelineLobe() {
        exercise(11, 170.0, -170.0, -20.0, 20.0, 175.0, 0.0);
    }

    @Test
    public void WRAPPED_SOURCE_ROTATES_TO_ORDINARY_variation2_wideWrappedSource() {
        exercise(12, 100.0, -100.0, -20.0, 20.0, 150.0, 0.0);
    }

    @Test
    public void WIDE_ORDINARY_INTERIOR_ROTATES_TO_WRAPPED_variation1_centralPoint() {
        exercise(13, -170.0, 170.0, -45.0, 45.0, 0.0, 0.0);
    }

    @Test
    public void WIDE_ORDINARY_NARROW_OUTSIDE_ARC_variation1_datelineGap() {
        exercise(14, -170.0, 170.0, -45.0, 45.0, 175.0, 0.0);
    }

    @Test
    public void LATITUDE_ABOVE_MAXIMUM_variation1_ordinaryRectangle() {
        exercise(15, -40.0, 40.0, -10.0, 10.0, 0.0, 11.0);
    }

    @Test
    public void LATITUDE_ABOVE_MAXIMUM_variation2_wrappedRectangle() {
        exercise(16, 170.0, -170.0, -10.0, 10.0, 175.0, 11.0);
    }

    @Test
    public void LATITUDE_BELOW_MINIMUM_variation1_ordinaryRectangle() {
        exercise(17, -40.0, 40.0, -10.0, 10.0, 0.0, -11.0);
    }

    @Test
    public void LATITUDE_BELOW_MINIMUM_variation2_wrappedRectangle() {
        exercise(18, 170.0, -170.0, -10.0, 10.0, -175.0, -11.0);
    }

    @Test
    public void LATITUDE_ON_MINIMUM_EDGE_variation1_ordinaryRectangle() {
        exercise(19, -40.0, 40.0, -10.0, 10.0, 0.0, -10.0);
    }

    @Test
    public void LATITUDE_ON_MINIMUM_EDGE_variation2_wrappedRectangle() {
        exercise(20, 170.0, -170.0, -10.0, 10.0, -175.0, -10.0);
    }

    @Test
    public void LATITUDE_ON_MAXIMUM_EDGE_variation1_ordinaryRectangle() {
        exercise(21, -40.0, 40.0, -10.0, 10.0, 0.0, 10.0);
    }

    @Test
    public void LATITUDE_ON_MAXIMUM_EDGE_variation2_wrappedRectangle() {
        exercise(22, 170.0, -170.0, -10.0, 10.0, 175.0, 10.0);
    }

    @Test
    public void ZERO_HEIGHT_RECTANGLE_HIT_variation1_exactLatitude() {
        exercise(23, -20.0, 20.0, 5.0, 5.0, 0.0, 5.0);
    }

    @Test
    public void ZERO_HEIGHT_RECTANGLE_MISS_variation1_justAbove() {
        exercise(24, -20.0, 20.0, 5.0, 5.0, 0.0, 5.000001);
    }

    @Test
    public void ZERO_HEIGHT_RECTANGLE_MISS_variation2_justBelow() {
        exercise(25, -20.0, 20.0, 5.0, 5.0, 0.0, 4.999999);
    }

    @Test
    public void NORTH_POLE_LATITUDE_BOUNDARY_variation1_upperPole() {
        exercise(26, -30.0, 30.0, 80.0, 90.0, 0.0, 90.0);
    }

    @Test
    public void SOUTH_POLE_LATITUDE_BOUNDARY_variation1_lowerPole() {
        exercise(27, -30.0, 30.0, -90.0, -80.0, 0.0, -90.0);
    }

    @Test
    public void ORDINARY_MIN_LONGITUDE_EDGE_variation1_negativeMinimum() {
        exercise(28, -40.0, 20.0, -10.0, 10.0, -40.0, 0.0);
    }

    @Test
    public void ORDINARY_MIN_LONGITUDE_EDGE_variation2_zeroMinimum() {
        exercise(29, 0.0, 60.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void ORDINARY_MAX_LONGITUDE_EDGE_variation1_positiveMaximum() {
        exercise(30, -40.0, 20.0, -10.0, 10.0, 20.0, 0.0);
    }

    @Test
    public void ORDINARY_MAX_LONGITUDE_EDGE_variation2_rotationCrossingMaximum() {
        exercise(31, 0.0, 60.0, -10.0, 10.0, 60.0, 0.0);
    }

    @Test
    public void ORDINARY_STRICTLY_LEFT_variation1_shiftPlus360() {
        exercise(32, 10.0, 20.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void ORDINARY_STRICTLY_RIGHT_variation1_shiftMinus360() {
        exercise(33, -20.0, -10.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void POSITIVE_180_ALIASES_NEGATIVE_180_EDGE_variation1_aliasMinimum() {
        exercise(34, -180.0, -160.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void NEGATIVE_180_ALIASES_POSITIVE_180_EDGE_variation1_aliasMaximum() {
        exercise(35, 160.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void WRAPPED_POSITIVE_BOUNDARY_variation1_inclusiveMinimum() {
        exercise(36, 170.0, -170.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    public void WRAPPED_NEGATIVE_BOUNDARY_variation1_inclusiveMaximum() {
        exercise(37, 170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    public void WRAPPED_JUST_INSIDE_GAP_variation1_belowPositiveBoundary() {
        exercise(38, 170.0, -170.0, -10.0, 10.0, 169.0, 0.0);
    }

    @Test
    public void WRAPPED_JUST_INSIDE_GAP_variation2_aboveNegativeBoundary() {
        exercise(39, 170.0, -170.0, -10.0, 10.0, -169.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_ORDINARY_HIT_variation1_exactLongitude() {
        exercise(40, 25.0, 25.0, -10.0, 10.0, 25.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_ORDINARY_MISS_variation1_lowerLongitude() {
        exercise(41, 25.0, 25.0, -10.0, 10.0, 24.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_ORDINARY_MISS_variation2_upperLongitude() {
        exercise(42, 25.0, 25.0, -10.0, 10.0, 26.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_DATELINE_ALIAS_HIT_variation1_positiveRectangle() {
        exercise(43, 180.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_DATELINE_ALIAS_HIT_variation2_negativeRectangle() {
        exercise(44, -180.0, -180.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void ROTATION_NORMALIZATION_CUT_BOUNDARY_variation1_sourceMaximum() {
        exercise(45, 0.0, 30.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void ROTATION_NORMALIZATION_CUT_BOUNDARY_variation2_sourceMinimum() {
        exercise(46, 30.0, 60.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void ORDINARY_RECTANGLE_CORNERS_variation1_lowerLeft() {
        exercise(47, -40.0, 20.0, -10.0, 10.0, -40.0, -10.0);
    }

    @Test
    public void ORDINARY_RECTANGLE_CORNERS_variation2_upperRight() {
        exercise(48, -40.0, 20.0, -10.0, 10.0, 20.0, 10.0);
    }

    @Test
    public void LONGITUDE_JUST_INSIDE_EDGE_variation1_minimumSide() {
        exercise(49, -20.0, 20.0, -10.0, 10.0, -19.999999, 0.0);
    }

    @Test
    public void LONGITUDE_JUST_OUTSIDE_EDGE_variation1_minimumSide() {
        exercise(50, -20.0, 20.0, -10.0, 10.0, -20.000001, 0.0);
    }
}
