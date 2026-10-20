import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static void assertMetamorphicRelationFor(double[][] source, int expectedSourceRank) {
        double[][] sourceSnapshot = copyOf(source);
        int sourceOutput = MatrixRank.computeRank(source);
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

    @Test
    void ZERO_SCALAR_zeroEntry() {
        assertMetamorphicRelationFor(new double[][] { { 0.0 } }, 0);
    }

    @Test
    void NONZERO_SCALAR_normalPivot() {
        assertMetamorphicRelationFor(new double[][] { { 7.0 } }, 1);
    }

    @Test
    void SUB_EPSILON_SCALAR_belowThreshold() {
        assertMetamorphicRelationFor(new double[][] { { 5.0e-11 } }, 0);
    }

    @Test
    void EXACT_EPSILON_SCALAR_strictThreshold() {
        assertMetamorphicRelationFor(new double[][] { { 1.0e-10 } }, 1);
    }

    @Test
    void NEGATIVE_EPSILON_SCALAR_negativeThreshold() {
        assertMetamorphicRelationFor(new double[][] { { -1.0e-10 } }, 1);
    }

    @Test
    void SINGLE_ROW_LATE_PIVOT_skippedColumns() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 0.0, 4.0, 0.0, -2.0 } }, 1);
    }

    @Test
    void SINGLE_COLUMN_LOWER_PIVOT_lowerRow() {
        assertMetamorphicRelationFor(new double[][] { { 0.0 }, { 0.0 }, { -3.0 }, { 5.0 } }, 1);
    }

    @Test
    void ALL_ZERO_WIDE_noPivots() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, 0);
    }

    @Test
    void ALL_ZERO_TALL_noPivots() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 0.0 }, { 0.0, 0.0 }, { 0.0, 0.0 } }, 0);
    }

    @Test
    void LAST_COLUMN_ONLY_PIVOT_noTrailingColumns() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 0.0 }, { 0.0, 5.0 } }, 1);
    }

    @Test
    void FULL_RANK_TWO_BY_TWO_ELIMINATION_positiveFactor() {
        assertMetamorphicRelationFor(new double[][] { { 2.0, 4.0 }, { 1.0, 3.0 } }, 2);
    }

    @Test
    void EXACT_DEPENDENT_TWO_BY_TWO_zeroResidual() {
        assertMetamorphicRelationFor(new double[][] { { 2.0, 4.0 }, { 1.0, 2.0 } }, 1);
    }

    @Test
    void SUB_EPSILON_ELIMINATION_RESIDUAL_smallResidual() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 1.0 }, { 1.0, 1.00000000005 } }, 1);
    }

    @Test
    void ABOVE_EPSILON_DIAGONAL_twoPivots() {
        assertMetamorphicRelationFor(new double[][] { { 2.0e-10, 0.0 }, { 0.0, 3.0e-10 } }, 2);
    }

    @Test
    void SUB_EPSILON_DIAGONAL_allClassifiedZero() {
        assertMetamorphicRelationFor(new double[][] { { 5.0e-11, 0.0 }, { 0.0, -5.0e-11 } }, 0);
    }

    @Test
    void MARKED_ROW_SKIPPED_IN_LATER_COLUMN_unmarkedSecondRow() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 1.0 }, { 0.0, 1.0 } }, 2);
    }

    @Test
    void ROW_SWAP_STYLE_PIVOT_ORDER_lowerThenUpper() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 1.0 }, { 1.0, 0.0 } }, 2);
    }

    @Test
    void THREE_BY_THREE_PERMUTATION_reversePivotRows() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 0.0, 1.0 }, { 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0 } }, 3);
    }

    @Test
    void LEADING_ZERO_COLUMN_THEN_TWO_PIVOTS_skippedFirstColumn() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 0.0, 1.0, 1.0 } }, 2);
    }

    @Test
    void FULL_ROW_RANK_WIDE_rankLimitedByRows() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 0.0, 0.0, 2.0 }, { 0.0, 1.0, 0.0, -1.0 } }, 2);
    }

    @Test
    void FULL_COLUMN_RANK_TALL_rankLimitedByColumns() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 }, { 2.0, -1.0 } }, 2);
    }

    @Test
    void RANK_ONE_RECTANGULAR_OUTER_PRODUCT_proportionalRows() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 2.0, -1.0, 3.0 }, { 2.0, 4.0, -2.0, 6.0 }, { 0.0, 0.0, 0.0, 0.0 } }, 1);
    }

    @Test
    void RANK_TWO_TALL_DEPENDENT_ROWS_linearCombinations() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 0.0, 1.0 }, { 0.0, 1.0, 1.0 }, { 1.0, 1.0, 2.0 }, { 2.0, -1.0, 1.0 } }, 2);
    }

    @Test
    void RANK_THREE_SQUARE_WITH_DEPENDENT_ROW_sumRow() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 1.0, 1.0, 1.0, 0.0 } }, 3);
    }

    @Test
    void FULL_RANK_THREE_BY_THREE_FRACTIONAL_normalization() {
        assertMetamorphicRelationFor(new double[][] { { 0.5, 1.0, 0.0 }, { 1.0, 3.0, 1.0 }, { 0.0, 1.0, 2.0 } }, 3);
    }

    @Test
    void NEGATIVE_ELIMINATION_FACTOR_negativeEntry() {
        assertMetamorphicRelationFor(new double[][] { { 2.0, 1.0 }, { -3.0, 4.0 } }, 2);
    }

    @Test
    void ELIMINATION_SKIPS_SUB_EPSILON_OTHER_ROW_smallOtherFactor() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 0.0 }, { 5.0e-11, 1.0 } }, 2);
    }

    @Test
    void MIXED_SCALE_DIAGONAL_smallAndLargePivots() {
        assertMetamorphicRelationFor(new double[][] { { 1.0e-9, 0.0 }, { 0.0, 1.0e9 } }, 2);
    }

    @Test
    void LARGE_SMALL_THREE_BY_THREE_DIAGONAL_scaleVariation() {
        assertMetamorphicRelationFor(new double[][] { { 1.0e100, 0.0, 0.0 }, { 0.0, 1.0e-8, 0.0 }, { 0.0, 0.0, -1.0e50 } }, 3);
    }

    @Test
    void SPARSE_FIVE_BY_THREE_FULL_COLUMN_RANK_separatePivotRows() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, 3);
    }

    @Test
    void SPARSE_THREE_BY_FIVE_FULL_ROW_RANK_sparseWide() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 0.0 } }, 3);
    }

    @Test
    void ZERO_LEADING_ROW_WITH_FULL_COLUMN_RANK_laterRowsProvidePivots() {
        assertMetamorphicRelationFor(new double[][] { { 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 1.0, 1.0, 1.0 } }, 3);
    }

    @Test
    void WIDE_MATRIX_WITH_ZERO_TRAILING_COLUMNS_repeatedNoPivot() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0, 0.0 } }, 2);
    }

    @Test
    void THREE_BY_THREE_RANK_TWO_NONADJACENT_PIVOTS_dependentMiddleColumn() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 2.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 1.0, 2.0, 1.0 } }, 2);
    }

    @Test
    void FIVE_BY_FIVE_IDENTITY_allDiagonalPivots() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 1.0 } }, 5);
    }

    @Test
    void FIVE_BY_FIVE_RANK_ONE_CONSTANT_identicalRows() {
        assertMetamorphicRelationFor(new double[][] { { 1.0, 1.0, 1.0, 1.0, 1.0 }, { 1.0, 1.0, 1.0, 1.0, 1.0 }, { 1.0, 1.0, 1.0, 1.0, 1.0 }, { 1.0, 1.0, 1.0, 1.0, 1.0 }, { 1.0, 1.0, 1.0, 1.0, 1.0 } }, 1);
    }
}
