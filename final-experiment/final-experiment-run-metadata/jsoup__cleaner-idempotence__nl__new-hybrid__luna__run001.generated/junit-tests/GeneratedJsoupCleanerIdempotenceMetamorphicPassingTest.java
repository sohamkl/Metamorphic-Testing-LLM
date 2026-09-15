import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static String generateFollowUp(String cleaned) {
        return cleaned;
    }

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(generateFollowUp(sourceOutput), baseUri, safelist);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static Safelist copied(Safelist source) {
        return new Safelist(source);
    }

    private static Safelist preservingRelativeLinks(Safelist source) {
        return source.preserveRelativeLinks(true);
    }

    private static String longText() {
        return "plain text ".repeat(30);
    }

    private static String longMixedFragment() {
        return "<p>Alpha &amp; beta <a href='https://example.com/a' title='remove'>link</a></p>"
                + "<div><strong>nested</strong><em>content</em><script>discarded</script></div>"
                + "<img src='https://example.com/image.png' onerror='remove'>"
                + "<blockquote cite='https://example.com/quote'>quoted</blockquote>"
                + " repeated content ".repeat(20);
    }

    @Test
    public void EMPTY_FRAGMENT_variation1() {
        assertMetamorphicRelationFor("", "", new Safelist());
    }

    @Test
    public void EMPTY_FRAGMENT_variation2() {
        assertMetamorphicRelationFor(" \t\n ", "http://example.com/page", Safelist.none());
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_variation1() {
        assertMetamorphicRelationFor(" \t\n  \r\n\t ", "https://example.com/base", Safelist.simpleText());
    }

    @Test
    public void PLAIN_TEXT_CONTENT_variation1() {
        assertMetamorphicRelationFor(longText(), "docs/page.html", Safelist.basic());
    }

    @Test
    public void ENTITY_ESCAPING_variation1() {
        assertMetamorphicRelationFor("5 &lt; 6 &amp; 7 > 2", "", Safelist.basicWithImages());
    }

    @Test
    public void DISALLOWED_TAG_CONTENT_RETENTION_variation1() {
        assertMetamorphicRelationFor("<script>alert('x')</script>", "http://example.com/", Safelist.relaxed());
    }

    @Test
    public void DISALLOWED_TAG_CONTENT_RETENTION_variation2() {
        Safelist policy = copied(Safelist.relaxed());
        assertMetamorphicRelationFor("<script><b>kept text</b>", "https://example.com/root/", policy);
    }

    @Test
    public void PERMITTED_TAG_PRESERVATION_variation1() {
        assertMetamorphicRelationFor("<p>paragraph</p>", "docs/page.html", new Safelist());
    }

    @Test
    public void PERMITTED_TAG_PRESERVATION_variation2() {
        assertMetamorphicRelationFor("visible text", "", Safelist.none());
    }

    @Test
    public void NESTED_ALLOWED_ELEMENTS_variation1() {
        assertMetamorphicRelationFor("<strong><em>nested text</em></strong>",
                "http://example.com/", Safelist.simpleText());
    }

    @Test
    public void MALFORMED_BALANCED_FRAGMENT_variation1() {
        assertMetamorphicRelationFor("<p><b>unclosed text", "https://example.com/base", Safelist.basic());
    }

    @Test
    public void DISALLOWED_ATTRIBUTE_variation1() {
        assertMetamorphicRelationFor("<p onclick='alert(1)'>safe text</p>", "", Safelist.relaxed());
    }

    @Test
    public void DISALLOWED_ATTRIBUTE_variation2() {
        Safelist policy = copied(Safelist.basic());
        assertMetamorphicRelationFor("<a href='https://example.com' style='color:red'>link</a>",
                "http://example.com/", policy);
    }

    @Test
    public void PERMITTED_ATTRIBUTE_variation1() {
        assertMetamorphicRelationFor("<a href='https://example.com'>allowed link</a>",
                "https://example.com/", new Safelist());
    }

    @Test
    public void PERMITTED_ATTRIBUTE_variation2() {
        assertMetamorphicRelationFor("<a href='https://example.com'>link</a>",
                "docs/index.html", Safelist.none());
    }

    @Test
    public void MIXED_ALLOWED_AND_DISALLOWED_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor("<b title='removed' class='removed'>bold text</b>",
                "", Safelist.simpleText());
    }

    @Test
    public void UNSAFE_URL_PROTOCOL_variation1() {
        assertMetamorphicRelationFor("<a href='javascript:alert(1)'>unsafe link</a>",
                "http://example.com/", Safelist.basic());
    }

    @Test
    public void UNSAFE_URL_PROTOCOL_variation2() {
        assertMetamorphicRelationFor("<img src='javascript:alert(1)' alt='bad'>image",
                "https://example.com/", Safelist.basicWithImages());
    }

    @Test
    public void ALLOWED_ABSOLUTE_URL_PROTOCOL_variation1() {
        assertMetamorphicRelationFor("<a href='https://example.com/path'>secure link</a>",
                "docs/page.html", Safelist.relaxed());
    }

    @Test
    public void ALLOWED_ABSOLUTE_URL_PROTOCOL_variation2() {
        Safelist policy = copied(Safelist.basic());
        assertMetamorphicRelationFor("<a href='http://example.com/path'>http link</a>", "", policy);
    }

    @Test
    public void RELATIVE_URL_WITH_NONEMPTY_BASE_variation1() {
        assertMetamorphicRelationFor("<a href='../target.html'>relative link</a>",
                "http://example.com/docs/page.html", new Safelist().addTags("a").addAttributes("a", "href")
                        .addProtocols("a", "href", "http", "https"));
    }

    @Test
    public void RELATIVE_URL_WITH_NONEMPTY_BASE_variation2() {
        assertMetamorphicRelationFor("<a href='images/picture.png'>relative image</a>",
                "https://example.com/articles/index.html", Safelist.relaxed());
    }

    @Test
    public void EMPTY_BASE_RELATIVE_LINKS_DISABLED_variation1() {
        assertMetamorphicRelationFor("<a href='relative/path'>relative disabled</a>", "",
                Safelist.basic());
    }

    @Test
    public void EMPTY_BASE_RELATIVE_LINKS_ENABLED_variation1() {
        Safelist policy = preservingRelativeLinks(Safelist.basic());
        assertMetamorphicRelationFor("<a href='relative/path'>relative enabled</a>", "", policy);
    }

    @Test
    public void EMPTY_BASE_ABSOLUTE_URL_variation1() {
        assertMetamorphicRelationFor("<a href='https://example.com/absolute'>absolute link</a>",
                "", Safelist.basic());
    }

    @Test
    public void BASIC_POLICY_TEXT_AND_LINK_variation1() {
        assertMetamorphicRelationFor("Text <p>paragraph</p> <a href='https://example.com'>link</a>",
                "https://example.com/start", Safelist.basic());
    }

    @Test
    public void BASIC_POLICY_TEXT_AND_LINK_variation2() {
        Safelist policy = copied(Safelist.basic());
        assertMetamorphicRelationFor("Read <a href='http://example.com' onclick='bad()'>more</a> &amp; continue",
                "docs/start.html", policy);
    }

    @Test
    public void BASIC_IMAGES_POLICY_variation1() {
        assertMetamorphicRelationFor("Image <img src='https://example.com/a.png' alt='a'>",
                "", Safelist.basicWithImages());
    }

    @Test
    public void BASIC_IMAGES_POLICY_variation2() {
        assertMetamorphicRelationFor("<img src='https://example.com/a.png' onerror='bad()'><script>x</script>",
                "http://example.com/", Safelist.basicWithImages());
    }

    @Test
    public void RELAXED_POLICY_RICH_FRAGMENT_variation1() {
        assertMetamorphicRelationFor("<p><b>bold</b> <i>italic</i> <a href='relative'>link</a>"
                        + "<script>bad</script>", "https://example.com/base", Safelist.relaxed());
    }

    @Test
    public void RELAXED_POLICY_RICH_FRAGMENT_variation2() {
        assertMetamorphicRelationFor("<table><tr><td>cell</td></tr></table><p title='bad'>text</p>"
                        + "<iframe>discard</iframe>", "docs/page.html", Safelist.basic());
    }

    @Test
    public void SIMPLE_TEXT_POLICY_MARKUP_variation1() {
        assertMetamorphicRelationFor("", "", Safelist.simpleText());
    }

    @Test
    public void SIMPLE_TEXT_POLICY_MARKUP_variation2() {
        assertMetamorphicRelationFor(" <b title='bad'>text</b> <div>removed</div> ",
                "http://example.com/", Safelist.simpleText());
    }

    @Test
    public void NONE_POLICY_MARKUP_variation1() {
        assertMetamorphicRelationFor("<div data-x='1'><span>text &lt; value</span></div>",
                "https://example.com/", copied(Safelist.none()));
    }

    @Test
    public void NONE_POLICY_MARKUP_variation2() {
        assertMetamorphicRelationFor("<section class='x'><em>escaped &amp; visible</em></section>",
                "relative/page.html", new Safelist());
    }

    @Test
    public void EMPTY_CONSTRUCTOR_POLICY_variation1() {
        assertMetamorphicRelationFor("<div class='x'>constructed policy text</div>", "",
                new Safelist());
    }

    @Test
    public void COPIED_SAFELIST_INSTANCE_variation1() {
        Safelist policy = copied(Safelist.relaxed());
        assertMetamorphicRelationFor("<p>allowed</p><script>removed</script>"
                        + "<a href='https://example.com' onclick='bad()'>link</a>",
                "http://example.com/", policy);
    }

    @Test
    public void MULTIPLE_SIMULTANEOUS_FILTERS_variation1() {
        assertMetamorphicRelationFor("<script>bad</script><p onclick='bad()'>text</p>"
                        + "<a href='javascript:bad()'>unsafe</a>", "https://example.com/",
                Safelist.basic());
    }

    @Test
    public void MULTIPLE_SIMULTANEOUS_FILTERS_variation2() {
        assertMetamorphicRelationFor("<div><img src='javascript:bad()' onerror='bad()'>"
                        + "<b>kept</b></div><iframe>gone</iframe>", "docs/page.html",
                Safelist.basicWithImages());
    }

    @Test
    public void LONG_MIXED_FRAGMENT_variation1() {
        assertMetamorphicRelationFor(longMixedFragment(), "", Safelist.relaxed());
    }
}
