package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P515_FindLargestValueInEachTreeRow {
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

    class Solution {

        public List<Integer> largestValues(TreeNode root) {
            List<Integer> largests = new ArrayList<>();
            if (root == null) {
                return largests;
            }
            findLargest(root, 0, largests);
            return largests;
        }

        private void findLargest(TreeNode node, int level, List<Integer> largests) {
            if (level >= largests.size()) {
                largests.add(node.val);
            } else {
                int largest = largests.get(level);
                if (largest < node.val) {
                    largests.set(level, node.val);
                }
            }
            if (node.left != null) {
                findLargest(node.left, level + 1, largests);
            }
            if (node.right != null) {
                findLargest(node.right, level + 1, largests);
            }
        }
    }
}
