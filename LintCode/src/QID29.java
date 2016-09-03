// http://www.lintcode.com/en/problem/interleaving-string/
public class QID29 {
    public boolean isInterleave(String s1, String s2, String s3) {

        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        if (len1 + len2 != len3) {
            return false;
        }

        // state: dp[i][j] = true if s3.substring(i + j) is formed by interleaving of s1.substring(i) and s2.substring(j)
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];

        // initialization
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {
            if (dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            } else {
                break;
            }
        }
        for (int j = 1; j <= len2; j++) {
            if (dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[0][j] = true;
            } else {
                break;
            }
        }

        // transition
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                        ||
                        dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)){
                    dp[i][j] = true;
                }
            }
        }

        // result
        return dp[len1][len2];
    }
}
