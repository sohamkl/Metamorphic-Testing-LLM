import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final class Fixture {
        final RectangleImpl rectangle;
        final Point point;

        Fixture(RectangleImpl rectangle, Point point) {
            this.rectangle = rectangle;
            this.point = point;
        }
    }

    private Fixture source(double minX, double maxX, double minY, double maxY,
                           double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        return new Fixture(
            new RectangleImpl(minX, maxX, minY, maxY, context),
            new PointImpl(pointX, pointY, context)
        );
    }

    private Fixture generateFollowUp(Fixture source) {
        SpatialContext context = new SpatialContext(true);
        return new Fixture(
            new RectangleImpl(
                rotateEast150(source.rectangle.getMinX()),
                rotateEast150(source.rectangle.getMaxX()),
                source.rectangle.getMinY(),
                source.rectangle.getMaxY(),
                context
            ),
            new PointImpl(
                rotateEast150(source.point.getX()),
                source.point.getY(),
                context
            )
        );
    }

    private double rotateEast150(double longitude) {
        double rotated = (longitude + 150.0) % 360.0;
        if (rotated < -180.0) {
            rotated += 360.0;
        }
        if (rotated >= 180.0) {
            rotated -= 360.0;
        }
        return rotated;
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                           SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private void check(double minX, double maxX, double minY, double maxY,
                       double pointX, double pointY, SpatialRelation expectedSourceOutput) {
        Fixture source = source(minX, maxX, minY, maxY, pointX, pointY);
        Fixture followUp = generateFollowUp(source);
        SpatialRelation sourceOutput = source.rectangle.relate(source.point);
        SpatialRelation followUpOutput = followUp.rectangle.relate(followUp.point);
        Assertions.assertEquals(expectedSourceOutput, sourceOutput);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
