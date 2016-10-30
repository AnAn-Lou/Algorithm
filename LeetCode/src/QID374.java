//
public class QID374 {

    int correntNum = 5;

    private int guess(int num) {
        if (num == correntNum) {
            return 0;
        } else if (num > correntNum) {
            return 1;
        } else {
            return -1;
        }
    }

    public int guessNumber(int n) {

        int left = 1;
        int right = n;

        while (left + 1 < right) {
            int mid = left + (right - left)/2;
            int res = guess(mid);

            if (res == 0){
                return mid;
            } else if (res > 0){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (guess(left) == 0) {
            return left;
        } else if (guess(right) == 0) {
            return right;
        }

        return -1; // not found
    }
}
