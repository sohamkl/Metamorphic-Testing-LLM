import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

import java.time.LocalDate;

public class GeneratedRangeLengthMetamorphicFailingTest {

    private static LocalDateRange endingWithLength(LocalDate endExclusive, long lengthInDays) {
        return LocalDateRange.of(endExclusive.minusDays(lengthInDays), endExclusive);
    }

    private static void exercise(LocalDateRange source) {
        int sourceOutput =
                org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArguments =
                RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp =
                (LocalDateRange) followUpArguments[0];
        int followUpOutput =
                org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
