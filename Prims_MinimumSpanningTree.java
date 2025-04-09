// Minimum Spanning Tree: A tree in which there are 'n' nodes and 'n-1' edges 
// and all nodes are reachable from one another


// No. of spanning Trees in a complete (K_n) = n^(n-2)
import java.util.*;

public class Prims_MinimumSpanningTree {

    static class Pair {

        int dist;
        int node;

        public Pair(int dist, int node) {
            this.dist = dist;
            this.node = node;
        }
    }

    static class Edge {

        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }

    }

    public static void createGraph(ArrayList<Edge>[] graph) {

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 0, 2));
        graph[1].add(new Edge(1, 2, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 1, 1));
        graph[2].add(new Edge(2, 3, 2));
        graph[2].add(new Edge(2, 4, 2));

        graph[3].add(new Edge(3, 2, 2));
        graph[3].add(new Edge(3, 4, 1));

        graph[4].add(new Edge(4, 2, 2));
        graph[4].add(new Edge(4, 3, 1));

    }

    private static ArrayList<Edge>[] Prims_MST(ArrayList<Edge>[] Graph) {

        int V = Graph.length;
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            vis[i] = false;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> x.dist - y.dist);
    }
    

    
    public static void main(String[] args) {

        int V = 5;
        ArrayList<Edge>[] Graph = new ArrayList[V];

        createGraph(Graph);

        ArrayList<Edge>[] MST = Prims_MST(Graph);
    }
}
