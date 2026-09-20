import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Expected source and transposed matrices to have the same rank, but source rank was "
                            + sourceOutput + " and follow-up rank was " + followUpOutput);
        }
    }

    @Test
    void ALL_ZERO_MATRICES_signedZerosSingleRow() {
        double[][] source = {{0.0, -0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_MATRICES_signedZerosSingleColumn() {
        double[][] source = {{-0.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_MATRICES_signedZerosThreeByFour() {
        double[][] source = {
            {0.0, -0.0, 0.0, -0.0},
            {-0.0, 0.0, -0.0, 0.0},
            {0.0, 0.0, -0.0, -0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_EPSILON_BOUNDARY_positiveBelowThreshold() {
        double[][] source = {{5e-11}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_EPSILON_BOUNDARY_negativeBelowThreshold() {
        double[][] source = {{-5e-11}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_EPSILON_BOUNDARY_positiveExactThreshold() {
        double[][] source = {{1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCALAR_EPSILON_BOUNDARY_negativeExactThreshold() {
        double[][] source = {{-1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_DELAYED_PIVOT_firstColumn() {
        double[][] source = {{2.0, 0.0, 3.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_DELAYED_PIVOT_interiorColumn() {
        double[][] source = {{0.0, 5e-11, -3.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_DELAYED_PIVOT_lastColumn() {
        double[][] source = {{-0.0, 5e-11, -5e-11, 7.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_DELAYED_PIVOT_firstRow() {
        double[][] source = {{3.0}, {0.0}, {-2.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_DELAYED_PIVOT_interiorRow() {
        double[][] source = {{5e-11}, {-4.0}, {2.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_DELAYED_PIVOT_lastRow() {
        double[][] source = {{0.0}, {-0.0}, {5e-11}, {6.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FULL_RANK_SQUARE_twoByTwo() {
        double[][] source = {{2.0, 1.0}, {0.0, 3.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FULL_RANK_SQUARE_threeByThree() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {0.0, 4.0, 5.0},
            {0.0, 0.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FULL_RANK_SQUARE_fourByFour() {
        double[][] source = {
            {2.0, 1.0, 0.0, 0.0},
            {0.0, 3.0, 1.0, 0.0},
            {0.0, 0.0, 4.0, 1.0},
            {0.0, 0.0, 0.0, 5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SQUARE_SINGULAR_RANKS_rankOneTwoByTwo() {
        double[][] source = {{2.0, 4.0}, {3.0, 6.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SQUARE_SINGULAR_RANKS_rankTwoThreeByThree() {
        double[][] source = {
            {1.0, 0.0, 1.0},
            {0.0, 1.0, 1.0},
            {1.0, 1.0, 2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SQUARE_SINGULAR_RANKS_rankThreeFourByFour() {
        double[][] source = {
            {1.0, 0.0, 0.0, 1.0},
            {0.0, 1.0, 0.0, 1.0},
            {0.0, 0.0, 1.0, 1.0},
            {1.0, 1.0, 1.0, 3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSE_PERMUTATION_PIVOTS_twoByTwo() {
        double[][] source = {{0.0, 1.0}, {1.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSE_PERMUTATION_PIVOTS_threeByThree() {
        double[][] source = {
            {0.0, 0.0, 1.0},
            {0.0, 1.0, 0.0},
            {1.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_AND_COLUMNS_rankOne() {
        double[][] source = {
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 2.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_AND_COLUMNS_rankTwo() {
        double[][] source = {
            {1.0, 0.0, 2.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_threeByTwo() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_fiveByThree() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 1.0, 0.0},
            {0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_fourByTwo() {
        double[][] source = {
            {2.0, 1.0},
            {0.0, 3.0},
            {2.0, 4.0},
            {0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_twoByThree() {
        double[][] source = {
            {1.0, 0.0, 2.0},
            {0.0, 1.0, 3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_threeByFive() {
        double[][] source = {
            {1.0, 0.0, 0.0, 2.0, 3.0},
            {0.0, 1.0, 0.0, 4.0, 5.0},
            {0.0, 0.0, 1.0, 6.0, 7.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_twoBySix() {
        double[][] source = {
            {2.0, 1.0, 7.0, 8.0, 9.0, 10.0},
            {0.0, 3.0, 4.0, 5.0, 6.0, 11.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RECTANGULAR_RANK_DEFICIENT_threeByTwoRankOne() {
        double[][] source = {
            {1.0, 2.0},
            {2.0, 4.0},
            {-3.0, -6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RECTANGULAR_RANK_DEFICIENT_twoByFourRankOne() {
        double[][] source = {
            {1.0, -2.0, 3.0, 4.0},
            {3.0, -6.0, 9.0, 12.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RECTANGULAR_RANK_DEFICIENT_fourByThreeRankTwo() {
        double[][] source = {
            {1.0, 0.0, 2.0},
            {0.0, 1.0, 3.0},
            {1.0, 1.0, 5.0},
            {2.0, -1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_ELIMINATION_CANCELLATION_twoByTwo() {
        double[][] source = {{1.0, 2.0}, {2.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_ELIMINATION_CANCELLATION_threeByThree() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {0.0, 1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEAR_DEPENDENCE_AROUND_EPSILON_belowThresholdResidual() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + 5e-11}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEAR_DEPENDENCE_AROUND_EPSILON_positiveThresholdResidual() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + 1e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEAR_DEPENDENCE_AROUND_EPSILON_negativeThresholdResidual() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 - 1e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_DIAGONAL_THRESHOLD_VALUES_threeByThree() {
        double[][] source = {
            {5e-11, 0.0, 0.0},
            {0.0, 1e-10, 0.0},
            {0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_DIAGONAL_THRESHOLD_VALUES_fourByFour() {
        double[][] source = {
            {-5e-11, 0.0, 0.0, 0.0},
            {0.0, -1e-10, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXTREME_FINITE_MAGNITUDES_tinyDiagonalEntry() {
        double[][] source = {
            {Double.MAX_VALUE, 0.0},
            {0.0, Double.MIN_NORMAL}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXTREME_FINITE_MAGNITUDES_eliminationOverflow() {
        double[][] source = {
            {Double.MAX_VALUE, Double.MAX_VALUE},
            {Double.MAX_VALUE, -Double.MAX_VALUE}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIGNED_ZERO_AND_SUBNORMAL_VALUES_signedZeroRectangle() {
        double[][] source = {
            {0.0, -0.0, 0.0},
            {-0.0, 0.0, -0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIGNED_ZERO_AND_SUBNORMAL_VALUES_subnormalRectangle() {
        double[][] source = {
            {Double.MIN_VALUE, -Double.MIN_VALUE, 0.0},
            {-0.0, Double.MIN_VALUE, -Double.MIN_VALUE}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_PIVOT_AND_PROPAGATION_directNaNPivot() {
        double[][] source = {
            {Double.NaN, 0.0},
            {0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_PIVOT_AND_PROPAGATION_trailingNaN() {
        double[][] source = {
            {1.0, Double.NaN},
            {1.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INFINITY_NORMALIZATION_PATHS_finiteDividedByInfinity() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, 1.0},
            {0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INFINITY_NORMALIZATION_PATHS_infinityDividedByInfinity() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
            {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PIVOT_ONLY_IN_LAST_COLUMN_threeByFour() {
        double[][] source = {
            {0.0, 5e-11, -0.0, 0.0},
            {-5e-11, 0.0, 5e-11, 4.0},
            {0.0, -5e-11, 0.0, -8.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ROWS_MARKED_BEFORE_FINAL_COLUMNS_twoByFive() {
        double[][] source = {
            {1.0, 0.0, 7.0, 8.0, 9.0},
            {0.0, 1.0, 3.0, 4.0, 5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SOURCE_MATRIX_NOT_MUTATED_normalizationAndElimination() {
        double[][] source = {
            {0.0, 2.0, 4.0},
            {1.0, 3.0, 5.0}
        };

        long[][] rawBitsBefore = {
            {
                Double.doubleToRawLongBits(source[0][0]),
                Double.doubleToRawLongBits(source[0][1]),
                Double.doubleToRawLongBits(source[0][2])
            },
            {
                Double.doubleToRawLongBits(source[1][0]),
                Double.doubleToRawLongBits(source[1][1]),
                Double.doubleToRawLongBits(source[1][2])
            }
        };

        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);

        double rawBitsRetentionGuard =
                Double.longBitsToDouble(rawBitsBefore[0][0])
                        + Double.longBitsToDouble(rawBitsBefore[0][1])
                        + Double.longBitsToDouble(rawBitsBefore[0][2])
                        + Double.longBitsToDouble(rawBitsBefore[1][0])
                        + Double.longBitsToDouble(rawBitsBefore[1][1])
                        + Double.longBitsToDouble(rawBitsBefore[1][2]);
        if (rawBitsRetentionGuard == Double.NEGATIVE_INFINITY) {
            source[0][0] = rawBitsRetentionGuard;
        }

        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
