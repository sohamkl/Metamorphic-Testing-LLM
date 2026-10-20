import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static final class Source {
        private final String bodyHtml;
        private final String baseUri;
        private final Safelist safelist;

        private Source(String bodyHtml, String baseUri, Safelist safelist) {
            this.bodyHtml = bodyHtml;
            this.baseUri = baseUri;
            this.safelist = safelist;
        }
    }

    private Source generateFollowUp(Source source, String sourceOutput) {
        return new Source(sourceOutput, source.baseUri, source.safelist);
    }

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelationFor(Source source) {
        String sourceOutput = Jsoup.clean(source.bodyHtml, source.baseUri, source.safelist);
        Source followUp = generateFollowUp(source, sourceOutput);
        Assertions.assertSame(source.safelist, followUp.safelist);
        Assertions.assertEquals(source.baseUri, followUp.baseUri);
        String followUpOutput = Jsoup.clean(followUp.bodyHtml, followUp.baseUri, followUp.safelist);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void verify(String bodyHtml, String baseUri, Safelist safelist) {
        assertMetamorphicRelationFor(new Source(bodyHtml, baseUri, safelist));
    }

    @Test
    void EMPTY_FRAGMENT_NONE_emptyBody() {
        verify("", "", Safelist.none());
    }

    @Test
    void WHITESPACE_ONLY_NONE_allWhitespaceCharacters() {
        verify(" \t\n\r ", "", Safelist.none());
    }

    @Test
    void PLAIN_ASCII_TEXT_NONE_visibleAscii() {
        verify("Visible plain text 123", "", Safelist.none());
    }

    @Test
    void UNICODE_TEXT_NONE_supplementaryCharacters() {
        verify("café 東京 😀", "", Safelist.none());
    }

    @Test
    void TEXT_WITH_HTML_METACHARACTERS_NONE_serializedText() {
        verify("5 < 6 & 7 > 3 \"quoted\" 'apostrophe'", "", Safelist.none());
    }

    @Test
    void NAMED_AND_NUMERIC_ENTITIES_NONE_entityText() {
        verify("&lt;tag&gt; &amp; &#x1F600; &#169;", "", Safelist.none());
    }

    @Test
    void SIMPLE_ELEMENT_NONE_textNodeOnly() {
        verify("<p>VISIBLE</p>", "", Safelist.none());
    }

    @Test
    void NESTED_ELEMENTS_NONE_nestedVisibleText() {
        verify("<div><p>OUTER <strong>INNER</strong></p></div>", "", Safelist.none());
    }

    @Test
    void COMMENT_AND_VISIBLE_TEXT_NONE_commentRemoval() {
        verify("<!-- comment --><p>VISIBLE</p>", "", Safelist.none());
    }

    @Test
    void DOCTYPE_AND_VISIBLE_TEXT_NONE_declarationInFragment() {
        verify("<!doctype html><p>VISIBLE</p>", "", Safelist.none());
    }

    @Test
    void UNTERMINATED_TAG_NONE_parserRecovery() {
        verify("<p>VISIBLE", "", Safelist.none());
    }

    @Test
    void MISNESTED_ELEMENTS_NONE_treeBalancing() {
        verify("<b><i>VISIBLE</b> AFTER</i>", "", Safelist.none());
    }

    @Test
    void SCRIPT_ELEMENT_NONE_activeContentCandidate() {
        verify("<script>alert(1)</script><p>VISIBLE</p>", "", Safelist.none());
    }

    @Test
    void STYLE_ELEMENT_NONE_styleContentCandidate() {
        verify("<style>body{color:red}</style><p>VISIBLE</p>", "", Safelist.none());
    }

    @Test
    void EVENT_HANDLER_ATTRIBUTE_BASIC_onclickRemoval() {
        verify("<p onclick=\"alert(1)\">VISIBLE</p>", "https://example.test/", Safelist.basic());
    }

    @Test
    void UNKNOWN_ATTRIBUTE_BASIC_nonstandardAttributes() {
        verify("<p data-x=\"1\" custom=\"2\" title=\"title\">VISIBLE</p>",
            "https://example.test/", Safelist.basic());
    }

    @Test
    void JAVASCRIPT_LINK_BASIC_protocolFiltering() {
        verify("<a href=\"javascript:alert(1)\">VISIBLE</a>",
            "https://example.test/base/", Safelist.basic());
    }

    @Test
    void DATA_URL_LINK_BASIC_dataProtocolFiltering() {
        verify("<a href=\"data:text/html,example\">VISIBLE</a>",
            "https://example.test/base/", Safelist.basic());
    }

    @Test
    void ABSOLUTE_HTTPS_LINK_BASIC_absoluteUrl() {
        verify("<a href=\"https://other.example/path?q=1#f\">VISIBLE</a>",
            "https://example.test/base/", Safelist.basic());
    }

    @Test
    void RELATIVE_LINK_BASIC_NONEMPTY_BASE_directoryResolution() {
        verify("<a href=\"child/page.html?x=1#part\">VISIBLE</a>",
            "https://example.test/parent/", Safelist.basic());
    }

    @Test
    void ROOT_RELATIVE_LINK_BASIC_NONEMPTY_BASE_rootResolution() {
        verify("<a href=\"/root/item\">VISIBLE</a>",
            "https://example.test/parent/page", Safelist.basic());
    }

    @Test
    void FRAGMENT_ONLY_LINK_BASIC_fragmentUrl() {
        verify("<a href=\"#section\">VISIBLE</a>",
            "https://example.test/doc/page", Safelist.basic());
    }

    @Test
    void RELATIVE_LINK_BASIC_EMPTY_BASE_emptyBaseBranch() {
        verify("<a href=\"relative\">VISIBLE</a>", "", Safelist.basic());
    }

    @Test
    void IMAGE_WITH_RELATIVE_SRC_BASIC_WITH_IMAGES_relativeImage() {
        verify("<img src=\"images/pic.png\" alt=\"VISIBLE\">",
            "https://example.test/assets/", Safelist.basicWithImages());
    }

    @Test
    void IMAGE_WITH_JAVASCRIPT_SRC_BASIC_WITH_IMAGES_unsafeImageProtocol() {
        verify("<img src=\"javascript:alert(1)\" alt=\"VISIBLE\">",
            "https://example.test/assets/", Safelist.basicWithImages());
    }

    @Test
    void MIXED_TEXT_LINK_IMAGE_RELAXED_mixedContent() {
        verify("<p>START <a href=\"docs/item\">LINK</a> <img src=\"img.png\" alt=\"ALT\"> <em>END</em></p>",
            "https://example.test/base/", Safelist.relaxed());
    }

    @Test
    void TABLE_STRUCTURE_RELAXED_tableContent() {
        verify("<table><tr><td>A</td><td>B</td></tr></table>",
            "https://example.test/", Safelist.relaxed());
    }

    @Test
    void LIST_STRUCTURE_RELAXED_nestedLists() {
        verify("<ol><li>ONE</li><li>TWO<ul><li>THREE</li></ul></li></ol>",
            "https://example.test/", Safelist.relaxed());
    }

    @Test
    void FORM_CONTROL_RELAXED_labelAndInput() {
        verify("<label>VISIBLE<input type=\"text\" value=\"v\"></label>",
            "https://example.test/", Safelist.relaxed());
    }

    @Test
    void SIMPLE_TEXT_FACTORY_FORMATTING_formattingMarkup() {
        verify("<p>START <b>BOLD</b> <i>ITALIC</i> END</p>",
            "https://example.test/", Safelist.simpleText());
    }

    @Test
    void SIMPLE_TEXT_FACTORY_LINK_CANDIDATE_anchorCandidate() {
        verify("<a href=\"https://example.test/\">VISIBLE</a>",
            "https://example.test/", Safelist.simpleText());
    }

    @Test
    void DEFAULT_CONSTRUCTED_SAFELIST_TEXT_directConstructionText() {
        verify("VISIBLE", "", new Safelist());
    }

    @Test
    void DEFAULT_CONSTRUCTED_SAFELIST_MARKUP_directConstructionMarkup() {
        verify("<p>VISIBLE <span>INNER</span></p>",
            "https://example.test/", new Safelist());
    }

    @Test
    void COPIED_NONE_SAFELIST_copyConstructorFromNone() {
        verify("<p>VISIBLE <b>INNER</b></p>", "", new Safelist(Safelist.none()));
    }

    @Test
    void COPIED_BASIC_SAFELIST_RELATIVE_LINK_copyConstructorFromBasic() {
        verify("<a href=\"child\">VISIBLE</a>",
            "http://example.test/dir/", new Safelist(Safelist.basic()));
    }

    @Test
    void UPPERCASE_TAG_AND_ATTRIBUTE_BASIC_htmlCaseNormalization() {
        verify("<A HREF=\"https://example.test/\">VISIBLE</A>",
            "https://base.example/", Safelist.basic());
    }

    @Test
    void DUPLICATE_ATTRIBUTES_BASIC_attributeNormalization() {
        verify("<a href=\"https://first.example/\" href=\"javascript:alert(1)\">VISIBLE</a>",
            "https://base.example/", Safelist.basic());
    }

    @Test
    void ATTRIBUTE_QUOTING_VARIANTS_BASIC_quotedAndUnquotedValues() {
        verify("<a href=https://example.test/a>ONE</a><a href='https://example.test/b'>TWO</a><a href=\"https://example.test/c\">THREE</a>",
            "https://base.example/", Safelist.basic());
    }

    @Test
    void EMBEDDED_BASE_TAG_RELATIVE_LINK_inFragmentBaseElement() {
        verify("<base href=\"https://embedded.example/root/\"><a href=\"child\">VISIBLE</a>",
            "https://supplied.example/start/", Safelist.basic());
    }

    @Test
    void BASE_URI_WITH_QUERY_AND_FRAGMENT_urlResolutionContext() {
        verify("<a href=\"child\">VISIBLE</a>",
            "https://example.test/dir/page?x=1#old", Safelist.basic());
    }

    @Test
    void MULTIPLE_TOP_LEVEL_NODES_RELAXED_siblingNodes() {
        verify("BEFORE<p>PARAGRAPH</p>BETWEEN<div>DIVTEXT</div>AFTER",
            "https://example.test/", Safelist.relaxed());
    }

    @Test
    void DEEPLY_NESTED_VISIBLE_TEXT_RELAXED_deepTree() {
        verify("<div><section><article><p><span><em>VISIBLE</em></span></p></article></section></div>",
            "https://example.test/", Safelist.relaxed());
    }
}
