import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    private static void exercise(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);

        Object[] followUp = CleanerIdempotenceMetamorphicSpec.generateFollowUp(
                bodyHtml, baseUri, safelist);

        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);

        CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static String nestedBoldHtml(int depth, String text) {
        StringBuilder html = new StringBuilder(depth * 7 + text.length());
        for (int i = 0; i < depth; i++) {
            html.append("<b>");
        }
        html.append(text);
        for (int i = 0; i < depth; i++) {
            html.append("</b>");
        }
        return html.toString();
    }

    private static String repeatedCharacter(char character, int count) {
        StringBuilder value = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            value.append(character);
        }
        return value.toString();
    }

}
