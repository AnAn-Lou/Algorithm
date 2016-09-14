// http://www.lintcode.com/en/problem/rehashing/
public class QID129 {
    public ListNode[] rehashing(ListNode[] hashTable) {
        if (hashTable == null || hashTable.length == 0) {
            return hashTable;
        }

        int capacity = hashTable.length * 2;
        ListNode[] newTable = new ListNode[capacity];

        for (ListNode head : hashTable) {
            while (head != null) {
                int hash = hashCode(head.val, capacity);
                ListNode node = new ListNode(head.val);
                if (newTable[hash] != null) {
                    node.next = newTable[hash];
                }
                newTable[hash] = node;
                head = head.next;
            }
        }
        return newTable;
    }

    private int hashCode(int val, int capacity) {
        return (val % capacity + capacity) % capacity;
    }
}
