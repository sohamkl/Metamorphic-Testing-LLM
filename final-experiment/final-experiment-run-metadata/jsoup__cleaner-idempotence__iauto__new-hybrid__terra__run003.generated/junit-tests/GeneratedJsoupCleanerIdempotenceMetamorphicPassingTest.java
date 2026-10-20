import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                "Cleaning the follow-up HTML must produce exactly the first cleaned output: source=<"
                    + sourceOutput + ">, follow-up=<" + followUpOutput + ">");
        }
    }

    @Test
    void NONE_EMPTY_FRAGMENT_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_PLAIN_TEXT_ENTITY_variation1() {
        String bodyHtml = "5 is &lt; 6.";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_ELEMENT_TEXT_CONTENT_variation1() {
        String bodyHtml = "<p>Hello <b>world</b>.</p>";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_MALFORMED_NESTING_variation1() {
        String bodyHtml = "<p>one<div>two</p>three";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_COMMENT_AND_DECLARATION_variation1() {
        String bodyHtml = "<!doctype html><!-- untrusted comment -->";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_ENCODED_MARKUP_TEXT_variation1() {
        String bodyHtml = "&lt;script&gt;not markup&lt;/script&gt;";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIMPLE_TEXT_FORMATTING_variation1() {
        String bodyHtml = "<p>alpha <strong>beta</strong> <em>gamma</em></p>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIMPLE_TEXT_EVENT_ATTRIBUTE_variation1() {
        String bodyHtml = "<b onclick=\"alert(1)\">visible</b>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_ABSOLUTE_HTTP_LINK_variation1() {
        String bodyHtml = "<p><a href=\"http://example.test/path\">link text</a></p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_ABSOLUTE_HTTPS_LINK_variation1() {
        String bodyHtml = "<a href=\"https://example.test/secure?q=1#part\">secure</a>";
        String baseUri = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_RELATIVE_LINK_NONEMPTY_BASE_variation1() {
        String bodyHtml = "<a href=\"../docs/item.html\">relative link</a>";
        String baseUri = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_RELATIVE_LINK_EMPTY_BASE_variation1() {
        String bodyHtml = "<a href=\"docs/item.html\">relative link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_JAVASCRIPT_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">unsafe protocol</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_OBFUSCATED_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\" &#x6a;avascript:alert(1)\">obfuscated protocol</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_ATTRIBUTE_MIX_variation1() {
        String bodyHtml = "<a href=\"https://example.test/\" title=\"t\" style=\"color:red\" data-x=\"1\" onclick=\"x()\">attribute mix</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_DISALLOWED_CONTAINER_variation1() {
        String bodyHtml = "<section><article><p>container text</p></article></section>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_DUPLICATE_ATTRIBUTES_variation1() {
        String bodyHtml = "<a href=\"https://example.test/first\" href=\"javascript:alert(1)\">duplicate href</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_ABSOLUTE_IMAGE_variation1() {
        String bodyHtml = "<p>before<img src=\"https://example.test/image.png\" alt=\"sample\">after</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_RELATIVE_IMAGE_variation1() {
        String bodyHtml = "<img src=\"images/pic.png\" alt=\"relative image\">caption";
        String baseUri = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_DATA_URL_variation1() {
        String bodyHtml = "<img src=\"data:image/png;base64,AA==\" alt=\"inline\">inline image";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_STRUCTURED_LIST_variation1() {
        String bodyHtml = "<h2>Heading</h2><ul><li>first</li><li>second <b>bold</b></li></ul>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_TABLE_NORMALIZATION_variation1() {
        String bodyHtml = "<table><tr><td>r1c1<td>r1c2<tr><td>r2c1</table>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_LINK_AND_IMAGE_variation1() {
        String bodyHtml = "<p><a href=\"/guide\">guide</a><img src=\"/logo.svg\" alt=\"logo\"></p>";
        String baseUri = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_UNSAFE_EMBEDDED_CONTENT_variation1() {
        String bodyHtml = "<iframe src=\"https://evil.test/\"></iframe><script>bad()</script><p>safe tail</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_BASE_ELEMENT_variation1() {
        String bodyHtml = "<base href=\"https://other.test/root/\"><a href=\"child\">base candidate</a>";
        String baseUri = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_UNICODE_REFERENCES_variation1() {
        String bodyHtml = "<p>café &#x1F642; &amp; &#169;</p>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTED_TEXT_variation1() {
        String bodyHtml = "direct constructor text";
        String baseUri = "";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTED_MARKUP_variation1() {
        String bodyHtml = "<div class=\"x\"><a href=\"https://example.test/\" onclick=\"x()\">constructed policy</a></div>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COPY_NONE_MARKUP_variation1() {
        String bodyHtml = "<i>copied none</i>";
        String baseUri = "";
        Safelist safelist = new Safelist(Safelist.none());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COPY_RELAXED_URL_CONTENT_variation1() {
        String bodyHtml = "<blockquote><a href=\"https://example.test/q\">copied relaxed link</a></blockquote>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist(Safelist.relaxed());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COPY_BASIC_IMAGES_EMPTY_BASE_variation1() {
        String bodyHtml = "<img src=\"relative.png\" alt=\"copy image\">copy image text";
        String baseUri = "";
        Safelist safelist = new Safelist(Safelist.basicWithImages());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
