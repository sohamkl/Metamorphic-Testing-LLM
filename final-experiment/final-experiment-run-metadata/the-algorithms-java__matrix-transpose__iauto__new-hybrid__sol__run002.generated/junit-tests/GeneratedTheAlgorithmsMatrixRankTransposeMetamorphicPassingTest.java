import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Expected source and transposed matrices to have the same rank, but source rank was "
                            + sourceOutput + " and transposed rank was " + followUpOutput);
        }
    }

    @Test
    void ZERO_SCALAR_variation1_exactZero() {
        double[][] source = {{0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_EPSILON_SCALARS_variation1_minValue() {
        double[][] source = {{Double.MIN_VALUE}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_EPSILON_SCALARS_variation2_positiveNextDown() {
        double[][] source = {{Math.nextDown(1e-10)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_EPSILON_SCALARS_variation3_negativeNextDown() {
        double[][] source = {{-Math.nextDown(1e-10)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EPSILON_CUTOFF_SCALARS_variation1_exactCutoff() {
        double[][] source = {{1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EPSILON_CUTOFF_SCALARS_variation2_aboveCutoff() {
        double[][] source = {{Math.nextUp(1e-10)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_SCALARS_variation1_positiveInfinity() {
        double[][] source = {{Double.POSITIVE_INFINITY}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_SCALARS_variation2_negativeInfinity() {
        double[][] source = {{Double.NEGATIVE_INFINITY}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_SCALARS_variation3_nan() {
        double[][] source = {{Double.NaN}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_RECTANGLES_variation1_wideTwoByFive() {
        double[][] source = new double[2][5];
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_RECTANGLES_variation2_tallFiveByTwo() {
        double[][] source = new double[5][2];
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_RECTANGLES_variation3_squareFourByFour() {
        double[][] source = new double[4][4];
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_PIVOT_POSITIONS_variation1_firstColumn() {
        double[][] source = {{2.0, 0.0, 0.0, 0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_PIVOT_POSITIONS_variation2_middleColumn() {
        double[][] source = {{0.0, 0.0, -3.0, 0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_PIVOT_POSITIONS_variation3_lastColumn() {
        double[][] source = {{0.0, 0.0, 0.0, 0.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_LATE_PIVOT_variation1_afterExactZeros() {
        double[][] source = {{0.0}, {0.0}, {7.0}, {0.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_LATE_PIVOT_variation2_afterSubEpsilon() {
        double[][] source = {
            {Math.nextDown(1e-10)}, {0.0}, {-2.0}, {3.0}, {0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IDENTITY_MATRICES_variation1_twoByTwo() {
        double[][] source = {{1.0, 0.0}, {0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IDENTITY_MATRICES_variation2_threeByThree() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IDENTITY_MATRICES_variation3_sixBySix() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERMUTATION_MATRICES_variation1_cyclicThreeByThree() {
        double[][] source = {
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERMUTATION_MATRICES_variation2_reverseIdentity() {
        double[][] source = {
            {0.0, 0.0, 0.0, 1.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {1.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARTIAL_DIAGONAL_RANKS_variation1_oneNonzeroEntry() {
        double[][] source = {
            {2.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARTIAL_DIAGONAL_RANKS_variation2_twoNonzeroEntries() {
        double[][] source = {
            {2.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, -3.0, 0.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARTIAL_DIAGONAL_RANKS_variation3_threeNonzeroEntries() {
        double[][] source = {
            {2.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, -3.0, 0.0},
            {0.0, 0.0, 0.0, 4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation1_twoByFive() {
        double[][] source = {
            {1.0, 0.0, 2.0, 0.0, 3.0},
            {0.0, 1.0, 4.0, 5.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation1_fiveByTwo() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0},
            {2.0, -1.0},
            {0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DUPLICATE_ROWS_variation1_threeIdenticalRows() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {1.0, 2.0, 3.0},
            {1.0, 2.0, 3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_SIGNED_ROWS_variation1_positiveAndNegativeMultiples() {
        double[][] source = {
            {1.0, -2.0, 3.0},
            {2.0, -4.0, 6.0},
            {-3.0, 6.0, -9.0},
            {0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DUPLICATE_COLUMNS_variation1_twoEqualColumns() {
        double[][] source = {
            {1.0, 1.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONSTANT_NONZERO_MATRIX_variation1_fourByThree() {
        double[][] source = {
            {5.0, 5.0, 5.0},
            {5.0, 5.0, 5.0},
            {5.0, 5.0, 5.0},
            {5.0, 5.0, 5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_variation1_independentRowsSeparated() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {1.0, 0.0, 2.0},
            {0.0, 0.0, 0.0},
            {0.0, 1.0, 3.0},
            {0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_COLUMNS_WITH_GAPS_variation1_leadingAndInternalGaps() {
        double[][] source = {
            {0.0, 1.0, 0.0, 2.0},
            {0.0, 0.0, 0.0, 1.0},
            {0.0, 1.0, 0.0, 3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONMONOTONIC_PIVOT_ROW_ORDER_variation1_rowOrderTwoZeroOne() {
        double[][] source = {
            {0.0, 1.0, 1.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_ELIMINATION_CANCELLATION_variation1_signedDependentRows() {
        double[][] source = {
            {2.0, 4.0, 6.0},
            {1.0, 2.0, 3.0},
            {-1.0, -2.0, -3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ELIMINATION_REVEALS_FULL_RANK_variation1_denseThreeByThree() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 5.0, 8.0},
            {0.0, 1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_DEPENDENT_AND_INDEPENDENT_ROWS_variation1_scaledAndIndependent() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {1.0, 1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIRECT_RESIDUAL_AROUND_EPSILON_variation1_belowCutoff() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, Math.nextDown(1e-10)}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIRECT_RESIDUAL_AROUND_EPSILON_variation2_exactCutoff() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, 1e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CANCELLATION_RESIDUAL_SIDES_variation1_smallDelta() {
        double delta = 1e-12;
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + delta}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CANCELLATION_RESIDUAL_SIDES_variation2_largeDelta() {
        double delta = 1e-9;
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + delta}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXTREME_FINITE_SCALING_variation1_subEpsilonSecondDiagonal() {
        double[][] source = {
            {1e300, 0.0},
            {0.0, 1e-300}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXTREME_FINITE_SCALING_variation2_ordinarySecondDiagonal() {
        double[][] source = {
            {1e300, 0.0},
            {0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_PIVOTS_variation1_upperTriangular() {
        double[][] source = {
            {-2.0, 1.0, 0.0},
            {0.0, -3.0, 1.0},
            {0.0, 0.0, -4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DENSE_NAN_MATRIX_variation1_twoByThree() {
        double[][] source = {
            {Double.NaN, Double.NaN, Double.NaN},
            {Double.NaN, Double.NaN, Double.NaN}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INFINITE_DIAGONAL_variation1_signedInfinities() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, 0.0},
            {0.0, Double.NEGATIVE_INFINITY}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void GENERATED_NAN_DURING_NORMALIZATION_variation1_infinitePivotRow() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
            {1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAXIMUM_BOUNDED_SPARSE_SHAPE_variation1_eightByEight() {
        double[][] source = new double[8][8];
        source[0][7] = 2.0;
        source[3][3] = -1.0;
        source[7][0] = 4.0;
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
