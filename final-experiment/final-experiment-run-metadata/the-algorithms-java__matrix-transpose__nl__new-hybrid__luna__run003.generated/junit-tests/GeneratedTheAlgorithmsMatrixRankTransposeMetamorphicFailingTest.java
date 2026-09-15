import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static double[][] generateFollowUp(double[][] source) {
        double[][] transpose = new double[source[0].length][source.length];
        for (int row = 0; row < source.length; row++) {
            for (int column = 0; column < source[row].length; column++) {
                transpose[column][row] = source[row][column];
            }
        }
        return transpose;
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(double[][] source) {
        double[][] snapshot = copy(source);
        int sourceOutput = MatrixRank.computeRank(source);
        assertUnchanged(snapshot, source);

        double[][] followUp = generateFollowUp(source);
        int followUpOutput = MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static double[][] copy(double[][] matrix) {
        double[][] result = new double[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            result[row] = matrix[row].clone();
        }
        return result;
    }

    private static void assertUnchanged(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }

}
