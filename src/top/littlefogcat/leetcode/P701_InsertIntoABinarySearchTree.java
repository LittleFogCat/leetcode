package top.littlefogcat.leetcode;

public class P701_InsertIntoABinarySearchTree {
    class Solution {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root.val > val) { // search left
                if (root.left != null) insertIntoBST(root.left, val);
                else root.left = new TreeNode(val);
            } else { // search right
                if (root.right != null) insertIntoBST(root.right, val);
                else root.right = new TreeNode(val);
            }
            return root;
        }
    }

    class Solution1 {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            TreeNode tmp = new TreeNode(val);
            tmp.left = null;
            tmp.right = null;
            if (root == null) {
                return tmp;
            }
            TreeNode curr = root;
            while (true) {
                if (curr.val > val) {
                    if (curr.left == null) {
                        curr.left = tmp;
                        return root;
                    } else {
                        curr = curr.left;
                    }

                } else {
                    if (curr.right == null) {
                        curr.right = tmp;
                        return root;
                    } else {
                        curr = curr.right;
                    }
                }
            }
        }
    }
}
