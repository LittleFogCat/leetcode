package top.littlefogcat.leetcode;

public class P1313_DecompressRunLengthEncodedList {
    @SuppressWarnings("StatementWithEmptyBody")
    public int[] decompressRLElist(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; len += nums[i], i += 2) ;
        int[] r = new int[len];
        for (int ri = 0, ni = 0; ni < nums.length; ni += 2) {
            int val = nums[ni + 1];
            int count = nums[ni];
            while (count-- > 0) {
                r[ri++] = val;
            }
        }
        return r;
    }

}
