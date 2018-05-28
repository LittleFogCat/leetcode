public class P647_PalindromicSubstrings {
    class Solution {
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            } else if (s.length() == 1) {
                return 1;
            }
            char[] arr = s.toCharArray();
            char lastChar = arr[0];
            int res = 1;
            int repeat = 1;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != lastChar) {
                    res++;
                    repeat = 1;
                    lastChar = arr[i];
                } else {
                    repeat++;
                    res += repeat;
                }
            }
            return res;
        }
    }
}
