// http://www.lintcode.com/en/problem/first-bad-version/
public class QID74 {
    // to find the smallest n such that isBadVersion(n) == true
    public int findFirstBadVersion(int n) {

        if (n <= 0) {
            return n;
        }
        
        int low = 0;
        int high = n;
        int mid;
        
        while (low + 1 < high) {
            
            mid = low + (high - low) / 2;
            
            if (SVNRepo.isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (SVNRepo.isBadVersion(low)) {
            return low;
        } else {
            return high;
        }
    }
}
