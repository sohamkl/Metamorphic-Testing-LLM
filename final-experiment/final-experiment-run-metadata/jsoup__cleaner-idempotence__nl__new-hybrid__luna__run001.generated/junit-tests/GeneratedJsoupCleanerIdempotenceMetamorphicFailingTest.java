import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {
    private static String generateFollowUp(String cleaned) {
        return cleaned;
    }

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(generateFollowUp(sourceOutput), baseUri, safelist);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static Safelist copied(Safelist source) {
        return new Safelist(source);
    }

    private static Safelist preservingRelativeLinks(Safelist source) {
        return source.preserveRelativeLinks(true);
    }

    private static String longText() {
        return "plain text ".repeat(30);
    }

    private static String longMixedFragment() {
        return "<p>Alpha &amp; beta <a href='https://example.com/a' title='remove'>link</a></p>"
                + "<div><strong>nested</strong><em>content</em><script>discarded</script></div>"
                + "<img src='https://example.com/image.png' onerror='remove'>"
                + "<blockquote cite='https://example.com/quote'>quoted</blockquote>"
                + " repeated content ".repeat(20);
    }

    @Test
    public void MALFORMED_BALANCED_FRAGMENT_variation2() {
        assertMetamorphicRelationFor("<p>one<div>two &amp; three", "relative/page.html",
                Safelist.basicWithImages());
    }
}
