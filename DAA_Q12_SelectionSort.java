
public class DAA_Q12_SelectionSort {

    public static void main(String[] args) {

        int[] arr = {5, 4, 3, 2, 2, 3, 4, 1, 3, 4};
        SelectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void SelectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int pos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[pos]) {
                    pos = j;
                }
            }

            swap(arr, i, pos);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
