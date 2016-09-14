import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

// http://www.lintcode.com/en/problem/lru-cache/

/*
// Version 1: LinkedHashMap
public class QID134 {

    private LinkedHashMap<Integer, Integer> linkedMap;

    // @param capacity, an integer
    public QID134(final int capacity) {
        linkedMap = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    // @return an integer
    public int get(int key) {
        if (linkedMap.containsKey(key)) {
            return linkedMap.get(key);
        } else {
            return -1;
        }
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        linkedMap.put(key, value);
    }

    public static void main(String[] args) {
        QID134 qid = new QID134(2);
        qid.set(2, 1);
        qid.set(1, 1);
        qid.get(2);
        qid.set(4, 1);
        qid.get(1);
        qid.get(2);
    }
}
*/

/*
// Version 2: LinedList + HashMap
public class QID134 {

    private LinkedList<Integer> list;
    private HashMap<Integer, Integer> map;
    private int capacity;

    // @param capacity, an integer
    public QID134(int capacity) {
        this.capacity = capacity;
        list = new LinkedList<>();
        map = new HashMap<>();
    }

    // @return an integer
    public int get(int key) {
        if (map.containsKey(key)) {
            list.remove(new Integer(key));
            list.addLast(key);
            return map.get(key);
        } else {
            return -1;
        }
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (map.get(key) != null) { // existing
            list.remove(new Integer(key));
            list.addLast(key);
            map.put(key, value);
        } else { // not existing
            if (list.size() == capacity) {
                map.remove(list.get(0));
                map.put(key, value);
                list.removeFirst();
                list.addLast(key);
            } else {
                map.put(key, value);
                list.addLast(key);
            }
        }
    }
    public static void main(String[] args) {
        QID134 qid = new QID134(2);
        qid.set(2, 1);
        qid.set(1, 1);
        qid.get(2);
        qid.set(4, 1);
        qid.get(1);
        qid.get(2);
    }
}
*/

// Version 3: ListNode + HashMap
public class QID134 {

    private class ListNode {
        int key;
        int val;
        ListNode next;
        ListNode prev;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            next = null;
            prev = null;
        }
    }

    private Map<Integer, ListNode> map; // <key, node>
    private ListNode dummy;
    private ListNode tail;
    private int capacity;

    // @param capacity, an integer
    public QID134(int capacity) {
        map = new HashMap<>();
        dummy = new ListNode(-1, -1);
        tail = dummy;
        this.capacity = capacity;
    }

    // @return an integer
    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            moveToTail(node);
            return node.val;
        } else {
            return -1;
        }
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            moveToTail(node);
        } else {
            if (map.size() == capacity) {
                removeFirst();
            }
            addLast(key, value);
        }
    }

    private void moveToTail(ListNode node) {
        if (node != tail) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            tail.next = node;
            node.next = null;
            node.prev = tail;
            tail = node;
        }
    }

    // remove the first element in the list
    // remove & the corresponding element in the map
    private void removeFirst() {
        if (dummy.next != null) {
            map.remove(dummy.next.key); // this is why we keep key in Node
            if (dummy.next == tail) {
                dummy.next = null;
                tail = dummy;
            } else {
                dummy.next = dummy.next.next;
                dummy.next.prev = dummy;
            }
        }
    }

    // add a new node to the end of the list
    // add a corresponding element to the map
    private void addLast(int key, int value){
        ListNode node = new ListNode(key, value);
        map.put(key, node);
        tail.next = node;
        node.prev = tail;
        tail = node;
    }
}
