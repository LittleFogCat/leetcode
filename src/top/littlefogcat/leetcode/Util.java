package top.littlefogcat.leetcode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 读取文件，需要文件内容是一个int数组
     */
    public static int[] readIntArrayFromFile(String file) {
        String fileName = "files/" + file;
        ArrayList<Integer> arr = new ArrayList<>();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName))) {
            int ch;
            int num = 0;
            boolean flag = false; // 标记是否正在读一个数
            int neg = 1; // 负数标记
            while ((ch = reader.read()) != -1) {
                char c = (char) ch;
                if (flag) {
                    if (isNumber(c)) {
                        num = num * 10 + (c - '0');
                    } else {
                        if (c == ' ' || c == ',') {
                            flag = false;
                            arr.add(num * neg);
                        } else if (c == ']') {
                            arr.add(num * neg);
                            break;
                        } else throw new RuntimeException("文件格式错误");
                    }
                } else {
                    // 开始读一个数的第一位
                    if (c == '-') {
                        flag = true;
                        num = 0;
                        neg = -1;
                    } else if (isNumber(c)) {
                        flag = true;
                        num = c - '0';
                        neg = 1;
                    } else if (c == ']') {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] a = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            a[i] = arr.get(i);
        }
        return a;
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
            String arr = "[[-2,-3,3],[-5,-10,1],[10,30,-5]]";
            System.out.println(convertFangkuohaoToHuakuohao(arr));
        }
    }
}
