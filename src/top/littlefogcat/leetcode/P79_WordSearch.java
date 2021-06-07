package top.littlefogcat.leetcode;

public class P79_WordSearch {
    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(board, chars, 0, i, j)) return true;
            }
        }
        return false;
    }

    public boolean exist(char[][] board, char[] word, int start, int r, int c) {
        boolean exist;
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) exist = false;
        else if (board[r][c] != word[start]) exist = false;
        else if (start == word.length - 1) exist = true;
        else {
            char bak = board[r][c];
            board[r][c] = 0;
            exist = exist(board, word, start + 1, r + 1, c) ||
                    exist(board, word, start + 1, r, c + 1) ||
                    exist(board, word, start + 1, r - 1, c) ||
                    exist(board, word, start + 1, r, c - 1);
            board[r][c] = bak;
        }
        if (exist) {
            System.out.println(word[start] + " exist on (" + r + ", " + c + ")");
        }
        return exist;
    }
}
