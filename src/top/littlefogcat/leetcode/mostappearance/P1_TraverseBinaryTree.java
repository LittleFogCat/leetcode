package top.littlefogcat.leetcode.mostappearance;

import java.util.*;

/**
 * 遍历二叉树
 */
public class P1_TraverseBinaryTree {
    /**
     * 树节点
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
     * 深度优先遍历DFS。
     * 深度优先遍历一般使用递归实现。
     *
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> traverseDFS(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        if (root == null) return l;
        preOrderTraverse(root, l);
        return l;
    }

    // 先序遍历
    private void preOrderTraverse(TreeNode node, List<Integer> list) {
        list.add(node.val);
        if (node.left != null) preOrderTraverse(node.left, list); // 遍历左子树
        if (node.right != null) preOrderTraverse(node.right, list); // 遍历右子树
    }

    // 中序遍历
    private void centerOrderTraverse(TreeNode node, List<Integer> list) {
        if (node.left != null) preOrderTraverse(node.left, list); // 遍历左子树
        list.add(node.val);
        if (node.right != null) preOrderTraverse(node.right, list); // 遍历右子树
    }

    // 后序遍历
    private void postOrderTraverse(TreeNode node, List<Integer> list) {
        if (node.left != null) preOrderTraverse(node.left, list); // 遍历左子树
        if (node.right != null) preOrderTraverse(node.right, list); // 遍历右子树
        list.add(node.val);
    }

    /**
     * 深度优先遍历的非递归实现。
     * 深度优先遍历的非递归实现一般使用栈。
     */
    public List<Integer> traverseDFSNoRecur(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        if (root == null) return l;

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (stack.size() > 0) {
            TreeNode top = stack.pop();
            l.add(top.val);
            if (top.left != null) stack.push(top.left);
            if (top.right != null) stack.push(top.right);
        }
        return l;
    }


    /**
     * 广度优先遍历BFS。
     * 广度优先遍历一般使用队列来实现。
     *
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> traverseBFS(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        if (root == null) return l;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            TreeNode first = queue.poll();
            l.add(first.val);
            if (first.left != null) queue.offer(first.left);
            if (first.right != null) queue.offer(first.right);
        }
        return l;
    }


    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] last = new int[2];
        last[1] = 1;
        while (--n > 0) {
            last[1] = last[0] + last[1];
            last[0] = last[1] - last[0];
        }
        return last[1];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.print(fib(i) + " ");
        }
    }
}
