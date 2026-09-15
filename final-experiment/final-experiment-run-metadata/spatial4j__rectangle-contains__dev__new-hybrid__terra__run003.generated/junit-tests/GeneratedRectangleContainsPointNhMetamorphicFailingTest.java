import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

    private void exercise(double minX, double maxX, double minY, double maxY,
                          double pointX, double pointY) {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point point = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput = rectangle.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
