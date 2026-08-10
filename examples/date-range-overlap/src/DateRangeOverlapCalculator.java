import java.time.LocalDate;

import org.threeten.extra.LocalDateRange;

/**
 * Thin adapter over {@link org.threeten.extra.LocalDateRange#overlaps} from threeten-extra.
 *
 * <p>{@code overlaps} is an instance method taking one argument, so the receiver and the
 * argument are wrapped together in {@link DateRangePairInput}. The ranges are carried as
 * four dates rather than as live {@code LocalDateRange} objects so the executed-data JSON
 * stays flat. No overlap logic lives here — this class only builds the ranges and delegates.</p>
 */
public final class DateRangeOverlapCalculator {

    private DateRangeOverlapCalculator() {
    }

    /**
     * Returns whether the first range overlaps the second.
     *
     * @param input the two half-open date ranges
     * @return true when the ranges share part of the timeline
     */
    public static boolean overlaps(DateRangePairInput input) {
        LocalDateRange first = LocalDateRange.of(input.firstStart(), input.firstEnd());
        LocalDateRange second = LocalDateRange.of(input.secondStart(), input.secondEnd());
        return first.overlaps(second);
    }
}

/**
 * Source input for the range-overlap example: two half-open date ranges.
 *
 * <p>Each range is {@code [start, end)}; {@code start} must not be after {@code end}.</p>
 */
final class DateRangePairInput {
    private final LocalDate firstStart;
    private final LocalDate firstEnd;
    private final LocalDate secondStart;
    private final LocalDate secondEnd;

    DateRangePairInput(LocalDate firstStart, LocalDate firstEnd,
                       LocalDate secondStart, LocalDate secondEnd) {
        this.firstStart = firstStart;
        this.firstEnd = firstEnd;
        this.secondStart = secondStart;
        this.secondEnd = secondEnd;
    }

    LocalDate firstStart() {
        return firstStart;
    }

    LocalDate firstEnd() {
        return firstEnd;
    }

    LocalDate secondStart() {
        return secondStart;
    }

    LocalDate secondEnd() {
        return secondEnd;
    }
}
