import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private String generateFollowUp(String sourceOutput) {
        return sourceOutput;
    }

    private void assertMetamorphicRelationFor(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        String followUpHtml = generateFollowUp(sourceOutput);
        String followUpOutput = Jsoup.clean(followUpHtml, baseUri, safelist);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    void none_empty_fragment_variation1() {
        assertMetamorphicRelationFor("", "", Safelist.none());
    }

    @Test
    void none_plain_text_variation1() {
        assertMetamorphicRelationFor("5 < 6 & 7 > 3", "", Safelist.none());
    }

    @Test
    void none_allowed_looking_markup_removed_text_retained_variation1() {
        assertMetamorphicRelationFor("<p>Hello <b>world</b></p>",
            "https://example.test/root/page.html", Safelist.none());
    }

    @Test
    void none_script_only_variation1() {
        assertMetamorphicRelationFor("<script>alert(1)</script>", "", Safelist.none());
    }

    @Test
    void none_entity_text_variation1() {
        assertMetamorphicRelationFor("A&amp;B &#x3C; C &#62; D",
            "https://example.test/root/page.html", Safelist.none());
    }

    @Test
    void simple_text_inline_formatting_variation1() {
        assertMetamorphicRelationFor("<b>bold</b> <em>emphasis</em> <strong>strong</strong>",
            "", Safelist.simpleText());
    }

    @Test
    void simple_text_block_element_variation1() {
        assertMetamorphicRelationFor("<h1>Title</h1><p>Paragraph</p>",
            "https://example.test/root/page.html", Safelist.simpleText());
    }

    @Test
    void simple_text_link_with_href_variation1() {
        assertMetamorphicRelationFor("<a href=\"https://safe.example/path\">link text</a>",
            "", Safelist.simpleText());
    }

    @Test
    void simple_text_unsafe_container_variation1() {
        assertMetamorphicRelationFor("<div><script>x()</script><b>kept text</b></div>",
            "https://example.test/root/page.html", Safelist.simpleText());
    }

    @Test
    void basic_absolute_http_link_variation1() {
        assertMetamorphicRelationFor("<a href=\"http://example.test/docs\">documentation</a>",
            "https://example.test/root/page.html", Safelist.basic());
    }

    @Test
    void basic_absolute_https_link_variation1() {
        assertMetamorphicRelationFor("<a href=\"https://example.test/docs?q=1\" title=\"Docs\">documentation</a>",
            "", Safelist.basic());
    }

    @Test
    void basic_relative_link_with_absolute_base_variation1() {
        assertMetamorphicRelationFor("<a href=\"guide/start.html\">guide</a>",
            "https://example.test/root/page.html", Safelist.basic());
    }

    @Test
    void basic_relative_link_with_empty_base_variation1() {
        assertMetamorphicRelationFor("<a href=\"guide/start.html\">guide</a>",
            "", Safelist.basic());
    }

    @Test
    void basic_javascript_link_variation1() {
        assertMetamorphicRelationFor("<a href=\"javascript:alert(1)\">click</a>",
            "https://example.test/root/page.html", Safelist.basic());
    }

    @Test
    void basic_whitespace_obscured_javascript_link_variation1() {
        assertMetamorphicRelationFor("<a href=\" \tjavascript:alert(1)\">click</a>",
            "", Safelist.basic());
    }

    @Test
    void basic_event_handler_attribute_variation1() {
        assertMetamorphicRelationFor("<a href=\"https://example.test/\" onclick=\"alert(1)\">safe text</a>",
            "https://example.test/root/page.html", Safelist.basic());
    }

    @Test
    void basic_unknown_attribute_variation1() {
        assertMetamorphicRelationFor("<p data-trace=\"123\" class=\"note\">paragraph</p>",
            "", Safelist.basic());
    }

    @Test
    void basic_nested_unsafe_element_variation1() {
        assertMetamorphicRelationFor("<p>before<iframe src=\"https://evil.example/\"></iframe>after</p>",
            "https://example.test/root/page.html", Safelist.basic());
    }

    @Test
    void basic_malformed_nesting_variation1() {
        assertMetamorphicRelationFor("<p>one<b>two</p>three</b>", "", Safelist.basic());
    }

    @Test
    void basic_with_images_safe_image_variation1() {
        assertMetamorphicRelationFor("<img src=\"https://images.example.test/a.png\" alt=\"sample\">",
            "https://example.test/root/page.html", Safelist.basicWithImages());
    }

    @Test
    void basic_with_images_relative_image_variation1() {
        assertMetamorphicRelationFor("<img src=\"images/a.png\" alt=\"sample\">",
            "https://example.test/assets/", Safelist.basicWithImages());
    }

    @Test
    void basic_with_images_data_image_variation1() {
        assertMetamorphicRelationFor("<img src=\"data:image/png;base64,AA==\" alt=\"inline\">",
            "", Safelist.basicWithImages());
    }

    @Test
    void basic_with_images_scripted_image_url_variation1() {
        assertMetamorphicRelationFor("<img src=\"javascript:alert(1)\" alt=\"bad\">caption",
            "https://example.test/root/page.html", Safelist.basicWithImages());
    }

    @Test
    void relaxed_headings_lists_and_paragraphs_variation1() {
        assertMetamorphicRelationFor("<h2>Heading</h2><p>Intro</p><ul><li>one</li><li>two</li></ul>",
            "", Safelist.relaxed());
    }

    @Test
    void relaxed_table_structure_variation1() {
        assertMetamorphicRelationFor(
            "<table><thead><tr><th>A</th><th>B</th></tr></thead><tbody><tr><td>1</td><td>2</td></tr></tbody></table>",
            "https://example.test/root/page.html", Safelist.relaxed());
    }

    @Test
    void relaxed_blockquote_and_code_variation1() {
        assertMetamorphicRelationFor("<blockquote><p>Quoted <code>x &lt; y</code></p></blockquote>",
            "", Safelist.relaxed());
    }

    @Test
    void relaxed_protocol_relative_link_variation1() {
        assertMetamorphicRelationFor("<a href=\"//cdn.example.test/lib.js\">cdn</a>",
            "https://example.test/root/page.html", Safelist.relaxed());
    }

    @Test
    void relaxed_mailto_link_variation1() {
        assertMetamorphicRelationFor("<a href=\"mailto:user@example.test\">email</a>",
            "", Safelist.relaxed());
    }

    @Test
    void relaxed_custom_unknown_element_variation1() {
        assertMetamorphicRelationFor("<custom-card><p>inside</p></custom-card>",
            "https://example.test/root/page.html", Safelist.relaxed());
    }

    @Test
    void relaxed_comment_and_declaration_variation1() {
        assertMetamorphicRelationFor("<!doctype html><!-- hidden --><p>visible</p>",
            "", Safelist.relaxed());
    }

    @Test
    void relaxed_style_and_visible_text_variation1() {
        assertMetamorphicRelationFor("<style>body{display:none}</style><p>visible</p>",
            "https://example.test/root/page.html", Safelist.relaxed());
    }

    @Test
    void relaxed_form_controls_variation1() {
        assertMetamorphicRelationFor(
            "<form action=\"https://example.test/post\"><input type=\"text\" value=\"x\"><button>Send</button></form>",
            "", Safelist.relaxed());
    }

    @Test
    void default_safe_list_empty_policy_variation1() {
        assertMetamorphicRelationFor("<p>visible <b>text</b></p>",
            "", new Safelist());
    }

    @Test
    void default_safe_list_url_attribute_variation1() {
        assertMetamorphicRelationFor("<a href=\"https://example.test/\" onclick=\"x()\">text</a>",
            "https://example.test/root/page.html", new Safelist());
    }

    @Test
    void default_safe_list_empty_base_relative_url_variation1() {
        assertMetamorphicRelationFor("<img src=\"relative.png\" alt=\"image\">text",
            "", new Safelist());
    }
}
