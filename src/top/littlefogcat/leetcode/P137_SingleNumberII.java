package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：通过Map统计出现次数或者位运算。
 */
public class P137_SingleNumberII {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) return e.getKey();
        }
        return 0;
    }

    /**
     * 位运算
     * <p>
     * 1. 奇怪的思路
     * <p>
     * 众所周知，二进制位运算中有：x^x = 0；所以出现两次的数可以通过异或运算来消除。
     * 那么，出现3次的数如何解决？
     * 自然而然就想到三进制了！对三进制的每一位进行“异或”操作，最后就可以将出现3次的数字消除了。
     * 定义三进制异或如下：
     * 0^0 = 0
     * 0^1 = 1
     * 0^2 = 2
     * 1^1 = 2
     * 1^2 = 0
     * 2^2 = 1
     * <p>
     * 当然计算机是基于二进制的，没有三进制的位运算，所以这只是思路不是方法。
     * <p>
     * <p>
     * <p>
     * 2. 标准答案：
     * <p>
     * 用两个整型记录每一个二进制位上面1出现的次数。记这两个整形名为`one`和`two`，其中`one`是个位；出现的数为`num`。
     * 对于出现了3次的数，那么每一个二进制位上面都会出现0次或3次1。当出现了3次时，将该位置0，最后剩下的就是只出现一次的数了。
     * <p>
     * 针对每一位来说，如下：
     * 00 -> 01 -> 10 -> 00
     * 也就是说，如果某个位置来了1，那么在该位上的变化是固定的。
     * one只有在one是0、two是0的时候才变成1；
     * two只有在one是1、two是0的时候才变成1；
     * 如果某个位置来了0，则都不改变。
     * <p>
     * 针对one看：
     * 如果num该位置是0，则不改变。
     * 如果num该位置是1，则如果one/two是0/0的话，one变成1；如果one/two是1/0的话，one变成0；其他情况不改变。
     * 也就是说，只有当num/one/two分别为1/0/0或者1/1/0时，one才改变。（可以发现跟one没关系）
     * 众所周知，对于位运算，0^1 = 1，1^1 = 0；也就是说，对1取异或就是改变。
     * 那么，根据以上结论可以得出：
     * one = one ^ (num & ~two)
     * <p>
     * 类似的，可以得出：
     * 只有当num/one/two分别为1/0/1或者1/1/0时，two才改变。
     * two = two ^ (num & (two ^ one))
     * <p>
     * 最后出现3次的数都抵消了，one则是只出现一次的数。
     */
    public int singleNumber0(int[] nums) {
        int one = 0;
        int two = 0;
        for (int num : nums) {
            int newOne = one ^ (num & ~two);
            int newTwo = two ^ (num & (two ^ one));
            one = newOne;
            two = newTwo;
        }
        return one;
    }

    /**
     * 位运算改进
     * <p>
     * 在上一个位运算中，因为改变了one之后会影响two的计算，所以需要2个变量来进行赋值。
     * 可以通过修改two的计算方式（使用修改之后的one）来消除变量。
     * <p>
     * 00 -> 01 -> 10 -> 00
     * <p>
     * 当num/修改后的one/two分别是1/0/1或1/0/0时，two才改变。可以记为：
     * two = two ^ (num & ~one)
     */
    public int singleNumber1(int[] nums) {
        int one = 0, two = 0;
        for (int num : nums) {
            one = one ^ (num & ~two);
            two = two ^ (num & ~one);
        }
        return one;
    }

}
