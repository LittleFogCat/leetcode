public class P338_CountingBits {
    class Solution {
        public int[] countBits(int num) {
            int[] res = new int[num + 1];

            for (int i = 0; i < res.length; i++) {
                res[i] = countOne(i);
            }
            return res;
        }
    }

    private static int countOne(int i) {
        int count = 0;
        while (i != 0) {
            if ((i & 1) == 1) {
                count++;
            }
            i >>= 1;
        }
        return count;
    }
}
