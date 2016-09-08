// http://www.lintcode.com/en/problem/subarray-sum/
import java.util.ArrayList;
import java.util.HashMap;

public class QID138 {
    public ArrayList<Integer> subarraySum(int[] nums) {

        ArrayList<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        // initialization
        int sum = 0;
        map.put(0,0);

        // transition

        for (int i = 1; i <= nums.length; i++) {
            sum += nums[i - 1];
            if (map.containsKey(sum)) {
                result.add(map.get(sum));
                result.add(i - 1);
                return result;
            }
            map.put(sum, i);
        }

        return result;
    }
}
