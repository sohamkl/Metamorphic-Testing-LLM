import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

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
    void COPY_CONSTRUCTED_SAFELIST_variation1() {
        Safelist sourcePolicy = Safelist.basic();
        Safelist copiedPolicy = new Safelist(sourcePolicy);
        String html =
                "<p>Accepted paragraph <strong>accepted text</strong></p>"
                        + "<script>rejectedScript()</script><custom>retained descendant</custom>";
        assertMetamorphicRelationFor(source(html, "", copiedPolicy));
    }
}
