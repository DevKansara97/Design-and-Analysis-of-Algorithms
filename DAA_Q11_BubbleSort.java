
public class DAA_Q11_BubbleSort {

    public static void main(String[] args) {

        int[] arr = {5, 4, 3, 2, 2, 3, 4, 1, 3, 4};
        BubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void BubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            boolean swapped = false;
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
