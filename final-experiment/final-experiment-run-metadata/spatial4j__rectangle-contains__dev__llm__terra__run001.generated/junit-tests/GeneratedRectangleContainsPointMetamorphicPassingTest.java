import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicPassingTest {

    private static final SpatialContext GEO = SpatialContext.GEO;

    private void check(RectangleImpl rectangle, Point point) {
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ordinaryInteriorRotatesToDatelineWrap_variation1() {
        check(new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO), GEO.makePoint(30.0, 0.0));
    }

    @Test
    public void ordinaryMinXBoundaryRotatesToWrap_variation1() {
        check(new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO), GEO.makePoint(20.0, 0.0));
    }

    @Test
    public void ordinaryMaxXBoundaryRotatesToWrap_variation1() {
        check(new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO), GEO.makePoint(40.0, 0.0));
    }

    @Test
    public void ordinaryMinYBoundary_variation1() {
        check(new RectangleImpl(-40.0, -20.0, -10.0, 10.0, GEO), GEO.makePoint(-30.0, -10.0));
    }

    @Test
    public void ordinaryMaxYBoundary_variation1() {
        check(new RectangleImpl(-40.0, -20.0, -10.0, 10.0, GEO), GEO.makePoint(-30.0, 10.0));
    }

    @Test
    public void latitudeBelowMinimum_variation1() {
        check(new RectangleImpl(-40.0, -20.0, -10.0, 10.0, GEO), GEO.makePoint(-30.0, -10.000001));
    }

    @Test
    public void latitudeAboveMaximum_variation1() {
        check(new RectangleImpl(-40.0, -20.0, -10.0, 10.0, GEO), GEO.makePoint(-30.0, 10.000001));
    }

    @Test
    public void ordinaryWesternLongitudeShiftDisjoint_variation1() {
        check(new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO), GEO.makePoint(-170.0, 0.0));
    }

    @Test
    public void ordinaryEasternLongitudeShiftDisjoint_variation1() {
        check(new RectangleImpl(-40.0, -20.0, -10.0, 10.0, GEO), GEO.makePoint(170.0, 0.0));
    }

    @Test
    public void ordinaryLongitudeOutsideWithoutLatitudeFailure_variation1() {
        check(new RectangleImpl(-100.0, -80.0, -20.0, 20.0, GEO), GEO.makePoint(-60.0, 0.0));
    }

    @Test
    public void datelineWrapEasternLobeInterior_variation1() {
        check(new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO), GEO.makePoint(175.0, 0.0));
    }

    @Test
    public void datelineWrapWesternLobeShiftedContainment_variation1() {
        check(new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO), GEO.makePoint(-175.0, 0.0));
    }

    @Test
    public void datelineWrapMinXBoundary_variation1() {
        check(new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO), GEO.makePoint(170.0, 0.0));
    }

    @Test
    public void datelineWrapMaxXBoundary_variation1() {
        check(new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO), GEO.makePoint(-170.0, 0.0));
    }

    @Test
    public void datelineWrapCentralGapDisjoint_variation1() {
        check(new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO), GEO.makePoint(0.0, 0.0));
    }

    @Test
    public void datelineWrapRotatesToOrdinary_variation1() {
        check(new RectangleImpl(170.0, -170.0, -20.0, 20.0, GEO), GEO.makePoint(-175.0, 5.0));
    }

    @Test
    public void zeroWidthVerticalLineContains_variation1() {
        check(new RectangleImpl(25.0, 25.0, -10.0, 10.0, GEO), GEO.makePoint(25.0, 0.0));
    }

    @Test
    public void zeroWidthVerticalLineDisjoint_variation1() {
        check(new RectangleImpl(25.0, 25.0, -10.0, 10.0, GEO), GEO.makePoint(25.000001, 0.0));
    }

    @Test
    public void zeroHeightHorizontalLineContains_variation1() {
        check(new RectangleImpl(-20.0, 20.0, 5.0, 5.0, GEO), GEO.makePoint(0.0, 5.0));
    }

    @Test
    public void zeroHeightHorizontalLineDisjoint_variation1() {
        check(new RectangleImpl(-20.0, 20.0, 5.0, 5.0, GEO), GEO.makePoint(0.0, 5.000001));
    }

    @Test
    public void pointRectangleDegenerateContains_variation1() {
        check(new RectangleImpl(-30.0, -30.0, 12.0, 12.0, GEO), GEO.makePoint(-30.0, 12.0));
    }

    @Test
    public void pointRectangleDegenerateDisjoint_variation1() {
        check(new RectangleImpl(-30.0, -30.0, 12.0, 12.0, GEO), GEO.makePoint(-29.999999, 12.0));
    }

    @Test
    public void southPoleLatitudeBoundary_variation1() {
        check(new RectangleImpl(-30.0, 30.0, -90.0, -70.0, GEO), GEO.makePoint(0.0, -90.0));
    }

    @Test
    public void northPoleLatitudeBoundary_variation1() {
        check(new RectangleImpl(-30.0, 30.0, 70.0, 90.0, GEO), GEO.makePoint(0.0, 90.0));
    }

    @Test
    public void fullWorldLongitudeLatitudeRejection_variation1() {
        check(new RectangleImpl(-180.0, 180.0, -10.0, 10.0, GEO), GEO.makePoint(0.0, 10.000001));
    }

    @Test
    public void negativeDatelineBoundaryMinus180_variation1() {
        check(new RectangleImpl(-180.0, -170.0, -10.0, 10.0, GEO), GEO.makePoint(-180.0, 0.0));
    }

    @Test
    public void positiveDatelineBoundaryPlus180_variation1() {
        check(new RectangleImpl(170.0, 180.0, -10.0, 10.0, GEO), GEO.makePoint(180.0, 0.0));
    }

    @Test
    public void rotationMinEndpointNormalizesToMinus180_variation1() {
        check(new RectangleImpl(30.0, 31.0, -5.0, 5.0, GEO), GEO.makePoint(30.5, 0.0));
    }

    @Test
    public void rotationMaxEndpointNormalizesToMinus180_variation1() {
        check(new RectangleImpl(29.0, 30.0, -5.0, 5.0, GEO), GEO.makePoint(29.5, 0.0));
    }

    @Test
    public void narrowDatelineWrapContains_variation1() {
        check(new RectangleImpl(179.0, -179.0, -1.0, 1.0, GEO), GEO.makePoint(-180.0, 0.0));
    }

    @Test
    public void narrowDatelineWrapNearbyGapDisjoint_variation1() {
        check(new RectangleImpl(179.0, -179.0, -1.0, 1.0, GEO), GEO.makePoint(-178.999999, 0.0));
    }

    @Test
    public void wideNonwrappingRectangleContains_variation1() {
        check(new RectangleImpl(-170.0, 170.0, -45.0, 45.0, GEO), GEO.makePoint(169.999999, 0.0));
    }

    @Test
    public void wideNonwrappingGapDisjoint_variation1() {
        check(new RectangleImpl(-170.0, 170.0, -45.0, 45.0, GEO), GEO.makePoint(180.0, 0.0));
    }
}
