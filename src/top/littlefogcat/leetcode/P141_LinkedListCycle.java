package top.littlefogcat.leetcode;

public class P141_LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return false;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
