import java.util.List;

// https://leetcode.com/problems/valid-word-square/
public class QID422 {
    public boolean validWordSquare(List<String> words) {
        if (words == null) return false;

        if (words.size() == 0) return true;

        for (int i = 0; i < words.size(); i++) { // row
            for (int j = 0; j < words.get(i).length(); j++) { // column
                if (words.size() < j + 1 ||
                        words.get(j).length() < i + 1 ||
                        words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
