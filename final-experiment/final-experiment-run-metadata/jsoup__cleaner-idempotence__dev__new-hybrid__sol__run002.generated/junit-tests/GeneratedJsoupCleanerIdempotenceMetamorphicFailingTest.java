import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

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

}
