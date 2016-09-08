// http://www.lintcode.com/en/problem/intersection-of-two-arrays/
import java.util.HashSet;
public class QID574 {
    // v1 hashset: time O(m + n); space O(m + n)
    // sort + two pointers: time O(nlogn + mlogm + n + m); space O(1)
    // sort + binary search: time O(nlogn + mlogn); space O(1)
    public int[] intersection(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0|| nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        HashSet<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            if (!set1.contains(num)){
                set1.add(num);
            }
        }

        HashSet<Integer> intersect = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num) && !intersect.contains(num)) {
                intersect.add(num);
            }
        }

        int[] result = new int[intersect.size()];
        int i = 0;
        for (int num : intersect) {
            result[i++] = num;
        }

        return result;
    }
}
