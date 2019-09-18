package com.xiaoaxiao.avl_tree_test;

/**
 * Created by xiaoaxiao on 2019/9/18
 * Description: AVL树的测试
 */
public class TestMain {

    public static void main(String[] args) {
        MyAvlTree myAvlTree = new MyAvlTree();
        AvlNode root = myAvlTree.root;

        // 单旋
        root = myAvlTree.insert(3,null);
        root = myAvlTree.insert(2,root);
        root = myAvlTree.insert(1,root);
        root = myAvlTree.insert(4,root);
        root = myAvlTree.insert(5,root);
        root = myAvlTree.insert(6,root);
        root = myAvlTree.insert(7,root);

        // 双旋
        root = myAvlTree.insert(16,root);
        root = myAvlTree.insert(15,root);
        root = myAvlTree.insert(14,root);
        root = myAvlTree.insert(13,root);
        root = myAvlTree.insert(12,root);
        root = myAvlTree.insert(11,root);
        root = myAvlTree.insert(10,root);
        root = myAvlTree.insert(8,root);
        root = myAvlTree.insert(9,root);

        myAvlTree.preOrder(root);
        System.out.println();
        System.out.println("=========================================");

        myAvlTree.remove(7,root);
        myAvlTree.preOrder(root);
        System.out.println();
        System.out.println("=========================================");

        // 看效果
        myAvlTree.remove(12,root);
        myAvlTree.preOrder(root);
    }
}
