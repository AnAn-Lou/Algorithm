import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
public class QID373 {
    // visulize as an k * k matrix: https://discuss.leetcode.com/topic/50450/slow-1-liner-to-fast-solutions
    // another visualization: https://discuss.leetcode.com/topic/50885/simple-java-o-klogk-solution-with-explanation
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return result;
        }
        PriorityQueue<Triplet> minHeap = new PriorityQueue<>();
        for (int j = 0; j < Math.min(k, nums2.length); j++) {
            minHeap.offer(new Triplet(nums1[0] + nums2[j], 0, j));
        }

        int numOfPairs = Math.min(k, nums1.length * nums2.length);
        for (int m = 0; m < numOfPairs; m++) { // k smallest pairs
            Triplet t = minHeap.poll();

            result.add(new int[]{nums1[t.i], nums2[t.j]});

            if (t.i < nums1.length - 1) {
                minHeap.offer(new Triplet(nums1[t.i + 1] + nums2[t.j], t.i + 1, t.j));
            }
        }
        return result;
    }
    //    2  4  6  j
    // 1  3  5  7
    // 7  9 11 13
    // 11
    // i
    private class Triplet implements Comparable<Triplet>{
        int sum; // nums1[i] + nums2[j]
        int i; // index for nums1[]
        int j; // index for nums2[]
        Triplet(int sum, int i, int j){
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
        @Override
        public int compareTo(Triplet t){
            return this.sum - t.sum;
        }
    }
}
