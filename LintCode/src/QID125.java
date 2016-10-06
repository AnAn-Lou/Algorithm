public class QID125 {
    public static int backPackII(int m, int[] A, int V[]) {
        if (m == 0 || A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;

        // state: dp[i][j] = the max size we can fill a backpack of size i using the first j items
        int[][] dp = new int[n + 1][m + 1];

        // initialization
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // transition
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= A[i - 1]){
                    dp[i][j] = Math.max(dp[i - 1][j - A[i - 1]] + V[i - 1], dp[i][j]);
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        int[] A = {77,22,29,50,99};
        int[] V = {92,22,87,46,90};
        int m = 100;
        System.out.println(QID125.backPackII(m, A, V));

    }
}
