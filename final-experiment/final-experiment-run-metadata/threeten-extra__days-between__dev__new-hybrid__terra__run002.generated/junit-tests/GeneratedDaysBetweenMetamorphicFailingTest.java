import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private void exercise(Temporal startDateInclusive, Temporal endDateExclusive) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(startDateInclusive, endDateExclusive);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
