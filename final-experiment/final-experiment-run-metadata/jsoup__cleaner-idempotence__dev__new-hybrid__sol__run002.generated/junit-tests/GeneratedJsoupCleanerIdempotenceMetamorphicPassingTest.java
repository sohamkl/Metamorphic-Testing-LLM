import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    private static void verify(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);

        Object[] followUp =
                CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);

        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static String nestedDivs(int depth, String text) {
        StringBuilder html = new StringBuilder(depth * 11 + text.length());
        for (int i = 0; i < depth; i++) {
            html.append("<div>");
        }
        html.append(text);
        for (int i = 0; i < depth; i++) {
            html.append("</div>");
        }
        return html.toString();
    }

    private static String repeatedSiblingFragment(int repetitions) {
        StringBuilder html = new StringBuilder(repetitions * 8);
        for (int i = 0; i < repetitions; i++) {
            html.append("<b>x</b>");
        }
        return html.toString();
    }

    @Test
    public void EMPTY_FRAGMENT_variation1_emptyPolicy() {
        verify("", "", new Safelist());
    }

    @Test
    public void PLAIN_TEXT_WITH_NONE_variation1_copiedNonePolicy() {
        Safelist safelist = new Safelist(Safelist.none()).preserveRelativeLinks(true);
        verify("Plain ASCII text survives cleaning.", "http://example.test/source/", safelist);
    }

    @Test
    public void ENTITY_TEXT_WITH_NONE_variation1_namedAndNumericEntities() {
        verify("A &lt; B and C &#38; D.", "https://example.test/entities/", Safelist.none());
    }

    @Test
    public void UNICODE_TEXT_variation1_supplementaryCharacter() {
        Safelist safelist = Safelist.simpleText().preserveRelativeLinks(true);
        verify("Unicode: café, 東京, and \uD83D\uDE03.", "", safelist);
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_variation1_mixedHtmlWhitespace() {
        verify(" \t\r\n  ", "http://example.test/whitespace/", Safelist.basic());
    }

    @Test
    public void COMMENT_ONLY_FRAGMENT_variation1_removedComment() {
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        verify("<!--comment only-->", "https://example.test/comments/", safelist);
    }

    @Test
    public void SIMPLE_TEXT_INLINE_TAG_variation1_boldText() {
        verify("<b>bold</b>", "", Safelist.simpleText());
    }

    @Test
    public void BASIC_BLOCK_TAG_variation1_paragraph() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<p>paragraph</p>", "http://example.test/articles/", safelist);
    }

    @Test
    public void COPIED_POLICY_NESTING_variation1_nestedFormatting() {
        Safelist safelist = new Safelist(Safelist.basic());
        verify("<strong><em>nested text</em></strong>",
                "https://example.test/nesting/", safelist);
    }

    @Test
    public void DISALLOWED_WRAPPER_RETAINS_TEXT_variation1_divWithNone() {
        Safelist safelist = Safelist.none().preserveRelativeLinks(true);
        verify("<div>ordinary child text</div>", "", safelist);
    }

    @Test
    public void MIXED_ALLOWED_AND_DISALLOWED_TAGS_variation1_boldAndDiv() {
        verify("<b>allowed</b><div>unwrapped</div>",
                "http://example.test/mixed/", Safelist.simpleText());
    }

    @Test
    public void SCRIPT_ONLY_SUBTREE_variation1_unsafeScript() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<script src=x src=y>alert('unsafe')</script>",
                "https://example.test/scripts/", safelist);
    }

    @Test
    public void SCRIPT_BETWEEN_SAFE_TEXT_variation1_visibleNeighbors() {
        verify("before<script src=payload.js>alert(1)</script>after",
                "", Safelist.basicWithImages());
    }

    @Test
    public void STYLE_ELEMENT_ONLY_variation1_cssData() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        verify("<style>body { color: red; }", "http://example.test/styles/", safelist);
    }

    @Test
    public void UNKNOWN_TAG_WITH_EMPTY_POLICY_variation1_customElement() {
        verify("<x-widget>custom child text</x-widget>",
                "https://example.test/custom/", new Safelist());
    }

    @Test
    public void UNCLOSED_ALLOWED_TAG_variation1_unclosedBold() {
        Safelist safelist = new Safelist(Safelist.basic()).preserveRelativeLinks(true);
        verify("<b>balanced by parser", "", safelist);
    }

    @Test
    public void MISNESTED_FORMATTING_TAGS_variation1_overlappingBoldItalic() {
        verify("<b>bold <i>italic</b> tail</i>",
                "http://example.test/repair/", Safelist.basic());
    }

    @Test
    public void MIXED_CASE_MARKUP_variation1_uppercaseElementAndAttribute() {
        Safelist safelist = Safelist.simpleText().preserveRelativeLinks(true);
        verify("<B TITLE='ignored'>Mixed Case</B>",
                "https://example.test/case/", safelist);
    }

    @Test
    public void FULL_DOCUMENT_AS_BODY_FRAGMENT_variation1_structuralMarkup() {
        verify("<HTML><HEAD><TITLE>Title</TITLE></HEAD>"
                        + "<BODY><p>surviving body text</p></BODY></HTML>",
                "", Safelist.basic());
    }

    @Test
    public void EMBEDDED_BASE_TAG_variation1_relativeLinkResolution() {
        verify("<base href='https://cdn.example.test/docs/'>"
                        + "<a href='chapter.html'>chapter</a>",
                "", Safelist.basic());
    }

    @Test
    public void DUPLICATE_ATTRIBUTES_variation1_duplicateHref() {
        verify("<a href='http://first.example.test/' "
                        + "href='javascript:alert(1)'>duplicate href</a>",
                "https://example.test/duplicates/", Safelist.relaxed());
    }

    @Test
    public void UNQUOTED_ATTRIBUTE_variation1_unquotedHttpsHref() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<a href=https://example.test/path>unquoted link</a>", "", safelist);
    }

    @Test
    public void ALLOWED_ATTRIBUTE_variation1_blockquoteCite() {
        Safelist safelist = new Safelist(Safelist.basic());
        verify("<blockquote cite='https://example.test/source'>quotation</blockquote>",
                "http://example.test/document/", safelist);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_variation1_onclickRemoved() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        verify("<p onclick='alert(1)'>click-safe text</p>",
                "https://example.test/events/", safelist);
    }

    @Test
    public void STYLE_ATTRIBUTE_variation1_inlineStyleRemoved() {
        verify("<p style='color:red'>styled text</p>", "", Safelist.basic());
    }

    @Test
    public void DATA_ATTRIBUTE_variation1_customDataRemoved() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<p data-user='42'>data-bearing text</p>",
                "http://example.test/data/", safelist);
    }

    @Test
    public void MIXED_SAFE_AND_UNSAFE_ATTRIBUTES_variation1_hrefAndOnclick() {
        verify("<a href='mailto:reviewer@example.test' onclick='steal()'>contact</a>",
                "https://example.test/contact/", Safelist.basicWithImages());
    }

    @Test
    public void ABSOLUTE_HTTP_LINK_variation1_acceptedProtocol() {
        Safelist safelist = Safelist.relaxed().preserveRelativeLinks(true);
        verify("<a href='http://example.test/page'>HTTP link</a>", "", safelist);
    }

    @Test
    public void ABSOLUTE_HTTPS_LINK_variation1_secureAbsoluteUrl() {
        verify("<a href=https://secure.example.test/page>HTTPS link</a>",
                "http://example.test/base/", Safelist.basic());
    }

    @Test
    public void MAILTO_LINK_PROTOCOL_variation1_permittedMailto() {
        Safelist safelist = new Safelist(Safelist.basic()).preserveRelativeLinks(true);
        verify("<a href='mailto:user@example.test'>send mail",
                "https://example.test/mail/", safelist);
    }

    @Test
    public void FTP_LINK_PROTOCOL_variation1_permittedFtp() {
        verify("<a href='ftp://files.example.test/archive.zip'>download</a>",
                "", Safelist.basic());
    }

    @Test
    public void JAVASCRIPT_LINK_PROTOCOL_variation1_unsafeHrefRemoved() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<a href='javascript:alert(1)'>visible anchor text</a>",
                "http://example.test/javascript/", safelist);
    }

    @Test
    public void OBFUSCATED_UNSAFE_PROTOCOL_variation1_entityDecodedScheme() {
        verify("<a href='jav&#x61;script:alert(1)'>obfuscated link</a>",
                "https://example.test/obfuscated/", Safelist.basic());
    }

    @Test
    public void DATA_URL_PROTOCOL_variation1_imageDataSourceRemoved() {
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        verify("<img src='data:image/png;base64,AAAA' alt='retained alternative'>",
                "", safelist);
    }

    @Test
    public void RELATIVE_LINK_WITH_BASE_REWRITE_variation1_pathResolution() {
        verify("<a href='chapters/one.html'>chapter one</a>",
                "http://example.test/books/index.html", Safelist.relaxed());
    }

    @Test
    public void ROOT_RELATIVE_LINK_variation1_authorityResolution() {
        verify("<a href='/shared/page.html' href='/duplicate'>root link</a>",
                "https://example.test/docs/current.html", Safelist.basic());
    }

    @Test
    public void QUERY_OR_FRAGMENT_REFERENCE_variation1_fragmentReference() {
        Safelist safelist = new Safelist(Safelist.basic()).preserveRelativeLinks(true);
        verify("<a href=#section-2>section two</a>",
                "https://example.test/manual/page.html?old=1", safelist);
    }

    @Test
    public void EMPTY_BASE_DROPS_RELATIVE_LINK_variation1_noResolutionContext() {
        verify("<a href='relative/page.html'>relative anchor</a>",
                "", Safelist.basic());
    }

    @Test
    public void EMPTY_BASE_PRESERVES_RELATIVE_LINK_variation1_dummyUriBranch() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<a href='../relative/page.html'>preserved relative anchor</a>",
                "", safelist);
    }

    @Test
    public void NONEMPTY_BASE_PRESERVES_RELATIVE_LINK_variation1_realBase() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<a href='relative/page.html'>preserved with real base</a>",
                "https://example.test/root/index.html", safelist);
    }

    @Test
    public void PROTOCOL_RELATIVE_URL_variation1_httpSchemeFromBase() {
        verify("<a href='//cdn.example.test/library.js'>protocol relative</a>",
                "http://example.test/application/", Safelist.basic());
    }

    @Test
    public void IMAGE_ALLOWED_POLICY_variation1_httpsImage() {
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        verify("<img src='https://images.example.test/photo.png' "
                        + "alt='sample photograph' title='Photo'>",
                "https://example.test/gallery/", safelist);
    }

    @Test
    public void IMAGE_UNSAFE_SOURCE_PROTOCOL_variation1_javascriptImage() {
        verify("<img src='javascript:alert(1)' alt='safe alternative text'>",
                "", Safelist.basicWithImages());
    }

    @Test
    public void IMAGE_DISALLOWED_BY_NONE_variation1_imageOnly() {
        Safelist safelist = new Safelist(Safelist.none()).preserveRelativeLinks(true);
        verify("<img src='https://example.test/image.png' alt='removed image'>",
                "http://example.test/images/", safelist);
    }

    @Test
    public void TABLE_FRAGMENT_RELAXED_variation1_rowsAndCells() {
        verify("<table><tr><td>alpha<td>beta</tr></table>",
                "https://example.test/tables/", Safelist.relaxed());
    }

    @Test
    public void ENTITY_IN_ATTRIBUTE_VALUE_variation1_escapedQuerySeparator() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        verify("<a href='https://example.test/search?a=1&amp;b=2'>search result",
                "", safelist);
    }

    @Test
    public void TEXT_REQUIRING_ESCAPING_variation1_ampersandAndLessThan() {
        verify("Fish & chips; mathematically 2 &lt; 3.",
                "http://example.test/text/", Safelist.none());
    }

    @Test
    public void EMBEDDED_NUL_CHARACTER_variation1_visibleTextAroundNul() {
        Safelist safelist = Safelist.basicWithImages().preserveRelativeLinks(true);
        verify("<p>before\u0000after</p>",
                "https://example.test/nul/", safelist);
    }

    @Test
    public void MAXIMUM_NESTING_BOUNDARY_variation1_thirtyTwoLevels() {
        verify(nestedDivs(32, "deepest retained text"), "", Safelist.relaxed());
    }

    @Test
    public void LARGE_FRAGMENT_BOUNDARY_variation1_sevenThousandTwoHundredUnits() {
        Safelist safelist = new Safelist().preserveRelativeLinks(true);
        verify(repeatedSiblingFragment(900),
                "http://example.test/large/", safelist);
    }
}
