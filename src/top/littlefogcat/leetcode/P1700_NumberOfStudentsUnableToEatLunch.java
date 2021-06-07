package top.littlefogcat.leetcode;

public class P1700_NumberOfStudentsUnableToEatLunch {
    public int countStudents(int[] st, int[] sw) {
        int[] nums = new int[2];
        for (int s : st) nums[s]++;
        for (int i = 0; i < sw.length; i++) {
            if (nums[sw[i]] == 0) return st.length - i;
            nums[sw[i]]--;
        }
        return 0;
    }
}
