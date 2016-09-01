// http://www.lintcode.com/en/problem/triangle/
public class QID109 {
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return 0;
        }

        int row = triangle.length;

        // state: triangle[i][j] = minimum path sum from (0,0) to (i,j) inclusively
        int[][] dp = new int[row][row];

        // initialization
        dp[0][0] = triangle[0][0];

        // transition
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }

        // result
        int min = dp[row - 1][0];
        for (int i : dp[row - 1]) {
            min = Math.min(min, i);
        }
        return min;
    }
}
