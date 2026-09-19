import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Expected equal ranks for a matrix and its transpose, but received "
                            + sourceOutput + " and " + followUpOutput);
        }
    }

}
