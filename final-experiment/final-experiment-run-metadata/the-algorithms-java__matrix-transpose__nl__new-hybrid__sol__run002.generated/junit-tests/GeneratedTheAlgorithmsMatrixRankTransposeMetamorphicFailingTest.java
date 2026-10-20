import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Assertions;
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

    private static void assertMetamorphicRelationFor(double[][] source) {
        double[][] sourceSnapshot = copyMatrix(source);
        double[][] sourceRowReferences = source.clone();

        double[][] followUp = generateFollowUp(source);
        double[][] followUpSnapshot = copyMatrix(followUp);
        double[][] followUpRowReferences = followUp.clone();

        int sourceOutput = MatrixRank.computeRank(source);
        assertMatrixUnchanged(source, sourceSnapshot, sourceRowReferences);

        int followUpOutput = MatrixRank.computeRank(followUp);
        assertMatrixUnchanged(source, sourceSnapshot, sourceRowReferences);
        assertMatrixUnchanged(followUp, followUpSnapshot, followUpRowReferences);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static double[][] copyMatrix(double[][] matrix) {
        double[][] copy = new double[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row].clone();
        }
        return copy;
    }

    private static void assertMatrixUnchanged(
        double[][] actual, double[][] expectedValues, double[][] expectedRowReferences) {
        Assertions.assertEquals(expectedValues.length, actual.length);
        for (int row = 0; row < actual.length; row++) {
            Assertions.assertSame(expectedRowReferences[row], actual[row]);
            Assertions.assertEquals(expectedValues[row].length, actual[row].length);
            for (int column = 0; column < actual[row].length; column++) {
                Assertions.assertEquals(
                    Double.doubleToRawLongBits(expectedValues[row][column]),
                    Double.doubleToRawLongBits(actual[row][column]));
            }
        }
    }

    private static double[][] identity(int size) {
        double[][] matrix = new double[size][size];
        for (int index = 0; index < size; index++) {
            matrix[index][index] = 1.0;
        }
        return matrix;
    }

}
