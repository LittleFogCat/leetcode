package top.littlefogcat.leetcode;

public class P669_TrimABinarySearchTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 该节点小于L，那么直接用右儿子取代该点；
     * 该节点大于R，那么直接用左儿子取代该点；
     */
    class Solution {
        public TreeNode trimBST(TreeNode root, int L, int R) {
            if (root == null) {
            } else if (root.val < L) {
                return trimBST(root.right, L, R);
            } else if (root.val > R) {
                return trimBST(root.left, L, R);
            } else {
                root.left = trimBST(root.left, L, R);
                root.right = trimBST(root.right, L, R);
            }
            return root;
        }
    }

    class Solution2 {
        public TreeNode trimBST(TreeNode root, int L, int R) {
            if (root == null) {
                return null;
            } else {
                root.left = trimBST(root.left, L, R);
                root.right = trimBST(root.right, L, R);
                return root.val < L ? root.right : root.val > R ? root.left : root;
            }
        }
    }


}
