import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {
    private static double[][] generateFollowUp(double[][] source) {
        double[][] transposed = new double[source[0].length][source.length];
        for (int r = 0; r < source.length; r++) {
            for (int c = 0; c < source[0].length; c++) {
                transposed[c][r] = source[r][c];
            }
        }
        return transposed;
    }

    private static void verify(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ZERO_SENTINEL_zero() {
        verify(new double[][] {{0.0}});
    }

    @Test
    void SINGLE_NONZERO_positive() {
        verify(new double[][] {{7.0}});
    }

    @Test
    void SINGLE_NONZERO_negative() {
        verify(new double[][] {{-3.5}});
    }

    @Test
    void ZERO_RECTANGULAR_MATRICES_tall() {
        verify(new double[][] {{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}});
    }

    @Test
    void ZERO_RECTANGULAR_MATRICES_wide() {
        verify(new double[][] {{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}});
    }

    @Test
    void FULL_COLUMN_RANK_TALL_threeByTwo() {
        verify(new double[][] {{1.0, 0.0}, {0.0, 1.0}, {0.0, 0.0}});
    }

    @Test
    void FULL_COLUMN_RANK_TALL_fourByThree() {
        verify(new double[][] {{1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}, {0.0, 0.0, 0.0}});
    }

    @Test
    void FULL_ROW_RANK_WIDE_twoByThree() {
        verify(new double[][] {{1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}});
    }

    @Test
    void FULL_ROW_RANK_WIDE_threeByFour() {
        verify(new double[][] {{1.0, 0.0, 0.0, 0.0}, {0.0, 1.0, 0.0, 0.0}, {0.0, 0.0, 1.0, 0.0}});
    }

    @Test
    void SQUARE_DIAGONAL_FULL_RANK_twoByTwo() {
        verify(new double[][] {{2.0, 0.0}, {0.0, 5.0}});
    }

    @Test
    void SQUARE_DIAGONAL_FULL_RANK_threeByThree() {
        verify(new double[][] {{1.0, 0.0, 0.0}, {0.0, -2.0, 0.0}, {0.0, 0.0, 4.0}});
    }

    @Test
    void RANK_ONE_MULTIPLE_ROWS_positiveMultiples() {
        verify(new double[][] {{1.0, 2.0, 3.0}, {2.0, 4.0, 6.0}, {-1.0, -2.0, -3.0}});
    }

    @Test
    void RANK_ONE_MULTIPLE_ROWS_wideMultiples() {
        verify(new double[][] {{2.0, -1.0}, {6.0, -3.0}, {-4.0, 2.0}, {0.0, 0.0}});
    }

    @Test
    void INTERMEDIATE_RANK_DEPENDENCY_threeByThree() {
        verify(new double[][] {{1.0, 0.0, 1.0}, {0.0, 1.0, 1.0}, {1.0, 1.0, 2.0}});
    }

    @Test
    void INTERMEDIATE_RANK_DEPENDENCY_fourByFour() {
        verify(new double[][] {{2.0, 0.0, 1.0, 0.0}, {0.0, 3.0, 0.0, 1.0}, {2.0, 3.0, 1.0, 1.0}, {4.0, -3.0, 2.0, -1.0}});
    }

    @Test
    void LEADING_ZERO_COLUMN_singleIdentity() {
        verify(new double[][] {{0.0, 1.0}, {0.0, 0.0}});
    }

    @Test
    void LEADING_ZERO_COLUMN_twoIdentity() {
        verify(new double[][] {{0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}, {0.0, 0.0, 0.0}});
    }

    @Test
    void INTERIOR_ZERO_COLUMN_twoPivots() {
        verify(new double[][] {{1.0, 0.0, 0.0}, {0.0, 0.0, 1.0}});
    }

    @Test
    void INTERIOR_ZERO_COLUMN_threePivots() {
        verify(new double[][] {{1.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 1.0, 0.0}, {0.0, 0.0, 0.0, 1.0}});
    }

    @Test
    void LATER_ROW_PIVOT_SELECTION_twoByTwo() {
        verify(new double[][] {{0.0, 1.0}, {2.0, 0.0}});
    }

    @Test
    void LATER_ROW_PIVOT_SELECTION_dependentRows() {
        verify(new double[][] {{0.0, 0.0, 1.0}, {3.0, 0.0, 0.0}, {0.0, 2.0, 0.0}});
    }

    @Test
    void MARKED_ROW_SKIPPED_ON_LATER_COLUMNS_basic() {
        verify(new double[][] {{2.0, 3.0}, {0.0, 4.0}});
    }

    @Test
    void MARKED_ROW_SKIPPED_ON_LATER_COLUMNS_negativePivot() {
        verify(new double[][] {{-5.0, 2.0, 1.0}, {0.0, 3.0, 0.0}});
    }

    @Test
    void NONUNIT_PIVOT_NORMALIZATION_twoByTwo() {
        verify(new double[][] {{2.0, 6.0}, {1.0, 4.0}});
    }

    @Test
    void NONUNIT_PIVOT_NORMALIZATION_threeByTwo() {
        verify(new double[][] {{4.0, 8.0}, {2.0, 5.0}, {0.0, 0.0}});
    }

    @Test
    void ELIMINATION_REQUIRED_FOR_DEPENDENCE_twoRows() {
        verify(new double[][] {{2.0, 5.0}, {4.0, 10.0}});
    }

    @Test
    void ELIMINATION_REQUIRED_FOR_DEPENDENCE_threeRows() {
        verify(new double[][] {{1.0, -2.0, 3.0}, {-2.0, 4.0, -6.0}, {3.0, -6.0, 9.0}});
    }

    @Test
    void SINGLE_ROW_MATRICES_allZero() {
        verify(new double[][] {{0.0, 0.0, 0.0, 0.0}});
    }

    @Test
    void SINGLE_ROW_MATRICES_nonzero() {
        verify(new double[][] {{0.0, -2.0, 0.0, 5.0}});
    }

    @Test
    void SINGLE_COLUMN_MATRICES_allZero() {
        verify(new double[][] {{0.0}, {0.0}, {0.0}});
    }

    @Test
    void SINGLE_COLUMN_MATRICES_nonzero() {
        verify(new double[][] {{0.0}, {-4.0}, {0.0}, {2.0}});
    }

    @Test
    void BELOW_EPSILON_ENTRIES_positiveAndNegative() {
        verify(new double[][] {{5.0e-11, -4.0e-11}, {2.0e-11, -1.0e-11}});
    }

    @Test
    void BELOW_EPSILON_ENTRIES_rectangular() {
        verify(new double[][] {{-9.0e-11, 7.0e-11, -3.0e-11}});
    }

    @Test
    void EXACT_EPSILON_PIVOTS_diagonal() {
        verify(new double[][] {{1.0e-10, 0.0}, {0.0, -1.0e-10}});
    }

    @Test
    void EXACT_EPSILON_PIVOTS_singleColumn() {
        verify(new double[][] {{0.0}, {1.0e-10}, {-1.0e-10}});
    }

    @Test
    void ELIMINATION_RESIDUAL_BELOW_EPSILON_power35() {
        double d = Math.scalb(1.0, -35);
        verify(new double[][] {{1.0, 1.0}, {1.0, 1.0 + d}});
    }

    @Test
    void ELIMINATION_RESIDUAL_BELOW_EPSILON_power36() {
        double d = Math.scalb(1.0, -36);
        verify(new double[][] {{1.0, 1.0}, {1.0, 1.0 + d}});
    }

    @Test
    void ELIMINATION_RESIDUAL_ABOVE_EPSILON_power33() {
        double d = Math.scalb(1.0, -33);
        verify(new double[][] {{1.0, 1.0}, {1.0, 1.0 + d}});
    }

    @Test
    void ELIMINATION_RESIDUAL_ABOVE_EPSILON_power32() {
        double d = Math.scalb(1.0, -32);
        verify(new double[][] {{1.0, 1.0}, {1.0, 1.0 + d}});
    }

    @Test
    void NEGATIVE_AND_SIGNED_ZERO_VALUES_negativeDiagonal() {
        verify(new double[][] {{-2.0, 0.0, 0.0}, {0.0, -3.0, 0.0}, {0.0, 0.0, -4.0}});
    }

    @Test
    void NEGATIVE_AND_SIGNED_ZERO_VALUES_signedZeroOnly() {
        verify(new double[][] {{-0.0, 0.0}, {0.0, -0.0}});
    }

    @Test
    void EXTREME_FINITE_MAGNITUDES_maxAndModerate() {
        verify(new double[][] {{Double.MAX_VALUE, 0.0}, {0.0, 2.0}});
    }

    @Test
    void EXTREME_FINITE_MAGNITUDES_largeNegative() {
        verify(new double[][] {{-Double.MAX_VALUE, 0.0, 0.0}, {0.0, 1.0e200, 0.0}, {0.0, 0.0, 7.0}});
    }

    @Test
    void TINY_FINITE_VALUES_minValue() {
        verify(new double[][] {{Double.MIN_VALUE, -Double.MIN_VALUE}, {Double.MIN_VALUE, Double.MIN_VALUE}});
    }

    @Test
    void TINY_FINITE_VALUES_smallNormal() {
        verify(new double[][] {{1.0e-200, -1.0e-300, 5.0e-320}});
    }

    @Test
    void NAN_ENTRIES_allNaN() {
        verify(new double[][] {{Double.NaN, Double.NaN, Double.NaN}, {Double.NaN, Double.NaN, Double.NaN}});
    }

    @Test
    void NAN_ENTRIES_mixedNaNZero() {
        verify(new double[][] {{Double.NaN, 0.0}, {0.0, 0.0}});
    }

    @Test
    void INFINITE_ENTRIES_diagonalInfinity() {
        verify(new double[][] {{Double.POSITIVE_INFINITY, 0.0}, {0.0, 1.0}});
    }

    @Test
    void INFINITE_ENTRIES_eliminationInfinity() {
        verify(new double[][] {{Double.POSITIVE_INFINITY, 1.0}, {1.0, 0.0}});
    }
}
