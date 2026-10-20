import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

import java.util.Objects;

/**
 * MR for RectangleImpl.relate(Point) (Spatial4j).
 *
 * Rotating a box and a point east by the same amount can't change whether the point is
 * inside the box - only the gap in longitude between them matters, not where they sit on
 * the globe. Latitude is left alone since a sphere isn't symmetric top to bottom.
 *
 * Rotating past 180 degrees wraps the box across the dateline (minX > maxX), which forces
 * relate(Point) through its dateline-unwrap code - exactly where bugs would hide.
 *
 */
public final class RectangleContainsPointMetamorphicSpec {

    /** Degrees of eastward rotation applied to both the box and the probe point. */
    private static final double ROTATION_DEG = 150.0;

    private RectangleContainsPointMetamorphicSpec() {
    }

    public static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        Objects.requireNonNull(rectangle, "rectangle");
        Objects.requireNonNull(point, "point");

        SpatialContext context = rectangle.getContext();

        RectangleImpl rotatedRectangle = new RectangleImpl(
                DistanceUtils.normLonDEG(rectangle.getMinX() + ROTATION_DEG),
                DistanceUtils.normLonDEG(rectangle.getMaxX() + ROTATION_DEG),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);

        Point rotatedPoint = context.makePoint(
                DistanceUtils.normLonDEG(point.getX() + ROTATION_DEG),
                point.getY());

        return new Object[]{rotatedRectangle, rotatedPoint};
    }

    public static void assertRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("Rotating the rectangle and the point east by " + ROTATION_DEG
                    + " degrees changed the relation from " + sourceOutput
                    + " to " + followUpOutput + ".");
        }
    }
}
