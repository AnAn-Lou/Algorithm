import java.util.*;
// https://leetcode.com/problems/evaluate-division/
public class QID399 {
    // DFS
    // another solution: floyd warshall-like
    // https://discuss.leetcode.com/topic/58355/esay-understand-java-solution-3ms
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations == null || equations.length == 0 || values == null || values.length == 0 || queries == null || queries.length == 0) {
            return new double[0];
        }

        Map<String, Map<String, Double>> map = new HashMap<>(); // <dividend, <divisor, value>>

        // build map
        int n = equations.length;
        for (int i = 0; i < n; i++) {
            addEquationToMap(equations[i][0], equations[i][1], values[i], map);
            addEquationToMap(equations[i][1], equations[i][0], 1.0 / values[i], map);
        }

        // each pass of the loop processes one query
        int m = queries.length;
        double[] results = new double[m];
        for (int i = 0; i < m; i++) {
            if (!map.containsKey(queries[i][0]) || !map.containsKey(queries[i][1])) {
                results[i] = -1;
                continue;
            }
            if (queries[i][0].equals(queries[i][1])) {
                results[i] = 1;
                continue;
            }
            Set<String> visited = new HashSet<>();
            Double res = dfs(queries[i][0], queries[i][1], 1, visited, map);
            results[i] = res == null ? -1 : res;
        }
        return results;
    }

    // return the val from cur to end or null if no such path
    private Double dfs(String cur, String end, double curVal, Set<String> visited, Map<String, Map<String, Double>> map){
        if (cur.equals(end)) {
            return curVal;
        }
        visited.add(cur);
        Map<String, Double> neighbors = map.get(cur);
        for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
            String neighbor = entry.getKey();
            if (visited.contains(neighbor)) {
                continue;
            }
            Double res = dfs(neighbor, end, curVal * entry.getValue(), visited, map);
            if (res != null) {
                return res;
            }
        }
        visited.remove(cur);
        return null;
    }

    private void addEquationToMap(String dividend, String divisor, double value, Map<String, Map<String, Double>> map) {
        Map<String, Double> innerMap;
        if (map.containsKey(dividend)) {
            innerMap = map.get(dividend);
        } else {
            innerMap = new HashMap<>();
            innerMap.put(dividend, 1.0);
            map.put(dividend, innerMap);
        }
        innerMap.put(divisor, value);
    }

    public static void main(String[] args) {
        QID399 driver = new QID399();
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queires = {{"a", "c"}, {"b", "c"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        double[] result = driver.calcEquation(equations, values, queires);
        System.out.println(Arrays.toString(result));
    }
}
