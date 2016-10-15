// https://leetcode.com/problems/decode-string/
public class QID394 {

    public static void main(String[] args) {
        QID394 qid = new QID394();
        System.out.println(qid.decodeString("3[a2[c]]"));


    }

    public String decodeString(String s) {

        if (s == null || s.length() < 4) { // 3[a]
            return s;
        }

        StringBuilder sb = helper(s);

        return sb.toString();
    }

    // s is always the unprocessed part of string
    private StringBuilder helper(String s){

        StringBuilder sb = new StringBuilder();

        while (s.length() > 0) {

            int index_left = s.indexOf('[');
            if (index_left == -1) { // [ not found
                sb.append(s);
                return sb;
            }

            // [ found
            if (index_left > 1) {
                sb.append(s.substring(0, index_left - 1));
            }

            int repeat = s.charAt(index_left - 1) - 48;
            s = s.substring(index_left);
            int index_right = findMatchRightParenthesis(s);

            String s_tmp = helper(s.substring(1, index_right)).toString();
            for (int i = 0; i < repeat; i++) {
                sb.append(s_tmp);
            }

            s = s.substring(index_right + 1);
        }

        return sb;
    }


    // s[0] == '['
    // return the index of ']' that matches s[0]
    private int findMatchRightParenthesis(String s){

        int len = s.length(); // >= 3

        int count = 1; // # of left parenthesis up to now
        for (int i = 1; i < len; i++) {
            int ch = s.charAt(i);
            if (ch == '[') {
                count ++;
            } else if (ch == ']'){
                count --;
                if (count == 0) {
                    return i;
                }
            }
        }

        return -1; // not found; illegal input s
    }
}
