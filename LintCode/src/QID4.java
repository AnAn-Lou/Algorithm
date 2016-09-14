import java.util.ArrayList;
import java.util.List;

// http://www.lintcode.com/en/problem/ugly-number-ii/
// reference: http://www.jiuzhang.com/solutions/ugly-number-ii/
public class QID4 {
    public int nthUglyNumber(int n) {

        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        List<Integer> list = new ArrayList<>();
        list.add(1); // first ugly number

        int p2 = 0; // pointer
        int p3 = 0; // pointer
        int p5 = 0; // pointer
        for (int i = 1; i < n; i++) { // index
            int prev = list.get(i - 1);
            while (list.get(p2) * 2 <= prev) {
                p2++;
            }
            while (list.get(p3) * 3 <= prev) {
                p3++;
            }
            while (list.get(p5) * 5 <= prev) {
                p5++;
            }
            list.add(Math.min(Math.min(list.get(p2) * 2, list.get(p3) * 3),
                    list.get(p5) * 5));
        }
        return list.get(n - 1);
    }
}
