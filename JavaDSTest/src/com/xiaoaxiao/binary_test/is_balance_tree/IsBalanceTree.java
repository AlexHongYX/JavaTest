package com.xiaoaxiao.binary_test.is_balance_tree;

import com.xiaoaxiao.binary_test.Node;

/**
 * Created by xiaoaxiao on 2019/8/6
 * Description: 遍历二叉树的每个结点，每个结点都判断一次其左右子树高度差是否满足条件
 *              时间复杂度O(n²)，复杂度太高
 */
public class IsBalanceTree {

    public boolean isBalance(Node root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }
        return isBalance(root.left) && isBalance(root.right);
    }

    public static int getHeight(Node root) {

        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    private static int i = 0;

    // 根据字符串创建二叉树
    public static Node createTestTree(String s) {
        char c = s.charAt(i);
        i++;
        if (c == '#') {
            return null;
        }

        Node node = new Node(c);
        node.left = createTestTree(s);
        node.right = createTestTree(s);
        return node;
    }

    public static void main(String[] args) {
        String s = "ABC##DN##E##F#G##";
        Node root = createTestTree(s);
    }
}
