import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        org.junit.jupiter.api.Assertions.assertEquals(
                sourceOutput,
                followUpOutput,
                "The rank must remain unchanged after transposition");
    }

    @Test
    void ZERO_SCALAR_VARIANTS_positiveZero() {
        double[][] source = {{0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_SCALAR_VARIANTS_negativeZero() {
        double[][] source = {{-0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_SCALAR_VARIANTS_immediatelyBelowEpsilon() {
        double[][] source = {{Math.nextDown(1e-10)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EPSILON_SCALAR_BOUNDARY_positiveEpsilon() {
        double[][] source = {{1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EPSILON_SCALAR_BOUNDARY_negativeEpsilon() {
        double[][] source = {{-1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EPSILON_SCALAR_BOUNDARY_immediatelyAboveEpsilon() {
        double[][] source = {{Math.nextUp(1e-10)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_DELAYED_PIVOT_twoColumnsSubnormalLead() {
        double[][] source = {{Double.MIN_VALUE, 2.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_DELAYED_PIVOT_fourColumnsSignedZeroLead() {
        double[][] source = {{-0.0, Math.nextDown(1e-10), -3.0, 7.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_DELAYED_PIVOT_eightColumnsLastPivot() {
        double[][] source = {{0.0, -0.0, Double.MIN_VALUE, -Double.MIN_VALUE, 1e-12, -1e-12, 0.0, 5.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_DELAYED_PIVOT_firstRow() {
        double[][] source = {{-2.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_DELAYED_PIVOT_interiorRow() {
        double[][] source = {{0.0}, {Double.MIN_VALUE}, {-4.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_DELAYED_PIVOT_lastRow() {
        double[][] source = {
            {0.0}, {-0.0}, {Double.MIN_VALUE}, {-Double.MIN_VALUE},
            {1e-12}, {-1e-12}, {Math.nextDown(1e-10)}, {9.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_RECTANGULAR_wideTwoByFive() {
        double[][] source = {
            {0.0, -0.0, 0.0, -0.0, 0.0},
            {-0.0, 0.0, -0.0, 0.0, -0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_RECTANGULAR_tallFiveByTwo() {
        double[][] source = {
            {0.0, -0.0}, {-0.0, 0.0}, {0.0, 0.0}, {-0.0, -0.0}, {0.0, -0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FULL_RANK_DIAGONAL_SQUARE_twoByTwo() {
        double[][] source = {{2.0, 0.0}, {0.0, -3.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FULL_RANK_DIAGONAL_SQUARE_threeByThree() {
        double[][] source = {
            {-1.0, 0.0, 0.0},
            {0.0, 4.0, 0.0},
            {0.0, 0.0, 0.5}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FULL_RANK_DIAGONAL_SQUARE_eightByEight() {
        double[][] source = {
            {2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, -3.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, -5.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0, -7.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 8.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -9.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_twoByFourIdentityBlock() {
        double[][] source = {{1.0, 0.0, 2.0, -1.0}, {0.0, 1.0, 3.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_threeBySixLeadingZeroColumn() {
        double[][] source = {
            {0.0, 1.0, 0.0, 0.0, 2.0, 3.0},
            {0.0, 0.0, 1.0, 0.0, -1.0, 4.0},
            {0.0, 0.0, 0.0, 1.0, 5.0, -2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_fourByEightTriangularBlock() {
        double[][] source = {
            {2.0, 1.0, 0.0, 0.0, 3.0, 0.0, -1.0, 2.0},
            {0.0, -3.0, 1.0, 0.0, 1.0, 4.0, 0.0, -2.0},
            {0.0, 0.0, 4.0, 1.0, 0.0, 2.0, 5.0, 1.0},
            {0.0, 0.0, 0.0, 5.0, -3.0, 1.0, 2.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_fourByTwo() {
        double[][] source = {{1.0, 0.0}, {0.0, 1.0}, {2.0, 3.0}, {0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_sixByThree() {
        double[][] source = {
            {2.0, 1.0, 0.0},
            {0.0, -3.0, 1.0},
            {0.0, 0.0, 4.0},
            {2.0, -2.0, 1.0},
            {0.0, 0.0, 0.0},
            {4.0, 2.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_eightByFour() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0},
            {1.0, 2.0, 3.0, 4.0},
            {-1.0, 1.0, 0.0, 2.0},
            {0.0, 0.0, 0.0, 0.0},
            {2.0, 0.0, -2.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RANK_ONE_DEPENDENT_FAMILIES_squareMultiples() {
        double[][] source = {
            {1.0, -2.0, 3.0},
            {2.0, -4.0, 6.0},
            {-3.0, 6.0, -9.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RANK_ONE_DEPENDENT_FAMILIES_wideWithZeroRow() {
        double[][] source = {
            {2.0, 0.0, -1.0, 4.0, 3.0},
            {0.0, 0.0, 0.0, 0.0, 0.0},
            {-4.0, 0.0, 2.0, -8.0, -6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RANK_ONE_DEPENDENT_FAMILIES_tallSignedMultiples() {
        double[][] source = {
            {1.0, 3.0},
            {-1.0, -3.0},
            {2.0, 6.0},
            {0.0, 0.0},
            {-4.0, -12.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERMEDIATE_RANK_WITH_ZERO_ROWS_threeByThreeRankTwo() {
        double[][] source = {
            {1.0, 0.0, 2.0},
            {0.0, 1.0, 3.0},
            {1.0, 1.0, 5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERMEDIATE_RANK_WITH_ZERO_ROWS_fourByFiveRankTwo() {
        double[][] source = {
            {1.0, 0.0, 2.0, -1.0, 3.0},
            {0.0, 1.0, -1.0, 2.0, 4.0},
            {1.0, 1.0, 1.0, 1.0, 7.0},
            {0.0, 0.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERMEDIATE_RANK_WITH_ZERO_ROWS_fiveByFourRankThree() {
        double[][] source = {
            {1.0, 0.0, 0.0, 2.0},
            {0.0, 1.0, 0.0, -1.0},
            {0.0, 0.0, 1.0, 3.0},
            {1.0, 1.0, 1.0, 4.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DELAYED_ROW_PIVOT_SELECTION_lowerRowInFirstColumn() {
        double[][] source = {
            {0.0, 1.0, 0.0},
            {Double.MIN_VALUE, 0.0, 1.0},
            {2.0, 3.0, 4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DELAYED_ROW_PIVOT_SELECTION_lowerRowInSecondColumn() {
        double[][] source = {
            {1.0, 0.0, 2.0, 0.0},
            {2.0, 0.0, 5.0, 1.0},
            {0.0, -3.0, 1.0, 0.0},
            {0.0, Double.MIN_VALUE, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEADING_AND_INTERIOR_ZERO_COLUMNS_leadingZeroColumn() {
        double[][] source = {
            {0.0, 1.0, 0.0, 2.0},
            {0.0, 0.0, 1.0, 3.0},
            {0.0, 1.0, 1.0, 5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEADING_AND_INTERIOR_ZERO_COLUMNS_interiorZeroColumn() {
        double[][] source = {
            {1.0, 0.0, 0.0, 2.0},
            {0.0, 0.0, 1.0, 3.0},
            {1.0, 0.0, 1.0, 5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CANCELLATION_AT_EPSILON_residualImmediatelyBelow() {
        double secondValue = Math.nextDown(1.0 + 1e-10);
        double[][] source = {{1.0, 1.0}, {1.0, secondValue}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CANCELLATION_AT_EPSILON_nominalBoundaryResidual() {
        double secondValue = 1.0 + 1e-10;
        double[][] source = {{1.0, 1.0}, {1.0, secondValue}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CANCELLATION_AT_EPSILON_residualImmediatelyAbove() {
        double secondValue = Math.nextUp(1.0 + 1e-10);
        double[][] source = {{1.0, 1.0}, {1.0, secondValue}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONUNIT_PIVOT_NORMALIZATION_twoByTwo() {
        double[][] source = {{2.0, 1.0}, {4.0, 3.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONUNIT_PIVOT_NORMALIZATION_threeByThreePowerOfTwo() {
        double[][] source = {
            {4.0, 2.0, 1.0},
            {8.0, 8.0, 2.0},
            {0.0, 4.0, 8.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_SIGN_ELIMINATION_fullRankNegativeMultiplier() {
        double[][] source = {{-2.0, 1.0}, {3.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_SIGN_ELIMINATION_signedDependency() {
        double[][] source = {
            {-1.0, 2.0, -3.0},
            {2.0, -4.0, 6.0},
            {3.0, -6.0, 9.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUBNORMAL_AND_TINY_VALUES_allTiny() {
        double[][] source = {
            {Double.MIN_VALUE, -Double.MIN_VALUE, 1e-200},
            {-1e-300, 1e-150, -1e-12}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUBNORMAL_AND_TINY_VALUES_tinyWithOrdinaryPivot() {
        double[][] source = {
            {Double.MIN_VALUE, -Double.MIN_VALUE},
            {1e-200, 0.0},
            {0.0, -5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_ARITHMETIC_duplicateMaximumRows() {
        double[][] source = {
            {Double.MAX_VALUE, Double.MAX_VALUE},
            {Double.MAX_VALUE, Double.MAX_VALUE}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_ARITHMETIC_eliminationOverflow() {
        double[][] source = {{1e308, 1e308}, {-1e308, 1e308}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_ENTRY_PATHS_scalarNaN() {
        double[][] source = {{Double.NaN}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_ENTRY_PATHS_nanWithZeroRow() {
        double[][] source = {{Double.NaN, 0.0}, {0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INFINITY_AND_PROPAGATED_NAN_scalarPositiveInfinity() {
        double[][] source = {{Double.POSITIVE_INFINITY}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INFINITY_AND_PROPAGATED_NAN_duplicateInfinitePivots() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, 1.0},
            {Double.POSITIVE_INFINITY, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROW_ORDER_PIVOT_VARIANTS_firstRowInitialPivot() {
        double[][] source = {
            {1.0, 2.0, 0.0},
            {0.0, 1.0, 3.0},
            {2.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROW_ORDER_PIVOT_VARIANTS_skippedRowInitialPivot() {
        double[][] source = {
            {0.0, 1.0, 3.0},
            {2.0, 0.0, 1.0},
            {1.0, 2.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
