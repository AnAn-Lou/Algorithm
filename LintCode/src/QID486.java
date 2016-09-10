// http://www.lintcode.com/en/problem/merge-k-sorted-arrays/
import java.util.*;

public class QID486 {
    // Version 2: Heap time: O(nlogk) space: O(k)
    public List<Integer> mergekSortedArrays(int[][] arrays) {

        if (arrays == null || arrays.length == 0 || arrays[0].length == 0) {
            return new LinkedList<>();
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        // init
        for (int row = 0; row < arrays.length; row++) {
            minHeap.offer(new Node(arrays[row][0], row, 0));
        }

        // build result
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result.add(node.val);
            node.col += 1;
            if (node.col < arrays[node.row].length) {
                node.val = arrays[node.row][node.col];
                minHeap.offer(node);
            }
        }

        return result;
    }

    private class Node implements Comparable<Node>{
        int val; // current value
        int row; // x cordinate of the current value
        int col; // y cordinate of the current value
        Node(int val, int row, int col){
            this.val = val;
            this.row = row;
            this.col = col;
        }
        @Override
        public int compareTo(Node n) {
            return this.val - n.val;
        }
    }


    /* Version 1: Divide & Conquer
    public List<Integer> mergekSortedArrays(int[][] arrays) {

        if (arrays == null || arrays.length == 0 || arrays[0].length == 0) {
            return new LinkedList<>();
        }

        return mergeHelper(arrays, 0, arrays.length - 1);

    }

    private List<Integer> mergeHelper(int[][] arrays, int start, int end) {

        if (start == end) {
            List<Integer> result = new LinkedList<>();
            for (int i = 0; i < arrays[start].length; i++) {
                result.add(arrays[start][i]);
            }
            return result;
        }

        if (start + 1 == end) {
            return mergeTwoArrays(arrays[start], arrays[end]);
        }

        // divide
        int mid = start + (end - start) / 2;
        List<Integer> left = mergeHelper(arrays, start, mid);
        List<Integer> right = mergeHelper(arrays, mid + 1, end);

        // conquer
        return mergeTwoLists(left, right);
    }

    private List<Integer> mergeTwoArrays(int[] A, int[] B){
        List<Integer> result = new LinkedList<>();
        int pA = 0;
        int pB = 0;
        while (pA < A.length && pB < B.length) {
            if (A[pA] < B[pB]) {
                result.add(A[pA]);
                pA++;
            } else {
                result.add(B[pB]);
                pB++;
            }
        }
        while (pA < A.length) {
            result.add(A[pA]);
            pA++;
        }
        while (pB < B.length) {
            result.add(B[pB]);
            pB++;
        }
        return result;
    }

    private List<Integer> mergeTwoLists(List<Integer> A, List<Integer> B){

        List<Integer> result = new LinkedList<>();
        int pA = 0;
        int pB = 0;
        while (pA < A.size() && pB < B.size()) {
            if (A.get(pA) < B.get(pB)) {
                result.add(A.get(pA));
                pA++;
            } else {
                result.add(B.get(pB));
                pB++;
            }
        }
        while (pA < A.size()) {
            result.add(A.get(pA));
            pA++;
        }
        while (pB < B.size()) {
            result.add(B.get(pB));
            pB++;
        }
        return result;
    }
    */
}
