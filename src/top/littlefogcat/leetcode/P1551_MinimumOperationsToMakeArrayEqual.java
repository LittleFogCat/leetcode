package top.littlefogcat.leetcode;

import javax.lang.model.type.ErrorType;
import java.util.zip.Inflater;

public class P1551_MinimumOperationsToMakeArrayEqual {
    public int minOperations(int n) {
        return (((n & 1) == 1 ? 0 : 1) + n) * (n / 2) / 2;
    }
}
