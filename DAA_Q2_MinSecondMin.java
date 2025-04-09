
import java.util.*;

public class DAA_Q2_MinSecondMin {

    public static void main(String[] args) {

        int[] arr = {12, 1, 3, 4, 65, 7, -43, 8, 3, 8, 45, 2};

        int[] minSecondMin = traditional_minSecondMin(arr);
        System.out.println("Min: " + minSecondMin[0]);
        System.out.println("Second Min: " + minSecondMin[1]);
        // int[] minSecondMin = minSecondMin_Heap(arr);
        // System.out.println("Min: " + minSecondMin[0]);
        // System.out.println("Second Min: " + minSecondMin[1]);
        // int[] minSecondMin = findMinAndSecondMin(arr);
        int[] minSecondMin_opt = minSecondMin_optimized(arr);
        System.out.println("Min: " + minSecondMin_opt[0]);
        System.out.println("Second Min: " + minSecondMin_opt[1]);

    }

    public static int[] minSecondMin_optimized(int[] arr) {

        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must have at least one element.");
        }

        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] < secondMin) {
                comparisons++;
                if (arr[i] < min) {
                    comparisons++;
                    secondMin = min;
                    min = arr[i];
                } else {
                    secondMin = arr[i];
                }
            }
        }

        System.out.println("Comparisons: " + comparisons);
        return new int[]{min, secondMin};
    }

    // (2n-2) Comparisons: Traditional Approach:
    static int[] traditional_minSecondMin(int[] arr) {

        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must have at least one element.");
        }

        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        int comparisons = 0;

        if (arr.length == 1) {
            System.out.println("Comparisons: " + 0);
            return new int[]{arr[0], arr[0]};
        }

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] < min) {
                comparisons++;
                secondMin = min;
                min = arr[i];
            } else if (arr[i] > min && arr[i] < secondMin) {
                comparisons++;
                secondMin = arr[i];
            }

        }

        // If no second min found, for duplicate arrays:
        if (secondMin == Integer.MAX_VALUE) {
            secondMin = min;
        }

        System.out.println("Comparisons: " + comparisons);
        return new int[]{min, secondMin};
    }

    // Min Heap Approach:
    public static int[] minSecondMin_Heap(int[] arr) {

        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must have at least one element.");
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        int[] minSecondMin = new int[]{pq.poll(), pq.poll()};
        return minSecondMin;
    }

    // (n + logn - 2) Comparisons: Tournament Approach
    public static int[] findMinAndSecondMin(int[] arr) {

        if (arr.length < 2) {
            throw new IllegalArgumentException("Array must contain at least 2 elements");
        }

        // Tournament to find min
        List<Integer> losers = new ArrayList<>();
        int min = arr[0];
        int comparisons = 0;

        for (int i = 1; i < arr.length; i++) {
            comparisons++;
            if (arr[i] < min) {
                losers.add(min);
                min = arr[i];
            } else {
                losers.add(arr[i]);
            }
        }

        // Find second min from losers
        int secondMin = Integer.MAX_VALUE;
        for (int num : losers) {
            comparisons++;
            if (num < secondMin) {
                secondMin = num;
            }
        }

        System.out.println("Comparisons: " + comparisons);
        return new int[]{min, secondMin};
    }

    public static int[] findMinAndSecondMin_(int[] arr) {

        if (arr.length < 2) {
            throw new IllegalArgumentException("Array must contain at least 2 elements");
        }

        // Tournament to find min:
        // List<Integer> losers = new ArrayList<>();
        int min = arr[0], secondMin = arr[0];
        int comparisons = 0;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] < secondMin) {
                comparisons++;
                if (arr[i] < min) {
                    comparisons++;
                    secondMin = min;
                    min = arr[i];
                } else {
                    secondMin = arr[i];
                }
            }
        }

        System.out.println("Comparisons: " + comparisons);
        return new int[]{min, secondMin};
    }
}
