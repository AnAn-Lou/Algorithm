import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
public class QID159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {

        int k = 2;

        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }

        Map<Character,Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        int result = 0; // global max
        int count = 0; // current sum of values of map

        for (int i = 0; i < arr.length; i++) {
            if (map.size() < k) { // less than k distinct chars
                if (map.containsKey(arr[i])) {
                    map.put(arr[i], map.get(arr[i]) + 1);
                } else {
                    map.put(arr[i], 1);
                }
                count++;
                result = Math.max(result, count);
            } else {

                if (map.containsKey(arr[i])) {
                    map.put(arr[i], map.get(arr[i]) + 1);
                    count++;
                    result = Math.max(result, count);
                } else {
                    map.put(arr[i], 1);
                    count++;
                    while (map.size() > k) {
                        int tmp = map.get(arr[i - count + 1]) - 1;
                        if (tmp == 0) {
                            map.remove(arr[i - count + 1]);
                        } else {
                            map.put(arr[i - count + 1], tmp);
                        }
                        count--;
                    } // while
                }
            }
        } // for
        return result;
    }
}
