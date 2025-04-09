
public class Knapsack {

    // Global table
    static int[][] table;

    public static void main(String[] args) {

        int n = 5;
        int[] V = {7, 2, 1, 6, 12};
        int[] W = {3, 1, 2, 4, 6};
        // int[] V = new int[n];
        // int[] W = new int[n];
        int C = 10;

        table = new int[C + 1][n + 1];
        for (int i = 0; i <= C; i++) {
            for (int j = 0; j <= n; j++) {
                table[i][j] = -1;
            }
        }

        int res = Knapsack_DP(V, W, C, 0);
        System.out.println(res);

    }

    private static int Knapsack(int[] V, int[] W, int C, int i) {
        // Base Case:
        if (i >= V.length) {
            return 0;
        }

        // not possible to take:
        if (C < W[i]) {
            return Knapsack(V, W, C, i + 1);
        }

        // not take or take:
        return Math.max(Knapsack(V, W, C, i + 1), V[i] + Knapsack(V, W, C - W[i], i + 1));

    }

    // Memoized Recursive Knapsack
    private static int Knapsack_DP(int[] V, int[] W, int C, int i) {
        if (i >= V.length) {
            return 0;
        }

        if (table[C][i] != -1) {
            return table[C][i]; // Return precomputed result
        }

        // If item cannot be included
        int ans = Knapsack_DP(V, W, C, i + 1);

        // If item can be included
        if (C >= W[i]) {
            ans = Math.max(ans, V[i] + Knapsack_DP(V, W, C - W[i], i + 1));
        }

        return table[C][i] = ans; // Store and return
    }

    // private static int Knapsack_BottomUp(int[] V, int[] W, int C) {

    //     int n = V.length;

    //     int[][] dp = new int[C + 1][n + 1];

    //     for (int i = 0; i < C + 1; i++) {
    //         dp[i][n] = 0;
    //     }

    //     for (int j = C-1; j >= 0; j--) {
            
    //     }

        
    // }
}
