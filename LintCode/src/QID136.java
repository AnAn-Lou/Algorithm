import java.util.ArrayList;
import java.util.List;

// http://www.lintcode.com/en/problem/palindrome-partitioning/
public class QID136 {
    public List<List<String>> partition(String s) {

        List<List<String>> rst = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return rst;
        }

        List<String> list = new ArrayList<>();
        dfs(s, 0, list, rst);
        return rst;
    }

    private void dfs(String s,
                     int pos, // last cut position
                     List<String> list,
                     List<List<String>> rst){
        // exit
        if (pos == s.length()) {
            rst.add(new ArrayList(list));
            return;
        }

        // break down
        for (int i = pos + 1; i <= s.length(); i++) { // next cut position
            if (isPalindrome(s, pos, i - 1)) {
                list.add(s.substring(pos, i));
                dfs(s, i, list, rst);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {// [start, end]
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
