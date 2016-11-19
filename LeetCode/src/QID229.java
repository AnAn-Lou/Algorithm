import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/majority-element-ii/
public class QID229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int m1 = 0; // candidate
        int m2 = 0; // candidate
        int c1 = 0; // count
        int c2 = 0; // count

        // first pass
        for (int n : nums) {
            if (n == m1) {
                c1 ++;
            } else if (n == m2) {
                c2 ++;
            } else if (c1 == 0) {
                m1 = n;
                c1 = 1;
            } else if (c2 == 0){
                m2 = n;
                c2 = 1;
            } else {
                c1 --;
                c2 --;
            }
        }

        // second pass
        c1 = 0;
        c2 = 0;
        for (int n : nums) {
            if (n == m1) {
                c1 ++;
            } else if (n == m2){
                c2 ++;
            }
        }

        if (c1 > nums.length / 3) {
            result.add(m1);
        }
        if (c2 > nums.length / 3) {
            result.add(m2);
        }

        return result;
    }
}
