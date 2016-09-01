// http://www.lintcode.com/en/problem/binary-tree-maximum-path-sum/
public class QID94 {

    int globalMax;
    /**
     * @param root: The root of binary tree.
     * @return An integer.
     */
    public int maxPathSum(TreeNode root) {

        if (root == null) {
            return 0;
        }

        globalMax = Integer.MIN_VALUE;
        helper(root);

        return globalMax;
    }

    // find the maximum single path (root -> any) of root.left; could be null
    // find the maximum single path (root -> any) of root.right; could be null
    // calculate the maximum path (any -> any) of root; at least contains root
    // update globalMax if maximum path sum > globalMax
    // return maximum single path of root; could be null
    private int helper(TreeNode root) {

        if (root == null) {
            return 0;
        }

        // Divide
        int left = helper(root.left);
        left = left > 0 ? left : 0;
        int right = helper(root.right);
        right = right > 0 ? right : 0;

        // update globalMax
        globalMax = Math.max(globalMax, left + right + root.val);

        // return maximum root -> anynode path sum
        return Math.max(left, right) + root.val;
    }
}