import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static Object[] generateFollowUp(String bodyHtml, String baseUri, Safelist safelist) {
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        return new Object[]{cleanedHtml, baseUri, safelist};
    }

    @Test
    public void EMPTY_BODY_NONE_1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_NONE_1() {
        String bodyHtml = "plain text retained";
        String baseUri = "https://example.test/page";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENTITIES_NONE_1() {
        String bodyHtml = "5 &lt; 6 &amp; 7";
        String baseUri = "http://example.test/entities";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_MARKUP_NONE_1() {
        String bodyHtml = "<section><b>visible text</b><script>ignored()</script></section>";
        String baseUri = "section/page.html";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_ALLOWED_AND_DISALLOWED_1() {
        String bodyHtml = "<b><i>formatted</i></b><div>plain structural text</div><script>bad()</script>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ALLOWED_INLINE_1() {
        String bodyHtml = "<p><strong>bold <em>nested</em></strong></p>";
        String baseUri = "https://example.test/page";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_DISALLOWED_ELEMENT_1() {
        String bodyHtml = "<p>allowed paragraph</p><iframe>disallowed frame text</iframe>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_ATTRIBUTE_FILTERING_1() {
        String bodyHtml = "<p class='kept?' id='removed' title='removed'>attribute text</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_1() {
        String bodyHtml = "<p onclick='alert(1)' onmouseover='bad()'>safe visible text</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_NESTED_MARKUP_1() {
        String bodyHtml = "<div><p>intro <strong>bold</strong></p><blockquote><em>quoted</em></blockquote></div>";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_ALLOWED_IMAGE_1() {
        String bodyHtml = "before <img src='https://cdn.example.test/photo.png' alt='photo'> after";
        String baseUri = "https://example.test/content/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_UNSAFE_PROTOCOL_1() {
        String bodyHtml = "<img src='data:image/png;base64,AAAA' alt='unsafe'>visible image text";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITH_HTTPS_BASE_1() {
        String bodyHtml = "<a href='../docs/guide.html'>read the guide</a>";
        String baseUri = "https://example.test/a/page.html";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_HTTP_LINK_1() {
        String bodyHtml = "<a href='http://example.org/news'>news link</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_HTTPS_LINK_1() {
        String bodyHtml = "<a href='https://secure.example.test/account'>secure account</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAVASCRIPT_LINK_PROTOCOL_1() {
        String bodyHtml = "<a href='javascript:alert(1)'>visible link text</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_1() {
        String bodyHtml = "<a href='//cdn.example.test/resource'>protocol relative resource</a>";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_RELATIVE_LINK_1() {
        String bodyHtml = "<a href='relative/path'>relative link text</a>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_BASE_STRING_1() {
        String bodyHtml = "<a href='next.html'>next page</a>";
        String baseUri = "section/page.html";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATA_URL_ATTRIBUTE_1() {
        String bodyHtml = "<q cite='data:text/plain,hello'>quoted visible text</q>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_UNBALANCED_FRAGMENT_1() {
        String bodyHtml = "<div><p>unbalanced <strong>fragment</div> trailing text";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMENTS_AND_TEXT_1() {
        String bodyHtml = "before<!-- hidden comment --><p>visible paragraph</p>after";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTIPLE_SIBLINGS_1() {
        String bodyHtml = "first <em>second</em><script>removed</script> third";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEW_EMPTY_SAFELIST_1() {
        String bodyHtml = "<div>constructor policy text</div>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNICODE_AND_WHITESPACE_1() {
        String bodyHtml = "<p>こんにちは\n\n  café&nbsp;&nbsp;世界</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FULL_DOCUMENT_TAGS_AS_FRAGMENT_1() {
        String bodyHtml = "<html><head><title>ignored title</title></head><body><p>fragment content</p></body></html>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONLY_FILTERED_CONTENT_1() {
        String bodyHtml = "<script>alert(1)</script><iframe>not retained</iframe>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_URL_PROTOCOLS_1() {
        String bodyHtml = "<a href='relative.html'>relative</a><a href='http://example.org/a'>http</a><a href='https://example.org/b'>https</a><a href='javascript:bad()'>script</a>";
        String baseUri = "https://example.test/base/page.html";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_MIXED_1() {
        String bodyHtml = "<img src='https://example.test/safe.png' alt='safe'><img src='data:image/png;base64,AAAA' alt='data'>surrounding image text";
        String baseUri = "https://example.test/assets/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASE_ELEMENT_PRESENT_1() {
        String bodyHtml = "<base href='https://cdn.example.test/assets/'><a href='guide.html'>base-relative guide</a>";
        String baseUri = "https://example.test/original/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
