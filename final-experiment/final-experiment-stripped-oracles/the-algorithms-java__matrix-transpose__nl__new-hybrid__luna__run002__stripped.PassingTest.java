import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.thealgorithms.matrix.MatrixRank;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

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

    @Test
    void SINGLE_ZERO_ENTRY_variation1() {
        double[][] source = { { 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_NONZERO_ENTRY_variation1() {
        double[][] source = { { -1.0e-10 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ALL_ZERO_RECTANGLE_variation1() {
        double[][] source = { { 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_NONZERO_ROW_variation1() {
        double[][] source = { { 2.0, -3.0, 1.0, 4.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_NONZERO_COLUMN_variation1() {
        double[][] source = { { 0.0 }, { -2.0 }, { 0.0 }, { 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SQUARE_FULL_RANK_variation1() {
        double[][] source = { { 2.0, 0.0, 0.0 }, { 0.0, -3.0, 0.0 }, { 0.0, 0.0, 4.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation1() {
        double[][] source = { { 1.0, 0.0 }, { 0.0, 1.0 }, { 2.0, 3.0 }, { -1.0, 4.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation1() {
        double[][] source = { { 1.0, 0.0, 1.0, 0.0 }, { 0.0, 1.0, 0.0, 1.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DUPLICATE_ROWS_variation1() {
        double[][] source = { { 1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0 }, { 0.0, 0.0, 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void PROPORTIONAL_ROWS_variation1() {
        double[][] source = { { 2.0, 4.0 }, { -6.0, -12.0 }, { 0.0, 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DUPLICATE_COLUMNS_variation1() {
        double[][] source = { { 1.0, 1.0, 0.0 }, { 2.0, 2.0, 0.0 }, { 3.0, 3.0, 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LEADING_ZERO_COLUMN_variation1() {
        double[][] source = { { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void PIVOT_BELOW_FIRST_ROW_variation1() {
        double[][] source = { { 0.0, 0.0 }, { 2.0, 1.0 }, { 0.0, 3.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MARKED_ROW_SKIPPED_variation1() {
        double[][] source = { { 1.0, 2.0 }, { 0.0, 3.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ZERO_PIVOT_AFTER_ELIMINATION_variation1() {
        double[][] source = { { 1.0, 2.0, 3.0 }, { 2.0, 4.0, 6.0 }, { 0.0, 1.0, 1.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_AND_MIXED_SIGN_VALUES_variation1() {
        double[][] source = { { -2.0, 3.0, -4.0 }, { 5.0, -7.0, 11.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SIGNED_ZERO_ENTRIES_variation1() {
        double[][] source = { { 0.0, -0.0, 2.0 }, { -0.0, 3.0, 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_BELOW_EPSILON_variation1() {
        double[][] source = { { 0.5e-10, 1.0 }, { 0.0, 0.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void EXACT_EPSILON_variation1() {
        double[][] source = { { 1.0e-10, 0.0 }, { 0.0, 1.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_ABOVE_EPSILON_variation1() {
        double[][] source = { { 1.0001e-10, 0.0 }, { 0.0, 1.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LARGE_FINITE_VALUES_variation1() {
        double[][] source = { { 1.0e200, 2.0e200 }, { 3.0e200, 5.0e200 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SMALL_FINITE_VALUES_variation1() {
        double[][] source = { { 1.0e-12, 0.0 }, { 0.0, 2.0e-12 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NAN_ENTRY_variation1() {
        double[][] source = { { Double.NaN, 0.0 }, { 0.0, 1.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INFINITE_ENTRY_variation1() {
        double[][] source = { { Double.POSITIVE_INFINITY, 0.0 }, { 0.0, Double.NEGATIVE_INFINITY } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void TRANSPOSE_ORIENTATION_CHANGE_variation1() {
        double[][] source = { { 0.0, 2.0, 3.0, 0.0 }, { 4.0, 0.0, 6.0, 8.0 } };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SOURCE_DEEP_COPY_OBSERVATION_variation1() {
        double[][] source = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 }, { 7.0, 8.0, 10.0 } };
        double[][] original = copy(source);
        assertMetamorphicRelationFor(source);
        for (int row = 0; row < source.length; row++) {
        }
    }
}
