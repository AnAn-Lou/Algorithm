import java.util.*;

// https://leetcode.com/problems/anagrams/
public class QID49 {

    // time O(n * mlogm), n = strs.length, m = avg(englis_word.length)
    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr); // O(mlogm)
            String sortedS = String.valueOf(arr);
            List<String> list;
            if (map.containsKey(sortedS)) {
                list = map.get(sortedS);
                list.add(s);
            } else {
                list = new ArrayList<>();
                list.add(s);
                map.put(sortedS, list);
            }
        }

        return new ArrayList<>(map.values());

    }

    /*
    // my solution time O(n^2 * m), n = strs.length, m = avg(englis_word.length)
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        if (strs == null || strs.length == 0) {
            return result;
        }

        boolean[] used = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            if (used[i]) { // already added to the result as an anagram of another word
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            used[i] = true;
            for (int j = i + 1; j < strs.length; j++) {
                if (used[j]) { // already added to the result as an anagram of another word
                    continue;
                }
                if (areAnagrams(strs[i], strs[j])) {
                    list.add(strs[j]);
                    used[j] = true;
                }
            }
            result.add(list);
        }

        return result;
    }

    private boolean areAnagrams(String word1, String word2) {

        if (word1 == null || word2 == null) {
            return false;
        }

        if (word1.length() != word2.length()) {
            return false;
        }

        char[] arr1 = word1.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : arr1) {
            int count;
            if (map.containsKey(ch)) {
                count = map.get(ch) + 1;
            } else {
                count = 1;
            }
            map.put(ch, count);
        }

        char[] arr2 = word2.toCharArray();
        for (char ch: arr2) {
            if (map.containsKey(ch)) {
                int count = map.get(ch);
                if (count == 0) {
                    return false;
                }
                map.put(ch, count - 1);
            } else {
                return false;
            }
        }

        return true;
    }
    */

}
