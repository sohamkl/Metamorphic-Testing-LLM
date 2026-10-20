import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private void verify(Temporal start, Temporal end) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0],
                        (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
