import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    @Test
    public void EMPTY_FRAGMENT_NONE_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_NONE_variation1() {
        String bodyHtml = "5 is &lt; 6 &amp; 7.";
        String baseUri = "http://example.test/base/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_TAG_CONTENT_RETENTION_variation1() {
        String bodyHtml = "<p>Visible text</p><script>alert('x')</script>outside";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_MARKUP_variation1() {
        String bodyHtml = "<span class=\"label\"><b>Bold</b> and <i>italic</i></span><script>bad()</script>";
        String baseUri = "/documents/page.html";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ALLOWED_AND_UNSAFE_ELEMENTS_variation1() {
        String bodyHtml = "<div><p>Read <a href=\"javascript:alert(1)\">this link</a></p><iframe src=\"evil\"></iframe></div>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_IMAGE_POLICY_variation1() {
        String bodyHtml = "<p>Start<img src=\"images/logo.png\" alt=\"Logo\"><a href=\"../home\">Home</a></p>";
        String baseUri = "https://example.test/articles/page.html";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_MIXED_MARKUP_variation1() {
        String bodyHtml = "<div data-unknown=\"remove\"><h2>Title</h2><p>Text <a href=\"../next\">next</a></p><table><tr><td>Cell</td></tr></table><script>bad()</script>&amp;</div>";
        String baseUri = "https://example.test/content/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CONSTRUCTOR_POLICY_variation1() {
        String bodyHtml = "<p class=\"notice\">Some text</p>";
        String baseUri = "";
        Safelist safelist = new Safelist();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPIED_RELAXED_POLICY_variation1() {
        String bodyHtml = "<section><p class=\"copy\">Copied <em>content</em></p><object data=\"bad\"></object></section>";
        String baseUri = "https://example.test/copy/";
        Safelist safelist = new Safelist(Safelist.relaxed());

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_RELATIVE_URL_variation1() {
        String bodyHtml = "<a href=\"relative/page.html\">Relative link</a><img src=\"images/pic.png\" alt=\"Picture\">";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_HTTP_URL_variation1() {
        String bodyHtml = "<p><a href=\"http://example.test/articles/item\">HTTP item</a></p>";
        String baseUri = "http://example.test/base/page.html";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_HTTPS_URL_variation1() {
        String bodyHtml = "<p><a href=\"https://secure.example.test/account\">Secure</a><img src=\"https://cdn.example.test/logo.png\" alt=\"Logo\"></p>";
        String baseUri = "https://example.test/base/page.html";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_URL_PROTOCOL_variation1() {
        String bodyHtml = "<p><a href=\"javascript:alert(1)\">Unsafe link</a><a href=\"mailto:user@example.test\">Mail</a></p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNKNOWN_ATTRIBUTE_REMOVAL_variation1() {
        String bodyHtml = "<p title=\"kept?\" data-secret=\"remove\" onclick=\"bad()\">Text</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENTITY_AND_COMMENT_NORMALIZATION_variation1() {
        String bodyHtml = "A &copy; &#169; <b>5 &lt; 6</b> <!-- hidden --> text &lt;literal&gt;";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_variation1() {
        String bodyHtml = " \t \n  \r\n ";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTIPLE_SIBLING_ELEMENTS_variation1() {
        String bodyHtml = "<p>First</p> between <strong>second</strong> and <a href=\"/third\">third</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONEMPTY_RELATIVE_LOOKING_BASE_variation1() {
        String bodyHtml = "<p><a href=\"../guide/start.html\">Guide</a> and <a href=\"/root\">Root</a></p>";
        String baseUri = "/documents/page.html";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEXT_ONLY_OUTPUT_PATH_variation1() {
        String bodyHtml = "<script>hidden</script><iframe>frame text</iframe><object>object text</object><div>visible text</div>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
