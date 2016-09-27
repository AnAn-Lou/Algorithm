import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// https://leetcode.com/problems/perfect-squares/
public class QID279 {
    // bfs
    // graph nodes are numbers that can be transformed from the original by deducting perfect squares
    // reference: https://discuss.leetcode.com/topic/59273/easiest-java-bfs-solution
    public int numSquares(int n) {

        if (n <= 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(n);
        int level = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                int ceiling = (int) Math.sqrt(curr);
                if (ceiling * ceiling == curr) {
                    return level;
                }
                for (int j = ceiling; j >= 1; j--) {
                    if (set.add(curr - j * j)){ // to avoid unnecessary expanding
                        queue.offer(curr - j * j);
                    }
                }
            }
            level++;
        }

        return level;
    }

    /* dp
    public int numSquares(int n) {

        if (n <= 0) {
            return 0;
        }

        // state: dp[i] = least number of perfect square numbers which sum to i
        int[] dp = new int[n + 1];

        // initialize
        dp[0] = 0;
        dp[1] = 1;

        // transition
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j]);
            }
            dp[i] += 1;
        }

        // result
        return dp[n];
    }
    */
}
