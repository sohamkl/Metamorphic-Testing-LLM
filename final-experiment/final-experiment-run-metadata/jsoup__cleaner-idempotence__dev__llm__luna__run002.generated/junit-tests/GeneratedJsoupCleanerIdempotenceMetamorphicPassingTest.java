import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    @Test
    public void EMPTY_FRAGMENT_WITH_EMPTY_BASE_1() {
        String b = "";
        String u = "";
        Safelist s = new Safelist();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void WHITESPACE_ONLY_FRAGMENT_1() {
        String b = " \t\n  ";
        String u = "http://example.test/page";
        Safelist s = Safelist.none();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void PLAIN_TEXT_WITH_NONE_POLICY_1() {
        String b = "plain & ordinary text";
        String u = "https://example.test/docs";
        Safelist s = Safelist.simpleText();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void ENTITY_ESCAPING_WITH_NONE_POLICY_1() {
        String b = "5 &lt; 6 &amp; 7";
        String u = "https://example.test/a/path";
        Safelist s = Safelist.basic();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void ENTITY_ESCAPING_WITH_NONE_POLICY_2() {
        String b = "<script>&lt;visible&gt; &amp; text</script>";
        String u = "relative/base";
        Safelist s = Safelist.basicWithImages();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void TEXT_WITH_DISALLOWED_ELEMENT_1() {
        String b = "<p class='kept'>Visible <iframe bad='x'>inside</iframe></p>";
        String u = "";
        Safelist s = Safelist.relaxed();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void TEXT_WITH_DISALLOWED_ELEMENT_2() {
        String b = "<a href='javascript:bad()'>link <object data='x'>object</object></a>";
        String u = "http://example.test/root";
        Safelist s = new Safelist(Safelist.relaxed());
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void PERMITTED_ELEMENT_WITH_SAFE_CONTENT_1() {
        String b = "<p>Readable paragraph</p>";
        String u = "https://example.test/";
        Safelist s = new Safelist();
        s.addTags("p");
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void PERMITTED_ELEMENT_WITH_DISALLOWED_ATTRIBUTE_1() {
        String b = "<p style='color:red' data-x='1'>text</p>";
        String u = "https://example.test/a";
        Safelist s = Safelist.none();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void PERMITTED_ELEMENT_WITH_DISALLOWED_ATTRIBUTE_2() {
        String b = "<strong title='bad' data-test='x'>bold text</strong>";
        String u = "relative/base";
        Safelist s = Safelist.simpleText();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void PERMITTED_ATTRIBUTE_WITH_NORMAL_VALUE_1() {
        String b = "<a href='docs/index.html' title='Documentation'>docs</a>";
        String u = "";
        Safelist s = Safelist.basic();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void PERMITTED_ATTRIBUTE_WITH_NORMAL_VALUE_2() {
        String b = "<img src='photo.png' alt='A photo' width='40'>";
        String u = "http://example.test/images/";
        Safelist s = Safelist.basicWithImages();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void PERMITTED_ATTRIBUTE_WITH_NORMAL_VALUE_3() {
        String b = "<a href='  ' title='link'>   </a>";
        String u = "https://example.test/";
        Safelist s = Safelist.relaxed();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void RELATIVE_URL_WITH_ABSOLUTE_BASE_1() {
        String b = "<a href='../guide/start.html'>Guide</a>";
        String u = "https://example.test/docs/page.html";
        Safelist s = new Safelist(Safelist.basic());
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void RELATIVE_URL_WITH_ABSOLUTE_BASE_2() {
        String b = "<a href='chapter-2.html'>chapter</a> plain";
        String u = "relative/base";
        Safelist s = new Safelist();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void RELATIVE_URL_WITH_EMPTY_BASE_AND_PRESERVE_RELATIVE_SETTING_1() {
        String b = "<a href='relative/page.html'>relative link</a>";
        String u = "";
        Safelist s = Safelist.basic();
        s.preserveRelativeLinks(true);
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void RELATIVE_URL_WITH_EMPTY_BASE_AND_PRESERVE_RELATIVE_SETTING_2() {
        String b = "<a href='section'>Section</a>";
        String u = "http://example.test/";
        Safelist s = Safelist.simpleText();
        s.preserveRelativeLinks(true);
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void RELATIVE_URL_WITH_EMPTY_BASE_AND_NONPRESERVING_POLICY_1() {
        String b = "<script><a href='x'>hidden</a></script>";
        String u = "https://example.test/";
        Safelist s = Safelist.basic();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void RELATIVE_URL_WITH_EMPTY_BASE_AND_NONPRESERVING_POLICY_2() {
        String b = "<img src='image.png' onclick='bad()' data-x='1'>";
        String u = "https://example.test/assets/";
        Safelist s = Safelist.basicWithImages();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void ABSOLUTE_SAFE_PROTOCOL_URL_1() {
        String b = "<a href='relative.html'>relative safe link</a>";
        String u = "relative/base";
        Safelist s = Safelist.relaxed();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void ABSOLUTE_SAFE_PROTOCOL_URL_2() {
        String b = "<a href='https://safe.example/path'>secure link</a>";
        String u = "";
        Safelist s = new Safelist(Safelist.basic());
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void UNSAFE_PROTOCOL_URL_1() {
        String b = "<a href='javascript:alert(1)'>unsafe</a>";
        String u = "http://example.test/";
        Safelist s = new Safelist();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void UNSAFE_PROTOCOL_URL_2() {
        String b = "<a href='javascript:void(0)'>bad <b>markup";
        String u = "https://example.test/";
        Safelist s = Safelist.none();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void UNSAFE_PROTOCOL_URL_3() {
        String b = "<!-- note --><a href='data:text/html,bad'>visible</a>";
        String u = "https://example.test/path";
        Safelist s = Safelist.simpleText();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void MIXED_SAFE_AND_UNSAFE_URLS_1() {
        String b = "<p><a href='one.html'>one</a> <a href='https://safe.example/two'>two</a> <a href='javascript:bad()'>three</a></p>";
        String u = "relative/base";
        Safelist s = Safelist.basic();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void MIXED_SAFE_AND_UNSAFE_URLS_2() {
        String b = " \n <img src='x.png'><img src='https://safe.example/y.png'><img src='javascript:bad'>";
        String u = "";
        Safelist s = Safelist.basicWithImages();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void BASIC_WITH_IMAGE_CONTENT_1() {
        String b = "<img src='photo.jpg' alt='portrait' style='bad'>";
        String u = "http://example.test/images/";
        Safelist s = Safelist.basicWithImages();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void BASIC_WITH_IMAGE_CONTENT_2() {
        String b = "<img src='javascript:bad' alt='bad protocol'> text";
        String u = "https://example.test/";
        Safelist s = new Safelist(Safelist.basicWithImages());
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void RELAXED_POLICY_NESTED_CONTENT_1() {
        String b = "escaped &lt;text&gt; <div class='box'><span data-x='bad'>nested</span><script>gone</script></div>";
        String u = "https://example.test/a/path";
        Safelist s = new Safelist();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void RELAXED_POLICY_NESTED_CONTENT_2() {
        String b = "<div class='outer'><p>inside <em>emphasis</em></p><iframe>remove</iframe></div>";
        String u = "relative/base";
        Safelist s = Safelist.none();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void SIMPLE_TEXT_POLICY_MARKUP_1() {
        String b = "<b>simple text</b><div>richer text</div>";
        String u = "";
        Safelist s = Safelist.simpleText();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void SIMPLE_TEXT_POLICY_MARKUP_2() {
        String b = "<p class='ok' data-bad='x'>paragraph <script>unsafe</script></p>";
        String u = "http://example.test/";
        Safelist s = Safelist.basic();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void NONE_POLICY_MARKUP_ONLY_1() {
        String b = "<div><span></span><img src='x.png'></div>";
        String u = "https://example.test/";
        Safelist s = Safelist.basicWithImages();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void NONE_POLICY_MARKUP_ONLY_2() {
        String b = "<a href='https://example.test/x'></a><div></div>";
        String u = "https://example.test/path";
        Safelist s = Safelist.relaxed();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void EMPTY_CONSTRUCTED_SAFELIST_1() {
        String b = "plain text <p>paragraph</p>";
        String u = "relative/base";
        Safelist s = new Safelist();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void COPIED_SAFELIST_POLICY_1() {
        String b = "<p>unclosed <a href='x'>link";
        String u = "";
        Safelist s = new Safelist(Safelist.basic());
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void COPIED_SAFELIST_POLICY_2() {
        String b = "<!-- comment -->text<!DOCTYPE html>";
        String u = "http://example.test/";
        Safelist s = new Safelist(Safelist.none());
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void MALFORMED_UNBALANCED_FRAGMENT_1() {
        String b = "<p>one <b>two <i>three</p> tail";
        String u = "https://example.test/";
        Safelist s = Safelist.simpleText();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void MALFORMED_UNBALANCED_FRAGMENT_2() {
        String b = " \n\t ";
        String u = "https://example.test/a/path";
        Safelist s = Safelist.basic();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void NESTED_DISALLOWED_AND_PERMITTED_ELEMENTS_1() {
        String b = "<script>bad <p>kept text</p></script>";
        String u = "relative/base";
        Safelist s = Safelist.basicWithImages();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void NESTED_DISALLOWED_AND_PERMITTED_ELEMENTS_2() {
        String b = "<div>outer <p>inner paragraph</p> text</div>";
        String u = "";
        Safelist s = Safelist.relaxed();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void COMMENTS_AND_DECLARATIONS_1() {
        String b = "<!-- note -->5 &lt; 6<!DOCTYPE fragment>";
        String u = "http://example.test/";
        Safelist s = new Safelist(Safelist.relaxed());
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void COMMENTS_AND_DECLARATIONS_2() {
        String b = "<!-- hidden --><p>visible</p>";
        String u = "https://example.test/";
        Safelist s = new Safelist();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void ABSOLUTE_BASE_WITH_PATH_1() {
        String b = "<script>bad</script><a href='../next.html'>next</a>";
        String u = "https://example.test/docs/guide/page.html";
        Safelist s = Safelist.none();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void ABSOLUTE_BASE_WITH_PATH_2() {
        String b = "<p class='allowed' data-no='x'>text</p><a href='next'>link</a>";
        String u = "relative/path/base.html";
        Safelist s = Safelist.simpleText();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void NONEMPTY_NONURL_BASE_STRING_1() {
        String b = "<a href='relative.html'>ordinary link</a> text";
        String u = "not-a-url";
        Safelist s = Safelist.basic();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void SPECIAL_CHARACTER_TEXT_1() {
        String b = "\"quotes\" & <angles> café 日本語";
        String u = "http://example.test/";
        Safelist s = Safelist.basicWithImages();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void SPECIAL_CHARACTER_TEXT_2() {
        String b = "Symbols &amp; text <a href='javascript:bad()'>\"link\"</a> café";
        String u = "https://example.test/";
        Safelist s = Safelist.relaxed();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void MULTIPLE_SIBLING_FRAGMENTS_1() {
        String b = "<p>first<b>second</p><script>bad</script><a href='javascript:x'>third";
        String u = "https://example.test/docs/";
        Safelist s = new Safelist(Safelist.basic());
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }

    @Test
    public void MULTIPLE_SIBLING_FRAGMENTS_2() {
        String b = "<!-- c -->plain <div>block</div> <span>span</span>";
        String u = "relative/base";
        Safelist s = new Safelist();
        String o = Jsoup.clean(b, u, s);
        Object[] f = jsoupmt.CleanerIdempotenceMetamorphicSpec.generateFollowUp(b, u, s);
        String q = Jsoup.clean((String) f[0], (String) f[1], (Safelist) f[2]);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(o, q);
    }
}
