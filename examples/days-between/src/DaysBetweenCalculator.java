import java.time.LocalDate;

import org.threeten.extra.Days;

/**
 * Thin adapter over {@link org.threeten.extra.Days#between} from threeten-extra.
 *
 * <p>The library method takes two temporals; the pipeline drives a single reference-type
 * source input, so the two endpoints are wrapped in {@link DaysBetweenInput}. No day-count
 * logic lives here — this class only unwraps and delegates.</p>
 */
public final class DaysBetweenCalculator {

    private DaysBetweenCalculator() {
    }

    /**
     * Returns the number of whole days between the input's two endpoints.
     *
     * @param input start (inclusive) and end (exclusive) dates
     * @return the day count, negative when end precedes start
     */
    public static int daysBetween(DaysBetweenInput input) {
        return Days.between(input.start(), input.end()).getAmount();
    }
}

/**
 * Source input for the days-between example: a pair of dates.
 */
final class DaysBetweenInput {
    private final LocalDate start;
    private final LocalDate end;

    DaysBetweenInput(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    LocalDate start() {
        return start;
    }

    LocalDate end() {
        return end;
    }
}
