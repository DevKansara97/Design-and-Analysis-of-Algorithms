
public class DAA_Q10_Minimum {

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 2, 3, 4, 1, 3, 4};
        System.out.println(findMinimum(arr));
    }

    public static int findMinimum(int[] arr) {

        int min = arr[0];
        int comparisons = 0;

        for (int i = 1; i < arr.length; i++) {
            comparisons++;
            if (arr[i] < min) {
                min = arr[i];
            }

        }

        System.out.println("Comparisons: " + comparisons);

        return min;
    }
}
