import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {
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

}
