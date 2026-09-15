import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static void assertMetamorphicRelationFor(
            String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        String followUpOutput = Jsoup.clean(sourceOutput, baseUri, safelist);
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static Safelist copied(Safelist source) {
        return new Safelist(source);
    }

    private static String largeFragment() {
        return "<div class='outer' data-drop='x'><p>Hello &amp; welcome "
                + "<a href='../docs/page.html?x=1#part' title='keep'>link</a> "
                + "<script>alert('x')</script><!-- note --></p>"
                + "<ul><li>one<li>two<li><img src='https://example.com/i.png' "
                + "onclick='bad()' alt='image'></ul></div>"
                + "<table><tr><td>cell</td></tr></table>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>"
                + "<div class='repeat'>alpha beta gamma delta epsilon</div>";
    }

    @Test
    public void EMPTY_FRAGMENT_variation1() {
        String bodyHtml = "";
        String baseUri = "";
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void SINGLE_TEXT_CHARACTER_variation1() {
        String bodyHtml = "<";
        String baseUri = "https://example.test/root/";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void PLAIN_TEXT_FRAGMENT_variation1() {
        String bodyHtml = "alpha & beta, with spaces\nand punctuation.";
        String baseUri = "https://example.test/a/path/page.html";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void ENTITY_ESCAPING_BOUNDARY_variation1() {
        String bodyHtml = "5 < 6 &amp; 7 > 3 & unknown";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void POLICY_EMPTY_TEXT_ONLY_variation1() {
        String bodyHtml = "plain text only: &lt;safe&gt;";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void POLICY_NONE_REMOVES_ELEMENTS_variation1() {
        String bodyHtml = "<div><span>visible <b>content</b></span></div>";
        String baseUri = "https://example.test/articles/index.html";
        Safelist safelist = Safelist.none();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void SIMPLE_TEXT_MARKUP_FILTER_variation1() {
        String bodyHtml = "before <b>bold</b> <em>emphasis</em> <div>block</div> after";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void BASIC_PERMITTED_INLINE_variation1() {
        String bodyHtml = "intro <p>paragraph with <strong>strong</strong></p><blockquote>quote</blockquote>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELAXED_NESTED_STRUCTURE_variation1() {
        String bodyHtml = "<div><table><tbody><tr><td><p><a href='x'>deep</a></p></td></tr></tbody></table></div><p>sibling</p>";
        String baseUri = "https://example.test/path/index.html";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void BASIC_WITH_IMAGES_variation1() {
        String bodyHtml = "before <img src='https://example.test/image.png' alt='photo' width='20'> after";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void DEFAULT_SAFELIST_CONSTRUCTION_variation1() {
        String bodyHtml = "text <div class='x' data-extra='y'>element</div>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void COPIED_SAFELIST_POLICY_variation1() {
        String bodyHtml = "<p class='lead' data-remove='1'>copied policy text</p>";
        String baseUri = "https://example.test/content/";
        Safelist safelist = copied(Safelist.relaxed());
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void DISALLOWED_ELEMENT_WITH_TEXT_variation1() {
        String bodyHtml = "<script>bad()</script><custom>retained visible text</custom>";
        String baseUri = "";
        Safelist safelist = Safelist.simpleText();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void DISALLOWED_ELEMENT_ONLY_variation1() {
        String bodyHtml = "<script>alert(1)</script><iframe src='x'></iframe>";
        String baseUri = "https://example.test/";
        Safelist safelist = copied(Safelist.basic());
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void NESTED_ALLOWED_AND_DISALLOWED_variation1() {
        String bodyHtml = "<div><p>allowed text <script>unsafe</script></p></div>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void REMOVED_ATTRIBUTE_variation1() {
        String bodyHtml = "<p class='kept-candidate' onclick='remove-me'>visible paragraph</p>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RETAINED_ATTRIBUTE_variation1() {
        String bodyHtml = "<a href='https://example.test/docs' title='documentation'>read this</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MIXED_RETAINED_AND_REMOVED_ATTRIBUTES_variation1() {
        String bodyHtml = "<a href='https://example.test/a' title='link' onclick='bad' data-x='drop'>text</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MIXED_RETAINED_AND_REMOVED_ATTRIBUTES_variation2() {
        String bodyHtml = "<img src='https://example.test/a.png' alt='picture' style='bad' onerror='bad'>image";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void ABSOLUTE_PROTOCOL_PERMITTED_variation1() {
        String bodyHtml = "<a href='https://example.test/page?q=1'>secure link</a>";
        String baseUri = "https://example.test/base/";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void ABSOLUTE_PROTOCOL_PERMITTED_variation2() {
        String bodyHtml = "<img src='https://cdn.example.test/photo.jpg' alt='photo'>caption";
        String baseUri = "https://example.test/gallery/";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void ABSOLUTE_PROTOCOL_DISALLOWED_variation1() {
        String bodyHtml = "<a href='javascript:alert(1)'>unsafe link</a>";
        String baseUri = "";
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void ABSOLUTE_PROTOCOL_DISALLOWED_variation2() {
        String bodyHtml = "<a href='ftp://example.test/file'>file link</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELATIVE_URL_NONEMPTY_BASE_variation1() {
        String bodyHtml = "<a href='../docs/guide.html'>guide</a><p>text</p>";
        String baseUri = "https://example.test/articles/2026/page.html";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELATIVE_URL_NONEMPTY_BASE_variation2() {
        String bodyHtml = "<img src='images/photo.png' alt='photo'>surrounding text";
        String baseUri = "https://example.test/a/b/c/index.html";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELATIVE_URL_EMPTY_BASE_PRESERVE_FALSE_variation1() {
        String bodyHtml = "<a href='relative/page.html'>relative link</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELATIVE_URL_EMPTY_BASE_PRESERVE_FALSE_variation2() {
        String bodyHtml = "<img src='../image.png' alt='relative image'>text";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELATIVE_URL_EMPTY_BASE_PRESERVE_TRUE_variation1() {
        String bodyHtml = "<a href='docs/page.html'>relative documentation</a>";
        String baseUri = "";
        Safelist safelist = copied(Safelist.basic());
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void RELATIVE_URL_EMPTY_BASE_PRESERVE_TRUE_variation2() {
        String bodyHtml = "<img src='images/icon.png' alt='icon'>visible text";
        String baseUri = "";
        Safelist safelist = copied(Safelist.basicWithImages());
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void URL_WITH_FRAGMENT_AND_QUERY_variation1() {
        String bodyHtml = "<a href='../docs/page.html?mode=full&lang=en#section-2'>details</a>";
        String baseUri = "https://example.test/articles/current/index.html";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MALFORMED_UNBALANCED_FRAGMENT_variation1() {
        String bodyHtml = "<p>first <b>bold <i>nested text</p>tail";
        String baseUri = "";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void MALFORMED_UNBALANCED_FRAGMENT_variation2() {
        String bodyHtml = "left <div><p>middle <em>right";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.none();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void QUOTED_UNQUOTED_ATTRIBUTE_BOUNDARY_variation1() {
        String bodyHtml = "<a href=\"https://example.test/page\" title=\"quoted\">quoted</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void QUOTED_UNQUOTED_ATTRIBUTE_BOUNDARY_variation2() {
        String bodyHtml = "<a href=https://example.test/page title=unquoted>unquoted</a>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void COMMENT_AND_TEXT_FRAGMENT_variation1() {
        String bodyHtml = "before<!-- hidden comment -->after <p>visible</p>";
        String baseUri = "https://example.test/";
        Safelist safelist = copied(Safelist.relaxed());
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void SCRIPT_STYLE_OR_UNSAFE_CONTENT_variation1() {
        String bodyHtml = "safe text<script>document.write('unsafe')</script><style>.x{color:red}</style>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void SCRIPT_STYLE_OR_UNSAFE_CONTENT_variation2() {
        String bodyHtml = "<script>bad()</script>visible <iframe src='x'>frame</iframe>";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void LARGE_MIXED_FRAGMENT_variation1() {
        String bodyHtml = largeFragment();
        String baseUri = "https://example.test/articles/2026/index.html";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void LARGE_MIXED_FRAGMENT_variation2() {
        String bodyHtml = largeFragment() + "<script>remove</script><!-- trailing -->";
        String baseUri = "https://example.test/a/b/page.html";
        Safelist safelist = Safelist.none();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void SAFELIST_POLICY_CROSS_SECTION_variation1() {
        String bodyHtml = "<p class='p' data-drop='x'>text <a href='https://example.test/a'>link</a></p>"
                + "<img src='https://example.test/i.png' alt='image'>"
                + "<table><tr><td>cell</td></tr></table><script>bad</script>";
        String baseUri = "";
        Safelist safelist = Safelist.relaxed();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void NONEMPTY_BASE_PATH_RESOLUTION_variation1() {
        String bodyHtml = "<a href='docs/page.html'>root-relative candidate</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void NONEMPTY_BASE_PATH_RESOLUTION_variation2() {
        String bodyHtml = "<a href='../docs/page.html'>deep relative candidate</a>";
        String baseUri = "https://example.test/one/two/three/index.html";
        Safelist safelist = copied(Safelist.basic());
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void ADJACENT_TEXT_TAG_TEXT_variation1() {
        String bodyHtml = "left<strong>middle</strong>right";
        String baseUri = "";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void FOLLOW_UP_ALREADY_CLEAN_variation1() {
        String bodyHtml = "<p>clean <strong>content</strong></p><a href='https://example.test/'>home</a>";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void FOLLOW_UP_ALREADY_CLEAN_variation2() {
        String bodyHtml = "<p>relaxed <em>content</em></p><img src='https://example.test/i.png' alt='i'>";
        String baseUri = "https://example.test/content/";
        Safelist safelist = Safelist.basicWithImages();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void WHITESPACE_AND_LINE_ENDINGS_variation2() {
        String bodyHtml = "\n<!-- comment -->\r\n <p>text &amp; more</p>\t";
        String baseUri = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void LONG_ATTRIBUTE_VALUE_variation1() {
        String value = "abcdefghijklmnopqrstuvwxyz0123456789&quot;".repeat(7);
        String bodyHtml = "<p title='" + value + "'>long attribute text</p>";
        String baseUri = "https://example.test/path/";
        Safelist safelist = Safelist.basic();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void EMPTY_AFTER_ATTRIBUTE_AND_ELEMENT_FILTERING_variation1() {
        String bodyHtml = "<script data-bad='1'>only unsafe content</script><iframe src='x'></iframe>";
        String baseUri = "";
        Safelist safelist = Safelist.none();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }

    @Test
    public void EMPTY_AFTER_ATTRIBUTE_AND_ELEMENT_FILTERING_variation2() {
        String bodyHtml = "<custom data-removed='yes'></custom><unknown onclick='bad'></unknown>";
        String baseUri = "https://example.test/";
        Safelist safelist = new Safelist();
        assertMetamorphicRelationFor(bodyHtml, baseUri, safelist);
    }
}
