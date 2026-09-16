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
        double[][] sourceSnapshot = copyMatrix(source);
        double[][] sourceRowReferences = source.clone();

        double[][] followUp = generateFollowUp(source);
        double[][] followUpSnapshot = copyMatrix(followUp);
        double[][] followUpRowReferences = followUp.clone();

        int sourceOutput = MatrixRank.computeRank(source);
        assertMatrixUnchanged(source, sourceSnapshot, sourceRowReferences);

        int followUpOutput = MatrixRank.computeRank(followUp);
        assertMatrixUnchanged(source, sourceSnapshot, sourceRowReferences);
        assertMatrixUnchanged(followUp, followUpSnapshot, followUpRowReferences);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static double[][] copyMatrix(double[][] matrix) {
        double[][] copy = new double[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row].clone();
        }
        return copy;
    }

    private static void assertMatrixUnchanged(
        double[][] actual, double[][] expectedValues, double[][] expectedRowReferences) {
        Assertions.assertEquals(expectedValues.length, actual.length);
        for (int row = 0; row < actual.length; row++) {
            Assertions.assertSame(expectedRowReferences[row], actual[row]);
            Assertions.assertEquals(expectedValues[row].length, actual[row].length);
            for (int column = 0; column < actual[row].length; column++) {
                Assertions.assertEquals(
                    Double.doubleToRawLongBits(expectedValues[row][column]),
                    Double.doubleToRawLongBits(actual[row][column]));
            }
        }
    }

    private static double[][] identity(int size) {
        double[][] matrix = new double[size][size];
        for (int index = 0; index < size; index++) {
            matrix[index][index] = 1.0;
        }
        return matrix;
    }

    @Test
    public void SCALAR_STRICTLY_BELOW_EPSILON_variation1_positiveZero() {
        double[][] source = {{+0.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SCALAR_STRICTLY_BELOW_EPSILON_variation2_nonzeroSubThreshold() {
        double[][] source = {{5.0e-11}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SCALAR_EXACT_EPSILON_variation1_positiveBoundary() {
        double[][] source = {{1.0e-10}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SCALAR_EXACT_EPSILON_variation2_negativeBoundary() {
        double[][] source = {{-1.0e-10}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SCALAR_ORDINARY_NONZERO_variation1_fractionalValue() {
        double[][] source = {{2.5}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ALL_ZERO_RECTANGULAR_variation1_wideSignedZeros() {
        double[][] source = {
            {+0.0, -0.0, +0.0, -0.0},
            {-0.0, +0.0, -0.0, +0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ALL_ZERO_RECTANGULAR_variation2_tallSignedZeros() {
        double[][] source = {
            {-0.0, +0.0},
            {+0.0, -0.0},
            {-0.0, -0.0},
            {+0.0, +0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SINGLE_ROW_WITH_ONE_OR_MORE_PIVOTS_CANDIDATES_variation1_firstColumnPivot() {
        double[][] source = {{3.0, 0.0, -2.0, 7.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SINGLE_ROW_WITH_ONE_OR_MORE_PIVOTS_CANDIDATES_variation2_leadingSubThreshold() {
        double[][] source = {{-0.0, 5.0e-11, -4.0, 8.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SINGLE_COLUMN_WITH_ZERO_AND_NONZERO_ROWS_variation1_topRowPivot() {
        double[][] source = {{1.0}, {0.0}, {-3.0}, {0.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SINGLE_COLUMN_WITH_ZERO_AND_NONZERO_ROWS_variation2_lowerRowPivot() {
        double[][] source = {{0.0}, {5.0e-11}, {-2.0}, {4.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SQUARE_IDENTITY_variation1_dimensionTwo() {
        double[][] source = identity(2);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SQUARE_IDENTITY_variation2_dimensionFour() {
        double[][] source = identity(4);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void RECTANGULAR_DIAGONAL_FULL_POSSIBLE_RANK_variation1_wide() {
        double[][] source = {
            {2.0, 0.0, 0.0, 0.0},
            {0.0, -3.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void RECTANGULAR_DIAGONAL_FULL_POSSIBLE_RANK_variation2_tall() {
        double[][] source = {
            {4.0, 0.0},
            {0.0, 0.5},
            {0.0, 0.0},
            {0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void PERMUTATION_MATRIX_LOWER_PIVOT_variation1_twoByTwoSwap() {
        double[][] source = {
            {0.0, 1.0},
            {1.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void PERMUTATION_MATRIX_LOWER_PIVOT_variation2_threeByThreeCycle() {
        double[][] source = {
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LEADING_ZERO_COLUMNS_BEFORE_PIVOTS_variation1_oneLeadingColumn() {
        double[][] source = {
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LEADING_ZERO_COLUMNS_BEFORE_PIVOTS_variation2_twoLeadingColumns() {
        double[][] source = {
            {0.0, 0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void INTERLEAVED_ZERO_COLUMNS_variation1_twoIndependentColumns() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void INTERLEAVED_ZERO_COLUMNS_variation2_threeIndependentColumns() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void DUPLICATE_NONZERO_ROWS_variation1_tallWithZeroRow() {
        double[][] source = {
            {2.0, -1.0},
            {2.0, -1.0},
            {0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void DUPLICATE_NONZERO_ROWS_variation2_wideRepeatedRows() {
        double[][] source = {
            {1.0, 3.0, -2.0, 4.0},
            {1.0, 3.0, -2.0, 4.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void PROPORTIONAL_NONZERO_COLUMNS_variation1_integerScales() {
        double[][] source = {
            {1.0, 2.0, -3.0},
            {2.0, 4.0, -6.0},
            {0.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ZERO_ROWS_BEFORE_AND_AFTER_INDEPENDENT_ROWS_variation1_twoUnitRows() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ZERO_ROWS_BEFORE_AND_AFTER_INDEPENDENT_ROWS_variation2_threeUnitRows() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void TWO_BY_TWO_ACTIVE_ELIMINATION_FULL_RANK_variation1_nonzeroResidual() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 2.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void TWO_BY_TWO_ACTIVE_ELIMINATION_DEPENDENT_variation1_exactCancellation() {
        double[][] source = {
            {1.0, 2.0},
            {2.0, 4.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void DENSE_THREE_BY_THREE_FULL_RANK_variation1_multipleEliminations() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 5.0, 8.0},
            {1.0, 0.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void DENSE_THREE_BY_THREE_INTERMEDIATE_RANK_variation1_dependentRow() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {0.0, 1.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void WIDE_FULL_ROW_RANK_WITH_EXTRA_COLUMNS_variation1_twoRows() {
        double[][] source = {
            {1.0, 0.0, 2.0, -1.0, 4.0},
            {0.0, 1.0, 3.0, 5.0, -2.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void WIDE_FULL_ROW_RANK_WITH_EXTRA_COLUMNS_variation2_threeRows() {
        double[][] source = {
            {1.0, 0.0, 0.0, 2.0, 3.0, 4.0},
            {0.0, 1.0, 0.0, -1.0, 5.0, 2.0},
            {0.0, 0.0, 1.0, 7.0, -3.0, 6.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void TALL_FULL_COLUMN_RANK_WITH_EXTRA_ROWS_variation1_zeroAndDependentRows() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0},
            {0.0, 0.0},
            {2.0, -3.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void TALL_FULL_COLUMN_RANK_WITH_EXTRA_ROWS_variation2_threeColumns() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 2.0, 3.0},
            {-2.0, 0.0, 1.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void PIVOT_ONLY_IN_LAST_COLUMN_variation1_leadingSubThresholdColumns() {
        double[][] source = {
            {0.0, 5.0e-11, -2.0e-11, 0.0},
            {-0.0, -4.0e-11, 0.0, 3.0},
            {1.0e-12, 0.0, 9.0e-11, -5.0e-11}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SINGLE_COLUMN_ACTIVE_ELIMINATION_EMPTY_INNER_LOOP_variation1_multipleNonzeroRows() {
        double[][] source = {{1.0}, {2.0}, {0.0}};
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void EXACT_THRESHOLD_DIAGONAL_variation1_oppositeSigns() {
        double[][] source = {
            {1.0e-10, 0.0},
            {0.0, -1.0e-10}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ALL_ENTRIES_STRICTLY_SUB_EPSILON_variation1_wideNonzeroValues() {
        double[][] source = {
            {5.0e-11, -4.0e-11, 0.0},
            {-9.0e-11, 2.0e-11, -0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ALL_ENTRIES_STRICTLY_SUB_EPSILON_variation2_tallNonzeroValues() {
        double[][] source = {
            {Double.MIN_VALUE, -5.0e-11},
            {8.0e-11, 0.0},
            {-7.0e-11, -Double.MIN_VALUE}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void CANCELLATION_RESIDUAL_BELOW_EPSILON_variation1_binaryResidual() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + 0x1.0p-40}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void CANCELLATION_RESIDUAL_ABOVE_EPSILON_variation1_binaryResidual() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + 0x1.0p-33}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_PIVOT_NORMALIZATION_variation1_activeElimination() {
        double[][] source = {
            {-2.0, 4.0},
            {1.0, 3.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FRACTIONAL_PIVOT_NORMALIZATION_variation1_fractionalEntries() {
        double[][] source = {
            {0.5, 0.25},
            {0.25, 0.5}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void VERY_LARGE_FINITE_DIAGONAL_variation1_oppositeSigns() {
        double[][] source = {
            {Double.MAX_VALUE, 0.0},
            {0.0, -Double.MAX_VALUE}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void MINIMAL_POSITIVE_VALUES_CLASSIFIED_ZERO_variation1_signedExtremes() {
        double[][] source = {
            {Double.MIN_VALUE, -0.0},
            {+0.0, -Double.MIN_VALUE}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void MIXED_MAGNITUDE_DIAGONAL_variation1_thresholdAndLargePivots() {
        double[][] source = {
            {1.0e308, 0.0, 0.0},
            {0.0, 1.0e-10, 0.0},
            {0.0, 0.0, -1.0e-9}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ALIASED_DUPLICATE_ROW_REFERENCES_variation1_sharedRowArray() {
        double[] shared = {2.0, -1.0, 4.0, 0.0};
        double[][] source = {
            shared,
            shared,
            {0.0, 0.0, 0.0, 0.0}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void GENERAL_DENSE_WELL_SCALED_RECTANGULAR_variation1_tallDenseMatrix() {
        double[][] source = {
            {1.25, -2.5, 3.75},
            {4.5, 5.25, -6.5},
            {-7.75, 8.5, 9.25},
            {2.125, -3.5, 4.875}
        };
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void GENERAL_DENSE_MIXED_SCALE_RECTANGULAR_variation1_mixedFiniteMagnitudes() {
        double[][] source = {
            {5.0e-11, 1.0e-10, 3.0, 1.0e50},
            {-1.0e-10, 2.0, -4.0, 2.0e50},
            {7.0e-12, -3.0, 5.0, -1.0e50}
        };
        assertMetamorphicRelationFor(source);
    }
}
