import java.util.Arrays;

// https://leetcode.com/problems/wiggle-sort-ii/
public class QID324 {

    // Solution 1
    // sort time O(nlogn) space O(n)
    public void wiggleSort(int[] nums) {

        Arrays.sort(nums);

        int[] sorted = new int[nums.length];

        int mid = (nums.length - 1) / 2;
        int right = nums.length - 1;
        // wiggle sort
        for (int i = 0; i < nums.length; i ++) {
            if (i % 2 == 0) {
                sorted[i] = nums[mid --];
            } else {
                sorted[i] = nums[right --];
            }
        }
        // copy
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sorted[i];
        }
    }

    /* need debug
    // Solution 2
    // quick select time O(n) space O(n)
    public static void wiggleSort(int[] nums) {
        // find median
        System.out.println("nums = " + Arrays.toString(nums));
        int medianIndex = findMedian(nums, 0, nums.length - 1);
        System.out.println("median index = " + medianIndex);
        // partition the array such that nums < median are at left side and nums > median are at right side
        partition(nums, 0, nums.length - 1, nums[medianIndex]);
        System.out.println("after partition nums = " + Arrays.toString(nums));
        // wiggle sort
        int[] sorted = new int[nums.length];
        int mid = (nums.length - 1) / 2;
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i ++) {
            if (i % 2 == 0) {
                sorted[i] = nums[mid --];
            } else {
                sorted[i] = nums[right --];
            }
        }
        // copy
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sorted[i];
        }
        System.out.println(Arrays.toString(nums));
    }

    // return the index of median
    private static int findMedian(int[] nums, int left, int right){
        int k = nums.length / 2; // find Kth smallest number in nums

        int pivot = nums[right];

        int partition = partition(nums, left, right, pivot);

        if (partition == k - 1) {
            return partition;
        } else if (partition > k - 1){
            return findMedian(nums, left, partition - 1);
        } else {
            return findMedian(nums, partition + 1, right);
        }
    }

    // [3, 5, 7, 4, 6, 5]  left = 0, right = 5, pivot = 5
    // return the index of partition point
    private static int partition(int[] nums, int leftBound, int rightBound, int pivot) {

        int lP = leftBound;
        int rP = rightBound - 1;

        while (true) {
            while (nums[lP] < pivot) {
                lP++;
            }
            while (lP < rP && nums[rP] >= pivot) {
                rP--;
            }
            if (lP < rP) {
                swap(nums, lP, rP);
            } else {
                break;
            }
        }

        swap(nums, lP, rightBound);
        return lP;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        QID324.wiggleSort(nums);
    }
    */
}
