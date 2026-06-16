public final class MatrixRankRowAdditionMetamorphicSpec {
    private static final double ROW_MULTIPLIER = 2.0;

    private MatrixRankRowAdditionMetamorphicSpec() {
    }

    public static double[][] generateFollowUp(double[][] source) {
        double[][] followUp = copyOf(source);
        if (followUp.length < 2) {
            throw new IllegalArgumentException("Row-addition MR requires at least two rows.");
        }

        for (int col = 0; col < followUp[0].length; col++) {
            followUp[1][col] = followUp[1][col] + ROW_MULTIPLIER * followUp[0][col];
        }
        return followUp;
    }

    public static void assertRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Expected rank to stay the same after adding a multiple of one row to another, but source rank was "
                            + sourceOutput + " and follow-up rank was " + followUpOutput);
        }
    }

    private static double[][] copyOf(double[][] matrix) {
        double[][] copy = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            System.arraycopy(matrix[row], 0, copy[row], 0, matrix[row].length);
        }
        return copy;
    }
}
