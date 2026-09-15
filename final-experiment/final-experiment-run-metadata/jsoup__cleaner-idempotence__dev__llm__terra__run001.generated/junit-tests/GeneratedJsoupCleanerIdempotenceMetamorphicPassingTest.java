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
    void WHITESPACE_ONLY_NONE_spacesTabsAndLines() {
        String bodyHtml = "  \t \n\r\n  ";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PLAIN_TEXT_WITH_HTML_METACHARACTERS_NONE_symbolsAndUnicode() {
        String bodyHtml = "5 < 6 & 7 > 3, \"quoted\" café";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PLAIN_TEXT_WITH_HTML_METACHARACTERS_NONE_apostrophesAndAccents() {
        String bodyHtml = "A & B < C > D: \"naïve\" résumé";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_STRIPS_ELEMENT_RETAINS_TEXT_paragraphWrapper() {
        String bodyHtml = "<p>retained text</p>";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_STRIPS_SCRIPT_WITHOUT_TEXT_scriptDataOnly() {
        String bodyHtml = "<script>window.alert('unsafe');</script>";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIMPLE_TEXT_FORMATTING_AND_BREAKS_nestedFormattingWithBreak() {
        String bodyHtml = "<b>Bold <em>and emphasized</em></b><br><i>italic text</i>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIMPLE_TEXT_FORMATTING_AND_BREAKS_adjacentFormattingAndParagraph() {
        String bodyHtml = "<strong>first</strong><em> second</em><p><code>third line</code></p>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_ALLOWED_LINK_ABSOLUTE_HTTPS_safeAbsoluteHref() {
        String bodyHtml = "<a href=\"https://safe.example.test/path?q=1#part\">safe link</a>";
        String baseUri = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_RELATIVE_WITH_ABSOLUTE_BASE_parentDirectoryReference() {
        String bodyHtml = "<a href=\"../docs/item.html\">documentation</a>";
        String baseUri = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_RELATIVE_WITH_EMPTY_BASE_noBaseUri() {
        String bodyHtml = "<a href=\"relative/path\">relative link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_JAVASCRIPT_PROTOCOL_executableHref() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">visible link text</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_PROTOCOL_OBFUSCATION_characterReferenceInProtocol() {
        String bodyHtml = "<a href=\"java&#x73;cript:alert(1)\">encoded protocol</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_LINK_PROTOCOL_OBFUSCATION_controlWhitespaceInProtocol() {
        String bodyHtml = "<a href=\"java\nscript:alert(1)\">whitespace protocol</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_DISALLOWED_EVENT_AND_STYLE_ATTRIBUTES_linkAttributes() {
        String bodyHtml = "<a href=\"https://safe.example.test/\" onclick=\"alert(1)\" style=\"color:red\">safe visible text</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_DISALLOWED_CONTAINER_RETAINS_NESTED_TEXT_divWithAnchor() {
        String bodyHtml = "<div>outer text <a href=\"https://safe.example.test/path\">nested link</a> tail text</div>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_IMAGE_HTTP_httpImageSource() {
        String bodyHtml = "<img src=\"http://images.example.test/a.png\" alt=\"sample image\">";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_IMAGE_DATA_PROTOCOL_dataImageSource() {
        String bodyHtml = "before image <img src=\"data:image/png;base64,AAAA\" alt=\"embedded\"> after image";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_TABLE_LIST_AND_FORMATTING_tableListAndEmphasis() {
        String bodyHtml = "<h2>Heading</h2><table><tr><td>cell <strong>one</strong></td></tr></table><ul><li>first item</li><li><em>second item</em></li></ul>";
        String baseUri = "https://example.test/content/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_TABLE_LIST_AND_FORMATTING_orderedListAndTableHead() {
        String bodyHtml = "<h3>Report</h3><table><thead><tr><th>name</th></tr></thead><tbody><tr><td><b>value</b></td></tr></tbody></table><ol><li>alpha</li><li>beta</li></ol>";
        String baseUri = "https://example.test/content/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_MALFORMED_NESTING_overlappingFormatting() {
        String bodyHtml = "<p>first <b>bold <em>emphasis</b> after</em><p>second subtree";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_MALFORMED_NESTING_unclosedListAndParagraph() {
        String bodyHtml = "<div>outer <p>paragraph <strong>strong text<ul><li>first<li>second";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_COMMENTS_DOCTYPE_AND_PROCESSING_LIKE_INPUT_nonElementTokens() {
        String bodyHtml = "<!-- hidden comment --><!DOCTYPE note><?process value?><p>ordinary retained text</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_ENTITY_AND_ATTRIBUTE_QUOTING_namedNumericAndQueryAmpersand() {
        String bodyHtml = "<a href=\"https://safe.example.test/search?a=1&amp;b=two\">Tom &amp; J&#233;r&#244;me</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_ENTITY_AND_ATTRIBUTE_QUOTING_quotesAndUnicodeReference() {
        String bodyHtml = "<a title=\"A &amp; B\" href=\"https://safe.example.test/?x=1&amp;y=2\">Fish &amp; chips &#9731;</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PRESERVE_RELATIVE_LINK_EMPTY_BASE_dummyUriBranch() {
        String bodyHtml = "<a href=\"docs/guide.html\">guide</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PRESERVE_RELATIVE_LINK_ABSOLUTE_BASE_parentRelativeReference() {
        String bodyHtml = "<a href=\"../asset/file.html\">asset file</a>";
        String baseUri = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CUSTOM_EMPTY_SAFELIST_directTextAndElement() {
        String bodyHtml = "direct text <span class=\"marker\">element text</span>";
        String baseUri = "";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COPIED_RELAXED_SAFELIST_mixedSafeAndUnsafeMarkup() {
        String bodyHtml = "<p onclick=\"alert(1)\">retained <strong>structure</strong></p><script>alert('remove')</script>";
        String baseUri = "http://example.test/base/";
        Safelist safelist = new Safelist(Safelist.relaxed());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
