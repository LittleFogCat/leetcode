package top.littlefogcat.leetcode;

public class Util {

    public static void print(Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append(' ');
        }
        System.out.println(sb);
    }

    public static long timeCounter(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        return end - start;
    }


    public static int[] arr(int... i) {
        return i;
    }

    /**
     * 将数组转换为链表
     */
    public static ListNode getLinkedListFromArray(int... arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode node = head;
        int i = 0;
        while (++i < arr.length) {
            node.next = new ListNode(arr[i]);
            node = node.next;
        }
        return head;
    }

    public static class ConvertQuoteSymbol {

        /**
         * 把方括号形式的数组转化成花括号的数组
         */
        public static String convertFangkuohaoToHuakuohao(String orig) {
            orig = orig.replace('[', '{');
            orig = orig.replace(']', '}');
            return orig;
        }

        public static void main(String[] args) {
            String arr = "[-1, 0, 1, 2, -1, -4]";
            System.out.println(convertFangkuohaoToHuakuohao(arr));
        }
    }
}
