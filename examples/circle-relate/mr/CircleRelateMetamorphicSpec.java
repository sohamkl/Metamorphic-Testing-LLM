import org.locationtech.spatial4j.shape.SpatialRelation;

/**
 * Developer-owned metamorphic relation for {@code CircleImpl.relate(Rectangle)} (Spatial4j).
 *
 * <p><b>Relation (translation invariance):</b> sliding a circle and a rectangle by the same
 * offset across a Euclidean plane moves them together, so how they sit relative to each other
 * cannot change. CONTAINS stays CONTAINS, DISJOINT stays DISJOINT, and so on. This is exact
 * on a plane, where there is no antimeridian or pole to wrap around.</p>
 *
 * <p><b>Why translation and not transpose symmetry.</b> The obvious relation — swapping the
 * two shapes should transpose the result — is a tautology here and could never fail:
 * {@code RectangleImpl.relate(Shape)} does not compute a rectangle-versus-circle answer at
 * all, it returns {@code other.relate(this).transpose()}, so the swapped call is literally
 * defined as the transpose of the original. Translation invariance instead forces the real
 * {@code relateRectanglePhase2} geometry to run on both the source and the follow-up.</p>
 */
public final class CircleRelateMetamorphicSpec {

    /** Offset applied to both shapes to build the follow-up input. */
    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private CircleRelateMetamorphicSpec() {
    }

    public static CircleRectangleInput generateFollowUp(CircleRectangleInput source) {
        return new CircleRectangleInput(
                source.centreX() + SHIFT_X,
                source.centreY() + SHIFT_Y,
                source.radius(),
                source.minX() + SHIFT_X,
                source.maxX() + SHIFT_X,
                source.minY() + SHIFT_Y,
                source.maxY() + SHIFT_Y);
    }

    public static void assertRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("Sliding both shapes by (" + SHIFT_X + ", " + SHIFT_Y
                    + ") changed the relation from " + sourceOutput + " to " + followUpOutput + ".");
        }
    }
}
