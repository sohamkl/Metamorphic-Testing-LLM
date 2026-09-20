import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static final double E = 1e-10;

    private static double[][] generateFollowUp(double[][] source) {
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

}
