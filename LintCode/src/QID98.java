// http://www.lintcode.com/en/problem/sort-list/
public class QID98 {

    // Version 1: merge sort
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        return mergeSort(head);
    }

    private ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(0);
        ListNode d = dummy;

        while (left != null && right != null) {

            if (left.val < right.val) {
                d.next = left;
                left = left.next;
            } else {
                d.next = right;
                right = right.next;
            }
            d = d.next;
        }

        if (left != null) {
            d.next = left;
        }
        if (right != null) {
            d.next = right;
        }

        return dummy.next;

    }

    private ListNode mergeSort(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMid(head);
        ListNode right = mid.next;
        mid.next = null;

        // Divide
        head = mergeSort(head);
        right = mergeSort(right);

        // Conquer
        return merge(head, right);
    }

    private ListNode findMid(ListNode head){

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
}
