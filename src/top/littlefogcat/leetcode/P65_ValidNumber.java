package top.littlefogcat.leetcode;

public class P65_ValidNumber {
    /**
     * 思路：科学计数法可以看做被’e‘分割的两个普通数。
     * 一个普通数包含可选符号、可选小数点、至少一个数字。
     */
    public boolean isNumber(String s) {
        if (s.contains("e") || s.contains("E")) {
            // 科学计数法
            int e = s.toLowerCase().indexOf("e");
            return isNormalNumber(s.substring(0, e)) && isInteger(s.substring(e + 1));
        }
        return isNormalNumber(s);
    }

    // 是否是一个普通数
    private boolean isNormalNumber(String s) {
        char[] cs = s.toCharArray();
        boolean isFrac = false; // 是否是小数，标记'.'是否出现过
        boolean containsNumber = false; // 至少包含一个数字
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c >= '0' && c <= '9') {
                // 是数字
                if (!containsNumber) containsNumber = true;
            } else if (c == '-' || c == '+') {
                // 是符号，必须在首位
                if (i == 0) continue;
                return false;
            } else if (c == '.') {
                // 是小数点，只能出现一次
                if (isFrac) return false;
                isFrac = true;
            } else return false; // 奇怪的符号
        }
        return containsNumber;
    }

    // 是否是整数
    private boolean isInteger(String s) {
        char[] cs = s.toCharArray();
        boolean containsNumber = false; // 至少包含一个数字
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c >= '0' && c <= '9') {
                // 是数字
                if (!containsNumber) containsNumber = true;
            } else if (c == '-' || c == '+') {
                // 是符号，必须在首位
                if (i == 0) continue;
                return false;
            } else return false; // 奇怪的符号
        }
        return containsNumber;
    }

    // -------------------------------------------------------------------------

    /**
     * 思路：依次分析
     */
    public boolean isNumber2(String s) {
        char[] cs = s.toCharArray();
        int e = -1; // 幂
        int dot = -1; // 小数点
        int sign = -1; // 符号
        int number = -1; // 数字

        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == 'e' || c == 'E') { // 出现指数，刷新标记
                if (number == -1 || e != -1) return false; // 如果没有出现过数字，或者已经出现了指数
                e = i;
                number = -1;
                dot = -1;
                sign = -1;
            } else if (c == '.') { // 小数点
                if (dot != -1 || e != -1) return false; // 出现过小数点，或者在指数
                dot = i;
            } else if (c == '-' || c == '+') { // 符号
                if (sign != -1) return false; // 出现过符号
                if (e == -1 && i != 0 || e != -1 && i != e + 1) return false; // 符号不在第一位
                sign = i;
            } else if (c >= '0' && c <= '9') {
                number = i;
            } else return false;
        }
        return number != -1; // 至少出现一次数字
    }


}
