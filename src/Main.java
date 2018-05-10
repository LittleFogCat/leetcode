import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        int[][] ts = {{3, 0, 8, 4},
                {2, 4, 5, 7},
                {9, 2, 6, 3},
                {0, 3, 1, 0}};

        System.out.println(new P807_MaxIncreasetoKeepCitySkyline().new Solution().maxIncreaseKeepingSkyline(ts));

        int[][] ts2 = {
                {57, 86, 30, 56},
                {59, 60, 83, 95},
                {83, 37, 38, 98},
                {15, 39, 56, 86}
        };
        System.out.println(new P807_MaxIncreasetoKeepCitySkyline().new Solution().maxIncreaseKeepingSkyline(ts2));
    }
}
