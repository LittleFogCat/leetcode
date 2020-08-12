package top.littlefogcat.leetcode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static ListNode arrayToLinkedList(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode p = head;
        for (int i = 1; i < arr.length; i++) {
            p.next = new ListNode(arr[i]);
            p = p.next;
        }

        return head;
    }

    public static List<List<Integer>> arrToList(int[][] arr) {
        List<List<Integer>> r = new ArrayList<>();
        for (int[] ints : arr) {
            List<Integer> list = new ArrayList<>();
            for (int anInt : ints) {
                list.add(anInt);
            }
            r.add(list);
        }
        return r;
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
            String arr = "[[95387,95790,97307,98168,99868,99995,99995,100000],[-69454,-17042,8172,50983,63432,72854,73012,80848,83723,85916,91759,99779,99913,99944,99994,99999,99999],[65641,95910,97995,98196,98969,99008,99591,99732,99735,99896,99952,99989,99999,100000],[57459,95855,97360,98320,99147,99865,99955,99989,99997,99998,100000],[-81589,-3474,84141,92981,95255,99192,99962,99970,99994,99998,99999,100000],[-23262,92924,95548,96462,99338,99553,99555,99569,99644,99903,99909,99999,99999,100000],[-58466,24432,87898,92795,95701,98143,98163,99182,99351,99746,99811,99943,99955,99978,99997,100000],[-97588,7867,10356,20288,67836,69868,73038,77753,81937,88474,89979,92182,98091,99635,99902,99941,99975,99987,99991,99998,99998,99998,99998,99998,99999,99999,99999,100000],[-96955,41521,84537,89794,96226,97103,97490,99347,99957,99997,100000],[-49247,93963,99006,99428,99964,99992,100000],[46062,48599,95745,98620,98677,99516,99802,99973,99993,100000],[-3786,59724,62870,80033,90471,98836,99395,99574,99682,99724,99909,99963,99979,99999,100000],[-62512,-19463,84187,89388,91368,95524,98987,99085,99230,99809,99978,100000],[18183,83019,98718,99570,99777,99980,100000],[19925,20448,81509,93698,98451,98776,98915,99007,99925,99994,99996,99999,100000],[-96129,93245,95417,98492,99013,99921,99934,99989,99995,100000],[-25468,61948,68372,85478,91239,98906,98988,99653,99915,99957,99998,99999,99999,100000],[36648,65266,95679,98905,99868,99977,99983,99983,99995,99995,99996,99997,100000],[56006,78969,86785,89834,92494,93887,98268,99771,99982,99998,99999,100000],[95387,95790,97307,98168,99868,99995,99995,100000],[-69454,-17042,8172,50983,63432,72854,73012,80848,83723,85916,91759,99779,99913,99944,99994,99999,99999],[65641,95910,97995,98196,98969,99008,99591,99732,99735,99896,99952,99989,99999,100000],[57459,95855,97360,98320,99147,99865,99955,99989,99997,99998,100000],[-81589,-3474,84141,92981,95255,99192,99962,99970,99994,99998,99999,100000],[-23262,92924,95548,96462,99338,99553,99555,99569,99644,99903,99909,99999,99999,100000]]";
            System.out.println(convertFangkuohaoToHuakuohao(arr));
        }
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println("----- printMatrix -----");
        for (int[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
    }

    public static void printMatrix(char[][] matrix) {
        System.out.println("----- printMatrix -----");
        for (char[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
    }
}
