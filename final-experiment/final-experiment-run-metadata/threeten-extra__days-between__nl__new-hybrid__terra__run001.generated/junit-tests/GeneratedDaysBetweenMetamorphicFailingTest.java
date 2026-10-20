import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input source(Temporal start, Temporal end) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(start, end);
    }

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input generateFollowUp(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(
                source.arg0().plus(37, ChronoUnit.DAYS),
                source.arg1().plus(37, ChronoUnit.DAYS));
    }

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source,
            int expectedSourceAmount) {
        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(generateFollowUp(source));
        Assertions.assertEquals(expectedSourceAmount, sourceOutput.getAmount());
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
