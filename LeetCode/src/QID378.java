import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class QID378 {
    // Version 2: my solution
    // 2^2 < 8 < 3^3  ->  locate the "_|"

    // Version 1: another klogk solution
    // https://discuss.leetcode.com/topic/52948/share-my-thoughts-and-clean-java-code
    public int kthSmallest(int[][] matrix, int k) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0; // or throw Exception
        }

        PriorityQueue<Triplet> minHeap = new PriorityQueue<>(new Comparator<Triplet>(){
            public int compare(Triplet t1, Triplet t2) {
                return t1.val - t2.val;
            }
        });
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < cols; i++) {
            minHeap.offer(new Triplet(matrix[0][i], 0, i));
        }

        for (int i = 0; i < k - 1; i++) {
            Triplet t = minHeap.poll();
            if (t.x == rows - 1) {
                continue;
            }
            minHeap.offer(new Triplet(matrix[t.x + 1][t.y], t.x + 1, t.y));
        }

        return minHeap.poll().val;
    }

    private class Triplet {
        int val; // matrix[x][y]
        int x;
        int y;
        Triplet(int val, int x, int y){
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }
}
