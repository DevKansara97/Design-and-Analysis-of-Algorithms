
public class BellmanFord extends Dijkstra {

    public static void main(String[] args) {
        int V = 7;
        int[][] edges = {{0, 1, 3}, {1, 0, 3}, {0, 3, 7}, {3, 0, 7}, {1, 2, 1}, {2, 1, 1}, {2, 5, 4}, {1, 3, 2}, {3, 1, 2}, {3, 4, 8}, {4, 3, 8}, {4, 5, 1}, {4, 6, 3}, {5, 2, 4}, {5, 4, 1}, {6, 4, 3}};

        int[] distances = bellmanFord(edges, 0, V);
        for (int d : distances) {
            System.out.print(d + " ");
        }
    }

    static int[] bellmanFord(int[][] edges, int src, int V) {

        // ArrayList<ArrayList<ArrayList<Integer>>> adj = constructAdj(edges, src);
        // Initialization:
        int[] dist = new int[V];
        for (int v = 0; v < V; v++) {
            dist[v] = Integer.MAX_VALUE;
        }

        dist[src] = 0;

        // Relax all edges |V|-1 times:
        for (int i = 1; i <= V - 1; i++) {
            for (int[] edge : edges) {
                Relax(edge, dist);
            }
        }

        // Negative Cycle detection:
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            if (dist[v] < dist[u] + wt) {
                System.out.println("Graph contains negative cycle");
            }
        }

        return dist;
    }

    static void Relax(int[] edge, int[] dist) {
        int u = edge[0], v = edge[1], wt = edge[2];
        if (dist[v] < dist[u] + wt) {
            dist[v] = dist[u] + wt;
        }
    }
}
