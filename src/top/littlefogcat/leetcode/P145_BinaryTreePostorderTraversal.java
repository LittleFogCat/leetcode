package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class P145_BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        return traversal(new ArrayList<>(), root);
    }

    private List<Integer> traversal(List<Integer> ans, TreeNode root) {
        if (root == null) return ans;
        traversal(ans, root.left);
        traversal(ans, root.right);
        ans.add(root.val);
        return ans;
    }

    /**
     * 迭代方式：使用栈，按照后序遍历相反的顺序遍历，最后再反转结果
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (stack.size() != 0) {
            TreeNode n = stack.pop();
            ans.add(n.val);
            if (n.left != null) stack.push(n.left);
            if (n.right != null) stack.push(n.right);
        }
//        System.out.println(ans);
        Collections.reverse(ans);
        return ans;
    }

}
