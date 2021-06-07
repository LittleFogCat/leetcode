package top.littlefogcat.leetcode;

public class P83_RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode h = head;
        while (head != null && head.next != null) {
            while (head.next != null && head.val == head.next.val) head.next = head.next.next;
            head = head.next;
        }
        return h;
    }
}
