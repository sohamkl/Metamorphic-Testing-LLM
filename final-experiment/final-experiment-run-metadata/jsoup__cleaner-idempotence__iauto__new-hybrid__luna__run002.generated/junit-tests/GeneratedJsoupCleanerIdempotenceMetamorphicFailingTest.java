import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import jsoupmt.CleanerIdempotenceMetamorphicSpec;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    private static void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning must be idempotent: expected <" + sourceOutput
                            + "> but second cleaning produced <" + followUpOutput + ">");
        }
    }

    private static void verify(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input followUp =
                new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(
                        (String) CleanerIdempotenceMetamorphicSpec
                                .generateFollowUp(bodyHtml, baseUri, safelist)[0],
                        (String) CleanerIdempotenceMetamorphicSpec
                                .generateFollowUp(bodyHtml, baseUri, safelist)[1],
                        (Safelist) CleanerIdempotenceMetamorphicSpec
                                .generateFollowUp(bodyHtml, baseUri, safelist)[2]);
        String followUpOutput = Jsoup.clean(
                followUp.arg0(), followUp.arg1(), followUp.arg2());
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
