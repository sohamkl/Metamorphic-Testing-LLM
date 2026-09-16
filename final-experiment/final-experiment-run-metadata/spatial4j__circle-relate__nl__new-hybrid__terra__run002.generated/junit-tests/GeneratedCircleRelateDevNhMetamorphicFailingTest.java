import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

  private static final SpatialContext CARTESIAN_CONTEXT = new SpatialContext(false);

  private static final class Fixture {
    private final double centerX;
    private final double centerY;
    private final double radius;
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;
    private final double dx;
    private final double dy;
    private final CircleImpl circle;
    private final Rectangle rectangle;

    private Fixture(double centerX, double centerY, double radius,
                    double minX, double maxX, double minY, double maxY,
                    double dx, double dy) {
      this.centerX = centerX;
      this.centerY = centerY;
      this.radius = radius;
      this.minX = minX;
      this.maxX = maxX;
      this.minY = minY;
      this.maxY = maxY;
      this.dx = dx;
      this.dy = dy;
      Point center = CARTESIAN_CONTEXT.makePoint(centerX, centerY);
      this.circle = new CircleImpl(center, radius, CARTESIAN_CONTEXT);
      this.rectangle = CARTESIAN_CONTEXT.makeRectangle(minX, maxX, minY, maxY);
    }
  }

  private Fixture source(double radius, double minX, double maxX,
                         double minY, double maxY, double dx, double dy) {
    return new Fixture(0.0, 0.0, radius, minX, maxX, minY, maxY, dx, dy);
  }

  private Fixture generateFollowUp(Fixture source) {
    return new Fixture(
        source.centerX + source.dx,
        source.centerY + source.dy,
        source.radius,
        source.minX + source.dx,
        source.maxX + source.dx,
        source.minY + source.dy,
        source.maxY + source.dy,
        source.dx,
        source.dy);
  }

  private void assertMetamorphicRelation(Fixture source) {
    Fixture followUp = generateFollowUp(source);
    SpatialRelation sourceOutput = source.circle.relate(source.rectangle);
    SpatialRelation followUpOutput = followUp.circle.relate(followUp.rectangle);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  private void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                         SpatialRelation followUpOutput) {
    Assertions.assertEquals(sourceOutput, followUpOutput);
  }

  @Test
  void CORNER_TANGENCY_NORTHEAST_translation() {
    Fixture source = source(1.0, 0.6, 2.0, 0.8, 2.0, 11.0, -9.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void CORNER_TANGENCY_SOUTHWEST_translation() {
    Fixture source = source(1.0, -2.0, -0.6, -2.0, -0.8, 5.0, 12.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void FARTHEST_CORNER_TANGENT_CONTAINS_translation() {
    Fixture source = source(1.0, 0.0, 0.6, 0.0, 0.8, -20.0, 12.0);
    assertMetamorphicRelation(source);
  }
}
