package top.littlefogcat.leetcode;

public class P82_RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode h = new ListNode(9);
        ListNode tail = h;
        ListNode p = head;
        while (p != null) {
            if (p.next == null || p.val != p.next.val) {
                tail.next = new ListNode(p.val);
                tail = tail.next;
                p = p.next;
                continue;
            }
            int val = p.val;
            while (p != null && p.val == val) {
                p = p.next;
            }
        }
        return h.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode before = new ListNode(0);
        before.next = head;
        ListNode p = before;
        while (p.next != null) {
            if (p.next.next != null && p.next.val == p.next.next.val) {
                int val = p.next.val;
                ListNode next = p.next.next.next;
                while (next != null && next.val == val) {
                    next = next.next;
                }
                p.next = next;
            } else {
                p = p.next;
            }
        }
        return before.next;
    }


}
