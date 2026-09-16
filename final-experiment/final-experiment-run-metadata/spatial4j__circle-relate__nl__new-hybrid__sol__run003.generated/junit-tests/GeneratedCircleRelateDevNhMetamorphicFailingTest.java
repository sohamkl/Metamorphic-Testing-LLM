import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);

    private static final class TranslationCase {
        private final MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input;
        private final double dx;
        private final double dy;

        private TranslationCase(
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input,
                double dx,
                double dy) {
            this.input = input;
            this.dx = dx;
            this.dy = dy;
        }
    }

    private TranslationCase source(
            double cx,
            double cy,
            double radius,
            double minX,
            double maxX,
            double minY,
            double maxY,
            double dx,
            double dy) {

        CircleImpl circle = new CircleImpl(
                CONTEXT.makePoint(cx, cy),
                radius,
                CONTEXT);
        Rectangle rectangle = CONTEXT.makeRectangle(minX, maxX, minY, maxY);

        return new TranslationCase(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circle, rectangle),
                dx,
                dy);
    }

    private MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            TranslationCase source) {

        Assertions.assertTrue(source.dx != 0.0 || source.dy != 0.0);

        CircleImpl originalCircle = source.input.receiver();
        Rectangle originalRectangle = source.input.arg0();

        CircleImpl translatedCircle = new CircleImpl(
                CONTEXT.makePoint(
                        originalCircle.getCenter().getX() + source.dx,
                        originalCircle.getCenter().getY() + source.dy),
                originalCircle.getRadius(),
                CONTEXT);

        Rectangle translatedRectangle = CONTEXT.makeRectangle(
                originalRectangle.getMinX() + source.dx,
                originalRectangle.getMaxX() + source.dx,
                originalRectangle.getMinY() + source.dy,
                originalRectangle.getMaxY() + source.dy);

        Assertions.assertEquals(originalCircle.getRadius(), translatedCircle.getRadius());
        Assertions.assertEquals(
                originalRectangle.getWidth(),
                translatedRectangle.getWidth());
        Assertions.assertEquals(
                originalRectangle.getHeight(),
                translatedRectangle.getHeight());

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle,
                translatedRectangle);
    }

    private void assertMetamorphicRelationFor(TranslationCase source) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(source.input);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

}
