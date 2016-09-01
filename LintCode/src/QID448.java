// http://www.lintcode.com/en/problem/inorder-successor-in-binary-search-tree/
public class QID448 {

    // Version 1
    // find a node, which has the smallest val that node.val > p.val
    // return the node if found, return null otherwise
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        if (root == null || p == null) {
            return null;
        }

        if (root.val > p.val){
            TreeNode left = inorderSuccessor(root.left, p);
            if (left != null) {
                return left;
            } else {
                return root;
            }
        } else {
            TreeNode right = inorderSuccessor(root.right, p);
            if (right != null) {
                return right;
            } else {
                return null;
            }
        }
    }

    // Version
    /*
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        if (root == null || p == null) {
            return null;
        }

        // if p has right child
        // then find the left-most leaf of the right subtree of p
        if (p.right != null) {
            TreeNode node = p.right;
            while(node.left != null) {
                node = node.left;
            }
            return node;
        }

        // if p doesn't have right child
        // then find the parent node at certain leve,
        // who must be the left child of its own parent
        TreeNode node = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (node != null || !stack.isEmpty()) {

            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                if (prev == p) {
                    return node;
                }
                prev = node;
                node = node.right;
            }
        }
        return null;
    }
    */

}
