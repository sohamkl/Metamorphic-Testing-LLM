import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    @Test
    public void EMPTY_FRAGMENT_NONE_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(" \n\t  ", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(" \n\t  ", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_WITH_ESCAPABLE_CHARACTERS_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("5 < 6 & 7 > 3 \"quoted\"", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("5 < 6 & 7 > 3 \"quoted\"", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEXT_WITH_PREEXISTING_ENTITIES_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("A&amp;B &#60;tag&#62; &#x1F642; &notAnEntity;", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("A&amp;B &#60;tag&#62; &#x1F642; &notAnEntity;", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_STRIPS_ELEMENT_MARKUP_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("<p>alpha <strong>beta</strong></p>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<p>alpha <strong>beta</strong></p>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_WITH_UNSAFE_SCRIPT_ELEMENT_variation1() {
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean("before<script>alert(1)</script>after", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("before<script>alert(1)</script>after", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEFAULT_CONSTRUCTED_SAFELIST_TEXT_variation1() {
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean("default-policy text", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("default-policy text", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEFAULT_CONSTRUCTED_SAFELIST_MARKUP_CANDIDATE_variation1() {
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean("<p class=\"c\"><em>default</em> policy</p>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<p class=\"c\"><em>default</em> policy</p>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_FACTORY_MARKUP_variation1() {
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean("<p>simple <b>bold</b> <i>italic</i></p>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<p>simple <b>bold</b> <i>italic</i></p>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_FACTORY_FORMATTING_CANDIDATES_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<p title=\"t\"><strong>bold</strong> <em>emphasis</em><br>tail</p>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<p title=\"t\"><strong>bold</strong> <em>emphasis</em><br>tail</p>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_FACTORY_ABSOLUTE_LINK_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href=\"https://safe.example/path?q=1#f\" title=\"link\">go</a>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"https://safe.example/path?q=1#f\" title=\"link\">go</a>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_VERSUS_IMAGE_POLICY_CANDIDATE_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<img src=\"https://images.example/a.png\" alt=\"image-marker\">", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<img src=\"https://images.example/a.png\" alt=\"image-marker\">", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_WITH_IMAGES_IMAGE_CANDIDATE_variation1() {
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean("<img src=\"https://images.example/a.png\" alt=\"image-marker\" onclick=\"run()\">", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<img src=\"https://images.example/a.png\" alt=\"image-marker\" onclick=\"run()\">", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_FACTORY_TABLE_STRUCTURE_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<table><thead><tr><th>H</th></tr></thead><tbody><tr><td>cell</td></tr></tbody></table>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<table><thead><tr><th>H</th></tr></thead><tbody><tr><td>cell</td></tr></tbody></table>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPY_CONSTRUCTED_RELAXED_POLICY_variation1() {
        Safelist safelist = new Safelist(Safelist.relaxed());
        String sourceOutput = Jsoup.clean("<blockquote cite=\"https://example.test/source\">copied policy</blockquote>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<blockquote cite=\"https://example.test/source\">copied policy</blockquote>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DISALLOWED_NESTED_CONTAINER_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<section><unknown><p>nested-marker</p></unknown></section>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<section><unknown><p>nested-marker</p></unknown></section>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_SCRIPT_AND_STYLE_MIX_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("start<script>evil()</script><style>body{display:none}</style>end", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("start<script>evil()</script><style>body{display:none}</style>end", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_EVENT_HANDLER_ATTRIBUTE_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<p onclick=\"alert(1)\">event-marker</p>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<p onclick=\"alert(1)\">event-marker</p>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_JAVASCRIPT_URL_PROTOCOL_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<a href=\"javascript:alert(1)\">js-url-marker</a>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"javascript:alert(1)\">js-url-marker</a>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNSAFE_DATA_URL_PROTOCOL_variation1() {
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean("<img src=\"data:text/html,<script>alert(1)</script>\" alt=\"data-url-marker\">", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<img src=\"data:text/html,<script>alert(1)</script>\" alt=\"data-url-marker\">", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITH_HTTP_BASE_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href=\"docs/page.html\">relative-http-marker</a>", "http://example.test/root/index.html", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"docs/page.html\">relative-http-marker</a>", "http://example.test/root/index.html", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITH_HTTPS_PATH_BASE_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<a href=\"../up?q=2#part\">relative-https-marker</a>", "https://example.test/a/b/page.html?base=1#base-fragment", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"../up?q=2#part\">relative-https-marker</a>", "https://example.test/a/b/page.html?base=1#base-fragment", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRAGMENT_ONLY_LINK_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href=\"#local\">fragment-marker</a>", "https://example.test/path/page.html", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"#local\">fragment-marker</a>", "https://example.test/path/page.html", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<a href=\"//cdn.example.test/file\">protocol-relative-marker</a>", "https://example.test/path/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"//cdn.example.test/file\">protocol-relative-marker</a>", "https://example.test/path/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_URI_RELATIVE_LINK_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href=\"relative/path\">empty-base-marker</a>", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"relative/path\">empty-base-marker</a>", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_URI_RELATIVE_IMAGE_variation1() {
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean("<img src=\"images/p.png\" alt=\"empty-base-image-marker\">", "", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<img src=\"images/p.png\" alt=\"empty-base-image-marker\">", "", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_HTTP_BASE_URI_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href=\"https://example.test/absolute\">non-http-base-marker</a>", "mailto:user@example.test", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"https://example.test/absolute\">non-http-base-marker</a>", "mailto:user@example.test", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMBEDDED_BASE_ELEMENT_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<base href=\"https://embedded.example/root/\"><a href=\"child\">embedded-base-marker</a>", "https://outer.example/start/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<base href=\"https://embedded.example/root/\"><a href=\"child\">embedded-base-marker</a>", "https://outer.example/start/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_UNCLOSED_NESTING_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<p>one<b>two<i>three", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<p>one<b>two<i>three", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MALFORMED_ATTRIBUTE_QUOTING_variation1() {
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean("<a href=\"https://example.test/path title=broken>quoted-attr-marker</a>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"https://example.test/path title=broken>quoted-attr-marker</a>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_CASE_TAG_AND_ATTRIBUTE_NAMES_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<A HREF=\"https://example.test/\" OnClIcK=\"x()\">case-marker</A>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<A HREF=\"https://example.test/\" OnClIcK=\"x()\">case-marker</A>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DUPLICATE_URL_ATTRIBUTES_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<a href=\"https://safe.example/\" href=\"javascript:alert(1)\">duplicate-href-marker</a>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<a href=\"https://safe.example/\" href=\"javascript:alert(1)\">duplicate-href-marker</a>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOCUMENT_STRUCTURAL_MARKUP_IN_BODY_FRAGMENT_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<html><head><title>head-marker</title></head><body><p>body-marker</p></body></html>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<html><head><title>head-marker</title></head><body><p>body-marker</p></body></html>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMENT_AND_DECLARATION_FRAGMENT_variation1() {
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean("<!doctype html><!-- comment-marker --><p>visible-comment-case</p>", "https://example.test/", safelist);
        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp("<!doctype html><!-- comment-marker --><p>visible-comment-case</p>", "https://example.test/", safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
