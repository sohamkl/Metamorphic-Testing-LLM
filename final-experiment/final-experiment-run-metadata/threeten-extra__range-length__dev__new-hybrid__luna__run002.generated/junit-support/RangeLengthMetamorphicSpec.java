import org.threeten.extra.LocalDateRange;

import java.time.LocalDate;
import java.util.Objects;

/**
 * MR for LocalDateRange.lengthInDays() (threeten-extra).
 *
 * Moving the end date n days later should make the range exactly n days longer.
 *
 * Unbounded ranges are skipped: lengthInDays() returns Integer.MAX_VALUE for those, which
 * isn't a real length to do math on.
 */
public final class RangeLengthMetamorphicSpec {

    /** Days added to the end of the range to build the follow-up input. */
    private static final int EXTRA_DAYS = 23;

    private RangeLengthMetamorphicSpec() {
    }

    public static Object[] generateFollowUp(LocalDateRange range) {
        Objects.requireNonNull(range, "range");

        LocalDate extendedEnd = range.getEnd().plusDays(EXTRA_DAYS);

        return new Object[]{LocalDateRange.of(range.getStart(), extendedEnd)};
    }

    public static void assertRelation(int sourceOutput, int followUpOutput) {
        // Skips all the unbounded ranges, as you cannot perform math on them
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }

        int actualIncrease = followUpOutput - sourceOutput;
        
        if (actualIncrease != EXTRA_DAYS) {
            throw new AssertionError("Moving the end date " + EXTRA_DAYS
                    + " days later should have lengthened the range by exactly " + EXTRA_DAYS
                    + " days, but the length went from " + sourceOutput + " to " + followUpOutput
                    + ", an increase of " + actualIncrease + ".");
        }
    }
}
