// http://www.lintcode.com/en/problem/search-insert-position/

public class QID60 {
      
      // find the smallest index i that A[i] >= target
      public int searchInsert(int[] A, int target) {
        
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int low = 0;
        int high = A.length - 1;
        int mid;
        
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (A[low] >= target) {
            return low;
        } else if (A[high] >= target){
            return high;
        } else {
            return A.length;
        }

    }

}
