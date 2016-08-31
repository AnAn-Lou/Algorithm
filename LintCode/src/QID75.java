// http://www.lintcode.com/en/problem/find-peak-element/
public class QID75{
    public int findPeak(int[] A) {
        
        if (A == null || A.length < 3) {
            return -1;
        }
        
        int low = 0;
        int high = A.length - 1;
        int mid;
        
        while (low + 1 < high) {
            
            mid = low + (high - low) / 2;
            
            if (A[mid - 1] < A[mid] && A[mid] < A[mid + 1]) {
                low = mid;
            } else if (A[mid - 1] < A[mid] && A[mid] > A[mid + 1]) {
                return mid;
            } else {
                high = mid;
            }
        }
        
        if (A[low - 1] < A[low] && A[low] > A[low + 1]) {
            return low;
        } else {
            return high;
        }
    }   
}
