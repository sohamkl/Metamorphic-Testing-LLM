import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

  private static final double DX = 7.0;
  private static final double DY = -5.0;

  private static final class Fixture {
    final SpatialContext context;
    final CircleImpl circle;
    final Rectangle rectangle;

    Fixture(SpatialContext context, CircleImpl circle, Rectangle rectangle) {
      this.context = context;
      this.circle = circle;
      this.rectangle = rectangle;
    }
  }

  private Fixture source(double cx, double cy, double radius,
                         double minX, double maxX, double minY, double maxY) {
    SpatialContext context = new SpatialContext(false);
    CircleImpl circle = new CircleImpl(new PointImpl(cx, cy, context), radius, context);
    Rectangle rectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
    return new Fixture(context, circle, rectangle);
  }

  private Fixture generateFollowUp(Fixture source) {
    double cx = source.circle.getCenter().getX() + DX;
    double cy = source.circle.getCenter().getY() + DY;
    Rectangle r = source.rectangle;
    CircleImpl translatedCircle =
        new CircleImpl(new PointImpl(cx, cy, source.context), source.circle.getRadius(), source.context);
    Rectangle translatedRectangle = new RectangleImpl(
        r.getMinX() + DX, r.getMaxX() + DX,
        r.getMinY() + DY, r.getMaxY() + DY,
        source.context);
    return new Fixture(source.context, translatedCircle, translatedRectangle);
  }

  private void assertMetamorphicRelationFor(Fixture source) {
    SpatialRelation sourceOutput = source.circle.relate(source.rectangle);
    Fixture followUp = generateFollowUp(source);
    SpatialRelation followUpOutput = followUp.circle.relate(followUp.rectangle);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  private void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                         SpatialRelation followUpOutput) {
    Assertions.assertEquals(sourceOutput, followUpOutput);
  }

}
