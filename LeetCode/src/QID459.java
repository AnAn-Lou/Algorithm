import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/repeated-substring-pattern/
public class QID459 {
    // From another perspectvie
    // From discuss: https://discuss.leetcode.com/topic/67992/java-simple-solution-with-explanation
    // O(kn), k = # of divisors of str.length()
    public boolean repeatedSubstringPattern(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        int len = str.length();

        for (int l = len / 2; l >= 1; l --) { // len of substrings
            if (len % l != 0) { // str cannot be divided into substrings with same length l
                continue;
            }
            Set<String> set = new HashSet<>();
            int i;
            for (i = 0; i < len / l; i ++) {
                String substr = str.substring(i * l, (i + 1) * l);
                if (!set.isEmpty() & set.add(substr)) break;
            }
            if (i == len / l) return true;
        }
        return false;
    }

    /*
    public boolean repeatedSubstringPattern(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        int len = str.length();

        for (int i = 2; i <= len; i ++) { // number of substrings
            if (len % i != 0) { // str cannot be divided into i substrings with same length
                continue;
            }
            int subLen = len / i;
            Set<String> set = new HashSet<>();
            int j;
            for (j = 0; j < i; j ++) {
                String substr = str.substring(j * subLen, (j + 1) * subLen);
                if (!set.isEmpty() & set.add(substr)) break; // & instead of &&
            }
            if (j == i) return true;
        }

        return false;
    }
    */

    public static void main(String[] args) {
        QID459 driver = new QID459();
        System.out.println(driver.repeatedSubstringPattern("")); // false
        System.out.println(driver.repeatedSubstringPattern("bb")); // true
        System.out.println(driver.repeatedSubstringPattern("abac")); // false
        System.out.println(driver.repeatedSubstringPattern("abcabc")); // true
        System.out.println(driver.repeatedSubstringPattern("ababab")); // true
        System.out.println(driver.repeatedSubstringPattern("abc")); // false
        System.out.println(driver.repeatedSubstringPattern("aaa")); // true
    }

}
