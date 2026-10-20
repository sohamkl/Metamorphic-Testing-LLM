import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static final class CleanInput {
        private final String bodyHtml;
        private final String baseUri;
        private final Safelist safelist;

        private CleanInput(String bodyHtml, String baseUri, Safelist safelist) {
            this.bodyHtml = bodyHtml;
            this.baseUri = baseUri;
            this.safelist = safelist;
        }
    }

    private CleanInput generateFollowUp(CleanInput source, String sourceOutput) {
        return new CleanInput(sourceOutput, source.baseUri, source.safelist);
    }

    private String run(CleanInput input) {
        return Jsoup.clean(input.bodyHtml, input.baseUri, input.safelist);
    }

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelationFor(CleanInput source) {
        String sourceOutput = run(source);
        CleanInput followUp = generateFollowUp(source, sourceOutput);
        String followUpOutput = run(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_FRAGMENT_NONE_emptyBody() {
        assertMetamorphicRelationFor(new CleanInput("", "", Safelist.none()));
    }

    @Test
    public void WHITESPACE_ONLY_NONE_whitespaceFragment() {
        assertMetamorphicRelationFor(new CleanInput(" \t\n\r ", "", Safelist.none()));
    }

    @Test
    public void PLAIN_TEXT_NONE_ordinaryText() {
        assertMetamorphicRelationFor(new CleanInput("Hello, safe world.", "", Safelist.none()));
    }

    @Test
    public void TEXT_WITH_HTML_ENTITIES_NONE_entityText() {
        assertMetamorphicRelationFor(new CleanInput(
            "5 &lt; 6 &amp; 7 &#62; 3",
            "",
            Safelist.none()));
    }

    @Test
    public void DISALLOWED_ELEMENT_WITH_TEXT_NONE_paragraphText() {
        assertMetamorphicRelationFor(new CleanInput(
            "<p>retained text</p>",
            "",
            Safelist.none()));
    }

    @Test
    public void SCRIPT_ONLY_NONE_rawTextScript() {
        assertMetamorphicRelationFor(new CleanInput(
            "<script>alert('x')</script>",
            "",
            Safelist.none()));
    }

    @Test
    public void COMMENT_AND_TEXT_NONE_visibleText() {
        assertMetamorphicRelationFor(new CleanInput(
            "<!-- hidden -->visible",
            "",
            Safelist.none()));
    }

    @Test
    public void SIMPLE_TEXT_ALLOWED_FORMATTING_formattingElements() {
        assertMetamorphicRelationFor(new CleanInput(
            "<b>bold</b><em>emphasis</em><strong>strong</strong><u>underlined</u>",
            "",
            Safelist.simpleText()));
    }

    @Test
    public void SIMPLE_TEXT_DISALLOWED_LINK_anchorText() {
        assertMetamorphicRelationFor(new CleanInput(
            "<a href=\"https://example.test/\">linked text</a>",
            "https://example.test/docs/",
            Safelist.simpleText()));
    }

    @Test
    public void BASIC_ALLOWED_PARAGRAPH_AND_LINK_absoluteHttpsLink() {
        assertMetamorphicRelationFor(new CleanInput(
            "<p>Read <a href=\"https://example.test/guide\">guide</a>.</p>",
            "https://origin.test/page",
            Safelist.basic()));
    }

    @Test
    public void BASIC_UNSAFE_JAVASCRIPT_HREF_rejectedProtocol() {
        assertMetamorphicRelationFor(new CleanInput(
            "<a href=\"javascript:alert(1)\">click</a>",
            "https://example.test/",
            Safelist.basic()));
    }

    @Test
    public void BASIC_MIXED_CASE_JAVASCRIPT_HREF_whitespaceAndCase() {
        assertMetamorphicRelationFor(new CleanInput(
            "<a href=\" \tJaVaScRiPt:alert(1)\">click</a>",
            "https://example.test/",
            Safelist.basic()));
    }

    @Test
    public void BASIC_HTTP_HREF_absoluteHttpLink() {
        assertMetamorphicRelationFor(new CleanInput(
            "<a href=\"http://example.test/path?q=1#part\">http link</a>",
            "https://origin.test/",
            Safelist.basic()));
    }

    @Test
    public void BASIC_RELATIVE_HREF_WITH_ABSOLUTE_BASE_relativePath() {
        assertMetamorphicRelationFor(new CleanInput(
            "<a href=\"child/page.html\">child</a>",
            "https://example.test/docs/index.html",
            Safelist.basic()));
    }

    @Test
    public void BASIC_ROOT_RELATIVE_HREF_WITH_ABSOLUTE_BASE_rootPath() {
        assertMetamorphicRelationFor(new CleanInput(
            "<a href=\"/assets/file\">asset</a>",
            "https://example.test/docs/index.html",
            Safelist.basic()));
    }

    @Test
    public void BASIC_FRAGMENT_ONLY_HREF_fragmentReference() {
        assertMetamorphicRelationFor(new CleanInput(
            "<a href=\"#section\">section</a>",
            "https://example.test/docs/index.html",
            Safelist.basic()));
    }

    @Test
    public void BASIC_RELATIVE_HREF_EMPTY_BASE_noExternalBase() {
        assertMetamorphicRelationFor(new CleanInput(
            "<a href=\"child/page.html\">child</a>",
            "",
            Safelist.basic()));
    }

    @Test
    public void BASIC_DISALLOWED_IMAGE_trailingText() {
        assertMetamorphicRelationFor(new CleanInput(
            "<img src=\"https://example.test/image.png\" alt=\"sample\">after",
            "https://origin.test/",
            Safelist.basic()));
    }

    @Test
    public void BASIC_WITH_IMAGES_ABSOLUTE_IMAGE_safeImage() {
        assertMetamorphicRelationFor(new CleanInput(
            "<img src=\"https://example.test/image.png\" alt=\"sample\">",
            "https://origin.test/",
            Safelist.basicWithImages()));
    }

    @Test
    public void BASIC_WITH_IMAGES_DATA_IMAGE_dataSource() {
        assertMetamorphicRelationFor(new CleanInput(
            "<img src=\"data:image/png;base64,AAAA\" alt=\"inline\">",
            "https://origin.test/",
            Safelist.basicWithImages()));
    }

    @Test
    public void BASIC_WITH_IMAGES_JAVASCRIPT_IMAGE_unsafeSource() {
        assertMetamorphicRelationFor(new CleanInput(
            "<img src=\"javascript:alert(1)\" alt=\"bad\">",
            "https://origin.test/",
            Safelist.basicWithImages()));
    }

    @Test
    public void RELAXED_TABLE_STRUCTURE_tableMarkup() {
        assertMetamorphicRelationFor(new CleanInput(
            "<table><thead><tr><th>H</th></tr></thead><tbody><tr><td>D</td></tr></tbody></table>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_HEADING_AND_DIV_blockMarkup() {
        assertMetamorphicRelationFor(new CleanInput(
            "<div><h1>Title</h1><p>Paragraph</p></div>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_UNKNOWN_ELEMENT_allowedDescendant() {
        assertMetamorphicRelationFor(new CleanInput(
            "<unknown-widget><p>kept descendant text</p></unknown-widget>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_DISALLOWED_GLOBAL_ATTRIBUTE_eventAndCustomAttributes() {
        assertMetamorphicRelationFor(new CleanInput(
            "<p onclick=\"alert(1)\" data-x=\"1\" title=\"ok\">text</p>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_STYLE_ATTRIBUTE_inlineStyle() {
        assertMetamorphicRelationFor(new CleanInput(
            "<p style=\"color:red\">styled text</p>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_MALFORMED_NESTING_misnestedBlocks() {
        assertMetamorphicRelationFor(new CleanInput(
            "<p>one<div>two</p>three</div>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_UNCLOSED_TAGS_openElements() {
        assertMetamorphicRelationFor(new CleanInput(
            "<div><p>open <em>still open",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_CASE_NORMALIZATION_uppercaseMarkup() {
        assertMetamorphicRelationFor(new CleanInput(
            "<P CLASS=\"note\"><A HREF=\"https://example.test/\">Link</A></P>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_DOCUMENT_LIKE_FRAGMENT_documentLookingInput() {
        assertMetamorphicRelationFor(new CleanInput(
            "<html><head><title>T</title></head><body><p>Body text</p></body></html>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_BASE_ELEMENT_AND_RELATIVE_LINK_embeddedBase() {
        assertMetamorphicRelationFor(new CleanInput(
            "<base href=\"https://example.test/assets/\"><a href=\"file.html\">file</a>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_EMBEDDED_COMMENT_commentWithinParagraph() {
        assertMetamorphicRelationFor(new CleanInput(
            "<p>before<!-- remove comment -->after</p>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void RELAXED_SCRIPT_WITH_SAFE_SIBLING_scriptAndParagraph() {
        assertMetamorphicRelationFor(new CleanInput(
            "<script>window.x=1</script><p>safe sibling</p>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void DEFAULT_CONSTRUCTED_SAFELIST_TEXT_plainText() {
        assertMetamorphicRelationFor(new CleanInput(
            "plain text only",
            "",
            new Safelist()));
    }

    @Test
    public void DEFAULT_CONSTRUCTED_SAFELIST_MARKUP_markupAndText() {
        assertMetamorphicRelationFor(new CleanInput(
            "<p>text <b>format</b></p>",
            "",
            new Safelist()));
    }

    @Test
    public void COPIED_BASIC_SAFELIST_LINK_copiedPolicy() {
        Safelist copiedBasic = new Safelist(Safelist.basic());
        assertMetamorphicRelationFor(new CleanInput(
            "<p><a href=\"https://example.test/a\">A</a></p>",
            "",
            copiedBasic));
    }

    @Test
    public void COPIED_NONE_SAFELIST_ENTITY_TEXT_copiedNonePolicy() {
        Safelist copiedNone = new Safelist(Safelist.none());
        assertMetamorphicRelationFor(new CleanInput(
            "<b>5 &lt; 6</b>",
            "",
            copiedNone));
    }

    @Test
    public void RELAXED_DEEP_NESTING_nestedMarkup() {
        assertMetamorphicRelationFor(new CleanInput(
            "<div><p><strong><em><span>deep</span></em></strong></p></div>",
            "",
            Safelist.relaxed()));
    }

    @Test
    public void BASIC_MULTIPLE_URL_ATTRIBUTES_AND_TEXT_mixedLinks() {
        assertMetamorphicRelationFor(new CleanInput(
            "<p><a href=\"https://example.test/ok\">ok</a> <a href=\"javascript:x()\">bad</a> tail</p>",
            "https://origin.test/",
            Safelist.basic()));
    }
}
