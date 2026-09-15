import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicTest {

    private static void exercise(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp =
                thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_SINGLETON_1() {
        exercise(new double[][] {{0.0}});
    }

    @Test
    void NONZERO_SINGLETON_1() {
        exercise(new double[][] {{5.0}});
    }

    @Test
    void ZERO_ROW_VECTOR_1() {
        exercise(new double[][] {{0.0, 0.0, 0.0}});
    }

    @Test
    void ZERO_COLUMN_VECTOR_1() {
        exercise(new double[][] {{0.0}, {0.0}, {0.0}});
    }

    @Test
    void ZERO_RECTANGULAR_MATRIX_1() {
        exercise(new double[][] {
            {0.0, 0.0},
            {0.0, 0.0},
            {0.0, 0.0}
        });
    }

    @Test
    void NONZERO_ROW_VECTOR_1() {
        exercise(new double[][] {{0.0, 0.0, 7.0}});
    }

    @Test
    void NONZERO_ROW_VECTOR_2() {
        exercise(new double[][] {{-4.0, 0.0, 9.0, 0.0}});
    }

    @Test
    void NONZERO_COLUMN_VECTOR_1() {
        exercise(new double[][] {{0.0}, {0.0}, {6.0}});
    }

    @Test
    void NONZERO_COLUMN_VECTOR_2() {
        exercise(new double[][] {{-2.0}, {0.0}, {8.0}, {0.0}});
    }

    @Test
    void IDENTITY_TWO_BY_TWO_1() {
        exercise(new double[][] {
            {1.0, 0.0},
            {0.0, 1.0}
        });
    }

    @Test
    void IDENTITY_THREE_BY_THREE_1() {
        exercise(new double[][] {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0}
        });
    }

    @Test
    void FULL_ROW_RANK_WIDE_1() {
        exercise(new double[][] {
            {1.0, 0.0, 2.0},
            {0.0, 1.0, 3.0}
        });
    }

    @Test
    void FULL_ROW_RANK_WIDE_2() {
        exercise(new double[][] {
            {2.0, -1.0, 4.0, 0.0},
            {0.0, 3.0, 1.0, 5.0}
        });
    }

    @Test
    void FULL_COLUMN_RANK_TALL_1() {
        exercise(new double[][] {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0}
        });
    }

    @Test
    void FULL_COLUMN_RANK_TALL_2() {
        exercise(new double[][] {
            {2.0, 1.0},
            {-1.0, 3.0},
            {4.0, -2.0},
            {1.0, 1.0}
        });
    }

    @Test
    void FULL_RANK_RECTANGULAR_THREE_BY_FOUR_1() {
        exercise(new double[][] {
            {1.0, 0.0, 0.0, 1.0},
            {0.0, 1.0, 0.0, 2.0},
            {0.0, 0.0, 1.0, 3.0}
        });
    }

    @Test
    void DIAGONAL_WITH_ZERO_1() {
        exercise(new double[][] {
            {2.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, -3.0}
        });
    }

    @Test
    void PROPORTIONAL_ROWS_1() {
        exercise(new double[][] {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {-3.0, -6.0, -9.0}
        });
    }

    @Test
    void DUPLICATE_ROWS_WITH_ZERO_ROW_1() {
        exercise(new double[][] {
            {1.0, 2.0, 0.0},
            {1.0, 2.0, 0.0},
            {0.0, 0.0, 0.0}
        });
    }

    @Test
    void LATE_ROW_PIVOT_1() {
        exercise(new double[][] {
            {0.0, 1.0},
            {2.0, 0.0},
            {0.0, 0.0}
        });
    }

    @Test
    void LATE_ROW_PIVOT_2() {
        exercise(new double[][] {
            {0.0, 0.0, 1.0},
            {0.0, 2.0, 0.0},
            {3.0, 0.0, 0.0}
        });
    }

    @Test
    void LEADING_ZERO_COLUMNS_1() {
        exercise(new double[][] {
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        });
    }

    @Test
    void MARKED_ROW_SKIP_1() {
        exercise(new double[][] {
            {1.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {0.0, 2.0, 0.0}
        });
    }

    @Test
    void MARKED_ROW_SKIP_2() {
        exercise(new double[][] {
            {2.0, 1.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 3.0, 1.0}
        });
    }

    @Test
    void ELIMINATION_NONZERO_OTHER_ROWS_1() {
        exercise(new double[][] {
            {2.0, 1.0, 3.0},
            {4.0, 0.0, 6.0},
            {-2.0, 5.0, 1.0}
        });
    }

    @Test
    void ELIMINATION_NONZERO_OTHER_ROWS_2() {
        exercise(new double[][] {
            {1.0, 2.0, 4.0},
            {3.0, 1.0, 0.0}
        });
    }

    @Test
    void ELIMINATION_ZERO_OTHER_ROWS_1() {
        exercise(new double[][] {
            {1.0, 2.0, 3.0},
            {0.0, 4.0, 5.0},
            {0.0, 6.0, 7.0}
        });
    }

    @Test
    void EXACT_ROW_DEPENDENCE_AFTER_ELIMINATION_1() {
        exercise(new double[][] {
            {1.0, 2.0, 3.0},
            {2.0, 4.0, 6.0},
            {1.0, 0.0, 1.0}
        });
    }

    @Test
    void EPSILON_BELOW_PIVOT_1() {
        exercise(new double[][] {{5.0e-11}});
    }

    @Test
    void EPSILON_BELOW_PIVOT_2() {
        exercise(new double[][] {{-9.0e-11}});
    }

    @Test
    void EPSILON_EXACT_PIVOT_1() {
        exercise(new double[][] {{1.0e-10}});
    }

    @Test
    void EPSILON_EXACT_PIVOT_2() {
        exercise(new double[][] {{-1.0e-10}});
    }

    @Test
    void EPSILON_ABOVE_PIVOT_1() {
        exercise(new double[][] {{1.1e-10}});
    }

    @Test
    void NEGATIVE_PIVOTS_1() {
        exercise(new double[][] {
            {-2.0, -1.0},
            {0.0, -3.0}
        });
    }

    @Test
    void MIXED_MAGNITUDES_1() {
        exercise(new double[][] {
            {1.0e-8, 1.0e8},
            {-1.0e8, 1.0e-8}
        });
    }

    @Test
    void PERMUTED_IDENTITY_1() {
        exercise(new double[][] {
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0},
            {1.0, 0.0, 0.0}
        });
    }

    @Test
    void INTERMEDIATE_RANK_THREE_BY_FOUR_1() {
        exercise(new double[][] {
            {1.0, 0.0, 1.0, 2.0},
            {0.0, 1.0, 1.0, 3.0},
            {1.0, 1.0, 2.0, 5.0}
        });
    }

    @Test
    void INTERMEDIATE_RANK_THREE_BY_FOUR_2() {
        exercise(new double[][] {
            {2.0, 1.0, 3.0, 5.0},
            {1.0, -1.0, 0.0, -1.0},
            {3.0, 0.0, 3.0, 4.0}
        });
    }

    @Test
    void RECTANGULAR_SINGLE_NONZERO_1() {
        exercise(new double[][] {
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 7.0}
        });
    }

    @Test
    void SOURCE_COPY_PRESERVATION_1() {
        exercise(new double[][] {
            {2.0, 4.0, 6.0},
            {1.0, 3.0, 5.0}
        });
    }

    @Test
    void SQUARE_RANK_ZERO_TO_FULL_RANGE_1() {
        exercise(new double[][] {
            {0.0, 0.0},
            {0.0, 0.0}
        });
    }

    @Test
    void SQUARE_RANK_ZERO_TO_FULL_RANGE_2() {
        exercise(new double[][] {
            {1.0, 2.0},
            {2.0, 4.0}
        });
    }

    @Test
    void SQUARE_RANK_ZERO_TO_FULL_RANGE_3() {
        exercise(new double[][] {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0}
        });
    }

    @Test
    void WIDE_TO_TALL_TRANSPOSE_PAIR_1() {
        exercise(new double[][] {
            {1.0, 0.0, 1.0},
            {0.0, 1.0, 1.0}
        });
    }

    @Test
    void WIDE_TO_TALL_TRANSPOSE_PAIR_2() {
        exercise(new double[][] {
            {1.0, 0.0, 1.0, 0.0},
            {0.0, 1.0, 1.0, 0.0}
        });
    }

    @Test
    void TALL_TO_WIDE_TRANSPOSE_PAIR_1() {
        exercise(new double[][] {
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0}
        });
    }

    @Test
    void TALL_TO_WIDE_TRANSPOSE_PAIR_2() {
        exercise(new double[][] {
            {2.0, 1.0},
            {-1.0, 3.0},
            {1.0, 4.0},
            {0.0, 0.0}
        });
    }

    @Test
    void EPSILON_MIXED_COLUMN_1() {
        exercise(new double[][] {
            {5.0e-11, 2.0},
            {1.0e-10, 5.0e-11}
        });
    }

    @Test
    void EPSILON_MIXED_COLUMN_2() {
        exercise(new double[][] {
            {-5.0e-11, 3.0},
            {-1.0e-10, -5.0e-11}
        });
    }
}
