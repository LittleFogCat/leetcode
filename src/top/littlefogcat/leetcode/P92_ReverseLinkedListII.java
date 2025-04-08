package top.littlefogcat.leetcode;

public class P92_ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode p = head;
        ListNode prev = new ListNode(0);
        prev.next = head;

        int i = 1;
        while (i < left) {
            p = p.next;
            prev = prev.next;
            i++;
        }
        // 将遍历到的节点插入到prev之后
        ListNode first = p; // 反转链表的第一个
        while (i < right) {
            i++;
            ListNode next = p.next; // next必不为null
            p.next = next.next;
            if (prev.next == head) head = next; // 插入位置在头节点
            prev.next = next;
            next.next = first;
            first = next;
        }
        return head;
    }
}
