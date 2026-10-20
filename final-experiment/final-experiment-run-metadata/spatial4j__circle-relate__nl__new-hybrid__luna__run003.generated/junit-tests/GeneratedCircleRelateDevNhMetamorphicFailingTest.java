import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicFailingTest {

    private static final class Source {
        final SpatialContext context;
        final CircleImpl circle;
        final Rectangle rectangle;

        Source(SpatialContext context, CircleImpl circle, Rectangle rectangle) {
            this.context = context;
            this.circle = circle;
            this.rectangle = rectangle;
        }
    }

    private Source source(double centerX, double centerY, double radius,
                          double minX, double maxX,
                          double minY, double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(centerX, centerY, context), radius, context);
        Rectangle rectangle = new RectangleImpl(
                centerX + minX, centerX + maxX,
                centerY + minY, centerY + maxY, context);
        return new Source(context, circle, rectangle);
    }

    private Source emptyCircleSource() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(Double.NaN, Double.NaN, context), 0, context);
        Rectangle rectangle = new RectangleImpl(
                10, 12, -1, 1, context);
        return new Source(context, circle, rectangle);
    }

    private Source emptyRectangleSource() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0, 0, context), 5, context);
        Rectangle rectangle = context.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);
        return new Source(context, circle, rectangle);
    }

    private void assertMetamorphicRelationFor(Source source, double dx, double dy) {
        SpatialRelation sourceOutput = source.circle.relate(source.rectangle);

        CircleImpl followUpCircle = new CircleImpl(
                new PointImpl(
                        source.circle.getCenter().getX() + dx,
                        source.circle.getCenter().getY() + dy,
                        source.context),
                source.circle.getRadius(),
                source.context);

        Rectangle followUpRectangle = new RectangleImpl(
                source.rectangle.getMinX() + dx,
                source.rectangle.getMaxX() + dx,
                source.rectangle.getMinY() + dy,
                source.rectangle.getMaxY() + dy,
                source.context);

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

}
