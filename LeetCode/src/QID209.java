// https://leetcode.com/problems/minimum-size-subarray-sum/
public class QID209 {
    public int minSubArrayLen(int s, int[] nums) {

        if (nums == null || nums.length == 0) { // no subarray available
            return 0;
        }

        int end = 0;
        int sum = 0; // sum from nums[start] to nums[end] inclusively
        int minLen = Integer.MAX_VALUE;
        for (int start = 0; start < nums.length; start++) {
            while (end < nums.length && sum < s) {
                sum += nums[end];
                end++;
            }
            if (sum >= s) {
                minLen = Math.min(minLen, end - start);
            }
            sum -= nums[start];
        }

        if (minLen == Integer.MAX_VALUE) {
            return 0;
        }
        return minLen;
    }
}
