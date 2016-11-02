import java.util.LinkedList;

// https://leetcode.com/problems/number-of-islands/
public class QID200 {

    private class Cordinates {
        int x;
        int y;

        Cordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // up, down, left, right
    private final int[] xDir = {0, 0, -1, 1};
    private final int[] yDir = {1, -1, 0, 0};

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];

        int islands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                explore(i, j, row, col, grid, visited);
                islands++;
            }
        }

        return islands;
    }

    // version 2:BFS
    // WHY TLE?
    private void explore(int i, int j, int row, int col, char[][] grid, boolean[][] visited) {
        if (!shouldExplore(i, j, row, col, grid, visited)) {
            return;
        }

        LinkedList<Cordinates> queue = new LinkedList<>();
        queue.offer(new Cordinates(i, j));

        while (!queue.isEmpty()) {
            Cordinates c = queue.poll();
            visited[c.x][c.y] = true;

            for (int k = 0; k < 4; k++) {
                if (shouldExplore(c.x + xDir[k], c.y + yDir[k], row, col, grid, visited)) {
                    queue.offer(new Cordinates(c.x + xDir[k], c.y + yDir[k]));
                }
            }
        }
    }

    /* Version 1: DFS
     * Find the number of connected blocks in a disconnected graph
    private void explore(int i, int j, int row, int col, char[][] grid, boolean[][] visited){
        if (!shouldExplore(i, j, row, col, grid, visited)) {
            return;
        }

        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            explore(i + xDir[k], j + yDir[k], row, col, grid, visited);
        }
    }
    */

    private boolean shouldExplore(int i, int j, int row, int col, char[][] grid, boolean[][] visited) {
        if (i >= 0 && i < row && j >= 0 && j < col && grid[i][j] == '1' && !visited[i][j]) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        QID200 qid = new QID200();

        String[] s = {"11111011111111101011","01111111111110111110","10111001101111111111","11110111111111111111","10011111111111111111","10111111011101110111","01111111111101101111","11111111111101111011","11111111110111111111","11111111111111111111","01111111011111111111","11111111111111111111","11111111111111111111","11111011111110111111","10111110111011110111","11111111111101111110","11111111111110111100","11111111111111111111","11111111111111111111","11111111111111111111"};

        char[][] grid = new char[s.length][20];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < 20; j++) {
                grid[i][j] = s[i].charAt(j);
            }
        }

        System.out.println(qid.numIslands(grid));

    }
}
