public class QID97 {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    
    /*
    // Version 1: Traversal
    int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        helper(root, 0);
        return maxDepth;
    }
    
    // find the depth of each leaf from the initial root, 
    // and update maxDepth if necessary
    private void helper(TreeNode root, int depth) {
        
        if (root == null) {
            maxDepth = Math.max(maxDepth, depth);
            return;
        }
        
        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }
    */
    
    // Version 2: Divide & Conquer (better)
    // return the maxDepth of the subtree whose root is root
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        return Math.max(left, right) + 1;
    }    
    
}
