import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// http://www.lintcode.com/en/problem/six-degrees/
public class QID531 {
    public int sixDegrees(List<UndirectedGraphNode> graph,
                          UndirectedGraphNode s,
                          UndirectedGraphNode t) {
        Set<UndirectedGraphNode> visited = new HashSet<>();
        return bfs(s, t, visited);
    }

    // bfs
    private int bfs(UndirectedGraphNode s,
                    UndirectedGraphNode t,
                    Set<UndirectedGraphNode> visited) {

        if (s == t) {
            return 0;
        }

        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(s);
        int size = queue.size();
        int degree = 0;
        while (!queue.isEmpty()) {
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode current = queue.poll();
                if (current == t) {
                    return degree;
                }
                visited.add(current);
                for (UndirectedGraphNode neighbor : current.neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                    }
                }
            }
            size = queue.size();
            degree++;
        }
        return -1;
    }
}
