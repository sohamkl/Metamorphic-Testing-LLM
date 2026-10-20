import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private void verifyTransposeRank(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ZERO_variation1() {
        verifyTransposeRank(new double[][] {{0.0}});
    }

    @Test
    void SINGLE_NONZERO_variation1() {
        verifyTransposeRank(new double[][] {{7.0}});
    }

    @Test
    void SINGLE_NEGATIVE_PIVOT_variation1() {
        verifyTransposeRank(new double[][] {{-3.5}});
    }

    @Test
    void SINGLE_POSITIVE_ZERO_variation1() {
        double positiveZero = 0.0;
        verifyTransposeRank(new double[][] {{positiveZero}});
    }

    @Test
    void SINGLE_NEGATIVE_ZERO_variation1() {
        verifyTransposeRank(new double[][] {{-0.0}});
    }

    @Test
    void SINGLE_BELOW_EPSILON_variation1() {
        verifyTransposeRank(new double[][] {{5.0e-11}});
    }

    @Test
    void SINGLE_EXACT_EPSILON_variation1() {
        verifyTransposeRank(new double[][] {{1.0e-10}});
    }

    @Test
    void SINGLE_NEGATIVE_EPSILON_variation1() {
        verifyTransposeRank(new double[][] {{-1.0e-10}});
    }

    @Test
    void SUBNORMAL_VALUE_variation1() {
        verifyTransposeRank(new double[][] {{Double.MIN_VALUE}});
    }

    @Test
    void ONE_ROW_LEADING_ZEROES_variation1() {
        verifyTransposeRank(new double[][] {{0.0, 0.0, 5.0}});
    }

    @Test
    void ONE_COLUMN_LEADING_ZERO_ROWS_variation1() {
        verifyTransposeRank(new double[][] {{0.0}, {0.0}, {6.0}});
    }

    @Test
    void NONTRIVIAL_ALL_ZERO_RECTANGLE_variation1() {
        verifyTransposeRank(new double[][] {{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}});
    }

    @Test
    void LEADING_ZERO_COLUMN_variation1() {
        verifyTransposeRank(new double[][] {{0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}});
    }

    @Test
    void INTERSPERSED_ZERO_COLUMN_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 0.0, 2.0}, {0.0, 0.0, 3.0}});
    }

    @Test
    void FINAL_COLUMN_ONLY_PIVOT_variation1() {
        verifyTransposeRank(new double[][] {{0.0, 0.0, 4.0}, {0.0, 0.0, 0.0}});
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 0.0, 2.0, 3.0}, {0.0, 1.0, 4.0, 5.0}});
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 0.0}, {0.0, 1.0}, {1.0, 1.0}, {2.0, 3.0}});
    }

    @Test
    void IDENTITY_WITH_EXTRA_COLUMNS_variation1() {
        verifyTransposeRank(new double[][] {
            {1.0, 0.0, 0.0, 8.0, 9.0},
            {0.0, 1.0, 0.0, 7.0, 6.0},
            {0.0, 0.0, 1.0, 5.0, 4.0}
        });
    }

    @Test
    void PROPORTIONAL_ROWS_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 2.0, 3.0}, {2.0, 4.0, 6.0}, {3.0, 6.0, 9.0}});
    }

    @Test
    void DEPENDENT_ROW_CANCELLATION_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 2.0}, {2.0, 4.0}});
    }

    @Test
    void INDEPENDENT_AFTER_LOWER_PIVOT_variation1() {
        verifyTransposeRank(new double[][] {{0.0, 1.0}, {1.0, 0.0}});
    }

    @Test
    void LOWER_PIVOT_WITH_SUFFIX_NORMALIZATION_variation1() {
        verifyTransposeRank(new double[][] {{0.0, 1.0, 1.0}, {2.0, 4.0, 6.0}});
    }

    @Test
    void MARKED_ROW_SKIPPED_FOR_NEXT_PIVOT_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 1.0}, {0.0, 1.0}});
    }

    @Test
    void MARKED_ROW_CAUSES_NO_PIVOT_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 1.0}, {0.0, 0.0}});
    }

    @Test
    void ELIMINATE_PREVIOUSLY_MARKED_ROW_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 5.0}, {0.0, 1.0}});
    }

    @Test
    void THREE_BY_THREE_GENERAL_ELIMINATION_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 2.0, 3.0}, {3.0, 5.0, 7.0}, {2.0, 4.0, 8.0}});
    }

    @Test
    void PERMUTATION_MATRIX_variation1() {
        verifyTransposeRank(new double[][] {{0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}, {1.0, 0.0, 0.0}});
    }

    @Test
    void UPPER_TRIANGULAR_NONUNIT_DIAGONAL_variation1() {
        verifyTransposeRank(new double[][] {{2.0, 3.0, 4.0}, {0.0, -5.0, 6.0}, {0.0, 0.0, 7.0}});
    }

    @Test
    void LOWER_TRIANGULAR_NONUNIT_DIAGONAL_variation1() {
        verifyTransposeRank(new double[][] {{2.0, 0.0, 0.0}, {3.0, -5.0, 0.0}, {4.0, 6.0, 7.0}});
    }

    @Test
    void MIXED_EPSILON_COLUMN_variation1() {
        verifyTransposeRank(new double[][] {{5.0e-11}, {1.0e-10}});
    }

    @Test
    void CANCELLATION_RESIDUAL_BELOW_EPSILON_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 1.0}, {1.0, 1.00000000005}});
    }

    @Test
    void CANCELLATION_RESIDUAL_ABOVE_EPSILON_variation1() {
        verifyTransposeRank(new double[][] {{1.0, 1.0}, {1.0, 1.0000000002}});
    }

    @Test
    void MAX_FINITE_DIAGONAL_variation1() {
        verifyTransposeRank(new double[][] {{Double.MAX_VALUE, 0.0}, {0.0, Double.MAX_VALUE}});
    }

    @Test
    void LARGE_FINITE_OVERFLOW_ELIMINATION_variation1() {
        verifyTransposeRank(new double[][] {
            {1.0, Double.MAX_VALUE},
            {Double.MAX_VALUE, Double.MAX_VALUE}
        });
    }

    @Test
    void POSITIVE_INFINITY_SINGLETON_variation1() {
        verifyTransposeRank(new double[][] {{Double.POSITIVE_INFINITY}});
    }

    @Test
    void NEGATIVE_INFINITY_SINGLETON_variation1() {
        verifyTransposeRank(new double[][] {{Double.NEGATIVE_INFINITY}});
    }

    @Test
    void NAN_SINGLETON_variation1() {
        verifyTransposeRank(new double[][] {{Double.NaN}});
    }

    @Test
    void NAN_WITH_NORMALIZATION_SUFFIX_variation1() {
        verifyTransposeRank(new double[][] {{Double.NaN, 0.0}});
    }

    @Test
    void INFINITY_WITH_NORMALIZATION_SUFFIX_variation1() {
        verifyTransposeRank(new double[][] {{Double.POSITIVE_INFINITY, 1.0}});
    }

    @Test
    void INFINITY_BY_INFINITY_NORMALIZATION_variation1() {
        verifyTransposeRank(new double[][] {{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}});
    }

    @Test
    void NAN_ELIMINATION_variation1() {
        verifyTransposeRank(new double[][] {{Double.NaN, 1.0}, {1.0, 1.0}});
    }

    @Test
    void INFINITY_ELIMINATION_variation1() {
        verifyTransposeRank(new double[][] {{Double.POSITIVE_INFINITY, 1.0}, {1.0, 1.0}});
    }

    @Test
    void MIXED_SIGNED_ZERO_RECTANGLE_variation1() {
        verifyTransposeRank(new double[][] {{0.0, -0.0}, {-0.0, 0.0}});
    }
}
