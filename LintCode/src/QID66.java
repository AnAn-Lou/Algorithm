// http://www.lintcode.com/en/problem/binary-tree-preorder-traversal/

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class QID66 {
    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    
    /*
    // Version 1: Traversal
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        
        ArrayList<Integer> result = new ArrayList<>();
        
        helper(root, result);
        
        return result;
    }
    
    private void helper(TreeNode root, ArrayList<Integer> result) {
        
        if (root == null) {
            return;
        }
        
        result.add(root.val);
        helper(root.left, result);
        helper(root.right, result);
    }
    */
    
    /*
    // Version 2: Divide and Conquer
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        
        ArrayList<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        result.add(root.val); 
        
        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);
        
        result.addAll(left);
        result.addAll(right);
        
        return result;
    }
    */
    
    
    // Version 3: Iteration
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        
        ArrayList<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        TreeNode cur = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        
        while (cur != null || !stack.isEmpty()) {
            
            while (cur != null) {
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            
            if (!stack.isEmpty()) {
                cur = stack.pop().right;
            }
        }
        
        return result;
    }  
    
}
