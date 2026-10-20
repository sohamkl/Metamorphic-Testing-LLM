import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CTX = new SpatialContext(false);

    private static CircleImpl circle(double x, double y, double radius) {
        return new CircleImpl(new PointImpl(x, y, CTX), radius, CTX);
    }

    private static Rectangle rectangle(double minX, double maxX,
                                       double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CTX);
    }

    private static MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input
    generateFollowUp(MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source,
                     double dx, double dy) {
        CircleImpl sourceCircle = source.receiver();
        Rectangle sourceRectangle = source.arg0();

        CircleImpl translatedCircle = circle(
                sourceCircle.getCenter().getX() + dx,
                sourceCircle.getCenter().getY() + dy,
                sourceCircle.getRadius());

        Rectangle translatedRectangle = rectangle(
                sourceRectangle.getMinX() + dx,
                sourceRectangle.getMaxX() + dx,
                sourceRectangle.getMinY() + dy,
                sourceRectangle.getMaxY() + dy);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle, translatedRectangle);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source,
            double dx, double dy) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(
                        generateFollowUp(source, dx, dy));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
