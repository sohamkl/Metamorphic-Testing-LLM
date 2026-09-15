import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private static final class TemporalPair {
        private final Temporal start;
        private final Temporal end;

        private TemporalPair(Temporal start, Temporal end) {
            this.start = start;
            this.end = end;
        }
    }

    private static TemporalPair generateFollowUp(TemporalPair source) {
        return new TemporalPair(
                source.start.plus(37, ChronoUnit.DAYS),
                source.end.plus(37, ChronoUnit.DAYS));
    }

    private static void assertMetamorphicRelation(
            Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(
                sourceOutput.getAmount(),
                followUpOutput.getAmount());
    }

    private static void assertMetamorphicRelationFor(TemporalPair source) {
        TemporalPair followUp = generateFollowUp(source);
        Days sourceOutput = Days.between(source.start, source.end);
        Days followUpOutput = Days.between(followUp.start, followUp.end);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertOverflowRelation(TemporalPair source) {
        TemporalPair followUp = generateFollowUp(source);
        ArithmeticException sourceFailure = Assertions.assertThrows(
                ArithmeticException.class,
                () -> Days.between(source.start, source.end));
        ArithmeticException followUpFailure = Assertions.assertThrows(
                ArithmeticException.class,
                () -> Days.between(followUp.start, followUp.end));
        Assertions.assertEquals(
                sourceFailure.getClass(),
                followUpFailure.getClass());
    }

}
