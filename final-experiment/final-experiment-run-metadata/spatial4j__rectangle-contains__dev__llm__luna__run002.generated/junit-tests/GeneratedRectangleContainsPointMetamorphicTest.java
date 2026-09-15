import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicTest {

    @Test
    public void INTERIOR_NON_WRAPPING_DIRECT_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(0.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_BELOW_EARLY_DISJOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(0.0, -11.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_ABOVE_EARLY_DISJOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(0.0, 11.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_MIN_BOUNDARY_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(0.0, -10.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_MAX_BOUNDARY_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(0.0, 10.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LONGITUDE_MIN_BOUNDARY_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(-20.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LONGITUDE_MAX_BOUNDARY_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(20.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_WRAPPING_LONGITUDE_BELOW_MIN_SHIFT_DISJOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(-30.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_WRAPPING_LONGITUDE_ABOVE_MAX_SHIFT_DISJOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(30.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_INTERIOR_DIRECT_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(175.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_LOW_SIDE_SHIFT_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(-175.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_HIGH_SIDE_SHIFT_DISJOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(160.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_ENDPOINT_BOUNDARIES_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(170.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATELINE_ENDPOINT_BOUNDARIES_CONTAINS_2() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(-170.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATION_CREATES_DATELINE_WRAPPING_FOLLOWUP_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(0.0, 60.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(30.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROTATION_UNWRAPS_SOURCE_DATELINE_TO_NON_WRAPPING_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(-175.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FULL_LONGITUDE_INTERVAL_CONTAINS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-180.0, 180.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(179.999999, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_LONGITUDE_POINT_BOX_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(10.0, 10.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(10.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_MIN_X_NAN_SENTINEL_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(Double.NaN, 10.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(0.0, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NAN_POINT_LONGITUDE_SENTINEL_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(Double.NaN, 0.0);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NAN_POINT_LATITUDE_SENTINEL_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, context);
        Point sourcePoint = context.makePoint(30.0, Double.NaN);

        SpatialRelation sourceOutput = org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(source, sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
