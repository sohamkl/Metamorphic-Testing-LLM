import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static double[][] generateFollowUp(double[][] source) {
        double[][] transpose = new double[source[0].length][source.length];
        for (int row = 0; row < source.length; row++) {
            for (int column = 0; column < source[0].length; column++) {
                transpose[column][row] = source[row][column];
            }
        }
        return transpose;
    }

    private static void assertMetamorphicRelationFor(double[][] source) {
        int sourceOutput = MatrixRank.computeRank(source);
        int followUpOutput = MatrixRank.computeRank(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static double[][] copy(double[][] matrix) {
        double[][] result = new double[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            result[row] = matrix[row].clone();
        }
        return result;
    }

    private static void assertBitwiseEqual(double[][] expected, double[][] actual) {
        Assertions.assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            Assertions.assertEquals(expected[row].length, actual[row].length);
            for (int column = 0; column < expected[row].length; column++) {
                Assertions.assertEquals(
                    Double.doubleToLongBits(expected[row][column]),
                    Double.doubleToLongBits(actual[row][column]));
            }
        }
    }

}
