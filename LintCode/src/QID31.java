// http://www.lintcode.com/en/problem/partition-array/
public class QID31 {
    public int partitionArray(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            while (nums[left] < k && left < right){
                left++;
            }
            while (nums[right] >= k && left < right){
                right--;
            }
            if (nums[left] >=k && nums[right] < k) {
                swap(nums, left, right);
            }
        }
        if (nums[left] >= k) {
            return left;
        } else if (nums[right] >= k){
            return right;
        } else {
            return right + 1;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
