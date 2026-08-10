/**
 * Developer-owned metamorphic relation for {@code Days.between} (threeten-extra).
 *
 * <p><b>Relation (translation invariance):</b> shifting both endpoints of a date interval
 * forward by the same number of days must not change the number of days between them. The
 * day count depends only on the distance between the endpoints, never on their absolute
 * position on the timeline.</p>
 */
public final class DaysBetweenMetamorphicSpec {

    /** Days added to both endpoints to build the follow-up input. */
    private static final int SHIFT_DAYS = 37;

    private DaysBetweenMetamorphicSpec() {
    }

    public static DaysBetweenInput generateFollowUp(DaysBetweenInput source) {
        return new DaysBetweenInput(
                source.start().plusDays(SHIFT_DAYS),
                source.end().plusDays(SHIFT_DAYS));
    }

    public static void assertRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("Shifting both endpoints by " + SHIFT_DAYS
                    + " days changed the day count from " + sourceOutput
                    + " to " + followUpOutput + ".");
        }
    }
}
