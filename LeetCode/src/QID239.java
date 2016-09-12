import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;

// https://leetcode.com/problems/sliding-window-maximum/
public class QID239 {
    /*
    // Version 1: heap O(nlogk + nk)
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 0) {
            int[] result = {};
            return result;
        }

        int[] result = new int[nums.length - k + 1];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        result[0] = minHeap.peek();

        for (int i = k; i < nums.length; i++) {
            minHeap.remove(new Integer(nums[i - k]));
            minHeap.offer(nums[i]);
            result[i - k + 1] = minHeap.peek();
        }

        return result;
    }
    */

    // Version 2: Deque
    // reference: https://discuss.leetcode.com/topic/19055/java-o-n-solution-using-deque-with-explanation
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            int[] result = {};
            return result;
        }

        int[] result = new int[nums.length - k + 1];

        Deque<Integer> queue = new ArrayDeque<>(); // store index of nums
        for (int i = 0; i < nums.length; i++) {

            // remove index out of window [i - k + 1, i] from the queue
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }
            // remove index i whose nums[i] has no chance to be 'max' in any future windows
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i] ) {
                queue.pollLast();
            }

            queue.offer(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[queue.peek()];
            }
        }

        return result;
    }

}
