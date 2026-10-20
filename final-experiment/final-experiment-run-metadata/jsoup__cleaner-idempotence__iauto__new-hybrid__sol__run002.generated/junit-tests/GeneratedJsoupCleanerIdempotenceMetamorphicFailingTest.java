import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning the follow-up HTML must produce exactly the source output: expected <"
                            + sourceOutput + "> but was <" + followUpOutput + ">");
        }
    }

}
