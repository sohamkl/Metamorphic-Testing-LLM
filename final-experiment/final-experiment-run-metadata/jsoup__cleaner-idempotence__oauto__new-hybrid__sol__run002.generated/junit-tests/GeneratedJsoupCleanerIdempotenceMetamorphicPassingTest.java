import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static Object[] generateFollowUp(
            String cleanedHtml, String baseUri, Safelist safelist) {
        return new Object[]{cleanedHtml, baseUri, safelist};
    }

    @Test
    public void EMPTY_BODY_SENTINEL_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_NONE_variation1() {
        String bodyHtml = "plain text";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENTITY_TEXT_NONE_variation1() {
        String bodyHtml = "5 &lt; 6 &amp; &#62; 4";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNICODE_TEXT_variation1() {
        String bodyHtml = "café 漢字 😀";
        String baseUri = "https://example.test/path/page.html";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_ONLY_BODY_variation1() {
        String bodyHtml = "  \t\n  ";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_REMOVES_FORMATTING_variation1() {
        String bodyHtml = "<b>bold <i>and italic</i></b>";
        String baseUri = "not a URL";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_MALFORMED_FORMATTING_variation1() {
        String bodyHtml = "<b>one<i>two</b>three";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEW_SAFELIST_TEXT_PATH_variation1() {
        String bodyHtml = "<p>alpha <b>beta</b></p>";
        String baseUri = "http://example.test/";
        Safelist safelist = new Safelist();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_FORMATTING_variation1() {
        String bodyHtml = "<b>bold</b> <em>emphasis</em> <u>underlined</u>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_BLOCK_WRAPPER_variation1() {
        String bodyHtml = "<div>before <strong>inside</strong> after</div>";
        String baseUri = "https://example.test/file.html";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_MIXED_ELEMENTS_variation1() {
        String bodyHtml = "<p>p</p><ul><li>one</li></ul><section><em>two</em></section>";
        String baseUri = "relative/base";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ABSOLUTE_LINK_variation1() {
        String bodyHtml = "<a href=\"https://example.test/a\">link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_EMPTY_BASE_variation1() {
        String bodyHtml = "<a href=\"docs/page.html\">docs</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_RELATIVE_LINK_WITH_BASE_variation1() {
        String bodyHtml = "<a href=\"/docs/page.html\">docs</a>";
        String baseUri = "https://example.test/root/index.html";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARENT_RELATIVE_LINK_WITH_BASE_variation1() {
        String bodyHtml = "<a href=\"../asset?q=1\">asset</a>";
        String baseUri = "https://example.test/a/b/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRAGMENT_ONLY_LINK_variation1() {
        String bodyHtml = "<a href=\"#part\">jump</a>";
        String baseUri = "https://example.test/page";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAVASCRIPT_LINK_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">click</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENCODED_PROTOCOL_LINK_variation1() {
        String bodyHtml = "<a href=\"java&#x73;cript:alert(1)\">click</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LINK_UNSAFE_ATTRIBUTES_variation1() {
        String bodyHtml =
                "<a href=\"https://example.test/\" onclick=\"go()\" style=\"color:red\">link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ENFORCED_LINK_ATTRIBUTE_variation1() {
        String bodyHtml = "<a href=\"https://example.test/\">link</a>";
        String baseUri = "http://base.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_UNDER_BASIC_variation1() {
        String bodyHtml = "<img src=\"https://example.test/a.png\" alt=\"a\">fallback";
        String baseUri = "https://example.test/docs/";
        Safelist safelist = new Safelist(Safelist.basic());

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_IMAGE_WITH_IMAGE_POLICY_variation1() {
        String bodyHtml =
                "<img src=\"https://example.test/a.png\" alt=\"sample\" width=\"10\" height=\"20\">";
        String baseUri = "https://example.test/path/page.html";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_IMAGE_WITH_BASE_variation1() {
        String bodyHtml = "<img src=\"images/a.png\" alt=\"a\">";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_IMAGE_PROTOCOL_variation1() {
        String bodyHtml = "<img src=\"data:image/png;base64,AAAA\" alt=\"sample\">";
        String baseUri = "not a URL";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_STRUCTURAL_CONTENT_variation1() {
        String bodyHtml = "<div><h2>Title</h2><p>A <span>paragraph</span></p></div>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_TABLE_CONTENT_variation1() {
        String bodyHtml =
                "<table><thead><tr><th>H</th></tr></thead>"
                        + "<tbody><tr><td>D</td></tr></tbody></table>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_TABLE_FRAGMENT_variation1() {
        String bodyHtml = "<table><td>A<td>B</table>";
        String baseUri = "https://example.test/a/b/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FORBIDDEN_WRAPPER_SAFE_DESCENDANTS_variation1() {
        String bodyHtml = "<custom><p>kept <strong>text</strong></p></custom>";
        String baseUri = "https://example.test/path/file.html";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SCRIPT_DATA_REMOVAL_variation1() {
        String bodyHtml = "before<script>alert(1)</script>after";
        String baseUri = "relative/base";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMENT_ONLY_BODY_variation1() {
        String bodyHtml = "<!-- secret -->";
        String baseUri = "not a URL";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FULL_DOCUMENT_AS_BODY_FRAGMENT_variation1() {
        String bodyHtml =
                "<!doctype html><html><head><title>T</title></head>"
                        + "<body><p>B</p></body></html>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MISNESTED_INLINE_ELEMENTS_variation1() {
        String bodyHtml = "<b>one<i>two</b>three</i>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNCLOSED_ATTRIBUTE_AND_TAG_variation1() {
        String bodyHtml = "<a href=\"https://example.test/path";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DUPLICATE_ATTRIBUTES_variation1() {
        String bodyHtml =
                "<a href=\"https://one.test/\" href=\"javascript:bad()\" "
                        + "title=\"a\" title=\"b\">x</a>";
        String baseUri = "https://example.test/path/page.html";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_CASE_HTML_AND_PROTOCOL_variation1() {
        String bodyHtml = "<A HrEf=\"HtTpS://example.test/X\"><STRONG>X</STRONG></A>";
        String baseUri = "relative/base";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FORM_CONTROL_REMOVAL_variation1() {
        String bodyHtml =
                "<form action=\"https://example.test/\"><label>Name"
                        + "<input name=\"n\" value=\"x\"></label></form>visible";
        String baseUri = "not a URL";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMBEDDED_CONTENT_ELEMENTS_variation1() {
        String bodyHtml =
                "<iframe src=\"https://example.test/\"></iframe>"
                        + "<object data=\"x\"></object><embed src=\"x\">";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STYLE_ATTRIBUTE_REMOVAL_variation1() {
        String bodyHtml = "<p style=\"background:url(javascript:bad())\">text</p>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void URL_QUERY_ENTITY_SERIALIZATION_variation1() {
        String bodyHtml =
                "<a href=\"https://example.test/search?a=1&amp;b=2#part\">search</a>";
        String baseUri = "https://example.test/a/b/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INPUT_BASE_ELEMENT_variation1() {
        String bodyHtml =
                "<base href=\"https://base.test/dir/\"><a href=\"page.html\">page</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_URL_BASE_TEXT_variation1() {
        String bodyHtml = "<a href=\"page.html\">page</a>";
        String baseUri = "not a URL";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPIED_RELAXED_POLICY_variation1() {
        String bodyHtml = "<div><p>copied <em>policy</em></p></div>";
        String baseUri = "not a URL";
        Safelist safelist = new Safelist(Safelist.relaxed());

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LONG_REPEATED_FRAGMENT_variation1() {
        String fragment = "<p>x<script>bad()</script><strong>y</strong></p>";
        StringBuilder bodyBuilder = new StringBuilder(fragment.length() * 1024);
        for (int i = 0; i < 1024; i++) {
            bodyBuilder.append(fragment);
        }
        String bodyHtml = bodyBuilder.toString();
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMBEDDED_NUL_TEXT_variation1() {
        String bodyHtml = "A\u0000B<b>C</b>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEEPLY_NESTED_ALLOWED_MARKUP_variation1() {
        StringBuilder bodyBuilder = new StringBuilder(128 * 7 + 1);
        for (int i = 0; i < 128; i++) {
            bodyBuilder.append("<b>");
        }
        bodyBuilder.append('x');
        for (int i = 0; i < 128; i++) {
            bodyBuilder.append("</b>");
        }
        String bodyHtml = bodyBuilder.toString();
        String baseUri = "https://example.test/a/b/";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_ALLOWED_ELEMENT_variation1() {
        String bodyHtml = "<p></p>";
        String baseUri = "https://example.test/path/page.html";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONLY_UNSAFE_VOID_ELEMENTS_variation1() {
        String bodyHtml =
                "<meta charset=\"utf-8\"><link rel=\"stylesheet\" "
                        + "href=\"https://example.test/a.css\">";
        String baseUri = "relative/base";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_SAFE_AND_UNSAFE_PROTOCOLS_variation1() {
        String bodyHtml =
                "<a href=\"https://safe.test/a\">safe</a>"
                        + "<a href=\"/relative\">relative</a>"
                        + "<a href=\"javascript:bad()\">bad</a>";
        String baseUri = "https://base.test/dir/page.html";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
