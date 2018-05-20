package extra;

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
}
