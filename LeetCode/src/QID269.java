import java.util.*;

// https://leetcode.com/problems/alien-dictionary/
public class QID269 {

    public static void main(String[] args) {
        QID269 qid = new QID269();
        String[] words = {"wrtkj","wrt"};
        System.out.println(qid.alienOrder(words));
    }

    public String alienOrder(String[] words) {

        if (words == null || words.length < 1) {
            return "";
        }

        // derive pair order
        List<char[]> pairOrderList = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            if (words[i - 1].indexOf(words[i]) == 0
                    && words[i].length() != words[i - 1].length()) { // wrong test case ["wrtkj","wrt"]
                return "";
            }
            char[] pairOrder = getPairOrder(words[i - 1], words[i]);
            if (pairOrder != null) {
                pairOrderList.add(pairOrder);
            }
        }

        // record all chars for later use
        Set<Character> set = new HashSet<>();
        for (String word : words) {
            int len = word.length();
            for (int i = 0; i < len; i++) {
                if (!set.contains(word.charAt(i))) {
                    set.add(word.charAt(i));
                }
            }
        }

        // topological sort
        Map<Character, List<Character>> nodeSuccessorMap = new HashMap<>(); // <node, list of nodes key points to>
        Map<Character, Integer> nodeIndegreeMap = new HashMap<>(); // <char, indegree>
        LinkedList<Character> queue = new LinkedList<>(); // ready to be added to result
        StringBuilder sb = new StringBuilder(); // result

        // build sets & maps
        for (char[] pairOrder : pairOrderList) {
            // set
            if (set.contains(pairOrder[1])) { // remove nodes that have inlinks
                set.remove(pairOrder[1]);
            }
            // nodeIndegreeMap
            if (nodeIndegreeMap.containsKey(pairOrder[1])) {
                nodeIndegreeMap.put(pairOrder[1], nodeIndegreeMap.get(pairOrder[1]) + 1);
            } else {
                nodeIndegreeMap.put(pairOrder[1], 1);
            }
            // nodeSuccessorMap
            List<Character> successors;
            if (!nodeSuccessorMap.containsKey(pairOrder[0])) {
                successors = new ArrayList<>();
                nodeSuccessorMap.put(pairOrder[0], successors);
            } else {
                successors = nodeSuccessorMap.get(pairOrder[0]);
            }
            successors.add(pairOrder[1]);
        }

        // add nodes with zero indegree to the queues
        queue.addAll(set);

        // sort
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            sb.append(ch);
            if (!nodeSuccessorMap.containsKey(ch)) { // no outlink
                continue;
            }
            List<Character> successors = nodeSuccessorMap.get(ch);
            for (char successor : successors) {
                int indegree = nodeIndegreeMap.get(successor);
                if (indegree == 1) {
                    queue.offer(successor);
                    nodeIndegreeMap.remove(successor);
                } else {
                    nodeIndegreeMap.put(successor, indegree - 1);
                }
            }
        }

        if (nodeIndegreeMap.size() > 0) {
            return "";
        }
        return sb.toString();
    }

    private char[] getPairOrder(String word1, String word2) {
        char[] pairOrder = new char[2];

        int len = Math.min(word1.length(), word2.length());
        for (int i = 0; i < len; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                pairOrder[0] = word1.charAt(i);
                pairOrder[1] = word2.charAt(i);
                return pairOrder;
            }
        }
        return null; // word1 = "er", word2 = "ert"
    }

}
