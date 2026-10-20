import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    @Test
    public void EMPTY_BODY_variation1_emptyBaseAndConstructedSafelist() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = new Safelist();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_ASCII_TEXT_variation1_plainTextWithNone() {
        String bodyHtml = "Ordinary plain ASCII text";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_ONLY_BODY_variation1_singleLineSpacesAndTabs() {
        String bodyHtml = " \t   \t ";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_ONLY_BODY_variation2_multilineWhitespace() {
        String bodyHtml = "\r\n \t\n\r\n";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CHARACTER_REFERENCE_NORMALIZATION_variation1_namedReferences() {
        String bodyHtml = "Tom &amp; Jane: &lt;safe&gt;";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CHARACTER_REFERENCE_NORMALIZATION_variation2_numericReferences() {
        String bodyHtml = "Decimal: &#169; Hexadecimal: &#x1F642;";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNICODE_TEXT_variation1_bmpAccentedAndNonLatinText() {
        String bodyHtml = "Café Ελληνικά 東京";
        String baseUri = "https://example.test/root/page";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNICODE_TEXT_variation2_supplementaryCharacter() {
        String bodyHtml = "Rocket \uD83D\uDE80 remains visible";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMBEDDED_NUL_TEXT_variation1_textSurroundingNul() {
        String bodyHtml = "prefix" + (char) 0 + "suffix";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_STRIPS_ELEMENTS_variation1_paragraphElement() {
        String bodyHtml = "<p>alpha</p>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_STRIPS_ELEMENTS_variation2_nestedDivAndBold() {
        String bodyHtml = "<div><b>beta</b></div>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_ESCAPES_LITERAL_TEXT_variation1_comparisonsAndAmpersand() {
        String bodyHtml = "<p>5 is &lt; 6 &amp; 7 is &gt; 3.</p>";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CONSTRUCTED_SAFELIST_variation1_nestedMarkupWithText() {
        String bodyHtml = "<section><p>constructed <b>safelist</b> text</p></section>";
        String baseUri = "";
        Safelist safelist = new Safelist();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPY_CONSTRUCTED_SAFELIST_variation1_copyOfNone() {
        String bodyHtml = "<article><em>copied policy text</em></article>";
        String baseUri = "http://example.test/";
        Safelist safelist = new Safelist(Safelist.none());

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_ALLOWED_FORMATTING_variation1_nestedFormattingOrderOne() {
        String bodyHtml = "<b>bold <em>em <i>italic <strong>strong <u>underlined</u></strong></i></em></b>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_ALLOWED_FORMATTING_variation2_nestedFormattingOrderTwo() {
        String bodyHtml = "<u>underline <strong>strong <i>italic <em>em <b>bold</b></em></i></strong></u>";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_BLOCK_ELEMENT_variation1_paragraphAroundStrong() {
        String bodyHtml = "<p>outer <strong>important</strong> tail</p>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_TEXT_STRUCTURE_variation1_paragraphListAndEmphasis() {
        String bodyHtml = "<p>Intro <em>emphasis</em></p><ul><li>First</li><li><strong>Second</strong></li></ul>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_REMOVES_IMAGE_ELEMENT_variation1_httpsImageOnly() {
        String bodyHtml = "<img src=\"https://images.example.test/picture.png\" alt=\"picture\">";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_RETAINS_IMAGE_variation1_httpsSourceAndAlt() {
        String bodyHtml = "<img src=\"https://images.example.test/safe.png\" alt=\"safe image\">";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_RICH_STRUCTURE_variation1_headingDivAndParagraph() {
        String bodyHtml = "<h2>Heading</h2><div><p>Rich paragraph content</p></div>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_RICH_STRUCTURE_variation2_tableRowsAndCells() {
        String bodyHtml = "<table><tbody><tr><th>Name</th><td>Alice</td></tr><tr><th>Role</th><td>Editor</td></tr></tbody></table>";
        String baseUri = "https://example.test/root/page";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HTML_COMMENT_REMOVAL_variation1_commentBetweenTextNodes() {
        String bodyHtml = "before<!-- hidden comment -->after";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SCRIPT_AND_STYLE_FILTERING_variation1_scriptBeforeSafeParagraph() {
        String bodyHtml = "<script>alert('unsafe')</script><p>safe paragraph after script</p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SCRIPT_AND_STYLE_FILTERING_variation2_styleBeforeSafeParagraph() {
        String bodyHtml = "<style>body { display: none; }</style><p>safe paragraph after style</p>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_variation1_onclickOnParagraph() {
        String bodyHtml = "<p onclick=\"alert(1)\">click-resistant content</p>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNKNOWN_ATTRIBUTE_variation1_dataAndClassOnParagraph() {
        String bodyHtml = "<p data-unknown=\"x\" class=\"c\">attribute-filtered text</p>";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_ALLOWED_LINK_PROTOCOLS_variation1_absoluteHttpLink() {
        String bodyHtml = "<a href=\"http://links.example.test/page\">HTTP link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_ALLOWED_LINK_PROTOCOLS_variation2_absoluteHttpsLink() {
        String bodyHtml = "<a href=\"https://secure.example.test/page?q=1\">HTTPS link</a>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_LINK_PROTOCOLS_variation1_javascriptLink() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">unsafe JavaScript link</a>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_LINK_PROTOCOLS_variation2_dataAndFileLinks() {
        String bodyHtml = "<a href=\"data:text/html,unsafe\">data link</a>"
                + "<a href=\"file:///tmp/private.txt\">file link</a>";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OBFUSCATED_UNSAFE_PROTOCOL_variation1_mixedCaseJavascript() {
        String bodyHtml = "<a href=\"JaVaScRiPt:alert(1)\">mixed-case scheme</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OBFUSCATED_UNSAFE_PROTOCOL_variation2_characterReferenceInScheme() {
        String bodyHtml = "<a href=\"java&#x73;cript:alert(1)\">encoded scheme</a>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITH_BASE_URI_variation1_childPathAgainstHttpsDirectory() {
        String bodyHtml = "<a href=\"child/page\">child page</a>";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITH_BASE_URI_variation2_rootRelativeAgainstHttpPath() {
        String bodyHtml = "<a href=\"/rooted\">root-relative page</a>";
        String baseUri = "http://example.test/a/b";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITHOUT_USABLE_BASE_variation1_emptyBaseUri() {
        String bodyHtml = "<a href=\"relative/path\">relative without base</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITHOUT_USABLE_BASE_variation2_unusualBaseUri() {
        String bodyHtml = "<a href=\"relative/path\">relative with unusable base</a>";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRAGMENT_AND_QUERY_REFERENCES_variation1_fragmentReference() {
        String bodyHtml = "<a href=\"#section\">section link</a>";
        String baseUri = "https://example.test/path/document";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRAGMENT_AND_QUERY_REFERENCES_variation2_queryReference() {
        String bodyHtml = "<a href=\"?page=2\">next query page</a>";
        String baseUri = "https://example.test/path/document";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_variation1_httpsNetworkPath() {
        String bodyHtml = "<a href=\"//cdn.example.test/resource\">CDN resource</a>";
        String baseUri = "https://origin.example.test/application/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_NON_HTTP_LINK_PROTOCOLS_variation1_mailtoLink() {
        String bodyHtml = "<a href=\"mailto:person@example.test\">send mail</a>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_NON_HTTP_LINK_PROTOCOLS_variation2_ftpLink() {
        String bodyHtml = "<a href=\"ftp://files.example.test/pub/archive.zip\">FTP archive</a>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_PROTOCOL_FILTERING_variation1_allowedHttpsImageSource() {
        String bodyHtml = "<img src=\"https://images.example.test/photo.jpg\" alt=\"allowed photo\">";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_PROTOCOL_FILTERING_variation2_javascriptImageSource() {
        String bodyHtml = "<img src=\"javascript:alert(1)\" alt=\"unsafe source image\">";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INPUT_BASE_ELEMENT_RESOLUTION_variation1_declaredBaseBeforeRelativeAnchor() {
        String bodyHtml = "<base href=\"https://example.test/base/\"><a href=\"child\">based child</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MISNESTED_MARKUP_variation1_crossingInlineTags() {
        String bodyHtml = "<b><i>text</b>tail</i>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNCLOSED_MARKUP_variation1_nestedUnclosedBasicElements() {
        String bodyHtml = "<p>opening paragraph <strong>unclosed emphasis";
        String baseUri = "not a URI";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DUPLICATE_ATTRIBUTES_variation1_twoAbsoluteHrefAttributes() {
        String bodyHtml = "<a href=\"https://first.example.test/path\" href=\"http://second.example.test/other\">duplicate href link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOCUMENT_TOKENS_AS_BODY_FRAGMENT_variation1_completeDocumentLikeInput() {
        String bodyHtml = "<!doctype html><html><head><title>Distinct title text</title></head>"
                + "<body><h1>Body heading</h1><p>Distinct safe body text</p></body></html>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
