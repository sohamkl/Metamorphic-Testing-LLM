import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static Object[] generateFollowUp(String bodyHtml, String baseUri, Safelist safelist) {
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        return new Object[]{cleanedHtml, baseUri, safelist};
    }

    @Test
    public void EMPTY_FRAGMENT_WITH_NONE_POLICY_1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_WITH_NONE_POLICY_1() {
        String bodyHtml = "Plain text retained safely.";
        String baseUri = "http://example.com/page";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ESCAPED_TEXT_WITH_NONE_POLICY_1() {
        String bodyHtml = "5 &lt; 6 &amp;&amp; 7 &gt; 3";
        String baseUri = "https://example.com/source";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAG_ONLY_FRAGMENT_WITH_NONE_POLICY_1() {
        String bodyHtml = "<p></p><div><span></span></div>";
        String baseUri = "relative/page";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ALLOWED_TEXT_FORMATTING_1() {
        String bodyHtml = "<p><strong>Important</strong> <em>formatted</em> <a>text</a></p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_DISALLOWED_SCRIPT_WITH_TEXT_1() {
        String bodyHtml = "<p>before</p><script>alert('x')</script><p>after</p>";
        String baseUri = "http://example.com/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_ELEMENT_AROUND_RETAINED_CONTENT_1() {
        String bodyHtml = "<custom-wrapper>retained ordinary content</custom-wrapper>";
        String baseUri = "https://example.com/content";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FORBIDDEN_ATTRIBUTE_ON_ALLOWED_ELEMENT_1() {
        String bodyHtml = "<p class=\"allowed-text\" onclick=\"alert(1)\" data-secret=\"hidden\">Visible</p>";
        String baseUri = "relative/base";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ALLOWED_HREF_1() {
        String bodyHtml = "<a href=\"https://example.com/\">Example</a>";
        String baseUri = "https://origin.example/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_JAVASCRIPT_HREF_1() {
        String bodyHtml = "<a href=\"javascript:alert(1)\">Safe visible label</a>";
        String baseUri = "https://origin.example/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_HREF_WITH_ABSOLUTE_BASE_1() {
        String bodyHtml = "<a href=\"docs/page.html\">Documentation</a>";
        String baseUri = "https://origin.example/root/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_HREF_WITH_EMPTY_BASE_AND_DEFAULT_RELATIVE_POLICY_1() {
        String bodyHtml = "<a href=\"docs/page.html\">Documentation</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_HREF_WITH_EMPTY_BASE_AND_PRESERVED_RELATIVE_POLICY_1() {
        String bodyHtml = "<a href=\"docs/page.html\">Documentation</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        safelist.preserveRelativeLinks(true);

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_ALLOWED_IMAGE_1() {
        String bodyHtml = "<img src=\"https://example.com/image.png\" alt=\"Example image\">";
        String baseUri = "http://origin.example/images/";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_UNSAFE_IMAGE_PROTOCOL_1() {
        String bodyHtml = "<img src=\"javascript:alert(1)\" alt=\"image\">";
        String baseUri = "https://example.com/images/";
        Safelist safelist = Safelist.basicWithImages();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_ALLOW_LIST_MARKUP_1() {
        String bodyHtml = "<div><blockquote><p><strong>Rich</strong> <em>nested</em> content</p></blockquote></div>";
        String baseUri = "relative/document";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_STRIPS_MARKUP_1() {
        String bodyHtml = "<section><p>Visible simple text</p></section>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_POLICY_REMOVES_MARKUP_BUT_RETAINS_TEXT_1() {
        String bodyHtml = "<strong><em>Nested visible text</em></strong>";
        String baseUri = "http://example.com/";
        Safelist safelist = Safelist.none();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_ATTRIBUTE_VALUE_1() {
        String bodyHtml = "<a href=\"\" title=\"\">Empty attributes</a>";
        String baseUri = "https://example.com/";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MANY_ATTRIBUTES_MIXED_ALLOWED_AND_FORBIDDEN_1() {
        String bodyHtml = "<a href=\"https://example.com/\" title=\"Example\" id=\"x\" onclick=\"bad()\">Link</a>";
        String baseUri = "relative/root/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_UNBALANCED_FRAGMENT_1() {
        String bodyHtml = "<p><strong>Unbalanced <em>fragment";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NESTED_ALLOWED_AND_DISALLOWED_ELEMENTS_1() {
        String bodyHtml = "Outside <custom><p>inside allowed markup</p></custom> after";
        String baseUri = "http://example.com/page";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_AROUND_MARKUP_1() {
        String bodyHtml = "  leading <strong>bold</strong>   middle <em>italic</em> trailing  ";
        String baseUri = "https://example.com/page";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_ATTRIBUTE_NAME_OR_UNUSUAL_ATTRIBUTE_SYNTAX_1() {
        String bodyHtml = "<p title=\"hello\" hidden>Unusual attributes</p>";
        String baseUri = "relative/page";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HTTPS_BASE_WITH_UNSAFE_RELATIVE_PROTOCOL_TEXT_1() {
        String bodyHtml = "<a href=\"https://safe.example/\">safe</a> <a href=\"docs/page.html\">relative</a> <a href=\"javascript:bad()\">unsafe</a>";
        String baseUri = "https://origin.example/root/";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPY_OF_POLICY_ON_ALREADY_NORMALIZED_FRAGMENT_1() {
        String originalBody = "<p><strong>Normalized</strong> <a href=\"https://example.com/\">link</a></p>";
        String baseUri = "https://origin.example/";
        Safelist originalSafelist = Safelist.basic();
        String normalizedBody = Jsoup.clean(originalBody, baseUri, originalSafelist);
        Safelist safelist = new Safelist(originalSafelist);

        String sourceOutput = Jsoup.clean(normalizedBody, baseUri, safelist);
        Object[] followUp = generateFollowUp(normalizedBody, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEFAULT_CONSTRUCTOR_POLICY_1() {
        String bodyHtml = "<p>Visible text <strong>and markup</strong></p>";
        String baseUri = "https://example.com/";
        Safelist safelist = new Safelist();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONEMPTY_BASE_URI_WITH_NO_URL_CONTENT_1() {
        String bodyHtml = "<p><strong>No URL content</strong> remains visible.</p>";
        String baseUri = "https://origin.example/page";
        Safelist safelist = Safelist.relaxed();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_URI_WITH_NONPRESERVING_POLICY_AND_URL_CONTENT_1() {
        String bodyHtml = "<a href=\"relative/page.html\">Relative link text</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();

        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
