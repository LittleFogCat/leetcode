package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P11_ContainerWithMostWater {
    public int maxArea0(int[] height) {
        int max = 0;
        int h1, h2, area;
        for (int i = 0; i < height.length; i++) {
            h1 = height[i];
            if (i > 1 && h1 < height[i - 1]) continue;
            for (int j = i + 1; j < height.length; j++) {
                h2 = height[j];
                while (j < height.length - 1 && h2 < height[j]) j++;
                area = (j - i) * Math.min(h1, h2);
                if (area > max) max = area;
            }
        }
        return max;
    }

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int maxArea = 0;
        while (j > i) {
            maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) while (i < j && height[i] > height[++i]) ;
            else while (j > i && height[j] > height[--j]) ;
        }
        return maxArea;
    }

    public int getArea(int[] height, int i, int j) {
//        System.out.println("getArea: i=" + i + ", j=" + j);
//        System.out.println("getArea: i=" + height[i] + ", j=" + height[j]);
        if (j <= i) return 0;
        return (j - i) * Math.min(height[i], height[j]);
    }

    public static void main(String[] args) {
        P11_ContainerWithMostWater p11 = new P11_ContainerWithMostWater();
        int[] testCase = new int[]{76, 155, 15, 188, 180, 154, 84, 34, 187, 142, 22, 5, 27, 183, 111, 128, 50, 58, 2, 112, 179, 2, 100, 111, 115, 76, 134, 120, 118, 103, 31, 146, 58, 198, 134, 38, 104, 170, 25, 92, 112, 199, 49, 140, 135, 160, 20, 185, 171, 23, 98, 150, 177, 198, 61, 92, 26, 147, 164, 144, 51, 196, 42, 109, 194, 177, 100, 99, 99, 125, 143, 12, 76, 192, 152, 11, 152, 124, 197, 123, 147, 95, 73, 124, 45, 86, 168, 24, 34, 133, 120, 85, 81, 163, 146, 75, 92, 198, 126, 191};

        System.out.println("testCase: " + Arrays.toString(testCase));
        System.out.println("correct: area = " + p11.maxArea0(testCase));
        System.out.println("area = " + p11.maxArea(testCase));
    }
}
