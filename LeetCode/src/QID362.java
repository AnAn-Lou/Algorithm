import java.util.LinkedList;

// https://leetcode.com/problems/design-hit-counter/
public class QID362 { //HitCounter
    private LinkedList<Integer> queue;
    /** Initialize your data structure here. */
    public QID362() { //HitCounter
        queue = new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        queue.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            queue.poll();
        }
        return queue.size();
    }
}
