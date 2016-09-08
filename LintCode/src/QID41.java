// http://www.lintcode.com/en/problem/maximum-subarray/
public class QID41 {
    public int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = 0; // sum from nums[0] to nums[i]
        int min = 0; // the min sum up to now
        int globalMax = Integer.MIN_VALUE; // the max sum of subarray up to now

        for (int n : nums) {
            sum += n;
            if (sum - min > globalMax) {
                globalMax = sum - min;
            }
            min = Math.min(min, sum);
        }

        return globalMax;
    }
}
