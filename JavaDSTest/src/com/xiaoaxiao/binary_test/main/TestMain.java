package com.xiaoaxiao.binary_test.main;

import com.xiaoaxiao.binary_test.Node;
import com.xiaoaxiao.binary_test.dao.BinaryTreeTest;

/**
 * Created by xiaoaxiao on 2019/8/1
 * Description: 二叉树基本操作
 */
public class TestMain {

    public static void main(String[] args) {
        BinaryTreeTest binaryTreeTest = new BinaryTreeTest();
//        String s = "ABC##DE#G##F###";
        String s = "ABC##DN##E##F#G##";
        Node root = binaryTreeTest.createTestTree(s);

        System.out.print("先序：");
        binaryTreeTest.binaryTreePrevOrder(root);
        System.out.println();

        System.out.print("中序：");
        binaryTreeTest.binaryTreeInOrder(root);
        System.out.println();

        System.out.print("后序：");
        binaryTreeTest.binaryTreePostOrder(root);
        System.out.println();

        System.out.println("共有"+binaryTreeTest.getSize(root)+"个结点");

        System.out.println("共有"+binaryTreeTest.getLeafSize(root)+"个叶子结点");

        System.out.println(binaryTreeTest.find(root,'F').data);

        System.out.println(binaryTreeTest.height(root));
    }
}
