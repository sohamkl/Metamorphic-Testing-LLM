import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private SpatialContext flatContext() {
        SpatialContextFactory factory = new SpatialContextFactory();
        factory.geo = false;
        return factory.newSpatialContext();
    }

    private CircleImpl circle(SpatialContext context, double x, double y, double radius) {
        Point center = context.makePoint(x, y);
        return new CircleImpl(center, radius, context);
    }

    private CircleImpl emptyCircle(SpatialContext context) {
        return new CircleImpl(context.makePoint(Double.NaN, Double.NaN), 0.0, context);
    }

    private Rectangle rectangle(SpatialContext context,
                                double minX, double maxX,
                                double minY, double maxY) {
        return context.makeRectangle(minX, maxX, minY, maxY);
    }

    private void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
