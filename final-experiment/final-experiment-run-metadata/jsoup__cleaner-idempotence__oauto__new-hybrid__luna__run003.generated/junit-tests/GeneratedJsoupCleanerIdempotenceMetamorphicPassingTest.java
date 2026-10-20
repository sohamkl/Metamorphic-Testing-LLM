import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
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

    @Test
    public void test_EMPTY_FRAGMENT_WITH_NONE_POLICY_1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = new Safelist();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_WHITESPACE_ONLY_TEXT_1() {
        String bodyHtml = " \t\r\n ";
        String baseUri = "http://example.test/page";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_PLAIN_TEXT_NONE_POLICY_1() {
        String bodyHtml = "5 < 6 & \"quoted\"";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_DISALLOWED_ELEMENT_TEXT_RETENTION_1() {
        String bodyHtml = "<video>visible <span>text</span></video><p>after</p>";
        String baseUri = "ftp://example.test/resource";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_SIMPLE_TEXT_POLICY_MARKUP_1() {
        String bodyHtml = "before <p><strong>middle</strong></p> after";
        String baseUri = "relative/base";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_ALLOWED_INLINE_ELEMENTS_1() {
        String bodyHtml = "start <p><em>nested <strong>text</strong></em> end";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_DISALLOWED_SCRIPT_WITH_TEXT_1() {
        String bodyHtml = "before<script>&lt;unsafe&gt;</script><strong>after</strong>";
        String baseUri = "http://example.test/";
        Safelist safelist = new Safelist(Safelist.basic());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_ALLOWED_ATTRIBUTE_AND_REMOVED_ATTRIBUTE_1() {
        String bodyHtml = "<a href=\"https://example.test/x\" title=\"discard\" data-extra=\"x\">link</a>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = new Safelist();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_RELATIVE_LINK_WITH_EMPTY_BASE_1() {
        String bodyHtml = "<a href=\"docs/page.html\">relative link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_ABSOLUTE_HTTP_LINK_1() {
        String bodyHtml = "<a href=\"http://example.test/page\">http link</a>";
        String baseUri = "relative/base";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_UNSAFE_LINK_PROTOCOL_1() {
        String bodyHtml = "before<a href=\"javascript:alert(1)\">unsafe</a><span>after</span>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_QUERY_AND_FRAGMENT_LINK_1() {
        String bodyHtml = "<a href=\"page.html?x=1#part\">query and fragment</a>";
        String baseUri = "http://example.test/root/";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_WITH_IMAGES_ALLOWED_IMAGE_1() {
        String bodyHtml = "image: <img src=\"photo.png\" alt=\"photo\"> end";
        String baseUri = "https://example.test/assets/";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_BASIC_WITH_IMAGES_UNSAFE_IMAGE_URL_1() {
        String bodyHtml = "<div>before<img src=\"javascript:bad()\">after</div>";
        String baseUri = "ftp://example.test/assets/";
        Safelist safelist = new Safelist(Safelist.basicWithImages());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_RELAXED_TABLE_STRUCTURE_1() {
        String bodyHtml = "<table><tbody><tr><td>cell one<td>cell two</tbody>";
        String baseUri = "relative/table";
        Safelist safelist = new Safelist();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_RELAXED_FORM_CONTROLS_1() {
        String bodyHtml = "<form><label for=\"q\">Name &amp; value</label><input id=\"q\" name=\"q\"><button>Send</button></form>";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_RELATIVE_LINK_PRESERVATION_SENTINEL_BRANCH_1() {
        String bodyHtml = "<a href=\"docs/page.html\">preserved link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText()
                .addTags("a")
                .addAttributes("a", "href")
                .preserveRelativeLinks(true);
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_RELATIVE_LINK_PRESERVATION_NONEMPTY_BASE_1() {
        String bodyHtml = "<!-- note --><a href=\"next/page.html\">next</a><a href=\"other.html\">other</a>";
        String baseUri = "https://example.test/base/";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_EMPTY_BASE_NONPRESERVING_POLICY_1() {
        String bodyHtml = "<div><img src=\"image.png\"><a href=\"page.html\">page</a></div>";
        String baseUri = "mailto:user@example.test";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_NONHTTP_BASE_URI_1() {
        String bodyHtml = "prefix <a href=\"relative.html\">relative</a> suffix";
        String baseUri = "file:///tmp/example/";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_COPY_OF_BASIC_POLICY_1() {
        String bodyHtml = "<a href=\"docs/index.html\" title=\"remove\" data-x=\"1\">documentation</a>";
        String baseUri = "";
        Safelist safelist = new Safelist(Safelist.basic());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_NEW_EMPTY_SAFELIST_WITH_MIXED_MARKUP_1() {
        String bodyHtml = "text <custom data-value=\"x\">inside</custom> tail";
        String baseUri = "http://example.test/";
        Safelist safelist = new Safelist();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_MALFORMED_UNBALANCED_NESTING_1() {
        String bodyHtml = "<p><em>visible <strong>text</em> tail";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_CHARACTER_REFERENCE_NORMALIZATION_1() {
        String bodyHtml = "A &amp; B <span>C &#x3c; D</span>";
        String baseUri = "file:///var/www/";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_COMMENTS_AND_METADATA_MARKUP_1() {
        String bodyHtml = "<!-- hidden --><meta charset=\"utf-8\">visible <em>text &amp; more</em>";
        String baseUri = "relative/document";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_SIBLING_ORDER_AND_DUPLICATE_NODES_1() {
        String bodyHtml = "one<a href=\"/a\">A</a><script>bad</script>two<strong>B</strong>three";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_NONASCII_AND_UTF16_TEXT_1() {
        String bodyHtml = "<!-- mark -->Café e\u0301 \uD83D\uDE03 <span>東京</span>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_URL_ATTRIBUTE_WITH_ENTITY_ENCODING_1() {
        String bodyHtml = "<a href=\"docs/page.html?x=1&amp;y=2#part\">encoded URL</a>";
        String baseUri = "https://example.test/root/";
        Safelist safelist = new Safelist(Safelist.basicWithImages());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void test_FULLY_FILTERED_STRUCTURAL_FRAGMENT_1() {
        String bodyHtml = "<script data-x=\"1\"></script><!-- comment --><custom></custom>";
        String baseUri = "ftp://example.test/";
        Safelist safelist = new Safelist();
        verify(bodyHtml, baseUri, safelist);
    }
}
