import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static String createDeeplyNestedFragment() {
        StringBuilder html = new StringBuilder(6000);
        for (int i = 0; i < 80; i++) {
            html.append(i % 2 == 0 ? "<div>" : "<section>");
        }
        html.append("deepest-visible-text-");
        for (int i = 0; i < 4300; i++) {
            html.append((char) ('a' + (i % 26)));
        }
        for (int i = 79; i >= 0; i--) {
            html.append(i % 2 == 0 ? "</div>" : "</section>");
        }
        return html.toString();
    }

    private static void exercise(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);

        Object[] followUp =
                CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);

        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning the follow-up HTML must produce exactly the source output");
        }
    }

    @Test
    public void EMPTY_FRAGMENT_variation1() {
        exercise("", "", new Safelist());
    }

    @Test
    public void PLAIN_TEXT_NONE_variation1() {
        Safelist safelist = Safelist.none().preserveRelativeLinks(true);
        exercise("Plain text remains visible.", "http://example.test/", safelist);
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_variation1() {
        exercise(" \t\r\n \n", "https://example.test/path/page.html", Safelist.none());
    }

    @Test
    public void ESCAPED_ENTITY_TEXT_variation1() {
        exercise("&lt; &gt; &amp; &quot;", "not an absolute base", Safelist.none());
    }

    @Test
    public void UNICODE_TEXT_variation1() {
        exercise("Καλημέρα 世界 😀", "", Safelist.none());
    }

    @Test
    public void NUL_IN_TEXT_variation1() {
        String bodyHtml = "left" + (char) 0 + "right";
        exercise(bodyHtml, "http://example.test/", Safelist.none());
    }

    @Test
    public void COMMENT_ONLY_variation1() {
        exercise("<!--marker-->", "https://example.test/a/b", Safelist.relaxed());
    }

    @Test
    public void DOCTYPE_IN_BODY_FRAGMENT_variation1() {
        Safelist safelist = new Safelist().preserveRelativeLinks(true);
        exercise("<!doctype html>visible declaration tail", "odd base value", safelist);
    }

    @Test
    public void SIMPLE_TEXT_ALLOWED_INLINE_TAGS_variation1() {
        Safelist safelist = new Safelist(Safelist.simpleText());
        exercise("<b>bold <i>italic <em>emphasis</em></i></b>", "", safelist);
    }

    @Test
    public void RELAXED_STRUCTURAL_MARKUP_variation1() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        exercise("<h2>Heading</h2><div><p>Paragraph <span>inline</span></p></div>",
                "http://example.test/root/", safelist);
    }

    @Test
    public void DISALLOWED_WRAPPER_RETAINS_TEXT_variation1() {
        exercise("<section onclick=\"bad()\">retained child text</section>",
                "https://example.test/path/index.html", Safelist.simpleText());
    }

    @Test
    public void SCRIPT_ONLY_SENTINEL_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<script>window.hostile = true;</script>", "malformed base", safelist);
    }

    @Test
    public void MIXED_SAFE_AND_UNSAFE_ELEMENTS_variation1() {
        exercise("<p>Safe paragraph</p><script>alert('unsafe')</script>",
                "", Safelist.basicWithImages());
    }

    @Test
    public void MISNESTED_FORMATTING_variation1() {
        Safelist safelist = Safelist.simpleText().preserveRelativeLinks(true);
        exercise("<b><i>x</b>y</i>", "http://example.test/", safelist);
    }

    @Test
    public void UNCLOSED_ALLOWED_ELEMENT_variation1() {
        exercise("<p>paragraph without an explicit closing tag",
                "https://example.test/path/page", Safelist.relaxed());
    }

    @Test
    public void ORPHAN_TABLE_CELLS_variation1() {
        Safelist safelist = new Safelist(Safelist.relaxed()).preserveRelativeLinks(true);
        exercise("<tr><td>orphan cell text</td><td>second cell</td></tr>",
                "strange base", safelist);
    }

    @Test
    public void DUPLICATE_ATTRIBUTES_variation1() {
        exercise("<a href=\"http://first.example/a\" href=\"https://second.example/b\">duplicate</a>",
                "https://base.example/root/", Safelist.basic());
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<p onclick=\"alert(1)\">event handler text</p>",
                "http://example.test/", safelist);
    }

    @Test
    public void HTTP_LINK_ALLOWED_variation1() {
        exercise("<a href=\"http://example.test/resource\">HTTP link</a>",
                "https://base.example/path/", Safelist.basic());
    }

    @Test
    public void HTTPS_LINK_ALLOWED_variation1() {
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        exercise("<a href=\"https://example.test/search?q=one&lang=en#result\">HTTPS link</a>",
                "unusual base", safelist);
    }

    @Test
    public void FTP_LINK_POLICY_PATH_variation1() {
        exercise("<a href=\"ftp://ftp.example.test/pub/file.txt\">FTP resource</a>",
                "", Safelist.basic());
    }

    @Test
    public void MAILTO_LINK_POLICY_PATH_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<a href=\"mailto:user@example.test?subject=Hello\">Send mail</a>",
                "http://example.test/", safelist);
    }

    @Test
    public void JAVASCRIPT_LINK_REJECTED_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        exercise("<a href=\"javascript:alert(1)\">visible unsafe link</a>",
                "https://example.test/path/", safelist);
    }

    @Test
    public void DATA_LINK_REJECTED_variation1() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        exercise("<a href=\"data:text/html,unsafe\">data link text</a>",
                "not://a normal base", safelist);
    }

    @Test
    public void TEL_LINK_POLICY_PATH_variation1() {
        exercise("<a href=\"tel:+1-202-555-0100\">telephone link</a>",
                "", Safelist.basic());
    }

    @Test
    public void ENTITY_ENCODED_PROTOCOL_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<a href=\"jav&#x61;script:alert(1)\">encoded protocol</a>",
                "http://example.test/", safelist);
    }

    @Test
    public void CONTROL_OBFUSCATED_PROTOCOL_variation1() {
        exercise("<a href=\"java&#x09;script:alert(1)\">control protocol</a>",
                "https://example.test/path/page", Safelist.basicWithImages());
    }

    @Test
    public void ROOT_RELATIVE_WITH_BASE_variation1() {
        exercise("<a href=\"/assets/manual.html\">root relative</a>",
                "http://example.test/docs/index.html", Safelist.basic());
    }

    @Test
    public void PARENT_RELATIVE_WITH_PATH_BASE_variation1() {
        exercise("<a href=\"../images/icon.png\">parent relative</a>",
                "https://example.test/docs/guide/page.html", Safelist.basic());
    }

    @Test
    public void PROTOCOL_RELATIVE_URL_variation1() {
        exercise("<a href=\"//cdn.example.test/library.js\">CDN resource</a>",
                "https://example.test/app/index.html", Safelist.basic());
    }

    @Test
    public void FRAGMENT_ONLY_LINK_variation1() {
        exercise("<a href=\"#section-two\">local section</a>",
                "https://example.test/document", Safelist.basic());
    }

    @Test
    public void EMPTY_HREF_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<a href=\"\">empty destination</a>", "malformed base", safelist);
    }

    @Test
    public void MALFORMED_URL_AND_BASE_variation1() {
        exercise("<a href=\"folder name/%ZZ/[item]\">malformed URL text</a>",
                "relative base with spaces/%", Safelist.basic());
    }

    @Test
    public void RELATIVE_LINK_EMPTY_BASE_DEFAULT_MODE_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        exercise("<a href=\"docs/start.html\">relative without base</a>", "", safelist);
    }

    @Test
    public void PRESERVED_RELATIVE_LINK_WITH_BASE_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<a href=\"guide/chapter.html\">preserved relative</a>",
                "https://example.test/docs/index.html", safelist);
    }

    @Test
    public void PRESERVED_RELATIVE_LINK_EMPTY_BASE_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<a href=\"local/page.html\">dummy URI branch</a>", "", safelist);
    }

    @Test
    public void BASE_ELEMENT_IN_FRAGMENT_variation1() {
        exercise("<base href=\"https://assets.example.test/root/\"><a href=\"manual.html\">manual</a>",
                "", Safelist.basic());
    }

    @Test
    public void ENFORCED_ANCHOR_ATTRIBUTE_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<a href=\"https://example.test/page\">enforced attribute link</a>",
                "http://base.example/", safelist);
    }

    @Test
    public void SAFE_IMAGE_BASIC_WITH_IMAGES_variation1() {
        exercise("<img src=\"https://images.example.test/logo.png\" alt=\"company logo\">",
                "https://example.test/path/index.html", Safelist.basicWithImages());
    }

    @Test
    public void UNSAFE_IMAGE_PROTOCOL_variation1() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        exercise("<img src=\"data:image/png;base64,AAAA\" alt=\"unsafe image source\">",
                "malformed base", safelist);
    }

    @Test
    public void RELATIVE_IMAGE_WITH_BASE_variation1() {
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(false);
        exercise("<img src=\"images/photo.jpg\" alt=\"relative photograph\">",
                "https://example.test/gallery/index.html", safelist);
    }

    @Test
    public void RELAXED_TABLE_variation1() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        exercise("<table><thead><tr><th>Key</th><th>Value</th></tr></thead>"
                        + "<tbody><tr><td>A</td><td>One</td></tr></tbody></table>",
                "http://example.test/", safelist);
    }

    @Test
    public void SIMPLE_TEXT_STRIPS_BLOCKS_variation1() {
        exercise("<div>first block</div><p>second block</p>",
                "https://example.test/path/", Safelist.simpleText());
    }

    @Test
    public void NONE_STRIPS_NESTED_MARKUP_variation1() {
        Safelist safelist = new Safelist(Safelist.none()).preserveRelativeLinks(true);
        exercise("<div><p><strong>deep visible text &amp; more</strong></p></div>",
                "unusual base", safelist);
    }

    @Test
    public void NEW_EMPTY_SAFELIST_variation1() {
        exercise("<article><b>visible &amp; escaped text</b></article>",
                "", new Safelist());
    }

    @Test
    public void COPIED_SAFELIST_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        exercise("<p class=\"allowed-or-filtered\" onclick=\"bad()\">"
                        + "<a href=\"https://example.test/\">copied policy</a></p>",
                "http://base.example/", safelist);
    }

    @Test
    public void MIXED_CASE_HTML_AND_PROTOCOL_variation1() {
        exercise("<A HREF=\"HtTpS://Example.Test/Path\">Mixed Case Link</A>",
                "https://base.example/path/index.html", Safelist.basic());
    }

    @Test
    public void LARGE_DEEPLY_NESTED_FRAGMENT_variation1() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        exercise(createDeeplyNestedFragment(), "malformed base value", safelist);
    }

    @Test
    public void VOID_LINE_BREAK_NORMALIZATION_variation1() {
        exercise("first<br>second<br/>third", "", Safelist.relaxed());
    }

    @Test
    public void IMAGE_MIXED_SAFE_AND_UNSAFE_ATTRIBUTES_variation1() {
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        exercise("<img src=\"https://images.example.test/photo.jpg\" alt=\"photo\" "
                        + "onclick=\"alert(1)\" data-private=\"secret\">",
                "http://example.test/root/", safelist);
    }
}
