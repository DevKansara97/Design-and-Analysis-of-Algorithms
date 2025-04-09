
import java.util.*;

public class DAA_Q6_DFS {

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

        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));
        graph[2].add(new Edge(2, 7));

        graph[3].add(new Edge(3, 2));
        graph[3].add(new Edge(3, 4));

        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 5));
        graph[6].add(new Edge(6, 7));

        graph[7].add(new Edge(7, 6));
        graph[7].add(new Edge(7, 2));

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

    public static List<Integer> DFS(ArrayList<Edge>[] graph) {

        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, int[]> timeMap = new HashMap<>();
        ArrayList<Integer> dfs = new ArrayList<>();

        boolean[] vis = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            vis[i] = false;
            parentMap.put(i, -1);
            timeMap.put(i, new int[]{0, 0});
        }

        int[] time = {0};
        int[] temp = {2, 3, 1, 5, 7, 6, 0, 4};
        for (int u : temp) {
            if (!vis[u]) {
                DFS_Visit(graph, u, time, timeMap, parentMap, vis, dfs);
            }
        }

        printMap2(timeMap);
        System.out.println();
        printMap(parentMap);
        return dfs;
    }

    public static void printMap2(Map<Integer, int[]> timeMap) {
        for (Map.Entry<Integer, int[]> e : timeMap.entrySet()) {
            System.out.println(e.getKey() + ": st = " + e.getValue()[0] + " ft = " + e.getValue()[1]);
        }
    }

    public static void DFS_Visit(ArrayList<Edge>[] graph, int u, int[] time, Map<Integer, int[]> timeMap, Map<Integer, Integer> parentMap, boolean[] vis, ArrayList<Integer> dfs) {

        time[0] = time[0] + 1;
        timeMap.get(u)[0] = time[0];
        vis[u] = true;

        dfs.add(u);
        for (Edge e : graph[u]) {
            int v = e.dest;
            if (!vis[v]) {
                parentMap.put(v, u);
                DFS_Visit(graph, v, time, timeMap, parentMap, vis, dfs);
            }
        }

        time[0] = time[0] + 1;
        timeMap.get(u)[1] = time[0];

    }

    private static void printMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            System.out.println(en.getKey() + " : " + en.getValue());
        }
    }

    public static void main(String[] args) {

        int V = 8;
        ArrayList<Edge>[] graph = new ArrayList[8];

        createGraph(graph);
        // System.out.println(neighbours(graph, 0));

        // System.out.println(BFS(graph, 2));
        // System.out.println(BFS_withDistanceAndParent(graph, 2));
        // System.out.println(shortestPath_fromStoU(graph, 2, 5));
        System.out.println(DFS(graph));
    }

}
