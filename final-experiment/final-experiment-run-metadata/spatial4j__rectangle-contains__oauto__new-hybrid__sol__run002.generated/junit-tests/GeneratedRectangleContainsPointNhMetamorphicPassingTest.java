import org.junit.jupiter.api.Test;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        SpatialContext context = rectangle.getContext();
        double rotatedMinX = DistanceUtils.normLonDEG(rectangle.getMinX() + 150.0);
        double rotatedMaxX = DistanceUtils.normLonDEG(rectangle.getMaxX() + 150.0);
        double rotatedPointX = DistanceUtils.normLonDEG(point.getX() + 150.0);

        RectangleImpl followUpRectangle = new RectangleImpl(
                rotatedMinX,
                rotatedMaxX,
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);
        Point followUpPoint = context.makePoint(rotatedPointX, point.getY());

        return new Object[]{followUpRectangle, followUpPoint};
    }

    @Test
    public void LATITUDE_ABOVE_MAXIMUM_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, -10, 10, context);
        Point sourcePoint = context.makePoint(0, 11);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_BELOW_MINIMUM_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, -10, 10, context);
        Point sourcePoint = context.makePoint(0, -11);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_UPPER_BOUNDARY_INCLUDED_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, -10, 10, context);
        Point sourcePoint = context.makePoint(0, 10);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_LOWER_BOUNDARY_INCLUDED_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, -10, 10, context);
        Point sourcePoint = context.makePoint(0, -10);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_HEIGHT_RECTANGLE_MATCH_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, 5, 5, context);
        Point sourcePoint = context.makePoint(0, 5);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_HEIGHT_RECTANGLE_MISS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, 5, 5, context);
        Point sourcePoint = context.makePoint(0, 6);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTH_POLE_BOUNDARY_INCLUDED_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, 80, 90, context);
        Point sourcePoint = context.makePoint(0, 90);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_INCLUDED_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, -90, -80, context);
        Point sourcePoint = context.makePoint(0, -90);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONWRAP_INTERIOR_DIRECT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-100, -50, -10, 10, context);
        Point sourcePoint = context.makePoint(-75, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONWRAP_MIN_LONGITUDE_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-100, -50, -10, 10, context);
        Point sourcePoint = context.makePoint(-100, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONWRAP_MAX_LONGITUDE_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-100, -50, -10, 10, context);
        Point sourcePoint = context.makePoint(-50, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONWRAP_WEST_DISJOINT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-100, -50, -10, 10, context);
        Point sourcePoint = context.makePoint(-101, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONWRAP_EAST_DISJOINT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-100, -50, -10, 10, context);
        Point sourcePoint = context.makePoint(-49, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_ZERO_WIDTH_MATCH_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-60, -60, -10, 10, context);
        Point sourcePoint = context.makePoint(-60, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_ZERO_WIDTH_MISS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-60, -60, -10, 10, context);
        Point sourcePoint = context.makePoint(-59, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATION_CREATES_WRAP_INTERIOR_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(0, 60, -10, 10, context);
        Point sourcePoint = context.makePoint(30, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATION_CREATES_WRAP_MIN_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(0, 60, -10, 10, context);
        Point sourcePoint = context.makePoint(0, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATION_CREATES_WRAP_MAX_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(0, 60, -10, 10, context);
        Point sourcePoint = context.makePoint(60, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATION_CREATES_WRAP_WEST_GAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(0, 60, -10, 10, context);
        Point sourcePoint = context.makePoint(-1, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATION_CREATES_WRAP_EAST_GAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(0, 60, -10, 10, context);
        Point sourcePoint = context.makePoint(61, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTH_ENDPOINTS_NORMALIZE_WITHOUT_WRAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(40, 80, -10, 10, context);
        Point sourcePoint = context.makePoint(60, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WEST_DATELINE_ENDPOINT_DIRECT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-180, -160, -10, 10, context);
        Point sourcePoint = context.makePoint(-180, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WEST_DATELINE_POSITIVE_ALIAS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-180, -160, -10, 10, context);
        Point sourcePoint = context.makePoint(180, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WEST_DATELINE_NEARBY_OUTSIDE_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-180, -160, -10, 10, context);
        Point sourcePoint = context.makePoint(179, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EAST_DATELINE_ENDPOINT_DIRECT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(160, 180, -10, 10, context);
        Point sourcePoint = context.makePoint(180, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EAST_DATELINE_NEGATIVE_ALIAS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(160, 180, -10, 10, context);
        Point sourcePoint = context.makePoint(-180, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EAST_DATELINE_NEARBY_OUTSIDE_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(160, 180, -10, 10, context);
        Point sourcePoint = context.makePoint(-179, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WRAPPING_EAST_INTERIOR_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(170, -170, -10, 10, context);
        Point sourcePoint = context.makePoint(175, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WRAPPING_WEST_INTERIOR_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(170, -170, -10, 10, context);
        Point sourcePoint = context.makePoint(-175, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WRAPPING_EAST_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(170, -170, -10, 10, context);
        Point sourcePoint = context.makePoint(170, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WRAPPING_WEST_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(170, -170, -10, 10, context);
        Point sourcePoint = context.makePoint(-170, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WRAPPING_CENTRAL_GAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(170, -170, -10, 10, context);
        Point sourcePoint = context.makePoint(0, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WRAPPING_JUST_BEFORE_EAST_EDGE_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(170, -170, -10, 10, context);
        Point sourcePoint = context.makePoint(169, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WRAPPING_JUST_AFTER_WEST_EDGE_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(170, -170, -10, 10, context);
        Point sourcePoint = context.makePoint(-169, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BROAD_WRAPPING_CONTAINS_SHIFTED_POINT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(100, 50, -10, 10, context);
        Point sourcePoint = context.makePoint(0, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BROAD_WRAPPING_SMALL_GAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(100, 50, -10, 10, context);
        Point sourcePoint = context.makePoint(75, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEAR_WORLD_WIDTH_INTERIOR_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-180, 179.999, -10, 10, context);
        Point sourcePoint = context.makePoint(0, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEAR_WORLD_EAST_MICRO_GAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-180, 179.999, -10, 10, context);
        Point sourcePoint = context.makePoint(179.9995, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEAR_WORLD_WEST_MICRO_GAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-179.999, 180, -10, 10, context);
        Point sourcePoint = context.makePoint(-179.9995, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_DATELINE_VERTICAL_ALIAS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(180, 180, -10, 10, context);
        Point sourcePoint = context.makePoint(-180, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEGATIVE_DATELINE_VERTICAL_ALIAS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-180, -180, -10, 10, context);
        Point sourcePoint = context.makePoint(180, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_DATELINE_VERTICAL_MISS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(180, 180, -10, 10, context);
        Point sourcePoint = context.makePoint(179, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEGATIVE_DATELINE_VERTICAL_MISS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-180, -180, -10, 10, context);
        Point sourcePoint = context.makePoint(-179, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_VERTICAL_LATITUDE_REJECTION_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(180, 180, -10, 10, context);
        Point sourcePoint = context.makePoint(-180, 11);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTH_POLE_LONGITUDE_DISJOINT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-10, 10, 90, 90, context);
        Point sourcePoint = context.makePoint(100, 90);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTH_POLE_WRAPPING_GAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(170, -170, -90, -90, context);
        Point sourcePoint = context.makePoint(0, -90);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FULL_LATITUDE_SPAN_INTERIOR_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(-20, 20, -90, 90, context);
        Point sourcePoint = context.makePoint(0, 45);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATED_MAX_ENDPOINT_NORMALIZES_TO_NEGATIVE_180_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(0, 30, -10, 10, context);
        Point sourcePoint = context.makePoint(30, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATED_MIN_ENDPOINT_NORMALIZES_TO_NEGATIVE_180_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(30, 60, -10, 10, context);
        Point sourcePoint = context.makePoint(30, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATED_INTERIOR_POINT_NORMALIZES_TO_NEGATIVE_180_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle = new RectangleImpl(20, 40, -10, 10, context);
        Point sourcePoint = context.makePoint(30, 0);
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
