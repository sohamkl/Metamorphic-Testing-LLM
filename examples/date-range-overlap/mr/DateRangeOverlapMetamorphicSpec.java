/**
 * Developer-owned metamorphic relation for {@code LocalDateRange.overlaps} (threeten-extra).
 *
 * <p><b>Relation (symmetry):</b> overlapping is a mutual property of two ranges, so
 * {@code a.overlaps(b)} must equal {@code b.overlaps(a)}. The follow-up input swaps the two
 * ranges and the answer must be unchanged.</p>
 *
 * <p>This holds without corner cases: the library defines overlap as
 * {@code other.equals(this) || (start &lt; other.end &amp;&amp; other.start &lt; end)}, and both
 * disjuncts are symmetric under swapping.</p>
 */
public final class DateRangeOverlapMetamorphicSpec {

    private DateRangeOverlapMetamorphicSpec() {
    }

    public static DateRangePairInput generateFollowUp(DateRangePairInput source) {
        return new DateRangePairInput(
                source.secondStart(), source.secondEnd(),
                source.firstStart(), source.firstEnd());
    }

    public static void assertRelation(boolean sourceOutput, boolean followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("overlaps() is not symmetric: got " + sourceOutput
                    + " one way and " + followUpOutput + " after swapping the two ranges.");
        }
    }
}
