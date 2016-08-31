// http://www.lintcode.com/en/problem/balanced-binary-tree/
public class QID93 {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        
        return (helper(root) != -1);
    }
    
    // return -1 if the tree is not balanced,
    // otherwise return the depth of the tree
    private int helper(TreeNode root) {
        
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        // A tree is balanced if
        // both its left subtree and right subtree are balanced
        // and the depth difference <= 1
        if (left != -1 && right != -1 && Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        }
        
        return -1;
    }
    
}
