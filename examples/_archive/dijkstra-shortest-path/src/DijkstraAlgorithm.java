import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Computes shortest-path distances from a source vertex in a weighted graph.
 *
 * <p>The graph is represented as an adjacency matrix. A value of {@code 0} means there is
 * no edge, except on the diagonal. All real edge weights must be positive.</p>
 */
public final class DijkstraAlgorithm {
    private static final int INF = Integer.MAX_VALUE / 4;

    private DijkstraAlgorithm() {
    }

    /**
     * Returns shortest distances for a wrapped graph/source input.
     *
     * @param input graph and source vertex
     * @return shortest distances from the input source vertex
     */
    public static int[] shortestDistances(DijkstraInput input) {
        return shortestDistances(input.graph(), input.sourceVertex());
    }

    /**
     * Returns the shortest distance from {@code sourceVertex} to every vertex.
     *
     * @param graph adjacency matrix with positive edge weights and {@code 0} for no edge
     * @param sourceVertex index of the source vertex
     * @return shortest distances, using a large sentinel value for unreachable vertices
     */
    public static int[] shortestDistances(int[][] graph, int sourceVertex) {
        validateGraph(graph, sourceVertex);

        int vertexCount = graph.length;
        int[] distances = new int[vertexCount];
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(distances, INF);
        distances[sourceVertex] = 0;

        PriorityQueue<NodeDistance> queue = new PriorityQueue<>();
        queue.add(new NodeDistance(sourceVertex, 0));

        while (!queue.isEmpty()) {
            NodeDistance current = queue.poll();
            if (visited[current.vertex]) {
                continue;
            }
            visited[current.vertex] = true;

            for (int neighbor = 0; neighbor < vertexCount; neighbor++) {
                int edgeWeight = graph[current.vertex][neighbor];
                if (edgeWeight <= 0 || visited[neighbor]) {
                    continue;
                }
                int candidateDistance = distances[current.vertex] + edgeWeight;
                if (candidateDistance < distances[neighbor]) {
                    distances[neighbor] = candidateDistance;
                    queue.add(new NodeDistance(neighbor, candidateDistance));
                }
            }
        }

        return distances;
    }

    public static int unreachableDistance() {
        return INF;
    }

    private static void validateGraph(int[][] graph, int sourceVertex) {
        if (graph == null || graph.length == 0) {
            throw new IllegalArgumentException("Graph must be non-empty.");
        }
        if (sourceVertex < 0 || sourceVertex >= graph.length) {
            throw new IllegalArgumentException("Source vertex is outside the graph.");
        }
        for (int row = 0; row < graph.length; row++) {
            if (graph[row] == null || graph[row].length != graph.length) {
                throw new IllegalArgumentException("Graph must be a square adjacency matrix.");
            }
            for (int col = 0; col < graph[row].length; col++) {
                if (graph[row][col] < 0) {
                    throw new IllegalArgumentException("Dijkstra requires non-negative edge weights.");
                }
            }
        }
    }

    private static final class NodeDistance implements Comparable<NodeDistance> {
        private final int vertex;
        private final int distance;

        private NodeDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance other) {
            return Integer.compare(distance, other.distance);
        }
    }
}

/**
 * Source input for the Dijkstra example.
 *
 * <p>Keeping graph and source vertex together makes it easier for generated tests to pass one
 * source input through the SUT and MR transformation.</p>
 */
final class DijkstraInput {
    private final int[][] graph;
    private final int sourceVertex;

    DijkstraInput(int[][] graph, int sourceVertex) {
        this.graph = copy(graph);
        this.sourceVertex = sourceVertex;
    }

    int[][] graph() {
        return copy(graph);
    }

    int sourceVertex() {
        return sourceVertex;
    }

    private static int[][] copy(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        int[][] copy = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            copy[row] = matrix[row] == null ? null : matrix[row].clone();
        }
        return copy;
    }
}
