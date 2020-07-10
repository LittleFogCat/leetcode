package top.littlefogcat.leetcode;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return next == null ? val + "" : val + " -> " + next;
    }
}
