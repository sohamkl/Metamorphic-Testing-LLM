import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

import java.util.Objects;

/**
 * Second MR for CircleImpl.relate(Rectangle) (Spatial4j), used alongside
 * CircleRelateDevMetamorphicSpec.
 *
 * Holding both shapes up to a mirror can't change how they sit next to each other - a
 * reflection doesn't stretch or move anything, it just flips left and right.
 *
 * The point of having this one as well as the sliding relation is that they are blind to
 * different things. Sliding leaves every comparison inside relateRectanglePhase2 untouched,
 * because it only ever compares the circle's axis against the rectangle's edges and both
 * sides shift by the same amount. Mirroring swaps those comparisons over: a circle that sat
 * left of the rectangle now sits right of it, so the follow-up runs down the opposite branch
 * and picks its closest and farthest corners from the opposite edges. A bug on one side of
 * that if/else shows up in only one of the two runs, which is what makes it visible.
 */
public final class CircleRelateMirrorMetamorphicSpec {

    private CircleRelateMirrorMetamorphicSpec() {
    }

    public static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        Objects.requireNonNull(circle, "circle");
        Objects.requireNonNull(rectangle, "rectangle");

        // reuse the source circle's own context rather than building a new one
        SpatialContext context = circle.getContext();
        Point sourceCentre = circle.getCenter();
        Point mirroredCentre = context.makePoint(mirror(sourceCentre.getX()), sourceCentre.getY());
        CircleImpl mirroredCircle = new CircleImpl(mirroredCentre, circle.getRadius(), context);

        // mirroring swaps which edge is the min and which is the max, so they go back the other way round
        RectangleImpl mirroredRectangle = new RectangleImpl(
                mirror(rectangle.getMaxX()),
                mirror(rectangle.getMinX()),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);
        return new Object[]{mirroredCircle, mirroredRectangle};
    }

    /**
     * Mirrors one x coordinate. Zero has to mirror onto itself: plain negation turns 0.0 into
     * -0.0, and Spatial4j's Rectangle.equals tells those two apart, which stops relate() from
     * spotting that a zero-radius circle's bounding box is the rectangle. That is a
     * floating-point wrinkle rather than anything geometric - the mirror image of the origin
     * is the origin.
     */
    private static double mirror(double x) {
        return x == 0.0 ? 0.0 : -x;
    }

    public static void assertRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");

        if (sourceOutput != followUpOutput) {
            throw new AssertionError("Mirroring both shapes left to right changed the relation from "
                    + sourceOutput + " to " + followUpOutput + ".");
        }
    }
}
