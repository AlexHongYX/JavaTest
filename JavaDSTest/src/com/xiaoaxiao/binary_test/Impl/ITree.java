package com.xiaoaxiao.binary_test.Impl;

import com.xiaoaxiao.binary_test.Node;

/**
 * Created by xiaoaxiao on 2019/8/1
 * Description: 二叉树基本思想都是递归
 */
public interface ITree {

    // 根据字符串创建二叉树
    Node createTestTree(String s);
    // 二叉树的前序遍历
    void binaryTreePrevOrder(Node root);
    // 二叉树的中序遍历
    void binaryTreeInOrder(Node root);
    // 二叉树的后序遍历
    void binaryTreePostOrder(Node root);
    // 结点个数
    int getSize(Node root);
    // 叶子结点个数
    int getLeafSize(Node root);
    // 第 k 层结点个数
    int getKLevelSize(Node root, int k);
    // 查找，依次在二叉树的 根、左子树、右子树 中查找 value，如果找到，返回结点，否则返回 null
    Node find(Node root, char value);

    // 二叉树的高度
    int height(Node root);
}
