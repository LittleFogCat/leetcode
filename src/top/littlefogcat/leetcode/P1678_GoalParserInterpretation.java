package top.littlefogcat.leetcode;

public class P1678_GoalParserInterpretation {
    public String interpret(String command) {
        return command.replace("()", "o")
                .replace("(al)", "al");
    }
}
