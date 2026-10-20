import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private void exerciseMetamorphicRelation(Temporal startDateInclusive, Temporal endDateExclusive) {
        Days sourceOutput = org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(
                startDateInclusive, endDateExclusive);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private HijrahDate findHijrahMonthBoundaryStart() {
        HijrahDate candidate = HijrahDate.from(LocalDate.of(2024, 1, 1));
        for (int i = 0; i < 400; i++) {
            HijrahDate next = candidate.plus(1, ChronoUnit.DAYS);
            if (candidate.get(ChronoField.MONTH_OF_YEAR) != next.get(ChronoField.MONTH_OF_YEAR)) {
                return candidate;
            }
            candidate = next;
        }
        throw new IllegalStateException("No Hijrah month boundary found");
    }

}
