import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static double[][] generateFollowUp(double[][] source) {
        int rows = source.length;
        int columns = source[0].length;
        double[][] transpose = new double[columns][rows];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                transpose[column][row] = source[row][column];
            }
        }
        return transpose;
    }

    private static void assertMetamorphicRelationFor(double[][] source) {
        double[][] followUp = generateFollowUp(source);
        int sourceOutput = MatrixRank.computeRank(source);
        int followUpOutput = MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(
            sourceOutput,
            followUpOutput,
            "A matrix and its transpose must have the same rank");
    }

    private static double[][] identityMatrix(int size) {
        double[][] matrix = new double[size][size];
        for (int index = 0; index < size; index++) {
            matrix[index][index] = 1.0;
        }
        return matrix;
    }

    @Test
    void ZERO_SCALAR_variation1() {
        double[][] source = {{0.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SUB_EPSILON_SCALAR_variation1() {
        double[][] source = {{5e-11}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    void EXACT_EPSILON_SCALAR_variation1() {
        double[][] source = {{1e-10}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_EPSILON_SCALAR_variation1() {
        double[][] source = {{-1e-10}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_ABOVE_EPSILON_SCALAR_variation1() {
        double[][] source = {{1.0001e-10}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ALL_ZERO_RECTANGULAR_variation1() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ALL_SUB_EPSILON_RECTANGULAR_variation1() {
        double[][] source = {
            {5e-11, -4e-11, 1e-300},
            {-9e-11, 2e-11, -7e-11}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SIGNED_ZERO_MATRIX_variation1() {
        double[][] source = {
            {0.0, -0.0},
            {-0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_ROW_INTERIOR_PIVOT_variation1() {
        double[][] source = {{0.0, 0.0, 2.0, 3.0, 0.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_ROW_LAST_COLUMN_PIVOT_variation1() {
        double[][] source = {{0.0, 0.0, 0.0, -2.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_COLUMN_TOP_PIVOT_variation1() {
        double[][] source = {
            {2.0},
            {4.0},
            {0.0},
            {-3.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_COLUMN_LOWER_PIVOT_variation1() {
        double[][] source = {
            {0.0},
            {5e-11},
            {-2.0},
            {3.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void IDENTITY_MATRIX_variation1() {
        double[][] source = identityMatrix(4);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LARGER_IDENTITY_MATRIX_variation1() {
        double[][] source = identityMatrix(8);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DEFICIENT_DIAGONAL_MATRIX_variation1() {
        double[][] source = {
            {2.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, -3.0, 0.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void PERMUTATION_PIVOTS_OUT_OF_ROW_ORDER_variation1() {
        double[][] source = {
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DENSE_FULL_RANK_SQUARE_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {0.0, 1.0, 4.0},
            {5.0, 6.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ALL_ROWS_MULTIPLES_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {-3.0, -6.0, -9.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ONE_DEPENDENT_ROW_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {0.0, 1.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DUPLICATE_AND_ZERO_ROWS_variation1() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {1.0, -1.0, 2.0},
            {1.0, -1.0, 2.0},
            {0.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation1() {
        double[][] source = {
            {1.0, 0.0, 2.0, 0.0},
            {0.0, 1.0, 0.0, 3.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void WIDE_RANK_DEFICIENT_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0, 4.0},
            {2.0, 4.0, 6.0, 8.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0},
            {2.0, -1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void TALL_RANK_DEFICIENT_variation1() {
        double[][] source = {
            {1.0, 2.0},
            {2.0, 4.0},
            {0.0, 0.0},
            {-1.0, -2.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LEADING_ZERO_COLUMN_variation1() {
        double[][] source = {
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {0.0, 1.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INTERNAL_ZERO_COLUMN_variation1() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void TRAILING_ZERO_COLUMNS_variation1() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {1.0, 1.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MULTIPLE_LEADING_ZERO_COLUMNS_variation1() {
        double[][] source = {
            {0.0, 0.0, 1.0},
            {0.0, 0.0, 2.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ELIMINATION_SKIPPED_FOR_EXACT_ZERO_variation1() {
        double[][] source = {
            {1.0, 3.0},
            {0.0, 2.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ELIMINATION_SKIPPED_FOR_SUB_EPSILON_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {5e-11, 2.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ELIMINATION_OF_MULTIPLE_ROWS_variation1() {
        double[][] source = {
            {1.0, 1.0, 0.0},
            {2.0, 3.0, 1.0},
            {-1.0, 0.0, 2.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void EXACT_CANCELLATION_TO_ZERO_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SUB_EPSILON_ELIMINATION_RESIDUAL_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, 5e-11}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void EXACT_EPSILON_ELIMINATION_RESIDUAL_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, 1e-10}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ABOVE_EPSILON_ELIMINATION_RESIDUAL_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {1.0, 1.0001e-10}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MIXED_SIGNS_FULL_RANK_variation1() {
        double[][] source = {
            {-2.0, 1.0, 0.0},
            {3.0, -4.0, 1.0},
            {0.0, 2.0, -3.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void FRACTIONAL_FULL_RANK_variation1() {
        double[][] source = {
            {0.5, 0.25},
            {0.125, 0.75}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void VERY_LARGE_FINITE_VALUES_variation1() {
        double[][] source = {
            {1e-10, 1e308},
            {1e308, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void TINY_AND_THRESHOLD_VALUES_variation1() {
        double[][] source = {
            {1e-10, 1e-300},
            {1e-300, 1e-10}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MIXED_THRESHOLD_DIAGONAL_variation1() {
        double[][] source = {
            {1e-10, 0.0, 0.0},
            {0.0, 5e-11, 0.0},
            {0.0, 0.0, -1e-10}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INTERLEAVED_ZERO_ROWS_variation1() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {1.0, 0.0, 2.0},
            {0.0, 0.0, 0.0},
            {0.0, 1.0, 3.0},
            {0.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ALL_ROWS_MARKED_BEFORE_LAST_COLUMNS_variation1() {
        double[][] source = {
            {1.0, 0.0, 7.0, 8.0, 9.0},
            {0.0, 1.0, 4.0, 5.0, 6.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_COLUMN_THRESHOLD_SEARCH_variation1() {
        double[][] source = {
            {0.0},
            {5e-11},
            {-5e-11},
            {1e-10}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_ROW_MIXED_MAGNITUDES_variation1() {
        double[][] source = {{5e-11, -1e-10, 2.0, 1e100}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    void COLUMN_ORDER_WITH_LOWER_FIRST_PIVOT_variation1() {
        double[][] source = {
            {0.0, 1.0, 1.0},
            {1.0, 0.0, 1.0},
            {1.0, 1.0, 2.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LATE_PIVOT_AFTER_CANCELLATION_variation1() {
        double[][] source = {
            {1.0, 1.0, 0.0},
            {1.0, 1.0, 0.0},
            {1.0, 2.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }
}
