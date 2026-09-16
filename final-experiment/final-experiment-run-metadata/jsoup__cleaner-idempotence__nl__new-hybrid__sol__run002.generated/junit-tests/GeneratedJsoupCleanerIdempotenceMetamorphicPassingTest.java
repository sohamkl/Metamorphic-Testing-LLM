import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source(
            String bodyHtml, String baseUri, Safelist safelist) {
        return new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(bodyHtml, baseUri, safelist);
    }

    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input generateFollowUp(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source, String sourceOutput) {
        return new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(
                sourceOutput, source.arg1(), source.arg2());
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source) {
        String sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(source);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input followUp =
                generateFollowUp(source, sourceOutput);
        String followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_FRAGMENT_variation1() {
        assertMetamorphicRelationFor(source("", "", Safelist.none()));
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_variation1() {
        assertMetamorphicRelationFor(source(" \t\r\n", "http://example.test/", Safelist.none()));
    }

    @Test
    public void PLAIN_TEXT_NONE_variation1() {
        assertMetamorphicRelationFor(source(
                "Ordinary plain text without markup",
                "https://example.test/",
                Safelist.none()));
    }

    @Test
    public void TEXT_REQUIRING_HTML_ESCAPING_variation1() {
        assertMetamorphicRelationFor(source(
                "5 is &lt; 6 &amp; 7",
                "https://example.test/articles/page",
                Safelist.none()));
    }

    @Test
    public void UNKNOWN_WRAPPER_WITH_SAFE_DESCENDANT_variation1() {
        assertMetamorphicRelationFor(source(
                "<unknown>outer<b>inner</b></unknown>",
                "https://example.test/index?q=clean",
                Safelist.basic()));
    }

    @Test
    public void HTML_COMMENT_ONLY_variation1() {
        assertMetamorphicRelationFor(source(
                "<!-- removable comment -->",
                "",
                Safelist.basicWithImages()));
    }

    @Test
    public void SCRIPT_ONLY_FRAGMENT_variation1() {
        assertMetamorphicRelationFor(source(
                "<script>window.alert('unsafe');</script>",
                "http://example.test/",
                Safelist.relaxed()));
    }

    @Test
    public void UNCLOSED_ALLOWED_TAG_variation1() {
        assertMetamorphicRelationFor(source(
                "<p>Paragraph text <b>bold text",
                "https://example.test/content/",
                Safelist.basic()));
    }

    @Test
    public void MISNESTED_FORMATTING_TAGS_variation1() {
        assertMetamorphicRelationFor(source(
                "<b>bold <i>italic</b> trailing</i>",
                "https://example.test/docs/page",
                Safelist.basic()));
    }

    @Test
    public void DUPLICATE_LINK_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"https://first.example.test/a\" "
                        + "href=\"https://second.example.test/b\">duplicate link</a>",
                "https://base.example.test/path?q=1",
                Safelist.basic()));
    }

    @Test
    public void MIXED_CASE_UNQUOTED_MARKUP_variation1() {
        assertMetamorphicRelationFor(source(
                "<A HrEf=https://example.test/Path>Mixed Case Link</A>",
                "",
                Safelist.basic()));
    }

    @Test
    public void NULL_CHARACTER_IN_TEXT_variation1() {
        String html = "printable-left" + (char) 0 + "printable-right";
        assertMetamorphicRelationFor(source(
                html,
                "http://example.test/",
                Safelist.none()));
    }

    @Test
    public void UNICODE_TEXT_AND_EMOJI_variation1() {
        assertMetamorphicRelationFor(source(
                "Zażółć Ελληνικά 日本語 😀",
                "https://example.test/",
                Safelist.none()));
    }

    @Test
    public void NONE_STRIPS_FORMATTING_TAGS_variation1() {
        assertMetamorphicRelationFor(source(
                "<b>bold <i>italic <u>underlined</u></i></b>",
                "https://example.test/articles/",
                Safelist.none()));
    }

    @Test
    public void NONE_IMAGE_ONLY_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src=\"javascript:alert(1)\">",
                "https://example.test/page?q=image",
                Safelist.none()));
    }

    @Test
    public void EMPTY_CONSTRUCTOR_TEXT_variation1() {
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(source(
                "Text retained by an empty tag policy",
                "",
                safelist));
    }

    @Test
    public void EMPTY_CONSTRUCTOR_TAG_ONLY_variation1() {
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(source(
                "<div><span></span><br></div>",
                "http://example.test/",
                safelist));
    }

    @Test
    public void SIMPLE_TEXT_INLINE_FORMATTING_variation1() {
        assertMetamorphicRelationFor(source(
                "<b>one <em>two <i>three <strong>four <u>five</u></strong></i></em></b>",
                "https://example.test/",
                Safelist.simpleText()));
    }

    @Test
    public void SIMPLE_TEXT_BLOCK_WRAPPER_variation1() {
        assertMetamorphicRelationFor(source(
                "<p>Block text <b>with bold content</b></p>",
                "https://example.test/path/page",
                Safelist.simpleText()));
    }

    @Test
    public void SIMPLE_TEXT_REMOVES_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<strong class=\"notice\" style=\"color:red\" onclick=\"run()\">safe words</strong>",
                "https://example.test/page?q=attributes",
                Safelist.simpleText()));
    }

    @Test
    public void SIMPLE_TEXT_DEEP_NESTING_variation1() {
        assertMetamorphicRelationFor(source(
                "<b><em><i><strong><u>deep text</u></strong></i></em></b>",
                "",
                Safelist.simpleText()));
    }

    @Test
    public void BASIC_PARAGRAPH_AND_LIST_variation1() {
        assertMetamorphicRelationFor(source(
                "<p>Shopping list</p><ul><li>apples</li><li>oranges &amp; pears</li></ul>",
                "http://example.test/",
                Safelist.basic()));
    }

    @Test
    public void BASIC_HTTPS_LINK_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        assertMetamorphicRelationFor(source(
                "<a href=\"https://secure.example.test/resource\">secure destination</a>",
                "https://example.test/",
                safelist));
    }

    @Test
    public void BASIC_HTTP_LINK_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"http://example.test/public\">HTTP destination</a>",
                "https://base.example.test/articles/page",
                Safelist.basic()));
    }

    @Test
    public void BASIC_MAILTO_LINK_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"mailto:reader@example.test\">send mail</a>",
                "https://example.test/page?q=mail",
                Safelist.basic()));
    }

    @Test
    public void BASIC_FTP_LINK_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"ftp://files.example.test/pub/archive.txt\">download archive</a>",
                "",
                Safelist.basic()));
    }

    @Test
    public void BASIC_JAVASCRIPT_LINK_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"javascript:alert('unsafe')\">ordinary link text</a>",
                "http://example.test/",
                Safelist.basic()));
    }

    @Test
    public void BASIC_DATA_LINK_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"data:text/html,unsafe\">data link text</a>",
                "https://example.test/",
                Safelist.basic()));
    }

    @Test
    public void BASIC_RELATIVE_LINK_WITH_BASE_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"../item?q=1\">resolved item</a>",
                "https://example.test/catalog/section/page.html",
                Safelist.basic()));
    }

    @Test
    public void BASIC_RELATIVE_LINK_EMPTY_BASE_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        assertMetamorphicRelationFor(source(
                "<a href=\"images/item.html\">relative item</a>",
                "",
                safelist));
    }

    @Test
    public void BASIC_PROTOCOL_RELATIVE_LINK_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"//cdn.example.test/path\">CDN resource</a>",
                "https://example.test/articles/index.html",
                Safelist.basic()));
    }

    @Test
    public void BASIC_FRAGMENT_LINK_WITH_BASE_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"#section\">jump to section</a>",
                "https://example.test/guide/page.html",
                Safelist.basic()));
    }

    @Test
    public void BASIC_EVENT_ATTRIBUTE_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"https://example.test/safe\" onclick=\"steal()\">safe destination</a>",
                "https://base.example.test/",
                Safelist.basic()));
    }

    @Test
    public void BASIC_POLICY_ENFORCED_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"https://example.test/account\">account link</a>",
                "https://base.example.test/content/page",
                Safelist.basic()));
    }

    @Test
    public void BASIC_IMAGES_HTTPS_SOURCE_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src=\"https://images.example.test/photo.png\" alt=\"A landscape\">",
                "https://example.test/gallery?q=summer",
                Safelist.basicWithImages()));
    }

    @Test
    public void BASIC_IMAGES_RELATIVE_SOURCE_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src=\"images/photo.png\" alt=\"Relative photograph\">",
                "https://example.test/gallery/album/index.html",
                Safelist.basicWithImages()));
    }

    @Test
    public void BASIC_IMAGES_DATA_SOURCE_variation1() {
        Safelist safelist = new Safelist(Safelist.basicWithImages());
        assertMetamorphicRelationFor(source(
                "<img src=\"data:image/png;base64,AAAA\" alt=\"blocked inline image\">",
                "http://example.test/",
                safelist));
    }

    @Test
    public void BASIC_IMAGES_EVENT_ATTRIBUTE_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src=\"https://images.example.test/icon.png\" "
                        + "alt=\"application icon\" onerror=\"alert(1)\">",
                "https://example.test/",
                Safelist.basicWithImages()));
    }

    @Test
    public void BASIC_IMAGES_VOID_TAG_NORMALIZATION_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src=\"https://images.example.test/logo.png\" alt=\"Company logo\" />",
                "https://example.test/assets/page.html",
                Safelist.basicWithImages()));
    }

    @Test
    public void RELAXED_HEADING_AND_TABLE_variation1() {
        assertMetamorphicRelationFor(source(
                "<h2>Quarterly figures</h2>"
                        + "<table><tr><td>Revenue</td><td>42</td></tr></table>",
                "https://example.test/reports/table.html?q=q3",
                Safelist.relaxed()));
    }

    @Test
    public void RELAXED_IMAGE_AND_CAPTION_TEXT_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src=\"https://images.example.test/coast.jpg\" alt=\"Coast\">"
                        + "<span>View from the coast</span>",
                "",
                Safelist.relaxed()));
    }

    @Test
    public void RELAXED_CONTAINER_NESTING_variation1() {
        assertMetamorphicRelationFor(source(
                "<div><section><div><p>Nested <em>relaxed content</em></p></div></section></div>",
                "http://example.test/",
                Safelist.relaxed()));
    }

    @Test
    public void RELAXED_STYLE_ATTRIBUTE_variation1() {
        assertMetamorphicRelationFor(source(
                "<p style=\"position:fixed;color:red\">Visible paragraph text</p>",
                "https://example.test/",
                Safelist.relaxed()));
    }

    @Test
    public void RELAXED_IFRAME_variation1() {
        assertMetamorphicRelationFor(source(
                "<iframe src=\"https://frames.example.test/embed\">fallback iframe text</iframe>",
                "https://example.test/articles/page",
                Safelist.relaxed()));
    }

    @Test
    public void BODY_FRAGMENT_DOCUMENT_TAGS_variation1() {
        assertMetamorphicRelationFor(source(
                "<html><head><title>Fragment title</title></head>"
                        + "<body><h1>Body heading</h1><p>ordinary body text</p></body></html>",
                "",
                Safelist.relaxed()));
    }

    @Test
    public void BASE_ELEMENT_SUPPLIES_RESOLUTION_variation1() {
        assertMetamorphicRelationFor(source(
                "<base href=\"https://assets.example.test/docs/\">"
                        + "<a href=\"chapter.html\">chapter link</a>",
                "",
                Safelist.basic()));
    }

    @Test
    public void BASE_URI_PATH_QUERY_RESOLUTION_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href=\"./part/../item?mode=full#details\">normalized item</a>",
                "https://example.test/a/b/page.html?old=true",
                Safelist.basic()));
    }

    @Test
    public void COPIED_RELAXED_POLICY_variation1() {
        Safelist safelist = new Safelist(Safelist.relaxed());
        assertMetamorphicRelationFor(source(
                "<div><h3>Copied policy</h3><p>Read the "
                        + "<a href=\"https://example.test/manual\">manual</a>.</p></div>",
                "https://base.example.test/docs/index.html",
                safelist));
    }

    @Test
    public void COPIED_EMPTY_POLICY_variation1() {
        Safelist safelist = new Safelist(new Safelist());
        assertMetamorphicRelationFor(source(
                "<img src=\"https://images.example.test/empty.png\"><unknown></unknown>",
                "https://example.test/page?q=empty",
                safelist));
    }
}
