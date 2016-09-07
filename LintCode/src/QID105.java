// http://www.lintcode.com/en/problem/copy-list-with-random-pointer/
public class QID105 {
        /*
    // Version 1: HashMap space O(n)
    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null) {
            return head;
        }

        // copy nodes
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>(); // <initialNode, copiedNode>
        RandomListNode p = head;
        while (p != null) {
            RandomListNode newNode = new RandomListNode(p.label);
            map.put(p, newNode);
            p = p.next;
        }

        // copy links
        p = head;
        while (p != null) {
            RandomListNode newNode = map.get(p);
            newNode.next = map.get(p.next);
            newNode.random = map.get(p.random);
            p = p.next;
        }

        return map.get(head);
    }
    */

    // Version 2: space O(1)
    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null) {
            return head;
        }

        // copy nodes
        RandomListNode p = head;
        while (p != null) {

            RandomListNode newNode = new RandomListNode(p.label);
            newNode.next = p.next;
            p.next = newNode;
            p = p.next.next;
        }

        // copy random pointers
        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        // seperate lists
        RandomListNode newHead = head.next;
        p = head;
        RandomListNode q = newHead;
        while (p != null) {
            p.next = p.next.next;
            p = p.next;
            if (q.next != null) {
                q.next = q.next.next;
            } else {
                q.next = null;
            }
            q = q.next;
        }

        return newHead;
    }
}
