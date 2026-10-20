import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError("Cleaning must be idempotent: source output <"
                    + sourceOutput + "> differs from follow-up output <" + followUpOutput + ">");
        }
    }

    @Test
    void EMPTY_FRAGMENT_emptyBasic() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WHITESPACE_ONLY_FRAGMENT_whitespaceBasic() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(" \t\n  ", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(" \t\n  ", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PLAIN_TEXT_WITH_HTML_METACHARACTERS_escapedText() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("5 < 6 & 7 > 3", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("5 < 6 & 7 > 3", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ENTITY_AND_LITERAL_TEXT_entityAndAmpersand() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("Tom &amp; Jerry & friends", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("Tom &amp; Jerry & friends", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMMENT_ONLY_FRAGMENT_commentRemoved() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<!-- untrusted comment -->", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<!-- untrusted comment -->", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_POLICY_STRIPS_TAGS_RETAINS_TEXT_nestedFormatting() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("<p>Hello <strong>safe text</strong>!</p>", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<p>Hello <strong>safe text</strong>!</p>", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIMPLE_TEXT_FORMATTING_formattingWithoutParagraph() {
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean("<p><strong>bold</strong> and <em>emphasis</em></p>", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<p><strong>bold</strong> and <em>emphasis</em></p>", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_ALLOWED_TEXT_ELEMENTS_paragraphListAndCode() {
        Safelist safelist = Safelist.basic();
        String html = "<p>One <em>two</em><br><code>three</code></p><ul><li>four</li></ul>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_UNSUPPORTED_CONTAINER_WITH_ALLOWED_CHILD_sectionWrapper() {
        Safelist safelist = Safelist.basic();
        String html = "<section>outside <strong>inside</strong></section>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_TABLE_STRUCTURE_tableNormalization() {
        Safelist safelist = Safelist.relaxed();
        String html = "<table><thead><tr><th>H</th></tr></thead><tbody><tr><td>C</td></tr></tbody></table>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_REMOVES_IMAGE_ELEMENT_imageBetweenText() {
        Safelist safelist = Safelist.basic();
        String html = "<p>before<img src=\"https://example.test/a.png\" alt=\"image\">after</p>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_WITH_IMAGES_RETAINS_SAFE_IMAGE_httpsImage() {
        Safelist safelist = Safelist.basicWithImages();
        String html = "<img src=\"https://example.test/assets/pic.png\" alt=\"portrait\" width=\"40\" height=\"20\">";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNKNOWN_CUSTOM_ELEMENT_customElementText() {
        Safelist safelist = Safelist.relaxed();
        String html = "<widget-card data-state=\"x\">widget text</widget-card>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DISALLOWED_DATA_ATTRIBUTE_anchorTrackingAttribute() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"https://example.test/ok\" data-track=\"abc\">link</a>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EVENT_HANDLER_ATTRIBUTE_inlineHandler() {
        Safelist safelist = Safelist.basic();
        String html = "<strong onclick=\"alert(1)\">text</strong>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCRIPT_WITH_VISIBLE_SIBLING_scriptAndParagraph() {
        Safelist safelist = Safelist.basic();
        String html = "<script>alert('x')</script><p>visible</p>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCRIPT_ONLY_FRAGMENT_scriptOnly() {
        Safelist safelist = Safelist.basic();
        String html = "<script>alert('x')</script>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAVASCRIPT_HREF_explicitJavaScriptProtocol() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"javascript:alert(1)\">click</a>";
        String baseUri = "https://example.test/root/page.html";
        String sourceOutput = Jsoup.clean(html, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OBFUSCATED_JAVASCRIPT_HREF_controlWhitespaceAndCase() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"&#x09;JaVaScRiPt:alert(1)\">click</a>";
        String baseUri = "https://example.test/root/page.html";
        String sourceOutput = Jsoup.clean(html, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DATA_HREF_dataProtocol() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"data:text/html,unsafe\">data link</a>";
        String baseUri = "https://example.test/root/page.html";
        String sourceOutput = Jsoup.clean(html, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ABSOLUTE_HTTP_HREF_httpLink() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"http://example.test/path?q=1#part\">http link</a>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ABSOLUTE_HTTPS_HREF_httpsQueryAndFragment() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"https://example.test/a/b?x=1&amp;y=2#top\">secure</a>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAILTO_HREF_mailLink() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"mailto:user@example.test?subject=Hello\">mail</a>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_HREF_WITH_ABSOLUTE_BASE_baseResolution() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"images/item.html\">item</a>";
        String baseUri = "https://example.test/catalog/page.html";
        String sourceOutput = Jsoup.clean(html, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_HREF_EMPTY_BASE_DEFAULT_POLICY_unresolvedLink() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"images/item.html\">item</a>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_HREF_EMPTY_BASE_PRESERVED_dummyUriSentinel() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String html = "<a href=\"images/item.html\">item</a>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_HREF_NONEMPTY_BASE_PRESERVED_parentRelativeLink() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String html = "<a href=\"../docs/readme.html\">docs</a>";
        String baseUri = "https://example.test/catalog/items/page.html";
        String sourceOutput = Jsoup.clean(html, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRAGMENT_ONLY_HREF_sameDocumentFragment() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"#details\">details</a>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROTOCOL_RELATIVE_HREF_networkPathReference() {
        Safelist safelist = Safelist.basic();
        String html = "<a href=\"//cdn.example.test/lib.js\">cdn</a>";
        String baseUri = "https://example.test/root/page.html";
        String sourceOutput = Jsoup.clean(html, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOCUMENT_BASE_ELEMENT_AFFECTS_RELATIVE_LINK_embeddedBaseElement() {
        Safelist safelist = Safelist.basic();
        String html = "<base href=\"https://assets.example.test/sub/\"><a href=\"file.html\">file</a>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNCLOSED_ALLOWED_TAGS_parserBalancing() {
        Safelist safelist = Safelist.basic();
        String html = "<p>one <em>two";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MISNESTED_ALLOWED_TAGS_treeRepair() {
        Safelist safelist = Safelist.basic();
        String html = "<p><strong>bold <em>both</strong> emphasis</em></p>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ESCAPED_MARKUP_IS_TEXT_encodedScriptText() {
        Safelist safelist = Safelist.none();
        String html = "&lt;script&gt;not executable&lt;/script&gt;";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_CASE_ELEMENT_AND_ATTRIBUTE_NAMES_caseNormalization() {
        Safelist safelist = Safelist.basic();
        String html = "<A HREF=\"https://example.test/x\"><STRONG>Case</STRONG></A>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HTML_BODY_WRAPPERS_IN_FRAGMENT_documentWrappers() {
        Safelist safelist = Safelist.basic();
        String html = "<html><head><title>ignored</title></head><body><p>body text</p></body></html>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNICODE_TEXT_AND_ENTITY_SERIALIZATION_unicodeAndNbsp() {
        Safelist safelist = Safelist.basic();
        String html = "<p>café 漢字 😀&nbsp;end</p>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_SAFE_AND_UNSAFE_LINKS_independentProtocolFiltering() {
        Safelist safelist = Safelist.basic();
        String html = "<p><a href=\"https://example.test/ok\">good</a> <a href=\"javascript:bad()\">bad</a></p>";
        String baseUri = "https://example.test/root/";
        String sourceOutput = Jsoup.clean(html, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNSAFE_OUTER_ELEMENT_WITH_ALLOWED_NESTED_CONTENT_scriptRawText() {
        Safelist safelist = Safelist.basic();
        String html = "<script><strong>not markup in script</strong></script><strong>visible</strong>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FORM_CONTROL_CONTENT_relaxedFormMarkup() {
        Safelist safelist = Safelist.relaxed();
        String html = "<form action=\"https://example.test/post\"><label>Name <input name=\"n\" value=\"v\"></label></form>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_CONSTRUCTED_SAFELIST_defaultAllowList() {
        Safelist safelist = new Safelist();
        String html = "<p>retained text <strong>format</strong></p>";
        String sourceOutput = Jsoup.clean(html, "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(html, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
