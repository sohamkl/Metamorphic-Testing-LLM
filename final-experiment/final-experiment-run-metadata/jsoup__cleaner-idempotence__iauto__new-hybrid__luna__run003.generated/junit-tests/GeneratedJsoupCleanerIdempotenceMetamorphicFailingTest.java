import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError("Cleaning is not idempotent: <" + sourceOutput
                    + "> != <" + followUpOutput + ">");
        }
    }

    @Test
    public void MIXED_SAFE_UNSAFE_FRAGMENT_1() {
        String bodyHtml = "<p data-private=\"x\">Safe text <script>bad()</script> <a href=\"javascript:void(0)\">link</a></p>";
        String baseUri = "https://example.test/content/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
