// https://leetcode.com/problems/range-sum-query-mutable/
// reference: https://discuss.leetcode.com/topic/31599/java-using-binary-indexed-tree-with-clear-explanation
public class QID307 {
    private int[] bit;
    private int[] nums;

    public QID307(int[] nums) {
        this.nums = nums;

        // build binary indexed tree
        bit = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            init(i, nums[i]);
        }
    }

    private void init(int i, int val) {
        i++;
        while (i < bit.length) {
            bit[i] += val;
            i += (i & -i);
        }
    }

    void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        init(i, diff);
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }

    // return sum(num[0]...num[i - 1])
    private int getSum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += bit[i];
            i -= (i & -i);
        }
        return sum;
    }
}
