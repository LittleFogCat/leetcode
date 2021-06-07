package top.littlefogcat.leetcode;

public class P142_LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return null;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != slow) {
            if (fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if (slow == null || fast == null) return null;
        }
        ListNode meet = slow;
        while (meet != head) {
            meet = meet.next;
            head = head.next;
        }
        return head;
    }
}
