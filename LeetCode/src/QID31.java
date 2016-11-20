// https://leetcode.com/problems/next-permutation/
public class QID31 {
    // reference: https://discuss.leetcode.com/topic/2542/share-my-o-n-time-solution
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // leftward find the first number nums[i] which is smaller than its successor
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }

        // if such "first number" nums[i] exists
        // leftward find the first number nums[j] which is larget than nums[i]
        // swap nums[i] with nums[j]
        // reverse nums[i+1 ~ nums.length - 1]
        if (i != -1) {
            int j;
            for (j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    break;
                }
            }
            swap(nums, i, j);
            reverse(nums, i + 1, nums.length - 1);

        } else { // cases like nums = {5, 4, 3, 2, 1}
            reverse(nums, 0, nums.length - 1);
        }
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l ++;
            r --;
        }
    }
}
