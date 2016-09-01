import java.util.ArrayList;
import java.util.LinkedList;
// http://www.lintcode.com/en/problem/binary-tree-level-order-traversal/
public class QID69 {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int size = queue.size();
        while (!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            result.add(level);
            size = queue.size();
        }

        return result;
    }
}
