package top.littlefogcat.leetcode;

import java.util.*;

public class P116_PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        if (root == null || root.left == null && root.right == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        for (int i = 1; ; i++) {
            Node n = q.poll();
            if (n == null) break;
            if (n.left != null) q.offer(n.left);
            if (n.right != null) q.offer(n.right);
            n.next = (i & (i + 1)) == 0 ? null : q.peek(); // 是否是2^n-1
        }
        return root;
    }

    public Node connect1(Node root) {
        if (root != null && (root.left != null || root.right != null)) {
            recur(Collections.singletonList(root));
        }
        return root;
    }

    private void recur(List<Node> nodes) {
        List<Node> next = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (i == nodes.size() - 1) node.next = null;
            else node.next = nodes.get(i + 1);
            if (node.left != null) next.add(node.left);
            if (node.right != null) next.add(node.right);
        }
        if (next.size() > 0) recur(next);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
