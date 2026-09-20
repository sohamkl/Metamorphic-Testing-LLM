import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError(
                    "Shifting both endpoints forward by the same number of days changed the day count");
        }
    }

    private void verifyRelation(Temporal start, Temporal end) {
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0],
                (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
