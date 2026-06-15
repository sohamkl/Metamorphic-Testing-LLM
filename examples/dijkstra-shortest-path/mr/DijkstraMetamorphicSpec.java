/**
 * Developer-owned metamorphic relation for Dijkstra shortest-path distances.
 *
 * <p>The follow-up graph multiplies every edge weight by the same positive factor. Shortest
 * distances should scale by that factor, while unreachable vertices should remain unreachable.</p>
 */
public final class DijkstraMetamorphicSpec {
    private static final int SCALE_FACTOR = 3;

    private DijkstraMetamorphicSpec() {
    }

    public static DijkstraInput generateFollowUp(DijkstraInput source) {
        int[][] sourceGraph = source.graph();
        int[][] followUpGraph = new int[sourceGraph.length][sourceGraph.length];
        for (int row = 0; row < sourceGraph.length; row++) {
            for (int col = 0; col < sourceGraph[row].length; col++) {
                followUpGraph[row][col] = sourceGraph[row][col] == 0 ? 0 : sourceGraph[row][col] * SCALE_FACTOR;
            }
        }
        return new DijkstraInput(followUpGraph, source.sourceVertex());
    }

    public static void assertRelation(int[] sourceOutput, int[] followUpOutput) {
        if (sourceOutput.length != followUpOutput.length) {
            throw new AssertionError("Source and follow-up outputs have different lengths.");
        }

        int unreachable = DijkstraAlgorithm.unreachableDistance();
        for (int vertex = 0; vertex < sourceOutput.length; vertex++) {
            if (sourceOutput[vertex] >= unreachable) {
                if (followUpOutput[vertex] < unreachable) {
                    throw new AssertionError("Vertex " + vertex + " became reachable after scaling edge weights.");
                }
            } else {
                int expected = sourceOutput[vertex] * SCALE_FACTOR;
                if (followUpOutput[vertex] != expected) {
                    throw new AssertionError("Expected distance to vertex " + vertex + " to scale from "
                            + sourceOutput[vertex] + " to " + expected + ", but was " + followUpOutput[vertex]);
                }
            }
        }
    }
}
