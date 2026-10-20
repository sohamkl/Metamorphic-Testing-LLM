import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {
    private static final class CleanInput {
        private final String bodyHtml;
        private final String baseUri;
        private final Safelist safelist;

        private CleanInput(String bodyHtml, String baseUri, Safelist safelist) {
            this.bodyHtml = bodyHtml;
            this.baseUri = baseUri;
            this.safelist = safelist;
        }
    }

    private CleanInput generateFollowUp(CleanInput source, String sourceOutput) {
        return new CleanInput(sourceOutput, source.baseUri, source.safelist);
    }

    private String run(CleanInput input) {
        return Jsoup.clean(input.bodyHtml, input.baseUri, input.safelist);
    }

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelationFor(CleanInput source) {
        String sourceOutput = run(source);
        CleanInput followUp = generateFollowUp(source, sourceOutput);
        String followUpOutput = run(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
