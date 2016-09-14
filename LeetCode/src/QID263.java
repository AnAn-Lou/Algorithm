// https://leetcode.com/problems/ugly-number/
public class QID263 {
    public boolean isUgly(int num) {

        if (num <= 0) {
            return false;
        }

        if (num == 1) {
            return true;
        }

        int[] divisor = {2, 3, 5};
        int[] remainder = new int[3];

        while (num != 1) {
            for (int i = 0; i < 3; i++) {
                remainder[i] = num % divisor[i];
            }

            if (remainder[0] != 0 && remainder[1] != 0 && remainder[2] != 0) {
                return false;
            }

            for (int i = 0; i < 3; i++) {
                if (remainder[i] == 0) {
                    num /= divisor[i];
                }
            }
        }

        return true;
    }
}
