package top.littlefogcat.leetcode.structs;

import java.util.Arrays;

/**
 * 二叉树
 */
public class BinaryTree {
    public static class Node {
        public Node left;
        public Node right;
        public int val;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node root;
    private int[] array;

    public BinaryTree(int[] arr) {
        array = arr;
        root = buildTree(arr);
    }

    private Node buildTree(int[] arr) {
        Node root = new Node(arr[0]);
        buildTree(arr, root, 0);
        return root;
    }

    private void buildTree(int[] arr, Node parent, int index) {
        int left = index * 2 + 1;
        int right = left + 1;

        if (left < arr.length) {
            parent.left = new Node(arr[left]);
            buildTree(arr, parent.left, left);
        }
        if (right < arr.length) {
            parent.right = new Node(arr[right]);
            buildTree(arr, parent.right, right);
        }
    }

    public int[] toArray() {
        return Arrays.copyOf(array, array.length);
    }
}
