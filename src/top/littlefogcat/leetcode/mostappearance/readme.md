# 面试常见算法题

## 简单

### 1. 遍历二叉树 / TraverseBinaryTree

一般来说，二叉树的遍历有两种方式，深度优先和广度优先。

**深度优先遍历**

深度优先遍历倾向于最快到达叶子节点，然后再遍历当前层级的其他节点。

```java
    /**
     * 深度优先遍历DFS。
     * 深度优先遍历一般使用递归实现。
     *
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> traverseDFS(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        if (root == null) return l;
        preOrderTraverse(root, l);
        return l;
    }

    // 先序遍历
    private void preOrderTraverse(TreeNode node, List<Integer> list) {
        list.add(node.val);
        if (node.left != null) preOrderTraverse(node.left, list); // 遍历左子树
        if (node.right != null) preOrderTraverse(node.right, list); // 遍历右子树
    }

    // 中序遍历
    private void centerOrderTraverse(TreeNode node, List<Integer> list) {
        if (node.left != null) preOrderTraverse(node.left, list); // 遍历左子树
        list.add(node.val);
        if (node.right != null) preOrderTraverse(node.right, list); // 遍历右子树
    }

    // 后序遍历
    private void postOrderTraverse(TreeNode node, List<Integer> list) {
        if (node.left != null) preOrderTraverse(node.left, list); // 遍历左子树
        if (node.right != null) preOrderTraverse(node.right, list); // 遍历右子树
        list.add(node.val);
    }

    /**
     * 深度优先遍历的非递归实现。
     * 深度优先遍历的非递归实现一般使用栈。
     */
    public List<Integer> traverseDFSNoRecur(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        if (root == null) return l;

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (stack.size() > 0) {
            TreeNode top = stack.pop();
            l.add(top.val);
            if (top.left != null) stack.push(top.left);
            if (top.right != null) stack.push(top.right);
        }
        return l;
    }
```

## 中等

## 困难