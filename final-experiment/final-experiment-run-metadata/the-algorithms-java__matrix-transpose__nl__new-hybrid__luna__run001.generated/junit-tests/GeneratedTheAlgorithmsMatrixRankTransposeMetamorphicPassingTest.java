import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.matrix.MatrixRank;
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

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(double[][] source) {
        int sourceOutput = MatrixRank.computeRank(source);
        int followUpOutput = MatrixRank.computeRank(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void singleZero_1() {
        assertMetamorphicRelationFor(new double[][] {{0.0}});
    }

    @Test
    void singleNonzero_1() {
        assertMetamorphicRelationFor(new double[][] {{-3.5}});
    }

    @Test
    void allZeroRectangle_1() {
        assertMetamorphicRelationFor(new double[][] {{0.0}, {0.0}, {0.0}});
    }

    @Test
    void allZeroRectangle_2() {
        assertMetamorphicRelationFor(new double[][] {{0.0, 0.0}, {0.0, 0.0}});
    }

    @Test
    void singleNonzeroRow_1() {
        assertMetamorphicRelationFor(new double[][] {{0.0, 0.0, 2.5, -1.0}});
    }

    @Test
    void singleNonzeroRow_2() {
        assertMetamorphicRelationFor(new double[][] {{4.0, 0.0, 0.0, 0.0, 0.0}});
    }

    @Test
    void singleNonzeroColumn_1() {
        assertMetamorphicRelationFor(new double[][] {{0.0}, {0.0}, {-7.0}, {0.0}});
    }

    @Test
    void singleNonzeroColumn_2() {
        assertMetamorphicRelationFor(new double[][] {{5.0}, {0.0}, {0.0}, {0.0}, {0.0}});
    }

    @Test
    void identitySquare_1() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 0.0}, {0.0, 1.0}});
    }

    @Test
    void identitySquare_2() {
        assertMetamorphicRelationFor(
                new double[][] {{1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}});
    }

    @Test
    void identitySquare_3() {
        assertMetamorphicRelationFor(
                new double[][] {
                    {1.0, 0.0, 0.0, 0.0},
                    {0.0, 1.0, 0.0, 0.0},
                    {0.0, 0.0, 1.0, 0.0},
                    {0.0, 0.0, 0.0, 1.0}
                });
    }

    @Test
    void identitySquare_4() {
        assertMetamorphicRelationFor(
                new double[][] {
                    {1.0, 0.0, 0.0, 0.0, 0.0},
                    {0.0, 1.0, 0.0, 0.0, 0.0},
                    {0.0, 0.0, 1.0, 0.0, 0.0},
                    {0.0, 0.0, 0.0, 1.0, 0.0},
                    {0.0, 0.0, 0.0, 0.0, 1.0}
                });
    }

    @Test
    void fullRankTall_1() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 0.0}, {0.0, 1.0}, {2.0, 3.0}});
    }

    @Test
    void fullRankTall_2() {
        assertMetamorphicRelationFor(
                new double[][] {{1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}, {2.0, -1.0, 4.0}});
    }

    @Test
    void fullRankTall_3() {
        assertMetamorphicRelationFor(
                new double[][] {
                    {1.0, 0.0, 0.0, 0.0},
                    {0.0, 1.0, 0.0, 0.0},
                    {0.0, 0.0, 1.0, 0.0},
                    {0.0, 0.0, 0.0, 1.0},
                    {3.0, 2.0, 1.0, -4.0}
                });
    }

    @Test
    void fullRankWide_1() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 0.0, 2.0}, {0.0, 1.0, -1.0}});
    }

    @Test
    void fullRankWide_2() {
        assertMetamorphicRelationFor(
                new double[][] {{1.0, 0.0, 0.0, 2.0}, {0.0, 1.0, 0.0, 3.0}, {0.0, 0.0, 1.0, 4.0}});
    }

    @Test
    void fullRankWide_3() {
        assertMetamorphicRelationFor(
                new double[][] {
                    {1.0, 0.0, 0.0, 0.0, 1.0},
                    {0.0, 1.0, 0.0, 0.0, 2.0},
                    {0.0, 0.0, 1.0, 0.0, 3.0},
                    {0.0, 0.0, 0.0, 1.0, 4.0}
                });
    }

    @Test
    void proportionalRows_1() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 2.0}, {3.0, 6.0}});
    }

    @Test
    void proportionalRows_2() {
        assertMetamorphicRelationFor(new double[][] {{2.0, -1.0, 4.0}, {-4.0, 2.0, -8.0}});
    }

    @Test
    void proportionalRows_3() {
        assertMetamorphicRelationFor(
                new double[][] {{1.0, 2.0, 3.0, 4.0}, {-2.0, -4.0, -6.0, -8.0}});
    }

    @Test
    void proportionalColumns_1() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 2.0}, {0.0, 0.0}});
    }

    @Test
    void proportionalColumns_2() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 3.0}, {2.0, 6.0}, {-1.0, -3.0}});
    }

    @Test
    void proportionalColumns_3() {
        assertMetamorphicRelationFor(
                new double[][] {{2.0, -1.0}, {4.0, -2.0}, {0.5, -0.25}, {-3.0, 1.5}});
    }

    @Test
    void leadingZeroColumns_1() {
        assertMetamorphicRelationFor(new double[][] {{0.0, 2.0, 1.0}, {0.0, 0.0, 3.0}});
    }

    @Test
    void leadingZeroColumns_2() {
        assertMetamorphicRelationFor(
                new double[][] {{0.0, 0.0, 1.0, 2.0}, {0.0, 0.0, 0.0, 3.0}, {0.0, 4.0, 0.0, 1.0}});
    }

    @Test
    void pivotAfterMarkedRow_1() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 0.0}, {0.0, 2.0}});
    }

    @Test
    void pivotAfterMarkedRow_2() {
        assertMetamorphicRelationFor(
                new double[][] {{3.0, 0.0, 0.0}, {0.0, 2.0, 0.0}, {0.0, 0.0, 1.0}});
    }

    @Test
    void firstEligiblePivotNotLargest_1() {
        assertMetamorphicRelationFor(new double[][] {{0.1, 1.0}, {100.0, 2.0}});
    }

    @Test
    void firstEligiblePivotNotLargest_2() {
        assertMetamorphicRelationFor(
                new double[][] {{0.25, 1.0, 0.0}, {50.0, 0.0, 1.0}, {2.0, 3.0, 4.0}});
    }

    @Test
    void multirowElimination_1() {
        assertMetamorphicRelationFor(new double[][] {{2.0, 1.0, 3.0}, {4.0, 2.0, 6.0}, {-2.0, 5.0, 1.0}});
    }

    @Test
    void multirowElimination_2() {
        assertMetamorphicRelationFor(
                new double[][] {
                    {3.0, 2.0, 1.0, 4.0},
                    {6.0, 1.0, 5.0, 2.0},
                    {-3.0, 4.0, 2.0, 1.0},
                    {9.0, 0.0, 7.0, 3.0}
                });
    }

    @Test
    void exactCancellation_1() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 2.0, 3.0}, {2.0, 4.0, 6.0}, {0.0, 1.0, 1.0}});
    }

    @Test
    void exactCancellation_2() {
        assertMetamorphicRelationFor(new double[][] {{1.0, -1.0, 2.0}, {2.0, -2.0, 4.0}, {1.0, 0.0, 3.0}});
    }

    @Test
    void belowEpsilonEntries_1() {
        assertMetamorphicRelationFor(new double[][] {{5e-11, 1.0}, {0.0, 2.0}});
    }

    @Test
    void belowEpsilonEntries_2() {
        assertMetamorphicRelationFor(new double[][] {{1.0, 0.0, 0.0}, {0.0, 5e-11, 1.0}, {0.0, 0.0, 2.0}});
    }

    @Test
    void exactEpsilonEntries_1() {
        assertMetamorphicRelationFor(new double[][] {{1e-10, 1.0}, {0.0, 2.0}});
    }

    @Test
    void exactEpsilonEntries_2() {
        assertMetamorphicRelationFor(new double[][] {{-1e-10, 0.0}, {1.0, 2.0}});
    }

    @Test
    void aboveEpsilonEntries_1() {
        assertMetamorphicRelationFor(new double[][] {{1.00000000001e-10, 1.0}, {0.0, 2.0}});
    }

    @Test
    void aboveEpsilonEntries_2() {
        assertMetamorphicRelationFor(new double[][] {{0.0, 1.000000000005e-10}, {2.0, 1.0}});
    }

    @Test
    void negativeZeroEntries_1() {
        assertMetamorphicRelationFor(new double[][] {{-0.0, 1.0}, {2.0, 3.0}});
    }

    @Test
    void largeFiniteScale_1() {
        assertMetamorphicRelationFor(new double[][] {{1e100, 2e100}, {3e100, 4e100}});
    }

    @Test
    void largeFiniteScale_2() {
        assertMetamorphicRelationFor(new double[][] {{1e200, 0.0, 1.0}, {0.0, 1e200, 2.0}});
    }

    @Test
    void nanEntry_1() {
        assertMetamorphicRelationFor(new double[][] {{Double.NaN, 1.0}, {2.0, 3.0}});
    }

    @Test
    void nanEntry_2() {
        assertMetamorphicRelationFor(
                new double[][] {{1.0, 0.0, Double.NaN}, {0.0, 1.0, 2.0}});
    }

    @Test
    void infiniteEntry_1() {
        assertMetamorphicRelationFor(new double[][] {{Double.POSITIVE_INFINITY, 1.0}, {2.0, 3.0}});
    }

    @Test
    void infiniteEntry_2() {
        assertMetamorphicRelationFor(
                new double[][] {{1.0, Double.NEGATIVE_INFINITY, 0.0}, {0.0, 1.0, 2.0}});
    }
}
