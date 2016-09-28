// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
public class QID298 {
    // reference: https://discuss.leetcode.com/topic/29205/simple-recursive-dfs-without-global-variable/2
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, 1);
    }

    // input the root node of subtree, the length of consecutive sequence starting with node
    // return the length of longest consecutive sequence
    private int helper(TreeNode node, int count) {
        if (node == null) {
            return 0;
        }
        // divide
        int left;
        if (node.left == null) {
            left = 0;
        } else {
            if (node.left.val - 1 == node.val) {
                left = helper(node.left, count + 1);
            } else {
                left = helper(node.left, 1);
            }
        }
        int right;
        if (node.right == null) {
            right = 0;
        } else {
            if (node.right.val - 1 == node.val) {
                right = helper(node.right, count + 1);
            } else {
                right = helper(node.right, 1);
            }
        }
        // conquer
        return Math.max(Math.max(left, right), count);
    }

}
