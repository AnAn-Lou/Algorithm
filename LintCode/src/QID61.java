// http://www.lintcode.com/en/problem/search-for-a-range/
public class QID61 {
    public int[] searchRange(int[] A, int target) {
        
        int[] result = {-1, -1};
        
        if (A == null || A.length == 0) {
            return result;
        }
        
        // find the smallest i start such that A[i] == target
        int low = 0;
        int high = A.length - 1;
        int mid;
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (A[mid] >= target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        int i;
        if (A[low] == target) {
            i = low;
        } else if (A[high] == target) {
            i = high;
        } else {
            return result;
        }
        
        // find the largest j end such that A[j] == target
        low = i;
        high = A.length - 1;
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (A[mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        int j;
        if (A[high] == target) {
            j = high;
        } else if (A[low] == target) {
            j = low;
        } else {
            return result;
        }
        
        // result
        result[0] = i;
        result[1] = j;
        
        return result;
    }
}
