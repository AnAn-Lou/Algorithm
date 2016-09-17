import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// http://www.lintcode.com/en/problem/combination-sum/
public class QID135 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> rst = new ArrayList<>();

        if (candidates == null || candidates.length == 0) {
            return rst;
        }

        Arrays.sort(candidates);

        List<Integer> solu = new ArrayList<>();
        dfs(candidates, target, 0, rst, solu);

        return rst;
    }

    private void dfs(int[] candidates, int target, int pos,
                     List<List<Integer>> rst, List<Integer> solu) {
        // exit
        if (target == 0) {
            rst.add(new ArrayList<>(solu));
            return;
        }

        // break down
        for (int i = pos; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > 0 && candidates[i - 1] == candidates[i]) { // since we could use each number multiple times, we could just ignore duplicates
                continue;
            }
            solu.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, rst, solu);
            solu.remove(solu.size() - 1);
        }
    }

    public static void main(String[] args) {
        QID135 qid = new QID135();
        int[] candidates = {8,7,4,3,4};
        int target = 11;
        qid.print(qid.combinationSum(candidates, target));

    }

    private void print(List<List<Integer>> result) {
        for (List<Integer> solu : result) {
            for (Integer num : solu) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
