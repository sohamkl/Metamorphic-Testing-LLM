import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    private static void verify(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_SAFE_AND_UNSAFE_SIBLINGS_variation1() {
        String bodyHtml = "<p>safe one</p><script>bad()</script>"
                + "<x-tag>middle marker</x-tag>"
                + "<a href='javascript:bad()'>safe two</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }
}
