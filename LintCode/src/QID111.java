// http://www.lintcode.com/en/problem/climbing-stairs/
public class QID111 {
    public int climbStairs(int n) {

        if (n < 0) {
            return -1;
        }

        if (n <= 1) {
            return 1;
        }


        // state: dp[i] = number of distinct ways to climb to stair i
        int[] dp = new int[n + 1];

        // initialization
        dp[0] = 1;
        dp[1] = 1;

        // transition
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // result
        return dp[n];
    }
}
