
public class DAA_Q14_SequentialSearch {

    public static void main(String[] args) {

        int[] arr = {5, 4, 3, 2, 2, 3, 4, 1, 3, 4};
        int idx = SequentialSearch(arr, 2);

        System.out.println(idx);
    }

    public static int SequentialSearch(int[] arr, int x) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }

        return -1;
    }
}
