// http://www.lintcode.com/en/problem/longest-increasing-subsequence/
public class QID76 {
    public int longestIncreasingSubsequence(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // state: dp[i] = the length of LIS ending with nums[i]
        int[] dp = new int[nums.length];
        int max;

        // initialization
        dp[0] = 1;
        max = dp[0];

        // transition
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }

        // result
        return max;
    }
}
