import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/number-of-islands-ii/
public class QID305 {
    // Refer to https://segmentfault.com/a/1190000004197552
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();

        int roots[] = new int[m * n];
        Arrays.fill(roots, -1); // -1 indicates water
        int[][] dir = {{0,-1}, {0,1}, {-1,0}, {1,0}}; // up, down, left, right
        int count = 0;

        for (int i = 0; i < positions.length; i++) { // for each position
            int root = positions[i][0] * n +positions[i][1];
            roots[root] = root;
            count ++;
            for (int j = 0; j < 4; j++) { // for each direction
                int nbx = positions[i][0] + dir[j][0];
                int nby = positions[i][1] + dir[j][1];
                int nb = nbx * n + nby;
                if (nbx < 0 || nbx >= m || nby < 0 || nby >= n || roots[nb] == -1) {
                    continue;
                }
                int nbRoot = getIslandRoot(roots, nb); // find
                if (root != nbRoot) {
                    roots[nbRoot] = root; // union islands
                    count --;
                }
            }
            result.add(count);
        }

        return result;
    }

    private int getIslandRoot(int[] roots, int index){
        while (roots[index] != index) {
            roots[index] = roots[roots[index]]; // path compression (jump one level)
            index = roots[index];
        }
        return index;
    }

    public static void main(String[] args){
        QID305 qid = new QID305();
        int m = 3;
        int n = 3;
        int[][]positions = {
                {0,0},
                {0,1},
                {1,2},
                {2,1}
        };
        System.out.println(qid.numIslands2(m ,n, positions).toString());
    }

}
