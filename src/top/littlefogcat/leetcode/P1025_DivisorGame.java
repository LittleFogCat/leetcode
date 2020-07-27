package top.littlefogcat.leetcode;

public class P1025_DivisorGame {
    int[] results = new int[1001];

    public boolean divisorGame(int N) {
        if (N == 1) return false;
        if (results[N] != 0) return results[N] == 1;
        int result = -1;
        for (int i = 1; i <= Math.sqrt(N); i++) {
            if (N % i == 0 && !divisorGame(N - i)) {
                result = 1;
                break;
            }
        }
        results[N] = result;
        return result == 1;
    }
}
