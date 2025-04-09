// Min-Max (with calculation of the number of comparisons)

public class DAA_Q1_MinMax {

    public static void main(String[] args) {

        int[] arr = {1, 7, -2, -3, 3, 3, 8, -1, -9, 6, 5, 4, 3, 6, 87, 32, 32, 5, 7, 9, 0, 4, 28, 82, 39, 9, 8, 5, 1, -4, 5, -87, -9, -9, -45, 78, 4, 12, 58, 6, 7, 8, 5, 4, -9, -63, 59};

        /////////////////////////////////////////////////////////////////
        long startTime1 = System.nanoTime();
        int[] minMax = minMax(arr);
        long endTime1 = System.nanoTime();

        System.out.println("Min: " + minMax[0]);
        System.out.println("Max: " + minMax[1]);

        long executionTime1 = endTime1 - startTime1;
        System.out.println(executionTime1);
        System.out.println();
        /////////////////////////////////////////////////////////////////
        long startTime2 = System.nanoTime();
        int[] traditional_minMax = traditional_minMax(arr);
        long endTime2 = System.nanoTime();

        System.out.println("Min: " + traditional_minMax[0]);
        System.out.println("Max: " + traditional_minMax[1]);

        long executionTime2 = endTime2 - startTime2;
        System.out.println(executionTime2);
        System.out.println();
        /////////////////////////////////////////////////////////////////

        long startTime3 = System.nanoTime();
        int[] result = minMaxRecursive(arr, 0, arr.length - 1, new int[]{0}); // Last argument to count comparisons
        long endTime3 = System.nanoTime();
        System.out.println("Comparisons: " + result[2]);
        System.out.println("Min: " + result[0]);
        System.out.println("Max: " + result[1]);

        long executionTime3 = endTime3 - startTime3;
        System.out.println(executionTime3);
        System.out.println();

    

    /////////////////////////////////////////////////////////////////
    }

    // Comparisons: 2n-2
    static int[] traditional_minMax(int[] arr) {

        int n = arr.length;
        int min = arr[0], max = arr[0];
        int comparisons = 0;

        for (int i = 1; i < n; i++) {
            comparisons++;
            if (min > arr[i]) {
                min = arr[i];
            }

            if (max < arr[i]) {
                max = arr[i];
            }
        }

        System.out.println("Comparisons: " + comparisons);
        return new int[]{min, max};
    }

    // Comparisons: ((1.5)n-2) : Using for loop:
    static int[] minMax(int[] arr) {

        int min, max;
        int n = arr.length;
        int comparisons = 0;

        if (n == 1) {
            System.out.println("Comparisons: " + comparisons);
            return new int[]{arr[0], arr[0]};
        }

        // 1 comparison:
        comparisons++;
        if (arr[0] <= arr[1]) {
            min = arr[0];
            max = arr[1];
        } else {
            min = arr[1];
            max = arr[0];
        }

        int i, j;
        for (i = 2, j = 3; i < n && j < n; i += 2, j += 2) {
            int cMin, cMax;

            // 1 comparison:
            comparisons++;
            if (arr[i] <= arr[j]) {
                cMin = arr[i];
                cMax = arr[j];
            } else {
                cMin = arr[j];
                cMax = arr[i];
            }

            // 1 comparison:
            comparisons++;
            if (min > cMin) {
                min = cMin;
            }

            // 1 comparison:
            comparisons++;
            if (max < cMax) {
                max = cMax;
            }
        }

        if (i < n) {
            // 1 comparison:
            comparisons++;
            if (min > arr[i]) {
                min = arr[i];
            } else if (max < arr[i]) {
                max = arr[i];
            }
        }

        System.out.println("Comparisons: " + comparisons);

        return new int[]{min, max};
    }

    // Comparisons: ((1.5)n-2)
    static int[] minMaxRecursive(int[] arr, int left, int right, int[] comparisons) {

        // Base Case: 1 element
        if (left == right) {
            return new int[]{arr[left], arr[left], comparisons[0]};
        }

        // Base Case: 2 element
        if (right == left + 1) {
            // Two comparisons:
            comparisons[0]++;
            if (arr[left] <= arr[right]) {
                return new int[]{arr[left], arr[right], comparisons[0]};
            } else {
                return new int[]{arr[right], arr[left], comparisons[0]};
            }
        }

        // Divide:
        int mid = left + (right - left) / 2;
        int[] leftPart = minMaxRecursive(arr, left, mid, comparisons);
        int[] rightPart = minMaxRecursive(arr, mid + 1, right, comparisons);

        // Conquer:
        comparisons[0] += 2;
        int globalMin = Math.min(leftPart[0], rightPart[0]);
        int globalMax = Math.max(leftPart[1], rightPart[1]);
        return new int[]{globalMin, globalMax, comparisons[0]};
    }
}

// Execution Time:
// final long startTime = System.currentTimeMillis();
// final long endTime = System.currentTimeMillis();
// long executionTime = startTime - endTime;
