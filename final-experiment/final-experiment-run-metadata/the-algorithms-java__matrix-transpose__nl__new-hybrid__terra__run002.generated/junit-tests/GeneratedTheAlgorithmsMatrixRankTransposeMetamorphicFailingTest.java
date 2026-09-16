import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static void assertMetamorphicRelationFor(double[][] source, int expectedSourceRank) {
        double[][] sourceSnapshot = copyOf(source);
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(expectedSourceRank, sourceOutput);
        assertRankInRange(sourceOutput, source.length, source[0].length);
        assertMatrixEquals(sourceSnapshot, source);

        double[][] followUp = generateFollowUp(source);
        int followUpOutput = MatrixRank.computeRank(followUp);
        assertRankInRange(followUpOutput, followUp.length, followUp[0].length);
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
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertRankInRange(int rank, int rows, int columns) {
        assertTrue(rank >= 0);
        assertTrue(rank <= Math.min(rows, columns));
    }

    private static double[][] copyOf(double[][] matrix) {
        double[][] copy = new double[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row].clone();
        }
        return copy;
    }

    private static void assertMatrixEquals(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }
}
