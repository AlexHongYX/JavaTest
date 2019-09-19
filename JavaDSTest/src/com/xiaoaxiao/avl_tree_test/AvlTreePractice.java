package com.xiaoaxiao.avl_tree_test;

/**
 * Created by xiaoaxiao on 2019/9/19
 * Description: 反复手撕Avl相关代码，第一次手撕2019.9.19
 */
public class AvlTreePractice {
    private AvlNode root = null;
    private static final int ALLOWED_IMBALANCE = 1;

    private int height(AvlNode root){
        return root==null?-1:root.getHeight();
    }

    public AvlNode insert(int x,AvlNode root){
        if (root == null){
            return new AvlNode(x);
        }
        if(x<root.getValue()){
            root.left = insert(x,root.left);
        }else if(x>root.getValue()){
            root.right = insert(x,root.right);
        }else {
            // do nothing
        }
        return balance(root);
    }

    private AvlNode balance(AvlNode root) {
        if (root == null){
            return null;
        }
        if (height(root.left)-height(root.right)>ALLOWED_IMBALANCE){
            if (height(root.left.left)>=height(root.left.right)){
                root = rotateWithLeftChild(root);
            }else {
                root = doubleWithLeftChild(root);
            }
        }else if(height(root.right)-height(root.left)>ALLOWED_IMBALANCE){
            if (height(root.right.right)>=height(root.right.left)){
                root = rotateWithRightChild(root);
            }else {
                root = doubleWithRightChild(root);
            }
        }
        root.setHeight(Math.max(height(root.left),height(root.right))+1);
        return root;
    }

    private AvlNode rotateWithLeftChild(AvlNode k2) {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.setHeight(Math.max(height(k2.left),height(k2.right))+1);
        k1.setHeight(Math.max(height(k1.left),k2.getHeight()));
        return k1;
    }

    private AvlNode rotateWithRightChild(AvlNode k2) {
        AvlNode k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.setHeight(Math.max(height(k2.left),height(k2.right))+1);
        k1.setHeight(Math.max(k2.getHeight(),height(k1.right))+1);
        return k1;
    }

    private AvlNode doubleWithLeftChild(AvlNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode doubleWithRightChild(AvlNode k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    public AvlNode remove(int x,AvlNode root){
        if (root == null){
            return null;
        }
        if (x<root.getValue()){
            root.left = remove(x,root.left);
        }else if (x>root.getValue()){
            root.right = remove(x,root.right);
        }else if(root.left!=null&&root.right!=null){
            root.setValue(findMin(root.right).getValue());
            root.right = remove(root.getValue(),root.right);
        }else {
            root = (root.left!=null)?root.left:root.right;
        }
        return balance(root);
    }

    private AvlNode findMin(AvlNode root) {
        if (root==null){
            return null;
        }else if (root.left==null){
            return root;
        }
        return findMin(root.left);
    }

}
