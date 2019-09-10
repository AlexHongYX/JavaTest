package com.xiaoaxiao.binary_search_test;

import com.xiaoaxiao.binary_test.Node;

/**
 * Created by xiaoaxiao on 2019/9/10
 * Description: 二叉搜索树的测试类
 */
public class SearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.put(6,106);
        bst.put(7,103);
        bst.put(4,104);
        bst.put(2,102);
        bst.put(1,102);
        bst.put(5,105);
        bst.put(9,109);
        bst.put(3,103);
        bst.put(8,108);
        System.out.println(bst.get(7));
        System.out.println(bst.get(11));
        System.out.println(bst.getOrDefault(11,111));

        bst.preorderPrint(bst.getRoot());
        System.out.println();
        bst.inorderPrint(bst.getRoot());
        System.out.println();
        System.out.println("======================================");
        System.out.println(bst.remove(4));
        bst.preorderPrint(bst.getRoot());
        System.out.println();
        bst.inorderPrint(bst.getRoot());
        System.out.println();
        System.out.println("======================================");

        System.out.println(bst.keySet());
        System.out.println("======================================");

        System.out.println(bst.entrySet());
        System.out.println("======================================");

        System.out.println(bst.values());
        System.out.println("======================================");

        System.out.println(bst.containsKey(8));
        System.out.println(bst.containsKey(11));
        System.out.println("======================================");

        System.out.println(bst.containsValue(108));
        System.out.println(bst.containsValue(111));
    }
}
