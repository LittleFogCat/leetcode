package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 测试用。
 * 使用方式：
 * <pre>
 *     Test test = new Test(
 *             // 在构造方法中通过`Test.testCaseOf`传入测试用例
 *             // 第一个参数是期望结果，第二个参数是测试用例参数
 *             // 如果测试用例是单个数组参数，需要强制转换为Object
 *             Test.testCaseOf(197, (Object) new int[]{1, 7, 90, 98, 99, 100}),
 *             Test.testCaseOf(13, (Object) new int[]{1, 2, 4, 5, 6, 8}),
 *             Test.testCaseOf(555, (Object) new int[]{111, 222, 333, 888}),
 *             Test.testCaseOf(131, (Object) new int[]{1, 7, 8, 12})
 *     ) {
 *         // 覆写`onTest`方法实现单个测试用例的测试
 *         // 返回计算值，如果没有返回则返回null
 *         public Object onTest(Object[] params) {
 *             int[] nums = (int[]) params[0];
 *             return e.splitArrayWithClosestSum(nums);
 *         }
 *     };
 *     // 调用`run()`方法执行测试
 *     test.run();
 * </pre>
 */
public abstract class Test {
    private List<TestCase> testCases = new ArrayList<>();

    public Test(TestCase... tc) {
        testCases.addAll(Arrays.asList(tc));
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public void addTestCase(Object expect, Object... params) {
        testCases.add(testCaseOf(expect, params));
    }

    public abstract Object onTest(Object[] params);

    public List<Result> run() {
        List<Result> results = new ArrayList<>();
        int failSize = 0;
        int failedIndex = -1;
        for (TestCase testCase : testCases) {
            Object output = onTest(testCase.params);
            Result r = new Result(testCase, output, testCase.expectResult);
            if (!r.success) {
                if (failedIndex == -1) {
                    failedIndex = results.size();
                }
                failSize++;
            }
            results.add(r);
        }
        if (failSize == 0) {
            Util.printlnWithColor("All test cases passed.", Util.GREEN);
        } else {
            Util.printlnWithColor("Wrong answer. " + (testCases.size() - failSize) + "/" + testCases.size() + " test cases passed.", Util.RED);
            for (int i = 0; i < results.size(); i++) {
                Result r = results.get(i);
                if (!r.success) {
                    if (i != failedIndex) System.out.println("---");
                    Util.printWithColor("Input: ", Util.YELLOW);
                    System.out.println(r.input.getParams());
                    Util.printWithColor("Output: ", Util.YELLOW);
                    System.out.println(r.output);
                    Util.printWithColor("Expected: ", Util.YELLOW);
                    System.out.println(r.expected);
                }
            }
        }
        return results;
    }

    public static TestCase testCaseOf(Object expect, Object... params) {
        return new TestCase(expect, params);
    }

    public static class Result {
        public TestCase input;
        public Object output;
        public Object expected;
        public boolean success;

        public Result(TestCase input, Object output, Object expected) {
            this.input = input;
            this.output = output;
            this.expected = expected;
            success = Objects.equals(output, expected);
        }
    }

    public static class TestCase {
        private final Object expectResult;
        private final Object[] params;

        public TestCase(Object expect, Object... params) {
            this.expectResult = expect;
            this.params = params;
        }

        public String getParams() {
            StringBuilder s = new StringBuilder();
            for (Object p : params) {
                if (p instanceof int[]) {
                    s.append(Arrays.toString((int[]) p)).append("\n");
                } else {
                    s.append(p).append("\n");
                }
            }
            if (s.charAt(s.length() - 1) == '\n') s.deleteCharAt(s.length() - 1);
            return s.toString();
        }
    }
}
