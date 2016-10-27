// https://leetcode.com/problems/closest-binary-search-tree-value/
public class QID270 {
    public int closestValue(TreeNode root, double target) {

        if (root == null) {
            return 0; // or throw Exception
        }

        int closestValue = root.val;
        double minDiff = Math.abs(root.val - target);

        TreeNode curr = root;

        while (curr != null) {
            if (Math.abs(curr.val - target) < minDiff) {
                minDiff = Math.abs(curr.val - target);
                closestValue = curr.val;
            }
            if (curr.val == target) {
                return curr.val;
            } else if (curr.val > target) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        return closestValue;
    }
}
