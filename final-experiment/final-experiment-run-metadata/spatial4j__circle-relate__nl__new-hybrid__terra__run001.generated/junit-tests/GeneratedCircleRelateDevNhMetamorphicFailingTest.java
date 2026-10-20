import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);
    private static final double DX = 11.0;
    private static final double DY = -7.0;

    private MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source(
            double cx, double cy, double radius,
            double minX, double maxX, double minY, double maxY) {
        CircleImpl circle = new CircleImpl(new PointImpl(cx, cy, CONTEXT), radius, CONTEXT);
        Rectangle rectangle = new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circle, rectangle);
    }

    private MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source) {
        CircleImpl originalCircle = source.receiver();
        Rectangle originalRectangle = source.arg0();

        CircleImpl translatedCircle = new CircleImpl(
                new PointImpl(
                        originalCircle.getCenter().getX() + DX,
                        originalCircle.getCenter().getY() + DY,
                        CONTEXT),
                originalCircle.getRadius(),
                CONTEXT);

        Rectangle translatedRectangle = new RectangleImpl(
                originalRectangle.getMinX() + DX,
                originalRectangle.getMaxX() + DX,
                originalRectangle.getMinY() + DY,
                originalRectangle.getMaxY() + DY,
                CONTEXT);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle, translatedRectangle);
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                           SpatialRelation followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input sourceInput) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(sourceInput);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(generateFollowUp(sourceInput));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
