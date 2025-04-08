package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P68_TextJustification {
    /**
     * 思路：
     * 首先按照每个单词间只空1格计算出一行最多能够容纳多少个单词。
     * 然后将多余的空格平均分配到单词之间。
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        fullJustify(words, maxWidth, 0, result);
        return result;
    }

    private void fullJustify(String[] words, int maxWidth, int index, List<String> result) {
        if (index >= words.length) return;
        /*
         * 1. 按照每个单词间只空1格计算出一行最多能够容纳多少个单词。
         */
        int wordNum = 1; // 本行能够容纳的单词数
        int len = words[index].length(); // 按照每个空格长度1的总长度
        int spaceLen = maxWidth - len; // 空格总长度
        int i = 1;
        for (i = 1; index + i < words.length; i++) {
            String word = words[index + i];
            if (len + 1 + word.length() > maxWidth)
                break; // 行长度超出
            len += 1 + word.length(); // 行长度增加
            wordNum++; // 单词数+1
            spaceLen -= word.length(); // 空格数 -= 单词长度
        }
        /*
         * 2. 分配多余的空格。
         */
        if (wordNum == 1 || index + i >= words.length) {
            // 两种情况在最后面补空格：只有一个单词或最后一行
            StringBuilder s = new StringBuilder(words[index]);
            for (int j = index + 1; j < index + wordNum; j++) {
                s.append(" ").append(words[j]);
                spaceLen -= 1;
            }
            s.append(" ".repeat(spaceLen));
            result.add(s.toString());
        } else {
            // 均分空格，spaceLen个空格，wordNum-1个位置
            // 其中一部分共addNum个位置多一个空格
            int each = spaceLen / (wordNum - 1); // 每个位置空格数
            int extra = spaceLen % (wordNum - 1); // 多出来的空格平均添加到其中
            StringBuilder s = new StringBuilder(words[index]);
            for (int j = 1; j < wordNum; j++) {
                String space = j <= extra ? " ".repeat(each + 1) : " ".repeat(each);
                s.append(space).append(words[index + j]);
            }
            result.add(s.toString());
//            System.out.println("line = " + s + ", space = " + spaceLen + ", each = " + each + ", extra = " + extra);
        }
        /*
         * 3. 递归下一行
         */
        fullJustify(words, maxWidth, index + wordNum, result);
    }
}
