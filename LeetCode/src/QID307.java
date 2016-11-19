// https://leetcode.com/problems/range-sum-query-mutable/
/*
// BIT
// reference: https://discuss.leetcode.com/topic/31599/java-using-binary-indexed-tree-with-clear-explanation
//
// nums.length = n
// bit.length = n + 1: for the convenience of bit manipulation
//
// nums[] =                     0   1   2   3   4   5   6   7
// bit[] idx                0   1   2   3   4   5   6   7   8
// bit[] responsibility         1  1-2  3  1-4  5  5-6  7  1-8
//
// i & (-i): quickly get the last '1' bit of i
// i + i & (-i): quickly move to its successor
// i - i & (-i): quickly move to its predecessor
//
// build tree O(nlogn)
// update O(logn)
// rangeSum O(2logn)

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
*/

/* Segment Tree
 * reference: https://discuss.leetcode.com/topic/29918/17-ms-java-solution-with-segment-tree
 */
public class QID307 {

    private class TreeNode{
        int sum;
        int start;
        int end;
        TreeNode left;
        TreeNode right;
        TreeNode(int start, int end){
            this.start = start;
            this.end = end;
            sum = 0;
            left = null;
            right = null;
        }
    }

    TreeNode root;

    public QID307(int[] nums) {
        if (nums.length > 0) {
            root = buildTree(nums, 0, nums.length - 1);
        }
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        TreeNode root = new TreeNode(start, end);

        if (start == end) {
            root.sum = nums[start];
            return root;
        }

        int mid = start + (end - start) / 2;

        root.left = buildTree(nums, start, mid);
        root.right = buildTree(nums, mid + 1, end);

        root.sum = root.left.sum + root.right.sum;

        return root;
    }

    void update(int pos, int val) {

        update(pos, val, root);
    }

    private void update(int pos, int val, TreeNode root){

        if (root.start == root.end) {
            root.sum = val;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;

        if (pos <= mid) {
            update(pos, val, root.left);
        } else {
            update(pos, val, root.right);
        }

        root.sum = root.left.sum + root.right.sum;
    }

    public int sumRange(int l, int r) {
        return sumRange(l, r, root);
    }

    private int sumRange(int l, int r, TreeNode root) {
        if (l == root.start && r == root.end) {
            return root.sum;
        }

        int mid = root.start + (root.end - root.start) / 2;

        if (r <= mid) {
            return sumRange(l, r, root.left);
        } else if (l >= mid + 1) {
            return sumRange(l, r, root.right);
        } else {
            return sumRange(l, mid, root.left) + sumRange(mid + 1, r, root.right);
        }
    }
}



/* Segment Tree
public class QID307 {
    int[] tree;
    int n;

    public QID307(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        for (int i = 2 * n - 1, j = n - 1; j >= 0; i--, j--) {
            tree[i] = nums[j];
        }
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    void update(int pos, int val) {
        int i = pos + n;
        int diff = val - tree[i];
        while (i > 0) {
            tree[i] += diff;
            i = i / 2;
        }
    }

    public int sumRange(int l, int r) {
        l += n;
        r += n;
        int sum = 0;
        while (l <= r) {
            if (l % 2 == 1) { // left pointer points to a right child
                sum += tree[l];
                l ++;
            }
            if (r % 2 == 0) { // right pointer points to a left child
                sum += tree[r];
                r --;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}
*/

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);