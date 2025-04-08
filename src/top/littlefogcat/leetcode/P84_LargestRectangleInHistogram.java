package top.littlefogcat.leetcode;

// TODO: 有更优解 单调栈
public class P84_LargestRectangleInHistogram {

    /**
     * 思路：以每个柱子为顶点使用中心扩散法构造矩形，返回其中面积最大的。
     */
    public int largestRectangleArea1(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            if (i != 0 && heights[i] == heights[i - 1]) continue;
            // 以heights[i]为顶点
            int left = i;
            int right = i;
            while (left > 0 && heights[left - 1] >= heights[i]) left--;
            while (right < heights.length - 1 && heights[right + 1] >= heights[i]) right++;
            int area = (right - left + 1) * heights[i];
//            System.out.println("i=" + i + ", area=" + area);
            if (area > max) max = area;
        }
        return max;
    }

}
