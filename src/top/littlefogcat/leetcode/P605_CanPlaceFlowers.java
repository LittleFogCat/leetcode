package top.littlefogcat.leetcode;

public class P605_CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int max = 0;
        for (int start = 0, end; start < flowerbed.length; ) {
            if (flowerbed[start] == 0) {
                for (end = start + 1; ; end++) {
                    if (end == flowerbed.length || flowerbed[end] == 1) {
                        break;
                    }
                } // [start, end) 都是0
                int len = end - start - 2;
                if (start == 0) len++;
                if (end == flowerbed.length) len++;
                max += (len + 1) / 2;
                start = end;
            } else start++;
        }
        return max >= n;
    }
}
