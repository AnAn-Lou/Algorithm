// http://www.lintcode.com/en/problem/jump-game/
public class QID116 {
    /*
    // Version 1: dp
    public boolean canJump(int[] A) {

        if (A == null || A.length == 0) {
            return false;
        }

        // state: dp[i] = true if index i can be reached, = false otherwise
        boolean[] dp = new boolean[A.length];

        // initialization
        dp[0] = true;

        // transition: dp[i] = true if exist 0 < j < i such that dp[j] == true && A[j] + j >= i
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && A[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // result
        return dp[A.length - 1];
    }
    */

    // Version 2: Greedy
    public boolean canJump(int[] A) {

        if (A == null || A.length == 0) {
            return false;
        }

        int canReach = 0; // the farest index can be reached
        for (int i = 0; i <= canReach; i++) {
            canReach = Math.max(canReach, i + A[i]);
            if (canReach >= A.length - 1) {
                return true;
            }
        }

        return false;
    }
}
