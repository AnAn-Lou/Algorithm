// https://leetcode.com/problems/plus-one-linked-list/
public class QID369 {

    // Version 2: Two Pointers
    // Discuss: https://discuss.leetcode.com/topic/49603/two-pointers-java-solution-o-n-time-o-1-space
    // i stands for the most significant digit to be incremented
    // dummy node deal with cases like 999
    public ListNode plusOne(ListNode head) {

        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode i = dummy;
        ListNode j = dummy;

        while (j != null) {
            i = j;
            while (j.next != null && j.next.val == 9) {
                j = j.next;
            }
            j = j.next;
        }

        i.val++;
        while (i.next != null) {
            i = i.next;
            i.val = 0;
        }

        if (dummy.val != 0) {
            return dummy;
        } else {
            return dummy.next;
        }
    }


// Version 1: two reverses
// time O(3n); space O(1)
/*
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return head;
        }

        // reverse
        head = reverseLinkedList(head);

        // plus one
        if (head.val != 9) {
            head.val ++;
        } else {
            boolean overflow = true;
            ListNode curr = head;
            curr.val = 0;

            while (curr.next != null) {
                curr = curr.next;
                if (curr.val != 9) {
                    curr.val ++;
                    overflow = false;
                    break;
                } else {
                    curr.val = 0;
                    overflow = true;
                }
            }

            if (overflow) {
                curr.next = new ListNode(1);
            }
        }

        // reverse
        head = reverseLinkedList(head);

        return head;
    }

    private ListNode reverseLinkedList(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode temp = head.next;

        while (curr != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }
*/
}
