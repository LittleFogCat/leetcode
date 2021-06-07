package top.littlefogcat.leetcode;

public class P738_MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits1(int N) {
        for (int i = N; i > 0; i--) {
            if (isMonoIncrease(i, 9)) return i;
        }
        return 0;
    }

    // 是否单调递增
    private boolean isMonoIncrease(int i, int last) {
        if (i < 10) return i <= last;
        if (i % 10 > last) return false;
        return isMonoIncrease(i / 10, i % 10);
    }

    // -------------------------------------------------

    public int monotoneIncreasingDigits2(int N) {
        for (int i = N; i > 0; i--) {
            if (isMonoIncreaseCycle(i, 9)) return i;
        }
        return 0;
    }

    private boolean isMonoIncreaseCycle(int i, int last) {
        while (true) {
            if (i < 10) return i <= last;
            if (i % 10 > last) return false;
            last = i % 10;
            i = i / 10;
        }
    }

    // -------------------------------------------

    public int monotoneIncreasingDigits3(int N) {
        if (N < 10) return N;
        int pow = (int) Math.log10(N); // N 的位数
        int pow10 = (int) (Math.pow(10, pow)); // 比N小的最大 10^x
        int n = N / pow10; // N 的首位
        System.out.println("n: " + n + ", pow: " + pow);
        int threshold = n; // 继续递归的阈值
        for (int i = 0; i < pow; i++) {
            threshold = threshold * 10 + n;
        }
        System.out.println("threshold: " + threshold);
        // 如果小于阈值，返回 x999999..
        return N < threshold ? n * pow10 - 1 :
                n * pow10 + monotoneIncreasingDigits3(N - n * pow10);
    }

    public int monotoneIncreasingDigits(int N) {
        if (N < 10) return N;
        int pow = (int) Math.log10(N); // N 的位数
        int pow10 = (int) (Math.pow(10, pow)); // 比N小的最大 10^x
        int n = N / pow10; // N 的首位
        System.out.println("n: " + n + ", pow: " + pow);
        int threshold = n; // 继续递归的阈值
        for (int i = 0; i < pow; i++) {
            threshold = threshold * 10 + n;
        }
        System.out.println("threshold: " + threshold);
        // 如果小于阈值，返回 x999999..
        return N < threshold ? n * pow10 - 1 :
                n * pow10 + monotoneIncreasingDigits3(N - n * pow10);
    }

    public static void main(String[] args) {
        P738_MonotoneIncreasingDigits p = new P738_MonotoneIncreasingDigits();
        Util.assertValue(5, p.monotoneIncreasingDigits3(5));
        Util.assertValue(9, p.monotoneIncreasingDigits3(10));
        Util.assertValue(69999999, p.monotoneIncreasingDigits3(77761672));
        Util.assertValue(699999999, p.monotoneIncreasingDigits3(747131058));
        Util.assertValue(6669, p.monotoneIncreasingDigits3(6671));
    }
}
