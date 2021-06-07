package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P118_PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> r = new ArrayList<>();
        if (numRows == 0) return r;
        r.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            // 生成第i行
            List<Integer> line = new ArrayList<>();
            List<Integer> last = r.get(r.size() - 1); // 上一行
            line.add(1);
            for (int j = 1; j < i - 1; j++) {
                line.add(last.get(j - 1) + last.get(j));
            }
            line.add(1);
            r.add(line);
        }
        return r;
    }
}
