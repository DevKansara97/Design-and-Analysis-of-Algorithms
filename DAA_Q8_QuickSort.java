
public class DAA_Q8_QuickSort {

    public static void main(String[] args) {
        int[] arr = {2, 7, 3, 1, 2, 2, 4, 5, 3, 4};
        QuickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static int partitionSequence(int[] theSeq, int low, int high) {

        int i = low - 1;
        int pivot = theSeq[high];

        for (int j = low; j < high; j++) {
            if (theSeq[j] < pivot) {
                i++;
                swap(theSeq, i, j);
            }
        }

        swap(theSeq, i + 1, high);
        return i + 1;
    }

    public static void QuickSort(int[] theSeq, int low, int high) {

        if (low < high) {
            int pi = partitionSequence(theSeq, low, high);
            QuickSort(theSeq, low, pi - 1);
            QuickSort(theSeq, pi + 1, high);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
