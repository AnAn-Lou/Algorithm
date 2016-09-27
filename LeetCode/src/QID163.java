import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/missing-ranges/
public class QID163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }

        if (nums.length == 0) {
            result.add(missingRange(lower,upper));
            return result;
        }

        if (nums[0] > lower) {
            result.add(missingRange(lower, nums[0] - 1));
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i] + 1) {
                result.add(missingRange(nums[i] + 1, nums[i + 1] - 1));
            }
        }

        if (nums[nums.length - 1] < upper) {
            result.add(missingRange(nums[nums.length - 1] + 1, upper));
        }

        return result;
    }

    private String missingRange(int low, int up) {
        if (low == up) {
            return String.valueOf(low);
        }
        return low + "->" + up;
    }
}
