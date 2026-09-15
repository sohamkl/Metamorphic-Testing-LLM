import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
