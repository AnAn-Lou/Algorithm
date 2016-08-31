// http://www.lintcode.com/en/problem/search-in-rotated-sorted-array/
public class QID62 {
    public int search(int[] A, int target) {

        if (A == null || A.length == 0) {
            return -1;
        }

        int low = 0;
        int high = A.length - 1;
        int mid;
        while (low + 1 < high) {

            mid = low + (high - low) / 2;

            if (A[mid] == target) {
                return mid;
            }

            if (A[mid] >= A[0]) {

                if (A[mid] > target && target >= A[0]) {
                    high = mid;
                } else {
                    low = mid;
                }

            } else {

                if (A[mid] < target && target <= A[A.length - 1]) {
                    low = mid;
                } else {
                    high = mid;
                }

            }
        }

        if (A[low] == target) {
            return low;
        } else if (A[high] == target){
            return high;
        } else {
            return -1;
        }
    }
}