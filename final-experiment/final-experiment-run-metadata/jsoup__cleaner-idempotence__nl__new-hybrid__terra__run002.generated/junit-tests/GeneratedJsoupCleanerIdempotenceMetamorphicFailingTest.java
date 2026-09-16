import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {
    private static final class Source {
        private final String bodyHtml;
        private final String baseUri;
        private final Safelist safelist;

        private Source(String bodyHtml, String baseUri, Safelist safelist) {
            this.bodyHtml = bodyHtml;
            this.baseUri = baseUri;
            this.safelist = safelist;
        }
    }

    private Source generateFollowUp(Source source, String sourceOutput) {
        return new Source(sourceOutput, source.baseUri, source.safelist);
    }

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelationFor(Source source) {
        String sourceOutput = Jsoup.clean(source.bodyHtml, source.baseUri, source.safelist);
        Source followUp = generateFollowUp(source, sourceOutput);
        Assertions.assertSame(source.safelist, followUp.safelist);
        Assertions.assertEquals(source.baseUri, followUp.baseUri);
        String followUpOutput = Jsoup.clean(followUp.bodyHtml, followUp.baseUri, followUp.safelist);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void verify(String bodyHtml, String baseUri, Safelist safelist) {
        assertMetamorphicRelationFor(new Source(bodyHtml, baseUri, safelist));
    }

}
