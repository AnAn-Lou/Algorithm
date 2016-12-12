import java.util.PriorityQueue;

// https://leetcode.com/problems/merge-k-sorted-lists/
public class QID23 {
    /*
    // Priority Queue
    // time O(nlogk) with n = total # of nodes in lists
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // min heap
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((ListNode l1, ListNode l2) -> Integer.compare(l1.val, l2.val));
        for (ListNode l : lists) {
            if (l != null) {
                minHeap.offer(l);
            }
        }

        // merge
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (minHeap.size() > 1) {
            tail.next = minHeap.poll();
            tail = tail.next;
            if (tail.next != null) {
                minHeap.offer(tail.next);
            }
        }

        tail.next = minHeap.peek();

        return dummy.next;
    }
    */

    // Divide and Conquer
    // time O(nlogk) with n = total # of nodes in lists
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        return mergeHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        // divide
        int mid = start + (end - start)/2;
        ListNode l1 = mergeHelper(lists, start, mid);
        ListNode l2 = mergeHelper(lists, mid + 1, end);

        // conquer
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {

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
}
