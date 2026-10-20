import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static void verify(double[][] source, int expectedSourceRank) {
        long[][] sourceBitsBefore = rawBits(source);
        int sourceOutput = MatrixRank.computeRank(source);
        Assertions.assertEquals(expectedSourceRank, sourceOutput);
        assertRawBitsEqual(sourceBitsBefore, source);

        double[][] followUp = generateFollowUp(source);
        int followUpOutput = MatrixRank.computeRank(followUp);

        assertRawBitsEqual(sourceBitsBefore, source);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static double[][] generateFollowUp(double[][] source) {
        double[][] transpose = new double[source[0].length][source.length];
        for (int row = 0; row < source.length; row++) {
            for (int column = 0; column < source[0].length; column++) {
                transpose[column][row] = source[row][column];
            }
        }
        return transpose;
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static long[][] rawBits(double[][] matrix) {
        long[][] bits = new long[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                bits[row][column] = Double.doubleToRawLongBits(matrix[row][column]);
            }
        }
        return bits;
    }

    private static void assertRawBitsEqual(long[][] expectedBits, double[][] actual) {
        Assertions.assertEquals(expectedBits.length, actual.length);
        for (int row = 0; row < expectedBits.length; row++) {
            Assertions.assertEquals(expectedBits[row].length, actual[row].length);
            for (int column = 0; column < expectedBits[row].length; column++) {
                Assertions.assertEquals(
                    expectedBits[row][column],
                    Double.doubleToRawLongBits(actual[row][column]));
            }
        }
    }
}
