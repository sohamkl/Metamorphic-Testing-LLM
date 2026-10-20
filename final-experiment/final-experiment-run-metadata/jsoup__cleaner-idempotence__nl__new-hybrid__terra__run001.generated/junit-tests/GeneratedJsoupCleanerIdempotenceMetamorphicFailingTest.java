import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratedJsoupCleanerIdempotenceMetamorphicFailingTest {

    private String generateFollowUp(String sourceOutput) {
        return sourceOutput;
    }

    private void assertMetamorphicRelationFor(String bodyHtml, String baseUri, Safelist safelist) {
        String sourceOutput = Jsoup.clean(bodyHtml, baseUri, safelist);
        String followUpHtml = generateFollowUp(sourceOutput);
        String followUpOutput = Jsoup.clean(followUpHtml, baseUri, safelist);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(String sourceOutput, String followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

}
