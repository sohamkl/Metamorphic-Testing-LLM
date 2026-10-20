import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static void verify(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BODY_NONE_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void WHITESPACE_ONLY_BODY_variation1() {
        String bodyHtml = " \t\r\n";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void PLAIN_TEXT_NONE_variation1() {
        String bodyHtml = "plain marker text";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void PREESCAPED_ENTITIES_NONE_variation1() {
        String bodyHtml = "5 is &lt; 6 &amp; 7 is &gt; 3";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RAW_SPECIAL_TEXT_ESCAPING_variation1() {
        String bodyHtml = "A & B < marker";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void UNICODE_TEXT_variation1() {
        String bodyHtml = "雪 café é 😀 marker";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void NONE_REMOVES_PARAGRAPH_MARKUP_variation1() {
        String bodyHtml = "<p>retained marker</p>";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void SIMPLE_TEXT_ALLOWED_INLINE_NESTING_variation1() {
        String bodyHtml = "<strong>outer <em>inner marker</em></strong>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void SIMPLE_TEXT_DISALLOWED_BLOCK_variation1() {
        String bodyHtml = "<p>block marker <strong>inline marker</strong></p>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void BASIC_PARAGRAPH_AND_LIST_variation1() {
        String bodyHtml = "<p>intro marker</p><ul><li>one</li><li>two</li></ul>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void BASIC_LINK_ENFORCED_ATTRIBUTES_variation1() {
        String bodyHtml = "<a href='https://example.test/page'>link marker</a>";
        String baseUri = "https://origin.test/root/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void BASIC_WITH_IMAGES_ABSOLUTE_SOURCE_variation1() {
        String bodyHtml = "<img src='https://img.example.test/a.png' alt='image marker'>";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELAXED_TABLE_STRUCTURE_variation1() {
        String bodyHtml = "<table><tr><td>cell marker</td></tr></table>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void EMPTY_CONSTRUCTOR_SAFELIST_variation1() {
        String bodyHtml = "<p>constructor marker</p><script>discarded</script>";
        String baseUri = "";
        Safelist safelist = new Safelist();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void COPIED_BASIC_SAFELIST_variation1() {
        String bodyHtml = "<p>copy marker <a href='https://example.test/'>link</a></p>";
        String baseUri = "";
        Safelist safelist = new Safelist(Safelist.basic());
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void UNCLOSED_ALLOWED_TAGS_variation1() {
        String bodyHtml = "<p>first marker<strong>second marker";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MISNESTED_FORMATTING_TAGS_variation1() {
        String bodyHtml = "<b>bold <i>inner</b> tail marker</i>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MIXED_CASE_TAG_NORMALIZATION_variation1() {
        String bodyHtml = "<P><A HREF='HTTPS://example.test/x'>case marker</A></P>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void COMMENT_REMOVAL_variation1() {
        String bodyHtml = "before marker<!-- secret comment -->after marker";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void DOCUMENT_WRAPPERS_IN_BODY_FRAGMENT_variation1() {
        String bodyHtml = "<!doctype html><html><head><title>head marker</title></head>"
                + "<body><p>body marker</p></body></html>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void SCRIPT_CONTENT_REMOVAL_variation1() {
        String bodyHtml = "<p>safe marker</p><script>alert('unsafe marker')</script>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void EMBEDDED_FRAME_REMOVAL_variation1() {
        String bodyHtml = "<iframe src='https://evil.test/'>frame marker</iframe>"
                + "<p>safe marker</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void UNKNOWN_CUSTOM_ELEMENT_variation1() {
        String bodyHtml = "<x-unknown data-x='1'>custom marker</x-unknown>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_variation1() {
        String bodyHtml = "<p onclick='run()'>event marker</p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void STYLE_ATTRIBUTE_FILTERING_variation1() {
        String bodyHtml = "<p style='color:red'>style-attribute marker</p>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void DUPLICATE_ATTRIBUTES_variation1() {
        String bodyHtml = "<a href='https://first.example/' "
                + "href='javascript:bad()'>duplicate marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void UNQUOTED_ATTRIBUTE_NORMALIZATION_variation1() {
        String bodyHtml = "<a href=https://example.test/path?q=1>unquoted marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void JAVASCRIPT_LINK_PROTOCOL_variation1() {
        String bodyHtml = "<a href='javascript:alert(1)'>unsafe-link marker</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void ENTITY_OBFUSCATED_JAVASCRIPT_PROTOCOL_variation1() {
        String bodyHtml = "<a href='java&#x73;cript:alert(1)'>encoded-unsafe marker</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void DATA_IMAGE_PROTOCOL_variation1() {
        String bodyHtml = "<img src='data:image/png;base64,AAAA' alt='data image marker'>"
                + "<p>tail marker</p>";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MAILTO_LINK_PROTOCOL_variation1() {
        String bodyHtml = "<a href='mailto:user@example.test'>mail marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void FTP_LINK_PROTOCOL_variation1() {
        String bodyHtml = "<a href='ftp://files.example.test/a.txt'>ftp marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void ABSOLUTE_HTTP_LINK_variation1() {
        String bodyHtml = "<a href='http://example.test/a'>http marker</a>";
        String baseUri = "https://origin.test/root/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELATIVE_PATH_WITH_BASE_variation1() {
        String bodyHtml = "<a href='child/page.html'>relative marker</a>";
        String baseUri = "https://example.test/root/index.html";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void PARENT_RELATIVE_LINK_WITH_BASE_variation1() {
        String bodyHtml = "<a href='../up/page.html'>parent marker</a>";
        String baseUri = "https://example.test/a/b/index.html";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void FRAGMENT_LINK_WITH_BASE_variation1() {
        String bodyHtml = "<a href='#section'>fragment marker</a>";
        String baseUri = "https://example.test/page.html";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void EMPTY_BASE_RELATIVE_LINK_DEFAULT_POLICY_variation1() {
        String bodyHtml = "<a href='relative/page.html'>empty-base marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void BODY_BASE_TAG_RESOLUTION_variation1() {
        String bodyHtml = "<base href='https://base.example/root/'>"
                + "<a href='child'>base-tag marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void PRESERVE_RELATIVE_EMPTY_BASE_SENTINEL_variation1() {
        String bodyHtml = "<a href='relative/page.html'>sentinel marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void PRESERVE_RELATIVE_WITH_REAL_BASE_variation1() {
        String bodyHtml = "<a href='child/page.html'>preserved marker</a>";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_variation1() {
        String bodyHtml = "<a href='//cdn.example.test/resource'>network-path marker</a>";
        String baseUri = "https://origin.example.test/root/";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELATIVE_IMAGE_SOURCE_WITH_BASE_variation1() {
        String bodyHtml = "<img src='images/a.png' alt='relative image marker'>";
        String baseUri = "https://example.test/articles/page.html";
        Safelist safelist = Safelist.basicWithImages();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void DEEP_ALLOWED_NESTING_variation1() {
        String bodyHtml = "<strong><em><u><i><strong><em>deep marker</em></strong>"
                + "</i></u></em></strong>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MULTILINE_BLOCK_SERIALIZATION_variation1() {
        String bodyHtml = "<div><p>first marker</p><p>second marker<br>line</p></div>"
                + "<blockquote>quote marker</blockquote>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void NULL_CHARACTER_IN_TEXT_variation1() {
        String bodyHtml = "before marker" + '\0' + "after marker";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void CHARACTER_REFERENCE_IN_URL_variation1() {
        String bodyHtml = "<a href='https://example.test/search?a=1&amp;b=2'>query marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MALFORMED_TAG_BOUNDARY_variation1() {
        String bodyHtml = "before marker <strong after marker";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void EMPTY_BASE_ABSOLUTE_LINK_variation1() {
        String bodyHtml = "<a href='https://example.test/page'>absolute marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        verify(bodyHtml, baseUri, safelist);
    }

    @Test
    public void PRESERVE_RELATIVE_UNSAFE_ABSOLUTE_PROTOCOL_variation1() {
        String bodyHtml = "<a href='javascript:bad()'>preserve-policy marker</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify(bodyHtml, baseUri, safelist);
    }
}
