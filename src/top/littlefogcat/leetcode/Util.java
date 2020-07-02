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

    public static class ConvertQuoteSymbol {

        public static void main(String[] args) {
            String arr = "[[4,7,11],[7,11,16],[10,11,20]]";
            arr = arr.replace('[', '{');
            arr = arr.replace(']', '}');
            System.out.println(arr);
        }
    }
}
