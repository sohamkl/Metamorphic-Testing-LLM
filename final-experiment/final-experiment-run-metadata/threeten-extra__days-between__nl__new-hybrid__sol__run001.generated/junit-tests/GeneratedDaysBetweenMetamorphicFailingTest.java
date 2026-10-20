import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private static final long SHIFT_DAYS = 37L;

    private static MtllmGeneratedDaysBetweenInvocation1uknttg.Input input(
            Temporal start, Temporal end) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(start, end);
    }

    private static MtllmGeneratedDaysBetweenInvocation1uknttg.Input generateFollowUp(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {
        Temporal shiftedStart = source.arg0().plus(SHIFT_DAYS, ChronoUnit.DAYS);
        Temporal shiftedEnd = source.arg1().plus(SHIFT_DAYS, ChronoUnit.DAYS);
        return input(shiftedStart, shiftedEnd);
    }

    private static void assertMetamorphicRelation(
            Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(
                sourceOutput.getAmount(),
                followUpOutput.getAmount());
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {
        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(
                generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertSourceAndMetamorphicRelation(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source,
            int expectedSourceAmount) {
        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        Assertions.assertEquals(expectedSourceAmount, sourceOutput.getAmount());

        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(
                generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
