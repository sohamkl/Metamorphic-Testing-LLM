import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;
import org.locationtech.spatial4j.shape.SpatialRelation;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static void verifyShiftInvariant(
            CircleImpl source,
            RectangleImpl rectangle) {
        SpatialRelation sourceOutput = source.relate(rectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[1];

        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
