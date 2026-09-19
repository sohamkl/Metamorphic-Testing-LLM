import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static double[][] matrix(double[]... rows) {
        return rows;
    }

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

    @Test
    void ALL_ZERO_MATRICES_1() {
        double[][] source = matrix(new double[] {0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_MATRICES_2() {
        double[][] source = matrix(new double[] {0.0, 0.0, 0.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_ZERO_MATRICES_3() {
        double[][] source = matrix(
                new double[] {0.0},
                new double[] {0.0},
                new double[] {0.0},
                new double[] {0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void IDENTITY_AND_DIAGONAL_FULL_RANK_1() {
        double[][] source = matrix(
                new double[] {1.0, 0.0, 0.0, 0.0},
                new double[] {0.0, -2.0, 0.0, 0.0},
                new double[] {0.0, 0.0, 3.0, 0.0},
                new double[] {0.0, 0.0, 0.0, 4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_1() {
        double[][] source = matrix(
                new double[] {1.0, 0.0, 2.0, -1.0},
                new double[] {0.0, 1.0, 3.0, 4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_2() {
        double[][] source = matrix(
                new double[] {1.0, 0.0, 0.0, 0.0, 0.0},
                new double[] {0.0, 1.0, 0.0, 0.0, 0.0},
                new double[] {0.0, 0.0, 1.0, 0.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_3() {
        double[][] source = matrix(
                new double[] {-1.0, 0.0, 2.0},
                new double[] {0.0, -2.0, 5.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void WIDE_FULL_ROW_RANK_4() {
        double[][] source = matrix(new double[] {0.0, 1.0, 2.0, 3.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_1() {
        double[][] source = matrix(
                new double[] {1.0},
                new double[] {0.0},
                new double[] {2.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_2() {
        double[][] source = matrix(
                new double[] {1.0, 0.0},
                new double[] {0.0, 1.0},
                new double[] {2.0, 3.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_3() {
        double[][] source = matrix(
                new double[] {1.0, 0.0, 0.0},
                new double[] {0.0, 1.0, 0.0},
                new double[] {0.0, 0.0, 1.0},
                new double[] {2.0, -1.0, 4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_4() {
        double[][] source = matrix(
                new double[] {1.0, 0.0},
                new double[] {0.0, 1.0},
                new double[] {-3.0, 2.0},
                new double[] {4.0, 5.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_ROWS_1() {
        double[][] source = matrix(
                new double[] {1.0, 2.0},
                new double[] {2.0, 4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_ROWS_2() {
        double[][] source = matrix(
                new double[] {1.0, -2.0, 3.0},
                new double[] {-2.0, 4.0, -6.0},
                new double[] {0.0, 0.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_ROWS_3() {
        double[][] source = matrix(
                new double[] {2.0, 1.0},
                new double[] {-6.0, -3.0},
                new double[] {1.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_COLUMNS_1() {
        double[][] source = matrix(
                new double[] {1.0, 2.0},
                new double[] {3.0, 6.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_COLUMNS_2() {
        double[][] source = matrix(
                new double[] {1.0, 2.0, 0.0},
                new double[] {0.0, 0.0, 1.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROPORTIONAL_COLUMNS_3() {
        double[][] source = matrix(
                new double[] {2.0, -4.0, 1.0},
                new double[] {3.0, -6.0, 0.0},
                new double[] {1.0, -2.0, 2.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEADING_ZERO_REQUIRES_LATER_PIVOT_ROW_1() {
        double[][] source = matrix(
                new double[] {0.0, 1.0},
                new double[] {2.0, 3.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEADING_ZERO_REQUIRES_LATER_PIVOT_ROW_2() {
        double[][] source = matrix(
                new double[] {0.0, 1.0, 2.0},
                new double[] {3.0, 4.0, 5.0},
                new double[] {0.0, 0.0, 1.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEADING_ZERO_REQUIRES_LATER_PIVOT_ROW_3() {
        double[][] source = matrix(
                new double[] {0.0},
                new double[] {1.0},
                new double[] {0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MARKED_ROWS_ARE_SKIPPED_1() {
        double[][] source = matrix(
                new double[] {1.0, 2.0},
                new double[] {0.0, 1.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MARKED_ROWS_ARE_SKIPPED_2() {
        double[][] source = matrix(
                new double[] {-1.0, 1.0, 0.0},
                new double[] {0.0, 1.0, 1.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MARKED_ROWS_ARE_SKIPPED_3() {
        double[][] source = matrix(
                new double[] {2.0, 4.0},
                new double[] {0.0, 1.0},
                new double[] {0.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_PIVOT_COLUMNS_BETWEEN_PIVOTS_1() {
        double[][] source = matrix(
                new double[] {1.0, 0.0, 0.0},
                new double[] {0.0, 0.0, 1.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_PIVOT_COLUMNS_BETWEEN_PIVOTS_2() {
        double[][] source = matrix(new double[] {0.0, 1.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_PIVOT_COLUMNS_BETWEEN_PIVOTS_3() {
        double[][] source = matrix(
                new double[] {1.0, 0.0, 2.0},
                new double[] {0.0, 0.0, 3.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_COLUMN_ELIMINATION_1() {
        double[][] source = matrix(
                new double[] {2.0, 3.0, 4.0},
                new double[] {1.0, 5.0, 6.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_COLUMN_ELIMINATION_2() {
        double[][] source = matrix(
                new double[] {-1.0, 2.0, 3.0, 4.0},
                new double[] {2.0, -1.0, 5.0, 6.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_COLUMN_ELIMINATION_3() {
        double[][] source = matrix(
                new double[] {1.0, 2.0},
                new double[] {3.0, 4.0},
                new double[] {5.0, 6.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BELOW_EPSILON_ENTRIES_1() {
        double[][] source = matrix(new double[] {5e-11});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BELOW_EPSILON_ENTRIES_2() {
        double[][] source = matrix(new double[] {-5e-11, 2.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BELOW_EPSILON_ENTRIES_3() {
        double[][] source = matrix(
                new double[] {1.0},
                new double[] {-5e-11},
                new double[] {0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EPSILON_BOUNDARY_AND_ABOVE_1() {
        double[][] source = matrix(
                new double[] {1e-10, 0.0},
                new double[] {0.0, 1.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EPSILON_BOUNDARY_AND_ABOVE_2() {
        double[][] source = matrix(
                new double[] {-1.00001e-10, 0.0},
                new double[] {0.0, 2.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EPSILON_BOUNDARY_AND_ABOVE_3() {
        double[][] source = matrix(
                new double[] {1.0000000001e-10, 2.0},
                new double[] {-3.0, 4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_AND_MIXED_SIGN_PIVOTS_1() {
        double[][] source = matrix(
                new double[] {-2.0, 1.0},
                new double[] {3.0, -4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_AND_MIXED_SIGN_PIVOTS_2() {
        double[][] source = matrix(new double[] {-1.0, 2.0, -3.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_MATRICES_1() {
        double[][] source = matrix(new double[] {0.0, 0.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_MATRICES_2() {
        double[][] source = matrix(new double[] {0.0, 0.0, 2.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ROW_MATRICES_3() {
        double[][] source = matrix(new double[] {-1.0, 2.0, 3.0, 0.0, 4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_MATRICES_1() {
        double[][] source = matrix(
                new double[] {0.0},
                new double[] {0.0},
                new double[] {0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_MATRICES_2() {
        double[][] source = matrix(
                new double[] {0.0},
                new double[] {3.0},
                new double[] {0.0},
                new double[] {0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_COLUMN_MATRICES_3() {
        double[][] source = matrix(
                new double[] {-1.0},
                new double[] {2.0},
                new double[] {4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERLEAVED_ZERO_AND_DUPLICATE_ROWS_1() {
        double[][] source = matrix(
                new double[] {0.0, 0.0},
                new double[] {1.0, 2.0},
                new double[] {2.0, 4.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERLEAVED_ZERO_AND_DUPLICATE_ROWS_2() {
        double[][] source = matrix(
                new double[] {1.0, 0.0, 1.0},
                new double[] {0.0, 0.0, 0.0},
                new double[] {1.0, 0.0, 1.0},
                new double[] {0.0, 1.0, 0.0});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SMALL_FINITE_SCALE_1() {
        double[][] source = matrix(
                new double[] {1e-7, 2e-7, 0.0},
                new double[] {0.0, 1e-7, 3e-7});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SMALL_FINITE_SCALE_2() {
        double[][] source = matrix(
                new double[] {-1e-8, 2e-8},
                new double[] {3e-8, -4e-8},
                new double[] {5e-8, 6e-8});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_SCALE_1() {
        double[][] source = matrix(
                new double[] {1e6, 0.0},
                new double[] {0.0, -2e6});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_SCALE_2() {
        double[][] source = matrix(new double[] {1e8, -2e8, 3e8});
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(generateFollowUp(source));
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
