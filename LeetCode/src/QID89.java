import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/gray-code/
// reference: http://bangbingsyb.blogspot.com/2014/11/leetcode-gray-code.html
public class QID89 {

    public List<Integer> grayCode(int n) {

        List<Integer> result = new ArrayList<>();

        if (n == 0) {
            result.add(0);
            return result;
        }

        List<Integer> tmp = grayCode(n - 1);
        for (int t : tmp) {
            result.add(t);
        }
        for (int i = tmp.size() - 1; i >= 0; i-- ) {
            result.add(tmp.get(i) + (int) Math.pow(2, n - 1));
        }

        return result;
    }
}
