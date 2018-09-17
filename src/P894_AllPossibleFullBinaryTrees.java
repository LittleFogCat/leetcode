import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P894_AllPossibleFullBinaryTrees {

    public static void main(String[] args) {
        Solution solution = new P894_AllPossibleFullBinaryTrees().new Solution();
        System.out.println(solution.allPossibleFBT(7));
    }

    class Solution {
        List<TreeNode> result = new ArrayList<>();

        public List<TreeNode> allPossibleFBT(int N) {
            if (N % 2 == 0) return result;
            if (N == 1) {
                result.add(new TreeNode(0));
                return result;
            }
            N--;

            for (int i = 1; i < N; i += 2) {
                int leftNodes = i;
                int rightNodes = N - i - 1;
                List<TreeNode> left = allPossibleFBT(leftNodes);
                List<TreeNode> right = allPossibleFBT(rightNodes);

                for (TreeNode leftNode : left) {
                    for (TreeNode rightNode : right) {
                        TreeNode root = new TreeNode(0);
                        root.left = leftNode;
                        root.right = rightNode;
                        result.add(root);
                    }
                }
            }
            return result;
        }

    }
}
