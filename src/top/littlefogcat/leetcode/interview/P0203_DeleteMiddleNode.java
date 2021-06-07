package top.littlefogcat.leetcode.interview;


import top.littlefogcat.leetcode.ListNode;

public class P0203_DeleteMiddleNode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
