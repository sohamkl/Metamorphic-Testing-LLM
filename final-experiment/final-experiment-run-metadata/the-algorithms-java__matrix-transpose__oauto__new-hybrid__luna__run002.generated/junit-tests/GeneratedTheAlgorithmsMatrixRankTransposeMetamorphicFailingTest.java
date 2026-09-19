import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static double[][] generateFollowUp(double[][] source) {
        int rows = source.length;
        int columns = source[0].length;
        double[][] followUp = new double[columns][rows];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                followUp[column][row] = source[row][column];
            }
        }
        return followUp;
    }

    private static void verifyTransposeRelation(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
