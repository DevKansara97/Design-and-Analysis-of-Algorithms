
public class FloydWarshall {

    public static int[][] floydWarshall(int[][] edges) {

        int V = edges.length;
        int[][] distance = new int[V][V];
        for (int i = 0; i < V; i++) {
            System.arraycopy(edges[i], 0, distance[i], 0, V);
        }

        int INF = Integer.MAX_VALUE;

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (distance[i][k] != INF && distance[k][j] != INF) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }

        return distance;
    }

    public static void printGraph(int[][] edges) {
        int V = edges.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int INF = Integer.MAX_VALUE;
        // int[][] edges = {{0, 3, INF, INF}, {3, 0, 1, 4}, {INF, 1, 0, 1}, {INF, 4, 1, 0}};
        int[][] edges = {{0, 2, INF, INF, 8}, {2, 0, 3, INF, 2}, {INF, 3, 0, 1, INF}, {INF, INF, 1, 0, 1}, {8, 2, INF, 1, 0}};

        int[][] distance = floydWarshall(edges);

        printGraph(distance);
    }

}
