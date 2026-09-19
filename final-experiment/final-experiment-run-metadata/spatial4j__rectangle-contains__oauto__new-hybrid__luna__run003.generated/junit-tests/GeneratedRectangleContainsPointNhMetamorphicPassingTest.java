import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        SpatialContext context = rectangle.getContext();
        RectangleImpl rotatedRectangle = new RectangleImpl(
                DistanceUtils.normLonDEG(rectangle.getMinX() + 150.0),
                DistanceUtils.normLonDEG(rectangle.getMaxX() + 150.0),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);
        Point rotatedPoint = context.makePoint(
                DistanceUtils.normLonDEG(point.getX() + 150.0),
                point.getY());
        return new Object[]{rotatedRectangle, rotatedPoint};
    }

    @Test
    public void LATITUDE_BELOW_BOX_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -10.0, 20.0, context);
        Point point = context.makePoint(0.0, -10.000001);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_ABOVE_BOX_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(170.0, -20.0);
        Point upperRight = context.makePoint(-150.0, 10.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(175.0, 10.000001);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_INTERIOR_DIRECT_CONTAINS_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl base = new RectangleImpl(-10.0, -10.0, -30.0, 30.0, context);
        RectangleImpl source = new RectangleImpl((Rectangle) base, context);
        Point point = context.makePoint(-10.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_MINX_INCLUSIVE_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl source = new RectangleImpl(-80.0, 20.0, -30.0, 30.0, context);
        Point point = context.makePoint(-80.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_MAXX_INCLUSIVE_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(-80.0, -30.0);
        Point upperRight = context.makePoint(20.0, 30.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(20.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_LONGITUDE_BELOW_DISJOINT_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl base = new RectangleImpl(-80.0, 20.0, -30.0, 30.0, context);
        RectangleImpl source = new RectangleImpl((Rectangle) base, context);
        Point point = context.makePoint(-100.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_LONGITUDE_ABOVE_DISJOINT_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl source = new RectangleImpl(30.0, 30.0, -30.0, 30.0, context);
        Point point = context.makePoint(40.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_ROTATION_CREATES_DATELINE_BOX_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(-20.0, -20.0);
        Point upperRight = context.makePoint(100.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(40.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_ROTATION_CREATES_DATELINE_DISJOINT_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl original = new RectangleImpl(-20.0, 100.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl((Rectangle) original, context);
        Point point = context.makePoint(-60.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_INTERIOR_EAST_SEGMENT_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl source = new RectangleImpl(170.0, -150.0, -25.0, 25.0, context);
        Point point = context.makePoint(175.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_INTERIOR_WEST_SEGMENT_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(170.0, -25.0);
        Point upperRight = context.makePoint(-150.0, 25.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(-175.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_OUTSIDE_CENTRAL_LONGITUDE_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl original = new RectangleImpl(170.0, -150.0, -25.0, 25.0, context);
        RectangleImpl source = new RectangleImpl((Rectangle) original, context);
        Point point = context.makePoint(0.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_MAXX_BOUNDARY_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl source = new RectangleImpl(170.0, -150.0, -25.0, 25.0, context);
        Point point = context.makePoint(-150.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_MINX_BOUNDARY_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(170.0, -25.0);
        Point upperRight = context.makePoint(-150.0, 25.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(170.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_POINT_ABOVE_UNWRAPPED_MAX_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl original = new RectangleImpl(170.0, -150.0, -25.0, 25.0, context);
        RectangleImpl source = new RectangleImpl((Rectangle) original, context);
        Point point = context.makePoint(160.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_ON_LINE_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl source = new RectangleImpl(30.0, 30.0, -20.0, 20.0, context);
        Point point = context.makePoint(30.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_OFF_LINE_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(30.0, -20.0);
        Point upperRight = context.makePoint(30.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(30.000001, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_ON_LINE_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl original = new RectangleImpl(-40.0, 40.0, 12.0, 12.0, context);
        RectangleImpl source = new RectangleImpl((Rectangle) original, context);
        Point point = context.makePoint(0.0, 12.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_OFF_LINE_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, 12.0, 12.0, context);
        Point point = context.makePoint(0.0, 12.000001);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTH_POLE_LATITUDE_BOUNDARY_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(-60.0, -90.0);
        Point upperRight = context.makePoint(60.0, -80.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(0.0, -90.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTH_POLE_LATITUDE_BOUNDARY_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl original = new RectangleImpl(-60.0, 60.0, 80.0, 90.0, context);
        RectangleImpl source = new RectangleImpl((Rectangle) original, context);
        Point point = context.makePoint(0.0, 90.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATION_NORMALIZES_PLUS180_ENDPOINT_1() {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl source = new RectangleImpl(-150.0, 30.0, -10.0, 10.0, context);
        Point point = context.makePoint(30.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEAR_FULL_WIDTH_DATELINE_CONTAINS_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(179.0, -5.0);
        Point upperRight = context.makePoint(-179.0, 5.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(179.5, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POINT_PAIR_CONSTRUCTOR_PATH_1() {
        SpatialContext context = new SpatialContext(true);
        Point lowerLeft = context.makePoint(-70.0, -20.0);
        Point upperRight = context.makePoint(10.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point point = context.makePoint(-30.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGLE_COPY_CONSTRUCTOR_PATH_1() {
        SpatialContext context = new SpatialContext(true);
        Rectangle sourceRectangle = new RectangleImpl(-70.0, 10.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(sourceRectangle, context);
        Point point = context.makePoint(40.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
