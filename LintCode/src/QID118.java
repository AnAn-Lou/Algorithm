// http://www.lintcode.com/en/problem/distinct-subsequences/
public class QID118 {
    public int numDistinct(String S, String T) {

        if (S == null || T == null) {
            return 0;
        }

        int lenS = S.length();
        int lenT = T.length();

        if (lenT == 0) {
            return 1;
        }

        if (lenS == 0) {
            return 0;
        }

        // state: dp[i][j] = distinct subsequences of T.substring(0,j) in S.substring(0,i);
        int[][] dp = new int[lenS + 1][lenT + 1];

        // initialization
        for (int i = 0; i <= lenS; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= lenT; j++) {
            dp[0][j] = 0;
        }

        // transition
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenT; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // result
        return dp[lenS][lenT];
    }
}
