import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {
    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError("Cleaning must be idempotent: source output <"
                    + sourceOutput + "> differs from follow-up output <" + followUpOutput + ">");
        }
    }

}
