package top.littlefogcat.leetcode.beautyofprogramming;

import java.io.UnsupportedEncodingException;

/**
 * 中国象棋将帅问题
 * <p>
 * |1|2|3|
 * |4|5|6|
 * |7|8|9|
 */
public class B2_JiangShuai {

    /**
     * 使用位运算
     * byte有8位，用上4位表示将，下4位表示帅
     */
    public void findLocations() {

        for (byte b = 0x10; ((b & 0xF0) >> 4) <= 9; b = (byte) ((((b & 0xF0) >> 4) + 1) << 4)) { // 将从1到9
            for (b = (byte) (b | 1); (b & 0x0F) <= 9; b++) { // 帅从1到9
                if (((b & 0xF0) >> 4) % 3 != (b & 0x0F) % 3)
                    System.out.println("A = " + ((b & 0xF0) >> 4) + ", B = " + (b & 0x0F));
            }
        }
    }

    /**
     * 数学法，相当于一个九九乘法表。
     * 因为将帅都只有9个格子，所以一共有81中情况。
     * 每9个为一轮，相当于 i = 0~9, j = 0~9 进行了两重for循环。
     * 其中 i = b / 9，j = b % 9。
     * <p>
     * 因为i和j都是从0开始循环的，所以最后结果要+1（相当于棋盘序号变化+1）
     */
    public void findLocations2() {
        byte b = 0;
        while (++b <= 81) {
            if (b / 9 % 3 == b % 9 % 3) continue;
            System.out.println("A = " + (b / 9 + 1) + ", B = " + (b % 9 + 1));
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        B2_JiangShuai b = new B2_JiangShuai();
        b.findLocations();
        System.out.println("------------------------------");
        b.findLocations2();
    }
}