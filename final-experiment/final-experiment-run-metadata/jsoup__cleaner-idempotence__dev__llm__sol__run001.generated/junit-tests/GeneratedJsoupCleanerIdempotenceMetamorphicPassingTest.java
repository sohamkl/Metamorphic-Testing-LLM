import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static void verifyRelation(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUpInput =
                CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUpInput[0],
                (String) followUpInput[1],
                (Safelist) followUpInput[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static String repeatCharacter(char character, int count) {
        char[] characters = new char[count];
        java.util.Arrays.fill(characters, character);
        return new String(characters);
    }

    @Test
    void EMPTY_FRAGMENT_NONE_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void WHITESPACE_ONLY_FRAGMENT_variation1() {
        String bodyHtml = " \t\n\r";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void PLAIN_TEXT_NONE_variation1() {
        String bodyHtml = "alpha beta 123";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void UNICODE_TEXT_NONE_variation1() {
        String bodyHtml = "café 東京 😀";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void NAMED_ENTITY_NORMALIZATION_variation1() {
        String bodyHtml = "Tom &amp; Jerry &lt; 6";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void NUMERIC_ENTITY_NORMALIZATION_variation1() {
        String bodyHtml = "&#65; &#x42; &#169;";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void COMMENT_ONLY_REMOVED_variation1() {
        String bodyHtml = "<!-- secret -->";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void COMMENT_BETWEEN_TEXT_variation1() {
        String bodyHtml = "left<!-- hidden -->right";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void SCRIPT_ONLY_DISCARDED_variation1() {
        String bodyHtml = "<script>alert(1)</script>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void STYLE_ONLY_DISCARDED_variation1() {
        String bodyHtml = "<style>body{display:none}</style>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void DISALLOWED_WRAPPER_RETAINS_TEXT_variation1() {
        String bodyHtml = "<iframe>fallback text</iframe>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void DISALLOWED_OUTER_ALLOWED_INNER_variation1() {
        String bodyHtml = "<div>before <strong>inside</strong> after</div>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void SIMPLE_TEXT_INLINE_TAGS_variation1() {
        String bodyHtml = "<b>b</b><em>e</em><i>i</i><strong>s</strong><u>u</u>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void SIMPLE_TEXT_BLOCK_UNWRAPPED_variation1() {
        String bodyHtml = "<p>one <b>two</b></p>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void BASIC_PARAGRAPH_AND_LIST_variation1() {
        String bodyHtml = "<p>intro</p><ul><li>one</li><li>two</li></ul>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void RELAXED_TABLE_STRUCTURE_variation1() {
        String bodyHtml = "<table><tbody><tr><th>H</th><td>D</td></tr></tbody></table>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void BASIC_IMAGE_NOT_ADMITTED_variation1() {
        String bodyHtml = "<img src=\"https://example.test/a.png\" alt=\"A\">";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void BASIC_WITH_IMAGES_HTTPS_IMAGE_variation1() {
        String bodyHtml = "<img src=\"https://cdn.example.test/a.png\" alt=\"A\" width=\"10\" height=\"20\">";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void RELAXED_HEADING_AND_BLOCKQUOTE_variation1() {
        String bodyHtml = "<h2>Title</h2><blockquote>Quote</blockquote>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void EMPTY_CONSTRUCTOR_POLICY_variation1() {
        String bodyHtml = "<p><b>visible</b></p>";
        String baseUri = "";
        Safelist safelist = new Safelist();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void COPY_CONSTRUCTOR_RELAXED_POLICY_variation1() {
        String bodyHtml = "<p>copy <strong>policy</strong></p>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist(Safelist.relaxed());
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void ALLOWED_LINK_ATTRIBUTE_variation1() {
        String bodyHtml = "<a href=\"https://example.test/page\">link</a>";
        String baseUri = "https://origin.example.test/root/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void UNKNOWN_ATTRIBUTE_REMOVED_variation1() {
        String bodyHtml = "<p mystery=\"value\">text</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void EVENT_HANDLER_ATTRIBUTE_REMOVED_variation1() {
        String bodyHtml = "<a href=\"https://example.test/\" onclick=\"evil()\">go</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void STYLE_ATTRIBUTE_REMOVED_variation1() {
        String bodyHtml = "<p style=\"background:url(javascript:evil())\">text</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void DUPLICATE_ATTRIBUTE_NORMALIZATION_variation1() {
        String bodyHtml = "<a href=\"https://one.example/\" href=\"https://two.example/\">x</a>";
        String baseUri = "https://base.example/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void MIXED_SAFE_AND_UNSAFE_ATTRIBUTES_variation1() {
        String bodyHtml = "<a href=\"https://example.test/x\" data-x=\"1\" onmouseover=\"evil()\">x</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void ABSOLUTE_HTTPS_LINK_variation1() {
        String bodyHtml = "<a href=\"https://example.test/a?q=1#f\">HTTPS</a>";
        String baseUri = "http://origin.example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void MAILTO_LINK_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"mailto:user@example.test\">mail</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void JAVASCRIPT_LINK_REMOVED_variation1() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">click</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void MIXED_CASE_JAVASCRIPT_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"JaVaScRiPt:alert(1)\">click</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void DATA_IMAGE_PROTOCOL_REMOVED_variation1() {
        String bodyHtml = "<img src=\"data:image/png;base64,AAAA\" alt=\"x\">";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void RELATIVE_LINK_WITH_BASE_RESOLVED_variation1() {
        String bodyHtml = "<a href=\"docs/page.html\">docs</a>";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void ROOT_RELATIVE_LINK_WITH_BASE_variation1() {
        String bodyHtml = "<a href=\"/account\">account</a>";
        String baseUri = "https://example.test/root/page";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void RELATIVE_LINK_PRESERVED_WITH_BASE_variation1() {
        String bodyHtml = "<a href=\"../next\">next</a>";
        String baseUri = "https://example.test/a/b/";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void EMPTY_BASE_PRESERVED_RELATIVE_LINK_variation1() {
        String bodyHtml = "<a href=\"local/page\">local</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void EMPTY_BASE_NONPRESERVED_RELATIVE_LINK_variation1() {
        String bodyHtml = "<a href=\"local/page\">local</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void EMPTY_BASE_PRESERVED_IMAGE_SOURCE_variation1() {
        String bodyHtml = "<img src=\"images/a.png\" alt=\"A\">";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void FRAGMENT_ONLY_HREF_WITH_BASE_variation1() {
        String bodyHtml = "<a href=\"#section\">section</a>";
        String baseUri = "https://example.test/page";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void BASE_ELEMENT_SUPPLIES_RESOLUTION_variation1() {
        String bodyHtml = "<base href=\"https://cdn.example.test/root/\"><a href=\"page\">page</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void UNCLOSED_ALLOWED_ELEMENTS_variation1() {
        String bodyHtml = "<p>one <strong>two";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void MISNESTED_FORMATTING_ELEMENTS_variation1() {
        String bodyHtml = "<b>bold <i>both</b> italic</i>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void STRAY_END_TAGS_variation1() {
        String bodyHtml = "before</p></div>after";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void ESCAPED_FAKE_TAG_TEXT_variation1() {
        String bodyHtml = "&lt;script&gt;alert(1)&lt;/script&gt;";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void CASE_INSENSITIVE_MARKUP_variation1() {
        String bodyHtml = "<A HREF=\"HTTPS://EXAMPLE.TEST/X\">X</A>";
        String baseUri = "https://origin.example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void NUL_IN_TEXT_variation1() {
        String bodyHtml = "A\u0000B";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void VOID_ELEMENT_NORMALIZATION_variation1() {
        String bodyHtml = "one<br>two<br/>three";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void BASIC_ENFORCED_LINK_ATTRIBUTE_variation1() {
        String bodyHtml = "<a href=\"https://example.test/\">safe link</a>";
        String baseUri = "https://origin.example.test/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void MIXED_SAFE_UNSAFE_SUBTREES_variation1() {
        String bodyHtml = "<p onclick=\"evil()\">hello <strong>world</strong>"
                + "<script>evil()</script><a href=\"https://example.test/\">go</a></p>";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.basic();
        verifyRelation(bodyHtml, baseUri, safelist);
    }

    @Test
    void MAXIMUM_LENGTH_PLAIN_TEXT_variation1() {
        String bodyHtml = repeatCharacter('a', 8192);
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verifyRelation(bodyHtml, baseUri, safelist);
    }
}
