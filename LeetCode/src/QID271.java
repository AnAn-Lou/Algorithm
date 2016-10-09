import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/encode-and-decode-strings/
public class QID271 {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {

        if (strs == null || strs.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (String s : strs) {
            sb.append(s.length());
            sb.append("/");
            sb.append(s);
        }

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {

        List<String> strs = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return strs;
        }

        int index = s.indexOf("/");
        while (index != -1) {
            int len = Integer.parseInt(s.substring(0, index));
            strs.add(s.substring(index + 1, index + 1 + len));
            s = s.substring(index + 1 + len);
            index = s.indexOf("/");
        }

        return strs;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
