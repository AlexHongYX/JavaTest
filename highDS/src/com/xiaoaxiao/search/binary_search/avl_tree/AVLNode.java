package com.xiaoaxiao.search.binary_search.avl_tree;

/**
 * Created by xiaoaxiao on 2019/12/7
 * Description: AVL树原型
 */
public class AVLNode<T> {
    T data;
    AVLNode<T> left;
    AVLNode<T> right;

    AVLNode<T> parent;

    // 平衡因子
    int bf;

    public AVLNode(T data) {
        this.data = data;
    }
}
