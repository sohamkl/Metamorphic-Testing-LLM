import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;

import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError("Cleaning must be idempotent: source output <"
                    + sourceOutput + "> differs from follow-up output <" + followUpOutput + ">");
        }
    }

    @Test
    public void PLAIN_TEXT_EMPTY_AND_ABSOLUTE_BASE_emptyBase() {
        String bodyHtml = "plain visible text";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_EMPTY_AND_ABSOLUTE_BASE_absoluteBase() {
        String bodyHtml = "plain visible text";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_TEXT_AND_ESCAPED_MARKUP_literalReservedCharacters() {
        String bodyHtml = "5 is < 6 & 7 > 3";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_TEXT_AND_ESCAPED_MARKUP_markupLookingText() {
        String bodyHtml = "before <b>bold</b> after";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRESH_AND_COPIED_SAFELIST_freshConstructor() {
        String bodyHtml = "<p>constructor marker</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRESH_AND_COPIED_SAFELIST_copyConstructor() {
        String bodyHtml = "<p>copy marker</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist(Safelist.basic());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ELEMENT_AND_LINK_CONTENT_inlineAndBlockMarkup() {
        String bodyHtml = "<p>basic marker <b>bold</b> <em>emphasis</em></p>";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ELEMENT_AND_LINK_CONTENT_absoluteLink() {
        String bodyHtml = "<a href=\"https://safe.example/path\">link marker</a>";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_IMAGE_AND_MIXED_CONTENT_absoluteImage() {
        String bodyHtml = "<img src=\"https://images.example/a.png\" alt=\"image marker\">";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_IMAGE_AND_MIXED_CONTENT_relativeImage() {
        String bodyHtml = "before <img src=\"icons/p.png\" alt=\"relative marker\"> after";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_RICH_CONTENT_headingAndParagraph() {
        String bodyHtml = "<h2>heading marker</h2><p>paragraph <strong>strong</strong></p>";
        String baseUri = "https://example.test/root/dir/?q=1";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_RICH_CONTENT_listAndCode() {
        String bodyHtml = "<ul><li>one</li><li>two <code>x()</code></li></ul>";
        String baseUri = "https://example.test/root/dir/?q=1";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_RICH_CONTENT_blockquoteAndRule() {
        String bodyHtml = "<blockquote><p>quoted marker</p></blockquote><hr>";
        String baseUri = "https://example.test/root/dir/?q=1";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_FORMATTING_inlineFormatting() {
        String bodyHtml = "simple <b>marker</b> text";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_FORMATTING_paragraphBoundary() {
        String bodyHtml = "<p>first marker</p><p>second marker</p>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_ELEMENT_REMOVAL_script() {
        String bodyHtml = "<p>keep marker</p><script>alert(1)</script>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_ELEMENT_REMOVAL_iframe() {
        String bodyHtml = "<p>keep marker</p><iframe src=\"https://evil.example/\"></iframe>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_ELEMENT_REMOVAL_style() {
        String bodyHtml = "<style>body{display:none}</style><p>keep marker</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ATTRIBUTE_FILTERING_eventHandler() {
        String bodyHtml = "<p onclick=\"alert(1)\">event marker</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ATTRIBUTE_FILTERING_styleAndData() {
        String bodyHtml = "<p style=\"color:red\" data-x=\"1\">attribute marker</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ATTRIBUTE_FILTERING_linkAttributes() {
        String bodyHtml = "<a href=\"https://safe.example/\" onclick=\"x()\" title=\"title marker\">link</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void URL_PROTOCOL_CLASSES_https() {
        String bodyHtml = "<a href=\"https://safe.example/a\">https marker</a>";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void URL_PROTOCOL_CLASSES_javascript() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">js marker</a>";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void URL_PROTOCOL_CLASSES_mailto() {
        String bodyHtml = "<a href=\"mailto:user@example.test\">mail marker</a>";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void URL_PROTOCOL_CLASSES_dataImage() {
        String bodyHtml = "<img src=\"data:image/png;base64,AA==\" alt=\"data marker\">";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_URL_AND_EMPTY_BASE_relativeEmptyBase() {
        String bodyHtml = "<a href=\"guide.html\">relative marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_URL_AND_EMPTY_BASE_rootRelativeAbsoluteBase() {
        String bodyHtml = "<a href=\"/root/path\">root marker</a>";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_URL_AND_EMPTY_BASE_fragmentEmptyBase() {
        String bodyHtml = "<a href=\"#part\">fragment marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_URL_AND_EMPTY_BASE_protocolRelativeAbsoluteBase() {
        String bodyHtml = "<a href=\"//cdn.example/lib.js\">protocol-relative marker</a>";
        String baseUri = "https://example.test/docs/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OBFUSCATED_PROTOCOL_INPUTS_encodedNewline() {
        String bodyHtml = "<a href=\" java&#x0A;script:alert(1)\">encoded marker</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OBFUSCATED_PROTOCOL_INPUTS_mixedCase() {
        String bodyHtml = "<a href=\"JaVaScRiPt:alert(1)\">case marker</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OBFUSCATED_PROTOCOL_INPUTS_entityEncodedInitialCharacter() {
        String bodyHtml = "<a href=\"&#x6a;avascript:alert(1)\">entity marker</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_AND_UNBALANCED_HTML_openParagraph() {
        String bodyHtml = "<p>open marker";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_AND_UNBALANCED_HTML_crossedFormatting() {
        String bodyHtml = "<b><i>crossed</b> marker</i>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_AND_UNBALANCED_HTML_implicitListItems() {
        String bodyHtml = "<ul><li>one<li>two</ul>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_AND_UNBALANCED_HTML_openAnchor() {
        String bodyHtml = "<a href=\"https://safe.example/\">link marker";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMENTS_DOCTYPE_AND_PROCESSING_LIKE_TEXT_comment() {
        String bodyHtml = "before<!-- comment marker -->after";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMENTS_DOCTYPE_AND_PROCESSING_LIKE_TEXT_doctype() {
        String bodyHtml = "<!doctype html><p>doctype marker</p>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMENTS_DOCTYPE_AND_PROCESSING_LIKE_TEXT_processingLikeSyntax() {
        String bodyHtml = "<?pi value?><p>processing marker</p>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENTITY_AND_UNICODE_SERIALIZATION_namedEntities() {
        String bodyHtml = "&lt;tag&gt; &amp; marker";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENTITY_AND_UNICODE_SERIALIZATION_unicodeAndNumericEntity() {
        String bodyHtml = "&#x1F600; café marker";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENTITY_AND_UNICODE_SERIALIZATION_attributeEntity() {
        String bodyHtml = "<p title=\"&quot;quoted&amp;marked\">entity marker</p>";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOCUMENT_LIKE_BODY_FRAGMENT_htmlHeadAndBody() {
        String bodyHtml = "<html><head><title>title marker</title></head><body><p>body marker</p></body></html>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOCUMENT_LIKE_BODY_FRAGMENT_headWithBase() {
        String bodyHtml = "<head><base href=\"https://other.example/\"></head><p>base marker</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOCUMENT_LIKE_BODY_FRAGMENT_bodyWithScript() {
        String bodyHtml = "<body><script>alert(1)</script><p>body marker</p></body>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_AND_EMPTY_FRAGMENT_empty() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_AND_EMPTY_FRAGMENT_surroundingWhitespace() {
        String bodyHtml = "  \n\t<p>space marker</p>  \r\n";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_AND_DEEP_FRAGMENT_largeText() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1024; i++) {
            builder.append('x');
        }
        String bodyHtml = builder.toString();
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_AND_DEEP_FRAGMENT_deepDivNesting() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            builder.append("<div>");
        }
        builder.append("depth marker");
        for (int i = 0; i < 32; i++) {
            builder.append("</div>");
        }
        String bodyHtml = builder.toString();
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
