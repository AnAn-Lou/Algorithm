import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionFind {
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();

        int roots[] = new int[m * n];
        Arrays.fill(roots, -1); // -1 indicates water
        int[][] dir = {{0,1}, {0,-1}, {-1,0}, {0,1}};
        int count = 0;

        for (int i = 0; i < positions.length; i++) { // for each position
            int root = positions[i][0] * m +positions[i][1];
            roots[root] = root;
            count ++;
            for (int j = 0; j < 4; j++) { // for each direction
                int nbx = positions[i][0] + dir[j][0];
                int nby = positions[i][1] + dir[j][1];
                int nb = nbx * m + nby;
                if (nbx < 0 || nbx >= m || nby < 0 || nby >= n || roots[nb] == -1) {
                    continue;
                }
                int nbRoot = getIslandRoot(roots, nb);
                if (root != nbRoot) {
                    roots[root] = nbRoot; // union islands
                    root = nbRoot;
                    count --;
                }
            }
            result.add(count);
            System.out.println(Arrays.toString(roots));
        }

        return result;
    }
    // roots[]
    // 0  1  2  3  4  5  6  7  8
    // 2  0  2 -1 -1 -1 -1 -1 -1

    private static int getIslandRoot(int[] roots, int index){
        while (roots[index] != index) {
            index = roots[index];
        }
        return index;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] positions = {
                {0,0},
                {0,2},
                {0,1},
        };
        System.out.println(UnionFind.numIslands2(m, n, positions));
    }
}
