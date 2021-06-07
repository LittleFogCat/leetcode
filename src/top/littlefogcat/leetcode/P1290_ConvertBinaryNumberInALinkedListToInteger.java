package top.littlefogcat.leetcode;

public class P1290_ConvertBinaryNumberInALinkedListToInteger {
    public int getDecimalValue(ListNode head) {
        int num = head.val;
        while ((head = head.next) != null) num = (num << 1) + head.val;
        return num;
    }
}
