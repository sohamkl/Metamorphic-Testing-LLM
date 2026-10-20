import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static void verifyIdempotence(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONE_EMPTY_FRAGMENT_variation1() {
        verifyIdempotence("", "", Safelist.none());
    }

    @Test
    void NONE_PLAIN_TEXT_variation1() {
        verifyIdempotence("plain text 123", "https://example.test/", Safelist.none());
    }

    @Test
    void NONE_MARKUP_WITH_TEXT_variation1() {
        verifyIdempotence("<p>Hello <strong>world</strong></p>", "https://example.test/", Safelist.none());
    }

    @Test
    void NONE_ESCAPED_ENTITY_TEXT_variation1() {
        verifyIdempotence("5 is &lt; 6 &amp; 7 is &gt; 3", "", Safelist.none());
    }

    @Test
    void NONE_UNSAFE_ELEMENT_WITH_FALLBACK_TEXT_variation1() {
        verifyIdempotence("before<script>alert(1)</script>after", "https://example.test/", Safelist.none());
    }

    @Test
    void BASIC_SIMPLE_FORMATTING_variation1() {
        verifyIdempotence("<p>Alpha <strong>Beta</strong> <em>Gamma</em></p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_SCRIPT_REMOVAL_variation1() {
        verifyIdempotence("<p>safe</p><script>window.evil=true</script><p>tail</p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_EVENT_HANDLER_REMOVAL_variation1() {
        verifyIdempotence("<p onclick=\"alert(1)\">clickable text</p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_HTTPS_LINK_variation1() {
        verifyIdempotence("<a href=\"https://safe.example/path?q=1#part\">link text</a>",
                "https://base.example/root/", Safelist.basic());
    }

    @Test
    void BASIC_JAVASCRIPT_LINK_variation1() {
        verifyIdempotence("<a href=\"javascript:alert(1)\">unsafe link text</a>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_WITH_ROOT_BASE_variation1() {
        verifyIdempotence("<a href=\"docs/page.html\">relative link</a>",
                "https://example.test/root/", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_WITH_EMPTY_BASE_variation1() {
        verifyIdempotence("<a href=\"docs/page.html\">relative link</a>", "", Safelist.basic());
    }

    @Test
    void BASIC_IMAGE_NOT_IN_BASIC_PROFILE_variation1() {
        verifyIdempotence("<p>before</p><img src=\"https://images.example/a.png\" alt=\"picture\"><p>after</p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_PARENT_RELATIVE_LINK_variation1() {
        verifyIdempotence("<a href=\"../up/item.html\">up one level</a>",
                "https://example.test/a/b/page.html", Safelist.basic());
    }

    @Test
    void BASIC_WITH_IMAGES_SAFE_IMAGE_variation1() {
        verifyIdempotence("<img src=\"https://images.example/photo.png\" alt=\"photo\">caption",
                "https://example.test/", Safelist.basicWithImages());
    }

    @Test
    void BASIC_WITH_IMAGES_JAVASCRIPT_IMAGE_SOURCE_variation1() {
        verifyIdempotence("<img src=\"javascript:alert(1)\" alt=\"unsafe image\">caption",
                "https://example.test/", Safelist.basicWithImages());
    }

    @Test
    void BASIC_WITH_IMAGES_EMPTY_BASE_RELATIVE_IMAGE_variation1() {
        verifyIdempotence("<img src=\"images/photo.png\" alt=\"relative image\">caption",
                "", Safelist.basicWithImages());
    }

    @Test
    void RELAXED_TABLE_STRUCTURE_variation1() {
        verifyIdempotence("<table><thead><tr><th>H</th></tr></thead><tbody><tr><td>C</td></tr></tbody></table>",
                "https://example.test/", Safelist.relaxed());
    }

    @Test
    void RELAXED_HEADINGS_LIST_AND_BLOCKQUOTE_variation1() {
        verifyIdempotence("<h1>Heading</h1><blockquote><ol><li>one</li><li>two</li></ol></blockquote>",
                "https://example.test/", Safelist.relaxed());
    }

    @Test
    void RELAXED_DATA_PROTOCOL_LINK_variation1() {
        verifyIdempotence("<a href=\"data:text/html,alert(1)\">data link</a>",
                "https://example.test/", Safelist.relaxed());
    }

    @Test
    void SIMPLE_TEXT_INLINE_FORMATTING_variation1() {
        verifyIdempotence("<b>bold</b> <i>italic</i> <u>underlined</u>",
                "https://example.test/", Safelist.simpleText());
    }

    @Test
    void SIMPLE_TEXT_BLOCK_AND_LINK_variation1() {
        verifyIdempotence("<p>paragraph</p><a href=\"https://example.test/\">link</a>",
                "https://base.example/", Safelist.simpleText());
    }

    @Test
    void DEFAULT_CONSTRUCTED_SAFELIST_variation1() {
        verifyIdempotence("<p>text</p><a href=\"https://example.test/\">link</a><script>alert(1)</script>",
                "https://base.example/", new Safelist());
    }

    @Test
    void COPY_CONSTRUCTED_RELAXED_SAFELIST_variation1() {
        verifyIdempotence("<p>copy profile</p><table><tr><td>cell</td></tr></table>",
                "https://example.test/", new Safelist(Safelist.relaxed()));
    }

    @Test
    void BASIC_COMMENT_AND_VISIBLE_TEXT_variation1() {
        verifyIdempotence("before<!-- hidden comment --><p>after</p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_DOCUMENT_STRUCTURE_IN_BODY_FRAGMENT_variation1() {
        verifyIdempotence("<!doctype html><html><head><title>T</title></head><body><p>body text</p></body></html>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_UNCLOSED_TAGS_variation1() {
        verifyIdempotence("<p>open <strong>nested", "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_MISNESTED_FORMATTING_variation1() {
        verifyIdempotence("<p><strong>bold <em>both</strong> italic</em></p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_MIXED_CASE_TAGS_AND_ATTRIBUTES_variation1() {
        verifyIdempotence("<A HREF=\"https://example.test/X\">Mixed Case Link</A>",
                "https://base.example/", Safelist.basic());
    }

    @Test
    void BASIC_DUPLICATE_HREF_ATTRIBUTES_variation1() {
        verifyIdempotence("<a href=\"https://safe.example/\" href=\"javascript:alert(1)\">duplicate attribute link</a>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void NONE_UNICODE_AND_ENTITY_SERIALIZATION_variation1() {
        verifyIdempotence("café ☕ &amp; \"quotes\" &lt;tag&gt;",
                "https://example.test/", Safelist.none());
    }

    @Test
    void BASIC_CHARACTER_REFERENCE_OBFUSCATED_JAVASCRIPT_variation1() {
        verifyIdempotence("<a href=\"java&#x73;cript:alert(1)\">obfuscated protocol</a>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_WHITESPACE_OBFUSCATED_JAVASCRIPT_variation1() {
        verifyIdempotence("<a href=\"  javascript:alert(1)\">whitespace protocol</a>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_FRAGMENT_ONLY_LINK_variation1() {
        verifyIdempotence("<a href=\"#section-1\">fragment link</a>", "", Safelist.basic());
    }

    @Test
    void RELAXED_DEEP_NESTING_variation1() {
        verifyIdempotence("<div><div><div><div><div><p><strong><em>depth-8 text</em></strong></p></div></div></div></div></div>",
                "https://example.test/", Safelist.relaxed());
    }

    @Test
    void NONE_LONG_TEXT_BOUNDARY_variation1() {
        verifyIdempotence("x".repeat(4096), "", Safelist.none());
    }

    @Test
    void BASIC_DIRECTORY_BASE_RELATIVE_LINK_variation1() {
        verifyIdempotence("<a href=\"child/page.html\">directory-relative link</a>",
                "https://example.test/dir/subdir/", Safelist.basic());
    }

    @Test
    void RELAXED_UNSAFE_EMBED_WITH_TEXT_variation1() {
        verifyIdempotence("<p>before</p><iframe src=\"https://evil.example/\"></iframe><p>after</p>",
                "https://example.test/", Safelist.relaxed());
    }

    @Test
    void SIMPLE_TEXT_EMPTY_BASE_LINK_variation1() {
        verifyIdempotence("<a href=\"relative\">simple text link</a>", "", Safelist.simpleText());
    }
}
