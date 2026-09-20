import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    private static String createDeeplyNestedFragment() {
        StringBuilder html = new StringBuilder(6000);
        for (int i = 0; i < 80; i++) {
            html.append(i % 2 == 0 ? "<div>" : "<section>");
        }
        html.append("deepest-visible-text-");
        for (int i = 0; i < 4300; i++) {
            html.append((char) ('a' + (i % 26)));
        }
        for (int i = 79; i >= 0; i--) {
            html.append(i % 2 == 0 ? "</div>" : "</section>");
        }
        return html.toString();
    }

    private static void exercise(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);

        Object[] followUp =
                CleanerIdempotenceMetamorphicSpec.generateFollowUp(bodyHtml, baseUri, safelist);

        String followUpOutput = Jsoup.clean(
                (String) followUp[0],
                (String) followUp[1],
                (Safelist) followUp[2]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning the follow-up HTML must produce exactly the source output");
        }
    }
}
