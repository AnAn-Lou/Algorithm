import java.util.TreeSet;

// https://leetcode.com/problems/contains-duplicate-iii/
public class QID220 {

    // TreeSet
    // E floor (E e);
    // Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> window = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            Integer floor = window.floor(nums[i]); // O(logn) find predecessor in a BST
            Integer ceiling = window.ceiling(nums[i]); // O(logn) find successor in a BST
            if ((floor != null && nums[i] - t <= floor) || (ceiling != null && nums[i] + t >= ceiling)) { // could avoid overflow in case nums[i] < = and t = Integer.MAX_VALUE
                return true;
            }
            window.add(nums[i]);
            if (i >= k) {
                window.remove(nums[i - k]);
            }
        }

        return false;
    }


    /* TLE
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        Set<Integer> window = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= t; j++ ) { // very inefficient when t is a big number
                if (window.contains(nums[i] + j) || window.contains(nums[i] - j)) {
                    return true;
                }
            }
            window.add(nums[i]);
            if (i >= k) {
                window.remove(new Integer(nums[i - k]));
            }
        }

        return false;
    }
    */
}
