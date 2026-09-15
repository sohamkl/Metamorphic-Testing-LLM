import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {
    private static void assertMetamorphicRelationFor(
            String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(sourceOutput, baseUri, safelist);
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static Safelist copied(Safelist source) {
        return new Safelist(source);
    }

    private static String largeFragment() {
        return "<div class='outer' data-drop='x'><p>Hello &amp; welcome "
                + "<a href='../docs/page.html?x=1#part' title='keep'>link</a> "
                + "<script>alert('x')</script><!-- note --></p>"
                + "<ul><li>one<li>two<li><img src='https://example.com/i.png' "
                + "onclick='bad()' alt='image'></ul></div>"
                + "<table><tr><td>cell</td></tr></table>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>";
    }

    @Test
    public void WHITESPACE_AND_LINE_ENDINGS_variation1() {
        String bodyHtml = " \t\r\n<p> spaced </p> \n <b>line</b>\t ";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }
}
