package top.littlefogcat.leetcode;

/**
 * 思路：记录每一层的头节点。
 * 因为每一层的结构相当于一个链表，只用记录头节点就可以访问该层的所有节点。然后再使用BFS即可。
 */
public class P117_PopulatingNextRightPointersInEachNodeII {
    public Node connect(Node root) {
        if (root == null) return null;
        connectNext(root);
        return root;
    }

    private void connectNext(Node head) {
        Node nextHead = null; // 下一层的头节点
        while (head != null) {
            if (head.left == null && head.right == null) {
                head = head.next;
            } else if (head.left != null) {
                nextHead = head.left;
                break;
            } else {
                nextHead = head.right;
                break;
            }
        }
        if (nextHead == null) return;
        Node p = nextHead;
        while (head != null) {
            if (p == head.left) {
                if (head.right != null) {
                    p.next = head.right;
                    p = p.next;
                }
                head = head.next;
            } else if (p == head.right) {
                head = head.next;
            } else {
                if (head.left != null) {
                    p.next = head.left;
                    p = p.next;
                } else if (head.right != null) {
                    p.next = head.right;
                    p = p.next;
                } else {
                    head = head.next;
                }
            }
        }
        connectNext(nextHead);
    }

    private class Node {
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

    ;
}

