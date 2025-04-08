package top.littlefogcat.leetcode;

import java.util.*;

/**
 * 思路：BFS
 */
public class P111_MinimumDepthOfBinaryTree {
    /**
     * 递归
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (leftDepth == 0) return rightDepth + 1;
        if (rightDepth == 0) return leftDepth + 1;
        return Math.min(leftDepth, rightDepth) + 1;
    }

    /**
     * DFS
     */
    public int minDepth1(TreeNode root) {
        traverse(root, 1);
        return minDepth;
    }

    int minDepth = 0;

    private void traverse(TreeNode node, int depth) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            if (minDepth == 0 || depth < minDepth) minDepth = depth;
        } else {
            traverse(node.left, depth + 1);
            traverse(node.right, depth + 1);
        }
    }

    /**
     * BFS
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int depth = 1;
        Queue<TreeNode> queue1 = new ArrayDeque<>();
        Queue<TreeNode> queue2 = new ArrayDeque<>();
        queue1.offer(root);
        while (!queue1.isEmpty()) {
            while (!queue1.isEmpty()) {
                TreeNode node = queue1.poll();
                if (node.left == null && node.right == null) return depth;
                if (node.left != null) queue2.offer(node.left);
                if (node.right != null) queue2.offer(node.right);
            }
            Queue<TreeNode> t = queue1;
            queue1 = queue2;
            queue2 = t;
            depth++;
        }
        return -1;
    }

    /**
     * BFS2
     */
    public int minDepth3(TreeNode root) {
        return root == null ? 0 : bfs2(Collections.singletonList(root), 1);
    }

    private int bfs2(List<TreeNode> nodes, int depth) {
        if (nodes.isEmpty()) return depth - 1;
        List<TreeNode> nextLevel = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node.left == null && node.right == null) return depth;
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);
        }
        return bfs2(nextLevel, depth + 1);
    }
}
