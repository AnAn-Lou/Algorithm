import java.util.LinkedList;

// http://www.lintcode.com/en/problem/largest-rectangle-in-histogram/
public class QID122 {

    // Version 4: ascending stack: time O(n) space O(n)
    public int largestRectangleArea(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        LinkedList<Integer> stack = new LinkedList<>(); // store index; used to calculate width
        int largestArea = 0;
        for (int i = 0; i <= height.length; i++) {

            int curt = (i == height.length) ? -1 : height[i];

            while (!stack.isEmpty() && height[stack.peek()] > curt) {
                int h = height[stack.pop()];
                int right = i - 1;
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                int area = h * (right - left + 1);
                largestArea = Math.max(largestArea, area);
            }

            stack.push(i);
        }

        return largestArea;
    }

    /*
    // Version 3: O(n^2)
    public int largestRectangleArea(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int globalMax = 0;
        for (int i = 0; i < height.length; i++) { // start
            int min = Integer.MAX_VALUE;
            for (int j = i; j < height.length; j++) { // end
                min = Math.min(min, height[j]);
                globalMax = Math.max(globalMax, min * (j - i + 1));
            }
        }

        // result
        return globalMax;
    }
    */


    /*
    // Version 2: dp: Memory Limit Exceeded
    // dp is not necessary here. just for personal reference.
    public int largestRectangleArea(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int len = height.length;

        // state: dp[i][j] = smallest number among height[i,j], i >= j
        int[][] dp = new int[len][len];
        // globalMax = max(dp[i][j] * (j - i + 1)), 0 <= j <= i <= len - 1
        int globalMax = 0;

        // initialization
        for (int i = 0; i < len; i++) {
            dp[i][i] = height[i];
            globalMax = Math.max(dp[i][i], globalMax);
        }

        // transition
        for (int window = 2; window <= len; window++) {
            for (int left = 0; left + window - 1 < len; left++) {
                dp[left][left + window - 1] = Math.min(height[left],
                                                dp[left + 1][left + window - 1]);
                globalMax = Math.max(globalMax,
                                dp[left][left + window - 1] * window);
            }
        }

        // result
        return globalMax;
    }
    */


    public static void main(String[] args) {
        QID122 qid = new QID122();
        int[] height = {1, 1};
        System.out.println(qid.largestRectangleArea(height));
    }

}
