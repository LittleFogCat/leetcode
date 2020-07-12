package top.littlefogcat.leetcode.beautyofprogramming;

public class B1_CPU50 {
    public void cpu50() {
        // 6核12线程
        for (int i = 0; i < 12; i++) {
            new Thread(() -> {
                while (true) {
                    // 调参拟合
                    for (long j = 0; j < 2000_0000L; ) {
                        j++;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    public static void main(String[] args) {
        B1_CPU50 b = new B1_CPU50();
        b.cpu50();
    }
}
