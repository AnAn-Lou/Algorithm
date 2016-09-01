// http://www.lintcode.com/en/problem/jump-game-ii/
public class QID117 {
    public int jump(int[] A) {

        if (A == null || A.length == 0) {
            return 0;
        }

        // state: dp[i] = minimum number of jumps to reach index i
        int[] dp = new int[A.length];

        // initialization
        dp[0] = 0;

        // transition
        for (int i = 1; i < A.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (A[j] + j >= i) {
                    min = Math.min(min, dp[j] + 1);
                }
            }
            if (min == Integer.MAX_VALUE) {
                return -1; // unreachable
            }
            dp[i] = min;
        }

        // result
        return dp[A.length - 1];
    }
}
