import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicFailingTest {

    private static Object[] generateFollowUp(
            java.time.temporal.Temporal startDateInclusive,
            java.time.temporal.Temporal endDateExclusive) {
        return new Object[]{
                startDateInclusive.plus(37, java.time.temporal.ChronoUnit.DAYS),
                endDateExclusive.plus(37, java.time.temporal.ChronoUnit.DAYS)
        };
    }

    @Test
    public void ZONED_DATE_TIME_DIFFERENT_DST_SCHEDULES_variation1() {
        java.time.ZonedDateTime start =
                java.time.ZonedDateTime.parse("2020-02-10T12:00:00-05:00[America/New_York]");
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2020-02-11T16:30:00Z[Europe/London]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
