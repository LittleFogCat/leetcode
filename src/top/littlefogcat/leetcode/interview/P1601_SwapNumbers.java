package top.littlefogcat.leetcode.interview;

public class P1601_SwapNumbers {
    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] + numbers[1]; // [0+1, 1]
        numbers[1] = numbers[0] - numbers[1]; // [0+1, 0]
        numbers[0] = numbers[0] - numbers[1]; // [1, 0]
        return numbers;
    }
}
