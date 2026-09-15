import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    @Test
    void EMPTY_FRAGMENT_NONE_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WHITESPACE_ONLY_NONE_variation1() {
        String bodyHtml = " \t\n ";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PLAIN_TEXT_NONE_variation1() {
        String bodyHtml = "plain visible text";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TEXT_WITH_ENTITY_NONE_variation1() {
        String bodyHtml = "5 &lt; 6 &amp; 7";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DISALLOWED_TAG_WITH_VISIBLE_TEXT_NONE_variation1() {
        String bodyHtml = "<p>retained text</p>";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMMENT_AND_TEXT_NONE_variation1() {
        String bodyHtml = "<!-- comment --><p>visible</p>";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCRIPT_ONLY_NONE_variation1() {
        String bodyHtml = "<script>alert(1)</script>";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIMPLE_TEXT_FORMATTING_variation1() {
        String bodyHtml = "<b>bold <em>emphasized</em></b>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIMPLE_TEXT_LINK_REMOVAL_variation1() {
        String bodyHtml = "<a href=\"https://example.test/path\">link text</a>";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_ALLOWED_LINK_ABSOLUTE_HTTP_variation1() {
        String bodyHtml = "<a href=\"https://example.test/docs?q=1\">docs</a>";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_RELATIVE_WITH_BASE_variation1() {
        String bodyHtml = "<a href=\"guide/start.html\">guide</a>";
        String baseUri = "https://example.test/root/sub/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_RELATIVE_EMPTY_BASE_variation1() {
        String bodyHtml = "<a href=\"guide/start.html\">guide</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_JAVASCRIPT_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">unsafe link text</a>";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_CASE_MIXED_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"JaVaScRiPt:alert(1)\">mixed protocol</a>";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_ENTITY_OBFUSCATED_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"java&#x73;cript:alert(1)\">entity protocol</a>";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_DISALLOWED_ATTRIBUTE_variation1() {
        String bodyHtml = "<p onclick=\"alert(1)\">paragraph text</p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_DISALLOWED_ELEMENT_NESTED_variation1() {
        String bodyHtml = "<p>before<script>alert(1)</script>after</p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_MALFORMED_NESTING_variation1() {
        String bodyHtml = "<p><b>one</p>two</b>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_UNKNOWN_TAG_CONTENT_variation1() {
        String bodyHtml = "<unknown-widget data-x=\"1\">widget text</unknown-widget>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_HTTP_IMAGE_variation1() {
        String bodyHtml = "<img src=\"https://example.test/image.png\" alt=\"image alternative\">";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_RELATIVE_IMAGE_variation1() {
        String bodyHtml = "<img src=\"images/pic.png\" alt=\"relative image\">";
        String baseUri = "https://example.test/root/sub/";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_DATA_IMAGE_variation1() {
        String bodyHtml = "<img src=\"data:image/png;base64,AA==\" alt=\"data image\">";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_TABLE_STRUCTURE_variation1() {
        String bodyHtml = "<table><tr><td>left</td><td>right</td></tr></table>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_LIST_STRUCTURE_variation1() {
        String bodyHtml = "<ol><li>first</li><li>second</li></ol>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_STYLE_ATTRIBUTE_variation1() {
        String bodyHtml = "<p style=\"background:url(javascript:alert(1))\">styled text</p>";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_BASE_ELEMENT_IN_FRAGMENT_variation1() {
        String bodyHtml = "<base href=\"https://other.test/assets/\"><a href=\"item.html\">item</a>";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTOR_TEXT_variation1() {
        String bodyHtml = "constructor policy text";
        String baseUri = "";
        Safelist safelist = new Safelist();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTOR_TAG_ONLY_variation1() {
        String bodyHtml = "<div></div>";
        String baseUri = "";
        Safelist safelist = new Safelist();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOCTYPE_AND_VISIBLE_TEXT_BASIC_variation1() {
        String bodyHtml = "<!doctype html><p>declaration text</p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CHARACTER_REFERENCE_IN_ATTRIBUTE_BASIC_variation1() {
        String bodyHtml = "<a href=\"https://example.test/search?a=1&amp;b=2\">search</a>";
        String baseUri = "https://example.test/root/page.html";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
