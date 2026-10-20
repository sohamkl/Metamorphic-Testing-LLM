import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                "Expected source and transposed matrix ranks to be equal, but they were "
                    + sourceOutput + " and " + followUpOutput);
        }
    }
}
