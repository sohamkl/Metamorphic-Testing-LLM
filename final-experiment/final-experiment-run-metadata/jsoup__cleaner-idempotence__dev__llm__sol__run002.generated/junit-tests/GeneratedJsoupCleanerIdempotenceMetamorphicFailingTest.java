import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    @Test
    public void BASIC_MIXED_SAFE_UNSAFE_TAGS_variation1_paragraphAndUnknownElement() {
        String bodyHtml = "<p>known paragraph</p><unknown-tag>unknown wrapper text</unknown-tag>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
