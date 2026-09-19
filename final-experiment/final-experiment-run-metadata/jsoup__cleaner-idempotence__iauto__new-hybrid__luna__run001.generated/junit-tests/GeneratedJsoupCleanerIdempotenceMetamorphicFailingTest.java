import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning must be idempotent: expected <" + sourceOutput
                            + "> but second cleaning produced <" + followUpOutput + ">");
        }
    }
}
