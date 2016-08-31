// http://www.lintcode.com/en/problem/first-position-of-target/
public class QID14 {

    // find the smallest index i such that nums[i] == target
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int low = 0;
        int high = nums.length - 1;
        int mid;
        
        while (low + 1 < high) {
            
            mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (nums[low] == target) {
            return low;
        } else if (nums[high] == target) {
            return high;
        } else {
            return -1;
        }
    }
}
