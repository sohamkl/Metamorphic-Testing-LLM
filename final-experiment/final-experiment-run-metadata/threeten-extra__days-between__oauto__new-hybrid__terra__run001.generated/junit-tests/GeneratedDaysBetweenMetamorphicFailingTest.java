import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private Temporal[] generateFollowUp(Temporal startDateInclusive, Temporal endDateExclusive) {
        return new Temporal[] {
                startDateInclusive.plus(37, ChronoUnit.DAYS),
                endDateExclusive.plus(37, ChronoUnit.DAYS)
        };
    }

}
