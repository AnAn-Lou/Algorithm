// https://leetcode.com/problems/range-sum-query-mutable/
// reference: https://discuss.leetcode.com/topic/31599/java-using-binary-indexed-tree-with-clear-explanation
// BIT
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

/* Segment Tree
 * still buggy
public class QID307 {

    TreeNode root;
    int n;
    int[] nums;

    public QID307(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            this.nums = nums;
            root = buildTree(nums, 0, n - 1);
        }
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(start);
        }

        int mid = start + (end - start) / 2;

        TreeNode left = buildTree(nums, start, mid);
        TreeNode right = buildTree(nums, mid + 1, end);

        TreeNode root = new TreeNode(left.val + right.val);
        root.left = left;
        root.right = right;

        return root;
    }

    void update(int pos, int val) {

        update(pos, val - nums[pos], 0, n - 1, root);
    }

    private void update(int pos, int diff, int start, int end, TreeNode node){
        if (start == end) {
            node.val += diff;
            return;
        }

        int mid = start + (end - start) / 2;
        if (pos <= mid) {
            update(pos, diff, start, mid, node.left);
        } else {
            update(pos, diff, mid + 1, end, node.right);
        }
    }

    public int sumRange(int l, int r) {
        return sumRange(l, r, 0, n - 1, root);
    }

    private int sumRange(int l, int r, int start, int end, TreeNode node) {
        if (l == start && r == end) {
            return node.val;
        }

        int mid = start + (end - start) / 2;

        int left = 0;
        if (l <= mid) {
            left = sumRange(l, mid, start, mid, node.left);
        }
        int right = 0;
        if (right >= mid + 1) {
            right = sumRange(mid + 1, r, mid + 1, end, node.right);
        }

        return left + right;
    }
}
*/

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