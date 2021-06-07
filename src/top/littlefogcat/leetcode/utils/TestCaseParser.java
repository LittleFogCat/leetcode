package top.littlefogcat.leetcode.utils;

import com.google.gson.Gson;

public class TestCaseParser {
    private static final Gson gson = new Gson();

    public static TestCase parse(String cmdString) {
        return gson.fromJson(cmdString, TestCase.class);
    }
}
