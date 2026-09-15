import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    @Test
    public void testZERO_SCALAR_variation1() {
        double[][] source = {{0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_SINGLE_ROW_variation1() {
        double[][] source = {{0.0, 0.0, 0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_SINGLE_COLUMN_variation1() {
        double[][] source = {{0.0}, {0.0}, {0.0}, {0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_RECTANGULAR_variation1_tall() {
        double[][] source = {
            {0.0, 0.0},
            {0.0, 0.0},
            {0.0, 0.0},
            {0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_RECTANGULAR_variation2_wide() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSIGNED_ZERO_MATRIX_variation1() {
        double[][] source = {
            {0.0, -0.0},
            {-0.0, 0.0},
            {0.0, -0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSCALAR_STRICTLY_BELOW_EPSILON_variation1_positive() {
        double[][] source = {{Math.nextDown(1e-10)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSCALAR_STRICTLY_BELOW_EPSILON_variation2_negative() {
        double[][] source = {{-Math.nextDown(1e-10)}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSCALAR_EXACT_EPSILON_variation1_positive() {
        double[][] source = {{1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSCALAR_EXACT_EPSILON_variation2_negative() {
        double[][] source = {{-1e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSCALAR_ORDINARY_FINITE_variation1() {
        double[][] source = {{-7.5}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSINGLE_ROW_ALL_SUB_EPSILON_variation1() {
        double[][] source = {{0.0, -0.0, 5e-11, -9e-11}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSINGLE_ROW_DELAYED_PIVOT_variation1() {
        double[][] source = {{0.0, 5e-11, -3.0, 8.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSINGLE_ROW_MULTIPLE_NONZERO_ENTRIES_variation1() {
        double[][] source = {{2.0, -3.0, 4.5, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSINGLE_COLUMN_DELAYED_PIVOT_variation1() {
        double[][] source = {{0.0}, {-0.0}, {5.0}, {-2.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSQUARE_IDENTITY_variation1_size2() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSQUARE_IDENTITY_variation2_size4() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDIAGONAL_WITH_ZERO_PIVOTS_variation1_internalZero() {
        double[][] source = {
            {2.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, -4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDIAGONAL_WITH_ZERO_PIVOTS_variation2_multipleZeros() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 3.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, -5.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testANTI_DIAGONAL_FULL_RANK_variation1_size2() {
        double[][] source = {
            {0.0, 1.0},
            {1.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testANTI_DIAGONAL_FULL_RANK_variation2_size4() {
        double[][] source = {
            {0.0, 0.0, 0.0, 1.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {1.0, 0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testWIDE_IDENTITY_BLOCK_variation1() {
        double[][] source = {
            {1.0, 0.0, 2.0, -3.0, 1.0},
            {0.0, 1.0, -1.0, 3.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testTALL_IDENTITY_BLOCK_variation1() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {2.0, -1.0, 3.0},
            {-3.0, 0.0, 2.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDUPLICATE_ROWS_variation1() {
        double[][] source = {
            {2.0, -1.0, 4.0},
            {2.0, -1.0, 4.0},
            {2.0, -1.0, 4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testEXACT_PROPORTIONAL_ROWS_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {4.0, 8.0, 12.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDUPLICATE_NONZERO_COLUMNS_variation1() {
        double[][] source = {
            {1.0, 1.0, 0.0, 1.0},
            {-2.0, -2.0, 0.0, -2.0},
            {3.0, 3.0, 0.0, 3.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testINTERSPERSED_ZERO_ROWS_variation1() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {0.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLEADING_ZERO_COLUMNS_variation1() {
        double[][] source = {
            {0.0, 0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testTRAILING_ZERO_COLUMNS_variation1() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testCONSTANT_NONZERO_MATRIX_variation1() {
        double[][] source = {
            {-2.5, -2.5, -2.5},
            {-2.5, -2.5, -2.5},
            {-2.5, -2.5, -2.5},
            {-2.5, -2.5, -2.5}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testEXACT_ELIMINATION_CANCELLATION_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testELIMINATION_RESIDUAL_BELOW_EPSILON_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, Math.nextUp(1.0)}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testELIMINATION_RESIDUAL_EXACT_EPSILON_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + 1e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testELIMINATION_RESIDUAL_ABOVE_EPSILON_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + 1.5e-10}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPIVOT_IN_LATER_ROW_variation1() {
        double[][] source = {
            {0.0, 1.0},
            {2.0, 0.0},
            {0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testMARKED_ROW_SKIPPED_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSTAGGERED_PIVOT_ROWS_variation1() {
        double[][] source = {
            {0.0, 1.0, 0.0},
            {1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_FRACTIONAL_FULL_RANK_variation1() {
        double[][] source = {
            {-2.0, 0.5, 1.0},
            {0.0, -0.25, 2.0},
            {0.0, 0.0, -4.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testFINITE_SCALE_DISPARITY_variation1() {
        double[][] source = {
            {1e100, 0.0},
            {0.0, 1e-9}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSUBNORMAL_SCALAR_variation1() {
        double[][] source = {{Double.MIN_VALUE}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testMAX_FINITE_SCALAR_variation1() {
        double[][] source = {{Double.MAX_VALUE}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNORMALIZATION_OVERFLOW_variation1() {
        double[][] source = {
            {1e-10, Double.MAX_VALUE},
            {1.0, Double.MAX_VALUE}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNORMALIZATION_UNDERFLOW_variation1() {
        double[][] source = {
            {Double.MAX_VALUE, Double.MIN_NORMAL},
            {0.0, 1.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNAN_SCALAR_variation1() {
        double[][] source = {{Double.NaN}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDELAYED_NAN_PIVOT_variation1() {
        double[][] source = {
            {0.0, Double.NaN},
            {0.0, 0.0}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testINFINITE_SCALAR_variation1_positive() {
        double[][] source = {{Double.POSITIVE_INFINITY}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testINFINITE_SCALAR_variation2_negative() {
        double[][] source = {{Double.NEGATIVE_INFINITY}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testINFINITY_GENERATES_NAN_variation1() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
            {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}
        };
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testALL_ROWS_ALIASED_variation1() {
        double[] shared = {1.0, -2.0, 3.0};
        double[][] source = {shared, shared, shared, shared};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPARTIALLY_ALIASED_ROWS_variation1() {
        double[] shared = {1.0, 0.0};
        double[] independent = {0.0, 1.0};
        double[][] source = {shared, shared, independent};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
