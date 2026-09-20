import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private void assertMetamorphicRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("Simultaneously rotating the rectangle and point must preserve containment relation.");
        }
    }

    @Test
    void ordinaryInteriorRotatesToWrap_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(30.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryMinXBoundary_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(20.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryMaxXBoundary_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(40.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryMinYBoundary_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(30.0, -10.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryMaxYBoundary_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(30.0, 10.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void latitudeAboveShortCircuit_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(30.0, 10.1);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void latitudeBelowShortCircuit_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(30.0, -10.1);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryWestOutsideShiftedDisjoint_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(10.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryEastOutsideShiftedDisjoint_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(50.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void westDatelineAliasShiftedContains_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, 180.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(-180.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void eastDatelineAliasShiftedContains_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-180.0, -170.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(180.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void wrappingEastLobeInterior_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(175.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void wrappingWestLobeInterior_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(-175.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void wrappingGapDisjoint_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(0.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void wrappingMinXBoundary_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(170.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void wrappingMaxXBoundary_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(-170.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryZeroWidthMatch_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(30.0, 30.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(30.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryZeroWidthMiss_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(30.0, 30.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(31.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void zeroHeightMatch_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, 0.0, 0.0, ctx);
        Point point = ctx.makePoint(30.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void zeroHeightMiss_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, 0.0, 0.0, ctx);
        Point point = ctx.makePoint(30.0, 0.1);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void datelineZeroWidthAlias_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(180.0, -180.0, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(-180.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void nearFullWorldNonFullWidth_variation1() {
        SpatialContext ctx = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-180.0, 179.999, -10.0, 10.0, ctx);
        Point point = ctx.makePoint(0.0, 0.0);
        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        SpatialRelation followUpOutput = ((RectangleImpl) followUp[0]).relate((Point) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
