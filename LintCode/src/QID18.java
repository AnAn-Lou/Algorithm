import java.util.ArrayList;
import java.util.Collections;

// http://www.lintcode.com/en/ladder/
public class QID18 {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (S == null || S.size() == 0) {
            return result;
        }

        Collections.sort(S);

        ArrayList<Integer> solution = new ArrayList<>();
        boolean[] used = new boolean[S.size()];
        result.add(new ArrayList<>(solution));
        helper(S, 0, used, solution, result);

        return result;
    }

    // find solutions starting with solution,
    // and add each into result
    private void helper(ArrayList<Integer> S,
                        int index,
                        boolean[] used,
                        ArrayList<Integer> solution,
                        ArrayList<ArrayList<Integer>> result) {
        // exit
        if (index == S.size()) {
            return;
        }

        // break down
        for (int i = index; i < S.size(); i++) {
            if (i > 0 && S.get(i) == S.get(i - 1) && !used[i - 1]){
                continue;
            }
            used[i] = true;
            solution.add(S.get(i));
            result.add(new ArrayList<>(solution));
            helper(S, i + 1, used, solution, result);
            used[i] = false;
            solution.remove(solution.size() - 1);
        }

    }

    /* My initial solution
     * had trouble dealing with duplicates
    public static void main(String args[]) {
        ArrayList<Integer> S = List.asList(5,5,5);
        ArrayList<ArrayList<Integer>> result = subsetsWithDup(S).size(); // [[],[5],[5,5],[5,5],[5,5,5]], but correct answer [[],[5],[5,5],[5,5,5]]

    }

    public static ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (S == null || S.size() == 0) {
            return result;
        }

        Collections.sort(S);

        ArrayList<Integer> solution = new ArrayList<>();
        result.add(new ArrayList<>(solution));
        helper(S, 0, solution, result);

        return result;
    }

    // find solutions starting with solution,
    // and add each into result
    private static void helper(ArrayList<Integer> S,
                        int index,
                        ArrayList<Integer> solution,
                        ArrayList<ArrayList<Integer>> result) {
        // exit
        if (index == S.size()) {
            return;
        }

        // break down
        for (int i = index; i < S.size(); i++) {
            if (i > 0 && S.get(i) == S.get(i - 1) &&
                (solution.size() == 0 || solution.get(solution.size() - 1) != S.get(i))) {
                continue;
            }
            solution.add(S.get(i));
            result.add(new ArrayList<>(solution));
            helper(S, i + 1, solution, result);
            solution.remove(solution.size() - 1);
        }

    }
    */
}
