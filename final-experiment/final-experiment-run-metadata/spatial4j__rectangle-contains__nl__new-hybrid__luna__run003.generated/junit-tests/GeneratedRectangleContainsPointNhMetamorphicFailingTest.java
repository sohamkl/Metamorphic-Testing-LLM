import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final SpatialContext GEO = new SpatialContext(true);
    private static final double ROTATION = 150.0;

    private static final class Source {
        final RectangleImpl rectangle;
        final Point point;

        Source(RectangleImpl rectangle, Point point) {
            this.rectangle = rectangle;
            this.point = point;
        }
    }

    private static Point point(double x, double y) {
        return new PointImpl(x, y, GEO);
    }

    private static RectangleImpl direct(double minX, double maxX,
                                        double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, GEO);
    }

    private static RectangleImpl endpointRectangle(double minX, double maxX,
                                                   double minY, double maxY) {
        return new RectangleImpl(point(minX, minY), point(maxX, maxY), GEO);
    }

    private static RectangleImpl copiedRectangle(double minX, double maxX,
                                                 double minY, double maxY) {
        Rectangle source = new RectangleImpl(minX, maxX, minY, maxY, GEO);
        return new RectangleImpl(source, GEO);
    }

    private static double rotate(double longitude) {
        double rotated = longitude + ROTATION;
        while (rotated > 180.0) {
            rotated -= 360.0;
        }
        while (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static Source generateFollowUp(Source source) {
        RectangleImpl rectangle = source.rectangle;
        RectangleImpl rotatedRectangle = direct(
                rotate(rectangle.getMinX()),
                rotate(rectangle.getMaxX()),
                rectangle.getMinY(),
                rectangle.getMaxY());
        Point rotatedPoint = point(
                rotate(source.point.getX()),
                source.point.getY());
        return new Source(rotatedRectangle, rotatedPoint);
    }

    private static void assertMetamorphicRelation(Source source) {
        SpatialRelation sourceOutput = source.rectangle.relate(source.point);
        Source followUp = generateFollowUp(source);
        SpatialRelation followUpOutput =
                followUp.rectangle.relate(followUp.point);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                                  SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    void FULL_WORLD_LONGITUDE_SENTINEL_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(-180, 180, -45, 45), point(0, 0)));
    }
}
