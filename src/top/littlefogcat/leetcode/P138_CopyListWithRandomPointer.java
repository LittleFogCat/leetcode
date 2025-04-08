package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：Map
 */
public class P138_CopyListWithRandomPointer {

    Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        return copyOf(head);
    }

    private Node copyOf(Node node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        Node copy = new Node(node.val);
        map.put(node, copy);
        copy.next = copyOf(node.next);
        copy.random = copyOf(node.random);
        return copy;
    }


    public Node copyRandomList1(Node head) {
        Node h = new Node(head.val);
        for (Node ptr = head, p = h; ptr != null; ptr = ptr.next, p = p.next) {
            if (ptr.next != null) p.next = new Node(ptr.next.val);
            p.random = ptr.random;
            ptr.random = p;
        }
        for (Node p = h; p != null; p = p.next) {
            if (p.random != null) {
                Node r = p.random;
                p.random = p.random.random;
            }
        }
        return h;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
