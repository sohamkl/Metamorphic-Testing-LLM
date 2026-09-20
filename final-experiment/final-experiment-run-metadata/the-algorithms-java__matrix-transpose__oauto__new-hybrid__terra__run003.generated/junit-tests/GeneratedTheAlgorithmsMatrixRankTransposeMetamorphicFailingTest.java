import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private double[][] generateFollowUp(double[][] source) {
        int rows = source.length;
        int columns = source[0].length;
        double[][] transposed = new double[columns][rows];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                transposed[column][row] = source[row][column];
            }
        }
        return transposed;
    }

    @Test
    void NAN_TRAILING_ENTRY_PROPAGATION_variation1() {
        double[][] source = {{1.0, Double.NaN}, {0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INFINITY_TIMES_ZERO_PROPAGATION_variation1() {
        double[][] source = {{1.0, 0.0}, {Double.POSITIVE_INFINITY, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
