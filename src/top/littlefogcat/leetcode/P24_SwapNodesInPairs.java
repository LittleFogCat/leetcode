package top.littlefogcat.leetcode;

public class P24_SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode second = head.next;
        ListNode before = new ListNode(0);
        before.next = head;
        while (before.next != null && before.next.next != null) {
            ListNode delete = before.next.next;
            before.next.next = before.next.next.next;
            delete.next = before.next;
            before.next = delete;
            before = before.next.next;
        }
        return second;
    }
}
