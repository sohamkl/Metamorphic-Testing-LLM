import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private Object[] generateFollowUp(String cleanedHtml, String baseUri, Safelist safelist) {
        return new Object[]{cleanedHtml, baseUri, safelist};
    }

    @Test
    public void PLAIN_TEXT_NONE_variation1() {
        String body = "KEEP_PLAIN_TEXT";
        String base = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PLAIN_TEXT_DEFAULT_SAFELIST_variation1() {
        String body = "KEEP_DEFAULT_POLICY";
        String base = "https://example.test/";
        Safelist safelist = new Safelist();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BASIC_SIMPLE_PARAGRAPH_variation1() {
        String body = "<p>KEEP_BASIC_PARAGRAPH</p>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELAXED_NESTED_FORMATTING_variation1() {
        String body = "<div><p><strong>KEEP_RELAXED_NESTED</strong></p></div>";
        String base = "https://example.test/docs/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIMPLE_TEXT_STRIPS_MARKUP_variation1() {
        String body = "<p><b>KEEP_SIMPLE_TEXT</b></p>";
        String base = "";
        Safelist safelist = Safelist.simpleText();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_ESCAPES_LITERAL_ANGLE_TEXT_variation1() {
        String body = "KEEP_ENTITY_TEXT &lt;tag&gt; &amp; value";
        String base = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNKNOWN_TAG_WITH_TEXT_variation1() {
        String body = "<custom-widget>KEEP_UNKNOWN_TAG</custom-widget>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SCRIPT_ELEMENT_WITH_OUTSIDE_TEXT_variation1() {
        String body = "KEEP_SCRIPT_OUTSIDE<script>alert('x')</script>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STYLE_ELEMENT_WITH_OUTSIDE_TEXT_variation1() {
        String body = "KEEP_STYLE_OUTSIDE<style>body{display:none}</style>";
        String base = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EVENT_HANDLER_ATTRIBUTE_variation1() {
        String body = "<p onclick=\"alert(1)\">KEEP_EVENT_ATTRIBUTE</p>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNKNOWN_ATTRIBUTE_ON_LINK_variation1() {
        String body = "<a href=\"https://safe.example/path\" data-private=\"x\">KEEP_UNKNOWN_ATTRIBUTE</a>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAVASCRIPT_HREF_variation1() {
        String body = "<a href=\"javascript:alert(1)\">KEEP_JS_URL</a>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_CASE_JAVASCRIPT_HREF_variation1() {
        String body = "<a href=\"JaVaScRiPt:alert(1)\">KEEP_MIXED_JS_URL</a>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WHITESPACE_OBFUSCATED_JAVASCRIPT_HREF_variation1() {
        String body = "<a href=\" java&#x0A;script:alert(1)\">KEEP_SPACE_JS_URL</a>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DATA_HREF_variation1() {
        String body = "<a href=\"data:text/html,unsafe\">KEEP_DATA_HREF</a>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_HTTPS_LINK_variation1() {
        String body = "<a href=\"https://safe.example/a?x=1#f\">KEEP_ABSOLUTE_HTTPS</a>";
        String base = "https://example.test/base/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ABSOLUTE_HTTP_LINK_variation1() {
        String body = "<a href=\"http://safe.example/a\">KEEP_ABSOLUTE_HTTP</a>";
        String base = "https://example.test/base/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RELATIVE_LINK_WITH_HTTP_BASE_variation1() {
        String body = "<a href=\"child/page.html\">KEEP_RELATIVE_HTTP_BASE</a>";
        String base = "http://example.test/dir/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_RELATIVE_LINK_WITH_HTTPS_BASE_variation1() {
        String body = "<a href=\"/root/path\">KEEP_ROOT_RELATIVE</a>";
        String base = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRAGMENT_ONLY_LINK_variation1() {
        String body = "<a href=\"#section\">KEEP_FRAGMENT_LINK</a>";
        String base = "https://example.test/dir/page.html";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROTOCOL_RELATIVE_LINK_variation1() {
        String body = "<a href=\"//safe.example/path\">KEEP_PROTOCOL_RELATIVE</a>";
        String base = "https://example.test/dir/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_RELATIVE_LINK_DEFAULT_variation1() {
        String body = "<a href=\"child\">KEEP_EMPTY_BASE_RELATIVE</a>";
        String base = "";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BASE_PRESERVE_RELATIVE_SENTINEL_variation1() {
        String body = "<a href=\"child\">KEEP_DUMMY_URI_BRANCH</a>";
        String base = "";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONEMPTY_BASE_PRESERVE_RELATIVE_variation1() {
        String body = "<a href=\"child\">KEEP_PRESERVE_NONEMPTY</a>";
        String base = "https://example.test/dir/";
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_WITH_SAFE_HTTPS_SOURCE_variation1() {
        String body = "KEEP_IMAGE_PREFIX<img src=\"https://images.example/p.png\" alt=\"desc\">";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_WITH_JAVASCRIPT_SOURCE_variation1() {
        String body = "KEEP_BAD_IMAGE_PREFIX<img src=\"javascript:alert(1)\" alt=\"x\">";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IMAGE_RELATIVE_SOURCE_variation1() {
        String body = "KEEP_REL_IMAGE_PREFIX<img src=\"images/p.png\" alt=\"x\">";
        String base = "https://example.test/assets/";
        Safelist safelist = Safelist.basicWithImages();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HTML_COMMENT_variation1() {
        String body = "KEEP_COMMENT<!-- hidden comment -->";
        String base = "";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOCTYPE_IN_FRAGMENT_variation1() {
        String body = "<!doctype html>KEEP_DOCTYPE_FRAGMENT";
        String base = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNCLOSED_ELEMENT_variation1() {
        String body = "<p>KEEP_UNCLOSED_ELEMENT";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MISNESTED_FORMATTING_variation1() {
        String body = "<p><b>KEEP_MISNESTED</p></b>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DUPLICATE_ATTRIBUTE_variation1() {
        String body = "<a href=\"https://first.example/\" href=\"javascript:alert(1)\">KEEP_DUPLICATE_ATTRIBUTE</a>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_CASE_TAG_AND_ATTRIBUTE_variation1() {
        String body = "<A HREF=\"https://safe.example/\">KEEP_CASE_NORMALIZATION</A>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.basic();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NESTED_UNSAFE_WRAPPER_variation1() {
        String body = "KEEP_OUTER_MARKER<iframe src=\"https://unsafe.example/\"><p>KEEP_INNER_MARKER</p></iframe>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.relaxed();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEXT_WITH_QUOTE_AND_AMPERSAND_variation1() {
        String body = "KEEP_SPECIAL_TEXT &amp; &quot;quoted&quot; &lt; less";
        String base = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NUMERIC_AND_NAMED_ENTITIES_variation1() {
        String body = "KEEP_ENTITIES &#65; &#x42; &amp;copy;";
        String base = "";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COPY_CONSTRUCTED_SAFELIST_variation1() {
        String body = "<p>KEEP_COPY_CONSTRUCTED</p>";
        String base = "https://example.test/";
        Safelist safelist = new Safelist(Safelist.basic());
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONE_WITH_REMOVED_TAG_AND_RETAINED_TEXT_variation1() {
        String body = "<div>KEEP_NONE_WRAPPED_TEXT</div>";
        String base = "https://example.test/";
        Safelist safelist = Safelist.none();
        String sourceOutput = Jsoup.clean(body, base, safelist);
        Object[] followUp = generateFollowUp(sourceOutput, base, safelist);
        String followUpOutput = Jsoup.clean((String) followUp[0], (String) followUp[1], (Safelist) followUp[2]);
        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
