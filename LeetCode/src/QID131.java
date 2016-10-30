import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/palindrome-partitioning/
public class QID131 {

    public static void main(String[] args) {
        QID131 qid = new QID131();
        System.out.println(qid.partition("aab"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return result;
        }

        List<String> list = new ArrayList<>();

        helper(result, list, 0, s);

        return result;
    }

    private void helper(List<List<String>> result,
                        List<String> list,
                        int lastCut,
                        String s) {
        // exit
        int len = s.length();
        if (lastCut == len) {
            result.add(new ArrayList<>(list));
            return;
        }

        // break down
        for (int i = lastCut + 1; i <= len; i++) {
            String substring = s.substring(lastCut, i);
            if (isPalindrome(substring)) {
                list.add(substring);
                helper(result, list, i, s);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s){
        if (s == null) return false;
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start ++;
            end --;
        }
        return true;
    }
}
