// http://www.lintcode.com/en/problem/longest-common-substring/
public class QID79 {
    public int longestCommonSubstring(String A, String B) {

        if (A == null || A.length() == 0 || B == null || B.length() == 0) {
            return 0;
        }

        int lenA = A.length();
        int lenB = B.length();

        // state: dp[i][j] = LCS ending with A.charAt(i - 1) and B.charAt(j - 1)
        int[][] dp = new int[lenA + 1][lenB + 1];

        // transition
        int max = 0;
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        // result
        return max;
    }
}
