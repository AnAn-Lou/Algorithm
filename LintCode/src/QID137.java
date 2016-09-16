import java.util.*;

// http://www.lintcode.com/en/problem/clone-graph/
public class QID137 {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null) {
            return null;
        }

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>(); //<node, copy>
        copyNodes(node, map);
        copyLinks(node, map);

        return map.get(node);
    }

    private void copyNodes(UndirectedGraphNode node,
                           Map<UndirectedGraphNode, UndirectedGraphNode> map){
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node, new UndirectedGraphNode(node.label));

        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.pop();
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!map.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                }
            }
        }
    }

    private void copyLinks(UndirectedGraphNode node,
                           Map<UndirectedGraphNode, UndirectedGraphNode> map){
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.pop();
            UndirectedGraphNode copy = map.get(curr);
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                copy.neighbors.add(map.get(neighbor));
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}
