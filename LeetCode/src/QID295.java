import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-median-from-data-stream/
public class QID295 {

    // maintain following rules all the time
    // rule 1: 0 =< maxHeap.size() - minHeap.size() <= 1
    // rule 2: each element in maxHeap <= any element in minHeap
    // so the median is maxHeap.peek() or (maxHeap.peek() + minHeap.peek()) * 1.0 / 2

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public QID295(){
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    // Adds a number into the data structure.
    // O(logn)
    public void addNum(int num) {
        if (maxHeap.size() == 0) {
            maxHeap.offer(num);

        } else if (maxHeap.size() == minHeap.size()) { // then increase maxHeap's size
            if (num > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }

        } else { // then increase minHeap's size
            if (num >= maxHeap.peek()) {
                minHeap.offer(num);
            } else {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            }
        }
    }

    // Returns the median of current data stream
    // O(1)
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) * 1.0 / 2;
        } else {
            return maxHeap.peek();
        }
    }
}

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
