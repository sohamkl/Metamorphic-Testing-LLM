import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

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
  void BBOX_DISJOINT_RIGHT_translation() {
    Fixture source = source(1.0, 2.0, 3.0, -0.5, 0.5, 3.0, -4.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void BBOX_DISJOINT_LEFT_translation() {
    Fixture source = source(1.0, -3.0, -2.0, -0.5, 0.5, -5.0, 6.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void BBOX_DISJOINT_ABOVE_translation() {
    Fixture source = source(1.0, -0.5, 0.5, 2.0, 3.0, 7.0, 2.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void BBOX_DISJOINT_BELOW_translation() {
    Fixture source = source(1.0, -0.5, 0.5, -3.0, -2.0, -8.0, -3.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DIAGONAL_BBOX_OVERLAP_DISJOINT_NORTHEAST_translation() {
    Fixture source = source(1.0, 0.8, 2.0, 0.8, 2.0, 4.0, -7.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DIAGONAL_BBOX_OVERLAP_DISJOINT_NORTHWEST_translation() {
    Fixture source = source(1.0, -2.0, -0.8, 0.8, 2.0, -6.0, 5.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DIAGONAL_BBOX_OVERLAP_DISJOINT_SOUTHWEST_translation() {
    Fixture source = source(1.0, -2.0, -0.8, -2.0, -0.8, 9.0, 3.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DIAGONAL_BBOX_OVERLAP_DISJOINT_SOUTHEAST_translation() {
    Fixture source = source(1.0, 0.8, 2.0, -2.0, -0.8, -4.0, -8.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void CORNER_TANGENCY_NORTHWEST_translation() {
    Fixture source = source(1.0, -2.0, -0.6, 0.8, 2.0, -10.0, 4.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void CORNER_TANGENCY_SOUTHEAST_translation() {
    Fixture source = source(1.0, 0.6, 2.0, -2.0, -0.8, -12.0, -5.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void INTERSECTS_RIGHT_AXIS_OVERLAP_translation() {
    Fixture source = source(1.0, 0.8, 2.0, -0.2, 0.2, 13.0, 6.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void INTERSECTS_LEFT_AXIS_OVERLAP_translation() {
    Fixture source = source(1.0, -2.0, -0.8, -0.2, 0.2, -13.0, -6.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void INTERSECTS_ABOVE_AXIS_OVERLAP_translation() {
    Fixture source = source(1.0, -0.2, 0.2, 0.8, 2.0, 14.0, -2.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void INTERSECTS_BELOW_AXIS_OVERLAP_translation() {
    Fixture source = source(1.0, -0.2, 0.2, -2.0, -0.8, -14.0, 2.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void INTERSECTS_VERTICAL_STRIP_THROUGH_CENTER_translation() {
    Fixture source = source(1.0, -0.2, 0.2, -2.0, 2.0, 15.0, 7.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void INTERSECTS_HORIZONTAL_STRIP_THROUGH_CENTER_translation() {
    Fixture source = source(1.0, -2.0, 2.0, -0.2, 0.2, -15.0, -7.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void BBOX_WITHIN_STRICT_RECTANGLE_translation() {
    Fixture source = source(1.0, -2.0, 2.0, -2.0, 2.0, 16.0, -8.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void BBOX_WITHIN_TOUCHING_RECTANGLE_translation() {
    Fixture source = source(1.0, -1.0, 1.0, -2.0, 2.0, -16.0, 8.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void BBOX_EQUALS_RECTANGLE_SPECIAL_WITHIN_translation() {
    Fixture source = source(1.0, -1.0, 1.0, -1.0, 1.0, 17.0, 9.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void CONTAINS_RECTANGLE_NORTHEAST_translation() {
    Fixture source = source(1.0, 0.1, 0.5, 0.1, 0.5, -17.0, -9.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void CONTAINS_RECTANGLE_NORTHWEST_translation() {
    Fixture source = source(1.0, -0.5, -0.1, 0.1, 0.5, 18.0, -10.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void CONTAINS_RECTANGLE_SOUTHWEST_translation() {
    Fixture source = source(1.0, -0.5, -0.1, -0.5, -0.1, -18.0, 10.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void CONTAINS_RECTANGLE_SOUTHEAST_translation() {
    Fixture source = source(1.0, 0.1, 0.5, -0.5, -0.1, 19.0, 11.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void CONTAINS_CENTERED_RECTANGLE_FARTHEST_TIE_translation() {
    Fixture source = source(1.0, -0.5, 0.5, -0.5, 0.5, -19.0, -11.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void BBOX_CONTAINS_BUT_CIRCLE_INTERSECTS_translation() {
    Fixture source = source(1.0, -1.0, 1.0, -0.2, 0.2, 20.0, -12.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void SIDE_TANGENCY_INTERSECTS_translation() {
    Fixture source = source(1.0, 1.0, 2.0, -0.2, 0.2, 21.0, 13.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DEGENERATE_VERTICAL_LINE_CONTAINED_translation() {
    Fixture source = source(1.0, 0.2, 0.2, -0.4, 0.4, -21.0, -13.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DEGENERATE_HORIZONTAL_LINE_INTERSECTS_translation() {
    Fixture source = source(1.0, -2.0, 2.0, 0.2, 0.2, 22.0, -14.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DEGENERATE_POINT_CONTAINED_translation() {
    Fixture source = source(1.0, 0.3, 0.3, 0.4, 0.4, -22.0, 14.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DEGENERATE_POINT_ON_CIRCLE_BOUNDARY_translation() {
    Fixture source = source(1.0, 1.0, 1.0, 0.0, 0.0, 23.0, 15.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void DEGENERATE_POINT_OUTSIDE_BBOX_translation() {
    Fixture source = source(1.0, 2.0, 2.0, 0.0, 0.0, -23.0, -15.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void ZERO_RADIUS_CENTER_STRICTLY_IN_RECTANGLE_translation() {
    Fixture source = source(0.0, -1.0, 1.0, -1.0, 1.0, 24.0, -16.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void ZERO_RADIUS_CENTER_ON_RECTANGLE_BOUNDARY_translation() {
    Fixture source = source(0.0, 0.0, 1.0, -1.0, 1.0, -24.0, 16.0);
    assertMetamorphicRelation(source);
  }

  @Test
  void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_translation() {
    Fixture source = source(0.0, 0.0, 0.0, 0.0, 0.0, 25.0, 17.0);
    assertMetamorphicRelation(source);
  }
}
