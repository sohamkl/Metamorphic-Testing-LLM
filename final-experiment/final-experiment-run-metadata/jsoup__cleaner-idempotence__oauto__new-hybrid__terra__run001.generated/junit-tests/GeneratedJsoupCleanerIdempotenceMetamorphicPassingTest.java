import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static Object[] generateFollowUp(String cleanedHtml, String baseUri, Safelist safelist) {
        return new Object[] { cleanedHtml, baseUri, safelist };
    }

    @Test
    public void EMPTY_FRAGMENT_NONE_emptyBody() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_ONLY_NONE_whitespaceBody() {
        String bodyHtml = " \t\n\r ";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMENT_AND_DOCTYPE_NONE_contentlessNodes() {
        String bodyHtml = "<!doctype html><!--comment-->";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_NONE_plainMarker() {
        String bodyHtml = "plain-none-marker";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEXT_WITH_ESCAPABLE_CHARACTERS_NONE_entitiesAndQuotes() {
        String bodyHtml = "escape-marker=5 &lt; 6 &amp; 7 \"quoted\"";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_ELEMENT_WITH_OUTER_TEXT_NONE_scriptBetweenText() {
        String bodyHtml = "before-none-marker<script>alert(1)</script>after-none-marker";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NESTED_FORMATTING_BASIC_commonFormatting() {
        String bodyHtml = "<p>basic-marker <b>bold</b> <i>italic</i> <u>under</u></p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ALLOWED_LINK_HTTP_absoluteHttp() {
        String bodyHtml = "<a href=\"http://example.test/path?q=1#frag\">http-link-marker</a>";
        String baseUri = "https://base.example.test/root/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ALLOWED_LINK_HTTPS_absoluteHttps() {
        String bodyHtml = "<a href=\"https://example.test/secure\">https-link-marker</a>";
        String baseUri = "http://base.example.test/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_JAVASCRIPT_LINK_unsafeProtocol() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">javascript-link-marker</a>";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_MIXED_CASE_JAVASCRIPT_LINK_mixedCaseProtocol() {
        String bodyHtml = "<a href=\"JaVaScRiPt:alert(1)\">mixed-protocol-marker</a>";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ENTITY_ENCODED_JAVASCRIPT_LINK_encodedProtocol() {
        String bodyHtml = "<a href=\"java&#x73;cript:alert(1)\">encoded-protocol-marker</a>";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_RELATIVE_LINK_WITH_HTTP_BASE_relativeHttp() {
        String bodyHtml = "<a href=\"docs/guide.html\">relative-http-base-marker</a>";
        String baseUri = "http://base.example.test/root/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_RELATIVE_LINK_WITH_HTTPS_BASE_relativeHttps() {
        String bodyHtml = "<a href=\"../up?q=1#part\">relative-https-base-marker</a>";
        String baseUri = "https://base.example.test/root/child/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_RELATIVE_LINK_EMPTY_BASE_emptyBase() {
        String bodyHtml = "<a href=\"docs/guide.html\">relative-empty-base-marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_FRAGMENT_ONLY_LINK_fragmentReference() {
        String bodyHtml = "<a href=\"#section\">fragment-url-marker</a>";
        String baseUri = "https://base.example.test/root/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_PROTOCOL_RELATIVE_LINK_schemeRelative() {
        String bodyHtml = "<a href=\"//cdn.example.test/file\">protocol-relative-marker</a>";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_LINK_WITH_EXTRA_ATTRIBUTES_attributeFiltering() {
        String bodyHtml = "<a href=\"https://example.test/x\" onclick=\"run()\" style=\"color:red\" class=\"c\" data-x=\"1\">attribute-marker</a>";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_NESTING_BASIC_balancedByParser() {
        String bodyHtml = "<p>malformed-marker<b>bold<i>italic</p>tail";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CASE_AND_DUPLICATE_ATTRIBUTE_BASIC_duplicateHref() {
        String bodyHtml = "<A HREF=\"https://first.example.test/\" href=\"javascript:alert(1)\">duplicate-attribute-marker</A>";
        String baseUri = "https://base.example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RAW_TEXT_SCRIPT_AND_STYLE_BASIC_rawTextElements() {
        String bodyHtml = "before-raw-marker<script>if (a < b) alert(1)</script><style>body{color:red}</style>after-raw-marker";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_SAFE_IMAGE_URL_absoluteImage() {
        String bodyHtml = "<img src=\"https://images.example.test/p.png\" alt=\"image-alt-marker\">";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_RELATIVE_IMAGE_URL_relativeImage() {
        String bodyHtml = "<img src=\"images/p.png\" alt=\"relative-image-marker\">";
        String baseUri = "https://base.example.test/root/page.html";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_UNSAFE_IMAGE_URL_unsafeProtocol() {
        String bodyHtml = "<img src=\"javascript:alert(1)\" alt=\"unsafe-image-marker\">";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_LIST_AND_BLOCK_STRUCTURE_nestedList() {
        String bodyHtml = "<div>relaxed-marker<ul><li>one</li><li><strong>two</strong></li></ul></div>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_TABLE_FRAGMENT_tableCells() {
        String bodyHtml = "<table><tr><td>table-marker</td><td>cell-two</td></tr></table>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_MIXED_SAFE_AND_UNSAFE_CONTENT_scriptAndEvent() {
        String bodyHtml = "<blockquote>relaxed-mixed-marker <a href=\"https://example.test/\" onclick=\"x()\">link-text</a><script>bad()</script></blockquote>";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_FORMATTING_FRAGMENT_simpleTextPolicy() {
        String bodyHtml = "simple-text-marker <b>bold</b> <em>emphasis</em> <a href=\"https://example.test/\">link-text</a>";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEFAULT_CONSTRUCTED_SAFELIST_directConstructor() {
        String bodyHtml = "<p>default-safelist-marker <span data-x=\"1\">span-text</span></p>";
        String baseUri = "";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPIED_RELAXED_SAFELIST_copyConstructor() {
        String bodyHtml = "<p>copy-marker <a href=\"https://example.test/a\">copied-link</a></p>";
        String baseUri = "https://base.example.test/root/";
        Safelist safelist = new Safelist(Safelist.relaxed());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEXT_AROUND_DISALLOWED_CONTAINER_RELAXED_customContainer() {
        String bodyHtml = "outside-container-marker<custom-widget><span>inside-container-marker</span></custom-widget>tail-container-marker";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BODY_AND_DOCUMENT_TAGS_IN_FRAGMENT_structuralTags() {
        String bodyHtml = "<html><head><title>head-title-marker</title></head><body><p>body-fragment-marker</p></body></html>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
