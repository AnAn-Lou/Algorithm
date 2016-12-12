import java.util.*;

// https://leetcode.com/problems/shortest-distance-from-all-buildings/
public class QID317 {
    public int shortestDistance(int[][] grid) {
        int[][] distance = new int[grid.length][grid[0].length]; // distance[i][j] = sum of distances from buildings to (i,j)
        int[][] reach = new int[grid.length][grid[0].length]; // reach[i][j] = number of buildings that could be reached from (i,j)
        int numOfBuildings = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    bfs(i, j, grid, distance, reach);
                    numOfBuildings++;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (reach[i][j] == numOfBuildings) {
                    min = Math.min(min, distance[i][j]);
                }
            }
        }

        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    private void bfs(int x, int y, int[][] grid, int[][] distance, int[][] reach) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int dist = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                // update distance & reach
                if (dist > 0) {
                    distance[p[0]][p[1]] += dist;
                    reach[p[0]][p[1]]++;
                }
                // offer legal neighbors to the queue
                explore(p[0], p[1], grid, visited, queue);
            }
            dist++;
        }
    }

    private void explore(int px, int py, int[][] grid, boolean[][] visited, LinkedList<int[]> queue) {
        int[] xDir = {0, 0, -1, 1};
        int[] yDir = {-1, 1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int x = px + xDir[i];
            int y = py + yDir[i];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) { // out of border
                continue;
            }
            if (grid[x][y] != 0) { // cannot pass through
                continue;
            }
            if (visited[x][y]) { // already visited
                continue;
            }
            queue.offer(new int[]{x, y});
            visited[x][y] = true;
        }
    }

    /* Using a custom class
     * case 2 not handled
    private class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString(){
            return "( " + x + " , " + y + " )";
        }
    }

    public int shortestDistance(int[][] grid) {
        Map<Point, Integer> map = new HashMap<>(); // <p, sum of distances from buildings to p>

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    Point building = new Point(i, j);
                    bfs(building, grid, map);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<Point, Integer> entry : map.entrySet()) {
            min = Math.min(min, entry.getValue());
        }

        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    private void bfs(Point building, int[][] grid, Map<Point, Integer> map) {
        LinkedList<Point> queue = new LinkedList<>();
        queue.offer(building);
        Set<Point> visited = new HashSet<>();
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                // update distance
                if (distance > 0) {
                    if (map.containsKey(p)) {
                        map.put(p, map.get(p) + distance);
                    } else {
                        map.put(p, distance);
                    }
                }
                // offer legal neighbors to the queue
                explore(p, grid, visited, queue);
            }
            distance ++;
        }
    }

    private void explore(Point p, int[][] grid, Set<Point> visited, LinkedList<Point> queue) {
        int[] xDir = {0, 0, -1, 1};
        int[] yDir = {-1, 1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int x = p.x + xDir[i];
            int y = p.y + yDir[i];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) { // out of border
                continue;
            }
            if (grid[x][y] != 0) { // cannot pass through
                continue;
            }
            Point neighbor = new Point(x, y);
            if (visited.contains(neighbor)) { // already visited
                continue;
            }
            queue.offer(neighbor);
            visited.add(neighbor);
        }
    }
    */
    public static void main(String[] args) {
        QID317 driver = new QID317();
        /*
        // case 1
        int[][] grid = {
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        */
        // case 2
        int[][] grid = {
                {0, 2, 1},
                {1, 0, 2},
                {0, 1, 0}
        };
        System.out.println(driver.shortestDistance(grid));
    }

}
