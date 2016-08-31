// http://www.lintcode.com/en/problem/validate-binary-search-tree/
public class QID95 {

    private class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {

        return helper(root).isValidBST;

    }

    private class ResultType {
        boolean isValidBST;
        long max;
        long min;

        ResultType(boolean isValidBST, long max, long min) {
            this.isValidBST = isValidBST;
            this.max = max;
            this.min = min;
        }
    }

    private ResultType helper(TreeNode root) {

        if (root == null) {
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        if (!left.isValidBST || !right.isValidBST) {
            return new ResultType(false, 0, 0);
        }

        if ((root.left != null && left.max >= root.val) || (root.right != null && right.min <= root.val)) {
            return new ResultType(false, 0, 0);
        }

        return new ResultType(true, Math.max(root.val, right.max), Math.min(root.val, left.min));
    }
}