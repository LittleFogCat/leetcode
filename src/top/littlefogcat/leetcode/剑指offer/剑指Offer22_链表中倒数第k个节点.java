package top.littlefogcat.leetcode.剑指offer;

import top.littlefogcat.leetcode.ListNode;

public class 剑指Offer22_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        p = head;
        for (int i = len - k; ; i--, p = p.next) {
            if (i == 0) return p;
        }
    }
}
