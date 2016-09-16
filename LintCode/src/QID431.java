import java.util.*;

// http://www.lintcode.com/en/problem/find-the-connected-component-in-the-undirected-graph/
public class QID431 {
    /* Version 2: DFS
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {

        List<List<Integer>> result = new ArrayList<>();

        if (nodes == null || nodes.size() == 0) {
            return result;
        }

        Set<UndirectedGraphNode> visited = new HashSet<>();
        for (UndirectedGraphNode node : nodes) {
            if (!visited.contains(node)) {
                List<Integer> subGraph = new ArrayList<>();
                dfs(node, result, subGraph, visited);
                Collections.sort(subGraph); // in order to pass lintcode submission
                result.add(subGraph);
            }
        }

        return result;
    }

    private void dfs(UndirectedGraphNode node,
                     List<List<Integer>> result,
                     List<Integer> subGraph,
                     Set<UndirectedGraphNode> visited) {

        subGraph.add(node.label);
        visited.add(node);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, result, subGraph, visited);
            }
        }
    }
    */


    // Version 1: BFS: find connected blocks
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {

        List<List<Integer>> result = new ArrayList<>();

        if (nodes == null || nodes.size() == 0) {
            return result;
        }

        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();

        for (UndirectedGraphNode node : nodes) {
            if (!visited.contains(node)) {
                List<Integer> subGraph = new ArrayList<>();
                queue.offer(node);
                visited.add(node);
                subGraph.add(node.label);
                while (!queue.isEmpty()) {
                    UndirectedGraphNode curr = queue.pop();
                    for (UndirectedGraphNode neighbor : curr.neighbors) {
                        if (!visited.contains(neighbor)) {
                            queue.offer(neighbor);
                            visited.add(neighbor);
                            subGraph.add(neighbor.label);
                        }
                    }
                }
                Collections.sort(subGraph); // in order to pass lintcode submission
                result.add(subGraph);
            }
        }

        return result;
    }
}
