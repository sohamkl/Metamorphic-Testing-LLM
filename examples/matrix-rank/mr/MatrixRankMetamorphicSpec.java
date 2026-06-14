public final class MatrixRankMetamorphicSpec {
    private MatrixRankMetamorphicSpec() {
    }

    public static double[][] generateFollowUp(double[][] source) {
        int rows = source.length;
        int cols = source[0].length;
        double[][] transposed = new double[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                transposed[c][r] = source[r][c];
            }
        }

        return transposed;
    }

    public static void assertRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Expected rank to stay the same after transpose, but source rank was "
                            + sourceOutput + " and follow-up rank was " + followUpOutput);
        }
    }
}
