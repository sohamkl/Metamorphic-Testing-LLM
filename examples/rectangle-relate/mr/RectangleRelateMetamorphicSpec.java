/**
 * Developer-owned metamorphic relation for {@code RectangleImpl.relate} (Spatial4j).
 *
 * <p><b>Relation (transpose symmetry):</b> swapping the two shapes must transpose the
 * relation — CONTAINS becomes WITHIN and vice versa, while INTERSECTS and DISJOINT are
 * unchanged. This is not an invented property: it is the contract Spatial4j documents on
 * {@code SpatialRelation.transpose()}, which states that if {@code a.relate(b)} is {@code r},
 * then {@code b.relate(a)} should be {@code r.transpose()}.</p>
 *
 * <p><b>Equal-shapes corner case.</b> The same javadoc notes that when the two shapes are
 * equal the flip is ambiguous — the call returns the same value both ways (CONTAINS or
 * WITHIN), which transposing would wrongly flag. The library says the caller has to handle
 * this, so the SUT reports whether the shapes were equal and the assertion below requires
 * plain equality in that case rather than weakening the check for every input.</p>
 */
public final class RectangleRelateMetamorphicSpec {

    private RectangleRelateMetamorphicSpec() {
    }

    public static RectanglePairInput generateFollowUp(RectanglePairInput source) {
        return new RectanglePairInput(
                source.secondMinX(), source.secondMaxX(), source.secondMinY(), source.secondMaxY(),
                source.firstMinX(), source.firstMaxX(), source.firstMinY(), source.firstMaxY());
    }

    public static void assertRelation(RelateOutcome sourceOutput, RelateOutcome followUpOutput) {
        if (sourceOutput.shapesEqual()) {
            if (followUpOutput.relation() != sourceOutput.relation()) {
                throw new AssertionError("Two equal rectangles related as "
                        + sourceOutput.relation() + " one way but "
                        + followUpOutput.relation() + " after swapping.");
            }
            return;
        }
        if (followUpOutput.relation() != sourceOutput.relation().transpose()) {
            throw new AssertionError("Swapping the rectangles should transpose "
                    + sourceOutput.relation() + " to " + sourceOutput.relation().transpose()
                    + ", but relate() returned " + followUpOutput.relation() + ".");
        }
    }
}
