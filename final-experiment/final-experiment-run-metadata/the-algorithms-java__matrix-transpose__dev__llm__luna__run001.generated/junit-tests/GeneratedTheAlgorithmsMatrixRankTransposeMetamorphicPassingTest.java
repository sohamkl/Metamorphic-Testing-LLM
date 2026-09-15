import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private static void verify(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp =
                thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_ZERO_variation1() {
        verify(new double[][] {{0.0}});
    }

    @Test
    void SINGLE_NONZERO_variation1() {
        verify(new double[][] {{1.0, -0.0, 2.5, 0.0}});
    }

    @Test
    void SINGLE_NEGATIVE_PIVOT_variation1() {
        verify(new double[][] {{0.0}, {-7.5}, {-15.0}});
    }

    @Test
    void ALL_ZERO_RECTANGULAR_variation1() {
        verify(new double[][] {
                {0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0}
        });
    }

    @Test
    void ALL_ZERO_RECTANGULAR_variation2() {
        verify(new double[][] {
                {0.0, -0.0},
                {0.0, 0.0},
                {-0.0, 0.0},
                {0.0, 0.0}
        });
    }

    @Test
    void SINGLE_ROW_FULL_ROW_RANK_variation1() {
        verify(new double[][] {{2.0, -1.0, 0.5, 9.0}});
    }

    @Test
    void SINGLE_ROW_FULL_ROW_RANK_variation2() {
        verify(new double[][] {{Double.POSITIVE_INFINITY}});
    }

    @Test
    void SINGLE_COLUMN_FULL_COLUMN_RANK_variation1() {
        verify(new double[][] {
                {2.0},
                {-1.0},
                {3.0},
                {4.0}
        });
    }

    @Test
    void SINGLE_COLUMN_FULL_COLUMN_RANK_variation2() {
        verify(new double[][] {
                {-0.0},
                {0.0},
                {-0.0},
                {0.0},
                {-0.0}
        });
    }

    @Test
    void SQUARE_IDENTITY_variation1() {
        verify(new double[][] {
                {1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0}
        });
    }

    @Test
    void SQUARE_IDENTITY_variation2() {
        verify(new double[][] {
                {-2.0, 0.0},
                {0.0, -3.0}
        });
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation1() {
        verify(new double[][] {
                {1.0, 0.0},
                {0.0, 1.0},
                {2.0, 3.0},
                {4.0, -2.0}
        });
    }

    @Test
    void TALL_FULL_COLUMN_RANK_variation2() {
        verify(new double[][] {
                {3.0e150, 0.0, 0.0},
                {0.0, -4.0e150, 0.0},
                {1.0e150, 2.0e150, 5.0e150},
                {-2.0e150, 3.0e150, 1.0e150},
                {4.0e150, -1.0e150, 2.0e150}
        });
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation1() {
        verify(new double[][] {
                {1.0, 0.0, 2.0, 3.0},
                {0.0, 1.0, -1.0, 4.0}
        });
    }

    @Test
    void WIDE_FULL_ROW_RANK_variation2() {
        verify(new double[][] {
                {2.0, -1.0, 0.0, 4.0, 7.0},
                {-3.0, 5.0, 1.0, 0.0, -2.0},
                {1.0, 2.0, 3.0, -1.0, 6.0}
        });
    }

    @Test
    void PROPORTIONAL_ROWS_variation1() {
        verify(new double[][] {
                {1.0, 2.0, 3.0},
                {2.0, 4.0, 6.0},
                {-3.0, -6.0, -9.0}
        });
    }

    @Test
    void PROPORTIONAL_ROWS_variation2() {
        verify(new double[][] {
                {1.0, 0.0, 2.0},
                {2.0, 0.0, 4.0},
                {-4.0, 0.0, -8.0},
                {3.0, 0.0, 6.0}
        });
    }

    @Test
    void PROPORTIONAL_COLUMNS_variation1() {
        verify(new double[][] {
                {1.0, 2.0, 0.0},
                {3.0, 6.0, 0.0},
                {4.0, 8.0, 0.0}
        });
    }

    @Test
    void PROPORTIONAL_COLUMNS_variation2() {
        verify(new double[][] {
                {2.0, 4.0, 6.0, 0.0},
                {1.0, 2.0, 3.0, 0.0}
        });
    }

    @Test
    void ZERO_PREFIX_PIVOT_SEARCH_variation1() {
        verify(new double[][] {
                {0.0, 1.0, 2.0},
                {3.0, 4.0, 5.0}
        });
    }

    @Test
    void ZERO_PREFIX_PIVOT_SEARCH_variation2() {
        verify(new double[][] {
                {0.0},
                {Double.POSITIVE_INFINITY},
                {2.0},
                {-3.0}
        });
    }

    @Test
    void ZERO_COLUMN_BETWEEN_PIVOTS_variation1() {
        verify(new double[][] {
                {1.0, 0.0, 2.0},
                {0.0, 0.0, 1.0}
        });
    }

    @Test
    void ZERO_COLUMN_BETWEEN_PIVOTS_variation2() {
        verify(new double[][] {
                {0.0, 1.0, 0.0},
                {2.0, 0.0, 0.0},
                {3.0, 4.0, 0.0},
                {-1.0, 2.0, 0.0}
        });
    }

    @Test
    void PIVOT_AFTER_MARKED_ROWS_variation1() {
        verify(new double[][] {
                {1.0, 0.0, 1.0},
                {2.0, 0.0, 2.0},
                {0.0, 1.0, 1.0}
        });
    }

    @Test
    void PIVOT_AFTER_MARKED_ROWS_variation2() {
        verify(new double[][] {
                {2.0, 0.0, 4.0, 1.0},
                {4.0, 0.0, 8.0, 2.0},
                {0.0, -3.0, 1.0, 5.0}
        });
    }

    @Test
    void NEGATIVE_AND_NONUNIT_NORMALIZATION_variation1() {
        verify(new double[][] {
                {-2.0, 4.0},
                {3.0, -5.0}
        });
    }

    @Test
    void NEGATIVE_AND_NONUNIT_NORMALIZATION_variation2() {
        verify(new double[][] {
                {-1.0e150},
                {3.0e150},
                {-5.0e150},
                {7.0e150}
        });
    }

    @Test
    void EPSILON_BELOW_PIVOT_variation1() {
        verify(new double[][] {{1.0e-11, 0.0}});
    }

    @Test
    void EPSILON_BELOW_PIVOT_variation2() {
        verify(new double[][] {
                {0.0, 0.0},
                {0.0, 0.0},
                {0.0, 0.0}
        });
    }

    @Test
    void EPSILON_EXACT_PIVOT_variation1() {
        verify(new double[][] {
                {1.0e-10, 0.0, 0.0},
                {0.0, 0.0, 0.0}
        });
    }

    @Test
    void EPSILON_EXACT_PIVOT_variation2() {
        verify(new double[][] {{1.0e-10, -1.0e-10}});
    }

    @Test
    void EPSILON_ABOVE_PIVOT_variation1() {
        verify(new double[][] {{1.0000000001e-10, 0.0, 2.0}});
    }

    @Test
    void EPSILON_ABOVE_PIVOT_variation2() {
        verify(new double[][] {
                {0.0},
                {1.0000000001e-10},
                {-2.0e-10}
        });
    }

    @Test
    void NEAR_DEPENDENCE_BELOW_EPSILON_AFTER_ELIMINATION_variation1() {
        verify(new double[][] {
                {1.0, 1.0},
                {1.0, 1.0 + 1.0e-11}
        });
    }

    @Test
    void NEAR_DEPENDENCE_BELOW_EPSILON_AFTER_ELIMINATION_variation2() {
        verify(new double[][] {
                {1.0, 0.0, 2.0},
                {0.0, 1.0, 3.0},
                {1.0, 1.0, 5.0}
        });
    }

    @Test
    void NEAR_DEPENDENCE_ABOVE_EPSILON_AFTER_ELIMINATION_variation1() {
        verify(new double[][] {
                {1.0, 1.0, 0.0},
                {1.0, 1.0 + 1.0e-9, 2.0}
        });
    }

    @Test
    void NEAR_DEPENDENCE_ABOVE_EPSILON_AFTER_ELIMINATION_variation2() {
        verify(new double[][] {{-0.0, 0.0, 0.0}});
    }

    @Test
    void SIGNED_ZERO_VALUES_variation1() {
        verify(new double[][] {
                {-0.0, 0.0},
                {-0.0, 0.0}
        });
    }

    @Test
    void LARGE_FINITE_VALUES_variation1() {
        verify(new double[][] {
                {1.0e150, 2.0e150},
                {3.0e150, 7.0e150}
        });
    }

    @Test
    void SMALL_FINITE_VALUES_ABOVE_EPSILON_variation1() {
        verify(new double[][] {
                {2.0e-10, 0.0},
                {0.0, 3.0e-10}
        });
    }

    @Test
    void NAN_ENTRY_variation1() {
        verify(new double[][] {
                {Double.NaN, 0.0},
                {1.0, 2.0},
                {3.0, 4.0}
        });
    }

    @Test
    void INFINITY_ENTRY_variation1() {
        verify(new double[][] {
                {Double.POSITIVE_INFINITY, 1.0, 2.0},
                {0.0, 1.0, 3.0}
        });
    }

    @Test
    void TRANSPOSE_SHAPE_SWAP_variation1() {
        verify(new double[][] {
                {1.0, 2.0, 0.0, 4.0, 5.0},
                {0.0, 1.0, 3.0, 0.0, 2.0}
        });
    }

    @Test
    void TRANSPOSE_SHAPE_SWAP_variation2() {
        verify(new double[][] {{-0.0, 0.0, 1.0, -2.0, 3.0, 4.0}});
    }
}
