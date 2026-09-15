import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private SpatialContext cartesianContext() {
        SpatialContextFactory factory = new SpatialContextFactory();
        factory.geo = false;
        return factory.newSpatialContext();
    }

    private CircleImpl circle(SpatialContext context, double radius) {
        return new CircleImpl(context.makePoint(0.0, 0.0), radius, context);
    }

}
