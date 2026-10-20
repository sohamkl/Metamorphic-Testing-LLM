import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Rank must be invariant under transpose: source="
                            + sourceOutput
                            + ", follow-up="
                            + followUpOutput);
        }
    }

}
