import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/word-break-ii/
public class QID140 {

    // search/backtrack
    public List<String> wordBreak(String s, Set<String> wordDict) {

        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) return new ArrayList<>();

        // check if breakable; important; otherwise TLE for case ["aaa..b", ["a","aa","aaa",...]]
        if (!isBreakable(s, wordDict)) { // O(n^2)
            return new ArrayList<>();
        }

        // find solutions; extreme case O(2^n)
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        helper(result, list, 0, s, wordDict);

        // build sentences
        List<String> sentences = new ArrayList<>();
        for (List<String> l : result) {
            StringBuilder sb = new StringBuilder();
            for (String word : l) {
                sb.append(word);
                sb.append(" ");
            }
            sb.setLength(sb.length() - 1); // remove trailing space
            sentences.add(sb.toString());
        }

        return sentences;
    }

    // find all possible sub sentences from lastCut
    private void helper(List<List<String>> result, List<String> list, int lastCut, String s, Set<String> wordDict) {
        // exit
        int len = s.length();
        if (lastCut == len) {
            result.add(new ArrayList<>(list));
            return;
        }

        // break down
        for (int i = lastCut + 1; i <= len; i++) {
            String substring = s.substring(lastCut, i);
            if (wordDict.contains(substring)) {
                list.add(substring);
                helper(result, list, i, s, wordDict);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isBreakable(String s, Set<String> wordDict) {

        if (s == null || s.length() == 0) {
            return true;
        }

        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }

        int len = s.length();

        // state: dp[i] = true if s.substring(0,i) is breakable
        boolean[] dp = new boolean[len + 1];

        // initialization
        dp[0] = true;

        // transition
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // result
        return dp[len];
    }
}
