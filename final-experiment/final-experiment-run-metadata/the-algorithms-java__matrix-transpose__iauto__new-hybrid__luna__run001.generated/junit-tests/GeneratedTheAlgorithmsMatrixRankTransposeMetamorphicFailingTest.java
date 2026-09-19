import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                "Expected equal ranks for the source and transposed matrices, but received "
                    + sourceOutput + " and " + followUpOutput);
        }
    }
}
