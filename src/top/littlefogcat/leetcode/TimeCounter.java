package top.littlefogcat.leetcode;

public class TimeCounter {
    public static long count(Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long countNano(Runnable r) {
        long start = System.nanoTime();
        r.run();
        long end = System.nanoTime();
        return end - start;
    }

    public static long countRepeat(Runnable r, int repeatTime) {
        int time = 0;
        for (int i = 0; i < repeatTime; i++) {
            time += count(r);
        }
        return time;
    }
}
