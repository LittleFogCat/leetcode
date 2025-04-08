package top.littlefogcat.leetcode;

import java.util.*;

public class P102_BinaryTreeLevelOrderTraversal {
    /**
     * 迭代
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<TreeNode> t = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            while (!q.isEmpty()) {
                TreeNode n = q.poll();
                level.add(n.val);
                if (n.left != null) t.offer(n.left);
                if (n.right != null) t.offer(n.right);
            }
            while (!t.isEmpty()) {
                TreeNode n = t.poll();
                q.offer(n);
            }
            result.add(level);
        }
        return result;
    }

    /**
     * 递归
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        levels(Collections.singletonList(root), result);
        return result;
    }

    private void levels(List<TreeNode> nodes, List<List<Integer>> result) {
        List<Integer> level = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();
        for (TreeNode node : nodes) {
            level.add(node.val);
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);
        }
        result.add(level);
        if (!nextLevel.isEmpty()) levels(nextLevel, result);
    }

    /**
     * 递归2
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        traverse(root, result, 0);
        return result;
    }

    private void traverse(TreeNode node, List<List<Integer>> result, int level) {
        if (result.size() == level) result.add(new ArrayList<>());
        List<Integer> currentLevel = result.get(level);
        currentLevel.add(node.val);
        if (node.left != null) traverse(node.left, result, level + 1);
        if (node.right != null) traverse(node.right, result, level + 1);
    }
}
