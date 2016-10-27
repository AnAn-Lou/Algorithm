// https://leetcode.com/problems/first-bad-version/
public class QID278 {
    public int firstBadVersion(int n) {

        int start = 1, end = n;

        while (start < Integer.MAX_VALUE && start + 1 < end) { // for case n = Integer.MAX_VALUE && fistBadVersion = n
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        if (isBadVersion(start)) {
            return start;
        }
        return end;
    }

    public static void main(String[] args){
        QID278 qid = new QID278();
        System.out.println(qid.firstBadVersion(2147483647));
    }

    private boolean isBadVersion(int n){
        int firstBadVersion = 2147483647;
        if (n >= firstBadVersion) {
            return true;
        }
        return false;
    }
}
