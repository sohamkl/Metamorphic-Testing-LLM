import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

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

  @Test
  void BBOX_DISJOINT_RIGHT_translation() {
    assertMetamorphicRelationFor(source(0, 0, 5, 6, 8, -1, 1));
  }

  @Test
  void BBOX_DISJOINT_LEFT_translation() {
    assertMetamorphicRelationFor(source(2, -3, 4, -5, -3, -4, -2));
  }

  @Test
  void BBOX_DISJOINT_ABOVE_translation() {
    assertMetamorphicRelationFor(source(-4, 2, 3, -5, -3, 6, 8));
  }

  @Test
  void BBOX_DISJOINT_BELOW_translation() {
    assertMetamorphicRelationFor(source(5, 4, 2, 4, 6, -1, 1));
  }

  @Test
  void BBOX_CORNER_OVERLAP_CIRCLE_DISJOINT_translation() {
    assertMetamorphicRelationFor(source(0, 0, 5, 4, 5, 4, 5));
  }

  @Test
  void CLOSEST_CORNER_TANGENCY_translation() {
    assertMetamorphicRelationFor(source(1, -1, 5, 4, 6, 3, 4));
  }

  @Test
  void CORNER_PENETRATION_INTERSECTS_translation() {
    assertMetamorphicRelationFor(source(-2, 3, 5, 1, 4, 6, 9));
  }

  @Test
  void VERTICAL_AXIS_SPAN_INTERSECTS_translation() {
    assertMetamorphicRelationFor(source(0, 0, 4, -1, 1, 3, 8));
  }

  @Test
  void HORIZONTAL_AXIS_SPAN_INTERSECTS_translation() {
    assertMetamorphicRelationFor(source(3, -2, 4, 6, 11, -3, -1));
  }

  @Test
  void BOTH_AXES_SPANNED_BBOX_INTERSECTS_translation() {
    assertMetamorphicRelationFor(source(-1, 1, 4, -9, 7, 0, 2));
  }

  @Test
  void BBOX_WITHIN_STRICT_RECTANGLE_translation() {
    assertMetamorphicRelationFor(source(2, 2, 3, -2, 6, -2, 6));
  }

  @Test
  void BBOX_WITHIN_BOUNDARY_TOUCHING_RECTANGLE_translation() {
    assertMetamorphicRelationFor(source(-3, 4, 5, -8, 2, -2, 10));
  }

  @Test
  void RECTANGLE_EQUALS_CIRCLE_BBOX_translation() {
    assertMetamorphicRelationFor(source(0, 0, 6, -6, 6, -6, 6));
  }

  @Test
  void SMALL_CENTERED_RECTANGLE_CONTAINED_translation() {
    assertMetamorphicRelationFor(source(4, -3, 5, 2, 6, -5, -1));
  }

  @Test
  void OFFCENTER_RECTANGLE_CONTAINED_translation() {
    assertMetamorphicRelationFor(source(0, 0, 5, 1, 3, -2, 2));
  }

  @Test
  void ABOVE_CENTER_RECTANGLE_CONTAINED_translation() {
    assertMetamorphicRelationFor(source(-2, -2, 5, -4, 0, -1, 1));
  }

  @Test
  void FARTHEST_CORNER_TANGENCY_CONTAINS_translation() {
    assertMetamorphicRelationFor(source(1, 1, 5, -2, 4, -3, 5));
  }

  @Test
  void FARTHEST_CORNER_OUTSIDE_INTERSECTS_translation() {
    assertMetamorphicRelationFor(source(0, 0, 5, -4, 4, -4, 4));
  }

  @Test
  void BBOX_CONTAINS_RECTANGLE_AXIS_SPAN_INTERSECTS_translation() {
    assertMetamorphicRelationFor(source(3, 3, 4, -1, 7, -1, 7));
  }

  @Test
  void BBOX_CONTAINS_NON_EQUAL_LARGE_RECTANGLE_INTERSECTS_translation() {
    assertMetamorphicRelationFor(source(-4, 1, 5, -8, 0, -3, 5));
  }

  @Test
  void ZERO_RADIUS_CENTER_STRICTLY_INSIDE_RECTANGLE_translation() {
    assertMetamorphicRelationFor(source(2, -1, 0, 0, 4, -4, 2));
  }

  @Test
  void ZERO_RADIUS_OUTSIDE_RECTANGLE_translation() {
    assertMetamorphicRelationFor(source(-2, 3, 0, -1, 0, 2, 4));
  }

  @Test
  void ZERO_RADIUS_CENTER_ON_RECTANGLE_BOUNDARY_translation() {
    assertMetamorphicRelationFor(source(1, 2, 0, 1, 3, 1, 3));
  }

  @Test
  void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_translation() {
    assertMetamorphicRelationFor(source(-5, 4, 0, -5, -5, 4, 4));
  }

  @Test
  void HORIZONTAL_LINE_STRICTLY_CONTAINED_translation() {
    assertMetamorphicRelationFor(source(0, 0, 5, -3, 3, 0, 0));
  }

  @Test
  void HORIZONTAL_LINE_CROSSING_CIRCLE_translation() {
    assertMetamorphicRelationFor(source(2, -2, 4, -6, 10, -2, -2));
  }

  @Test
  void VERTICAL_LINE_STRICTLY_CONTAINED_translation() {
    assertMetamorphicRelationFor(source(1, 1, 5, 1, 1, -2, 4));
  }

  @Test
  void VERTICAL_LINE_TANGENT_translation() {
    assertMetamorphicRelationFor(source(-3, 0, 4, 1, 1, -2, 2));
  }

  @Test
  void POINT_RECTANGLE_STRICTLY_INSIDE_translation() {
    assertMetamorphicRelationFor(source(0, 0, 5, 3, 3, 2, 2));
  }

  @Test
  void POINT_RECTANGLE_ON_CIRCLE_BOUNDARY_translation() {
    assertMetamorphicRelationFor(source(2, -1, 5, 7, 7, -1, -1));
  }

  @Test
  void POINT_RECTANGLE_IN_BBOX_OUTSIDE_CIRCLE_translation() {
    assertMetamorphicRelationFor(source(0, 0, 5, 4, 4, 4, 4));
  }

  @Test
  void POINT_RECTANGLE_OUTSIDE_BBOX_translation() {
    assertMetamorphicRelationFor(source(-1, 2, 3, 3, 3, 2, 2));
  }
}
