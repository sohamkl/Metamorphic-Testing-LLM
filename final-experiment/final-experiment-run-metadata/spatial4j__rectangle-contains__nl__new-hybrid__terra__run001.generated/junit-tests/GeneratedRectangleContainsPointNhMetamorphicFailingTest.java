import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final double ROTATION_DEGREES = 150.0;

    private MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point point = new PointImpl(pointX, pointY, context);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangle, point);
    }

    private MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl rectangle = source.receiver();
        Point point = source.arg0();
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rotatedRectangle = new RectangleImpl(
                rotateLongitude(rectangle.getMinX()),
                rotateLongitude(rectangle.getMaxX()),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);
        Point rotatedPoint = new PointImpl(
                rotateLongitude(point.getX()),
                point.getY(),
                context);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rotatedRectangle, rotatedPoint);
    }

    private double rotateLongitude(double longitude) {
        double rotated = longitude + ROTATION_DEGREES;
        while (rotated > 180.0) {
            rotated -= 360.0;
        }
        while (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        SpatialRelation sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertCase(double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        assertMetamorphicRelationFor(source(minX, maxX, minY, maxY, pointX, pointY));
    }

}
