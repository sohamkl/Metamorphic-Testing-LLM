import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source(
            String bodyHtml, String baseUri, Safelist safelist) {
        return new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(bodyHtml, baseUri, safelist);
    }

    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input generateFollowUp(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input original, String cleanedHtml) {
        return source(cleanedHtml, original.arg1(), original.arg2());
    }

    private static void assertMetamorphicRelation(
            String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input original) {
        String firstOutput =
                MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(original);
        String followUpOutput =
                MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(
                        generateFollowUp(original, firstOutput));
        assertMetamorphicRelation(firstOutput, followUpOutput);
    }

    @Test
    public void EMPTY_FRAGMENT_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("", "", Safelist.none());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source(" \t\n  \r\n", "", Safelist.none());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void PLAIN_TEXT_FRAGMENT_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("Plain text, punctuation: one & two.", "", Safelist.none());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void ESCAPED_ENTITY_TEXT_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("5 &lt; 6 &amp; 7 &gt; 3", "urn:example:base", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void NEW_EMPTY_SAFELIST_MARKUP_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p>retained text <script>alert(1)</script></p>",
                        "http://example.test/page", new Safelist());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void BASIC_ALLOWED_AND_DISALLOWED_TAGS_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p><strong>safe</strong> <iframe>unsafe</iframe></p>",
                        "https://example.test/page", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void BASIC_ATTRIBUTE_FILTERING_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p class=\"kept\" style=\"removed\" title=\"removed\">text</p>",
                        "https://example.test/page", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void BASIC_RELATIVE_URL_WITH_BASE_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<!-- link --><a href=\"../docs/page.html\">documentation</a>",
                        "https://example.test/path/page.html", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void EMPTY_BASE_RELATIVE_URL_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source(" \n<a href=\"relative/page.html\">relative link</a>",
                        "", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void DISALLOWED_URL_PROTOCOL_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<a href=\"javascript:alert(1)\">unsafe link</a>",
                        "http://example.test/", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void BASIC_WITH_IMAGES_POLICY_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("before<img src=\"/images/icon.png\" alt=\"icon\">after",
                        "https://example.test/assets/page.html",
                        Safelist.basicWithImages());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void RELAXED_NESTED_MARKUP_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<div id=\"outer\"><p class=\"copy\">Outer <em data-x=\"1\"><strong>inner</strong></em></p></div>",
                        "urn:example:document", Safelist.relaxed());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void SIMPLE_TEXT_MARKUP_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<b title=\"discard\">bold</b><span class=\"discard\"> plain</span><i>italic</i>",
                        "", Safelist.simpleText());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void COPIED_SAFELIST_variation1() {
        Safelist copied = new Safelist(Safelist.relaxed());
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p class=\"kept\" onclick=\"discard\">See <a href=\"/guide\">guide</a></p>",
                        "http://example.test/root/index.html", copied);
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void DISALLOWED_TAG_CONTENT_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("outside<script>hidden script text</script><p>visible text</p>",
                        "", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void MALFORMED_UNBALANCED_FRAGMENT_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p>first <em>nested <strong>text</p> after",
                        "urn:example:fragment", Safelist.relaxed());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void MIXED_CASE_AND_DUPLICATE_ATTRIBUTES_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<P CLASS=\"one\" class=\"two\" OnClick=\"bad\">Mixed</P>",
                        "https://example.test/", Safelist.relaxed());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void COMMENTS_AND_DECLARATION_CONTENT_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("before<!-- comment --><![CDATA[ignored]]>after",
                        "", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void ONLY_REMOVABLE_MARKUP_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<script>remove me</script><style>also remove</style>",
                        "", Safelist.none());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void NONEMPTY_NONHTTP_BASE_URI_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<a href=\"chapter.html\">chapter</a><a href=\"https://example.test\">site</a>",
                        "urn:example:base", Safelist.relaxed());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void MULTI_FEATURE_FRAGMENT_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p class=\"kept\" onclick=\"discard\"><strong>5 &lt; 6</strong> "
                                + "<a href=\"relative/page\">relative</a> "
                                + "<a href=\"https://example.test/absolute\">absolute</a> "
                                + "<blink>unlisted</blink></p>",
                        "https://example.test/root/index.html", Safelist.relaxed());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void FACTORY_POLICY_COMPARISON_variation1() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p class=\"x\">text <img src=\"/x.png\" alt=\"x\"><a href=\"/next\">next</a></p>",
                        "http://example.test/page", Safelist.basic());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void FACTORY_POLICY_COMPARISON_variation2() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p style=\"bad\">text <img src=\"https://example.test/x.png\" width=\"10\">"
                                + "<u>underlined</u></p>",
                        "https://example.test/page", Safelist.basicWithImages());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void FACTORY_POLICY_COMPARISON_variation3() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<div><p>nested <em data-extra=\"remove\">content</em></p>"
                                + "<img src=\"/image.png\" alt=\"image\"><a href=\"/more\">more</a></div>",
                        "urn:example:comparison", Safelist.relaxed());
        assertMetamorphicRelationFor(input);
    }

    @Test
    public void FACTORY_POLICY_COMPARISON_variation4() {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input =
                source("<p>text <img src=\"/x.png\"><script>bad</script>",
                        "", Safelist.simpleText());
        assertMetamorphicRelationFor(input);
    }
}
