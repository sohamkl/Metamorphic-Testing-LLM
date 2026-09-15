import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicTest {

    @Test
    void LATITUDE_BELOW_EARLY_DISJOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(0.0, -20.000001);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATITUDE_AT_MIN_BOUNDARY_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(0.0, -20.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATITUDE_AT_MAX_BOUNDARY_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(0.0, 20.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATITUDE_ABOVE_EARLY_DISJOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(0.0, 20.000001);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_DIRECT_INTERIOR_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(25.0, 5.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_MINX_ENDPOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(-100.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_MAXX_ENDPOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(100.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_LEFT_SHIFT_TO_CONTAIN_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(100.0, 180.0, -20.0, 20.0, context);
        Point point = context.makePoint(-180.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_RIGHT_SHIFT_TO_CONTAIN_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-180.0, -100.0, -20.0, 20.0, context);
        Point point = context.makePoint(180.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_LEFT_SHIFT_REMAINS_OUTSIDE_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(-150.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_RIGHT_SHIFT_REMAINS_OUTSIDE_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(150.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WRAPPING_DIRECT_INTERIOR_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        Point point = context.makePoint(175.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WRAPPING_NEGATIVE_SIDE_SHIFT_TO_CONTAIN_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        Point point = context.makePoint(-175.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WRAPPING_GAP_DISJOINT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        Point point = context.makePoint(0.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WRAPPING_ENDPOINTS_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        Point point = context.makePoint(170.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WRAPPING_ENDPOINTS_2() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        Point point = context.makePoint(-170.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROTATION_CREATES_DATELINE_WRAP_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(0.0, 100.0, -20.0, 20.0, context);
        Point point = context.makePoint(50.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROTATION_REMOVES_DATELINE_WRAP_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, context);
        Point point = context.makePoint(-175.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_CONTAINMENT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(30.0, 30.0, -20.0, 20.0, context);
        Point point = context.makePoint(30.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_HEIGHT_LATITUDE_CONTAINMENT_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-100.0, 100.0, 0.0, 0.0, context);
        Point point = context.makePoint(0.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NORMALIZED_DATELINE_BOUNDARY_1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl source = new RectangleImpl(-180.0, -100.0, -20.0, 20.0, context);
        Point point = context.makePoint(180.0, 0.0);

        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        SpatialRelation followUpOutput =
                ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
