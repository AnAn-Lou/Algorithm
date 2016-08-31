// http://www.lintcode.com/en/problem/strstr/
public class QID13 {  
    public int strStr(String source, String target) {

        if (source == null || target == null) {
            return -1;
        }
        
        int lenS = source.length();
        int lenT = target.length();
        
        for (int i = 0; i < lenS - lenT + 1; i++) {
            int j;
            for (j = 0; j < lenT; j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }
            if (j == lenT) {
                return i;
            }
        }
        
        return -1;
    }
}
