// http://www.lintcode.com/en/problem/two-sum-closest/
import java.util.Arrays;

public class QID533 {
    public int twoSumCloset(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        int minDiff = Integer.MAX_VALUE;
        while (left < right) {
            int diff = nums[left] + nums[right] - target;

            if (diff == 0) {
                return 0;
            }
            minDiff = Math.min(minDiff, Math.abs(diff));

            if (diff < 0) {
                left++;
            } else {
                right--;
            }
        }

        return minDiff;
    }

    /* Version 2: performance affected by the true value of minDiff
    public int twoSumCloset(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        HashSet<Integer> set = new HashSet<>();
        set.add(nums[0]);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < minDiff; j++) {
                if (set.contains(target - nums[i] - j) || set.contains(target - nums[i] + j)) {
                    minDiff = j;
                    if (minDiff == 0) {
                        return 0;
                    }
                }
            }
            set.add(nums[i]);
        }

        return minDiff;
    }
    */
}
