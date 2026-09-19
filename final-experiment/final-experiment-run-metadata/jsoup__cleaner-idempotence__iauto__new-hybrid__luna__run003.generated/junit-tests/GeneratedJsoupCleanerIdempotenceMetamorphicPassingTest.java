import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError("Cleaning is not idempotent: <" + sourceOutput
                    + "> != <" + followUpOutput + ">");
        }
    }

    @Test
    public void EMPTY_BODY_NONE_1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_PRESERVED_1() {
        String bodyHtml = "A plain sentence with no markup.";
        String baseUri = "http://example.test/page";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENTITY_ESCAPING_TEXT_ONLY_1() {
        String bodyHtml = "<p>5 is &lt; 6.</p>";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ALLOWED_MARKUP_1() {
        String bodyHtml = "<p>Welcome <b>reader</b>.</p>";
        String baseUri = "https://example.test/page";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_ELEMENT_CONTENT_RETENTION_1() {
        String bodyHtml = "<custom>Visible text</custom>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_ELEMENT_WITH_NESTED_ALLOWED_CONTENT_1() {
        String bodyHtml = "<custom><p>Nested permitted text</p></custom>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_ATTRIBUTE_REMOVAL_1() {
        String bodyHtml = "<p class=\"kept\" data-secret=\"removed\">Readable text</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_SAFE_ATTRIBUTE_RETENTION_1() {
        String bodyHtml = "<a href=\"https://example.test/docs\">Documentation</a>";
        String baseUri = "https://example.test/docs/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_RETains_TEXT_DROPS_MARKUP_1() {
        String bodyHtml = "<div><em>Keep this text</em> and this too.</div>";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_MARKUP_FILTERING_1() {
        String bodyHtml = "Intro <b>bold</b> <script>alert(1)</script> outro";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_IMAGE_ELEMENT_1() {
        String bodyHtml = "Logo: <img src=\"https://example.test/assets/logo.png\" alt=\"Logo\">";
        String baseUri = "https://example.test/assets/";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_RICH_MARKUP_1() {
        String bodyHtml = "<section><h1 title=\"Heading\">Title</h1><p>Body <a href=\"/next\">link</a></p></section>";
        String baseUri = "https://example.test/article";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_ALLOWED_URL_1() {
        String bodyHtml = "<a href=\"https://safe.example/path\">Safe link</a>";
        String baseUri = "https://example.test/base/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_URL_PROTOCOL_1() {
        String bodyHtml = "<a href=\"javascript:alert('x')\">Unsafe link</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_URL_PROTOCOL_2() {
        String bodyHtml = "<img src=\"javascript:alert(1)\" alt=\"warning\">";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_URL_WITH_NONEMPTY_BASE_1() {
        String bodyHtml = "<a href=\"../page\">Relative page</a>";
        String baseUri = "https://example.test/articles/current/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_WITH_RELATIVE_LINK_PRESERVATION_1() {
        String bodyHtml = "<a href=\"relative/page\">Relative page</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_WITHOUT_RELATIVE_LINK_PRESERVATION_1() {
        String bodyHtml = "<a href=\"relative/page\">Relative page</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_NESTING_NORMALIZATION_1() {
        String bodyHtml = "<p><b>Unbalanced</p></b>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_NESTING_NORMALIZATION_2() {
        String bodyHtml = "<div><p>First <em>second</div> tail";
        String baseUri = "https://example.test/content/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DUPLICATE_ATTRIBUTES_1() {
        String bodyHtml = "<p title=\"first\" title=\"second\">Duplicate attributes</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_AND_UNICODE_TEXT_1() {
        String bodyHtml = "Café  κόσμος   —  naïve";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_SAFE_UNSAFE_FRAGMENT_2() {
        String bodyHtml = "Before <strong>after</strong><img src=\"images/pic.png\" data-x=\"drop\"> end";
        String baseUri = "https://example.test/content/";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPY_SAFELIST_POLICY_1() {
        Safelist original = Safelist.relaxed();
        Safelist safelist = new Safelist(original);
        String bodyHtml = "<p class=\"copied\">Copied <a href=\"/destination\">policy</a></p>";
        String baseUri = "https://example.test/";

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = jsoupmt.CleanerIdempotenceMetamorphicSpec
                .generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
