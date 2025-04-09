
import java.util.*;

public class DAA_Q5_BipartiteGraph {

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

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));
        graph[1].add(new Edge(1, 5));

        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 2));
        graph[3].add(new Edge(3, 4));

        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 1));
        graph[5].add(new Edge(5, 4));


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

    // public static List<Integer> BFS_withDistanceAndParent(ArrayList<Edge>[] graph, int s) {
    //     List<Integer> bfs = new ArrayList<>();
    //     Map<Integer, Integer> distance = new HashMap<>();
    //     Map<Integer, Integer> parent = new HashMap<>();
    //     boolean[] vis = new boolean[graph.length];
    //     for (int i = 0; i < graph.length; i++) {
    //         vis[i] = false;
    //         distance.put(i, Integer.MAX_VALUE);
    //         parent.put(i, -1);
    //     }
    //     vis[s] = true;
    //     distance.put(s, 0);
    //     Queue<Integer> q = new LinkedList<>();
    //     q.offer(s);
    //     while (!q.isEmpty()) {
    //         int u = q.poll();
    //         for (Edge e : graph[u]) {
    //             int v = e.dest;
    //             if (!vis[v]) {
    //                 vis[v] = true;
    //                 distance.put(v, distance.get(u) + 1);
    //                 parent.put(v, u);
    //                 q.offer(v);
    //             }
    //         }
    //         bfs.add(u);
    //     }
    //     printMap(distance);
    //     System.out.println();
    //     printMap(parent);
    //     return bfs;
    // }
    private static void printMap(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> en : map.entrySet()) {
            System.out.println(en.getKey() + " : " + en.getValue());
        }
    }

    private static boolean isBipartite(ArrayList<Edge>[] graph) {

        boolean[] vis = new boolean[graph.length];
        Map<Integer, String> colorMap = new HashMap<>();

        for (int i = 0; i < graph.length; i++) {
            vis[i] = false;
            colorMap.put(i, "");
        }

        Queue<Integer> q = new LinkedList<>();
        int s = 2; // Random Source for BFS 
        vis[s] = true;
        q.offer(s);
        colorMap.put(s, "Red");

        while (!q.isEmpty()) {

            int u = q.poll();
            String currColor;
            if (colorMap.get(u).equals("Red")) {
                currColor = "Blue";
            } else {
                currColor = "Red";
            }

            for (Edge e : graph[u]) {

                int v = e.dest;
                if (colorMap.get(v).equals(colorMap.get(u))) {
                    return false;
                }

                if (!vis[v]) {
                    colorMap.put(v, currColor);
                    q.offer(v);
                    vis[v] = true;
                }
            }
        }

        printMap(colorMap);
        return true;
    }

    public static void main(String[] args) {

        int V = 8;
        ArrayList<Edge>[] graph = new ArrayList[8];

        createGraph(graph);

        // System.out.println(neighbours(graph, 0));
        // System.out.println(BFS(graph, 2));
        // System.out.println(BFS_withDistanceAndParent(graph, 2));
        System.out.println(isBipartite(graph) ? "Yes" : "No");
    }
}
