
public class LongestCommonSubsequence {

    private static String LCS(String A, String B, int i, int j) {

        // Base Case:
        if (i == A.length() || j == B.length()) {
            return "";
        }

        // Recursion:
        // Identify first occurance of A[0] in B:
        int k = -1;
        for (int it = j; it < B.length(); it++) {
            if (A.charAt(i) == B.charAt(it)) {
                k = it;
                break;
            }
        }

        String ans1 = "";
        if (k != -1) {
            ans1 = ans1 + A.charAt(i) + LCS(A, B, i + 1, k + 1);
        }

        String ans2 = "";
        ans2 = ans2 + LCS(A, B, i + 1, j);

        return ans1.length() > ans2.length() ? ans1 : ans2;
    }

    public static void main(String[] args) {
        String A = "abdef";
        String B = "fxbcde";

        System.out.println(LCS(A, B, 0, 0));
    }
}
