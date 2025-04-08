package top.littlefogcat.leetcode;

/**
 * 思路：从平方根开始逐渐减小找到最大的宽度。
 */
public class P492_ConstructTheRectangle {
    public int[] constructRectangle(int area) {
        int maxWidth = (int) Math.sqrt(area);
        int[] res = new int[2];
        for (int w = maxWidth; ; w--) {
            if (area / w * w == area) { // 整除
                res[0] = area / w;
                res[1] = w;
                return res;
            }
        }
    }
}
