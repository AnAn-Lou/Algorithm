// http://www.lintcode.com/en/problem/convert-sorted-list-to-balanced-bst/
public class QID106 {
    ListNode current;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        current = head;
        int size = getListSize(head);
        return helper(size);
    }

    // return the size of the list
    private int getListSize(ListNode head){
        if (head == null) {
            return 0;
        }

        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }

    // return the root of the tree constructed from a list of size
    // and move the current pointer to the next position in the list
    private TreeNode helper(int size) {
        if (size == 0) {
            return null;
        }

        TreeNode left = helper(size / 2);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = helper(size - size / 2 - 1);
        root.left = left;
        root.right = right;

        return root;
    }
}
