package top.littlefogcat.leetcode;

public class P12_IntegerToRoman {
    /**
     * 1 I
     * 2 II
     * 3 III
     * 4 IV
     * 5 V
     * 6 VI
     * 7 VII
     * 8 VIII
     * 9 IX
     * 10 X
     *
     * @param num 1~3999
     */
    public String intToRoman(int num) {
        StringBuilder str = new StringBuilder();

        // 千位
        int thousand = num / 1000;
        switch (thousand) {
            case 3 -> str.append("MMM");
            case 2 -> str.append("MM");
            case 1 -> str.append("M");
        }
        num -= thousand * 1000;

        // 百位
        int hundred = num / 100;
        switch (hundred) {
            case 9 -> str.append("CM");
            case 8 -> str.append("DCCC");
            case 7 -> str.append("DCC");
            case 6 -> str.append("DC");
            case 5 -> str.append("D");
            case 4 -> str.append("CD");
            case 3 -> str.append("CCC");
            case 2 -> str.append("CC");
            case 1 -> str.append("C");
        }
        num -= hundred * 100;

        // 十位
        int ten = num / 10;
        switch (ten) {
            case 9 -> str.append("XC");
            case 8 -> str.append("LXXX");
            case 7 -> str.append("LXX");
            case 6 -> str.append("LX");
            case 5 -> str.append("L");
            case 4 -> str.append("XL");
            case 3 -> str.append("XXX");
            case 2 -> str.append("XX");
            case 1 -> str.append("X");
        }
        num -= ten * 10;

        // 个位
        switch (num) {
            case 9 -> str.append("IX");
            case 8 -> str.append("VIII");
            case 7 -> str.append("VII");
            case 6 -> str.append("VI");
            case 5 -> str.append("V");
            case 4 -> str.append("IV");
            case 3 -> str.append("III");
            case 2 -> str.append("II");
            case 1 -> str.append("I");
        }

        return str.toString();
    }

    public String intToRoman0(int num) {
        String[] thousand = new String[]{"", "M", "MM", "MMM"};
        String[] hundred = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] ten = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] one = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        StringBuilder str = new StringBuilder();
        str.append(thousand[num / 1000])
                .append(hundred[num / 100 % 10])
                .append(ten[num / 10 % 10])
                .append(one[num % 10]);
        return str.toString();
    }

    public static void main(String[] args) {
        P12_IntegerToRoman p12 = new P12_IntegerToRoman();
        System.out.println(p12.intToRoman(2713));
    }
}
