import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private static final int SHIFT_DAYS = 37;

    private static Object[] generateFollowUp(
            Temporal startDateInclusive,
            Temporal endDateExclusive) {
        return new Object[]{
                startDateInclusive.plus(SHIFT_DAYS, ChronoUnit.DAYS),
                endDateExclusive.plus(SHIFT_DAYS, ChronoUnit.DAYS)
        };
    }

    private static void exercise(
            Temporal startDateInclusive,
            Temporal endDateExclusive) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(
                        startDateInclusive,
                        endDateExclusive);

        Object[] followUp = generateFollowUp(
                startDateInclusive,
                endDateExclusive);

        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0],
                        (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(
                sourceOutput,
                followUpOutput);
    }

}
