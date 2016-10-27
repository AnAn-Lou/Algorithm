// https://leetcode.com/problems/the-skyline-problem/
import java.util.*;

public class QID218 {
    // Refered to Discuss: https://discuss.leetcode.com/topic/22482/short-java-solution
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();

        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return result;
        }

        // extract [Li, -Hi] & [Ri, Hi] for each building
        // use -Hi to indicate left edge for each building
        List<int[]> coordinates = new ArrayList<>();
        for (int[] b : buildings) {
            coordinates.add(new int[]{b[0], -b[2]}); // left edge
            coordinates.add(new int[]{b[1], b[2]}); // right edge
        }

        // sort first by x coordinate ascendingly, then by y coordinate ascendingly
        Collections.sort(coordinates, new Comparator<int[]>(){
            @Override
            public int compare(int[] c1, int[] c2){
                if (c1[0] != c2[0]) {
                    return c1[0] - c2[0]; // x
                } else {
                    return c1[1] - c2[1]; // y
                }
            }
        });

        // output skyline
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        heap.offer(0);
        int prev = 0;

        for (int[] c : coordinates) {
            if (c[1] < 0) { // left edge
                heap.offer(-c[1]);
            } else { // right edge
                heap.remove(c[1]);
            }

            int curr = heap.peek();
            if (prev != curr) {
                result.add(new int[]{c[0], curr});
                prev = curr;
            }
        }

        return result;
    }
}
