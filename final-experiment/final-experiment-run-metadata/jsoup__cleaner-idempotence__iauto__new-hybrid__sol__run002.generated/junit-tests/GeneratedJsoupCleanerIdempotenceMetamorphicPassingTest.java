import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning the follow-up HTML must produce exactly the source output: expected <"
                            + sourceOutput + "> but was <" + followUpOutput + ">");
        }
    }

    @Test
    public void EMPTY_BODY_NONE_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_ONLY_BODY_variation1() {
        String bodyHtml = " \t\n\r\f ";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_ASCII_TEXT_variation1() {
        String bodyHtml = "Ordinary plain ASCII text.";
        String baseUri = "https://example.test/base/";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNICODE_TEXT_variation1() {
        String bodyHtml = "Café 東京 😀 Привет";
        String baseUri = "relative/base/path";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENTITY_BEARING_TEXT_variation1() {
        String bodyHtml = "Five is &lt; six &amp; seven is &gt; three.";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void AMBIGUOUS_AMPERSAND_TEXT_variation1() {
        String bodyHtml = "Rock & roll and incomplete &notAnEntity";
        String baseUri = "http://example.test/root/";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_REMOVES_MARKUP_variation1() {
        String bodyHtml = "<section><strong>Visible text</strong></section>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_INLINE_MARKUP_variation1() {
        String bodyHtml = "<b>bold <i>italic <u>underlined</u></i></b><span> tail</span>";
        String baseUri = "local/path";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ANCHOR_WITH_TEXT_variation1() {
        String bodyHtml = "<a href=\"https://example.test/page\">link text</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_BLOCK_STRUCTURE_variation1() {
        String bodyHtml = "<p>First</p><ul><li>One <em>item</em></li><li>Second</li></ul>";
        String baseUri = "http://example.test/articles/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_POLICY_ALLOWS_IMAGE_variation1() {
        String bodyHtml = "<img src=\"https://images.example.test/picture.png\" alt=\"A picture\">";
        String baseUri = "https://example.test/gallery/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_POLICY_REJECTS_IMAGE_ELEMENT_variation1() {
        String bodyHtml = "<img src=\"https://example.test/only.png\" alt=\"only image\">";
        String baseUri = "relative/path";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_CONTAINER_RETAINS_TEXT_variation1() {
        String bodyHtml = "<custom-container>ordinary descendant text</custom-container>";
        String baseUri = "";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_variation1() {
        String bodyHtml = "<strong onclick=\"alert('x')\">safe visible text</strong>";
        String baseUri = "http://example.test/";
        Safelist safelist = new Safelist(Safelist.basic());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNKNOWN_ATTRIBUTE_ON_ALLOWED_ELEMENT_variation1() {
        String bodyHtml = "<p mystery=\"unknown\">paragraph text</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HTTP_ABSOLUTE_LINK_variation1() {
        String bodyHtml = "<a href=\"http://example.test/p\">x</a>";
        String baseUri = "local/base";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HTTPS_ABSOLUTE_LINK_variation1() {
        String bodyHtml = "<a href=\"https://example.test/p\">x</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MAILTO_LINK_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"mailto:user@example.test\">send mail</a>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAVASCRIPT_LINK_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">clickable text</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATA_IMAGE_PROTOCOL_variation1() {
        String bodyHtml = "<img src=\"data:image/png;base64,AAAA\" alt=\"embedded image\">";
        String baseUri = "path/like/base";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITH_ABSOLUTE_BASE_variation1() {
        String bodyHtml = "<a href=\"../next?q=1\">next page</a>";
        String baseUri = "https://example.test/dir/current/";
        Safelist safelist = new Safelist(Safelist.basic()).preserveRelativeLinks(false);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_EMPTY_BASE_DEFAULT_variation1() {
        String bodyHtml = "<a href=\"docs/start.html\">documentation</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_EMPTY_BASE_PRESERVED_variation1() {
        String bodyHtml = "<a href=\"docs/start.html\">preserved documentation</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_NONEMPTY_BASE_PRESERVED_variation1() {
        String bodyHtml = "<a href=\"assets/page.html\">relative page</a>";
        String baseUri = "https://example.test/root/index.html";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRAGMENT_ONLY_LINK_variation1() {
        String bodyHtml = "<a href=\"#section\">section link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_variation1() {
        String bodyHtml = "<a href=\"//cdn.example.test/library\">CDN resource</a>";
        String baseUri = "https://example.test/application/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_CASE_UNSAFE_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"JaVaScRiPt:alert(1)\">mixed case link</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENCODED_UNSAFE_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"java&#x73;cript:alert(1)\">encoded protocol</a>";
        String baseUri = "path/base";
        Safelist safelist = new Safelist(Safelist.basic());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HTTPS_IMAGE_SOURCE_variation1() {
        String bodyHtml = "<img src=\"https://static.example.test/photo.jpg\" alt=\"Mountain view\">";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_WITHOUT_SOURCE_variation1() {
        String bodyHtml = "<img alt=\"Missing source\">";
        String baseUri = "http://example.test/images/";
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POLICY_ENFORCED_ATTRIBUTES_variation1() {
        String bodyHtml = "<a href=\"https://example.test/safe\">safe destination</a>";
        String baseUri = "https://example.test/base/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEEPLY_NESTED_ALLOWED_CONTENT_variation1() {
        String bodyHtml = "<div><blockquote><p><strong><em>deep text</em></strong></p></blockquote></div>";
        String baseUri = "nested/path";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_OUTER_ALLOWED_INNER_variation1() {
        String bodyHtml = "<unsupported-wrapper><p>allowed inner paragraph</p></unsupported-wrapper>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_CHILD_INSIDE_ALLOWED_PARENT_variation1() {
        String bodyHtml = "<p>before<iframe src=\"https://evil.example.test/\">frame text</iframe>after</p>";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNCLOSED_START_TAGS_variation1() {
        String bodyHtml = "<div><p>unclosed <strong>content";
        String baseUri = "https://example.test/path/";
        Safelist safelist = new Safelist(Safelist.relaxed());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MISNESTED_FORMATTING_TAGS_variation1() {
        String bodyHtml = "<b>bold <i>italic</b> continuation</i>";
        String baseUri = "local/path";
        Safelist safelist = Safelist.simpleText().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DUPLICATE_ATTRIBUTES_variation1() {
        String bodyHtml = "<a href=\"https://first.example.test/\" href=\"https://second.example.test/\">duplicate href</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HTML_COMMENTS_variation1() {
        String bodyHtml = "before<!-- hidden comment -->after";
        String baseUri = "http://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SCRIPT_RAW_DATA_variation1() {
        String bodyHtml = "before<script>if (x < 2) alert('unsafe');</script>after";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STYLE_RAW_DATA_variation1() {
        String bodyHtml = "left<style>body { background: url(javascript:bad) }</style>right";
        String baseUri = "relative/base";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOCTYPE_ONLY_FRAGMENT_variation1() {
        String bodyHtml = "<!DOCTYPE html>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FULL_DOCUMENT_AS_BODY_FRAGMENT_variation1() {
        String bodyHtml = "<html><head><title>Ignored title</title></head><body><p>body text</p></body></html>";
        String baseUri = "http://example.test/document/";
        Safelist safelist = new Safelist(Safelist.relaxed()).preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASE_ELEMENT_IN_FRAGMENT_variation1() {
        String bodyHtml = "<base href=\"https://cdn.example.test/root/\"><a href=\"next.html\">next</a>";
        String baseUri = "https://origin.example.test/start/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TABLE_PARSER_NORMALIZATION_variation1() {
        String bodyHtml = "<table><tr><td>cell one<td>cell two</tr></table>";
        String baseUri = "table/base/path";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void VOID_ELEMENT_SERIALIZATION_variation1() {
        String bodyHtml = "first<br>second";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_CASE_TAG_AND_ATTRIBUTE_NAMES_variation1() {
        String bodyHtml = "<A HREF=\"HTTP://example.test/Path\">Mixed Case</A>";
        String baseUri = "http://example.test/base/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ATTRIBUTE_QUOTING_AND_ESCAPING_variation1() {
        String bodyHtml = "<a href=\"https://example.test/search?a=1&amp;b=&quot;two&quot;\">search link</a>";
        String baseUri = "https://example.test/base/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTROL_CHARACTER_BETWEEN_TEXT_variation1() {
        String bodyHtml = "before\u0000\u0007after";
        String baseUri = "control/base/path";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPY_CONSTRUCTED_SAFELIST_variation1() {
        String bodyHtml = "<p onclick=\"bad()\">copied <a href=\"https://example.test/\">policy</a></p>";
        String baseUri = "";
        Safelist safelist = new Safelist(Safelist.relaxed());
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CONSTRUCTED_SAFELIST_variation1() {
        String bodyHtml = "<article><strong>visible ordinary text</strong></article>";
        String baseUri = "http://example.test/";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
