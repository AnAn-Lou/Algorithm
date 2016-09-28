import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class QID297 {
    // reference: https://discuss.leetcode.com/topic/28029/easy-to-understand-java-solution

    private static final String NILL = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    // pre-order traverse
    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NILL);
            sb.append(",");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        buildString(root.left, sb);
        buildString(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // empty tree
        if (data.length() == 0) {
            return null;
        }
        List<String> list = Arrays.asList(data.split(","));
        list = new ArrayList<>(list); // UnSupportedOperationException http://blog.csdn.net/wangjian5748/article/details/577797

        return buildTree(list);
    }

    private TreeNode buildTree(List<String> list){
        // list exhausts
        if (list.size() == 0) {
            return null;
        }
        // NILL
        String val = list.get(0);
        list.remove(0);
        if (val.equals(NILL)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = buildTree(list);
        root.right = buildTree(list);
        return root;
    }
}
