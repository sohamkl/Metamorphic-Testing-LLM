import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private static final SpatialContext GEO_CONTEXT = new SpatialContext(true);

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {
        RectangleImpl rectangle =
                new RectangleImpl(minX, maxX, minY, maxY, GEO_CONTEXT);
        Point point = new PointImpl(pointX, pointY, GEO_CONTEXT);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                rectangle, point);
    }

    private static double wrap150(double longitude) {
        double rotated = longitude + 150.0;
        while (rotated > 180.0) {
            rotated -= 360.0;
        }
        while (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl sourceRectangle = source.receiver();
        Point sourcePoint = source.arg0();

        RectangleImpl followUpRectangle = new RectangleImpl(
                wrap150(sourceRectangle.getMinX()),
                wrap150(sourceRectangle.getMaxX()),
                sourceRectangle.getMinY(),
                sourceRectangle.getMaxY(),
                GEO_CONTEXT);

        Point followUpPoint = new PointImpl(
                wrap150(sourcePoint.getX()),
                sourcePoint.getY(),
                GEO_CONTEXT);

        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(
                followUpRectangle, followUpPoint);
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
