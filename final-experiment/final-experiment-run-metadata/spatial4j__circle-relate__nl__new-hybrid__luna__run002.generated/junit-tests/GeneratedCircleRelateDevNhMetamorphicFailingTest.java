import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final class Fixture {
        final SpatialContext context;
        final MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source;
        final double dx;
        final double dy;

        Fixture(SpatialContext context,
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source,
                double dx, double dy) {
            this.context = context;
            this.source = source;
            this.dx = dx;
            this.dy = dy;
        }
    }

    private static Fixture fixture(double cx, double cy, double radius,
                                   double minX, double maxX,
                                   double minY, double maxY,
                                   double dx, double dy) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(cx, cy, context), radius, context);
        Rectangle rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input =
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle, rectangle);
        return new Fixture(context, input, dx, dy);
    }

    private static MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            Fixture fixture) {
        CircleImpl sourceCircle = fixture.source.receiver();
        Rectangle sourceRectangle = fixture.source.arg0();
        SpatialContext context = fixture.context;
        CircleImpl translatedCircle = new CircleImpl(
                new PointImpl(
                        sourceCircle.getCenter().getX() + fixture.dx,
                        sourceCircle.getCenter().getY() + fixture.dy,
                        context),
                sourceCircle.getRadius(), context);
        Rectangle translatedRectangle = new RectangleImpl(
                sourceRectangle.getMinX() + fixture.dx,
                sourceRectangle.getMaxX() + fixture.dx,
                sourceRectangle.getMinY() + fixture.dy,
                sourceRectangle.getMaxY() + fixture.dy,
                context);
        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle, translatedRectangle);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(Fixture fixture) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(fixture.source);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(
                        generateFollowUp(fixture));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
