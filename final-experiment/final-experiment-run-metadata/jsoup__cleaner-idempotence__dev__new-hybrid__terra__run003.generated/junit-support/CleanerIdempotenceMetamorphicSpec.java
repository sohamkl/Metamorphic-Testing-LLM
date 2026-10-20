package jsoupmt;

import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/** Developer-owned idempotence relation for jsoup HTML cleaning. */
public final class CleanerIdempotenceMetamorphicSpec {
    private CleanerIdempotenceMetamorphicSpec() {
    }

    public static Object[] generateFollowUp(String bodyHtml, String baseUri, Safelist safelist) {
        Objects.requireNonNull(bodyHtml, "bodyHtml");
        Objects.requireNonNull(baseUri, "baseUri");
        Objects.requireNonNull(safelist, "safelist");

        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        return new Object[]{cleanedHtml, baseUri, safelist};
    }

    public static void assertRelation(String sourceOutput, String followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");

        if (!sourceOutput.equals(followUpOutput)) {
            throw new AssertionError(
                    "Cleaning must be idempotent: expected <" + sourceOutput
                            + "> but second cleaning produced <" + followUpOutput + ">");
        }
    }
}
