package top.littlefogcat.leetcode.extra;

import java.util.*;

public class E02_BST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    private static void add(TreeNode root, TreeNode toAdd) {
        if (root.val < toAdd.val) {
            if (root.right == null) {
                root.right = toAdd;
            } else {
                add(root.right, toAdd);
            }
        } else if (root.val > toAdd.val) {
            if (root.left == null) {
                root.left = toAdd;
            } else {
                add(root.left, toAdd);
            }
        } else {
            throw new IllegalArgumentException("Cannot be 2 same value");
        }
    }


    public static Integer[] BSTtoArray(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> list = new ArrayList<>();
        queue.add(root);
        BSTtoArrayHelp(queue, list);
        Integer[] result = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                result[i] = null;
            } else {
                result[i] = list.get(i).val;
            }
        }
        return result;
    }

    private static void BSTtoArrayHelp(Queue<TreeNode> queue, List<TreeNode> list) {
        TreeNode node = queue.poll();
        list.add(node);
//        System.out.println("add " + (node == null ? null : node.val));
        if (node != null && !(node.left == null && node.right == null)) {
            queue.add(node.left);
            queue.add(node.right);
//            System.out.println("add child " + node.left + ", " + node.right);
        }
        if (!queue.isEmpty()) {
            BSTtoArrayHelp(queue, list);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        add(root, new TreeNode(2));
        add(root, new TreeNode(1));
        add(root, new TreeNode(5));
        add(root, new TreeNode(0));
        add(root, new TreeNode(4));
        add(root, new TreeNode(7));
        System.out.println(Arrays.toString(BSTtoArray(root)));
    }
}
