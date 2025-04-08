package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1992_FindAllGroupsofFarmland {

    public int[][] findFarmland(int[][] land) {
        List<int[]> ans = new ArrayList<>();
        for (int r1 = 0; r1 < land.length; r1++) {
            int[] row = land[r1];
            for (int c1 = 0; c1 < row.length; c1++) {
                if (land[r1][c1] == 0) continue; // is forest
                if (r1 != 0 && land[r1 - 1][c1] == 1) continue; // is not left
                if (c1 != 0 && row[c1 - 1] == 1) continue; // is not top
                // 左上为 (r1, c1)，寻找右下
                int r2 = r1;
                int c2 = c1;
                while (c2 < row.length - 1 && row[c2 + 1] == 1) c2++;
                while (r2 < land.length - 1 && land[r2 + 1][c1] == 1) r2++;
                ans.add(new int[]{r1, c1, r2, c2});
            }
        }
        return ans.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        P1992_FindAllGroupsofFarmland solution = new P1992_FindAllGroupsofFarmland();
        int[][] land = new int[][]{
                new int[]{0, 1},
                new int[]{1, 0}
        };
        int[][] farmland = solution.findFarmland(land);
        System.out.println(Arrays.deepToString(farmland));
    }
}
