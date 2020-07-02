package top.littlefogcat.leetcode;

public class P2_AddTwoNumbers {

    /**
     * 类似于小学加法列竖式；这里直接新建一个链表表示结果会更方便
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int c = 0;
            ListNode head = l1;
            for (; ; l1 = l1.next, l2 = l2.next) {
                int sum = l1.val + l2.val + c;
                if (sum < 10) {
                    c = 0;
                } else {
                    sum -= 10;
                    c = 1;
                }
                l1.val = sum;
                if (l1.next != null && l2.next != null) continue;
                if (l1.next == null && l2.next == null) {
                    if (c == 1) l1.next = new ListNode(1);
                    return head;
                }
                l1.next = l1.next == null ? l2.next : l1.next;
                if (c == 0) return head;
                for (; ; l1 = l1.next) {
                    if (l1.next == null) {
                        l1.next = new ListNode(1);
                        return head;
                    }
                    l1.next.val++;
                    if (l1.next.val < 10) {
                        return head;
                    } else {
                        l1.next.val = 0;
                    }
                }
            }
        }
    }
}
