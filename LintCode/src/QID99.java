// http://www.lintcode.com/en/problem/reorder-list/
public class QID99 {
    public void reorderList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = findMid(head);
        ListNode right = mid.next;
        mid.next = null;

        right = reverse(right);

        interleavingMerge(head, right);

    }

    private ListNode findMid(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        while (head != null) {

            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }

        return prev;
    }

    private ListNode interleavingMerge(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode d = dummy;
        while (l2 != null) {
            d.next = l1;
            l1 = l1.next;
            d = d.next;

            d.next = l2;
            l2 = l2.next;
            d = d.next;
        }

        if (l1 != null) {
            d.next = l1;
        }

        return d.next;
    }
}
