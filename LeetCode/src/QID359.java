import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// https://leetcode.com/problems/logger-rate-limiter/
public class QID359 {

    // Queue + Set
    // time O(1) space O(10)
    private class Message {
        int timestamp;
        String str;

        Message(int timestamp, String str) {
            this.timestamp = timestamp;
            this.str = str;
        }
    }

    LinkedList<Message> queue;
    Set<String> set;

    // Initialize your data structure here.
    public QID359() {
        queue = new LinkedList<>();
        set = new HashSet<>();
    }

    // Returns true if the message should be printed in the given timestamp, otherwise returns false.
    // If this method returns false, the message will not be printed.
    // The timestamp is in seconds granularity.
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!queue.isEmpty() && queue.peek().timestamp + 10 <= timestamp) {
            Message toRemove = queue.poll();
            set.remove(toRemove.str);
        }

        if (set.contains(message)) {
            return false;
        }

        queue.offer(new Message(timestamp, message));
        set.add(message);

        return true;
    }

    /*
    // HashMap
    // time O(1) space O(n)
    HashMap<String, Integer> map;

    // Initialize your data structure here.
    public QID359() {
        map = new HashMap<>();
    }

    // Returns true if the message should be printed in the given timestamp, otherwise returns false.
    // If this method returns false, the message will not be printed.
    // The timestamp is in seconds granularity.
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (map.containsKey(message) && map.get(message) + 10 > timestamp) {
            return false;
        }
        map.put(message, timestamp);
        return true;
    }
    */
}
