import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CARTESIAN_CONTEXT = new SpatialContext(false);

    private static final class Fixture {
        private final MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source;
        private final double dx;
        private final double dy;

        private Fixture(
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source,
                double dx,
                double dy) {
            this.source = source;
            this.dx = dx;
            this.dy = dy;
        }
    }

    private Fixture fixture(
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
                new PointImpl(cx, cy, CARTESIAN_CONTEXT),
                radius,
                CARTESIAN_CONTEXT);

        Rectangle rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, CARTESIAN_CONTEXT);

        return new Fixture(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circle, rectangle),
                dx,
                dy);
    }

    private MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            Fixture fixture) {

        CircleImpl sourceCircle = fixture.source.receiver();
        Rectangle sourceRectangle = fixture.source.arg0();

        double translatedCenterX = sourceCircle.getCenter().getX() + fixture.dx;
        double translatedCenterY = sourceCircle.getCenter().getY() + fixture.dy;

        CircleImpl translatedCircle = new CircleImpl(
                new PointImpl(
                        translatedCenterX,
                        translatedCenterY,
                        CARTESIAN_CONTEXT),
                sourceCircle.getRadius(),
                CARTESIAN_CONTEXT);

        Rectangle translatedRectangle = new RectangleImpl(
                sourceRectangle.getMinX() + fixture.dx,
                sourceRectangle.getMaxX() + fixture.dx,
                sourceRectangle.getMinY() + fixture.dy,
                sourceRectangle.getMaxY() + fixture.dy,
                CARTESIAN_CONTEXT);

        Assertions.assertEquals(
                sourceRectangle.getWidth(),
                translatedRectangle.getWidth(),
                0.0);
        Assertions.assertEquals(
                sourceRectangle.getHeight(),
                translatedRectangle.getHeight(),
                0.0);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle,
                translatedRectangle);
    }

    private void assertMetamorphicRelationFor(Fixture fixture) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(fixture.source);

        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input followUp =
                generateFollowUp(fixture);

        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

}
