import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/unique-word-abbreviation/
public class QID288 {
    Map<String,Set<String>> abbrMap; // <abbr,list of words from the dictionary that have this abbr>
    public QID288(String[] dictionary) {
        abbrMap = new HashMap<>();
        for (String s : dictionary) {
            String abbr = getAbbr(s);
            if (!abbrMap.containsKey(abbr)) {
                Set<String> set = new HashSet<>();
                set.add(s);
                abbrMap.put(abbr, set);
            } else {
                Set<String> set = abbrMap.get(abbr);
                if (!set.contains(s)){
                    set.add(s);
                }
            }
        }
    }

    private String getAbbr(String s) {
        int len = s.length();
        if (len <= 2) {
            return s;
        }
        if (len == 3) {
            return s.charAt(0) + "1" + s.charAt(len - 1);
        }
        return s.charAt(0) + String.valueOf(len - 2) +s.charAt(len - 1);
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (abbrMap.containsKey(abbr)) {
            Set<String> set = abbrMap.get(abbr);
            if (set.size() > 1) { // at least one different word in the dictionary have the same abbr as the input word
                return false;
            }
            if (set.contains(word)) { // the input word is the same as the word in the dictionary which has the same abbr
                return true;
            }
            return false;
        } else {
            return true;
        }
    }
}
