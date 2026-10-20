import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static SpatialContext geo() {
        return new SpatialContext(true);
    }

    private static Point point(SpatialContext context, double x, double y) {
        return context.makePoint(x, y);
    }

    @Test
    public void testORDINARY_INTERIOR_SHORT_CIRCUIT_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(context, -5.0, 3.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testORDINARY_MIN_LONGITUDE_BOUNDARY_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, -40.0, -20.0);
        Point upperRight = point(context, 40.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, -40.0, 0.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testORDINARY_MAX_LONGITUDE_BOUNDARY_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(-180.0, 180.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, 180.0, 0.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testORDINARY_LONGITUDE_BELOW_AND_OUTSIDE_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(20.0, 20.0, -20.0, 20.0, context);
        Point sourcePoint = point(context, 0.0, 20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testORDINARY_LONGITUDE_ABOVE_AND_OUTSIDE_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, -40.0, -20.0);
        Point upperRight = point(context, 40.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, 100.0, 20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testWEST_DATELINE_ENDPOINT_ADJUSTMENT_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(-180.0, -100.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, 180.0, -20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testEAST_DATELINE_ENDPOINT_ADJUSTMENT_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(100.0, 180.0, -20.0, 20.0, context);
        Point sourcePoint = point(context, 150.0, -20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDATELINE_WRAP_INTERIOR_EAST_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, 170.0, -20.0);
        Point upperRight = point(context, -170.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, 175.0, 0.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDATELINE_WRAP_INTERIOR_WEST_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, -175.0, 20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDATELINE_WRAP_OUTSIDE_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        Point sourcePoint = point(context, 0.0, 20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDATELINE_WRAP_ENDPOINT_ANTIMERIDIAN_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, 170.0, -20.0);
        Point upperRight = point(context, -170.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, -170.0, -20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDATELINE_WRAP_ENDPOINT_170_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, 170.0, -20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLATITUDE_BELOW_EARLY_DISJOINT_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -20.0, 20.0, context);
        Point sourcePoint = point(context, 0.0, -20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLATITUDE_ABOVE_EARLY_DISJOINT_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, -40.0, -20.0);
        Point upperRight = point(context, 40.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, -40.0, 20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLATITUDE_MIN_BOUNDARY_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(-180.0, 180.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, 180.0, 20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLATITUDE_MAX_BOUNDARY_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(20.0, 20.0, -20.0, 20.0, context);
        Point sourcePoint = point(context, 0.0, -20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_WIDTH_VERTICAL_LINE_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, 20.0, -20.0);
        Point upperRight = point(context, 20.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, 20.000001, -20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_WIDTH_LINE_OFF_LONGITUDE_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(20.0, 20.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, 20.000001, 0.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_HEIGHT_HORIZONTAL_LINE_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, 10.0, 10.0, context);
        Point sourcePoint = point(context, 0.0, 10.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testWORLD_LONGITUDE_RANGE_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, -180.0, -20.0);
        Point upperRight = point(context, 180.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, -180.0, 0.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testFOLLOWUP_CREATES_DATELINE_WRAP_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(-20.0, 60.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, 60.0, -20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testFOLLOWUP_LANDS_ON_NEGATIVE_180_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(30.0, 60.0, -20.0, 20.0, context);
        Point sourcePoint = point(context, 45.0, -20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOINT_CONSTRUCTOR_PATH_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, -30.0, -10.0);
        Point upperRight = point(context, 30.0, 10.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, 180.0, 0.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testRECTANGLE_COPY_PATH_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, -160.0, 20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDATELINE_ROTATION_REMAINS_WRAPPED_1() {
        SpatialContext context = geo();
        RectangleImpl source = new RectangleImpl(100.0, -100.0, -30.0, 30.0, context);
        Point sourcePoint = point(context, 150.0, 30.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDATELINE_WEST_SIDE_OUTSIDE_1() {
        SpatialContext context = geo();
        Point lowerLeft = point(context, 170.0, -20.0);
        Point upperRight = point(context, -170.0, 20.0);
        RectangleImpl source = new RectangleImpl(lowerLeft, upperRight, context);
        Point sourcePoint = point(context, 170.0, -20.000001);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDATELINE_EAST_SIDE_OUTSIDE_1() {
        SpatialContext context = geo();
        RectangleImpl original = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        RectangleImpl source = new RectangleImpl(original, context);
        Point sourcePoint = point(context, -170.0, -20.0);

        SpatialRelation sourceOutput = source.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
