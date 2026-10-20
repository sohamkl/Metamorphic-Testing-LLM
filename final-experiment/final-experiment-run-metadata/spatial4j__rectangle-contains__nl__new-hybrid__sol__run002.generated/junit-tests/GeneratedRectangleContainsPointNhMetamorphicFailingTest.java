import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final SpatialContext GEO = new SpatialContext(true);
    private static final double ROTATION_DEGREES = 150.0;

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {
        RectangleImpl rectangle =
                new RectangleImpl(minX, maxX, minY, maxY, GEO);
        Point point = new PointImpl(pointX, pointY, GEO);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rectangle, point);
    }

    private static double wrapLongitude(double longitude) {
        double wrapped = ((longitude + 180.0) % 360.0 + 360.0) % 360.0 - 180.0;
        return wrapped == 0.0 ? 0.0 : wrapped;
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl sourceRectangle = source.receiver();
        Point sourcePoint = source.arg0();

        RectangleImpl rotatedRectangle = new RectangleImpl(
                wrapLongitude(sourceRectangle.getMinX() + ROTATION_DEGREES),
                wrapLongitude(sourceRectangle.getMaxX() + ROTATION_DEGREES),
                sourceRectangle.getMinY(),
                sourceRectangle.getMaxY(),
                GEO);

        Point rotatedPoint = new PointImpl(
                wrapLongitude(sourcePoint.getX() + ROTATION_DEGREES),
                sourcePoint.getY(),
                GEO);

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
