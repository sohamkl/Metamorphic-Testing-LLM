import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private void verify(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_FRAGMENT_NONE_emptyBody() {
        verify("", "", Safelist.none());
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_NONE_mixedWhitespace() {
        verify(" \t\r\n  ", "", Safelist.none());
    }

    @Test
    public void PLAIN_TEXT_NONE_plainText() {
        verify("plain text", "", Safelist.none());
    }

    @Test
    public void TEXT_WITH_HTML_METACHARACTERS_NONE_literalMetacharacters() {
        verify("5 < 6 & 7 > 3", "", Safelist.none());
    }

    @Test
    public void PREESCAPED_ENTITY_TEXT_NONE_entities() {
        verify("5 is &lt; 6 &amp; 7", "", Safelist.none());
    }

    @Test
    public void FORMATTING_TAG_NONE_removedElementText() {
        verify("<b>retained text</b>", "", Safelist.none());
    }

    @Test
    public void NESTED_FORMATTING_NONE_nestedElements() {
        verify("<b><i><em>nested text</em></i></b>", "", Safelist.none());
    }

    @Test
    public void UNSAFE_TAG_ONLY_NONE_scriptOnly() {
        verify("<script>alert(1)</script>", "", Safelist.none());
    }

    @Test
    public void UNSAFE_TAG_WITH_SURROUNDING_TEXT_NONE_surroundingText() {
        verify("before<script>alert(1)</script>after", "", Safelist.none());
    }

    @Test
    public void COMMENT_WITH_TEXT_NONE_commentAdjacentText() {
        verify("before<!-- hidden -->after", "", Safelist.none());
    }

    @Test
    public void DOCTYPE_AND_TEXT_NONE_declarationAndText() {
        verify("<!doctype html>body text", "", Safelist.none());
    }

    @Test
    public void MALFORMED_UNCLOSED_TAG_NONE_unclosedTags() {
        verify("<p>one<b>two", "", Safelist.none());
    }

    @Test
    public void MISNESTED_TAGS_NONE_misnestedFormatting() {
        verify("<b>one<i>two</b>three</i>", "", Safelist.none());
    }

    @Test
    public void COMPLETE_DOCUMENT_MARKUP_NONE_documentLikeFragment() {
        verify("<html><head><title>x</title></head><body>body text</body></html>", "", Safelist.none());
    }

    @Test
    public void DEFAULT_SAFELIST_TEXT_directConstructorText() {
        verify("default safelist text", "", new Safelist());
    }

    @Test
    public void DEFAULT_SAFELIST_MARKUP_directConstructorMarkup() {
        verify("<b>default policy</b>", "https://example.test/", new Safelist());
    }

    @Test
    public void COPIED_SAFELIST_MARKUP_copyConstructedBasic() {
        verify("<p><strong>copied policy</strong></p>",
                "https://example.test/",
                new Safelist(Safelist.basic()));
    }

    @Test
    public void SIMPLE_TEXT_FORMATTING_multipleFormattingTags() {
        verify("<b>bold</b><i>italic</i><u>underlined</u>", "", Safelist.simpleText());
    }

    @Test
    public void SIMPLE_TEXT_LINK_ATTRIBUTE_linkCandidate() {
        verify("<a href=\"https://example.test/path\">link text</a>",
                "https://base.test/",
                Safelist.simpleText());
    }

    @Test
    public void BASIC_PARAGRAPH_AND_FORMATTING_nestedBasicMarkup() {
        verify("<p>paragraph <strong>emphasis</strong></p>", "", Safelist.basic());
    }

    @Test
    public void BASIC_UNSAFE_ATTRIBUTE_eventAttribute() {
        verify("<p onclick=\"alert(1)\">clickable text</p>",
                "https://example.test/",
                Safelist.basic());
    }

    @Test
    public void BASIC_ABSOLUTE_HTTP_LINK_httpUrl() {
        verify("<a href=\"http://example.test/a\">http link</a>",
                "https://base.test/root/",
                Safelist.basic());
    }

    @Test
    public void BASIC_ABSOLUTE_HTTPS_LINK_httpsUrl() {
        verify("<a href=\"https://example.test/a\">https link</a>",
                "http://base.test/root/",
                Safelist.basic());
    }

    @Test
    public void BASIC_JAVASCRIPT_SCHEME_LINK_unsafeProtocol() {
        verify("<a href=\"javascript:alert(1)\">unsafe link text</a>",
                "https://base.test/",
                Safelist.basic());
    }

    @Test
    public void BASIC_DATA_SCHEME_LINK_dataProtocol() {
        verify("<a href=\"data:text/html,hello\">data link text</a>",
                "https://base.test/",
                Safelist.basic());
    }

    @Test
    public void BASIC_PROTOCOL_RELATIVE_LINK_protocolRelativeUrl() {
        verify("<a href=\"//cdn.example.test/lib.js\">protocol relative</a>",
                "https://base.test/path/",
                Safelist.basic());
    }

    @Test
    public void BASIC_ROOT_RELATIVE_LINK_rootRelativeUrl() {
        verify("<a href=\"/asset\">root relative</a>",
                "https://base.test/root/page.html",
                Safelist.basic());
    }

    @Test
    public void BASIC_PATH_RELATIVE_LINK_pathRelativeUrl() {
        verify("<a href=\"child/page.html\">path relative</a>",
                "https://base.test/dir/current.html",
                Safelist.basic());
    }

    @Test
    public void BASIC_FRAGMENT_ONLY_LINK_fragmentReference() {
        verify("<a href=\"#section\">fragment link</a>",
                "https://base.test/dir/page.html?x=1#old",
                Safelist.basic());
    }

    @Test
    public void BASIC_EMPTY_BASE_RELATIVE_LINK_emptyBase() {
        verify("<a href=\"child\">empty-base relative</a>", "", Safelist.basic());
    }

    @Test
    public void PRESERVE_RELATIVE_EMPTY_BASE_SENTINEL_preservedRelativeLink() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<a href=\"child\">preserved relative candidate</a>", "", safelist);
    }

    @Test
    public void PRESERVE_RELATIVE_NONEMPTY_BASE_preservedRelativeWithBase() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<a href=\"child\">nonempty-base relative candidate</a>",
                "https://base.test/dir/",
                safelist);
    }

    @Test
    public void NON_PRESERVE_EMPTY_BASE_relativeLinkWithoutPreservation() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        verify("<a href=\"child\">non-preserved relative candidate</a>", "", safelist);
    }

    @Test
    public void BASIC_DUPLICATE_ATTRIBUTES_duplicateHref() {
        verify("<a href=\"https://first.test/\" href=\"javascript:alert(1)\">duplicate attrs</a>",
                "https://base.test/",
                Safelist.basic());
    }

    @Test
    public void BASIC_MIXED_CASE_NAMES_mixedCaseTagAndAttribute() {
        verify("<A HREF=\"https://example.test/\">mixed case</A>",
                "https://base.test/",
                Safelist.basic());
    }

    @Test
    public void BASIC_WHITESPACE_OBFUSCATED_URL_surroundingUrlWhitespace() {
        verify("<a href=\"  https://example.test/path  \">spaced URL</a>",
                "https://base.test/",
                Safelist.basic());
    }

    @Test
    public void BASIC_NON_ABSOLUTE_BASE_URI_nonAbsoluteBase() {
        verify("<a href=\"child\">nonabsolute base</a>",
                "not an absolute URI",
                Safelist.basic());
    }

    @Test
    public void BASIC_WITH_IMAGES_IMAGE_URL_absoluteImageSource() {
        verify("<img src=\"https://images.example.test/p.png\" alt=\"picture\">",
                "https://base.test/",
                Safelist.basicWithImages());
    }

    @Test
    public void BASIC_WITH_IMAGES_RELATIVE_IMAGE_relativeImageSource() {
        verify("<img src=\"images/p.png\" alt=\"relative picture\">",
                "https://base.test/articles/page.html",
                Safelist.basicWithImages());
    }

    @Test
    public void BASIC_WITH_IMAGES_SCRIPT_SCHEME_IMAGE_unsafeImageSource() {
        verify("<img src=\"javascript:alert(1)\" alt=\"unsafe image\">",
                "https://base.test/",
                Safelist.basicWithImages());
    }

    @Test
    public void RELAXED_STRUCTURAL_FRAGMENT_structuralMarkup() {
        verify("<div><h1>Heading</h1><p>paragraph</p><blockquote>quote</blockquote></div>",
                "",
                Safelist.relaxed());
    }

    @Test
    public void RELAXED_TABLE_FRAGMENT_tableMarkup() {
        verify("<table><tr><th>H</th><td>C</td></tr></table>", "", Safelist.relaxed());
    }

    @Test
    public void RELAXED_LIST_FRAGMENT_nestedList() {
        verify("<ul><li>one</li><li>two<ul><li>nested</li></ul></li></ul>",
                "",
                Safelist.relaxed());
    }

    @Test
    public void RELAXED_MIXED_SAFE_AND_UNSAFE_mixedContent() {
        verify("<p onclick=\"x()\">visible <em>content</em></p><script>bad()</script><div>tail</div>",
                "https://base.test/",
                Safelist.relaxed());
    }

    @Test
    public void RELAXED_DEEP_NESTING_deeplyNestedLeafText() {
        verify("<div><div><div><p><span><strong>deep leaf</strong></span></p></div></div></div>",
                "https://base.test/",
                Safelist.relaxed());
    }
}
