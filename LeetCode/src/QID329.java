// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public class QID329 {

    public static void main(String[] args) {
        QID329 qid = new QID329();
        int[][] matrix = {
                {0,1,2,3,4,5,6,7,8,9},
                {19,18,17,16,15,14,13,12,11,10},
                {20,21,22,23,24,25,26,27,28,29},
                {0,0,0,0,0,0,0,0,0,0}
        };
        System.out.println(qid.longestIncreasingPath(matrix));

    }

    // Version 3
    // Even better idea: Graph theory
    // https://discuss.leetcode.com/topic/35021/graph-theory-java-solution-o-v-2-no-dfs

    // Version 2
    // DFS + memorization
    // REFERENCE
    // https://discuss.leetcode.com/topic/34835/15ms-concise-java-solution
    private static final int[] x_arr = {0, 0, -1, 1};
    private static final int[] y_arr = {-1, 1 ,0, 0};

    public int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int max = 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, helper(matrix, i, j, cache));
            }
        }

        return max;
    }

    // return the length of longest increasing path starting from matrix[i][j]
    private int helper(int[][] matrix, int x, int y, int[][] cache) {
        if (cache[x][y] != 0) {
            return cache[x][y];
        }

        int max = 1; //
        for (int i = 0; i < 4; i++) { // go up, down, left, right

            if (outOfBoundry(matrix, x + x_arr[i], y + y_arr[i])
                    || matrix[x][y] >= matrix[x + x_arr[i]][y + y_arr[i]]) { // path close

                continue;
            }

            max = Math.max(max, 1 + helper(matrix, x + x_arr[i], y + y_arr[i], cache));
        }

        cache[x][y] = max;
        return cache[x][y];
    }

    // return true if the current cordinate (x,y) is out of the boundry of the matrix
    private boolean outOfBoundry(int[][] matrix, int x, int y){

        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            return true;
        }

        return false;
    }


    /* My Solution
     *
     * clarify questions:
     * 1. where do I start and end? - anywhere
     * 2. which directions can I go? - up down left right; not diagonally
     *
     * Time Limit Exceed
     *
    int max = 0;
    int[] x_arr = {0, 0, -1, 1};
    int[] y_arr = {-1, 1 ,0, 0};

    public int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                helper(matrix, i, j, 1);
            }
        }

        return max;
    }

    // return the length of longest increasing path starting from matrix[i][j]
    private void helper(int[][] matrix, int x, int y, int len) {

        for (int i = 0; i < 4; i++) { // go up, down, left, right
            if (!outOfBoundry(matrix, x + x_arr[i], y + y_arr[i])
                    && matrix[x][y] < matrix[x + x_arr[i]][y + y_arr[i]]) {
                helper(matrix, x + x_arr[i], y + y_arr[i], len + 1);
            } else {
                if (len > max) {
                    max = len;
                }
                continue;
            }
        }
    }

    // return true if the current cordinate (x,y) is out of the boundry of the matrix
    private boolean outOfBoundry(int[][] matrix, int x, int y){

        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            return true;
        }

        return false;
    }

    */

}
