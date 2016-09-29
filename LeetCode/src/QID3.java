import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class QID3 {
    // version 1: sliding window
    // time: O(2n), space: O(min(m,n)), n = s.length(), m = size of charSet used
    /*
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] arr = s.toCharArray();
        int l = 0; // left bound, inclusively
        int r = 0; // right bound, inclusively
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        while (l < arr.length && r < arr.length) {
            if (set.contains(arr[r])) {
                set.remove(arr[l]);
                l++;
            } else {
                set.add(arr[r]);
                r++;
                maxLen = Math.max(maxLen, r - l);
            }
        }
        return maxLen;
    }
    */

    // version 2: sliding window (optimized)
    // time: O(n), space: O(min(m, n)), n = s.length(), m = size of charSet used
    // optimization: intead of using a set to record who is in the window, use a hashmap to record the most recent presence p, and move pointer l to p + 1 (skipping elements)
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] arr = s.toCharArray();
        int l = 0; // left bound, inclusively
        int r = 0; // right bound, inclusively
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        for (; r < arr.length; r++) {
            if (map.containsKey(arr[r])) {
                int tmp = map.get(arr[r]);
                l = Math.max(tmp + 1, l); // attention: items at the left side of left bound is never removed
            }
            map.put(arr[r],r);
            maxLen = Math.max(maxLen, r - l + 1);

        }
        System.out.println("l: " + l + ", r: " + r);
        return maxLen;
    }
}
