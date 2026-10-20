import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {
    private static Object[] generateFollowUp(String bodyHtml, String baseUri, Safelist safelist) {
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        return new Object[] { cleanedHtml, baseUri, safelist };
    }

    private static void verify(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
