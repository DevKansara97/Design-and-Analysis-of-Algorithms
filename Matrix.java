
public class Matrix {

    public static void main(String[] args) {

        int n = 3;
        int[][] mtx = new int[n][n];

        int[][] A = {{1, 2, 3}, {-1, 0, 3}, {4, 5, 1}};
        int[][] B = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtx[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    mtx[i][j] = mtx[i][j] + A[i][k] * B[k][j];
                }
            }
        }

        printMatrix(mtx);
    }

    static void printMatrix(int[][] mtx) {
        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx[0].length; j++) {
                System.out.print(mtx[i][j] + " ");
            }
            System.out.println();
        }
    }
}
