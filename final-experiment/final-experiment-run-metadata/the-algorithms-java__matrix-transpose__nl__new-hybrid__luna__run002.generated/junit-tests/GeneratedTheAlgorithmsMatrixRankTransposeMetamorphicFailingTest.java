import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.thealgorithms.matrix.MatrixRank;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static double[][] transpose(double[][] source) {
        double[][] result = new double[source[0].length][source.length];
        for (int row = 0; row < source.length; row++) {
            for (int column = 0; column < source[0].length; column++) {
                result[column][row] = source[row][column];
            }
        }
        return result;
    }

    private static double[][] copy(double[][] source) {
        double[][] result = new double[source.length][];
        for (int row = 0; row < source.length; row++) {
            result[row] = source[row].clone();
        }
        return result;
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(double[][] source) {
        int sourceOutput = MatrixRank.computeRank(source);
        int followUpOutput = MatrixRank.computeRank(transpose(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
