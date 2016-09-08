// http://www.lintcode.com/en/problem/two-sum/
import java.util.HashMap;

public class QID56 {
    public int[] twoSum(int[] numbers, int target) {

        if (numbers == null || numbers.length == 0) {
            return new int[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>(); // <number, index>
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                /*
                int[] result = new int[2];
                result[0] = map.get(target - numbers[i]) + 1;
                result[1] = i + 1;
                */
                // more concise
                int[] result = {map.get(numbers[i]) + 1, i + 1};
                return result;
            }
            map.put(numbers[i], i);
        }

        return new int[0];
    }
}
