import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {
    private static void exercise(String bodyHtml, String baseUri, Safelist safelist) {
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source =
                new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(bodyHtml, baseUri, safelist);

        String sourceOutput = Jsoup.clean(source.arg0(), source.arg1(), source.arg2());
        Object[] followUpValues = CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                source.arg0(), source.arg1(), source.arg2());
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input followUp =
                new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(
                        (String) followUpValues[0],
                        (String) followUpValues[1],
                        (Safelist) followUpValues[2]);
        String followUpOutput = Jsoup.clean(
                followUp.arg0(), followUp.arg1(), followUp.arg2());

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_FRAGMENT_NONE_variation1() {
        exercise("", "", Safelist.none());
    }

    @Test
    void PLAIN_TEXT_NONE_variation1() {
        exercise("Plain visible text", "http://example.test/", Safelist.none());
    }

    @Test
    void ENTITY_TEXT_NONE_variation1() {
        exercise("5 is &lt; 6 &amp; 7 &gt; 4", "https://example.test/base", Safelist.none());
    }

    @Test
    void ONLY_UNSAFE_MARKUP_NONE_variation1() {
        exercise("<script><style></style></script>", "relative/base",
                Safelist.none());
    }

    @Test
    void SIMPLE_TEXT_ALLOWED_FORMATTING_variation1() {
        exercise("<b>bold</b><i>italic</i><u>under</u>",
                "https://example.test/path/page", Safelist.simpleText());
    }

    @Test
    void SIMPLE_TEXT_ALLOWED_FORMATTING_variation2() {
        exercise("A long line of text with <b>bold</b> and <!-- comment --> marks",
                "", Safelist.simpleText());
    }

    @Test
    void SIMPLE_TEXT_DISALLOWED_NESTING_variation1() {
        exercise("<b>x</b><div><script>y</script></div>",
                "http://example.test/", new Safelist(Safelist.simpleText()));
    }

    @Test
    void BASIC_ALLOWED_LINK_variation1() {
        exercise("<p>go <a href=\"http://example.com/path\">there</a></p>",
                "https://base.example/", Safelist.basic());
    }

    @Test
    void BASIC_ALLOWED_LINK_variation2() {
        exercise("<p><a href=\"docs/page.html\">read</a></p>",
                "relative/root", Safelist.basic());
    }

    @Test
    void BASIC_UNSAFE_ATTRIBUTE_variation1() {
        exercise("<p onclick=\"alert(1)\" data-x=\"1\">text</p>",
                "https://example.test/path/index.html", Safelist.basic());
    }

    @Test
    void BASIC_UNSAFE_ATTRIBUTE_variation2() {
        exercise("<p>one</p><p>two</p><p>three</p>",
                "", Safelist.basic());
    }

    @Test
    void BASIC_UNSAFE_LINK_PROTOCOL_variation1() {
        exercise("<a href=\"javascript:alert(1)\">run</a>",
                "http://example.com/", Safelist.basic());
    }

    @Test
    void BASIC_UNSAFE_LINK_PROTOCOL_variation2() {
        exercise("<a href=\"relative/page.html\">relative</a>",
                "https://example.com/base", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_NONEMPTY_BASE_variation1() {
        exercise("<a href=\"docs/page.html\">docs</a>",
                "https://example.com/root/index.html", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_NONEMPTY_BASE_variation2() {
        exercise("<script>bad</script><a href=\"next\">next</a>",
                "relative/base/path", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_EMPTY_BASE_variation1() {
        exercise("<a href=\"docs/page.html\">docs</a>", "", Safelist.basic());
    }

    @Test
    void BASIC_RELATIVE_LINK_EMPTY_BASE_variation2() {
        exercise("<div><a href=\"child\">child</a><script>x</script></div>",
                "http://example.test/", Safelist.basic());
    }

    @Test
    void BASIC_IMAGE_WITH_UNSAFE_PROTOCOL_variation1() {
        exercise("<img src=\"data:image/svg+xml,<svg/onload=alert(1)>\" alt=\"x\">",
                "https://example.com/", Safelist.basicWithImages());
    }

    @Test
    void BASIC_IMAGE_WITH_UNSAFE_PROTOCOL_variation2() {
        exercise("<img src=\"javascript:alert(1)\" alt=\"bad\">",
                "relative/assets", Safelist.basicWithImages());
    }

    @Test
    void BASIC_IMAGE_RELATIVE_SOURCE_variation1() {
        exercise("<img src=\"images/picture.png\" alt=\"picture\">",
                "https://cdn.example/assets/index.html",
                Safelist.basicWithImages());
    }

    @Test
    void BASIC_IMAGE_RELATIVE_SOURCE_variation2() {
        exercise("<img src=\"pic.png\" alt=\"5 &amp; 6\">",
                "", Safelist.basicWithImages());
    }

    @Test
    void RELAXED_NESTED_ALLOWED_MARKUP_variation1() {
        exercise("<h1>Title</h1><p><strong>Important</strong> "
                        + "<a href=\"https://example.com\">link</a></p>",
                "http://origin.example/", Safelist.relaxed());
    }

    @Test
    void RELAXED_NESTED_ALLOWED_MARKUP_variation2() {
        exercise("<p><em>one</em></p><p><strong>two</strong></p>",
                "https://origin.example/", Safelist.relaxed());
    }

    @Test
    void RELAXED_DISALLOWED_SCRIPT_AROUND_CONTENT_variation1() {
        exercise("<!--x--><p>before</p><script>danger()</script><p>after</p>",
                "relative/page", Safelist.relaxed());
    }

    @Test
    void RELAXED_DISALLOWED_SCRIPT_AROUND_CONTENT_variation2() {
        exercise("<p>one<p>two<script>x", "https://example.test/a/b",
                Safelist.relaxed());
    }

    @Test
    void EMPTY_CUSTOM_SAFELIST_variation1() {
        exercise("<p>text</p><b>more</b>", "",
                new Safelist());
    }

    @Test
    void COPIED_NONE_SAFELIST_variation1() {
        exercise("<b>copy</b><script>remove</script>",
                "http://example.test/", new Safelist(Safelist.none()));
    }

    @Test
    void EMPTY_BASE_PRESERVE_RELATIVE_LINKS_variation1() {
        Safelist safelist = new Safelist(Safelist.basic())
                .preserveRelativeLinks(true);
        exercise("<a href=\"relative/page.html\">relative</a>", "", safelist);
    }

    @Test
    void EMPTY_BASE_PRESERVE_RELATIVE_LINKS_variation2() {
        Safelist safelist = new Safelist(Safelist.relaxed())
                .preserveRelativeLinks(true);
        exercise("<p><a href=\"child\">child</a></p>", "", safelist);
    }

    @Test
    void EMPTY_BASE_ABSOLUTE_LINK_PRESERVE_RELATIVE_variation1() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        exercise("<a href=\"https://example.com/path\">absolute</a>",
                "", safelist);
    }

    @Test
    void HTTPS_BASE_RELATIVE_URL_variation1() {
        exercise("<a href=\"../next\">next</a>",
                "https://example.test/a/b/index.html", Safelist.relaxed());
    }

    @Test
    void HTTP_BASE_RELATIVE_URL_variation1() {
        exercise("<a href=\"child\">child</a>",
                "http://example.test/root/", Safelist.relaxed());
    }

    @Test
    void NONEMPTY_NONURL_BASE_variation1() {
        exercise("<p>base independent</p>",
                "relative/base", Safelist.relaxed());
    }

    @Test
    void COMMENT_AND_DECLARATION_INPUT_variation1() {
        exercise("<!-- comment --><p>visible</p><!DOCTYPE html>",
                "relative/base", Safelist.basic());
    }

    @Test
    void UNBALANCED_TAG_INPUT_variation1() {
        exercise("<p>one<p>two<b>three</b>",
                "https://example.test/a/b", Safelist.relaxed());
    }

    @Test
    void UNBALANCED_TAG_INPUT_variation2() {
        exercise("<a href=\"https://example.com\">one<p>two</a>",
                "", Safelist.relaxed());
    }

    @Test
    void NESTED_ALLOWED_AND_DISALLOWED_variation1() {
        exercise("<div><b>inside</b><script>bad</script></div><p>outside</p>",
                "http://example.test/", Safelist.basic());
    }

    @Test
    void NESTED_ALLOWED_AND_DISALLOWED_variation2() {
        exercise("plain text <p>paragraph</p>",
                "https://example.test/", Safelist.basic());
    }

    @Test
    void DUPLICATE_ATTRIBUTE_INPUT_variation1() {
        exercise("<a HREF=\"https://example.com\" href=\"javascript:bad\" "
                        + "title=\"t\">x</a>",
                "relative/base", Safelist.basic());
    }

    @Test
    void WHITESPACE_AND_SIBLINGS_variation1() {
        exercise("  <p>first</p>\n\t<p>second</p>  ",
                "https://example.test/path/index.html", Safelist.relaxed());
    }

    @Test
    void LONG_REPEATED_SAFE_FRAGMENT_variation1() {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            body.append("<p><a href=\"https://example.com/item\">item</a></p>");
        }
        exercise(body.toString(), "", Safelist.relaxed());
    }

    @Test
    void LONG_REPEATED_SAFE_FRAGMENT_variation2() {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            body.append("<p>item ").append(i).append("</p>");
        }
        exercise(body.toString(), "http://example.com/root/",
                new Safelist(Safelist.relaxed()));
    }

    @Test
    void LONG_MIXED_UNSAFE_FRAGMENT_variation1() {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            body.append("<p onclick=\"x\">text</p>")
                    .append("<a href=\"javascript:x\">bad</a>")
                    .append("<script>x</script>");
        }
        exercise(body.toString(), "https://example.com/root/",
                Safelist.basic());
    }

    @Test
    void LONG_MIXED_UNSAFE_FRAGMENT_variation2() {
        exercise("<div><b>safe</b><script>x</script></div>"
                        + "<p title=\"x\">text</p><a href=\"javascript:y\">bad</a>",
                "relative/base", Safelist.basic());
    }

    @Test
    void ALL_TEXT_AFTER_REMOVAL_variation1() {
        exercise("<script><b>visible text</b></script>",
                "https://example.test/path", Safelist.none());
    }
}
