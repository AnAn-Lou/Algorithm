// http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array/
// better solution from Jiuzhang: http://www.jiuzhang.com/solutions/find-minimum-in-rotated-sorted-array/
public class QID159 {
    public int findMin(int[] num) {
        
        if (num == null || num.length == 0) {
            return -1;
        }
        
        int len = num.length;
        
        // not rotated
        if (num[0] < num[len - 1]) {
            return num[0];
        }
        
        // rotated
        int low = 0;
        int high = len - 1;
        int mid;
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (num[mid] >= num[low]) {
                low = mid;
            } else if (num[mid] < num[low]) {
                high = mid;
            }
        }
        
        return Math.min(num[low], num[high]);
    }
}
