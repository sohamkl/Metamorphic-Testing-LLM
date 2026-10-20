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

    private static void checkTransposeRelation(double[][] source) {
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        thealgorithmsmt.MatrixRankMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void singleNonzeroEntryVariation1() {
        double[][] source = {{0.0}};
        checkTransposeRelation(source);
    }

    @Test
    void singleNonzeroEntryVariation2() {
        double[][] source = {{2.0, 4.0, 6.0}};
        checkTransposeRelation(source);
    }

    @Test
    void singleZeroEntryVariation1() {
        double[][] source = {{-3.0}, {0.0}, {0.0}};
        checkTransposeRelation(source);
    }

    @Test
    void allZeroRectangularVariation1() {
        double[][] source = {{0.0, 0.0}, {0.0, 0.0}};
        checkTransposeRelation(source);
    }

    @Test
    void allZeroRectangularVariation2() {
        double[][] source = {{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
        checkTransposeRelation(source);
    }

    @Test
    void squareIdentityVariation1() {
        double[][] source = {
            {1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void tallFullRowRankVariation1() {
        double[][] source = {{1.0}};
        checkTransposeRelation(source);
    }

    @Test
    void tallFullRowRankVariation2() {
        double[][] source = {{1e-12, 2e-12, 3e-12}};
        checkTransposeRelation(source);
    }

    @Test
    void wideFullColumnRankVariation1() {
        double[][] source = {{Double.NaN}, {2.0}};
        checkTransposeRelation(source);
    }

    @Test
    void wideFullColumnRankVariation2() {
        double[][] source = {
            {Double.POSITIVE_INFINITY, 0.0},
            {0.0, 1.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void dependentRowsVariation1() {
        double[][] source = {
            {Double.NEGATIVE_INFINITY, 1.0},
            {Double.NEGATIVE_INFINITY, 1.0},
            {0.0, 0.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void dependentRowsVariation2() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void dependentColumnsVariation1() {
        double[][] source = {{5.0}};
        checkTransposeRelation(source);
    }

    @Test
    void dependentColumnsVariation2() {
        double[][] source = {{-2.0, -4.0, -6.0}};
        checkTransposeRelation(source);
    }

    @Test
    void initialZeroColumnsVariation1() {
        double[][] source = {{0.0}, {3.0}, {0.0}};
        checkTransposeRelation(source);
    }

    @Test
    void initialZeroColumnsVariation2() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {0.0, 1e-12, 2.0},
            {0.0, 0.0, 0.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void latePivotRowVariation1() {
        double[][] source = {
            {0.0, 0.0},
            {0.0, 1.0},
            {1e-10, 2.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void latePivotRowVariation2() {
        double[][] source = {
            {0.0, 0.0, 1e300},
            {0.0, 2.0, 3.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void markedRowSkipVariation1() {
        double[][] source = {{1e-12}};
        checkTransposeRelation(source);
    }

    @Test
    void markedRowSkipVariation2() {
        double[][] source = {{Double.NaN, 1.0, 2.0}};
        checkTransposeRelation(source);
    }

    @Test
    void activeEliminationVariation1() {
        double[][] source = {{Double.POSITIVE_INFINITY}};
        checkTransposeRelation(source);
    }

    @Test
    void activeEliminationVariation2() {
        double[][] source = {
            {Double.NEGATIVE_INFINITY, 1.0},
            {1.0, 0.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void zeroEliminationTargetsVariation1() {
        double[][] source = {
            {1.0, 2.0},
            {0.0, 0.0},
            {0.0, 3.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void zeroEliminationTargetsVariation2() {
        double[][] source = {
            {2.0, 1.0, 0.0},
            {0.0, 3.0, 1.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void nonunitPivotNormalizationVariation1() {
        double[][] source = {{-4.0}};
        checkTransposeRelation(source);
    }

    @Test
    void nonunitPivotNormalizationVariation2() {
        double[][] source = {{-2.0, 4.0, -6.0}};
        checkTransposeRelation(source);
    }

    @Test
    void epsilonBelowThresholdVariation1() {
        double[][] source = {{0.5e-10}, {0.0}};
        checkTransposeRelation(source);
    }

    @Test
    void epsilonBelowThresholdVariation2() {
        double[][] source = {
            {0.5e-10, 1.0},
            {0.0, 2.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void epsilonExactThresholdVariation1() {
        double[][] source = {
            {1e-10, 0.0},
            {0.0, 1.0},
            {0.0, 0.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void epsilonExactThresholdVariation2() {
        double[][] source = {
            {-1e-10, 1e-12, 0.0},
            {0.0, 1e-12, 1e-12}
        };
        checkTransposeRelation(source);
    }

    @Test
    void negativePivotsVariation1() {
        double[][] source = {{Double.NaN}};
        checkTransposeRelation(source);
    }

    @Test
    void negativePivotsVariation2() {
        double[][] source = {{Double.POSITIVE_INFINITY, -2.0, 3.0}};
        checkTransposeRelation(source);
    }

    @Test
    void zeroRowBetweenIndependentRowsVariation1() {
        double[][] source = {
            {Double.NEGATIVE_INFINITY},
            {0.0},
            {2.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void zeroRowBetweenIndependentRowsVariation2() {
        double[][] source = {
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void largeFiniteValuesVariation1() {
        double[][] source = {
            {1e300, 2e299},
            {3e299, 4e299},
            {5e299, 6e299}
        };
        checkTransposeRelation(source);
    }

    @Test
    void largeFiniteValuesVariation2() {
        double[][] source = {
            {-1e300, -2e300, 3e299},
            {-4e299, -5e299, 6e299}
        };
        checkTransposeRelation(source);
    }

    @Test
    void smallFiniteValuesVariation1() {
        double[][] source = {{-5e-11}};
        checkTransposeRelation(source);
    }

    @Test
    void smallFiniteValuesVariation2() {
        double[][] source = {{5e-11, 1e-12, 2e-12}};
        checkTransposeRelation(source);
    }

    @Test
    void nanEntryVariation1() {
        double[][] source = {
            {1e-10},
            {Double.NaN},
            {0.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void nanEntryVariation2() {
        double[][] source = {
            {1e300, Double.NaN, 1.0},
            {0.0, 2.0, 3.0},
            {4.0, 0.0, 5.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void infiniteEntryVariation1() {
        double[][] source = {
            {1e-11, 0.0},
            {0.0, Double.POSITIVE_INFINITY},
            {1e-10, 2.0}
        };
        checkTransposeRelation(source);
    }

    @Test
    void infiniteEntryVariation2() {
        double[][] source = {
            {Double.NaN, 1.0, 0.0},
            {0.0, Double.NEGATIVE_INFINITY, 2.0}
        };
        checkTransposeRelation(source);
    }
}
