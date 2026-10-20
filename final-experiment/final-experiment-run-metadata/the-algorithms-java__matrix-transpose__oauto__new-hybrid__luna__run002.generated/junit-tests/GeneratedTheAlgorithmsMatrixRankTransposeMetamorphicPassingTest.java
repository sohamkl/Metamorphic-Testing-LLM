import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static double[][] generateFollowUp(double[][] source) {
        int rows = source.length;
        int columns = source[0].length;
        double[][] followUp = new double[columns][rows];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                followUp[column][row] = source[row][column];
            }
        }
        return followUp;
    }

    private static void verifyTransposeRelation(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ZERO_variation1() {
        double[][] source = {{0.0}};
        verifyTransposeRelation(source);
    }

    @Test
    void SINGLE_NONZERO_variation1() {
        double[][] source = {{7.0}};
        verifyTransposeRelation(source);
    }

    @Test
    void ALL_ZERO_RECTANGULAR_variation1() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ONE_ROW_WIDE_variation1() {
        double[][] source = {{0.0, 0.0, 3.5, -2.0}};
        verifyTransposeRelation(source);
    }

    @Test
    void ONE_COLUMN_TALL_variation1() {
        double[][] source = {
            {0.0},
            {0.0},
            {4.0},
            {-3.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void PIVOT_AFTER_LEADING_ZERO_variation1() {
        double[][] source = {
            {0.0, 1.0},
            {2.0, 3.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void PIVOT_AFTER_MARKED_ROW_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 2.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void FULL_RANK_SQUARE_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {0.0, 1.0, 4.0},
            {5.0, 6.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void FULL_COLUMN_RANK_TALL_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0},
            {2.0, -1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void FULL_ROW_RANK_WIDE_variation1() {
        double[][] source = {
            {1.0, 0.0, 2.0, 3.0},
            {0.0, 1.0, 4.0, 5.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void DEPENDENT_ROWS_variation1() {
        double[][] source = {
            {1.0, 2.0},
            {2.0, 4.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void DEPENDENT_COLUMNS_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {2.0, 2.0},
            {3.0, 3.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ZERO_LEADING_COLUMNS_variation1() {
        double[][] source = {
            {0.0, 0.0, 1.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 2.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void INTERMEDIATE_RANK_RECTANGULAR_variation1() {
        double[][] source = {
            {1.0, 2.0, 0.0, 1.0},
            {0.0, 1.0, 1.0, 2.0},
            {1.0, 3.0, 1.0, 3.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void EXACT_EPSILON_PIVOT_variation1() {
        double[][] source = {{1e-10}};
        verifyTransposeRelation(source);
    }

    @Test
    void JUST_BELOW_EPSILON_variation1() {
        double[][] source = {{9.999999999e-11}};
        verifyTransposeRelation(source);
    }

    @Test
    void JUST_ABOVE_EPSILON_variation1() {
        double[][] source = {{1.0000000001e-10}};
        verifyTransposeRelation(source);
    }

    @Test
    void NEGATIVE_EPSILON_BOUNDARIES_variation1() {
        double[][] source = {
            {-1e-10, -9.999999999e-11, -1.0000000001e-10}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void SIGNED_ZERO_variation1() {
        double[][] source = {
            {-0.0, 0.0},
            {0.0, -0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void LARGE_FINITE_VALUES_variation1() {
        double[][] source = {
            {1e200, 2e200},
            {3e200, 5e200}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void SMALL_FINITE_VALUES_variation1() {
        double[][] source = {
            {2e-10, 3e-10},
            {5e-10, 7e-10}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void NAN_ENTRY_variation1() {
        double[][] source = {
            {1.0, Double.NaN},
            {0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void POSITIVE_INFINITY_ENTRY_variation1() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, 1.0},
            {1.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void NEGATIVE_INFINITY_ENTRY_variation1() {
        double[][] source = {
            {Double.NEGATIVE_INFINITY, 2.0},
            {0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void TRANSPOSE_SHAPE_SWAP_variation1() {
        double[][] source = {
            {1.0, 0.0, 2.0, 0.0, 3.0},
            {0.0, 1.0, 0.0, 4.0, 5.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void FRACTIONAL_ELIMINATION_variation1() {
        double[][] source = {
            {2.0, 1.0, 3.0},
            {1.0, 4.0, 2.0},
            {0.0, 1.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ZERO_RESIDUAL_AFTER_ELIMINATION_variation1() {
        double[][] source = {
            {2.0, 4.0, 1.0},
            {1.0, 2.0, 3.0},
            {0.0, 1.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void DECIMAL_CANCELLATION_variation1() {
        double[][] source = {
            {0.1, 0.2},
            {0.2, 0.4}
        };
        verifyTransposeRelation(source);
    }
}
