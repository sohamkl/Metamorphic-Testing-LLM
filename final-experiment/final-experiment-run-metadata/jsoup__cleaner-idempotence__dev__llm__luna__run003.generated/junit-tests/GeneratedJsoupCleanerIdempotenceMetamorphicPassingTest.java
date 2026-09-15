import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static void verify(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Safelist copied(Safelist source) {
        return new Safelist(source);
    }

    @Test
    void EMPTY_FRAGMENT_NONE_1() {
        verify("", "", Safelist.none());
    }

    @Test
    void PLAIN_TEXT_NONE_1() {
        verify("5 < 6 & 7 > 3", "http://example.test/page", Safelist.none());
    }

    @Test
    void NONE_MIXED_TAGS_1() {
        verify("before <b>bold</b><script>alert('x')</script> after",
                "https://example.test/", Safelist.none());
    }

    @Test
    void SIMPLE_TEXT_ALLOWED_AND_DISALLOWED_1() {
        verify("<b>Bold</b> <em>emphasis</em><div>visible block text</div>",
                "https://example.test/start", Safelist.simpleText());
    }

    @Test
    void SIMPLE_TEXT_ALLOWED_AND_DISALLOWED_2() {
        verify("<u>underlined</u><div>flattened text</div><p>paragraph text</p>",
                "https://example.test/", Safelist.simpleText());
    }

    @Test
    void SIMPLE_TEXT_ATTRIBUTES_1() {
        verify("<b title=\"t\" onclick=\"alert(1)\" data-x=\"y\">text</b>",
                "", Safelist.simpleText());
    }

    @Test
    void BASIC_ALLOWED_ELEMENTS_1() {
        verify("<p>A <em>simple</em> paragraph with <a href=\"https://example.test\">a link</a>.</p>",
                "https://example.test/articles/", Safelist.basic());
    }

    @Test
    void BASIC_ALLOWED_ELEMENTS_2() {
        verify("<blockquote><p>Quoted <code>source</code></p></blockquote>"
                        + "<ul><li>one</li><li>two</li></ul>",
                "https://example.test/content", Safelist.basic());
    }

    @Test
    void BASIC_DISALLOWED_ELEMENTS_1() {
        verify("<p>Visible text</p><script>secret()</script><style>.x{display:none}</style>",
                "http://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_DISALLOWED_ELEMENTS_2() {
        verify("<strong>Kept text</strong><iframe src=\"https://evil.example\">frame text</iframe>",
                "http://example.test/page", Safelist.basic());
    }

    @Test
    void BASIC_ATTRIBUTE_FILTERING_1() {
        verify("<p id=\"p1\" class=\"note\" style=\"color:red\" onclick=\"run()\" data-extra=\"x\">text</p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_ATTRIBUTE_FILTERING_2() {
        verify("<a href=\"docs/index.html\" title=\"Docs\" rel=\"nofollow\" "
                        + "target=\"_blank\" onmouseover=\"bad()\">documentation</a>",
                "https://example.test/help/", Safelist.basic());
    }

    @Test
    void BASIC_SAFE_LINK_1() {
        verify("<p>Visit <a href=\"http://example.test/home\">the home page</a>.</p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_SAFE_LINK_2() {
        verify("<a href=\"https://secure.example.test/account\" title=\"Account\">account</a>",
                "http://example.test/start", Safelist.basic());
    }

    @Test
    void BASIC_UNSAFE_PROTOCOL_1() {
        verify("<a href=\"javascript:alert('x')\">safe-looking link text</a>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_WITH_BASE_1() {
        verify("<p>Read <a href=\"docs/page.html\">the documentation</a></p>",
                "https://example.test/articles/index.html", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_WITH_BASE_2() {
        verify("<a href=\"../images/info.html\">more information</a>",
                "http://example.test/guide/chapter.html", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_EMPTY_BASE_1() {
        verify("<a href=\"docs/page.html\">relative documentation</a>",
                "", Safelist.basic());
    }

    @Test
    void BASIC_WITH_IMAGES_1() {
        verify("<p>Logo: <img src=\"https://cdn.example.test/logo.png\" "
                        + "alt=\"Example logo\"> for visitors.</p>",
                "https://example.test/", Safelist.basicWithImages());
    }

    @Test
    void BASIC_WITH_IMAGES_2() {
        verify("<div>Before <img src=\"/images/photo.jpg\" alt=\"Photo\"> after</div>",
                "https://example.test/gallery/index.html", Safelist.basicWithImages());
    }

    @Test
    void IMAGE_UNSAFE_SOURCE_1() {
        verify("<p>Fallback text <img src=\"javascript:alert(1)\" alt=\"fallback\"></p>",
                "http://example.test/page", Safelist.basicWithImages());
    }

    @Test
    void RELAXED_RICH_MARKUP_1() {
        verify("<div class=\"content\"><h2>Heading</h2><p>Text with "
                        + "<strong>emphasis</strong> and <a href=\"https://example.test\">link</a>.</p>"
                        + "<table><tr><td>Cell</td></tr></table></div>",
                "https://example.test/article", Safelist.relaxed());
    }

    @Test
    void RELAXED_RICH_MARKUP_2() {
        verify("<section><blockquote cite=\"https://example.test/source\">Quote</blockquote>"
                        + "<p><img src=\"https://cdn.example.test/picture.png\" alt=\"Picture\"> caption</p>"
                        + "</section>",
                "https://example.test/", Safelist.relaxed());
    }

    @Test
    void RELAXED_UNSAFE_MIX_1() {
        verify("<div class=\"safe\">Visible text <span data-unknown=\"x\" "
                        + "onclick=\"bad()\">content</span></div><script>evil()</script>",
                "http://example.test/page", Safelist.relaxed());
    }

    @Test
    void MALFORMED_NESTING_1() {
        verify("<p>Unclosed <b>bold <i>and italic</p> trailing text",
                "https://example.test/", Safelist.relaxed());
    }

    @Test
    void MALFORMED_NESTING_2() {
        verify("<div><p>First <em>misnested</div> second</em><p>third",
                "https://example.test/path", Safelist.relaxed());
    }

    @Test
    void COMMENTS_AND_DECLARATIONS_1() {
        verify("<!-- hidden comment --><!DOCTYPE html>Visible text",
                "https://example.test/", Safelist.none());
    }

    @Test
    void ENTITY_TEXT_NORMALIZATION_1() {
        verify("<p>Tom &amp; Jerry &lt;3 &#x1F600;</p>",
                "http://example.test/", Safelist.basic());
    }

    @Test
    void ENTITY_TEXT_NORMALIZATION_2() {
        verify("Fish &amp; Chips &lt; fresh &gt; frozen",
                "http://example.test/page", Safelist.none());
    }

    @Test
    void NONEMPTY_NONURL_BASE_1() {
        verify("<p>Plain <em>visible</em> text without URLs.</p>",
                "not-an-absolute-url", Safelist.basic());
    }

    @Test
    void COPIED_SAFELIST_1() {
        Safelist source = Safelist.relaxed();
        Safelist copiedPolicy = copied(source);
        verify("<p class=\"article\">Copied policy <strong>text</strong></p>",
                "https://example.test/article", copiedPolicy);
    }

    @Test
    void DEFAULT_EMPTY_SAFELIST_1() {
        verify("<div>Visible text in a newly empty policy</div>",
                "http://example.test/", new Safelist());
    }

    @Test
    void ALL_CONTENT_FILTERED_1() {
        verify("<script>only unsafe content</script><style>.hidden{display:none}</style>",
                "", Safelist.none());
    }

    @Test
    void ALREADY_CLEAN_FRAGMENT_1() {
        Safelist source = Safelist.basic();
        verify("<p>Already clean <em>markup</em> with "
                        + "<a href=\"https://example.test\">a safe link</a>.</p>",
                "https://example.test/", copied(source));
    }

    @Test
    void ALREADY_CLEAN_FRAGMENT_2() {
        verify("<p>Relaxed <strong>content</strong> with "
                        + "<img src=\"https://cdn.example.test/image.png\" alt=\"image\"></p>",
                "https://example.test/page", Safelist.relaxed());
    }
}
