package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P89_GrayCode {
    public List<Integer> grayCode(int n) {
        if (n == 0) return Collections.singletonList(0);
        List<Integer> prev = grayCode(n - 1);
        List<Integer> r = new ArrayList<>(2 * prev.size());
        r.addAll(prev);
        int flag = 1 << (n - 1);
        for (int i = prev.size() - 1; i >= 0; i--) {
            r.add(prev.get(i) | flag);
        }
        return r;
    }
}
