import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

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

    private static void assertMetamorphicRelationFor(double[][] source, int expectedSourceRank) {
        double[][] followUp = generateFollowUp(source);
        int sourceOutput = MatrixRank.computeRank(source);
        int followUpOutput = MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_MATRICES_ACROSS_SHAPES_variation1_singleton() {
        double[][] source = { { 0.0 } };
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void ZERO_MATRICES_ACROSS_SHAPES_variation2_wideAliasedRows() {
        double[] zeroRow = { 0.0, 0.0, 0.0 };
        double[][] source = { zeroRow, zeroRow };
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void ZERO_MATRICES_ACROSS_SHAPES_variation3_tall() {
        double[][] source = { { 0.0, 0.0 }, { 0.0, 0.0 }, { 0.0, 0.0 } };
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void NEGATIVE_ZERO_MATRIX_variation1_mixedSignedZeros() {
        double[][] source = { { -0.0, 0.0 }, { 0.0, -0.0 } };
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void SCALAR_EPSILON_BOUNDARIES_variation1_belowEpsilon() {
        double[][] source = { { 5.0e-11 } };
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void SCALAR_EPSILON_BOUNDARIES_variation2_positiveExactEpsilon() {
        double[][] source = { { 1.0e-10 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void SCALAR_EPSILON_BOUNDARIES_variation3_negativeExactEpsilon() {
        double[][] source = { { -1.0e-10 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void SCALAR_EPSILON_BOUNDARIES_variation4_aboveEpsilon() {
        double[][] source = { { 1.1e-10 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void NONZERO_ROW_VECTORS_variation1_lateNonzeroEntry() {
        double[][] source = { { 0.0, 0.0, 7.0, 0.0 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void NONZERO_ROW_VECTORS_variation2_twoEntries() {
        double[][] source = { { 2.0, -1.0 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void NONZERO_COLUMN_VECTORS_variation1_lateRowPivot() {
        double[][] source = { { 0.0 }, { -3.0 }, { 0.0 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void NONZERO_COLUMN_VECTORS_variation2_proportionalEntries() {
        double[][] source = { { 2.0 }, { 4.0 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void IDENTITY_MATRICES_variation1_twoByTwo() {
        double[][] source = { { 1.0, 0.0 }, { 0.0, 1.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void IDENTITY_MATRICES_variation2_fourByFour() {
        double[][] source = { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
        assertMetamorphicRelationFor(source, 4);
    }

    @Test
    void DIAGONAL_MATRICES_WITH_GAPS_variation1_threeByThree() {
        double[][] source = { { 0.0, 0.0, 0.0 }, { 0.0, 2.0, 0.0 }, { 0.0, 0.0, 0.0 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void DIAGONAL_MATRICES_WITH_GAPS_variation2_fourByFour() {
        double[][] source = { { 3.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, -2.0, 0.0 }, { 0.0, 0.0, 0.0, 5.0 } };
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    void FULL_RANK_TRIANGULAR_MATRICES_variation1_upperTriangular() {
        double[][] source = { { 2.0, 1.0, 0.0 }, { 0.0, -3.0, 4.0 }, { 0.0, 0.0, 5.0 } };
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    void FULL_RANK_TRIANGULAR_MATRICES_variation2_lowerTriangular() {
        double[][] source = { { 1.0, 0.0, 0.0 }, { 2.0, 1.0, 0.0 }, { 3.0, 4.0, 1.0 } };
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    void FULL_ROW_RANK_WIDE_MATRICES_variation1_twoByFour() {
        double[][] source = { { 1.0, 0.0, 2.0, 0.0 }, { 0.0, 1.0, 3.0, 4.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void FULL_ROW_RANK_WIDE_MATRICES_variation2_threeByFive() {
        double[][] source = { { 1.0, 0.0, 0.0, 2.0, 3.0 }, { 0.0, 1.0, 0.0, 4.0, 5.0 }, { 0.0, 0.0, 1.0, 6.0, 7.0 } };
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    void FULL_COLUMN_RANK_TALL_MATRICES_variation1_fourByTwo() {
        double[][] source = { { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 }, { 2.0, -1.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void FULL_COLUMN_RANK_TALL_MATRICES_variation2_fiveByThree() {
        double[][] source = { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 1.0, 1.0, 0.0 }, { 0.0, 1.0, 1.0 } };
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    void PROPORTIONAL_ROWS_variation1_signedMultiples() {
        double[][] source = { { 1.0, 2.0, 3.0 }, { -2.0, -4.0, -6.0 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void PROPORTIONAL_ROWS_variation2_withZeroRow() {
        double[][] source = { { 1.0, 2.0 }, { 2.0, 4.0 }, { 0.0, 0.0 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_variation1_zeroRowsAroundIndependentRows() {
        double[][] source = { { 0.0, 0.0, 0.0 }, { 1.0, 2.0, 3.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 1.0, 1.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_variation2_leadingZeroRow() {
        double[][] source = { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void ZERO_COLUMNS_WITH_LATER_PIVOTS_variation1_leadingZeroColumn() {
        double[][] source = { { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void ZERO_COLUMNS_WITH_LATER_PIVOTS_variation2_intermediateZeroColumn() {
        double[][] source = { { 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 1.0, 0.0, 1.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void DUPLICATE_ROWS_PLUS_INDEPENDENT_ROW_variation1() {
        double[][] source = { { 1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0 }, { 0.0, 1.0, 0.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void ALL_EQUAL_RECTANGULAR_MATRIX_variation1_threeByFour() {
        double[][] source = { { 1.0, 1.0, 1.0, 1.0 }, { 1.0, 1.0, 1.0, 1.0 }, { 1.0, 1.0, 1.0, 1.0 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void DELAYED_AND_OUT_OF_ORDER_PIVOTS_variation1() {
        double[][] source = { { 0.0, 1.0, 0.0 }, { 2.0, 0.0, 1.0 }, { 0.0, 0.0, 1.0 } };
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    void ELIMINATION_CREATES_LATER_PIVOT_variation1() {
        double[][] source = { { 1.0, 2.0 }, { 1.0, 3.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void ELIMINATION_REVEALS_DEPENDENCE_variation1() {
        double[][] source = { { 1.0, 2.0, 3.0 }, { 2.0, 4.0, 6.0 }, { 1.0, 1.0, 1.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void WIDE_LINEAR_COMBINATION_RANK_TWO_variation1() {
        double[][] source = { { 1.0, 0.0, 2.0, 1.0 }, { 0.0, 1.0, -1.0, 3.0 }, { 1.0, 1.0, 1.0, 4.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void TALL_LINEAR_COMBINATION_RANK_THREE_variation1() {
        double[][] source = { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 1.0, 1.0, 1.0 } };
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    void REVERSE_PERMUTATION_MATRIX_variation1_fourByFour() {
        double[][] source = { { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 } };
        assertMetamorphicRelationFor(source, 4);
    }

    @Test
    void NEGATIVE_AND_FRACTIONAL_ENTRIES_variation1() {
        double[][] source = { { -0.5, 1.5 }, { 2.0, -1.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void MIXED_MAGNITUDE_DIAGONALS_variation1_bothPivotsAboveTolerance() {
        double[][] source = { { 1.0e-9, 0.0 }, { 0.0, 1.0e9 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void MIXED_MAGNITUDE_DIAGONALS_variation2_onePivotSuppressed() {
        double[][] source = { { 1.0e-11, 0.0 }, { 0.0, 1.0e5 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void MULTI_ENTRY_EPSILON_BOUNDARY_variation1_belowTolerance() {
        double[][] source = { { 9.0e-11, 0.0 }, { 0.0, -9.0e-11 } };
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void MULTI_ENTRY_EPSILON_BOUNDARY_variation2_exactTolerance() {
        double[][] source = { { 1.0e-10, 0.0 }, { 0.0, -1.0e-10 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void CANCELLATION_RESIDUAL_AROUND_EPSILON_variation1_belowToleranceResidual() {
        double[][] source = { { 1.0, 1.0 }, { 1.0, 1.00000000005 } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void CANCELLATION_RESIDUAL_AROUND_EPSILON_variation2_aboveToleranceResidual() {
        double[][] source = { { 1.0, 1.0 }, { 1.0, 1.0000000002 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void EXTREME_FINITE_NORMALIZATION_variation1_sourceRemainsUnchanged() {
        double[][] source = { { 1.0e-10, 1.0e308 }, { 1.0, 0.0 } };
        double[] firstRowBefore = source[0].clone();
        double[] secondRowBefore = source[1].clone();
        assertMetamorphicRelationFor(source, 2);
        for (double[] row : source) {
            for (double value : row) {
            }
        }
    }

    @Test
    void MINIMUM_AND_MAXIMUM_FINITE_SCALARS_variation1_minimumSubnormal() {
        double[][] source = { { Double.MIN_VALUE } };
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void MINIMUM_AND_MAXIMUM_FINITE_SCALARS_variation2_maximumFinite() {
        double[][] source = { { Double.MAX_VALUE } };
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void ALIASED_SOURCE_ROWS_variation1_aliasPreserved() {
        double[] sharedRow = { 1.0, 2.0 };
        double[][] source = { sharedRow, sharedRow, new double[] { 0.0, 1.0 } };
        double[] sharedValuesBefore = sharedRow.clone();
        double[] independentValuesBefore = source[2].clone();
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void LARGER_TALL_FULL_COLUMN_RANK_variation1_sevenByFive() {
        double[][] source = { { 1.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 1.0 }, { 1.0, 1.0, 1.0, 1.0, 1.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0 } };
        assertMetamorphicRelationFor(source, 5);
    }

    @Test
    void BELOW_EPSILON_CANDIDATE_SKIPPED_variation1_lowerRowFirstPivot() {
        double[][] source = { { 5.0e-11, 2.0 }, { 1.0, 0.0 } };
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void LAST_COLUMN_ONLY_RANK_variation1() {
        double[][] source = { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 2.0 }, { 0.0, 0.0, -3.0 } };
        assertMetamorphicRelationFor(source, 1);
    }
}
