import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Rank must remain unchanged after transposition: source="
                            + sourceOutput
                            + ", follow-up="
                            + followUpOutput);
        }
    }
}
