import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning must be idempotent: expected <" + sourceOutput
                            + "> but second cleaning produced <" + followUpOutput + ">");
        }
    }

    @Test
    public void EMPTY_FRAGMENT_NONE_variation1() {
        String first = Jsoup.clean("", "", Safelist.none());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp("", "", Safelist.none());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void PLAIN_TEXT_SIMPLE_TEXT_variation1() {
        String first = Jsoup.clean("plain text with no tags", "https://example.test/page", Safelist.simpleText());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                "plain text with no tags", "https://example.test/page", Safelist.simpleText());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ESCAPED_COMPARISON_TEXT_variation1() {
        String first = Jsoup.clean("5 &lt; 6 &amp; 7", "https://example.test/page", Safelist.none());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                "5 &lt; 6 &amp; 7", "https://example.test/page", Safelist.none());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void BASIC_FORMATTING_FRAGMENT_variation1() {
        String html = "<p><b>Bold</b> and <i>italic</i></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void DISALLOWED_SCRIPT_WITH_TEXT_variation1() {
        String html = "Visible<script>alert(1)</script><p>content</p>";
        String first = Jsoup.clean(html, "file:///tmp/page.html", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "file:///tmp/page.html", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void UNKNOWN_ELEMENT_TEXT_variation1() {
        String html = "<custom-widget>custom text</custom-widget>";
        String first = Jsoup.clean(html, "", Safelist.none());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "", Safelist.none());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ATTRIBUTE_FILTERING_BASIC_variation1() {
        String html = "<p class='allowed-looking' data-secret='remove'>Text</p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_variation1() {
        String html = "<p onclick='alert(1)'>click me</p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_variation2() {
        String html = "<div onload='bad()'><p><b>nested</b></p></div>";
        String first = Jsoup.clean(html, "relative/base", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "relative/base", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void BASIC_ABSOLUTE_HTTP_LINK_variation1() {
        String html = "<a href='http://example.test/path'>HTTP link</a>";
        String first = Jsoup.clean(html, "https://origin.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://origin.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void BASIC_ABSOLUTE_HTTPS_LINK_variation1() {
        String html = "<a href='https://example.test/path'>HTTPS link</a>";
        String first = Jsoup.clean(html, "https://origin.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://origin.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void JAVASCRIPT_LINK_PROTOCOL_variation1() {
        String html = "<a href='javascript:alert(1)'>unsafe link</a>";
        String first = Jsoup.clean(html, "https://origin.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://origin.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void DATA_LINK_PROTOCOL_variation1() {
        String html = "<a href='data:text/plain,hello'>data link</a>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void RELATIVE_LINK_NONEMPTY_BASE_variation1() {
        String html = "<a href='docs/page.html'>documentation</a>";
        String first = Jsoup.clean(html, "https://example.test/root/index.html", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/root/index.html", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void RELATIVE_LINK_EMPTY_BASE_variation1() {
        String html = "<a href='docs/page.html'>documentation</a>";
        String first = Jsoup.clean(html, "", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_variation1() {
        String html = "<a href='//cdn.example.test/resource'>CDN</a>";
        String first = Jsoup.clean(html, "https://origin.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://origin.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void MAILTO_LINK_variation1() {
        String html = "<a href='mailto:user@example.test'>Email us</a>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void IMAGE_WITH_SAFE_LOOKING_SRC_variation1() {
        String html = "<img src='https://example.test/image.png' alt='image'>";
        String first = Jsoup.clean(html, "https://origin.test/page", Safelist.basicWithImages());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://origin.test/page", Safelist.basicWithImages());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void IMAGE_WITH_UNSAFE_SRC_variation1() {
        String html = "<img src='data:image/png;base64,AAAA' alt='image'>";
        String first = Jsoup.clean(html, "", Safelist.basicWithImages());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "", Safelist.basicWithImages());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void IMAGE_UNDER_BASIC_variation1() {
        String html = "<img src='https://example.test/image.png'>";
        String first = Jsoup.clean(html, "https://origin.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://origin.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void NONE_PRESERVES_TEXT_ONLY_variation1() {
        String html = "<p>Hello <b>world</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.none());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.none());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void SIMPLE_TEXT_MARKUP_variation1() {
        String html = "<p class='x'>Visible <b>text</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.simpleText());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.simpleText());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void RELAXED_TABLE_FRAGMENT_variation1() {
        String html = "<table><tr><td>one</td><td>two</td></tr></table>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void NESTED_MIXED_POLICY_FRAGMENT_variation1() {
        String html = "<div><p><a href='docs/page.html'>link <script>bad()</script></a></p></div>";
        String first = Jsoup.clean(html, "https://example.test/root/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/root/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void MALFORMED_NESTING_variation1() {
        String html = "<p><b>text</p><i>more</i>";
        String first = Jsoup.clean(html, "file:///tmp/page.html", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "file:///tmp/page.html", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void COMMENT_MIXED_FRAGMENT_variation1() {
        String html = "before<!-- hidden comment -->after";
        String first = Jsoup.clean(html, "", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void DOCTYPE_IN_BODY_FRAGMENT_variation1() {
        String html = "<!DOCTYPE html><p>body text</p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_variation1() {
        String html = " \t\n  ";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.none());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.none());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void SPECIAL_ATTRIBUTE_QUOTES_variation1() {
        String html = "<p title='quoted &amp; value with spaces'>text</p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void REPEATED_SIBLINGS_variation1() {
        String html = "start<p>one</p><b>two</b><script>bad()</script><i>three</i><div>end</div>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void EMPTY_BASE_WITH_BASIC_POLICY_variation1() {
        String html = "<a href='relative/page.html'>relative</a>";
        String first = Jsoup.clean(html, "", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void EMPTY_BASE_WITH_BASIC_POLICY_variation2() {
        String html = "<a href='other/page.html'>another relative link</a>";
        String first = Jsoup.clean(html, "", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ABSOLUTE_NONHTTP_BASE_variation1() {
        String html = "<a href='docs/page.html'>file-based link</a>";
        String first = Jsoup.clean(html, "file:///tmp/page.html", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "file:///tmp/page.html", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void SAFE_OUTPUT_RECLEAN_variation1() {
        String html = "<p><b>safe text</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void SAFE_OUTPUT_RECLEAN_variation2() {
        String html = "<div><p><a href='https://example.test/x'>safe link</a></p></div>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void MIXED_URL_PROTOCOLS_variation1() {
        String html = "<a href='http://example.test/a'>http</a>"
                + "<a href='https://example.test/b'>https</a>"
                + "<a href='javascript:bad()'>bad</a>"
                + "<a href='docs/c.html'>relative</a>";
        String first = Jsoup.clean(html, "https://example.test/root/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/root/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ALL_POLICY_FACTORIES_TEXT_FRAGMENT_variation1() {
        String html = "<p class='x'>policy text <b>here</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", new Safelist());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", new Safelist());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ALL_POLICY_FACTORIES_TEXT_FRAGMENT_variation2() {
        String html = "<p class='x'>policy text <b>here</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.basic());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.basic());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ALL_POLICY_FACTORIES_TEXT_FRAGMENT_variation3() {
        String html = "<p class='x'>policy text <b>here</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.basicWithImages());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.basicWithImages());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ALL_POLICY_FACTORIES_TEXT_FRAGMENT_variation4() {
        String html = "<p class='x'>policy text <b>here</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.none());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.none());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ALL_POLICY_FACTORIES_TEXT_FRAGMENT_variation5() {
        String html = "<p class='x'>policy text <b>here</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.relaxed());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.relaxed());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }

    @Test
    public void ALL_POLICY_FACTORIES_TEXT_FRAGMENT_variation6() {
        String html = "<p class='x'>policy text <b>here</b></p>";
        String first = Jsoup.clean(html, "https://example.test/page", Safelist.simpleText());
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                html, "https://example.test/page", Safelist.simpleText());
        String second = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(first, second);
    }
}
