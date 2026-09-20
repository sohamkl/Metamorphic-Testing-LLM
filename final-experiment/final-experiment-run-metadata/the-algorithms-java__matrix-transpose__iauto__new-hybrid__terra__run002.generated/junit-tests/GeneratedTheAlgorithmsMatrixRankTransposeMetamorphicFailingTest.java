import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Expected source and transposed matrices to have the same rank, but got "
                            + sourceOutput + " and " + followUpOutput);
        }
    }

}
