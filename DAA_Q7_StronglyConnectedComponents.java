
import java.util.*;

public class DAA_Q7_StronglyConnectedComponents {

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

        graph[1].add(new Edge(1, 2));
        graph[1].add(new Edge(1, 4));
        graph[1].add(new Edge(1, 5));

        graph[2].add(new Edge(2, 3));
        graph[2].add(new Edge(2, 6));

        graph[3].add(new Edge(3, 2));
        graph[3].add(new Edge(3, 7));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 7));

        graph[7].add(new Edge(7, 7));

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

        // printMap2(timeMap);
        // System.out.println();
        // printMap(parentMap);
        return dfs;
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

    public static int StronglyConnectedComponents(ArrayList<Edge>[] graph) {

        HashMap<Integer, Integer> finishTimeMap = new HashMap<>();
        boolean[] vis = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            vis[i] = false;
            finishTimeMap.put(i, -1);
        }

        int[] time = {0};
        for (int i = 0; i < graph.length; i++) {
            DFS_Visit2(graph, i, time, finishTimeMap, vis);
        }

        int cnt = 0;
        finishTimeMap = sortByValue(finishTimeMap);
        ArrayList<Integer> SortedFinishTime = new ArrayList<>(finishTimeMap.keySet());

        for (int i = 0; i < graph.length; i++) {
            vis[i] = false;
        }

        time[0] = 0;
        for (int i = SortedFinishTime.size() - 1; i >= 0; i--) {
            int u = SortedFinishTime.get(i);
            if (!vis[u]) {
                cnt++;
                DFS_Visit2(graph, u, time, finishTimeMap, vis);
            }

        }

        return cnt;
    }

    static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Integer>> list
                = new LinkedList<Map.Entry<Integer, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                    Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap 
        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static ArrayList<Edge>[] reverseGraph(ArrayList<Edge>[] graph) {

        ArrayList<Edge>[] revGraph = new ArrayList[graph.length];

        for (int i = 0; i < graph.length; i++) {
            revGraph[i] = new ArrayList<>();
        }

        for (ArrayList<Edge> ls : graph) {
            for (Edge e : ls) {
                revGraph[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        return revGraph;
    }

    public static void DFS_Visit2(ArrayList<Edge>[] graph, int u, int[] time, Map<Integer, Integer> finishTimeMap, boolean[] vis) {

        time[0]++;
        // start time
        vis[u] = true;

        for (Edge e : graph[u]) {
            int v = e.dest;
            if (!vis[v]) {
                DFS_Visit2(graph, v, time, finishTimeMap, vis);
            }
        }

        time[0]++;
        finishTimeMap.put(u, time[0]);
    }

    public static void printGraph(ArrayList<Edge>[] graph) {
        int i = 0;
        for (ArrayList<Edge> ls : graph) {
            System.out.print(i++ + " : ");
            for (Edge e : ls) {
                System.out.print(e.dest + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int V = 8;
        ArrayList<Edge>[] graph = new ArrayList[8];

        createGraph(graph);

        // printGraph(graph);
        // printGraph(reverseGraph(graph));
        int cnt = StronglyConnectedComponents(graph);
        System.out.println("No. of strongly connected components: " + cnt);

    }

}
