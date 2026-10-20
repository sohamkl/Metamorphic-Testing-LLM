import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private static final class Input {
        private final Temporal start;
        private final Temporal end;

        private Input(Temporal start, Temporal end) {
            this.start = start;
            this.end = end;
        }
    }

    private static Input generateFollowUp(Input source) {
        return new Input(
                source.start.plus(37, ChronoUnit.DAYS),
                source.end.plus(37, ChronoUnit.DAYS));
    }

    private static void assertMetamorphicRelationFor(Input source) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(source.start, source.end);
        Input followUp = generateFollowUp(source);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUp.start, followUp.end);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            org.threeten.extra.Days sourceOutput,
            org.threeten.extra.Days followUpOutput) {
        Assertions.assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

}
