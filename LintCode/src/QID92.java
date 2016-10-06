public class QID92 {
    public int backPack(int m, int[] A) {

        if (m == 0 || A == null || A.length == 0) {
            return 0;
        }

        int len = A.length;

        // state: dp[i][j] = the max size we can fill a backpack of size i using the first j items
        int[][] dp = new int[len + 1][m + 1];

        // initialization
        for (int i = 0; i <= len; i++) {
            dp[i][0] = 0;
        }

        // transition
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= A[i - 1]){
                    dp[i][j] = Math.max(dp[i - 1][j - A[i - 1]] + A[i - 1], dp[i][j]);
                }
            }
        }

        return dp[len][m];
    }
}
