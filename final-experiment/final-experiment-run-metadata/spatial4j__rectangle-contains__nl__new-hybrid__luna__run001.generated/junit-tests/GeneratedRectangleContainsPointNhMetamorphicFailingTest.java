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

    private static double rotate(double longitude) {
        double rotated = longitude + 150.0;
        if (rotated > 180.0) {
            rotated -= 360.0;
        } else if (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static Point point(double x, double y) {
        return new PointImpl(x, y, GEO);
    }

    private static RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY, int constructorPath) {
        if (constructorPath == 1) {
            return new RectangleImpl(
                    point(minX, minY), point(maxX, maxY), GEO);
        }
        if (constructorPath == 2) {
            Rectangle source = new RectangleImpl(minX, maxX, minY, maxY, GEO);
            return new RectangleImpl(source, GEO);
        }
        return new RectangleImpl(minX, maxX, minY, maxY, GEO);
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY, int constructorPath) {
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rectangle(minX, maxX, minY, maxY, constructorPath),
                point(pointX, pointY));
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl rectangle = source.receiver();
        Point originalPoint = source.arg0();
        RectangleImpl rotatedRectangle = new RectangleImpl(
                rotate(rectangle.getMinX()),
                rotate(rectangle.getMaxX()),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                GEO);
        Point rotatedPoint = point(
                rotate(originalPoint.getX()), originalPoint.getY());
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rotatedRectangle, rotatedPoint);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        SpatialRelation sourceOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(
                        generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

}
