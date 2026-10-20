import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static String deeplyNestedFormatting(int depth, String text) {
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

    private static void exercise(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);

        Object[] followUp =
                CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                        bodyHtml, baseUri, safelist);

        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning the follow-up HTML must produce exactly the source output");
        }
    }

    @Test
    public void EMPTY_BODY_NONE_variation1() {
        exercise("", "", Safelist.none());
    }

    @Test
    public void WHITESPACE_ONLY_BODY_variation1() {
        exercise(" \t\n", "", Safelist.none());
    }

    @Test
    public void PLAIN_ASCII_TEXT_variation1() {
        exercise("alpha beta 123", "", Safelist.none());
    }

    @Test
    public void NAMED_ENTITY_TEXT_variation1() {
        exercise("A &amp; B", "", Safelist.none());
    }

    @Test
    public void UNICODE_TEXT_variation1() {
        exercise("café 漢字 😀", "", Safelist.none());
    }

    @Test
    public void EMBEDDED_NUL_TEXT_variation1() {
        exercise("left\u0000right", "", Safelist.none());
    }

    @Test
    public void COMMENT_ONLY_FRAGMENT_variation1() {
        exercise("<!--secret-->", "", Safelist.relaxed());
    }

    @Test
    public void SCRIPT_RAW_DATA_variation1() {
        exercise("<script>alert(1)</script>", "", Safelist.relaxed());
    }

    @Test
    public void STYLE_RAW_DATA_variation1() {
        exercise("<style>body{display:none}</style>", "", Safelist.relaxed());
    }

    @Test
    public void UNKNOWN_TAG_WITH_TEXT_variation1() {
        exercise("<unknown>kept text</unknown>", "", Safelist.relaxed());
    }

    @Test
    public void NONE_REMOVES_MARKUP_RETAINS_TEXT_variation1() {
        exercise("<p>Hello <b>world</b>.</p>", "", Safelist.none());
    }

    @Test
    public void SIMPLE_TEXT_ALLOWED_FORMATTING_variation1() {
        exercise(
                "<b>bold</b> <em>emphasis</em> <u>underlined</u>",
                "",
                Safelist.simpleText());
    }

    @Test
    public void SIMPLE_TEXT_DISALLOWED_LINK_variation1() {
        exercise(
                "<a href=\"https://example.test/\">label</a>",
                "",
                Safelist.simpleText());
    }

    @Test
    public void BASIC_PARAGRAPH_AND_FORMATTING_variation1() {
        exercise("<p>one <strong>two</strong></p>", "", Safelist.basic());
    }

    @Test
    public void BASIC_ABSOLUTE_HTTP_LINK_variation1() {
        exercise(
                "<a href=\"http://example.test/a\">HTTP</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void BASIC_ABSOLUTE_HTTPS_LINK_variation1() {
        exercise(
                "<a href=\"https://example.test/a?x=1#f\">HTTPS</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void RELATIVE_LINK_WITH_ABSOLUTE_BASE_variation1() {
        exercise(
                "<a href=\"child/page.html\">child</a>",
                "https://example.test/root/",
                Safelist.basic());
    }

    @Test
    public void RELATIVE_LINK_WITH_EMPTY_BASE_variation1() {
        exercise(
                "<a href=\"child/page.html\">child</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void PARENT_RELATIVE_LINK_variation1() {
        exercise(
                "<a href=\"../up.html\">up</a>",
                "https://example.test/a/b/",
                Safelist.basic());
    }

    @Test
    public void QUERY_ONLY_LINK_variation1() {
        exercise(
                "<a href=\"?page=2\">next</a>",
                "https://example.test/index.html?page=1",
                Safelist.basic());
    }

    @Test
    public void FRAGMENT_ONLY_LINK_variation1() {
        exercise(
                "<a href=\"#section\">section</a>",
                "https://example.test/page",
                Safelist.basic());
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_variation1() {
        exercise(
                "<a href=\"//cdn.example.test/x\">cdn</a>",
                "https://example.test/root/",
                Safelist.basic());
    }

    @Test
    public void MAILTO_LINK_PROTOCOL_variation1() {
        exercise(
                "<a href=\"mailto:user@example.test\">mail</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void FTP_LINK_PROTOCOL_variation1() {
        exercise(
                "<a href=\"ftp://example.test/file\">file</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void JAVASCRIPT_LINK_REJECTED_variation1() {
        exercise(
                "<a href=\"javascript:alert(1)\">click</a>",
                "https://example.test/",
                Safelist.basic());
    }

    @Test
    public void CASE_AND_WHITESPACE_JAVASCRIPT_LINK_variation1() {
        exercise(
                "<a href=\"&#x09;JaVaScRiPt:alert(1)\">click</a>",
                "https://example.test/",
                Safelist.basic());
    }

    @Test
    public void DATA_LINK_PROTOCOL_variation1() {
        exercise(
                "<a href=\"data:text/html,unsafe\">data</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void MALFORMED_URL_ATTRIBUTE_variation1() {
        exercise(
                "<a href=\"http://[invalid\">broken</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void BASIC_FILTERS_IMAGE_TAG_variation1() {
        exercise(
                "<img src=\"https://example.test/x.png\" alt=\"x\">fallback",
                "",
                Safelist.basic());
    }

    @Test
    public void BASIC_WITH_IMAGES_ABSOLUTE_IMAGE_variation1() {
        exercise(
                "<img src=\"https://example.test/x.png\" alt=\"sample\" width=\"10\">",
                "",
                Safelist.basicWithImages());
    }

    @Test
    public void BASIC_WITH_IMAGES_RELATIVE_IMAGE_variation1() {
        exercise(
                "<img src=\"images/x.png\" alt=\"sample\">",
                "https://example.test/assets/",
                Safelist.basicWithImages());
    }

    @Test
    public void DATA_IMAGE_PROTOCOL_variation1() {
        exercise(
                "<img src=\"data:image/png;base64,AAAA\" alt=\"sample\">after",
                "",
                Safelist.basicWithImages());
    }

    @Test
    public void RELAXED_BLOCK_STRUCTURE_variation1() {
        exercise(
                "<h2>Title</h2><p>Intro</p><ul><li>One</li><li>Two</li></ul>",
                "",
                Safelist.relaxed());
    }

    @Test
    public void RELAXED_TABLE_STRUCTURE_variation1() {
        exercise(
                "<table><thead><tr><th>H</th></tr></thead>"
                        + "<tbody><tr><td>D</td></tr></tbody></table>",
                "",
                Safelist.relaxed());
    }

    @Test
    public void UNSAFE_IFRAME_WITH_FALLBACK_TEXT_variation1() {
        exercise(
                "before<iframe src=\"https://example.test/\"></iframe>after",
                "",
                Safelist.relaxed());
    }

    @Test
    public void EVENT_ATTRIBUTE_ON_ALLOWED_TAG_variation1() {
        exercise(
                "<a href=\"https://example.test/\" onclick=\"alert(1)\">go</a>",
                "",
                Safelist.relaxed());
    }

    @Test
    public void STYLE_ATTRIBUTE_FILTERING_variation1() {
        exercise(
                "<p style=\"position:fixed\">content</p>",
                "",
                Safelist.relaxed());
    }

    @Test
    public void SAFE_NON_URL_ATTRIBUTE_variation1() {
        exercise(
                "<a href=\"https://example.test/\" title=\"description\">label</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void DUPLICATE_ATTRIBUTES_variation1() {
        exercise(
                "<a href=\"https://one.example/\" "
                        + "href=\"javascript:alert(1)\">dup</a>",
                "",
                Safelist.basic());
    }

    @Test
    public void UNCLOSED_ALLOWED_ELEMENTS_variation1() {
        exercise("<p>alpha <b>beta", "", Safelist.basic());
    }

    @Test
    public void MISNESTED_FORMATTING_ELEMENTS_variation1() {
        exercise(
                "<b>bold <i>both</b> italic</i>",
                "",
                Safelist.simpleText());
    }

    @Test
    public void UPPERCASE_TAG_AND_ATTRIBUTE_NAMES_variation1() {
        exercise(
                "<A HREF=\"HTTPS://EXAMPLE.TEST/X\">UPPER</A>",
                "",
                Safelist.basic());
    }

    @Test
    public void ESCAPED_MARKUP_AS_TEXT_variation1() {
        exercise(
                "&lt;script&gt;not markup&lt;/script&gt;",
                "",
                Safelist.none());
    }

    @Test
    public void NUMERIC_ENTITY_BOUNDARIES_variation1() {
        exercise(
                "5 &#60; 6 &#x26;&#x26; 7 &#62; 6",
                "",
                Safelist.none());
    }

    @Test
    public void FULL_DOCUMENT_AS_BODY_FRAGMENT_variation1() {
        exercise(
                "<html><head><title>T</title></head>"
                        + "<body><p>B</p></body></html>",
                "",
                Safelist.relaxed());
    }

    @Test
    public void BASE_ELEMENT_INSIDE_FRAGMENT_variation1() {
        exercise(
                "<base href=\"https://other.example/root/\">"
                        + "<a href=\"child\">child</a>",
                "https://example.test/start/",
                Safelist.basic());
    }

    @Test
    public void MIXED_SAFE_AND_UNSAFE_BATCH_variation1() {
        exercise(
                "<p>start <a href=\"https://example.test/\" onclick=\"x()\">link</a>"
                        + "<script>x()</script>"
                        + "<img src=\"javascript:x\" alt=\"bad\"> end</p>",
                "https://example.test/root/",
                Safelist.relaxed());
    }

    @Test
    public void EMPTY_CONSTRUCTED_SAFELIST_variation1() {
        exercise(
                "<p>alpha <b>beta</b></p>",
                "",
                new Safelist());
    }

    @Test
    public void COPIED_BASIC_SAFELIST_variation1() {
        exercise(
                "<p>copy <a href=\"https://example.test/\">link</a></p>",
                "",
                new Safelist(Safelist.basic()));
    }

    @Test
    public void DEEPLY_NESTED_ALLOWED_FORMATTING_variation1() {
        exercise(
                deeplyNestedFormatting(64, "deep"),
                "",
                Safelist.simpleText());
    }
}
