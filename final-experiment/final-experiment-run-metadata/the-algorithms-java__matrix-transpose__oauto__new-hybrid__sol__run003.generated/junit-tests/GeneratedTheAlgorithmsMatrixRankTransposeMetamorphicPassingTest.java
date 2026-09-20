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
    void ZERO_SCALAR_variation1() {
        double[][] source = {{0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_RECTANGULAR_variation1_2x4() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_RECTANGULAR_variation2_4x2() {
        double[][] source = {
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
    void SIGNED_ZERO_ENTRIES_variation1() {
        double[][] source = {
            {-0.0, 0.0},
            {0.0, -0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_BELOW_EPSILON_variation1_positive() {
        double[][] source = {{5e-11}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_BELOW_EPSILON_variation2_negative() {
        double[][] source = {{-5e-11}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_EXACTLY_EPSILON_variation1_positive() {
        double[][] source = {{1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_EXACTLY_EPSILON_variation2_negative() {
        double[][] source = {{-1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_JUST_ABOVE_EPSILON_variation1_positive() {
        double[][] source = {{1.0001e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_JUST_ABOVE_EPSILON_variation2_negative() {
        double[][] source = {{-1.0001e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_LATE_PIVOT_variation1_zeroPrefix() {
        double[][] source = {{0.0, 0.0, 3.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_LATE_PIVOT_variation2_belowThresholdPrefix() {
        double[][] source = {{5e-11, 0.0, -2.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_LOWER_PIVOT_variation1_zeroFirstRow() {
        double[][] source = {
            {0.0},
            {2.0},
            {0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_LOWER_PIVOT_variation2_thresholdSkippedFirstRow() {
        double[][] source = {
            {5e-11},
            {-3.0},
            {7.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IDENTITY_FULL_RANK_variation1_2x2() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IDENTITY_FULL_RANK_variation2_4x4() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIAGONAL_WITH_GAPS_variation1_3x3() {
        double[][] source = {
            {2.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, -3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIAGONAL_WITH_GAPS_variation2_4x4() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 4.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERMUTATION_PIVOTS_variation1_2x2Swap() {
        double[][] source = {
            {0.0, 1.0},
            {1.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERMUTATION_PIVOTS_variation2_3x3Cycle() {
        double[][] source = {
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DENSE_FULL_RANK_SQUARE_variation1_2x2() {
        double[][] source = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DENSE_FULL_RANK_SQUARE_variation2_3x3() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {0.0, 1.0, 4.0},
            {5.0, 6.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DUPLICATE_ROWS_variation1_pair() {
        double[][] source = {
            {1.0, 2.0},
            {1.0, 2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DUPLICATE_ROWS_variation2_withIndependentRow() {
        double[][] source = {
            {1.0, 0.0, 3.0},
            {1.0, 0.0, 3.0},
            {0.0, 2.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_ROWS_variation1_positiveMultiple() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_ROWS_variation2_mixedMultipliers() {
        double[][] source = {
            {-2.0, 4.0},
            {1.0, -2.0},
            {3.0, -6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_variation1() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {1.0, 0.0, 2.0},
            {0.0, 0.0, 0.0},
            {0.0, 3.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEADING_ZERO_COLUMNS_variation1() {
        double[][] source = {
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation1_2x3() {
        double[][] source = {
            {1.0, 0.0, 2.0},
            {0.0, 1.0, 3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation2_3x4() {
        double[][] source = {
            {1.0, 0.0, 0.0, 4.0},
            {0.0, 1.0, 0.0, 5.0},
            {0.0, 0.0, 1.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation1_3x2() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation2_4x3() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_RANK_DEFICIENT_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0, 4.0},
            {2.0, 4.0, 6.0, 8.0},
            {0.0, 1.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_RANK_DEFICIENT_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {0.0, 1.0, 1.0},
            {0.0, 2.0, 2.0},
            {1.0, 3.0, 4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NORMALIZATION_CREATES_DEPENDENCY_variation1() {
        double[][] source = {
            {2.0, 4.0},
            {1.0, 2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_CANCELLATION_AFTER_ELIMINATION_variation1() {
        double[][] source = {
            {2.0, 4.0, 6.0},
            {1.0, 2.0, 3.0},
            {-1.0, -2.0, -3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_RESIDUAL_BELOW_EPSILON_variation1_positive() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, 5e-11}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_RESIDUAL_BELOW_EPSILON_variation2_negative() {
        double[][] source = {
            {1.0, 0.0},
            {-1.0, -5e-11}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_RESIDUAL_EXACTLY_EPSILON_variation1_positive() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, 1e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_RESIDUAL_EXACTLY_EPSILON_variation2_negative() {
        double[][] source = {
            {1.0, 0.0},
            {-1.0, -1e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_RESIDUAL_ABOVE_EPSILON_variation1_positive() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, 1.0001e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_RESIDUAL_ABOVE_EPSILON_variation2_negative() {
        double[][] source = {
            {1.0, 0.0},
            {-1.0, -1.0001e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_SKIPPED_FOR_SMALL_COEFFICIENT_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {5e-11, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_EXECUTED_AT_EPSILON_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {1e-10, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LAST_COLUMN_ONLY_PIVOT_variation1() {
        double[][] source = {
            {0.0, 0.0, 2.0},
            {0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RANK_SATURATION_WITH_REMAINING_COLUMNS_variation1() {
        double[][] source = {
            {1.0, 0.0, 7.0, 8.0, 9.0},
            {0.0, 1.0, 4.0, 5.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_SIGNS_AND_FRACTIONS_variation1() {
        double[][] source = {
            {-0.5, 1.5, 2.0},
            {2.0, -1.0, 0.5},
            {1.0, 1.0, -1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_INPUT_INTERNAL_OVERFLOW_variation1() {
        double[][] source = {
            {1e-10, 1e308},
            {1e-10, -1e308}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUBNORMAL_MATRIX_variation1() {
        double[][] source = {
            {Double.MIN_VALUE, -Double.MIN_VALUE},
            {2 * Double.MIN_VALUE, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALIASED_DUPLICATE_ROWS_variation1() {
        double[] sharedRow = {1.0, 2.0};
        double[][] source = {sharedRow, sharedRow};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
