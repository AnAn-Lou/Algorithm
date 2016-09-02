// http://www.lintcode.com/en/problem/palindrome-partitioning-ii/
public class QID108 {
    public int minCut(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();

        // state: dp[i] = min cuts of s.substring(0,i)
        int[] dp = new int[len + 1];

        // initialization
        dp[0] = -1;

        // preparation: isPalindrome[i][j] = true if s.substring(i,j) is a palindrome
        boolean[][] isPalindrome = getIsPalindrome(s);

        // transition
        for (int i = 1; i <= len; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i]) { // s.substring(j,i)
                    min = Math.min(min, dp[j] + 1);
                }
            }
            dp[i] = min;
        }

        // result
        return dp[len];

    }

    private boolean[][] getIsPalindrome(String s) {

        int len = s.length();
        // state: isPalindrome[i][j] = true if s.substring(i,j) is a palindrome
        boolean[][] isPalindrome = new boolean[len][len + 1];
        // initialization:
        for (int i = 0; i < len; i++) { // len == 0
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < len; i++) { // len == 1
            isPalindrome[i][i+1] = true;
        }
        // transition
        for (int length = 2; length <= len; length++) {
            for (int start = 0; start <= len - length; start++) {
                if (isPalindrome[start + 1][start + length - 1]
                        && s.charAt(start) == s.charAt(start + length - 1)) {
                    isPalindrome[start][start + length] = true;
                }
            }
        }

        return isPalindrome;
    }

    // return true if s.substring(i,j) is a palindrome
    private boolean isPalindrome(int i, int j, String s) {
        j--;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
