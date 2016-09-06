// http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-list-ii/
public class QID113 {

    // one pointer
    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {

            if (prev.next.val == prev.next.next.val) { // duplicate
                int val = prev.next.val;
                while (prev.next.val == val) {
                    prev.next = prev.next.next;
                }
            } else { // not duplicate
                prev = prev.next;
            }

        }

        return dummy.next; // 而非head
    }

    /* two pointers
    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        while (prev.next != null) {

            ListNode curr = prev.next;
            int val = curr.val;

            if (curr.next != null && curr.next.val == val) {
                while (curr != null && curr.val == val) {
                    curr = curr.next;
                }
                prev.next = curr;
            } else {
                prev = prev.next;
            }
        }

        return dummy.next;
    }
    */
}
