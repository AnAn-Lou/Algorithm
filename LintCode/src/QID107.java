import java.util.Set;

// http://www.lintcode.com/en/problem/word-break/
public class QID107 {
    public boolean wordBreak(String s, Set<String> dict) {

        if (s == null || s.length() == 0) {
            return true;
        }

        if (dict == null || dict.size() == 0) {
            return false;
        }

        int len = s.length();

        // state: dp[i] = true if s.substring(0,i) is breakable
        boolean[] dp = new boolean[len + 1];

        // initialization
        dp[0] = true;

        // transition
        int maxLen = getMaxLength(dict);
        for (int i = 1; i <= len; i++) {
            for (int j = Math.max(0, i - maxLen); j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // result
        return dp[len];
    }

    // return the max word length in dict
    private int getMaxLength(Set<String> dict){
        int max = 0;
        for (String d : dict) {
            max = Math.max(max, d.length());
        }
        return max;
    }
}
