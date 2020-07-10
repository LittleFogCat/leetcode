package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("StatementWithEmptyBody")
public class P18_4Sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> r = new ArrayList<>();

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break; // 无论如何都会大于target
                if (nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target)
                    continue; // 无论如何都会小于target
                int k = j + 1, l = nums.length - 1;
                while (k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        r.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        while (nums[k] == nums[++k] && k < l) ;
                        while (nums[l] == nums[--l] && k < l) ;
                    } else if (sum < target) while (nums[k] == nums[++k] && k < l) ;
                    else while (nums[l] == nums[--l] && k < l) ;
                }
                while (j < nums.length - 2 && nums[j] == nums[j + 1]) j++;
            }
            while (i < nums.length - 3 && nums[i] == nums[i + 1]) i++;
        }

        return r;
    }

    public static void main(String[] args) {
        P18_4Sum p18 = new P18_4Sum();
        int[] orig = {-1, 0, 1, 2, -1, -4};
        int target = -1;
        Arrays.sort(orig);
        System.out.println("orig: " + Arrays.toString(orig));

        System.out.println(p18.fourSum(orig, target));
    }
}
