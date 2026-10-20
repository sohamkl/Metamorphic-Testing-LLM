import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicFailingTest {

    private static double[][] generateFollowUp(double[][] source) {
        int rows = source.length;
        int columns = source[0].length;
        double[][] transpose = new double[columns][rows];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                transpose[column][row] = source[row][column];
            }
        }
        return transpose;
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(double[][] source) {
        int sourceOutput = MatrixRank.computeRank(source);
        int followUpOutput = MatrixRank.computeRank(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
