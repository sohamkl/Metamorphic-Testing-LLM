import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final SpatialContext GEO = new SpatialContext(true);

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        RectangleImpl rectangle = new RectangleImpl(minX, maxX, minY, maxY, GEO);
        Point point = new PointImpl(pointX, pointY, GEO);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangle, point);
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl original = source.receiver();
        Point originalPoint = source.arg0();

        RectangleImpl rotatedRectangle = new RectangleImpl(
                rotateLongitude(original.getMinX()),
                rotateLongitude(original.getMaxX()),
                original.getMinY(),
                original.getMaxY(),
                GEO);
        Point rotatedPoint = new PointImpl(
                rotateLongitude(originalPoint.getX()),
                originalPoint.getY(),
                GEO);

        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rotatedRectangle, rotatedPoint);
    }

    private static double rotateLongitude(double longitude) {
        double rotated = (longitude + 150.0) % 360.0;
        if (rotated >= 180.0) {
            rotated -= 360.0;
        } else if (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        SpatialRelation sourceOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

}
