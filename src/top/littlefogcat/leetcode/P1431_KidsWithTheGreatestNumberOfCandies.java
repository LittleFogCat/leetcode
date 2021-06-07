package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1431_KidsWithTheGreatestNumberOfCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            if (candies[i] > max) max = candies[i];
        }
        List<Boolean> r = new ArrayList<>(candies.length);
        int min = max - extraCandies;
        for (int candy : candies) {
            r.add(candy >= min);
        }
        return r;
    }
}
