package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P1588_SumOfAllOddLengthSubarrays {
    public int sumOddLengthSubarrays0(int[] arr) {
        int sum = 0;
        for (int len = 1; len <= arr.length; len += 2) {
            for (int s = 0; s <= arr.length - len; s++) {
                for (int i = 0; i < len; i++) {
                    sum += arr[s + i];
                }
            }
        }
        return sum;
    }

    public int sumOddLengthSubarrays1(int[] arr) {
        int sum = 0;
        int arrLen = arr.length;
        int[] times = new int[arrLen]; // 每一个元素累加的次数
        int mid = (arrLen - 1) / 2;
        boolean arrLenIsEven = (arrLen & 1) == 0;
        for (int len = 1; len <= arrLen; len += 2) {
            for (int i = 0; i <= mid; i++) {
                int max = Math.min(len, arrLen - len + 1);
                int time = Math.min(Math.min(i + 1, arrLen - i), max);
                times[i] += time;
                if (i != mid || arrLenIsEven) times[arrLen - 1 - i] += time;
            }
        }
//        System.out.println(arr.length + ", " + Arrays.toString(times));
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] * times[i];
        }
        return sum;
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int[] times = new int[arr.length];
        times[0] = (arr.length + 1) / 2;
        for (int i = 1, step = arr.length % 2 == 0 ? times[0] - 1 : times[0] - 2; i < arr.length; i++) {
            times[i] = times[i - 1] + step;
            step -= arr.length % 2 == 0 ? 1 : i % 2 == 0 ? 2 : 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) sum += arr[i] * times[i];
        return sum;
    }

    public static void main(String[] args) {
        P1588_SumOfAllOddLengthSubarrays p = new P1588_SumOfAllOddLengthSubarrays();
        for (int i = 2; i < 20; i++) {
            int[] arr = new int[i];
//            p.sumOddLengthSubarrays1(arr);
            int sum = p.sumOddLengthSubarrays(arr);
            System.out.println("n=" + i + ", sum=" + sum);
        }
    }
}
