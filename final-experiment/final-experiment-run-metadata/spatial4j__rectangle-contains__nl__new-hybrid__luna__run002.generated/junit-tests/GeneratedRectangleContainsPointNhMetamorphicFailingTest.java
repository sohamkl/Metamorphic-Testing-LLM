import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static RectangleImpl rectangle(SpatialContext ctx,
                                           double minX, double maxX,
                                           double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, ctx);
    }

    private static Point point(SpatialContext ctx, double x, double y) {
        return new PointImpl(x, y, ctx);
    }

    private static double rotateLongitude(double longitude) {
        double rotated = longitude + 150.0;
        while (rotated > 180.0) {
            rotated -= 360.0;
        }
        while (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static RectangleImpl generateFollowUp(RectangleImpl source) {
        SpatialContext ctx = source.getContext();
        return new RectangleImpl(
                rotateLongitude(source.getMinX()),
                rotateLongitude(source.getMaxX()),
                source.getMinY(),
                source.getMaxY(),
                ctx);
    }

    private static Point generateFollowUp(Point source) {
        return new PointImpl(
                rotateLongitude(source.getX()),
                source.getY(),
                source.getContext());
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(
            RectangleImpl sourceRectangle,
            Point sourcePoint) {
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        RectangleImpl followUpRectangle = generateFollowUp(sourceRectangle);
        Point followUpPoint = generateFollowUp(sourcePoint);
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void GEO_FULL_RAW_WIDTH_1() {
        SpatialContext ctx = new SpatialContext(true);
        RectangleImpl rectangle = rectangle(ctx, -180, 180, -30, 30);
        Point point = point(ctx, 0, 0);
        assertMetamorphicRelationFor(rectangle, point);
    }

    @Test
    public void NON_GEO_INTERIOR_1() {
        SpatialContext ctx = new SpatialContext(false);
        RectangleImpl rectangle = rectangle(ctx, -100, 100, -30, 30);
        Point point = point(ctx, 25, 0);
        assertMetamorphicRelationFor(rectangle, point);
    }

    @Test
    public void NON_GEO_DATELINE_ORDERING_IS_NOT_WRAP_1() {
        SpatialContext ctx = new SpatialContext(false);
        RectangleImpl rectangle = rectangle(ctx, 170, -170, -20, 20);
        Point point = point(ctx, 175, 0);
        assertMetamorphicRelationFor(rectangle, point);
    }

    @Test
    public void NON_GEO_LONGITUDE_BOUNDARY_1() {
        SpatialContext ctx = new SpatialContext(false);
        RectangleImpl rectangle = rectangle(ctx, -100, 100, -30, 30);
        Point point = point(ctx, 100, 30);
        assertMetamorphicRelationFor(rectangle, point);
    }
}
