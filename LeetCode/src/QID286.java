import java.util.Arrays;
import java.util.LinkedList;

// https://leetcode.com/problems/walls-and-gates/
public class QID286 {
    // time O(m*n)
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        LinkedList<int[]> queue = new LinkedList<>();

        // add gates to the queue
        for (int rowIdx = 0; rowIdx < rooms.length; rowIdx ++) {
            for (int colIdx = 0; colIdx < rooms[0].length; colIdx ++) {
                if (rooms[rowIdx][colIdx] == 0) {
                    queue.offer(new int[] {rowIdx, colIdx});
                }
            }
        }

        int[] xDir = {0, 0, -1, 1};
        int[] yDir = {1, -1, 0, 0};

        int size;
        int distance = 0;

        while (!queue.isEmpty()) {
            size = queue.size();
            distance ++;
            // each pass of the loop processes one level of the BFS
            for (int i = 0; i < size; i ++) {
                int[] curr = queue.poll();
                // each pass of the loop processes one direction
                for (int j = 0; j < xDir.length; j ++) {
                    int rowIdx = curr[0] + xDir[j];
                    int colIdx = curr[1] + yDir[j];
                    if (rowIdx < 0 || rowIdx >= rooms.length || colIdx < 0 || colIdx >= rooms[0].length) { // out of border
                        continue;
                    }
                    if (rooms[rowIdx][colIdx] == Integer.MAX_VALUE) {
                        rooms[rowIdx][colIdx] = distance;
                        queue.offer(new int[] {rowIdx, colIdx});
                    }
                }
            }
        }
    }


    /* time O(m*n*m*n)
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        for (int row = 0; row < rooms.length; row ++) {
            for (int col = 0; col < rooms[0].length; col ++) {
                if (rooms[row][col] == Integer.MAX_VALUE) { // a room to be filled
                    // BFS find the closest gate
                    rooms[row][col] = getDistanceToClosestGate(rooms, row, col);
                }
            }
        }
    }

    private int getDistanceToClosestGate(int[][] rooms, int row, int col){
        int[] xDir = {0, 0, -1, 1};
        int[] yDir = {1, -1, 0, 0};

        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];

        queue.offer(new int[] {row, col});
        visited[row][col] = true;

        int distance = 0;

        while (!queue.isEmpty()) {
            distance ++;
            int size = queue.size();
            // each pass of the loop processes one level of BFS
            for (int i = 0; i < size; i ++) {
                int[] curr = queue.poll();
                // each pass of the loop processes one direction
                for (int j = 0; j < 4; j ++) {
                    int neighborRow = curr[0] + xDir[j];
                    int neighborCol = curr[1] + yDir[j];
                    if (neighborRow < 0 || neighborRow >= rooms.length || neighborCol < 0 || neighborCol >= rooms[0].length ||rooms[neighborRow][neighborCol] == -1 || visited[neighborRow][neighborCol]) { // out of border or obstacle
                        continue;
                    }
                    if (rooms[neighborRow][neighborCol] == 0) { // gate found
                        return distance;
                    }
                    queue.offer(new int[] {neighborRow, neighborCol});
                    visited[neighborRow][neighborCol] = true;
                }
            }
        }

        return Integer.MAX_VALUE;
    }
    */

    public static void main(String[] args) {
        QID286 driver = new QID286();
        int[][] rooms = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };

        driver.wallsAndGates(rooms);

        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }
    }
}
