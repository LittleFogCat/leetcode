package top.littlefogcat.leetcode;

public class P21_MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (; l1 != null && l2 != null; p = p.next) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) p.next = l2;
        else p.next = l1;
        return head.next;
    }
}
