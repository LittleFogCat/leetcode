package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P17_LetterCombinationsOfAPhoneNumber {
    char[][] lettersArr = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        char[] str = digits.toCharArray();
        letterCombinations(str, new StringBuilder(), 0, result);
        return result;
    }

    public void letterCombinations(char[] str, StringBuilder builder, int current, List<String> result) {
        char c = str[current];
        char[] letters = lettersArr[c - '0'];
        for (char letter : letters) {
            builder.append(letter);
            if (current == str.length - 1) {
                result.add(builder.toString());
            } else {
                letterCombinations(str, builder, current + 1, result);
            }
            builder.deleteCharAt(current);
        }
    }
}
