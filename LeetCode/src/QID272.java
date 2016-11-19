import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/closest-binary-search-tree-value-ii/
public class QID272 {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
            left = null;
            right = null;
        }
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {

        if (root == null) {
            return new ArrayList<>();
        }

        // find closest node
        TreeNode closestNode = findClosestNode(root, target); // O(logn)

        // find predecessors and successors of the closest node
        final double t = target;
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2) {
                return Double.compare(Math.abs(i1 - t), Math.abs(i2 - t));
            }
        });

        heap.offer(closestNode.val);

        TreeNode predecessor = getPredecessor(root, closestNode);
        int count = 0;
        while (predecessor != null && count < k - 1) {
            heap.offer(predecessor.val);
            predecessor = getPredecessor(root, predecessor);
            count ++;
        }

        TreeNode successor = getSuccessor(root, closestNode);
        count = 0;
        while (successor != null && count < k - 1) {
            heap.offer(successor.val);
            successor = getSuccessor(root, successor);
            count ++;
        }

        List<Integer> result = new ArrayList<>();
        count = 0;
        while (!heap.isEmpty() && count < k) {
            result.add(heap.poll());
            count ++;
        }
        return result;
    }

    private TreeNode findClosestNode(TreeNode root, double target) {

        TreeNode current = root;
        TreeNode closestNode = root;
        double closestVal = Math.abs(root.val - target);

        while (current != null) {
            if (current.val == target) {
                return current;
            }

            if (Math.abs(current.val - target) < closestVal) {
                closestVal = Math.abs(current.val - target);
                closestNode = current;
            }

            if (current.val < target) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return closestNode;
    }

    private TreeNode getPredecessor(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return null;
        }

        TreeNode prev = null;
        TreeNode current = root;

        while (current != null) {
            if (current.val == node.val) {
                if (current.left == null) {
                    return prev;
                }
                current = current.left;
                while (current != null && current.right != null) {
                    current = current.right;
                }
                return current;
            } else if (current.val > node.val) {
                current = current.left;
            } else {
                prev = current;
                current = current.right;
            }
        }
        return null;
    }

    private TreeNode getSuccessor(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return null;
        }

        TreeNode next = null;
        TreeNode current = root;

        while (current != null) {
            if (current.val == node.val) {
                if (current.right == null) {
                    return next;
                }
                current = current.right;
                while (current != null && current.left != null) {
                    current = current.left;
                }
                return current;
            } else if (current.val > node.val) {
                next = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }
}