import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    @Test
    void EMPTY_FRAGMENT_NONE_emptyFragment() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WHITESPACE_ONLY_NONE_whitespaceFragment() {
        String bodyHtml = " \t\n  \n\t ";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PLAIN_TEXT_NONE_plainText() {
        String bodyHtml = "plain text 123";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ENTITY_TEXT_NONE_entityBearingText() {
        String bodyHtml = "5 is &lt; 6 &amp; 7";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALLOWED_BASIC_PARAGRAPH_safeParagraph() {
        String bodyHtml = "<p>safe paragraph</p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NESTED_BASIC_FORMATTING_nestedStrongAndEmphasis() {
        String bodyHtml = "<p><strong>bold <em>and emphasized</em></strong></p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DISALLOWED_WRAPPER_RETAINS_TEXT_sectionWrapper() {
        String bodyHtml = "<section>retained descendant text</section>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCRIPT_ELEMENT_BASIC_scriptBetweenText() {
        String bodyHtml = "before<script>alert(1)</script>after";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void STYLE_ELEMENT_BASIC_styleBetweenText() {
        String bodyHtml = "before<style>body{display:none}</style>after";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HTML_COMMENT_BASIC_commentAdjacentToText() {
        String bodyHtml = "visible<!-- hidden comment -->text";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MALFORMED_NESTING_BASIC_misnestedFormatting() {
        String bodyHtml = "<p>one<strong>two</p>three";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNKNOWN_ELEMENT_WITH_ALLOWED_DESCENDANT_customWidgetStrongText() {
        String bodyHtml = "<custom-widget><strong>kept text</strong></custom-widget>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_ALLOWED_ABSOLUTE_HTTP_httpAnchor() {
        String bodyHtml = "<a href=\"http://example.test/path?q=1\">HTTP link</a>";
        String baseUri = "https://base.example/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_ALLOWED_ABSOLUTE_HTTPS_httpsAnchor() {
        String bodyHtml = "<a href=\"https://example.test/path\">HTTPS link</a>";
        String baseUri = "http://base.example/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_JAVASCRIPT_PROTOCOL_javascriptHref() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">unsafe link</a>";
        String baseUri = "https://base.example/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_DATA_PROTOCOL_dataHref() {
        String bodyHtml = "<a href=\"data:text/html,unsafe\">data link</a>";
        String baseUri = "https://base.example/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_RELATIVE_WITH_HTTP_BASE_relativeHref() {
        String bodyHtml = "<a href=\"docs/page.html\">relative link</a>";
        String baseUri = "http://base.example/dir/index.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_ROOT_RELATIVE_WITH_HTTPS_BASE_rootRelativeHref() {
        String bodyHtml = "<a href=\"/account\">root relative link</a>";
        String baseUri = "https://base.example/dir/page";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_RELATIVE_EMPTY_BASE_emptyBaseUri() {
        String bodyHtml = "<a href=\"docs/page.html\">relative link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_PROTOCOL_RELATIVE_protocolRelativeHref() {
        String bodyHtml = "<a href=\"//cdn.example.test/lib.js\">cdn link</a>";
        String baseUri = "https://base.example/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_DISALLOWED_ATTRIBUTE_eventAndTitleAttributes() {
        String bodyHtml = "<a href=\"https://example.test/\" onclick=\"alert(1)\" title=\"note\">link text</a>";
        String baseUri = "https://base.example/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IMAGE_BASIC_WITH_IMAGES_absoluteImageSource() {
        String bodyHtml = "<img src=\"https://images.example.test/pic.png\" alt=\"picture\">";
        String baseUri = "https://base.example/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IMAGE_DATA_URL_BASIC_WITH_IMAGES_dataImageSource() {
        String bodyHtml = "<img src=\"data:image/png;base64,AAAA\" alt=\"inline\">";
        String baseUri = "https://base.example/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_TABLE_STRUCTURE_tableCells() {
        String bodyHtml = "<table><tr><td>cell one</td><td>cell two</td></tr></table>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_IMAGE_RELATIVE_URL_relativeImageSource() {
        String bodyHtml = "<img src=\"media/photo.png\" alt=\"relative image\">";
        String baseUri = "https://base.example/articles/page.html";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIMPLE_TEXT_WITH_MARKUP_formattingAndLink() {
        String bodyHtml = "start <strong>strong text</strong> <a href=\"https://example.test/\">link text</a> end";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTED_SAFELIST_defaultPolicy() {
        String bodyHtml = "<p>paragraph</p><a href=\"https://example.test/\">link</a>";
        String baseUri = "not a URL";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FULL_DOCUMENT_TAGS_AS_BODY_FRAGMENT_structuralTags() {
        String bodyHtml = "<html><head><title>ignored title</title></head><body><p>body content</p></body></html>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
