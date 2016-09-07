// http://www.lintcode.com/en/problem/merge-k-sorted-lists/
import java.util.ArrayList;
import java.util.List;

public class QID104 {

    // Version 1: merge two by two
    public ListNode mergeKLists(List<ListNode> lists) {

        int size = lists.size();
        if (size == 0) {
            return null;
        }

        while (size > 1) {
            lists = MergeHelper(lists);
            size = lists.size();
        }

        return lists.get(0);
    }

    // merge lists two by two, and return the new list
    private List<ListNode> MergeHelper(List<ListNode> lists) {

        List<ListNode> newList = new ArrayList<>();

        int size = lists.size();
        for (int i = 0; i < size / 2; i++) {
            newList.add(merge2Lists(lists.get(i), lists.get(size - 1 - i)));
        }
        if (size % 2 != 0) {
            newList.add(lists.get(size / 2));
        }

        return newList;
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode d = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                d.next = l1;
                l1 = l1.next;
            } else {
                d.next = l2;
                l2 = l2.next;
            }
            d = d.next;
        }

        if (l1 != null) {
            d.next = l1;
        } else if (l2 != null) {
            d.next = l2;
        }

        return dummy.next;
    }

    /*
    // Version 2: Divide and Conquer
    // 两两merge
    public ListNode mergeKLists(List<ListNode> lists) {

        if (lists == null || lists.size() == 0) {
            return null;
        }

        return mergeHelper(lists, 0, lists.size() - 1);
    }

    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }

        // divide
        int mid = start + (end - start)/2;
        ListNode l1 = mergeHelper(lists, start, mid);
        ListNode l2 = mergeHelper(lists, mid + 1, end);

        // conquer
        return merge2Lists(l1, l2);
    }

    */
}
