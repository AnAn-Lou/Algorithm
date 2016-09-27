import java.util.ArrayList;

// http://www.lintcode.com/en/problem/permutations-ii/
public class QID16 {
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (nums == null || nums.size() == 0) {
            return result;
        }

        ArrayList<Integer> solution = new ArrayList<>();
        boolean[] used = new boolean[nums.size()];
        helper(nums, used, solution, result);

        return result;
    }

    // find permutations starting with solution using unused numbers,
    // and add them into result
    private void helper(ArrayList<Integer> nums,
                        boolean[] used,
                        ArrayList<Integer> solution,
                        ArrayList<ArrayList<Integer>> result){
        // exit
        if (solution.size() == nums.size()) {
            result.add(new ArrayList<>(solution));
        }

        // break down
        for (int i = 0; i < nums.size(); i++){
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums.get(i) == nums.get(i - 1) && used[i - 1]) {
                continue;
            }
            used[i] = true;
            solution.add(nums.get(i));
            helper(nums, used, solution, result);
            solution.remove(solution.size() - 1);
            used[i] = false;
        }

    }
}
