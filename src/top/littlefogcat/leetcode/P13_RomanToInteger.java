package top.littlefogcat.leetcode;

public class P13_RomanToInteger {
    public int romanToInt(String s) {
        //
        String[] thousand = new String[]{"", "M", "MM", "MMM"};
        String[] hundred = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] ten = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] one = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[][] currents = new String[][]{thousand, hundred, ten, one};
        int num = 0;
        for (int i = 0, pow = 1000, offset = 0; i < currents.length; i++, pow /= 10) {
            String[] current = currents[i];
            for (int j = current.length - 1; j >= 0; j--) {
                if (s.startsWith(current[j], offset)) {
                    num += pow * j;
                    offset += current[j].length();
                    break;
                }
            }
        }

        return num;
    }
}
