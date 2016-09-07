// http://www.lintcode.com/en/problem/reverse-linked-list-ii/
public class QID36 {
    public ListNode reverseBetween(ListNode head, int m , int n) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // find m - 1
        ListNode curr = dummy; // 从dummy开始，方便算步数
        for (int i = 1; i < m; i++) {
            curr = curr.next;
        }
        ListNode m_prev = curr;

        // find m
        curr = curr.next;
        ListNode m_node = curr;

        // reverse between
        ListNode prev = null;
        ListNode temp;
        for (int i = m; i <= n; i++) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        ListNode n_node = prev;
        ListNode n_next = curr;

        // connect three parts
        m_prev.next = n_node;
        m_node.next = n_next;

        return dummy.next;
    }
}
