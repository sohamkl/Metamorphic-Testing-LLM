import com.thealgorithms.matrix.MatrixRank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static double[][] generateFollowUp(double[][] source) {
        double[][] transpose = new double[source[0].length][source.length];
        for (int row = 0; row < source.length; row++) {
            for (int column = 0; column < source[0].length; column++) {
                transpose[column][row] = source[row][column];
            }
        }
        return transpose;
    }

    private static void assertMetamorphicRelationFor(double[][] source) {
        int sourceOutput = MatrixRank.computeRank(source);
        int followUpOutput = MatrixRank.computeRank(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static double[][] copy(double[][] matrix) {
        double[][] result = new double[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            result[row] = matrix[row].clone();
        }
        return result;
    }

    private static void assertBitwiseEqual(double[][] expected, double[][] actual) {
        Assertions.assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            Assertions.assertEquals(expected[row].length, actual[row].length);
            for (int column = 0; column < expected[row].length; column++) {
                Assertions.assertEquals(
                    Double.doubleToLongBits(expected[row][column]),
                    Double.doubleToLongBits(actual[row][column]));
            }
        }
    }

    @Test
    void SINGLE_ZERO_onlyCell() {
        assertMetamorphicRelationFor(new double[][] {{0.0}});
    }

    @Test
    void SINGLE_NONZERO_immediatePivot() {
        assertMetamorphicRelationFor(new double[][] {{7.0}});
    }

    @Test
    void SINGLE_BELOW_EPSILON_strictlyIgnored() {
        assertMetamorphicRelationFor(new double[][] {{1.0e-11}});
    }

    @Test
    void SINGLE_POSITIVE_EPSILON_thresholdPivot() {
        assertMetamorphicRelationFor(new double[][] {{1.0e-10}});
    }

    @Test
    void SINGLE_NEGATIVE_EPSILON_negativeThresholdPivot() {
        assertMetamorphicRelationFor(new double[][] {{-1.0e-10}});
    }

    @Test
    void SIGNED_ZERO_MATRIX_signedZerosOnly() {
        assertMetamorphicRelationFor(new double[][] {
            {-0.0, 0.0},
            {0.0, -0.0}
        });
    }

    @Test
    void ZERO_WIDE_ROW_noPivotColumns() {
        assertMetamorphicRelationFor(new double[][] {{0.0, 0.0, 0.0, 0.0}});
    }

    @Test
    void LATE_COLUMN_PIVOT_leadingZeros() {
        assertMetamorphicRelationFor(new double[][] {{0.0, 0.0, 5.0, 9.0}});
    }

    @Test
    void LATE_ROW_PIVOT_leadingZeroRows() {
        assertMetamorphicRelationFor(new double[][] {
            {0.0},
            {0.0},
            {5.0},
            {0.0}
        });
    }

    @Test
    void ONE_COLUMN_MULTIPLE_NONZERO_ROWS_firstCandidateMarked() {
        assertMetamorphicRelationFor(new double[][] {
            {2.0},
            {-3.0},
            {4.0}
        });
    }

    @Test
    void ANTI_DIAGONAL_TWO_BY_TWO_delayedRowPivot() {
        assertMetamorphicRelationFor(new double[][] {
            {0.0, 1.0},
            {1.0, 0.0}
        });
    }

    @Test
    void DUPLICATE_ROWS_RANK_ONE_identicalRows() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 2.0, 3.0},
            {1.0, 2.0, 3.0}
        });
    }

    @Test
    void SCALAR_MULTIPLE_ROWS_RANK_ONE_negativeAndFractionalMultiples() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, -2.0, 4.0},
            {-3.0, 6.0, -12.0},
            {0.5, -1.0, 2.0}
        });
    }

    @Test
    void EXACT_ELIMINATION_CANCELLATION_dependentSquare() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 1.0},
            {1.0, 1.0}
        });
    }

    @Test
    void FULL_RANK_TWO_BY_TWO_immediatePivots() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 2.0},
            {3.0, 4.0}
        });
    }

    @Test
    void DIAGONAL_THREE_BY_THREE_independentDiagonal() {
        assertMetamorphicRelationFor(new double[][] {
            {2.0, 0.0, 0.0},
            {0.0, -3.0, 0.0},
            {0.0, 0.0, 4.0}
        });
    }

    @Test
    void WIDE_FULL_ROW_RANK_rowBoundReached() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 0.0, 2.0, 3.0},
            {0.0, 1.0, 4.0, 5.0}
        });
    }

    @Test
    void WIDE_EXHAUSTED_MARKED_ROWS_trailingColumns() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 0.0, 0.0, 7.0},
            {0.0, 1.0, 0.0, 8.0}
        });
    }

    @Test
    void TALL_FULL_COLUMN_RANK_columnBoundReached() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 0.0},
            {0.0, 1.0},
            {2.0, 3.0},
            {-1.0, 4.0}
        });
    }

    @Test
    void TALL_RANK_ONE_WITH_LEADING_ZERO_initialZeroRow() {
        assertMetamorphicRelationFor(new double[][] {
            {0.0, 0.0},
            {2.0, 4.0},
            {-1.0, -2.0},
            {3.0, 6.0}
        });
    }

    @Test
    void INTERMEDIATE_RANK_THREE_BY_THREE_dependentMiddleRow() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {0.0, 1.0, 1.0}
        });
    }

    @Test
    void NONCONSECUTIVE_PIVOT_COLUMNS_skippedMiddleColumn() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 0.0, 2.0},
            {0.0, 0.0, 3.0},
            {0.0, 0.0, 0.0}
        });
    }

    @Test
    void UPPER_TRIANGULAR_FRACTIONAL_negativeFractionalEntries() {
        assertMetamorphicRelationFor(new double[][] {
            {-2.0, 0.5, 1.5},
            {0.0, 3.0, -0.25},
            {0.0, 0.0, 4.0}
        });
    }

    @Test
    void LOWER_TRIANGULAR_LATE_PIVOTS_eliminationBelowPivot() {
        assertMetamorphicRelationFor(new double[][] {
            {2.0, 0.0, 0.0},
            {3.0, -1.0, 0.0},
            {4.0, 5.0, 6.0}
        });
    }

    @Test
    void RESIDUAL_BELOW_EPSILON_nearZeroResidual() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 1.0},
            {1.0, 1.00000000001}
        });
    }

    @Test
    void RESIDUAL_AT_EPSILON_thresholdResidual() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 1.0},
            {1.0, 1.0000000001}
        });
    }

    @Test
    void MIXED_SUBTHRESHOLD_AND_USABLE_PIVOT_laterUsableEntry() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0e-11, 2.0},
            {0.0, 0.0}
        });
    }

    @Test
    void LARGE_FINITE_VALUES_nearOverflowMagnitude() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0e308, 1.0e308},
            {1.0e308, -1.0e308}
        });
    }

    @Test
    void VERY_SMALL_ALL_SUBTHRESHOLD_allEntriesIgnored() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0e-300, -1.0e-300},
            {5.0e-200, 0.0}
        });
    }

    @Test
    void POSITIVE_INFINITY_SCALAR_infinitePivot() {
        assertMetamorphicRelationFor(new double[][] {{Double.POSITIVE_INFINITY}});
    }

    @Test
    void NEGATIVE_INFINITY_SCALAR_negativeInfinitePivot() {
        assertMetamorphicRelationFor(new double[][] {{Double.NEGATIVE_INFINITY}});
    }

    @Test
    void NAN_SCALAR_nanPivot() {
        assertMetamorphicRelationFor(new double[][] {{Double.NaN}});
    }

    @Test
    void NAN_WITH_FINITE_DIAGONAL_nanAndFinitePivots() {
        assertMetamorphicRelationFor(new double[][] {
            {Double.NaN, 0.0},
            {0.0, 1.0}
        });
    }

    @Test
    void NAN_CREATED_ELIMINATION_RESIDUAL_nanResidual() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 1.0},
            {Double.NaN, 1.0}
        });
    }

    @Test
    void INFINITY_NORMALIZATION_AND_ELIMINATION_infiniteFirstPivot() {
        assertMetamorphicRelationFor(new double[][] {
            {Double.POSITIVE_INFINITY, 1.0},
            {1.0, 1.0}
        });
    }

    @Test
    void RECTANGULAR_THREE_BY_FOUR_RANK_TWO_wideDependentRow() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 2.0, 0.0, 1.0},
            {0.0, 1.0, 1.0, 2.0},
            {1.0, 3.0, 1.0, 3.0}
        });
    }

    @Test
    void RECTANGULAR_FOUR_BY_THREE_RANK_TWO_tallDependentRows() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 0.0, 1.0},
            {0.0, 1.0, 1.0},
            {1.0, 1.0, 2.0},
            {2.0, -1.0, 1.0}
        });
    }

    @Test
    void FIRST_PIVOT_NOT_TOPMOST_AFTER_MARKING_distinctMarkedRows() {
        assertMetamorphicRelationFor(new double[][] {
            {1.0, 1.0, 0.0},
            {0.0, 2.0, 1.0},
            {0.0, 0.0, 3.0}
        });
    }

    @Test
    void SOURCE_ARRAY_PRESERVATION_WITH_ELIMINATION_deepCopyAndTranspose() {
        double[][] source = {
            {2.0, 4.0},
            {1.0, 3.0}
        };
        double[][] snapshot = copy(source);
        assertMetamorphicRelationFor(source);
        assertBitwiseEqual(snapshot, source);
    }
}
