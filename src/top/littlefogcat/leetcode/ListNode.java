package top.littlefogcat.leetcode;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return next == null ? val + "" : val + " -> " + next;
    }
}
