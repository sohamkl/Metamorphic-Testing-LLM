import org.threeten.extra.Days;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Objects;

/**
 * MR for Days.between (threeten-extra).
 *
 * Shifting both dates forward by the same number of days shouldn't change the gap between
 * them - only how far apart they are matters, not where they sit on the calendar.
 */
public final class DaysBetweenMetamorphicSpec {

    /** Days added to both endpoints to build the follow-up input. */
    private static final int SHIFT_DAYS = 37;

    private DaysBetweenMetamorphicSpec() {
    }

    public static Object[] generateFollowUp(Temporal startDateInclusive, Temporal endDateExclusive) {
        Objects.requireNonNull(startDateInclusive, "startDateInclusive");
        Objects.requireNonNull(endDateExclusive, "endDateExclusive");
        
        return new Object[]{
                startDateInclusive.plus(SHIFT_DAYS, ChronoUnit.DAYS),
                endDateExclusive.plus(SHIFT_DAYS, ChronoUnit.DAYS)};
    }

    public static void assertRelation(Days sourceOutput, Days followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");
        
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError("Shifting both endpoints by " + SHIFT_DAYS
                    + " days changed the day count from " + sourceOutput.getAmount()
                    + " to " + followUpOutput.getAmount() + ".");
        }
    }
}
