import org.junit.jupiter.api.Test;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final org.locationtech.spatial4j.context.SpatialContext CONTEXT =
            new org.locationtech.spatial4j.context.SpatialContext(false);

    private static Object[] generateFollowUp(
            org.locationtech.spatial4j.shape.impl.CircleImpl sourceCircle,
            org.locationtech.spatial4j.shape.Rectangle sourceRectangle) {

        org.locationtech.spatial4j.context.SpatialContext context = sourceCircle.getContext();
        org.locationtech.spatial4j.shape.Point shiftedCenter = context.makePoint(
                sourceCircle.getCenter().getX() + 17.5,
                sourceCircle.getCenter().getY() - 9.25);

        org.locationtech.spatial4j.shape.impl.CircleImpl shiftedCircle =
                new org.locationtech.spatial4j.shape.impl.CircleImpl(
                        shiftedCenter, sourceCircle.getRadius(), context);

        org.locationtech.spatial4j.shape.Rectangle shiftedRectangle =
                new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                        sourceRectangle.getMinX() + 17.5,
                        sourceRectangle.getMaxX() + 17.5,
                        sourceRectangle.getMinY() - 9.25,
                        sourceRectangle.getMaxY() - 9.25,
                        context);

        return new Object[]{shiftedCircle, shiftedRectangle};
    }

}
