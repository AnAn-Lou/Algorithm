// http://www.lintcode.com/en/problem/n-queens-ii/
public class QID34 {
    private int total;

    public int totalNQueens(int n) {


        boolean[] col = new boolean[n];
        boolean[] leftdiag = new boolean[2 * n - 1]; // row + col
        boolean[] rightdiag = new boolean[2 * n - 1]; // row - col + n - 1

        total = 0;

        helper(n, 0, col, leftdiag, rightdiag);

        return total;
    }

    // increase total by 1 if find a solution starting from current row
    private void helper(int n, int row,
                        boolean[] col, boolean[] leftdiag, boolean[] rightdiag){
        // exit
        if (row == n) {
            total++;
            return;
        }

        // break down
        for (int i = 0; i < n; i++) { // for the current row, for each column i
            if (col[i] || leftdiag[row + i] || rightdiag[row - i + n - 1]) {
                continue;
            }

            col[i] = true;
            leftdiag[row + i] = true;
            rightdiag[row - i + n - 1] = true;

            helper(n, row + 1, col, leftdiag, rightdiag);

            col[i] = false;
            leftdiag[row + i] = false;
            rightdiag[row - i + n - 1] = false;
        }
    }
}
