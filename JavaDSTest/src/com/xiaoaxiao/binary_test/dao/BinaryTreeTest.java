package com.xiaoaxiao.binary_test.dao;

import com.xiaoaxiao.binary_test.Impl.ITree;
import com.xiaoaxiao.binary_test.Node;

/**
 * Created by xiaoaxiao on 2019/8/1
 * Description:
 */
public class BinaryTreeTest implements ITree {

    private int i = 0;

    // 根据字符串创建二叉树
    @Override
    public Node createTestTree(String s) {
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

    // 前序
    @Override
    public void binaryTreePrevOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data);
        binaryTreePrevOrder(root.left);
        binaryTreePrevOrder(root.right);
    }

    // 中序
    @Override
    public void binaryTreeInOrder(Node root) {
        if (root == null) {
            return;
        }
        binaryTreeInOrder(root.left);
        System.out.print(root.data);
        binaryTreeInOrder(root.right);
    }

    // 后序
    @Override
    public void binaryTreePostOrder(Node root) {
        if (root == null) {
            return;
        }
        binaryTreePostOrder(root.left);
        binaryTreePostOrder(root.right);
        System.out.print(root.data);
    }

    // 结点个数
    @Override
    public int getSize(Node root) {
        if (root == null) {
            return 0;
        }
        return getSize(root.left) + getSize(root.right) + 1;
    }

    // 叶子结点个数
    @Override
    public int getLeafSize(Node root) {

        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return getLeafSize(root.left) + getLeafSize(root.right);
    }

    @Override
    public int getKLevelSize(Node root, int k) {
        return 0;
    }

    @Override
    public Node find(Node root, char value) {

        if (root == null) {
            return null;
        }
        if (root.data == value) {
            return root;
        }
        // 先将ret设置为左子树中返回的值
        Node ret = find(root.left, value);
        // 若ret==null（左子树中没找到返回null）
        if (ret == null) {
            // 再去右子树中找
            ret = find(root.right, value);
        }
        // 若左子树中找到，就不会进上面的if语句，则返回的就是左子树中找到的结点
        // 若左子树中没找到，在右子树中找，若右子树中找到了就返回对应结点
        // 若左右子树都没找到就返回null
        return ret;
    }


        if (root == null) {
            return 0;
        }

//        return Math.max(height(root.left),height(root.right))+1;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }
}
