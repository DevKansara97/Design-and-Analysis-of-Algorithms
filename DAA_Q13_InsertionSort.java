
public class DAA_Q13_InsertionSort {

    public static void main(String[] args) {

        int[] arr = {5, 4, 3, 2, 2, 3, 4, 1, 3, 4};
        InsertionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void InsertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                swap(arr, j, j - 1);
                j--;
            }
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
