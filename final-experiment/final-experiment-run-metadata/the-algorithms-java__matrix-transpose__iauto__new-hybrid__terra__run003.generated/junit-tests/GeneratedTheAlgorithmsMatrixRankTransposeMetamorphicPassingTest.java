import org.junit.jupiter.api.Test;

public class GeneratedTheAlgorithmsMatrixRankTransposeMetamorphicPassingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Rank must remain unchanged after transposition: source="
                            + sourceOutput
                            + ", follow-up="
                            + followUpOutput);
        }
    }

    @Test
    void allZeroRectangular_variation1() {
        double[][] source = {{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void scalarZero_variation1() {
        double[][] source = {{0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void scalarNonzero_variation1() {
        double[][] source = {{-7.5}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void singleRowWideNonzero_variation1() {
        double[][] source = {{0.0, 5.0, -2.0, 9.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void singleColumnTallMixed_variation1() {
        double[][] source = {{0.0}, {0.0}, {-3.0}, {8.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void leadingZeroColumn_variation1() {
        double[][] source = {{0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}, {0.0, 0.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void interiorZeroColumnWithLaterPivot_variation1() {
        double[][] source = {{1.0, 0.0, 0.0}, {0.0, 0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void firstPivotBelowTopRow_variation1() {
        double[][] source = {{0.0, 1.0}, {2.0, 3.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void nonMonotonicPivotRowOrder_variation1() {
        double[][] source = {{0.0, 1.0, 0.0}, {1.0, 0.0, 1.0}, {0.0, 0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void eliminationWithNonzeroOtherRows_variation1() {
        double[][] source = {{2.0, 4.0}, {3.0, 7.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void rankOneProportionalRows_variation1() {
        double[][] source = {{1.0, 2.0, 3.0}, {2.0, 4.0, 6.0}, {3.0, 6.0, 9.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void tallFullColumnRank_variation1() {
        double[][] source = {{1.0, 0.0}, {0.0, 1.0}, {1.0, 1.0}, {2.0, -1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void wideFullRowRank_variation1() {
        double[][] source = {{1.0, 0.0, 2.0, 3.0}, {0.0, 1.0, -1.0, 4.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void upperTriangularFullRank_variation1() {
        double[][] source = {{1.0, 2.0, 3.0}, {0.0, 1.0, 4.0}, {0.0, 0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void belowEpsilonScalar_variation1() {
        double[][] source = {{9.0e-11}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void positiveEpsilonScalar_variation1() {
        double[][] source = {{1.0e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void negativeEpsilonScalar_variation1() {
        double[][] source = {{-1.0e-10}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void eliminationResidualBelowEpsilon_variation1() {
        double[][] source = {{1.0, 1.0}, {1.0, 1.0000000000582077}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void eliminationResidualAboveEpsilon_variation1() {
        double[][] source = {{1.0, 1.0}, {1.0, 1.0000000001164153}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void subEpsilonLeadingColumnWithNormalLaterColumn_variation1() {
        double[][] source = {{1.0e-11, 0.0}, {0.0, 1.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void veryLargeFiniteDependentRows_variation1() {
        double[][] source = {{1.0e308, 1.0e308}, {1.0e308, 1.0e308}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void negativeZeroEntries_variation1() {
        double[][] source = {{-0.0, 0.0}, {0.0, -0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void nanScalar_variation1() {
        double[][] source = {{Double.NaN}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void positiveInfinityScalar_variation1() {
        double[][] source = {{Double.POSITIVE_INFINITY}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void nanEliminationPath_variation1() {
        double[][] source = {{Double.NaN, 1.0}, {1.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void infinityNormalizationAndElimination_variation1() {
        double[][] source = {{Double.POSITIVE_INFINITY, 1.0}, {1.0, 0.0}};
        int sourceOutput = com.thealgorithms.matrix.MatrixRank.computeRank(source);
        double[][] followUp = thealgorithmsmt.MatrixRankMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = com.thealgorithms.matrix.MatrixRank.computeRank(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
