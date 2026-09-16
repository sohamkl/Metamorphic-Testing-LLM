import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private static final long FOLLOW_UP_SHIFT_DAYS = 37L;

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input generateFollowUp(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {

        Temporal shiftedStart = source.arg0().plus(FOLLOW_UP_SHIFT_DAYS, ChronoUnit.DAYS);
        Temporal shiftedEnd = source.arg1().plus(FOLLOW_UP_SHIFT_DAYS, ChronoUnit.DAYS);
        assertNotNull(shiftedStart);
        assertNotNull(shiftedEnd);
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(shiftedStart, shiftedEnd);
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {

        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        MtllmGeneratedDaysBetweenInvocation1uknttg.Input followUp = generateFollowUp(source);
        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input input(
            Temporal start, Temporal end) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(start, end);
    }

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input dateGap(
            long startEpochDay, long gap) {
        LocalDate start = LocalDate.ofEpochDay(startEpochDay);
        LocalDate end = LocalDate.ofEpochDay(startEpochDay + gap);
        return input(start, end);
    }

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input dateTimeGap(
            LocalDateTime start, long days, long hours) {
        return input(start, start.plusDays(days).plusHours(hours));
    }

}
