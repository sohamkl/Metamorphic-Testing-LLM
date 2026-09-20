import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Translation changed the spatial relation from "
                            + sourceOutput + " to " + followUpOutput);
        }
    }

    @Test
    public void testDIAGONAL_CONTACT_DISTANCE_EXACTLY_RADIUS_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 1.0, context);
        Rectangle rectangle = context.makeRectangle(0.6, 0.7, 0.8, 0.9);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUpValues = CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
