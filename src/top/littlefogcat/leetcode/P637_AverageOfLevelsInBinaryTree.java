package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P637_AverageOfLevelsInBinaryTree {
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
     * Wrong Answer
     */
    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> result = new ArrayList<>();
            average(root, 0, 1, result);
            return result;
        }

        private void average(TreeNode node, int sum, int level, List<Double> average) {
            int newSum = sum + node.val;
            System.out.println("current num is " + node.val + ", current level is " + level +
                    ", left is " + (node.left == null ? "null" : node.left.val) +
                    ", right is " + (node.right == null ? "null" : node.right.val));
            if (node.left == null && node.right == null) {
                System.out.println("average is " + newSum + "/" + level);
                average.add(((double) newSum) / level);
            } else if (node.left == null) {
                average(node.right, newSum, level + 1, average);
            } else if (node.right == null) {
                average(node.left, newSum, level + 1, average);
            } else {
                average(node.left, newSum, level + 1, average);
                average(node.right, newSum, level + 1, average);
            }
        }
    }

    class Solution1 {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> av = new ArrayList<>();
            List<Integer> count = new ArrayList<>();
            average(root, 0, av, count);
            return av;
        }

        private void average(TreeNode node, int level, List<Double> av, List<Integer> count) {
            int val = node.val;
            if (level >= av.size()) {
                // 新增
                av.add((double) val);
                count.add(1);
            } else {
                // 不新增
                double aver = av.get(level);
                int ct = count.get(level);
                count.set(level, ct + 1);
                av.set(level, aver + (val - aver) / (ct + 1));
            }

            if (node.left != null) average(node.left, level + 1, av, count);
            if (node.right != null) average(node.right, level + 1, av, count);
        }
    }
}
