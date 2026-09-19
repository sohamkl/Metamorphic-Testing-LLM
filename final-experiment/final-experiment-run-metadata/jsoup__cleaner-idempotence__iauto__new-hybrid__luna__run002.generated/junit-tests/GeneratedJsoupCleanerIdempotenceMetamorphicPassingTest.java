import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning must be idempotent: expected <" + sourceOutput
                            + "> but second cleaning produced <" + followUpOutput + ">");
        }
    }

    private static void verify(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input followUp =
                new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(
                        (String) CleanerIdempotenceMetamorphicSpec
                                .generateFollowUp(bodyHtml, baseUri, safelist)[0],
                        (String) CleanerIdempotenceMetamorphicSpec
                                .generateFollowUp(bodyHtml, baseUri, safelist)[1],
                        (Safelist) CleanerIdempotenceMetamorphicSpec
                                .generateFollowUp(bodyHtml, baseUri, safelist)[2]);
        String followUpOutput = Jsoup.clean(
                followUp.arg0(), followUp.arg1(), followUp.arg2());
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_FRAGMENT_WITH_NONE_POLICY_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void EMPTY_FRAGMENT_WITH_RELAXED_POLICY_variation1() {
        String bodyHtml = "";
        String baseUri = "https://example.com/page";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void PLAIN_TEXT_NONE_POLICY_variation1() {
        String bodyHtml = "5 is &lt; 6 &amp; 7 &gt; 3";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void PLAIN_TEXT_SIMPLE_TEXT_POLICY_variation1() {
        String bodyHtml = "ordinary readable text";
        String baseUri = "http://example.com/articles/index.html";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void ALLOWED_ELEMENTS_BASIC_POLICY_variation1() {
        String bodyHtml = "<p>Welcome <b>reader</b></p>";
        String baseUri = "https://example.com/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void ALLOWED_ELEMENTS_RELAXED_POLICY_variation1() {
        String bodyHtml = "<div><p><strong>Nested</strong> content</p></div>";
        String baseUri = "http://example.com/content";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void IMAGE_ELEMENTS_BASIC_WITH_IMAGES_variation1() {
        String bodyHtml = "before <img src=\"https://example.com/image.png\" alt=\"picture\"> after";
        String baseUri = "https://example.com/gallery/";
        Safelist safelist = new Safelist(Safelist.basicWithImages());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void DISALLOWED_ELEMENT_WITH_TEXT_variation1() {
        String bodyHtml = "Keep <script>alert('x')</script> this text";
        String baseUri = "https://example.com/path";
        Safelist safelist = new Safelist();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void UNSAFE_ONLY_ELEMENT_variation1() {
        String bodyHtml = "<script>alert('unsafe')</script>";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void ALLOWED_ATTRIBUTE_RETENTION_variation1() {
        String bodyHtml = "<a href=\"https://example.com\" title=\"Example\">link</a>";
        String baseUri = "http://example.org/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void DISALLOWED_ATTRIBUTE_REMOVAL_variation1() {
        String bodyHtml = "<p class=\"unknown\" data-secret=\"value\">visible text</p>";
        String baseUri = "https://example.com/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void ALLOWED_HTTP_URL_variation1() {
        String bodyHtml = "<a href=\"http://example.com/resource\">HTTP link</a>";
        String baseUri = "https://example.com/base/";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void ALLOWED_HTTPS_URL_variation1() {
        String bodyHtml = "<a href=\"https://secure.example.com/login\">Secure link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void UNSAFE_URL_PROTOCOL_variation1() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">unsafe link</a>";
        String baseUri = "http://example.com/";
        Safelist safelist = new Safelist(Safelist.basic());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void RELATIVE_URL_WITH_NONEMPTY_BASE_variation1() {
        String bodyHtml = "<a href=\"../docs/guide.html\">Guide</a>";
        String baseUri = "https://example.com/articles/current/page.html";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void RELATIVE_URL_WITH_EMPTY_BASE_AND_RELATIVE_PRESERVATION_variation1() {
        String bodyHtml = "<a href=\"docs/guide.html\">Guide</a>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void RELATIVE_URL_WITH_EMPTY_BASE_AND_DEFAULT_POLICY_variation1() {
        String bodyHtml = "<a href=\"docs/guide.html\">Guide</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void BASE_URI_PATH_RESOLUTION_variation1() {
        String bodyHtml = "<a href=\"images/icons/home.png\">home</a>";
        String baseUri = "https://example.com/site/articles/index.html";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void NESTED_ALLOWED_AND_DISALLOWED_ELEMENTS_variation1() {
        String bodyHtml = "<p>Outer <span>inner</span> <script>bad</script></p>";
        String baseUri = "https://example.com/";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void MALFORMED_UNBALANCED_FRAGMENT_variation1() {
        String bodyHtml = "<p><b>Unbalanced <i>fragment";
        String baseUri = "http://example.com/content/page";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void COMMENTS_AND_TEXT_variation1() {
        String bodyHtml = "before<!-- hidden comment -->after";
        String baseUri = "";
        Safelist safelist = new Safelist(Safelist.simpleText());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void ENTITY_ESCAPING_AFTER_TAG_REMOVAL_variation1() {
        String bodyHtml = "<em>Tom &amp; Jerry</em> &#169; 5 &lt; 7";
        String baseUri = "http://example.com/";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void NEW_EMPTY_SAFELIST_variation1() {
        String bodyHtml = "visible <b class=\"unsafe\">text</b>";
        String baseUri = "https://example.com/";
        Safelist safelist = new Safelist();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void COPIED_SAFELIST_POLICY_variation1() {
        String bodyHtml = "<a href=\"https://example.com\">copied policy</a>";
        String baseUri = "http://example.com/articles/index.html";
        Safelist safelist = new Safelist(Safelist.simpleText());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void MULTIPLE_URLS_AND_ATTRIBUTES_variation1() {
        String bodyHtml =
                "Links: <a href=\"relative/page.html\" title=\"one\">one</a> "
                        + "<a href=\"https://example.com/two\">two</a> "
                        + "<img src=\"javascript:bad()\" alt=\"bad\">";
        String baseUri = "https://example.com/root/index.html";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void UNICODE_WHITESPACE_AND_TEXT_variation1() {
        String bodyHtml = "  Καλημέρα\u00a0世界 \n\t φίλοι  ";
        String baseUri = "http://example.com/";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void MIXED_SAFE_UNSAFE_FRAGMENT_variation1() {
        String bodyHtml =
                "Start <p title=\"ok\" data-bad=\"x\">safe</p> "
                        + "<script>bad()</script> "
                        + "<a href=\"javascript:bad()\">link</a>";
        String baseUri = "https://example.com/";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    void REPEATED_MARKUP_NORMALIZATION_variation1() {
        String bodyHtml = "<p><b>one</b></p><p><b>two</b></p>";
        String baseUri = "http://example.com/articles/index.html";
        Safelist safelist = new Safelist(Safelist.basic());
        verify(bodyHtml, baseUri, safelist);
    }
}
