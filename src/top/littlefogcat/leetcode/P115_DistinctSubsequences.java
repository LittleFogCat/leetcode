package top.littlefogcat.leetcode;

import java.util.Arrays;

/**
 * 思路：动态规划+记忆化
 */
public class P115_DistinctSubsequences {

    public int numDistinct(String s, String t) {
        return dp(s.toCharArray(), t.toCharArray(), 0, 0, new Integer[s.length()][t.length() + 1]);
//        return dp0(s.toCharArray(), t.toCharArray(), new int[s.length()][t.length()]);
    }

    /**
     * 状态转移方程式：
     * dp[si][ti] = s[si]==t[ti] ? dp[si+1][ti] + dp[si+1][ti+1] : dp[si+1][ti]
     */
    private int dp(char[] s, char[] t, int si, int ti, Integer[][] dp) {
        if (ti == t.length) return 1;
        if (t.length - ti > s.length - si) return 0;
        if (dp[si][ti] != null) return dp[si][ti];
        if (s[si] == t[ti]) {
            dp[si][ti] = dp(s, t, si + 1, ti + 1, dp) // s[si]与t[ti]匹配
                    + dp(s, t, si + 1, ti, dp); // s[si]不与t[ti]匹配
        } else {
            dp[si][ti] = dp(s, t, si + 1, ti, dp);
        }
        return dp[si][ti];
    }

    /**
     * 递推
     */
    private int dp0(char[] s, char[] t, int[][] dp) {
        for (int si = s.length - 1; si >= 0; si--) {
            for (int ti = t.length - 1; ti >= 0; ti--) {
                if (ti == t.length - 1 && si == s.length - 1) {
                    dp[si][ti] = s[si] == t[ti] ? 1 : 0;
                } else if (ti == t.length - 1) {
                    dp[si][ti] = s[si] == t[ti] ? dp[si + 1][ti] + 1 : dp[si + 1][ti];
                } else if (t.length - ti > s.length - si) {
                    dp[si][ti] = 0;
                } else {
                    dp[si][ti] = dp[si + 1][ti];
                    if (s[si] == t[ti]) dp[si][ti] += dp[si + 1][ti + 1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 使用一位数组。注意这里会造成时间的浪费，因为使用二维数组时，并不会把所有情况都递推出来。
     * <p>
     * 递推：
     * dp[si][ti] = s[si]==t[ti] ? dp[si+1][ti] + dp[si+1][ti+1] : dp[si+1][ti]
     */
    private int dp1(char[] s, char[] t) {
        int[] dp = new int[t.length];
        int[] dp_1 = new int[t.length];
        int slast = s.length - 1;
        int tlast = t.length - 1;
        for (int si = slast, ti = tlast; si >= 0; ) {
            if (t.length - ti > s.length - si) {
                dp[ti] = 0;
            } else if (ti == tlast) {
                dp[ti] = s[si] == t[ti] ? dp_1[ti] + 1 : dp_1[ti];
            } else {
                dp[ti] = s[si] == t[ti] ? dp_1[ti] + dp_1[ti + 1] : dp_1[ti];
            }
            if (ti == 0) {
                if (si == 0) break;
                int[] tmp = dp_1;
                dp_1 = dp;
                dp = tmp;
                ti = tlast;
                si--;
            } else {
                ti--;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        P115_DistinctSubsequences p = new P115_DistinctSubsequences();
        System.out.println(p.numDistinct("xslledayhxhadmctrliaxqpokyezcfhzaskeykchkmhpyjipxtsuljkwkovmvelvw" +
                        "xzwieeuqnjozrfwmzsylcwvsthnxujvrkszqwtglewkycikdaiocglwzukwovsghkhyidevhbgffoqkpabthmqihcfxxzde" +
                        "jletqjoxmwftlxfcxgxgvpperwbqvhxgsbbkmphyomtbjzdjhcrcsggleiczpbfjcgtpycpmrjnckslrwduqlccqmgrdhx" +
                        "olfjafmsrfdghnatexyanldrdpxvvgujsztuffoymrfteholgonuaqndinadtumnuhkboyzaqguwqijwxxszngextfcoz" +
                        "petyownmyneehdwqmtpjloztswmzzdzqhuoxrblppqvyvsqhnhryvqsqogpnlqfulurexdtovqpqkfxxnqykgscxaskmks" +
                        "ivoazlducanrqxynxlgvwonalpsyddqmaemcrrwvrjmjjnygyebwtqxehrclwsxzylbqexnxjcgspeynlbmetlkacnnbhm" +
                        "aizbadynajpibepbuacggxrqavfnwpcwxbzxfymhjcslghmajrirqzjqxpgtgisfjreqrqabssobbadmtmdknmakdigjqy" +
                        "qcruujlwmfoagrckdwyiglviyyrekjealvvigiesnvuumxgsveadrxlpwetioxibtdjblowblqvzpbrmhupyrdophjxvh" +
                        "gzclidzybajuxllacyhyphssvhcffxonysahvzhzbttyeeyiefhunbokiqrpqfcoxdxvefugapeevdoakxwzykmhbdytjbh" +
                        "igffkmbqmqxsoaiomgmmgwapzdosorcxxhejvgajyzdmzlcntqbapbpofdjtulstuzdrffafedufqwsknumcxbschdybosx" +
                        "krabyfdejgyozwillcxpcaiehlelczioskqtptzaczobvyojdlyflilvwqgyrqmjaeepydrcchfyftjighntqzoo",
                "rwmimatmhydhbujebqehjprrwfkoebcxxqfktayaaeheys"));
//        System.out.println(p.numDistinct("rabbbit", "rabbit"));

//        String s1 = "xslledayhxhadmctrliaxqpokyezcfhzaskeykchkmhpyjipxtsuljkwkovmvelvw" +
//                "xzwieeuqnjozrfwmzsylcwvsthnxujvrkszqwtglewkycikdaiocglwzukwovsghkhyidevhbgffoqkpabthmqihcfxxzde" +
//                "jletqjoxmwftlxfcxgxgvpperwbqvhxgsbbkmphyomtbjzdjhcrcsggleiczpbfjcgtpycpmrjnckslrwduqlccqmgrdhx" +
//                "olfjafmsrfdghnatexyanldrdpxvvgujsztuffoymrfteholgonuaqndinadtumnuhkboyzaqguwqijwxxszngextfcoz" +
//                "petyownmyneehdwqmtpjloztswmzzdzqhuoxrblppqvyvsqhnhryvqsqogpnlqfulurexdtovqpqkfxxnqykgscxaskmks" +
//                "ivoazlducanrqxynxlgvwonalpsyddqmaemcrrwvrjmjjnygyebwtqxehrclwsxzylbqexnxjcgspeynlbmetlkacnnbhm" +
//                "aizbadynajpibepbuacggxrqavfnwpcwxbzxfymhjcslghmajrirqzjqxpgtgisfjreqrqabssobbadmtmdknmakdigjqy" +
//                "qcruujlwmfoagrckdwyiglviyyrekjealvvigiesnvuumxgsveadrxlpwetioxibtdjblowblqvzpbrmhupyrdophjxvh" +
//                "gzclidzybajuxllacyhyphssvhcffxonysahvzhzbttyeeyiefhunbokiqrpqfcoxdxvefugapeevdoakxwzykmhbdytjbh" +
//                "igffkmbqmqxsoaiomgmmgwapzdosorcxxhejvgajyzdmzlcntqbapbpofdjtulstuzdrffafedufqwsknumcxbschdybosx" +
//                "krabyfdejgyozwillcxpcaiehlelczioskqtptzaczobvyojdlyflilvwqgyrqmjaeepydrcchfyftjighntqzoo";
//        String s2 = "ktayaaeheys";
//        int j = 0;
//        for (int i = 0; i < s2.length(); i++) {
//            while (s1.charAt(j) != s2.charAt(i)) j++;
//        }
//        j++;
//        System.out.println(s1.substring(j));
    }
}
