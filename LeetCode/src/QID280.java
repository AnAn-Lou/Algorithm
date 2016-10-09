import java.util.Arrays;

// https://leetcode.com/problems/wiggle-sort/
public class QID280 {

    // My solution time O(nlogn)
    public void wiggleSort(int[] nums) {

        if (nums == null || nums.length < 2) {
            return;
        }

        Arrays.sort(nums); // O(nlogn)

        if (nums.length == 2) {
            return;
        }

        for (int i = 2; i < nums.length; i = i + 2) {
            swap(nums, i - 1, i);
        }
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
