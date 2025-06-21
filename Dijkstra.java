
import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        int V = 7;
        int[][] edges = {{0, 1, 3}, {1, 0, 3}, {0, 3, 7}, {3, 0, 7}, {1, 2, 1}, {2, 1, 1}, {2, 5, 4}, {1, 3, 2}, {3, 1, 2}, {3, 4, 8}, {4, 3, 8}, {4, 5, 1}, {4, 6, 3}, {5, 2, 4}, {5, 4, 1}, {6, 4, 3}};

        int[] distances = dijkstra(edges, 0, V);
        for (int d : distances) {
            System.out.print(d + " ");
        }
    }

    static ArrayList<ArrayList<ArrayList<Integer>>> constructAdj(int[][] edges, int V) {

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            // Add edge from u -> v
            ArrayList<Integer> e1 = new ArrayList<>();
            e1.add(v);
            e1.add(wt);
            adj.get(u).add(e1);

            // Since graph is undirected add same edge from v -> u
            ArrayList<Integer> e2 = new ArrayList<>();
            e2.add(u);
            e2.add(wt);
            adj.get(v).add(e2);

        }

        return adj;
    }

    public static int[] dijkstra(int[][] edges, int src, int V) {

        ArrayList<ArrayList<ArrayList<Integer>>> adj = constructAdj(edges, V);
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(0)));

        // Initialize all distances to infinity
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Source:
        dist[src] = 0;
        ArrayList<Integer> start = new ArrayList<>();
        start.add(0);
        start.add(src);
        pq.offer(start);

        while (!pq.isEmpty()) {

            ArrayList<Integer> curr = pq.poll();
            int d = curr.get(0);
            int u = curr.get(1);

            for (ArrayList<Integer> neighbour : adj.get(u)) {
                int v = neighbour.get(0);
                int wt = neighbour.get(1);

                // If there exists a shorter path to v through u
                if (dist[v] > dist[u] + wt) {

                    dist[v] = dist[u] + wt;

                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(dist[v]);
                    temp.add(v);
                    pq.offer(temp);
                }

            }

        }

        return dist;

    }
}
