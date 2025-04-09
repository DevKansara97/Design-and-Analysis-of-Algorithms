
public class DAA_Q9_MergeSort {

    public static void main(String[] args) {

        int[] arr = {2, 7, 3, 1, 2, 2, 4, 5, 3, 4};
        divide(arr, 0, 9);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void divide(int[] theSeq, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            divide(theSeq, left, mid);
            divide(theSeq, mid + 1, right);

            merge(theSeq, left, mid, right);
        }
    }

    private static void merge(int[] theSeq, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = theSeq[left + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = theSeq[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                theSeq[k] = L[i];
                i++;
            } else {
                theSeq[k] = R[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            theSeq[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            theSeq[k] = R[j];
            j++;
            k++;
        }
    }
}
