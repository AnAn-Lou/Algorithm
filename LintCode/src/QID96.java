// http://www.lintcode.com/en/problem/partition-list/
public class QID96 {
    public ListNode partition(ListNode head, int x) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode leftH = new ListNode(0); // append nodes < x
        ListNode leftP = leftH;
        ListNode rightH = new ListNode(0); // append nodes >= x
        ListNode rightP = rightH;

        while (head != null) {
            if (head.val < x) {
                leftP.next = head;
                leftP = leftP.next;
            } else {
                rightP.next = head;
                rightP = rightP.next;
            }
            head = head.next;
        }

        leftP.next = rightH.next;
        rightP.next = null;

        return leftH.next;
    }
}
