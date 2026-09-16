import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source(
            String bodyHtml, String baseUri, Safelist safelist) {
        return new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(bodyHtml, baseUri, safelist);
    }

    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input generateFollowUp(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source,
            String sourceOutput) {
        return new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(
                sourceOutput, source.arg1(), source.arg2());
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source) {
        String sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(source);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input followUp =
                generateFollowUp(source, sourceOutput);
        String followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static String repeat(String value, int count) {
        StringBuilder result = new StringBuilder(value.length() * count);
        for (int i = 0; i < count; i++) {
            result.append(value);
        }
        return result.toString();
    }

    @Test
    public void EMPTY_BODY_NONE_variation1() {
        assertMetamorphicRelationFor(source("", "", Safelist.none()));
    }

    @Test
    public void PLAIN_ASCII_TEXT_NONE_variation1() {
        assertMetamorphicRelationFor(
                source("plain text 123", "http://origin.test/", Safelist.none()));
    }

    @Test
    public void PREESCAPED_ENTITIES_NONE_variation1() {
        assertMetamorphicRelationFor(source(
                "5 is &lt; 6 &amp;&amp; 7 is &gt; 3",
                "https://origin.test/",
                Safelist.none()));
    }

    @Test
    public void LITERAL_SPECIAL_CHARACTERS_NONE_variation1() {
        assertMetamorphicRelationFor(source(
                "5 < 6 & 7 > 3",
                "https://origin.test/path?q=1#fragment",
                Safelist.none()));
    }

    @Test
    public void UNICODE_TEXT_variation1() {
        assertMetamorphicRelationFor(
                source("日本語 é e\u0301 😀", "", Safelist.none()));
    }

    @Test
    public void WHITESPACE_ONLY_BODY_variation1() {
        assertMetamorphicRelationFor(
                source(" \t\n  \n", "http://origin.test/", Safelist.none()));
    }

    @Test
    public void COMMENT_ONLY_BODY_variation1() {
        assertMetamorphicRelationFor(source(
                "<!--comment-->",
                "https://origin.test/",
                Safelist.relaxed()));
    }

    @Test
    public void DOCTYPE_ONLY_FRAGMENT_variation1() {
        assertMetamorphicRelationFor(source(
                "<!doctype html>",
                "https://origin.test/path?q=1#fragment",
                Safelist.relaxed()));
    }

    @Test
    public void UNSAFE_WRAPPER_VISIBLE_TEXT_variation1() {
        assertMetamorphicRelationFor(
                source("<unknown>visible</unknown>", "", Safelist.basic()));
    }

    @Test
    public void SCRIPT_ONLY_CONTENT_variation1() {
        assertMetamorphicRelationFor(source(
                "<script>alert(1)</script>",
                "http://origin.test/",
                Safelist.relaxed()));
    }

    @Test
    public void SCRIPT_BETWEEN_SAFE_TEXT_variation1() {
        assertMetamorphicRelationFor(source(
                "before<script>alert(1)</script>after",
                "https://origin.test/",
                Safelist.basic()));
    }

    @Test
    public void STYLE_ONLY_CONTENT_variation1() {
        assertMetamorphicRelationFor(source(
                "<style>body{display:none}</style>",
                "https://origin.test/path?q=1#fragment",
                Safelist.relaxed()));
    }

    @Test
    public void SIMPLE_TEXT_INLINE_MARKUP_variation1() {
        assertMetamorphicRelationFor(source(
                "<b>bold <em>emphasized</em></b>",
                "",
                Safelist.simpleText()));
    }

    @Test
    public void SIMPLE_TEXT_BLOCK_WRAPPER_variation1() {
        assertMetamorphicRelationFor(source(
                "<p>paragraph <b>bold</b></p>",
                "http://origin.test/",
                Safelist.simpleText()));
    }

    @Test
    public void BASIC_NESTED_LIST_variation1() {
        assertMetamorphicRelationFor(source(
                "<p>items</p><ul><li>one</li><li><strong>two</strong></li></ul>",
                "https://origin.test/",
                Safelist.basic()));
    }

    @Test
    public void GLOBAL_UNSAFE_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<p id='p1' class='c' style='color:red' data-x='1'>text</p>",
                "https://origin.test/path?q=1#fragment",
                Safelist.basic()));
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<p onclick='run()'>p</p>"
                        + "<a href='https://example.test/' onmouseover='run()'>a</a>",
                "",
                Safelist.basic()));
    }

    @Test
    public void BASIC_ABSOLUTE_HTTPS_LINK_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='https://example.test/path?q=1#f'>link</a>",
                "https://origin.test/root/",
                Safelist.basic()));
    }

    @Test
    public void BASIC_ENFORCED_LINK_ATTRIBUTE_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='https://example.test/'>link</a>",
                "https://origin.test/",
                Safelist.basic()));
    }

    @Test
    public void RELATIVE_LINK_WITH_BASE_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='../target?q=1#f'>target</a>",
                "https://example.test/a/b/",
                Safelist.basic()));
    }

    @Test
    public void RELATIVE_LINK_WITH_EMPTY_BASE_variation1() {
        assertMetamorphicRelationFor(
                source("<a href='target'>target</a>", "", Safelist.basic()));
    }

    @Test
    public void BODY_BASE_ELEMENT_RESOLUTION_variation1() {
        assertMetamorphicRelationFor(source(
                "<base href='https://example.test/root/'><a href='child'>child</a>",
                "",
                Safelist.basic()));
    }

    @Test
    public void JAVASCRIPT_LINK_PROTOCOL_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='javascript:alert(1)'>click</a>",
                "https://origin.test/",
                Safelist.basic()));
    }

    @Test
    public void OBFUSCATED_UNSAFE_PROTOCOL_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='JaVaScRiPt&#58;alert(1)'>click</a>",
                "https://origin.test/path?q=1#fragment",
                Safelist.basic()));
    }

    @Test
    public void MAILTO_LINK_PROTOCOL_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='mailto:user@example.test'>mail</a>",
                "",
                Safelist.basic()));
    }

    @Test
    public void FRAGMENT_ONLY_LINK_EMPTY_BASE_variation1() {
        assertMetamorphicRelationFor(
                source("<a href='#section'>section</a>", "", Safelist.basic()));
    }

    @Test
    public void BASIC_POLICY_IMAGE_variation1() {
        assertMetamorphicRelationFor(source(
                "before<img src='https://example.test/a.png' alt='a'>after",
                "https://origin.test/",
                Safelist.basic()));
    }

    @Test
    public void BASIC_WITH_IMAGES_ABSOLUTE_SOURCE_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src='https://example.test/a.png' alt='sample' "
                        + "width='10' height='20'>",
                "https://origin.test/path?q=1#fragment",
                Safelist.basicWithImages()));
    }

    @Test
    public void BASIC_WITH_IMAGES_RELATIVE_SOURCE_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src='../img/a.png' alt='sample'>",
                "https://example.test/pages/current/",
                Safelist.basicWithImages()));
    }

    @Test
    public void BASIC_WITH_IMAGES_UNSAFE_SOURCE_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src='data:text/html,unsafe' alt='sample'>",
                "http://origin.test/",
                Safelist.basicWithImages()));
    }

    @Test
    public void RELAXED_STRUCTURAL_ELEMENTS_variation1() {
        assertMetamorphicRelationFor(source(
                "<div><h2>Heading</h2><p>text <em>em</em></p></div>",
                "https://origin.test/",
                Safelist.relaxed()));
    }

    @Test
    public void RELAXED_TABLE_STRUCTURE_variation1() {
        assertMetamorphicRelationFor(source(
                "<table><thead><tr><th>H</th></tr></thead>"
                        + "<tbody><tr><td>D</td></tr></tbody></table>",
                "https://origin.test/path?q=1#fragment",
                Safelist.relaxed()));
    }

    @Test
    public void RELAXED_LIST_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<ol start='2' type='A'><li>x</li></ol>"
                        + "<ul type='square'><li>y</li></ul>",
                "",
                Safelist.relaxed()));
    }

    @Test
    public void RELAXED_IMAGE_MIXED_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<img src='https://example.test/a.png' alt='a' "
                        + "title='t' onclick='run()'>",
                "http://origin.test/",
                Safelist.relaxed()));
    }

    @Test
    public void RELAXED_IFRAME_FALLBACK_variation1() {
        Safelist safelist = new Safelist(Safelist.relaxed());
        assertMetamorphicRelationFor(source(
                "<iframe src='https://example.test/'>fallback</iframe>",
                "https://origin.test/",
                safelist));
    }

    @Test
    public void UNCLOSED_ALLOWED_ELEMENTS_variation1() {
        assertMetamorphicRelationFor(source(
                "<p>one<strong>two",
                "https://origin.test/path?q=1#fragment",
                Safelist.basic()));
    }

    @Test
    public void MISNESTED_FORMATTING_ELEMENTS_variation1() {
        assertMetamorphicRelationFor(source(
                "<b>bold <i>both</b> italic</i>",
                "",
                Safelist.simpleText()));
    }

    @Test
    public void IMPLIED_TABLE_STRUCTURE_variation1() {
        assertMetamorphicRelationFor(source(
                "<td>A</td><td>B</td>",
                "http://origin.test/",
                Safelist.relaxed()));
    }

    @Test
    public void DUPLICATE_HREF_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='https://safe.example/' "
                        + "href='javascript:alert(1)'>link</a>",
                "https://origin.test/",
                Safelist.basic()));
    }

    @Test
    public void FULL_DOCUMENT_AS_BODY_FRAGMENT_variation1() {
        assertMetamorphicRelationFor(source(
                "<html><head><title>T</title></head>"
                        + "<body><p>B</p></body></html>",
                "https://origin.test/path?q=1#fragment",
                Safelist.relaxed()));
    }

    @Test
    public void ENCODED_MARKUP_REMAINS_TEXT_variation1() {
        assertMetamorphicRelationFor(source(
                "&lt;script&gt;alert(1)&lt;/script&gt;",
                "",
                Safelist.none()));
    }

    @Test
    public void UNKNOWN_CHARACTER_REFERENCE_variation1() {
        assertMetamorphicRelationFor(source(
                "known:&amp; unknown:&notARealEntity;",
                "http://origin.test/",
                Safelist.none()));
    }

    @Test
    public void NULL_CHARACTER_IN_TEXT_variation1() {
        String bodyHtml = "A" + '\0' + "B";
        assertMetamorphicRelationFor(
                source(bodyHtml, "https://origin.test/", Safelist.none()));
    }

    @Test
    public void LONG_TEXT_NODE_variation1() {
        String bodyHtml = repeat("x", 4096);
        assertMetamorphicRelationFor(source(
                bodyHtml,
                "https://origin.test/path?q=1#fragment",
                Safelist.none()));
    }

    @Test
    public void DEEP_ALLOWED_NESTING_variation1() {
        String bodyHtml = repeat("<b>", 100) + "x" + repeat("</b>", 100);
        assertMetamorphicRelationFor(
                source(bodyHtml, "", Safelist.simpleText()));
    }

    @Test
    public void COPY_OF_BASIC_POLICY_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        assertMetamorphicRelationFor(source(
                "<p>text <a href='https://example.test/'>link</a>"
                        + "<script>x</script></p>",
                "http://origin.test/",
                safelist));
    }

    @Test
    public void COPY_OF_RELAXED_POLICY_variation1() {
        Safelist safelist = new Safelist(Safelist.relaxed());
        assertMetamorphicRelationFor(source(
                "<table><tr><td>cell</td></tr></table>"
                        + "<iframe>fallback</iframe>",
                "https://origin.test/",
                safelist));
    }

    @Test
    public void EMPTY_CONSTRUCTOR_POLICY_variation1() {
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(source(
                "<p><b>visible</b></p>",
                "https://origin.test/path?q=1#fragment",
                safelist));
    }

    @Test
    public void MIXED_CASE_TAGS_AND_ATTRIBUTES_variation1() {
        assertMetamorphicRelationFor(source(
                "<P><A HrEf='HTTPS://example.test/path'>Link</A></P>",
                "",
                Safelist.basic()));
    }

    @Test
    public void TEXTAREA_MARKUP_LIKE_CONTENT_variation1() {
        assertMetamorphicRelationFor(source(
                "<textarea><b>not bold</b> &amp; text</textarea>",
                "http://origin.test/",
                Safelist.basic()));
    }
}
