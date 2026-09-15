import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.safety.Safelist;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {
    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input source(
            String bodyHtml, String baseUri, Safelist safelist) {
        return new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input(bodyHtml, baseUri, safelist);
    }

    private static MtllmGeneratedJsoupCleanInvocationbsm8w3.Input generateFollowUp(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input original, String cleanedHtml) {
        return source(cleanedHtml, original.arg1(), original.arg2());
    }

    private static void assertMetamorphicRelation(
            String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedJsoupCleanInvocationbsm8w3.Input original) {
        String firstOutput =
                MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(original);
        String followUpOutput =
                MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(
                        generateFollowUp(original, firstOutput));
        assertMetamorphicRelation(firstOutput, followUpOutput);
    }

}
