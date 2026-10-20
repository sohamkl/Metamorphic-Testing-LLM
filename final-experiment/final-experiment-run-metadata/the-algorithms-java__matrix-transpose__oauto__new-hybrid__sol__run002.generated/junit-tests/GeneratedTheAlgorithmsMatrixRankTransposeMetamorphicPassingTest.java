import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static final double E = 1e-10;

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
    public void SIGNED_ZERO_SCALAR_variation1_positiveZero() {
        double[][] source = {{+0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIGNED_ZERO_SCALAR_variation2_negativeZero() {
        double[][] source = {{-0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SUB_EPSILON_SCALAR_variation1_positiveHalfEpsilon() {
        double[][] source = {{E / 2.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SUB_EPSILON_SCALAR_variation2_negativeHalfEpsilon() {
        double[][] source = {{-E / 2.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_EPSILON_SCALAR_variation1_positiveBoundary() {
        double[][] source = {{E}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_EPSILON_SCALAR_variation2_negativeBoundary() {
        double[][] source = {{-E}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JUST_ABOVE_EPSILON_SCALAR_variation1_positiveNextUp() {
        double[][] source = {{Math.nextUp(E)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JUST_ABOVE_EPSILON_SCALAR_variation2_negativeNextUp() {
        double[][] source = {{-Math.nextUp(E)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_NONZERO_SCALAR_variation1_negativeFraction() {
        double[][] source = {{-3.25}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_ROW_ALL_ZERO_variation1_mixedTinyValues() {
        double[][] source = {{+0.0, -0.0, E / 4.0, -E / 3.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_ROW_EARLY_PIVOT_variation1_nonUnitFirstPivot() {
        double[][] source = {{2.0, -3.0, 5.0, 0.5}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_ROW_LATE_PIVOT_variation1_lastColumnOnly() {
        double[][] source = {{0.0, -0.0, E / 2.0, -7.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_COLUMN_ALL_ZERO_variation1_signedAndTinyZeros() {
        double[][] source = {{0.0}, {-0.0}, {E / 2.0}, {-E / 2.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_COLUMN_DELAYED_PIVOT_variation1_thirdRowPivot() {
        double[][] source = {{0.0}, {E / 2.0}, {-4.0}, {8.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGULAR_ZERO_MATRICES_variation1_tallZeroMatrix() {
        double[][] source = {
            {0.0, -0.0},
            {-0.0, 0.0},
            {0.0, 0.0},
            {-0.0, -0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGULAR_ZERO_MATRICES_variation2_wideZeroMatrix() {
        double[][] source = {
            {-0.0, 0.0, -0.0, 0.0, 0.0},
            {0.0, -0.0, 0.0, -0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IDENTITY_MATRICES_variation1_orderTwo() {
        double[][] source = {{1.0, 0.0}, {0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void IDENTITY_MATRICES_variation2_orderFive() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERMUTATION_MATRIX_variation1_cyclicPermutation() {
        double[][] source = {
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAGONAL_WITH_SUB_EPSILON_ENTRY_variation1_middleTinyEntry() {
        double[][] source = {
            {2.0, 0.0, 0.0, 0.0},
            {0.0, E / 2.0, 0.0, 0.0},
            {0.0, 0.0, -3.0, 0.0},
            {0.0, 0.0, 0.0, E}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAGONAL_AT_EPSILON_variation1_alternatingSigns() {
        double[][] source = {
            {E, 0.0, 0.0, 0.0},
            {0.0, -E, 0.0, 0.0},
            {0.0, 0.0, E, 0.0},
            {0.0, 0.0, 0.0, -E}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UPPER_TRIANGULAR_FULL_RANK_variation1_denseUpperTriangle() {
        double[][] source = {
            {2.0, -1.0, 4.0, 3.0},
            {0.0, 3.0, 5.0, -2.0},
            {0.0, 0.0, -4.0, 7.0},
            {0.0, 0.0, 0.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOWER_TRIANGULAR_FULL_RANK_variation1_denseLowerTriangle() {
        double[][] source = {
            {2.0, 0.0, 0.0, 0.0},
            {3.0, -1.0, 0.0, 0.0},
            {-4.0, 5.0, 6.0, 0.0},
            {7.0, 8.0, -2.0, 3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEGATIVE_PIVOTS_variation1_mixedSignPivots() {
        double[][] source = {
            {-2.0, 1.0, 0.0},
            {0.0, 3.0, 4.0},
            {0.0, 0.0, -5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEGATIVE_PIVOTS_variation2_allNegativeDiagonalPivots() {
        double[][] source = {
            {-1.0, 0.0, 0.0, 0.0},
            {0.0, -2.0, 0.0, 0.0},
            {0.0, 0.0, -3.0, 0.0},
            {0.0, 0.0, 0.0, -4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DUPLICATE_ROWS_variation1_repeatedNonzeroRow() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {1.0, 2.0, 3.0},
            {0.0, 1.0, -1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROPORTIONAL_ROWS_variation1_positiveNegativeAndZeroMultiples() {
        double[][] source = {
            {2.0, -4.0, 6.0},
            {1.0, -2.0, 3.0},
            {-3.0, 6.0, -9.0},
            {0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROW_LINEAR_COMBINATION_variation1_exactSum() {
        double[][] source = {
            {1.0, 0.0, 1.0},
            {0.0, 1.0, 1.0},
            {1.0, 1.0, 2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DUPLICATE_COLUMNS_variation1_firstAndThirdEqual() {
        double[][] source = {
            {1.0, 0.0, 1.0},
            {0.0, 1.0, 0.0},
            {2.0, 3.0, 2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DENSE_OUTER_PRODUCT_variation1_rectangularOuterProduct() {
        double[][] source = {
            {2.0, -4.0, 6.0, 10.0},
            {3.0, -6.0, 9.0, 15.0},
            {-5.0, 10.0, -15.0, -25.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TALL_AND_WIDE_MAXIMAL_RANK_variation1_tallFullColumnRank() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 1.0, 1.0},
            {2.0, -1.0, 3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TALL_AND_WIDE_MAXIMAL_RANK_variation2_wideFullRowRank() {
        double[][] source = {
            {1.0, 0.0, 0.0, 2.0, -1.0},
            {0.0, 1.0, 0.0, 3.0, 4.0},
            {0.0, 0.0, 1.0, -2.0, 5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERSPERSED_ZERO_ROWS_variation1_twoIndependentRows() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {1.0, 2.0, 0.0},
            {-0.0, 0.0, 0.0},
            {0.0, 1.0, 3.0},
            {0.0, -0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERSPERSED_ZERO_COLUMNS_variation1_twoIndependentColumns() {
        double[][] source = {
            {0.0, 1.0, 0.0, 0.0, 2.0},
            {0.0, 0.0, 0.0, 0.0, 1.0},
            {0.0, 3.0, 0.0, 0.0, -1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEADING_ZERO_COLUMNS_variation1_twoLateIndependentColumns() {
        double[][] source = {
            {0.0, E / 2.0, 1.0, 0.0, 4.0},
            {-0.0, -E / 2.0, 0.0, 1.0, 5.0},
            {0.0, 0.0, 1.0, 1.0, 9.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TRAILING_COLUMNS_AFTER_ALL_ROWS_MARKED_variation1_identityPrefix() {
        double[][] source = {
            {1.0, 0.0, 2.0, -1.0, 4.0},
            {0.0, 1.0, 3.0, 5.0, -2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MARKED_ROW_SKIPPED_DURING_PIVOT_SEARCH_variation1_upperTriangular() {
        double[][] source = {
            {1.0, 1.0, 0.0},
            {0.0, 1.0, 1.0},
            {0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOWER_ROW_FIRST_PIVOT_variation1_secondRowSuppliesPivot() {
        double[][] source = {
            {0.0, 1.0, 1.0},
            {2.0, 0.0, 1.0},
            {0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ELIMINATION_SKIPS_SUB_EPSILON_ENTRY_variation1_halfEpsilonEntry() {
        double[][] source = {
            {1.0, 0.0},
            {E / 2.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ELIMINATION_ACCEPTS_EXACT_EPSILON_ENTRY_variation1_boundaryEntry() {
        double[][] source = {
            {1.0, 1.0},
            {E, 1.0 + E}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_CANCELLATION_TO_ZERO_variation1_proportionalRows() {
        double[][] source = {
            {2.0, 3.0},
            {4.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CANCELLATION_TO_SUB_EPSILON_RESIDUAL_variation1_tinyResidual() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + E / 2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ELIMINATION_RESIDUAL_AT_EPSILON_variation1_exactBoundaryResidual() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, E}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRACTIONAL_NORMALIZATION_variation1_halfNormalizedValue() {
        double[][] source = {
            {2.0, 1.0},
            {1.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LAST_COLUMN_NEW_PIVOT_variation1_interveningZeroColumn() {
        double[][] source = {
            {1.0, 0.0, 1.0},
            {0.0, 0.0, E}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_FINITE_DYNAMIC_RANGE_variation1_largeAndNearThresholdPivots() {
        double[][] source = {
            {1e200, 1.0},
            {0.0, 1e-9}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FINITE_SOURCE_WITH_OVERFLOWING_INTERMEDIATE_variation1_maxValueEntries() {
        double maximum = Double.MAX_VALUE;
        double[][] source = {
            {maximum, maximum},
            {maximum, -maximum}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_SIGNED_ZEROS_variation1_oneUsablePivot() {
        double[][] source = {
            {-0.0, 1.0},
            {+0.0, -0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ALIASED_DUPLICATE_ROWS_variation1_sharedRowReference() {
        double[] sharedRow = {2.0, -3.0, 5.0};
        double[][] source = {sharedRow, sharedRow};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SPARSE_BLOCK_DIAGONAL_MIXED_RANK_variation1_threeBlocks() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 1.0, 2.0},
            {0.0, 0.0, 0.0, 0.0, 2.0, 4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
