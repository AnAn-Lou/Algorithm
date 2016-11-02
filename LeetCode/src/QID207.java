import java.util.*;

// https://leetcode.com/problems/course-schedule/
public class QID207 {

    /* From Discuss
     * using a matrix, instead of a HashMap<Integer, List>
     * using a int count, instead of a Set<Integer>
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        int[] indegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0)
                indegree[ready]++; //duplicate case
            matrix[pre][ready] = 1;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }

    /*
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (prerequisites == null || prerequisites.length == 0 || numCourses == 0) { // no course or no prerequisites
            return true;
        }
        if (prerequisites[0].length != 2) { // wrong input
            return false;
        }

        // calculate in-degree
        int[] indegree = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>(); // <prerequisite, prerequisite of which courses>
        List<Integer> list;
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
            if (map.containsKey(prerequisites[i][1])) {
                list = map.get(prerequisites[i][1]);
            } else {
                list = new ArrayList<>();
                map.put(prerequisites[i][1], list);
            }
            list.add(prerequisites[i][0]);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int course = 0; course < numCourses; course++) {
            set.add(course);
        }

        // find the node whose in-degree == 0
        Deque<Integer> queue = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.add(course);
                set.remove(course); // 入q或出q时做都行
            }
        }

        // find a topological order
        int cur;
        while (!queue.isEmpty()) {
            cur = queue.pop();
            if (map.containsKey(cur)) {
                list = map.get(cur);
                for (Integer course : list) {
                    indegree[course]--;
                    if (indegree[course] == 0) {
                        queue.add(course);
                        set.remove(course);
                    }
                }
            }
        }

        // result
        if (set.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    */
}
