package top.littlefogcat.leetcode.utils;

import top.littlefogcat.leetcode.ListNode;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Util {

    public static void swap(int[] arr, int p, int q) {
        int t = arr[p];
        arr[p] = arr[q];
        arr[q] = t;
    }

    public static void assertEqual(Object o1, Object o2) {
        if (Objects.equals(o1, o2)) return;
        throw new AssertionError("Not Equal: " + "val1 = " + o1 + ", val2 = " + o2);
    }

    public static void swap(char[] arr, int p, int q) {
        char t = arr[p];
        arr[p] = arr[q];
        arr[q] = t;
    }

    public static <T> void swap(T[] arr, int p, int q) {
        T t = arr[p];
        arr[p] = arr[q];
        arr[q] = t;
    }

    public static <T> void swap(List<T> arr, int p, int q) {
        T t = arr.get(p);
        arr.set(p, arr.get(q));
        arr.set(q, t);
    }

    public static <T> String matrixToString(T[][] matrix) {
        StringBuilder s = new StringBuilder();
        for (T[] row : matrix) {
            s.append(Arrays.toString(row)).append("\n");
        }
        return s.deleteCharAt(s.length() - 1).toString();
    }

    public static String matrixToString(int[][] matrix) {
        StringBuilder s = new StringBuilder();
        for (int[] row : matrix) {
            s.append(Arrays.toString(row)).append("\n");
        }
        return s.deleteCharAt(s.length() - 1).toString();
    }

    public static String matrixToString(char[][] matrix) {
        StringBuilder s = new StringBuilder();
        for (char[] row : matrix) {
            s.append(Arrays.toString(row)).append("\n");
        }
        return s.deleteCharAt(s.length() - 1).toString();
    }

    public static void print(Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append(' ');
        }
        System.out.println(sb);
    }

    public static long countTime(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long timeCounterNano(Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        long end = System.nanoTime();
        return end - start;
    }

    public static long countTime(Runnable runnable, int times) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
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

    /**
     * 读取文件，需要文件内容是一个int数组
     */
    @SuppressWarnings("ConstantConditions")
    public static int[][] readMatrixFromFile(String file) {
        String fileName = "files/" + file;
        List<List<Integer>> arr = null;
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName))) {
            int ch;
            StringBuilder num = new StringBuilder();
            List<Integer> cur = null;
            while ((ch = reader.read()) != -1) {
                char c = (char) ch;
                if (c == '[') {
                    if (arr == null) {
                        arr = new ArrayList<>();
                    } else {
                        cur = new ArrayList<>();
                    }
                } else if (isNumber(c)) {
                    num.append(c);
                } else if (c == ']') {
                    if (cur != null) {
                        int n = Integer.parseInt(num.toString());
                        cur.add(n);
                        num.setLength(0);
                        arr.add(cur);
                        cur = null;
                    } else {
                        break;
                    }
                } else if (c == ',' && cur != null) {
                    int n = Integer.parseInt(num.toString());
                    cur.add(n);
                    num.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (arr == null || arr.size() < 1) return null;
        int[][] a = new int[arr.size()][arr.get(0).size()];
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(0).size(); j++) {
                a[i][j] = arr.get(i).get(j);
            }
        }
        return a;
    }

    public static ListNode linkedList(int... arr) {
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


    private int max(int... ints) {
        int max = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] > max) max = ints[i];
        }
        return max;
    }

    public static final int BLACK = 30;
    public static final int RED = 31;
    public static final int GREEN = 32;
    public static final int YELLOW = 33;
    public static final int BLUE = 34;
    public static final int PURPLE = 35;
    public static final int CYAN = 36;
    public static final int WHITE = 37;

    public static void printlnWithColor(String s, int color) {
        System.out.println("\033[1;" + color + "m" + s + "\033[0m ");
    }

    public static void printWithColor(String s, int color) {
        System.out.print("\033[1;" + color + "m" + s + "\033[0m ");
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
            String arr = "[['5','3','.','.','7','.','.','.','.'],['6','.','.','1','9','5','.','.','.'],['.','9','8','.','.','.','.','6','.'],['8','.','.','.','6','.','.','.','3'],['4','.','.','8','.','3','.','.','1'],['7','.','.','.','2','.','.','.','6'],['.','6','.','.','.','.','2','8','.'],['.','.','.','4','1','9','.','.','5'],['.','.','.','.','8','.','.','7','9']]";
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

    public static int[][] convertTo2dIntArray(String str) {
        // 去掉最外层的方括号
        String innerStr = str.substring(1, str.length() - 1);

        // 如果字符串为空，返回空数组
        if (innerStr.isEmpty()) {
            return new int[0][0];
        }

        // 按照 "],[", 分割成多个子数组
        String[] subArrays = innerStr.split("\\],\\[");

        // 创建二维数组
        int[][] result = new int[subArrays.length][];

        for (int i = 0; i < subArrays.length; i++) {
            // 去掉子数组的方括号
            String subArrayStr = subArrays[i].replaceAll("^\\[|\\]$", "");

            // 按照逗号分割成单个整数
            String[] elements = subArrayStr.split(",");

            // 创建子数组并填充
            int[] subArray = new int[elements.length];
            for (int j = 0; j < elements.length; j++) {
                subArray[j] = Integer.parseInt(elements[j].trim());
            }

            result[i] = subArray;
        }

        return result;
    }


    public static int[][] read2dIntArrayFromFile(String str) {
        return convertTo2dIntArray(readFile(str));
    }

    public static String readFile(String file) {
        File f = new File("files/" + file);
        StringBuilder content = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
            content.setLength(content.length() - 1);
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
