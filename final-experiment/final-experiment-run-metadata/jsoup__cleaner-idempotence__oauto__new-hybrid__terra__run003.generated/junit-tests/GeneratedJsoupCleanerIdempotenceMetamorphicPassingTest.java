import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private Object[] generateFollowUp(String cleanedHtml, String baseUri, Safelist safelist) {
        return new Object[]{cleanedHtml, baseUri, safelist};
    }

    @Test
    void EMPTY_FRAGMENT_NONE_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("", "", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WHITESPACE_ONLY_SIMPLE_TEXT_variation1() {
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(" \t\r\n  ", "", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PLAIN_TEXT_NONE_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("plain text 123", "", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ESCAPED_MARKUP_NONE_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("&lt;script&gt;not an element&lt;/script&gt;", "", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASIC_TEXT_FORMATTING_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<p>alpha <strong>beta</strong> <em>gamma</em></p>",
                "https://example.test/docs/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/docs/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELAXED_DOCUMENT_LIKE_CONTENT_variation1() {
        Safelist safelist = Safelist.relaxed();
        String html = "<h1>Title</h1><p>Intro</p><ul><li>one</li><li>two</li></ul>"
                + "<blockquote>quote</blockquote><code>sample()</code>";
        String sourceOutput = Jsoup.clean(html, "https://example.test/articles/intro/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/articles/intro/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTED_SAFELIST_variation1() {
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean("<p>text <a href='https://example.test/'>link</a></p>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COPIED_BASIC_SAFELIST_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        String sourceOutput = Jsoup.clean("<p><b>bold</b> <a href='https://example.test/x'>link</a></p>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COPIED_RELAXED_SAFELIST_variation1() {
        Safelist safelist = new Safelist(Safelist.relaxed());
        String html = "<table><tr><th>Name</th><td><a href='https://example.test/item'>item</a></td></tr></table>";
        String sourceOutput = Jsoup.clean(html, "https://example.test/catalog/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/catalog/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNSAFE_ELEMENT_WITH_VISIBLE_SIBLING_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<p>before</p><script>alert(1)</script><p>after</p>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNSAFE_ONLY_ELEMENT_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("<script>alert(1)</script>", "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DISALLOWED_ATTRIBUTES_ON_ALLOWED_LOOKING_ELEMENT_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<p id='p1' class='c' style='color:red' onclick='run()'>visible</p>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ABSOLUTE_HTTP_AND_HTTPS_LINKS_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='http://example.test/a'>http</a>",
                "https://base.example.test/root/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://base.example.test/root/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ABSOLUTE_HTTP_AND_HTTPS_LINKS_variation2() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='https://example.test/b'>https</a>",
                "https://base.example.test/root/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://base.example.test/root/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAILTO_LINK_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='mailto:user@example.test'>contact</a>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAVASCRIPT_PROTOCOL_LINK_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='javascript:alert(1)'>click</a>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OBFUSCATED_JAVASCRIPT_PROTOCOL_LINKS_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='java&#x73;cript:alert(1)'>encoded</a>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OBFUSCATED_JAVASCRIPT_PROTOCOL_LINKS_variation2() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href=' javascript:alert(1)'>spaced</a>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DATA_URL_IMAGE_variation1() {
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean("<img src='data:image/png;base64,AAAA' alt='pixel'>",
                "https://example.test/assets/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/assets/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ABSOLUTE_IMAGE_BASIC_WITH_IMAGES_variation1() {
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean("<img src='https://cdn.example.test/p.png' alt='diagram' width='10' height='20'>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_LINK_EMPTY_BASE_DEFAULT_POLICY_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='guide/page.html'>guide</a>", "", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_LINK_NONEMPTY_BASE_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='guide/page.html'>guide</a>",
                "https://example.test/docs/current.html", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/docs/current.html", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_LINK_NONEMPTY_BASE_variation2() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='/root/page.html'>root</a>",
                "https://example.test/docs/current.html", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/docs/current.html", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_LINK_NONEMPTY_BASE_variation3() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='#part'>fragment</a>",
                "https://example.test/docs/current.html", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/docs/current.html", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RELATIVE_LINK_NONEMPTY_BASE_variation4() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='?q=1'>query</a>",
                "https://example.test/docs/current.html", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/docs/current.html", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROTOCOL_RELATIVE_LINK_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='//cdn.example.test/lib.js'>cdn</a>",
                "https://example.test/page/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/page/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_BASE_PRESERVE_RELATIVE_LINKS_SENTINEL_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean("<a href='relative/path.html'>relative</a>", "", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONEMPTY_BASE_PRESERVE_RELATIVE_LINKS_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean("<a href='relative/path.html'>relative</a>",
                "https://example.test/base/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/base/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BASE_ELEMENT_IN_FRAGMENT_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<base href='https://cdn.example.test/assets/'><a href='x.html'>x</a>",
                "", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MALFORMED_NESTING_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<p>one<b>two</p>three</b>", "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNCLOSED_TAGS_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<div><p>open <em>still open", "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BROKEN_ATTRIBUTE_QUOTING_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='https://example.test/ title=broken>text</a>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMMENTS_DOCTYPE_AND_PROCESSING_LIKE_MARKUP_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<!doctype html><!-- comment --><p>visible</p><?pi data?>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RAW_TEXT_LIKE_CONTENT_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<style>body{color:red}</style><script>1 < 2</script><p>visible</p>",
                "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TABLE_STRUCTURE_RELAXED_variation1() {
        Safelist safelist = Safelist.relaxed();
        String html = "<table><caption>C</caption><thead><tr><th>H</th></tr></thead>"
                + "<tbody><tr><td>D</td></tr></tbody></table>";
        String sourceOutput = Jsoup.clean(html, "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FOREIGN_MARKUP_variation1() {
        Safelist safelist = Safelist.relaxed();
        String html = "<svg><circle cx='1' cy='1' r='1'></circle></svg>"
                + "<math><mi>x</mi></math><p>text</p>";
        String sourceOutput = Jsoup.clean(html, "https://example.test/", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNICODE_AND_ENTITY_TEXT_variation1() {
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean("<p>café 😀 e\u0301 &amp; &nbsp;</p>", "", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_CHARACTER_AND_CONTROL_TEXT_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("first\u0000middle\u0001\tsecond\r\nthird",
                "not a URL but non-empty", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "not a URL but non-empty", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_URL_BASE_URI_STRING_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href='child.html'>child</a><p>text</p>",
                "not a URL but non-empty", safelist);
        Object[] followUp = generateFollowUp(sourceOutput, "not a URL but non-empty", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
