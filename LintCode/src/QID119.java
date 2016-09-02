// http://www.lintcode.com/en/problem/edit-distance/
public class QID119 {
    public int minDistance(String word1, String word2) {

        if (word1 == null || word2 == null) {
            return -1;
        }

        int len1 = word1.length();
        int len2 = word2.length();

        if (len1 == 0 || len2 == 0) {
            return Math.max(len1, len2);
        }

        // state: dp[i][j] = edit distance from word1.substring(0,i) to word2.substring(0,j)
        int[][] dp = new int[len1 + 1][len2 + 1];

        // initialization
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        // transition
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // if replace
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // if insert
                dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i][j]);
                // if delete
                dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j]);
            }
        }

        // result
        return dp[len1][len2];
    }
}
