import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

import java.util.Objects;

/**
 * MR for CircleImpl.relate(Rectangle) (Spatial4j).
 *
 * Sliding a circle and a rectangle by the same offset can't change how they sit relative to
 * each other - eg. if they were touching they stay touching, if they were disjoint they stay disjoint
 * and so on. 
 *
 * Swapping the two shapes instead is a more obvious relation, but it can't fail because
 * RectangleImpl.relate just returns other.relate(this).transpose() by definition. Sliding
 * them is the one that actually forces the real geometry and logic to run
 *
 */
public final class CircleRelateDevMetamorphicSpec {

    /** Offset applied to both shapes to build the follow-up input. */
    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private CircleRelateDevMetamorphicSpec() {
    }

    public static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        Objects.requireNonNull(circle, "circle");
        Objects.requireNonNull(rectangle, "rectangle");

        // reuse the source circle's own context rather than building a new one
        SpatialContext context = circle.getContext();
        Point sourceCentre = circle.getCenter();
        Point shiftedCentre = context.makePoint( sourceCentre.getX() + SHIFT_X, sourceCentre.getY() + SHIFT_Y);
        CircleImpl shiftedCircle = new CircleImpl(shiftedCentre, circle.getRadius(), context);
        
        RectangleImpl shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + SHIFT_X,
                rectangle.getMaxX() + SHIFT_X,
                rectangle.getMinY() + SHIFT_Y,
                rectangle.getMaxY() + SHIFT_Y,
                context);
        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    public static void assertRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("Sliding both shapes by (" + SHIFT_X + ", " + SHIFT_Y
                    + ") changed the relation from " + sourceOutput + " to " + followUpOutput + ".");
        }
    }
}
