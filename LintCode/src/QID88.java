// http://www.lintcode.com/en/problem/lowest-common-ancestor/
public class QID88 {

        // return LCA if LCA found
        // return A if A found
        // return B if B found
        // return null if neither found
        /**
        * @param root: The root of the binary search tree.
        * @param A and B: two nodes in a Binary.
        * @return Return the least common ancestor(LCA) of the two nodes.
        */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {

            if (root == null || A == null || B == null) {
                return null;
            }

            if (root == A) {
                return A;
            }

            if (root == B) {
                return B;
            }

            TreeNode left = lowestCommonAncestor(root.left, A, B);
            TreeNode right = lowestCommonAncestor(root.right, A, B);

            if (left != null && right != null) { // LCA found
                return root;
            }

            if (left == null && right == null) { // neigher A or B found
                return null;
            }

            if (left!= null) { // A or B found
                return left;
            }

            /*
            if (right != null) { // A or B found
                return right;
            }
            */

            return right;
        }
}
