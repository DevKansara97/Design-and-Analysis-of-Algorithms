
import java.util.*;

public class FractionalKnapsackProblem {

    public static void main(String[] args) {

        double capacity = 20;
        double[] profit = {25, 24, 15};
        double[] weight = {18, 15, 10};

        double MaxProfit = FractionalKnapsack(profit, weight, capacity);

        // Map<Double, double[]> profit_weight_Ratio = new HashMap<>();
        // for (int i = 0; i < profit.length; i++) {
        //     profit_weight_Ratio.put(profit[i] / weight[i], new double[]{profit[i], weight[i]});
        // }
        System.out.println(MaxProfit);
    }

    public static double FractionalKnapsack(double[] profit, double[] weight, double capacity) {

        int n = profit.length;
        Map<Double, Integer> PbyW_Map = new HashMap<>();

        double[] P_W = new double[n];

        for (int i = 0; i < profit.length; i++) {
            P_W[i] = profit[i] / weight[i];
            PbyW_Map.put(profit[i] / weight[i], i);
        }

        Arrays.sort(P_W);
        double res = 0;

        for (int i = n - 1; i >= 0; i--) {

            if (capacity > 0) {
                int idx = PbyW_Map.get(P_W[i]);
                if (weight[idx] <= capacity) {
                    capacity -= weight[idx];
                    res += profit[idx];
                } else {
                    res += profit[idx] * capacity / weight[idx];
                    capacity = 0;
                }

            } else {
                break;
            }

        }

        return res;
    }
}
