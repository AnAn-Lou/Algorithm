import java.util.*;

// https://leetcode.com/problems/reconstruct-itinerary/
public class QID332 {

    // from discuss: Hierholzer's algorithm
    // https://discuss.leetcode.com/topic/36370/short-ruby-python-java-c
    // https://discuss.leetcode.com/topic/36383/share-my-solution
    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    List<String> route = new LinkedList<>();

    public List<String> findItinerary(String[][] tickets) {
        for (String[] ticket : tickets) {
            PriorityQueue<String> pq;
            if (!targets.containsKey(ticket[0])) {
                pq = targets.get(ticket[0]);
            } else {
                pq = new PriorityQueue<>();
            }
            pq.add(ticket[1]);
        }

        visit("JFK");
        return route;
    }

    void visit(String airport) {
        while (targets.containsKey(airport) && !targets.get(airport).isEmpty()) {
            visit(targets.get(airport).poll());
        }
        route.add(0, airport);
    }

    /* my solution
    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0 || tickets[0].length == 0) {
            return new ArrayList<>();
        }

        // find all involved cities
        Set<String> citySet = new HashSet<>();
        for (String[] ticket : tickets) {
            if (!citySet.contains(ticket[0])) {
                citySet.add(ticket[0]);
            }
            if (!citySet.contains(ticket[1])) {
                citySet.add(ticket[1]);
            }
        }

        // sort the cities lexically and assign each a number
        int numOfCities = citySet.size();
        List<String> cityList = new ArrayList<>(citySet);
        Collections.sort(cityList);
        Map<String, Integer> cityMap = new HashMap<>();
        for (int i = 0; i < numOfCities; i++) {
            cityMap.put(cityList.get(i), i);
        }

        // use a matrix to represent the graph
        int[][] graph = new int[numOfCities][numOfCities];
        for (String[] ticket : tickets) {
            int depart = cityMap.get(ticket[0]);
            int arrive = cityMap.get(ticket[1]);
            graph[depart][arrive] ++;
        }

        // DFS
        int jfk = cityMap.get("JFK");
        int numOfTickets = tickets.length;
        List<Integer> path = new ArrayList<>();
        List<Integer> result = dfs(jfk, graph, path, numOfTickets);

        // decode
        List<String> travel = new ArrayList<>();
        for (int i : result) {
            travel.add(cityList.get(i));
        }

        return travel;
    }

    // append path starts with cur to the result, if any
    private List<Integer> dfs(int cur, int[][] graph, List<Integer> path, int numOfTickets) {
        path.add(cur);

        if (path.size() - 1 == numOfTickets) {
            return new ArrayList<>(path);
        }

        // break down
        for (int i = 0; i < graph.length; i++) {
            if (graph[cur][i] == 0) {
                continue;
            }
            graph[cur][i] --;
            List<Integer> result = dfs(i, graph, path, numOfTickets);
            if (result != null) {
                return result;
            }
            graph[cur][i] ++;
        }

        path.remove(path.size() - 1);
        return null;
    }
    */
}
