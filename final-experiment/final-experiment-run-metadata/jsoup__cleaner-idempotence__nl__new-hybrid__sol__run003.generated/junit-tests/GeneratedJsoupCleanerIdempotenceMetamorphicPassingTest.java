import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source(
            String bodyHtml, String baseUri, Safelist safelist) {
        return new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(bodyHtml, baseUri, safelist);
    }

    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input generateFollowUp(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input original, String sourceOutput) {
        return new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(
                sourceOutput, original.arg1(), original.arg2());
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source) {
        String sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(source);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input followUp =
                generateFollowUp(source, sourceOutput);
        String followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static String deeplyNestedFragment() {
        StringBuilder html = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            html.append("<b><custom-box>");
        }
        html.append("deep sentinel");
        for (int i = 0; i < 16; i++) {
            html.append("</custom-box></b>");
        }
        return html.toString();
    }

    private static String longMixedFragment() {
        String unit =
                "<p class='kept' onclick='bad()'>Long safe text &amp; data</p>"
                        + "<a href='https://safe.example/items?a=1&amp;b=2'>safe link</a>"
                        + "<a href='javascript:alert(1)'>unsafe link</a>"
                        + "<img src='https://safe.example/image.png' alt='safe image'>"
                        + "<img src='javascript:alert(2)' alt='unsafe image'>"
                        + "<script>window.bad=true;</script><unknown-tag>retained text</unknown-tag>";
        StringBuilder html = new StringBuilder();
        while (html.length() + unit.length() <= 6000) {
            html.append(unit);
        }
        return html.toString();
    }

    @Test
    void EMPTY_FRAGMENT_variation1() {
        assertMetamorphicRelationFor(source("", "", new Safelist()));
    }

    @Test
    void WHITESPACE_ONLY_FRAGMENT_variation1() {
        Safelist safelist = new Safelist(Safelist.simpleText());
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(source(" \t\r\n  \n", "http://example.test/base/", safelist));
    }

    @Test
    void PLAIN_ASCII_TEXT_variation1() {
        Safelist safelist = Safelist.none();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(
                source("Ordinary plain ASCII text 123.", "https://example.test/", safelist));
    }

    @Test
    void ENTITY_AND_META_CHARACTER_TEXT_variation1() {
        assertMetamorphicRelationFor(source(
                "Entity text: &copy; &#169; &#x1F642; and raw & plus 2 < 3 > 1.",
                "urn:example:entity",
                Safelist.none()));
    }

    @Test
    void UNICODE_AND_NUL_TEXT_variation1() {
        Safelist safelist = Safelist.none();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(
                source("before-é\u0000after-\uD83D\uDE42", "C:/html/input.html", safelist));
    }

    @Test
    void NONE_REMOVES_WRAPPER_RETAINS_TEXT_variation1() {
        Safelist safelist = Safelist.none();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(
                source("<section><p>Retained ordinary text</p></section>", "", safelist));
    }

    @Test
    void SCRIPT_STYLE_WITH_TEXT_SENTINEL_variation1() {
        String html =
                "visible-before<script>alert('unsafe')</script>"
                        + "<style>body{display:none}</style>visible-after";
        assertMetamorphicRelationFor(source(html, "", Safelist.basic()));
    }

    @Test
    void COMMENT_AND_DOCTYPE_FRAGMENT_variation1() {
        Safelist safelist = new Safelist();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(source(
                "<!doctype html><!-- hidden comment -->Visible fragment text",
                "http://example.test/comment/",
                safelist));
    }

    @Test
    void SIMPLE_TEXT_FORMATTING_variation1() {
        Safelist safelist = new Safelist(Safelist.simpleText());
        assertMetamorphicRelationFor(source(
                "<strong>outer <em>inner <u>formatted</u></em></strong>",
                "https://example.test/",
                safelist));
    }

    @Test
    void SIMPLE_TEXT_REJECTS_BLOCK_WRAPPER_variation1() {
        assertMetamorphicRelationFor(source(
                "<div>Block text with <strong>permitted emphasis</strong></div>",
                "file:/tmp/document.html",
                Safelist.simpleText()));
    }

    @Test
    void BASIC_PARAGRAPH_AND_LIST_variation1() {
        String html =
                "<p>Opening paragraph</p><ul><li>first item</li><li>second item</li></ul>";
        assertMetamorphicRelationFor(source(html, "/local/base/path", Safelist.basic()));
    }

    @Test
    void BASIC_ABSOLUTE_HTTPS_LINK_variation1() {
        String html = "<a href='https://example.test/docs/start'>Secure documentation</a>";
        assertMetamorphicRelationFor(source(html, "", Safelist.basic()));
    }

    @Test
    void RELATIVE_LINK_RESOLVED_variation1() {
        Safelist safelist = Safelist.basic();
        safelist.preserveRelativeLinks(false);
        assertMetamorphicRelationFor(source(
                "<a href='../guide/page.html'>Resolved guide</a>",
                "https://example.test/products/current/",
                safelist));
    }

    @Test
    void RELATIVE_LINK_PRESERVED_WITH_BASE_variation1() {
        Safelist safelist = Safelist.basic();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(source(
                "<a href='./chapter-one.html?mode=full'>Relative chapter</a>",
                "http://example.test/books/",
                safelist));
    }

    @Test
    void EMPTY_BASE_PRESERVE_SENTINEL_variation1() {
        Safelist safelist = Safelist.basic();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(
                source("<a href='docs/index.html'>Preserved docs</a>", "", safelist));
    }

    @Test
    void EMPTY_BASE_WITHOUT_PRESERVATION_variation1() {
        Safelist safelist = Safelist.basic();
        safelist.preserveRelativeLinks(false);
        assertMetamorphicRelationFor(
                source("<a href='relative/page.html'>Link text remains</a>", "", safelist));
    }

    @Test
    void MAILTO_LINK_PROTOCOL_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='mailto:developer@example.test?subject=Hello'>Send mail</a>",
                "/path/unused",
                Safelist.basic()));
    }

    @Test
    void JAVASCRIPT_URL_REMOVED_variation1() {
        assertMetamorphicRelationFor(source(
                "<a href='javascript:alert(1)'>Unsafe destination</a>",
                "",
                Safelist.basic()));
    }

    @Test
    void OBFUSCATED_UNSAFE_PROTOCOL_variation1() {
        String html = "<a href='JaVaScRiPt&#58;alert(1)'>Obfuscated unsafe link</a>";
        assertMetamorphicRelationFor(source(html, "", Safelist.basic()));
    }

    @Test
    void PROTOCOL_RELATIVE_URL_variation1() {
        Safelist safelist = Safelist.basic();
        safelist.preserveRelativeLinks(false);
        assertMetamorphicRelationFor(source(
                "<a href='//cdn.example.test/library/file.js'>CDN resource</a>",
                "http://origin.example.test/app/",
                safelist));
    }

    @Test
    void BASIC_ENFORCED_LINK_ATTRIBUTE_variation1() {
        String html = "<div><p><a href='https://example.test/account'>Account</a></p></div>";
        assertMetamorphicRelationFor(
                source(html, "https://example.test/root/", Safelist.basic()));
    }

    @Test
    void UNSAFE_EVENT_AND_STYLE_ATTRIBUTES_variation1() {
        String html =
                "<p onclick='runAttack()' style='position:fixed'>Safe paragraph text</p>";
        assertMetamorphicRelationFor(
                source(html, "urn:example:attributes", Safelist.relaxed()));
    }

    @Test
    void DUPLICATE_ATTRIBUTES_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        String html =
                "<a href='https://first.example.test/' "
                        + "href='javascript:alert(1)'>Duplicate href link</a>";
        assertMetamorphicRelationFor(source(html, "/duplicate/base", safelist));
    }

    @Test
    void BASIC_WITH_IMAGES_SAFE_IMAGE_variation1() {
        String html =
                "Before image <img src='https://images.example.test/a.png' "
                        + "alt='descriptive image'> after image";
        assertMetamorphicRelationFor(source(html, "", Safelist.basicWithImages()));
    }

    @Test
    void IMAGE_UNSAFE_SOURCE_PROTOCOL_variation1() {
        String html =
                "Before <img src='javascript:alert(1)' alt='unsafe source'> after";
        assertMetamorphicRelationFor(source(html, "", Safelist.basicWithImages()));
    }

    @Test
    void IMAGE_RELATIVE_SOURCE_variation1() {
        Safelist safelist = Safelist.basicWithImages();
        safelist.preserveRelativeLinks(false);
        String html = "Photo: <img src='../media/photo.png' alt='relative photo'> end";
        assertMetamorphicRelationFor(
                source(html, "http://example.test/articles/2026/", safelist));
    }

    @Test
    void RELAXED_TABLE_STRUCTURE_variation1() {
        String html =
                "<table><thead><tr><th>Name</th><th>Value</th></tr></thead>"
                        + "<tbody><tr><td>Alpha</td><td>42</td></tr></tbody></table>";
        assertMetamorphicRelationFor(
                source(html, "https://example.test/tables/", Safelist.relaxed()));
    }

    @Test
    void RELAXED_DIV_AND_HEADINGS_variation1() {
        String html =
                "<div><h1>Main heading</h1><h3>Subheading</h3>"
                        + "<p>Non-empty paragraph content.</p></div>";
        assertMetamorphicRelationFor(
                source(html, "urn:example:structure", Safelist.relaxed()));
    }

    @Test
    void FORM_CONTROLS_REMOVED_variation1() {
        String html =
                "Before form <form action='/submit'><label>Name"
                        + "<input name='user' value='unsafe'></label>"
                        + "<button>Submit</button></form> after form";
        assertMetamorphicRelationFor(source(html, "C:/forms/input.html", Safelist.basic()));
    }

    @Test
    void UNCLOSED_ELEMENTS_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        String html = "<div><p>Paragraph <strong>unclosed strong <em>nested emphasis";
        assertMetamorphicRelationFor(source(html, "", safelist));
    }

    @Test
    void MISNESTED_FORMATTING_variation1() {
        String html = "<strong>bold <em>bold italic</strong> italic tail</em>";
        assertMetamorphicRelationFor(source(html, "", Safelist.simpleText()));
    }

    @Test
    void TABLE_FOSTER_PARENTING_variation1() {
        String html =
                "<table><tr><td>Cell one</td></tr>"
                        + "<p>Irregular paragraph inside table</p>"
                        + "<tr><td>Cell two</td></tr></table>";
        assertMetamorphicRelationFor(
                source(html, "http://example.test/table/", Safelist.relaxed()));
    }

    @Test
    void FULL_DOCUMENT_AS_BODY_FRAGMENT_variation1() {
        String html =
                "<html><head><title>Fragment title</title></head>"
                        + "<body><h1>Body heading</h1><p>Visible body text</p></body></html>";
        assertMetamorphicRelationFor(
                source(html, "https://example.test/document/", Safelist.relaxed()));
    }

    @Test
    void BASE_ELEMENT_RESOLUTION_variation1() {
        String html =
                "<base href='https://assets.example.test/docs/'>"
                        + "<a href='chapter.html'>Chapter through base</a>";
        Safelist safelist = Safelist.basic();
        safelist.preserveRelativeLinks(false);
        assertMetamorphicRelationFor(source(html, "", safelist));
    }

    @Test
    void LINKS_AROUND_BASE_ELEMENT_variation1() {
        String html =
                "<a href='before.html'>Before base</a>"
                        + "<base href='https://declared.example.test/new/'>"
                        + "<a href='after.html'>After base</a>";
        assertMetamorphicRelationFor(
                source(html, "https://initial.example.test/original/", Safelist.basic()));
    }

    @Test
    void ATTRIBUTE_ENTITY_ESCAPING_variation1() {
        String html =
                "<a href='https://example.test/search?a=1&amp;b=2&#38;c=3'>Query link</a>";
        assertMetamorphicRelationFor(source(html, "", Safelist.basic()));
    }

    @Test
    void QUOTED_ATTRIBUTE_NORMALIZATION_variation1() {
        Safelist safelist = new Safelist(Safelist.basic());
        String html =
                "<a href='https://example.test/item' "
                        + "onclick=\"attack()\" id=unquoted>Normalized attributes</a>";
        assertMetamorphicRelationFor(source(html, "", safelist));
    }

    @Test
    void INCOMPLETE_TAG_OPENER_variation1() {
        Safelist safelist = Safelist.none();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(
                source("Leading ordinary text followed by <spa", "http://example.test/", safelist));
    }

    @Test
    void UNKNOWN_CUSTOM_ELEMENTS_variation1() {
        String html =
                "<outer-widget>Outer text <inner-gadget>"
                        + "<strong>permitted child</strong></inner-gadget></outer-widget>";
        assertMetamorphicRelationFor(
                source(html, "https://example.test/widgets/", Safelist.simpleText()));
    }

    @Test
    void DEEP_NESTING_variation1() {
        assertMetamorphicRelationFor(
                source(deeplyNestedFragment(), "urn:example:deep", Safelist.basic()));
    }

    @Test
    void LONG_MIXED_FRAGMENT_variation1() {
        Safelist safelist = Safelist.basicWithImages();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(
                source(longMixedFragment(), "C:/large/fragments/base.html", safelist));
    }

    @Test
    void ALREADY_CLEAN_CANONICAL_FRAGMENT_variation1() {
        String html =
                "<div><h2>Canonical heading</h2><p>Clean paragraph with "
                        + "<em>emphasis</em>.</p><ul><li>One</li><li>Two</li></ul></div>";
        assertMetamorphicRelationFor(
                source(html, "https://example.test/canonical/", Safelist.relaxed()));
    }

    @Test
    void DEFAULT_CONSTRUCTED_SAFELIST_variation1() {
        Safelist safelist = new Safelist();
        String html = "<section><p>Ordinary retained text</p></section>";
        assertMetamorphicRelationFor(
                source(html, "http://example.test/default/", safelist));
    }

    @Test
    void EMPTY_ELEMENTS_AND_LINE_BREAKS_variation1() {
        String html = "first line<br>second line<br/>third line";
        assertMetamorphicRelationFor(
                source(html, "https://example.test/lines/", Safelist.basic()));
    }

    @Test
    void MULTIPLE_TOP_LEVEL_NODES_variation1() {
        String html =
                "alpha <p>bravo</p><script>discard()</script>"
                        + "<strong>charlie</strong> delta <custom>echo</custom>";
        assertMetamorphicRelationFor(
                source(html, "urn:example:siblings", Safelist.basic()));
    }

    @Test
    void NESTED_UNSAFE_PARENT_SAFE_CHILD_variation1() {
        String html =
                "<custom-shell data-x='1'><p>Safe paragraph with "
                        + "<strong>safe child</strong></p></custom-shell>";
        assertMetamorphicRelationFor(
                source(html, "/nested/unsafe/parent", Safelist.basic()));
    }

    @Test
    void MIXED_SAFE_RELATIVE_AND_UNSAFE_LINKS_variation1() {
        String html =
                "<a href='https://safe.example.test/a'>absolute safe</a>"
                        + "<a href='../relative/page'>relative safe</a>"
                        + "<a href='javascript:alert(1)'>unsafe script</a>";
        Safelist safelist = Safelist.basic();
        safelist.preserveRelativeLinks(false);
        assertMetamorphicRelationFor(
                source(html, "https://origin.example.test/path/current/", safelist));
    }

    @Test
    void NONSTANDARD_BASE_WITHOUT_URL_ATTRIBUTES_variation1() {
        String html =
                "<div><h2>Résumé \uD83D\uDE80</h2>"
                        + "<p>Formatted <em>Unicode text</em> without URL attributes.</p></div>";
        assertMetamorphicRelationFor(
                source(html, "C:/offline/documents/page.html", Safelist.relaxed()));
    }

    @Test
    void EMPTY_RESULT_FROM_UNSAFE_ONLY_INPUT_variation1() {
        String html =
                "<script>window.attack=true;</script>"
                        + "<style>body{display:none}</style><!-- removed comment -->";
        Safelist safelist = new Safelist();
        safelist.preserveRelativeLinks(true);
        assertMetamorphicRelationFor(
                source(html, "http://example.test/unsafe-only/", safelist));
    }
}
