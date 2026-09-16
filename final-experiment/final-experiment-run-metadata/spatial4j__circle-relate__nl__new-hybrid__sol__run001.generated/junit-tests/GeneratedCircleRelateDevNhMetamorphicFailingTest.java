import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final SpatialContext CARTESIAN_CONTEXT = new SpatialContext(false);
    private static final double TRANSLATE_X = 16.0;
    private static final double TRANSLATE_Y = -8.0;

    private static MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source(
            double centerX,
            double centerY,
            double radius,
            double minX,
            double maxX,
            double minY,
            double maxY) {

        Point center = CARTESIAN_CONTEXT.makePoint(centerX, centerY);
        CircleImpl circle = new CircleImpl(center, radius, CARTESIAN_CONTEXT);
        Rectangle rectangle =
                CARTESIAN_CONTEXT.makeRectangle(minX, maxX, minY, maxY);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                circle, rectangle);
    }

    private static MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source) {

        CircleImpl sourceCircle = source.receiver();
        Rectangle sourceRectangle = source.arg0();

        Point translatedCenter = CARTESIAN_CONTEXT.makePoint(
                sourceCircle.getCenter().getX() + TRANSLATE_X,
                sourceCircle.getCenter().getY() + TRANSLATE_Y);

        CircleImpl translatedCircle = new CircleImpl(
                translatedCenter,
                sourceCircle.getRadius(),
                CARTESIAN_CONTEXT);

        Rectangle translatedRectangle = CARTESIAN_CONTEXT.makeRectangle(
                sourceRectangle.getMinX() + TRANSLATE_X,
                sourceRectangle.getMaxX() + TRANSLATE_X,
                sourceRectangle.getMinY() + TRANSLATE_Y,
                sourceRectangle.getMaxY() + TRANSLATE_Y);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle, translatedRectangle);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source) {

        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input followUp =
                generateFollowUp(source);

        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {

        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

}
