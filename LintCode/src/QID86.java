import java.util.LinkedList;

/*
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * }
 */
// http://www.lintcode.com/en/problem/binary-search-tree-iterator/
public class QID86 {
    //@param root: The root of binary tree.

    TreeNode cur;
    LinkedList<TreeNode> stack;

    public QID86(TreeNode root) {
        cur = root;
        stack = new LinkedList<>();
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

    //@return: return next node
    public TreeNode next() {

        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        if (!stack.isEmpty()){
            TreeNode node = stack.pop();
            cur = node.right;
            return node;
        }

        return null;

    }
}