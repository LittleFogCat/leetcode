package top.littlefogcat.leetcode;

public class P521_LongestUncommonSubsequenceI {
    /**
     * 这个解法leetcode通不过，不知道为何，不过不是最优解就不管了吧
     */
    class Solution {
        public int findLUSlength(String a, String b) {
            int lenA = a.length();
            int lenB = b.length();
            // 保证a比b长
            if (lenA < lenB) {
                String tmp = a;
                a = b;
                b = tmp;
                lenB = b.length();
                lenA = a.length();
            }

            out:
            for (int i = lenB; i > 0; i--) {
                // i: 字符串长度
                // j: 子字符串起始序号
                // k: 子字符串最大起始序号
                int k = lenB - i;
//                print("i:", i, "k:", k);
                for (int j = 0; j <= k; j++) {
                    String aString = b.substring(j, j + i);
//                    System.out.print("aString = " + aString + ", ");
                    if (a.contains(aString)) {
//                        System.out.println(a + " contains " + aString);
                        continue out;
                    }
                }
                return i == lenB ? lenA : i;
            }

            return (lenA == 0 || lenB == 0) ? lenA + lenB : -1;
        }
    }

    /**
     * Totally Wrong
     */
    class Solution2 {
        public int findLUSlength(String a, String b) {
            int lenA = a.length();
            int lenB = b.length();
            String newA = a;
            String newB = b;
            if (lenA == 0 && lenB == 0) {
                return -1;
            } else if (lenA == 0 || lenB == 0) {
                return lenA + lenB;
            }
            if (lenA < lenB) {
                String tmp = newA;
                newA = newB;
                newB = tmp;
            }

            String[] split = newA.split(newB);
            int max = -1;
            for (String s : split) {
                int len = s.length();
                System.out.println(s + ": " + len);
                if (len != 0 && len > max) {
                    max = len;
                }
            }

            return max;
        }

    }

    /**
     * It's a joke yes
     */
    class Solution1 {
        public int findLUSlength(String a, String b) {
            return a.equals(b) ? -1 : Math.max(a.length(), b.length());
        }

    }

    public static void main(String[] args) {
        String a = "aefawfawfawfaw";
        String b = "aefawfeawfwafwaef";
        P521_LongestUncommonSubsequenceI.Solution2 solution = new P521_LongestUncommonSubsequenceI().new Solution2();
        System.out.println(solution.findLUSlength(a, ""));
    }
}
