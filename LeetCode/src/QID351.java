// https://leetcode.com/problems/android-unlock-patterns/
public class QID351 {
    // reference: https://discuss.leetcode.com/topic/46260/java-dfs-solution-with-clear-explanations-and-optimization-beats-97-61-12ms
    public int numberOfPatterns(int m, int n) {
        int result = 0;

        boolean[] visited = new boolean[10];

        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[7][9] = skip[9][7] = 8;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;

        for (int len = m; len <= n; len ++) {
            result += dfs(1, len - 1, visited, skip) * 4;
            result += dfs(2, len - 1, visited, skip) * 4;
            result += dfs(5, len - 1, visited, skip);
        }

        return result;
    }
    // return the number of patterns that
    // 1. start with current
    // 2. length of the pattern = remain + 1
    private int dfs(int current, int remain, boolean[] visited, int[][] skip){
        if (remain == 0) {
            return 1;
        }

        int result = 0;
        visited[current] = true;
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && (skip[current][i] == 0 || visited[skip[current][i]])) {
                result += dfs(i, remain - 1, visited, skip);
            }
        }
        visited[current] = false;
        return result;
    }
}
