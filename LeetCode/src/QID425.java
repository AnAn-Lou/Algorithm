import java.util.*;

// https://leetcode.com/problems/word-squares/
public class QID425 {

    public static void main(String[] args){
        QID425 qid = new QID425();
        String[] words = {"area","lead","wall","lady","ball"};
        qid.wordSquares(words);
    }

    // My solution: hashmap + backtracking
    public List<List<String>> wordSquares(String[] words) {

        List<List<String>> result = new ArrayList<>();
        // border cases
        if (words == null || words.length == 0) {
            return result;
        }
        // build prefix hashmap
        int len = words[0].length();
        List<HashMap<String, List<String>>> hashMaps = new ArrayList<>(len - 1); // <prefix, word>
        for (int i = 0; i < len - 1; i++) {
            hashMaps.add(new HashMap<String, List<String>>());
        }

        for (String word : words) {
            for (int i = 0; i < len - 1; i++) {
                String prefix = word.substring(0, i + 1);
                List<String> prefixWordsList;
                if (hashMaps.get(i).containsKey(prefix)) {
                    prefixWordsList = hashMaps.get(i).get(prefix);
                } else {
                    prefixWordsList = new ArrayList<>();
                    hashMaps.get(i).put(prefix, prefixWordsList);
                }
                prefixWordsList.add(word);
            }
        }

        // backtrack to find word squares
        List<String> list = new ArrayList<>();
        Set<String> used = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            list.add(words[i]);
            helper(result, list, hashMaps, len);
            list.clear();
        }

        return result;
    }

    private void helper(List<List<String>> result,
                        List<String> list,
                        List<HashMap<String, List<String>>> hashMaps,
                        int len){
        // exit
        if (list.size() == len) {
            result.add(new ArrayList<>(list));
            return;
        }

        // build prefix
        int curr = list.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < curr; i++) {
            sb.append(list.get(i).charAt(curr));
        }
        String prefix = sb.toString();
        // get prefixWordsList
        HashMap<String, List<String>> map = hashMaps.get(curr - 1);
        if (!map.containsKey(prefix)) {
            return;
        }
        List<String> prefixWordsList = map.get(prefix);
        // try each word
        for (String word : prefixWordsList) { // multiple use of the same word is allowed
            list.add(word);
            helper(result, list, hashMaps, len);
            list.remove(list.size() - 1);
        }
    }
}
