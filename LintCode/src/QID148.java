// http://www.lintcode.com/en/problem/sort-colors/
public class QID148 {
    public void sortColors(int[] nums) {

        if (nums == null || nums.length == 0) {
            return;
        }

        // sort 0 against 1 & 2
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (nums[left] == 0 && left < right) {
                left++;
            }
            while(nums[right] != 0 && left < right) {
                right--;
            }
            if (nums[left] != 0 && nums[right] == 0) {
                swap(nums, left, right);
            }
        }

        // sort 1 against 2
        left = nums[left] != 0 ? left : right;
        right = nums.length - 1;
        while (left < right) {
            while (nums[left] == 1 && left < right) {
                left++;
            }
            while(nums[right] == 2 && left < right) {
                right--;
            }
            if (nums[left] != 1 && nums[right] != 2) {
                swap(nums, left, right);
            }
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
