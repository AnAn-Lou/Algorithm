import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// http://www.lintcode.com/en/problem/topological-sorting/
public class QID127 {
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        if (graph == null || graph.size() == 0) {
            return result;
        }

        // calculate initial in-degrees
        Map<DirectedGraphNode, Integer> map = new HashMap<>(0); // node, in-degree
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                int indegree;
                if (map.containsKey(neighbor)) {
                    indegree = map.get(neighbor) + 1;
                } else {
                    indegree = 1;
                }
                map.put(neighbor, indegree);
            }
        }

        // find nodes whose indegree == 0
        LinkedList<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph)
            if (!map.containsKey(node)) {
                queue.offer(node);
            }

        // topological sort
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            result.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                int indegree = map.get(neighbor);
                if (indegree == 1) {
                    queue.offer(neighbor);
                } else {
                    map.put(neighbor, indegree - 1);
                }
            }
        }
        return result;
    }
}
