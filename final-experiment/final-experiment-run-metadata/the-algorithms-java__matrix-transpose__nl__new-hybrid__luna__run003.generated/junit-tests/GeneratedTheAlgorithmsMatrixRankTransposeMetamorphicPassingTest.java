import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static double[][] generateFollowUp(double[][] source) {
        double[][] transpose = new double[source[0].length][source.length];
        for (int row = 0; row < source.length; row++) {
            for (int column = 0; column < source[row].length; column++) {
                transpose[column][row] = source[row][column];
            }
        }
        return transpose;
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(double[][] source) {
        double[][] snapshot = copy(source);
        int sourceOutput = MatrixRank.computeRank(source);
        assertUnchanged(snapshot, source);

        double[][] followUp = generateFollowUp(source);
        int followUpOutput = MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static double[][] copy(double[][] matrix) {
        double[][] result = new double[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            result[row] = matrix[row].clone();
        }
        return result;
    }

    private static void assertUnchanged(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }

    @Test
    void SINGLE_ZERO_ENTRY_variation1() {
        double[][] source = {{0.0}};
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(0, sourceOutput);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_NONZERO_ENTRY_variation1() {
        double[][] source = {{0.0, 4.0, -2.0}};
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(1, sourceOutput);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ALL_ZERO_RECTANGULAR_variation1() {
        double[][] source = {
            {0.0},
            {-0.0},
            {0.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(0, sourceOutput);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ONE_BY_MANY_ROW_variation1() {
        double[][] source = {{0.0, 1e-10, 3.0, -5.0}};
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(1, sourceOutput);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MANY_BY_ONE_COLUMN_variation1() {
        double[][] source = {
            {0.0},
            {-7.0},
            {2.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(1, sourceOutput);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DIAGONAL_FULL_RANK_variation1() {
        double[][] source = {
            {2.0, 0.0},
            {0.0, -3.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(2, sourceOutput);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation1() {
        double[][] source = {
            {1.0, 2.0},
            {3.0, 4.0},
            {5.0, 7.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(2, sourceOutput);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation1() {
        double[][] source = {
            {0.0, 2.0, 1.0, 4.0},
            {0.0, 0.0, 3.0, 5.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertEquals(2, sourceOutput);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DUPLICATE_ROWS_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {1.0, 2.0, 3.0},
            {0.0, 1.0, 1.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void PROPORTIONAL_ROWS_variation1() {
        double[][] source = {
            {2.0, -1.0, 4.0},
            {6.0, -3.0, 12.0},
            {1.0, 0.0, 2.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void PIVOT_BELOW_LEADING_ZERO_variation1() {
        double[][] source = {
            {0.0, 1.0, 2.0},
            {3.0, 4.0, 5.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MARKED_ROW_SKIPPED_variation1() {
        double[][] source = {
            {2.0, 3.0, 1.0},
            {0.0, 4.0, 5.0},
            {0.0, 0.0, 6.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void COLUMN_WITHOUT_AVAILABLE_PIVOT_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 0.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NONZERO_ELIMINATION_variation1() {
        double[][] source = {
            {2.0, 1.0, 3.0},
            {4.0, 5.0, 7.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ZERO_ELIMINATION_OPERANDS_variation1() {
        double[][] source = {
            {1.0, 2.0},
            {0.0, 3.0},
            {0.0, 4.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void BELOW_EPSILON_PIVOT_variation1() {
        double[][] source = {
            {5e-11, 0.0, 1.0},
            {0.0, 2.0, 3.0},
            {0.0, 0.0, 4.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void EXACT_EPSILON_PIVOT_variation1() {
        double[][] source = {
            {1e-10, 2.0},
            {0.0, 3.0},
            {0.0, 0.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SIGNED_ZERO_VALUES_variation1() {
        double[][] source = {
            {0.0, -0.0, 1.0},
            {-0.0, 2.0, 0.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ABOVE_EPSILON_SMALL_VALUES_variation1() {
        double[][] source = {
            {2e-9, 3e-9},
            {4e-9, 7e-9}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void CANCELLATION_DURING_ELIMINATION_variation1() {
        double[][] source = {
            {1.0, 1.0, 2.0},
            {2.0, 2.0, 4.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LARGE_FINITE_VALUES_variation1() {
        double[][] source = {
            {1e200, 2e100},
            {3e100, 4e200}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SMALL_FINITE_VALUES_variation1() {
        double[][] source = {
            {1e-200, 0.0},
            {0.0, 2e-9}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NAN_ENTRY_variation1() {
        double[][] source = {
            {Double.NaN, 1.0, 2.0},
            {3.0, 4.0, 5.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_INFINITY_ENTRY_variation1() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, 1.0},
            {2.0, 3.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_INFINITY_ENTRY_variation1() {
        double[][] source = {
            {Double.NEGATIVE_INFINITY, 1.0, 2.0},
            {3.0, 4.0, 5.0},
            {6.0, 7.0, 8.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MIXED_SPECIAL_VALUES_variation1() {
        double[][] source = {
            {Double.NaN, Double.POSITIVE_INFINITY},
            {Double.NEGATIVE_INFINITY, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    void TRANSPOSE_DIMENSION_SWAP_variation1() {
        double[][] source = {
            {0.0, 1.0, 2.0, 3.0},
            {0.0, 2.0, 4.0, 6.0},
            {1.0, 0.0, 1.0, 0.0}
        };
        int sourceOutput = MatrixRank.computeRank(source);
        assertMetamorphicRelationFor(source);
        assertEquals(source[0].length, generateFollowUp(source).length);
        assertEquals(source.length, generateFollowUp(source)[0].length);
    }
}
