import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static void verify(double[][] source, int expectedSourceRank) {
        long[][] sourceBitsBefore = rawBits(source);
        int sourceOutput = MatrixRank.computeRank(source);
        Assertions.assertEquals(expectedSourceRank, sourceOutput);
        assertRawBitsEqual(sourceBitsBefore, source);

        double[][] followUp = generateFollowUp(source);
        int followUpOutput = MatrixRank.computeRank(followUp);

        assertRawBitsEqual(sourceBitsBefore, source);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static double[][] generateFollowUp(double[][] source) {
        double[][] transpose = new double[source[0].length][source.length];
        for (int row = 0; row < source.length; row++) {
            for (int column = 0; column < source[0].length; column++) {
                transpose[column][row] = source[row][column];
            }
        }
        return transpose;
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static long[][] rawBits(double[][] matrix) {
        long[][] bits = new long[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                bits[row][column] = Double.doubleToRawLongBits(matrix[row][column]);
            }
        }
        return bits;
    }

    private static void assertRawBitsEqual(long[][] expectedBits, double[][] actual) {
        Assertions.assertEquals(expectedBits.length, actual.length);
        for (int row = 0; row < expectedBits.length; row++) {
            Assertions.assertEquals(expectedBits[row].length, actual[row].length);
            for (int column = 0; column < expectedBits[row].length; column++) {
                Assertions.assertEquals(
                    expectedBits[row][column],
                    Double.doubleToRawLongBits(actual[row][column]));
            }
        }
    }

    @Test
    void SINGLE_ZERO_zeroSingleton() {
        verify(new double[][] {{0.0}}, 0);
    }

    @Test
    void SINGLE_NEGATIVE_ZERO_negativeZeroSingleton() {
        verify(new double[][] {{-0.0}}, 0);
    }

    @Test
    void SINGLE_POSITIVE_PIVOT_unitSingleton() {
        verify(new double[][] {{1.0}}, 1);
    }

    @Test
    void SINGLE_NEGATIVE_PIVOT_negativeSingleton() {
        verify(new double[][] {{-7.0}}, 1);
    }

    @Test
    void SINGLE_BELOW_EPSILON_smallSingleton() {
        verify(new double[][] {{5.0e-11}}, 0);
    }

    @Test
    void SINGLE_AT_EPSILON_thresholdSingleton() {
        verify(new double[][] {{1.0e-10}}, 1);
    }

    @Test
    void SINGLE_ABOVE_EPSILON_aboveThresholdSingleton() {
        verify(new double[][] {{2.0e-10}}, 1);
    }

    @Test
    void ZERO_ROW_VECTOR_allZeroWide() {
        verify(new double[][] {{0.0, 0.0, 0.0}}, 0);
    }

    @Test
    void ZERO_COLUMN_VECTOR_allZeroTall() {
        verify(new double[][] {{0.0}, {0.0}, {0.0}}, 0);
    }

    @Test
    void IDENTITY_THREE_BY_THREE_identity() {
        verify(new double[][] {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0}
        }, 3);
    }

    @Test
    void WIDE_FULL_ROW_RANK_sparseWide() {
        verify(new double[][] {
            {1.0, 0.0, 2.0, 0.0},
            {0.0, 1.0, 0.0, 3.0}
        }, 2);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_independentColumns() {
        verify(new double[][] {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0},
            {2.0, -1.0}
        }, 2);
    }

    @Test
    void LATER_ROW_FIRST_PIVOT_lowerFirstPivot() {
        verify(new double[][] {
            {0.0, 1.0},
            {2.0, 3.0}
        }, 2);
    }

    @Test
    void PERMUTED_IDENTITY_PIVOTS_permutation() {
        verify(new double[][] {
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0}
        }, 3);
    }

    @Test
    void LEADING_ZERO_COLUMN_laterColumnPivot() {
        verify(new double[][] {
            {0.0, 1.0},
            {0.0, 2.0},
            {0.0, 3.0}
        }, 1);
    }

    @Test
    void TRAILING_ZERO_COLUMNS_rankFoundEarly() {
        verify(new double[][] {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0}
        }, 2);
    }

    @Test
    void INTERSPERSED_ZERO_ROW_middleZeroRow() {
        verify(new double[][] {
            {1.0, 0.0},
            {0.0, 0.0},
            {0.0, 1.0}
        }, 2);
    }

    @Test
    void RANK_ONE_MULTIPLES_dependentRows() {
        verify(new double[][] {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {3.0, 6.0, 9.0}
        }, 1);
    }

    @Test
    void DUPLICATE_ROWS_duplicateAndIndependent() {
        verify(new double[][] {
            {1.0, 2.0},
            {1.0, 2.0},
            {0.0, 1.0}
        }, 2);
    }

    @Test
    void RANK_TWO_WIDE_DEPENDENT_linearCombinationWide() {
        verify(new double[][] {
            {1.0, 0.0, 2.0, 1.0},
            {0.0, 1.0, -1.0, 3.0},
            {1.0, 1.0, 1.0, 4.0}
        }, 2);
    }

    @Test
    void RANK_TWO_TALL_DEPENDENT_linearCombinationTall() {
        verify(new double[][] {
            {1.0, 0.0, 1.0},
            {0.0, 1.0, 1.0},
            {1.0, 1.0, 2.0},
            {2.0, -1.0, 1.0}
        }, 2);
    }

    @Test
    void NONZERO_ELIMINATION_TWO_BY_TWO_activeElimination() {
        verify(new double[][] {
            {2.0, 1.0},
            {3.0, 4.0}
        }, 2);
    }

    @Test
    void ELIMINATION_SKIPS_ZERO_PIVOT_ENTRY_zeroEntrySkipped() {
        verify(new double[][] {
            {2.0, 5.0},
            {0.0, 3.0}
        }, 2);
    }

    @Test
    void FRACTIONAL_NORMALIZATION_nonUnitPivots() {
        verify(new double[][] {
            {4.0, 2.0, 6.0},
            {2.0, 3.0, 1.0},
            {0.0, 5.0, 2.0}
        }, 3);
    }

    @Test
    void NEGATIVE_PIVOT_AND_ELIMINATION_negativeLeadingPivot() {
        verify(new double[][] {
            {-2.0, 4.0},
            {6.0, -3.0}
        }, 2);
    }

    @Test
    void LARGE_FINITE_MAGNITUDES_largeFiniteEntries() {
        verify(new double[][] {
            {1.0e100, 2.0e100},
            {3.0e100, 5.0e100}
        }, 2);
    }

    @Test
    void SMALL_SCALE_ABOVE_EPSILON_smallIndependentDiagonal() {
        verify(new double[][] {
            {2.0e-10, 0.0},
            {0.0, 3.0e-10}
        }, 2);
    }

    @Test
    void SMALL_SCALE_BELOW_EPSILON_tinyDiagonal() {
        verify(new double[][] {
            {5.0e-11, 0.0},
            {0.0, -5.0e-11}
        }, 0);
    }

    @Test
    void CANCELLATION_RESIDUAL_BELOW_EPSILON_tinyResidual() {
        verify(new double[][] {
            {1.0, 1.0},
            {1.0, 1.00000000005}
        }, 1);
    }

    @Test
    void CANCELLATION_RESIDUAL_ABOVE_EPSILON_visibleResidual() {
        verify(new double[][] {
            {1.0, 1.0},
            {1.0, 1.0000000002}
        }, 2);
    }

    @Test
    void EXACT_EPSILON_DIAGONAL_thresholdAndUnit() {
        verify(new double[][] {
            {1.0e-10, 0.0, 0.0},
            {0.0, 1.0, 0.0}
        }, 2);
    }

    @Test
    void NAN_SINGLETON_nanPivot() {
        verify(new double[][] {{Double.NaN}}, 1);
    }

    @Test
    void POSITIVE_INFINITY_SINGLETON_positiveInfinityPivot() {
        verify(new double[][] {{Double.POSITIVE_INFINITY}}, 1);
    }

    @Test
    void NEGATIVE_INFINITY_SINGLETON_negativeInfinityPivot() {
        verify(new double[][] {{Double.NEGATIVE_INFINITY}}, 1);
    }

    @Test
    void INFINITE_PIVOT_NORMALIZATION_infiniteLeadingPivot() {
        verify(new double[][] {
            {Double.POSITIVE_INFINITY, 1.0},
            {1.0, 1.0}
        }, 2);
    }

    @Test
    void INFINITY_OVER_INFINITY_NAN_PROPAGATION_infiniteSquare() {
        verify(new double[][] {
            {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
            {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}
        }, 2);
    }

    @Test
    void NAN_MIXED_RECTANGULAR_nanAndFiniteWide() {
        verify(new double[][] {
            {Double.NaN, 0.0, 1.0},
            {0.0, 1.0, 0.0}
        }, 2);
    }

    @Test
    void MIXED_SIGN_SPARSE_RECTANGULAR_laterRowSparsePivot() {
        verify(new double[][] {
            {0.0, -2.0, 0.0, 4.0},
            {0.0, 0.0, 0.0, 0.0},
            {3.0, 0.0, -1.0, 0.0}
        }, 2);
    }
}
