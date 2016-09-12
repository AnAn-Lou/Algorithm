// https://leetcode.com/problems/longest-palindromic-substring/
public class QID5 {
    public String longestPalindrome(String s) {

        if (s == null) {
            return "";
        }
        int len = s.length();
        if (len <= 1) {
            return s;
        }

        char[] arr = s.toCharArray();
        while (len > 1) { // assume a unique longest palindromic substring must exist
            for (int i = 0; i < s.length() - len + 1; i++) {
                int start = i;
                int end = i + len - 1;
                while (start < end) {
                    if (arr[start] == arr[end]) {
                        start++;
                        end--;
                    } else {
                        break;
                    }
                }
                if (start >= end) {
                    return s.substring(i, i + len);
                } // inner-while
            } // for
            len--;
        } // while

        return "";
    }
}
