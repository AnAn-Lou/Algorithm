import java.util.PriorityQueue;

// http://www.lintcode.com/en/problem/top-k-largest-numbers/
public class QID544 {
    public int[] topk(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        if (k > nums.length) {
            return nums;
        }


        PriorityQueue<Integer> queue = new PriorityQueue<>(k); // natural ordering
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (queue.peek() < nums[i]) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll();
        }

        return result;
    }
}
