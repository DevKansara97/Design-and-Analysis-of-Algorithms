
import java.util.*;

// Time Complexity: O(ElogV)
// Space Complexity: O(E + V)
public class PrimsAlgorithm {

    static int MST_weight;

    static class State {

        int wt;
        int node;
        int parent;

        State(int wt, int node, int parent) {
            this.wt = wt;
            this.node = node;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {

        int[][][] graph = {{{1, 2}, {2, 1}}, {{0, 2}, {2, 1}}, {{0, 1}, {1, 1}, {3, 2}, {4, 2}}, {{2, 2}, {4, 1}}, {{2, 2}, {3, 1}}};

        int V = graph.length;
        List<int[]> MST = primsAlgorithm(graph, V);

        for (int[] edge : MST) {
            System.out.print("[" + edge[0] + ", " + edge[1] + "] ");
        }

        System.out.println("\nWeight of Minimum Spanning Tree: " + MST_weight);

    }

    public static List<int[]> primsAlgorithm(int[][][] graph, int V) {

        boolean[] vis = new boolean[V];
        Arrays.fill(vis, false);
        List<int[]> MST = new ArrayList<>();

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.wt, b.wt));
        pq.add(new State(0, 0, -1));

        while (!pq.isEmpty()) {

            State st = pq.remove();
            int u = st.node;
            if (vis[u]) {
                continue;
            }
            vis[u] = true;

            for (int[] edge : graph[u]) {
                int v = edge[0], wt = edge[1];
                if (!vis[v]) {
                    pq.add(new State(wt, v, u));
                }
            }

            if (st.parent != -1) {
                MST.add(new int[]{st.parent, u});
                MST_weight += st.wt;
            }

        }

        return MST;
    }
}
