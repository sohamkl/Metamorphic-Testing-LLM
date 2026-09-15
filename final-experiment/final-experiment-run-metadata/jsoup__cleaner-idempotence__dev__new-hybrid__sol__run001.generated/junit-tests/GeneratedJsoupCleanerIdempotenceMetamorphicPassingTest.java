import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static void exercise(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);

        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                bodyHtml, baseUri, safelist);

        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static String nestedBoldHtml(int depth, String text) {
        StringBuilder html = new StringBuilder(depth * 7 + text.length());
        for (int i = 0; i < depth; i++) {
            html.append("<b>");
        }
        html.append(text);
        for (int i = 0; i < depth; i++) {
            html.append("</b>");
        }
        return html.toString();
    }

    private static String repeatedCharacter(char character, int count) {
        StringBuilder value = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            value.append(character);
        }
        return value.toString();
    }

    @Test
    public void EMPTY_BODY_FRAGMENT_variation1() {
        exercise("", "", Safelist.none());
    }

    @Test
    public void ASCII_TEXT_ONLY_variation1() {
        exercise("plain text 123", "http://example.test/", Safelist.none());
    }

    @Test
    public void UNICODE_TEXT_ONLY_variation1() {
        exercise("café 漢字 😀", "https://example.test/", Safelist.none());
    }

    @Test
    public void ENTITY_TEXT_NORMALIZATION_variation1() {
        exercise("5 &lt; 6 &amp; 7 &gt; 3", "https://example.test/root/", Safelist.none());
    }

    @Test
    public void NONE_REMOVES_TAGS_RETAINS_TEXT_variation1() {
        exercise("<p>A <b>B</b> C</p>", "https://example.test/page?mode=1", Safelist.none());
    }

    @Test
    public void NONE_MARKUP_WITHOUT_TEXT_variation1() {
        exercise("<div><span></span></div>", "file:///tmp/root/", Safelist.none());
    }

    @Test
    public void SIMPLE_TEXT_ALLOWED_FORMATTING_variation1() {
        exercise(
                "<b>B</b><em>E</em><i>I</i><strong>S</strong><u>U</u>",
                "",
                Safelist.simpleText());
    }

    @Test
    public void SIMPLE_TEXT_REMOVES_BLOCK_WRAPPER_variation1() {
        exercise("<p>outer <b>inner</b></p>", "http://example.test/", Safelist.simpleText());
    }

    @Test
    public void BASIC_ABSOLUTE_HTTPS_LINK_variation1() {
        exercise(
                "<a href=\"https://example.test/p\">link</a>",
                "https://origin.example/",
                Safelist.basic());
    }

    @Test
    public void BASIC_HTTP_LINK_ENFORCED_ATTRIBUTE_variation1() {
        exercise(
                "<a href=\"http://example.test/\">link</a>",
                "https://origin.example/root/",
                Safelist.basic());
    }

    @Test
    public void BASIC_JAVASCRIPT_LINK_REMOVAL_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise("<a href=\"javascript:alert(1)\">click</a>", "", safelist);
    }

    @Test
    public void BASIC_ENCODED_JAVASCRIPT_LINK_variation1() {
        exercise(
                "<a href=\"java&#x73;cript:alert(1)\">click</a>",
                "file:///tmp/root/",
                Safelist.basic());
    }

    @Test
    public void BASIC_EMPTY_BASE_RELATIVE_LINK_variation1() {
        exercise("<a href=\"docs/page.html\">docs</a>", "", Safelist.basic());
    }

    @Test
    public void BASIC_NONEMPTY_BASE_RELATIVE_LINK_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise(
                "<a href=\"docs/page.html\">docs</a>",
                "https://example.test/root/",
                safelist);
    }

    @Test
    public void PRESERVE_RELATIVE_NONEMPTY_BASE_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise(
                "<a href=\"docs/page.html\">docs</a>",
                "https://example.test/root/",
                safelist);
    }

    @Test
    public void PRESERVE_RELATIVE_EMPTY_BASE_SENTINEL_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<a href=\"docs/page.html\">docs</a>", "", safelist);
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise(
                "<a href=\"//cdn.example.test/a\">cdn</a>",
                "https://example.test/root/",
                safelist);
    }

    @Test
    public void FRAGMENT_ONLY_LINK_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise(
                "<a href=\"#section\">jump</a>",
                "https://example.test/page",
                safelist);
    }

    @Test
    public void QUERY_ONLY_LINK_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise(
                "<a href=\"?mode=1\">query</a>",
                "https://example.test/path/page",
                safelist);
    }

    @Test
    public void DOT_SEGMENT_RELATIVE_LINK_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise(
                "<a href=\"../asset\">asset</a>",
                "https://example.test/a/b/",
                safelist);
    }

    @Test
    public void MAILTO_LINK_PROTOCOL_variation1() {
        exercise(
                "<a href=\"mailto:user@example.test\">mail</a>",
                "https://example.test/",
                Safelist.basic());
    }

    @Test
    public void NON_ALLOWED_BASE_PROTOCOL_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise("<a href=\"child\">child</a>", "file:///tmp/root/", safelist);
    }

    @Test
    public void BASIC_REMOVES_IMAGE_ELEMENT_variation1() {
        exercise(
                "before<img src=\"https://example.test/i.png\" alt=\"I\">after",
                "https://example.test/page?image=1",
                Safelist.basic());
    }

    @Test
    public void BASIC_WITH_IMAGES_ABSOLUTE_SOURCE_variation1() {
        exercise(
                "<img src=\"https://example.test/i.png\" alt=\"I\">",
                "file:///tmp/root/",
                Safelist.basicWithImages());
    }

    @Test
    public void BASIC_WITH_IMAGES_RELATIVE_SOURCE_variation1() {
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(false);
        exercise(
                "<img src=\"images/i.png\" alt=\"I\">",
                "https://example.test/root/",
                safelist);
    }

    @Test
    public void IMAGE_DATA_PROTOCOL_REMOVAL_variation1() {
        exercise(
                "<img src=\"data:image/png;base64,AAAA\" alt=\"I\">",
                "http://example.test/",
                Safelist.basicWithImages());
    }

    @Test
    public void RELAXED_BLOCK_STRUCTURE_variation1() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        exercise(
                "<div><p>P</p><ul><li>One</li><li>Two</li></ul></div>",
                "https://example.test/",
                safelist);
    }

    @Test
    public void RELAXED_TABLE_NORMALIZATION_variation1() {
        Safelist safelist = new Safelist(Safelist.relaxed());
        exercise(
                "<table><tr><td>A</td><td>B</td></tr></table>",
                "https://example.test/root/path/",
                safelist);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_REMOVAL_variation1() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(false);
        exercise(
                "<p onclick=\"alert(1)\">safe text</p>",
                "https://example.test/page?event=1",
                safelist);
    }

    @Test
    public void MIXED_ALLOWED_AND_UNSAFE_ATTRIBUTES_variation1() {
        exercise(
                "<a href=\"https://example.test/\" onclick=\"x()\">mixed</a>",
                "file:///tmp/root/",
                Safelist.relaxed());
    }

    @Test
    public void UNKNOWN_ELEMENT_WITH_TEXT_variation1() {
        exercise("<widget data-x=\"1\">marker</widget>", "", Safelist.relaxed());
    }

    @Test
    public void SCRIPT_DATA_REMOVAL_variation1() {
        exercise(
                "<script>alert(1)</script>",
                "http://example.test/",
                Safelist.relaxed());
    }

    @Test
    public void COMMENT_AND_DOCTYPE_REMOVAL_variation1() {
        exercise(
                "<!doctype html><!--secret-->",
                "https://example.test/",
                Safelist.relaxed());
    }

    @Test
    public void UNCLOSED_ALLOWED_ELEMENTS_variation1() {
        exercise(
                "<p>one<p>two",
                "https://example.test/root/path/",
                Safelist.relaxed());
    }

    @Test
    public void MISNESTED_FORMATTING_REPAIR_variation1() {
        Safelist safelist = new Safelist(Safelist.simpleText());
        exercise(
                "<b>bold<i>both</b>italic</i>",
                "https://example.test/page?format=1",
                safelist);
    }

    @Test
    public void DUPLICATE_HREF_ATTRIBUTES_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise(
                "<a href=\"https://one.example/\" href=\"https://two.example/\">dup</a>",
                "file:///tmp/root/",
                safelist);
    }

    @Test
    public void QUOTED_ATTRIBUTE_DELIMITERS_variation1() {
        exercise(
                "<a href=\"https://example.test/?a=1&amp;b=2\" title=\"1 > 0\">quoted</a>",
                "",
                Safelist.relaxed());
    }

    @Test
    public void MIXED_CASE_TAG_AND_ATTRIBUTE_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise(
                "<A HREF=\"https://example.test/\"><B>Case</B></A>",
                "http://origin.example/",
                safelist);
    }

    @Test
    public void VOID_ELEMENT_NORMALIZATION_variation1() {
        exercise("a<br/>b", "https://example.test/", Safelist.basic());
    }

    @Test
    public void DISALLOWED_PARENT_ALLOWED_CHILD_variation1() {
        exercise(
                "<section>before <b>inside</b> after</section>",
                "https://example.test/root/path/",
                Safelist.basic());
    }

    @Test
    public void MIXED_SAFE_AND_UNSAFE_SIBLINGS_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise(
                "<p>safe</p><script>bad()</script><b>bold</b>tail",
                "https://example.test/page?mixed=1",
                safelist);
    }

    @Test
    public void URL_LEADING_CONTROL_OR_SPACE_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        exercise(
                "<a href=\" \tjavascript:alert(1)\">trimcheck</a>",
                "file:///tmp/root/",
                safelist);
    }

    @Test
    public void BASE_ELEMENT_SUPPLIES_RESOLUTION_URI_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise(
                "<base href=\"https://base.example/root/\"><a href=\"child\">child</a>",
                "",
                safelist);
    }

    @Test
    public void DOCUMENT_LIKE_INPUT_AS_BODY_FRAGMENT_variation1() {
        exercise(
                "<html><head><title>T</title></head><body><p>B</p></body></html>",
                "http://example.test/",
                Safelist.relaxed());
    }

    @Test
    public void MULTIPLE_BLOCKS_PRETTY_SERIALIZATION_variation1() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        exercise(
                "<p>one</p><p>two</p><p>three</p>",
                "https://example.test/",
                safelist);
    }

    @Test
    public void PUBLIC_EMPTY_SAFELIST_variation1() {
        Safelist safelist = new Safelist();
        exercise(
                "<p>A <b>B</b></p>",
                "https://example.test/root/path/",
                safelist);
    }

    @Test
    public void COPIED_BASIC_SAFELIST_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        exercise(
                "<a href=\"https://example.test/\"><b>copy</b></a>",
                "https://example.test/page?copy=1",
                safelist);
    }

    @Test
    public void REPLACEMENT_CHARACTER_FROM_NULL_CODE_POINT_variation1() {
        String bodyHtml = "left" + Character.toString((char) 0) + "right";
        exercise(bodyHtml, "file:///tmp/root/", Safelist.none());
    }

    @Test
    public void DEEPLY_NESTED_ALLOWED_ELEMENTS_variation1() {
        String bodyHtml = nestedBoldHtml(64, "deep");
        Safelist safelist = new Safelist(Safelist.simpleText());
        exercise(bodyHtml, "", safelist);
    }

    @Test
    public void LONG_TEXT_NODE_variation1() {
        String bodyHtml = repeatedCharacter('x', 4096);
        exercise(bodyHtml, "http://example.test/", Safelist.none());
    }
}
