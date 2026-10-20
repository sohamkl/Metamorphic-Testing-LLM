import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private static final long SHIFT_DAYS = 37L;

    private static final class TemporalPair {
        private final Temporal start;
        private final Temporal end;

        private TemporalPair(Temporal start, Temporal end) {
            this.start = start;
            this.end = end;
        }
    }

    private TemporalPair generateFollowUp(TemporalPair source) {
        return new TemporalPair(
                source.start.plus(SHIFT_DAYS, ChronoUnit.DAYS),
                source.end.plus(SHIFT_DAYS, ChronoUnit.DAYS));
    }

    private Days runSut(TemporalPair input) {
        return Days.between(input.start, input.end);
    }

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private void assertMetamorphicRelationFor(TemporalPair source, int expectedSourceAmount) {
        Days sourceOutput = runSut(source);
        Assertions.assertEquals(expectedSourceAmount, sourceOutput.getAmount());
        Days followUpOutput = runSut(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
