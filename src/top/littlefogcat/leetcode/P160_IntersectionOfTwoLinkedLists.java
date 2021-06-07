package top.littlefogcat.leetcode;

public class P160_IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if ((headA == null || headB == null)) return null;
        ListNode a = headA, b = headB;
        boolean flagA = false, flagB = false;
        while (true) {
            if (a == b) return a;
            else if (a != null && b != null) {
                a = a.next;
                b = b.next;
            } else if (a == null && !flagA) {
                a = headB;
                flagA = true;
            } else if (b == null && !flagB) {
                b = headA;
                flagB = true;
            } else return null;
        }
    }

    /**
     * 翻转链表，使用栈的思想
     */
    private ListNode reverse(ListNode head) {
        ListNode stack = null;
        while (head != null) {
            ListNode h = head; // 删除head
            head = head.next; // 删除head
            h.next = stack; // push
            stack = h; // push
        }
        return stack;
    }
}
