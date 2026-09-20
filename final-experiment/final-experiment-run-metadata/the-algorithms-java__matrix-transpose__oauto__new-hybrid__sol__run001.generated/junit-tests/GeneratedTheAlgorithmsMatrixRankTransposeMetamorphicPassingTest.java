import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static double[][] generateFollowUp(double[][] source) {
        int rows = source.length;
        int columns = source[0].length;
        double[][] transposed = new double[columns][rows];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                transposed[column][row] = source[row][column];
            }
        }
        return transposed;
    }

    @Test
    void ZERO_SCALAR_positiveZero() {
        double[][] source = {{0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_EPSILON_SCALAR_positiveValue() {
        double[][] source = {{5e-11}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_EPSILON_SCALAR_negativeValue() {
        double[][] source = {{-5e-11}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_EPSILON_SCALAR_positiveBoundary() {
        double[][] source = {{1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_EPSILON_SCALAR_negativeBoundary() {
        double[][] source = {{-1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_ZERO_SCALAR_rawNegativeZero() {
        double[][] source = {{-0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_WIDE_MATRIX_oneByFour() {
        double[][] source = {{0.0, 0.0, 0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_WIDE_MATRIX_twoByFive() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_TALL_MATRIX_fourByOne() {
        double[][] source = {{0.0}, {0.0}, {0.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_TALL_MATRIX_fiveByTwo() {
        double[][] source = {
            {0.0, 0.0},
            {0.0, 0.0},
            {0.0, 0.0},
            {0.0, 0.0},
            {0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_SINGLE_ROW_DELAYED_PIVOT_thirdColumn() {
        double[][] source = {{0.0, 0.0, 3.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_SINGLE_ROW_DELAYED_PIVOT_fourthColumnNegative() {
        double[][] source = {{0.0, 0.0, 0.0, -2.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_SINGLE_COLUMN_DELAYED_PIVOT_thirdRow() {
        double[][] source = {{0.0}, {0.0}, {7.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_SINGLE_COLUMN_DELAYED_PIVOT_secondRowNegative() {
        double[][] source = {{0.0}, {-3.0}, {5.0}, {0.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IDENTITY_FULL_RANK_twoByTwo() {
        double[][] source = {{1.0, 0.0}, {0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IDENTITY_FULL_RANK_threeByThree() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_twoByThree() {
        double[][] source = {{1.0, 0.0, 2.0}, {0.0, 1.0, 3.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_threeByFive() {
        double[][] source = {
            {1.0, 0.0, 0.0, 2.0, 1.0},
            {0.0, 1.0, 0.0, 3.0, 4.0},
            {0.0, 0.0, 1.0, 5.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_threeByTwo() {
        double[][] source = {{1.0, 0.0}, {0.0, 1.0}, {2.0, 3.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_fiveByThree() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {2.0, 3.0, 4.0},
            {-1.0, 2.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_ROWS_RANK_ONE_denseMultiples() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {-1.0, -2.0, -3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_ROWS_RANK_ONE_leadingZeroColumn() {
        double[][] source = {{0.0, 2.0}, {0.0, 4.0}, {0.0, -2.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DUPLICATE_ROWS_WITH_INDEPENDENT_ROW_threeByThree() {
        double[][] source = {
            {1.0, 0.0, 1.0},
            {1.0, 0.0, 1.0},
            {0.0, 1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DUPLICATE_ROWS_WITH_INDEPENDENT_ROW_withZeroRow() {
        double[][] source = {
            {1.0, 2.0, 0.0},
            {1.0, 2.0, 0.0},
            {0.0, 0.0, 1.0},
            {0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_AND_COLUMNS_singleCenterPivot() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_AND_COLUMNS_twoSeparatedPivots() {
        double[][] source = {
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 2.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOWER_ROW_PIVOT_PERMUTATION_twoByTwoReverse() {
        double[][] source = {{0.0, 1.0}, {1.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOWER_ROW_PIVOT_PERMUTATION_threeByThreeReverse() {
        double[][] source = {
            {0.0, 0.0, 1.0},
            {0.0, 1.0, 0.0},
            {1.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEADING_COLUMNS_WITHOUT_PIVOTS_oneLeadingColumn() {
        double[][] source = {{0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEADING_COLUMNS_WITHOUT_PIVOTS_twoLeadingColumns() {
        double[][] source = {
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0},
            {0.0, 0.0, 1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_REVEALS_LATER_PIVOTS_twoByTwo() {
        double[][] source = {{1.0, 1.0}, {1.0, 2.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_REVEALS_LATER_PIVOTS_threeByThree() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 5.0, 8.0},
            {0.0, 1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_CANCELLATION_DEPENDENCY_doubleRow() {
        double[][] source = {{1.0, 2.0}, {2.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_EPSILON_ELIMINATION_RESIDUAL_positiveResidual() {
        double[][] source = {{1.0, 1.0}, {1.0, 1.00000000005}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_EPSILON_ELIMINATION_RESIDUAL_negativeResidual() {
        double[][] source = {{1.0, 1.0}, {1.0, 0.99999999995}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_EPSILON_LATER_PIVOT_positiveResidual() {
        double[][] source = {{1.0, 0.0}, {1.0, 1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_EPSILON_LATER_PIVOT_negativeResidual() {
        double[][] source = {{1.0, 0.0}, {1.0, -1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ENTRIES_STRICTLY_BELOW_EPSILON_mixedSigns() {
        double[][] source = {
            {5e-11, -4e-11, 0.0},
            {Double.MIN_VALUE, -9e-11, 2e-11}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_SIDES_OF_EPSILON_lowerBoundaryPivot() {
        double[][] source = {{Math.nextDown(1e-10), 1.0}, {1e-10, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_PIVOT_NORMALIZATION_twoByTwo() {
        double[][] source = {{-2.0, 4.0}, {1.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SOLE_PIVOT_IN_LAST_COLUMN_twoByThree() {
        double[][] source = {{0.0, 0.0, 2.0}, {0.0, 0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DENSE_FULL_RANK_MATRIX_threeByThree() {
        double[][] source = {
            {2.0, 1.0, 3.0},
            {1.0, 0.0, 4.0},
            {3.0, 2.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAX_FINITE_DIAGONAL_twoByTwo() {
        double[][] source = {
            {Double.MAX_VALUE, 0.0},
            {0.0, Double.MAX_VALUE}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_INPUT_NORMALIZATION_OVERFLOW_largeTrailingValue() {
        double[][] source = {{1e-10, Double.MAX_VALUE}, {0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUBNORMAL_ENTRY_IGNORED_minimumPositiveValue() {
        double[][] source = {{Double.MIN_VALUE, 0.0}, {0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_AS_PIVOT_scalarNaN() {
        double[][] source = {{Double.NaN}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_AS_PIVOT_delayedNaN() {
        double[][] source = {{0.0, Double.NaN}, {0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INFINITE_PIVOTS_positiveInfinityScalar() {
        double[][] source = {{Double.POSITIVE_INFINITY}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INFINITE_PIVOTS_negativeInfinityDiagonal() {
        double[][] source = {
            {Double.NEGATIVE_INFINITY, 0.0},
            {0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_CREATED_BY_INFINITY_ELIMINATION_duplicateInfiniteTrailingValues() {
        double[][] source = {
            {1.0, Double.POSITIVE_INFINITY},
            {1.0, Double.POSITIVE_INFINITY}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
