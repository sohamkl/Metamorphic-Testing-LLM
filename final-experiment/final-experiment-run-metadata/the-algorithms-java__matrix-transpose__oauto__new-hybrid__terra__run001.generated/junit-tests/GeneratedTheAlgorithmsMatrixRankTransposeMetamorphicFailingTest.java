import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {
    private static double[][] generateFollowUp(double[][] source) {
        double[][] transposed = new double[source[0].length][source.length];
        for (int r = 0; r < source.length; r++) {
            for (int c = 0; c < source[0].length; c++) {
                transposed[c][r] = source[r][c];
            }
        }
        return transposed;
    }

    private static void verify(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
