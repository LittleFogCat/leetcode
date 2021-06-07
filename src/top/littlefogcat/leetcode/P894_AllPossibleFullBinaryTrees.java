package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P894_AllPossibleFullBinaryTrees {

    public static void main(String[] args) {
        Solution solution = new P894_AllPossibleFullBinaryTrees().new Solution();
        System.out.println(solution.allPossibleFBT(7));
    }

    class Solution {

        public List<TreeNode> allPossibleFBT(int n) {
            List<TreeNode> ret = new ArrayList<>();
            if (n == 1) {
                // Exactly.
                ret.add(new TreeNode(0));
                return ret;
            } else {
                /*
                 * For a given number n, exclude the root node, there's n-1 nodes left.
                 *
                 * Because it's a FBT, so the son nodes is FBT too, and it must contains
                 * odd number of nodes.
                 *
                 * That is, root.left = allPossibleFBT(1) and root.right = allPossibleFBT(n - 2),
                 * or root.left = allPossibleFBT(3) and root.right = allPossibleFBT(n - 4),
                 * ...
                 * or root.left = allPossibleFBT(n - 2) and root.right = allPossibleFBT(1).
                 *
                 * Combining all the possible situation, there's the final result.
                 */
                for (int i = 1; i <= (n - 1) / 2; i += 2) {
                    List<TreeNode> left = allPossibleFBT(i);
                    List<TreeNode> right = allPossibleFBT(n - 1 - i);
                    for (TreeNode leftNode : left) {
                        for (TreeNode rightNode : right) {
                            TreeNode node = new TreeNode(0);
                            node.left = leftNode;
                            node.right = rightNode;
                            ret.add(node);
                            if (i != (n - 1) / 2) {
                                TreeNode node1 = new TreeNode(0);
                                node1.left = rightNode;
                                node1.right = leftNode;
                                ret.add(node1);
                            }
                        }
                    }
                }
            }
            return ret;
        }
    }
}
