// https://leetcode.com/problems/regular-expression-matching/
public class QID10 {

    public static void main(String[] args) {
        QID10 qid = new QID10();
        System.out.println(qid.isMatch("aa","aa"));

    }

    // Version 1: DP
    // great explanation for dp: http://blog.csdn.net/hk2291976/article/details/51165010
    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }

        int lenS = s.length();
        int lenP = p.length();

        // state: dp[i][j] == true if isMatch(s.substring(0, i), p.substring(0,j))
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];

        // initialization
        // dp[0][0] = true; dp[i][0] = false, i != 0; dp[0][j] dependes
        dp[0][0] = true;
        for (int j = 2; j <= lenP; j ++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // transition
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenP; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];

                } else if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];

                } else if (p.charAt(j - 1) == '*'){

                    // if ( X* matches zero OR X* matches one X OR X* matches multiple X )
                    if (dp[i][j - 2] ||
                            (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) && (dp[i - 1][j - 2] || dp[i - 1][j])) {

                        dp[i][j] = true;

                    } else { // not necessary; just to make the code easier to follow
                        dp[i][j] = false;
                    }

                } else { // not necessary; just to make the code easier to follow
                    dp[i][j] = false;
                }
            }
        }

        return dp[lenS][lenP];

    }

    /*
    // still buggy
    // Version 2: backtracking
    // reference: http://blog.csdn.net/hk2291976/article/details/51165010
    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }

        char[] s_arr = s.toCharArray();
        char[] p_arr = p.toCharArray();

        return isMatchHelper(s_arr, s_arr.length - 1, p_arr, p_arr.length - 1);

    }

    private boolean isMatchHelper(char[] s_arr, int i,
                                  char[] p_arr, int j) {
        // exit
        if (j == -1 && i == -1) {
            return true;
        } else if (j == -1 && i != -1){
            return false;
        }

        // solve a smaller problem

        if (p_arr[j] == '*') {

            // X* matches one or more X
            if (i > -1 && p_arr[j - 1] == '.' || p_arr[j - 1] == s_arr[i]) {
                if (isMatchHelper(s_arr, i - 1, p_arr, j)) {
                    return true;
                }
            }

            // X* matches zero X
            return isMatchHelper(s_arr, i, p_arr, j - 2);

        } else if (p_arr[j] == '.' || p_arr[j] == s_arr[i]) {
            return isMatchHelper(s_arr, i - 1, p_arr, j - 1);

        } else {
            return false;
        }
    }
    */
}
