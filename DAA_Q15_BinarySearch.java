
public class DAA_Q15_BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 3, 4, 8, 9, 11, 45};
        // int idx = BinarySearch_Iterative(arr, 2);
        int idx = BinarySearch_Recursive(arr, 0, arr.length - 1, 2);

        System.out.println(idx);
    }

    public static int BinarySearch_Iterative(int[] arr, int x) {

        int s = 0, e = arr.length - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (x < arr[mid]) {
                e = mid - 1;
            } else if (x > arr[mid]) {
                s = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static int BinarySearch_Recursive(int[] arr, int s, int e, int x) {

        if (s > e) {
            return -1;
        }

        int mid = s + (e - s) / 2;
        if (arr[mid] == x) {
            return mid;
        }

        if (x < arr[mid]) {
            return BinarySearch_Recursive(arr, s, mid - 1, x);
        } else {
            return BinarySearch_Recursive(arr, mid + 1, e, x);
        }
    }

}
