// Algotrithm:
// Findelt(r, A):
// 1. Divide Array A into n/5 groups of size 5 
// 2. Find median of all n/5 groups and put them in another array B
// 3. Findelt(n/10, B) -> Xmed (approximate median)
// 4. Partition Array A into Al and Ar such that all elements in Al <= Xmed and all elements in Ar > Xmed
// 5. |Al| = r-1 -> return Xmed
// 6. |Al| > r-1 -> return Findelt(r, Al)
// 7. |Al| < r-1 -> return Findelt(r - |Al| - 1, Ar)

import java.util.*;

public class Rank_of_Element {

    public static void main(String[] args) {
        int[] A = {12, 3, 5, 7, 19, 26, 45, 17, 1, 2, 8, 4, 6, 10};
        // Sorted sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 17, 19, 26, 45
        int r = 11;
        int result = Rank_of_Element_Function(r, A);
        System.out.println("The element at rank " + r + " is " + result);
    }

    public static int Rank_of_Element_Function(int r, int[] A) {
        int n = A.length;

        // Base case
        if (n == 1) {
            return A[0];
        }

        // Step 1&2: Divide Array A into n/5 groups of size 5
        int numGroups = (int) Math.ceil(n / 5.0);
        int[] B = new int[numGroups];
        int B_idx = 0;

        for (int i = 0; i < n; i += 5) {
            int end = Math.min(i + 5, n);
            B[B_idx] = FindMedian(Arrays.copyOfRange(A, i, end));
            B_idx++;
        }

        // Step 3: Findelt(n/10, B) -> Xmed (approximate median)
        int Xmed = Rank_of_Element_Function(numGroups / 2, B);

        // Step 4: Partition Array A into Al and Ar
        List<Integer> Al = new ArrayList<>();
        List<Integer> Ar = new ArrayList<>();

        for (int num : A) {
            if (num < Xmed) {
                Al.add(num);
            } else if (num > Xmed) {
                Ar.add(num);
            }
        }

        // Count the occurrences of Xmed in A
        int countXmed = n - Al.size() - Ar.size();

        // Step 5: |Al| = r-1 -> return Xmed
        if (Al.size() == r - 1) {
            return Xmed;
        }

        // Step 6: |Al| > r-1 -> return Findelt(r, Al)
        if (Al.size() > r - 1) {
            return Rank_of_Element_Function(r, Al.stream().mapToInt(Integer::intValue).toArray());
        }

        // Step 7: |Al| < r-1 -> return Findelt(r - |Al| - 1, Ar)
        return Rank_of_Element_Function(r - Al.size() - countXmed, Ar.stream().mapToInt(Integer::intValue).toArray());
    }

    public static int FindMedian(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length / 2];
    }
}
