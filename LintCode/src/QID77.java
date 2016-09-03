// http://www.lintcode.com/en/problem/longest-common-subsequence/
public class QID77 {
    public int longestCommonSubsequence(String A, String B) {

        if (A == null || A.length() == 0 || B == null || B.length() == 0) {
            return 0;
        }

        int lenA = A.length();
        int lenB = B.length();

        // state: dp[i][j] = LCS between A.substring(0,i) and B.substring(0,j)
        int[][] dp = new int[lenA + 1][lenB + 1];

        // transition
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        // result
        return dp[lenA][lenB];
    }
}
