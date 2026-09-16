import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final double ROTATION_DEGREES = 150.0;

    private MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);
        Point point = context.makePoint(pointX, pointY);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rectangle, point);
    }

    private MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl sourceRectangle = source.receiver();
        Point sourcePoint = source.arg0();

        SpatialContext context = new SpatialContext(true);
        RectangleImpl rotatedRectangle = new RectangleImpl(
                normalizeLongitude(sourceRectangle.getMinX() + ROTATION_DEGREES),
                normalizeLongitude(sourceRectangle.getMaxX() + ROTATION_DEGREES),
                sourceRectangle.getMinY(),
                sourceRectangle.getMaxY(),
                context);
        Point rotatedPoint = context.makePoint(
                normalizeLongitude(sourcePoint.getX() + ROTATION_DEGREES),
                sourcePoint.getY());

        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rotatedRectangle, rotatedPoint);
    }

    private double normalizeLongitude(double longitude) {
        double normalized = longitude % 360.0;
        if (normalized < -180.0) {
            normalized += 360.0;
        } else if (normalized >= 180.0) {
            normalized -= 360.0;
        }
        return normalized == 0.0 ? 0.0 : normalized;
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        SpatialRelation sourceOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(
                        generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(
                sourceOutput,
                followUpOutput,
                "Rotating the rectangle and point east by 150 degrees must preserve relate(Point)");
    }

}
