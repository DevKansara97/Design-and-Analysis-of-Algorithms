
import java.util.*;

public class Graph {

    static class Edge {

        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }

    }
    
    public static void createGraph(ArrayList<Edge>[] graph) {

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 2));

    }

    public static List<Integer> neighbours(ArrayList<Edge>[] graph, int V) {

        List<Integer> ls = new ArrayList<>();

        for (Edge e : graph[V]) {
            ls.add(e.dest);
        }

        return ls;
    }

    public static List<Integer> BFS(ArrayList<Edge>[] graph, int s) {

        List<Integer> bfs = new ArrayList<>();

        boolean[] vis = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            vis[i] = false;
        }

        vis[s] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);

        while (!q.isEmpty()) {

            int u = q.poll();
            for (Edge e : graph[u]) {

                int v = e.dest;
                if (!vis[v]) {
                    vis[v] = true;
                    q.offer(v);
                }
            }

            bfs.add(u);
        }

        return bfs;
    }

    public static void main(String[] args) {

        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);
        System.out.println(neighbours(graph, 0));

        System.out.println(BFS(graph, 0));
    }
}
