import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private void verifyTransposeRelation(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp =
                thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_SCALAR_variation1() {
        double[][] source = {{0.0}};
        verifyTransposeRelation(source);
    }

    @Test
    void NEGATIVE_ZERO_SCALAR_variation1() {
        double[][] source = {{-0.0}};
        verifyTransposeRelation(source);
    }

    @Test
    void SUB_EPSILON_SCALAR_variation1() {
        double[][] source = {{5e-11}};
        verifyTransposeRelation(source);
    }

    @Test
    void POSITIVE_EPSILON_SCALAR_variation1() {
        double[][] source = {{1e-10}};
        verifyTransposeRelation(source);
    }

    @Test
    void NEGATIVE_EPSILON_SCALAR_variation1() {
        double[][] source = {{-1e-10}};
        verifyTransposeRelation(source);
    }

    @Test
    void ORDINARY_NONZERO_SCALAR_variation1() {
        double[][] source = {{-3.5}};
        verifyTransposeRelation(source);
    }

    @Test
    void ZERO_ROW_VECTOR_variation1() {
        double[][] source = {{0.0, 0.0, 0.0, 0.0}};
        verifyTransposeRelation(source);
    }

    @Test
    void ZERO_COLUMN_VECTOR_variation1() {
        double[][] source = {
            {0.0},
            {0.0},
            {0.0},
            {0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void NONZERO_ROW_VECTOR_WITH_LEADING_ZERO_variation1() {
        double[][] source = {{0.0, 2.0, 0.0, -3.0}};
        verifyTransposeRelation(source);
    }

    @Test
    void NONZERO_COLUMN_VECTOR_WITH_LATE_PIVOT_variation1() {
        double[][] source = {
            {0.0},
            {0.0},
            {2.0},
            {-3.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void RECTANGULAR_ZERO_MATRIX_variation1() {
        double[][] source = {
            {0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void SQUARE_IDENTITY_FULL_RANK_variation1() {
        double[][] source = {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void PERMUTATION_MATRIX_LOWER_FIRST_PIVOT_variation1() {
        double[][] source = {
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation1() {
        double[][] source = {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0},
            {0.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation1() {
        double[][] source = {
            {1.0, 0.0, 2.0, 0.0},
            {0.0, 1.0, 3.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void TALL_RANK_DEFICIENT_variation1() {
        double[][] source = {
            {1.0, 0.0, 1.0},
            {0.0, 1.0, 1.0},
            {1.0, 1.0, 2.0},
            {2.0, 2.0, 4.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void WIDE_RANK_DEFICIENT_variation1() {
        double[][] source = {
            {1.0, 0.0, 1.0, 2.0, 0.0},
            {0.0, 1.0, 1.0, 3.0, 0.0},
            {1.0, 1.0, 2.0, 5.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void PROPORTIONAL_ROWS_RANK_ONE_variation1() {
        double[][] source = {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {-3.0, -6.0, -9.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void DUPLICATE_ROWS_PLUS_INDEPENDENT_ROW_variation1() {
        double[][] source = {
            {1.0, 2.0, 0.0},
            {1.0, 2.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void DUPLICATE_COLUMNS_PLUS_INDEPENDENT_COLUMN_variation1() {
        double[][] source = {
            {1.0, 1.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void INTERSPERSED_ZERO_ROWS_variation1() {
        double[][] source = {
            {0.0, 0.0},
            {1.0, 2.0},
            {0.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void LEADING_ZERO_COLUMNS_variation1() {
        double[][] source = {
            {0.0, 0.0, 1.0, 2.0},
            {0.0, 0.0, 0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void INTERIOR_ZERO_COLUMN_variation1() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void TRAILING_ZERO_COLUMN_variation1() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void PIVOT_FOUND_IN_LOWER_ROW_variation1() {
        double[][] source = {
            {0.0, 1.0},
            {2.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void MARKED_ROW_SKIPPED_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ELIMINATION_REVEALS_INDEPENDENCE_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 2.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ELIMINATION_EXACT_CANCELLATION_variation1() {
        double[][] source = {
            {2.0, 4.0},
            {1.0, 2.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void NON_UNIT_NEGATIVE_AND_FRACTIONAL_PIVOTS_variation1() {
        double[][] source = {
            {-2.0, 4.0, 1.0},
            {0.0, 0.5, 3.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ELIMINATION_RESIDUAL_BELOW_EPSILON_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + 5e-11}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ELIMINATION_RESIDUAL_AT_OR_ABOVE_EPSILON_variation1() {
        double[][] source = {
            {1.0, 1.0},
            {1.0, 1.0 + 1e-10}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ALL_ENTRIES_STRICTLY_BELOW_EPSILON_variation1() {
        double[][] source = {
            {5e-11, -9e-11},
            {1e-12, -5e-11}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void SUB_EPSILON_CANDIDATE_SKIPPED_BEFORE_LOWER_PIVOT_variation1() {
        double[][] source = {
            {5e-11, 1.0},
            {1.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void MIXED_MAGNITUDE_DIAGONAL_variation1() {
        double[][] source = {
            {1e-10, 0.0},
            {0.0, 1e10}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void FINITE_ELIMINATION_OVERFLOW_variation1() {
        double[][] source = {
            {Double.MAX_VALUE, Double.MAX_VALUE},
            {Double.MAX_VALUE, -Double.MAX_VALUE}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void SUBNORMAL_TRAILING_NORMALIZATION_variation1() {
        double[][] source = {
            {1e-10, Double.MIN_VALUE},
            {0.0, 1e-10}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void NAN_SCALAR_variation1() {
        double[][] source = {{Double.NaN}};
        verifyTransposeRelation(source);
    }

    @Test
    void NAN_IN_TRAILING_PIVOT_ROW_ENTRY_variation1() {
        double[][] source = {
            {1.0, Double.NaN},
            {0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void NAN_AS_LATE_PIVOT_variation1() {
        double[][] source = {
            {0.0, Double.NaN},
            {0.0, 0.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void POSITIVE_INFINITY_SCALAR_variation1() {
        double[][] source = {{Double.POSITIVE_INFINITY}};
        verifyTransposeRelation(source);
    }

    @Test
    void INFINITY_WITH_INDEPENDENT_FINITE_ROW_variation1() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, 1.0},
            {0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void INFINITY_DIVIDED_BY_INFINITY_variation1() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
            {0.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void ALIASED_DUPLICATE_ROWS_variation1() {
        double[] sharedRow = {1.0, 2.0};
        double[][] source = {sharedRow, sharedRow};
        verifyTransposeRelation(source);
    }

    @Test
    void TALL_DELAYED_PIVOTS_WITH_EXTRA_DEPENDENT_ROW_variation1() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0},
            {1.0, 1.0, 1.0}
        };
        verifyTransposeRelation(source);
    }

    @Test
    void WIDE_RANK_SATURATED_BEFORE_FINAL_COLUMNS_variation1() {
        double[][] source = {
            {0.0, 1.0, 0.0, 2.0, 4.0},
            {0.0, 0.0, 1.0, 3.0, 5.0}
        };
        verifyTransposeRelation(source);
    }
}
