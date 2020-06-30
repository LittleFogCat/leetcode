package top.littlefogcat.leetcode;

public class P876_MiddleOfTheLinkedList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode middle = head;
            ListNode tail = head;
            while (tail != null && tail.next != null) {
                middle = middle.next;
                tail = tail.next.next;
            }
            return middle;
        }
    }
}
