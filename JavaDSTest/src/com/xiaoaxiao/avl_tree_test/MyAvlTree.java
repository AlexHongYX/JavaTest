package com.xiaoaxiao.avl_tree_test;

/**
 * Created by xiaoaxiao on 2019/9/18
 * Description: Avl平衡树——单旋+双旋
 *          1、对a的左儿子的左子树进行一次插入——单旋(rotateWithLeftChild)
 *          2、对a的左儿子的右子树进行一次插入——双旋()
 *          3、对a的右儿子的左子树进行一次插入——双旋()
 *          4、对a的右儿子的右子树进行一次插入——单旋(rotateWithRightChild)
 *
 *        所谓双旋，就是a的儿子与孙子先进行一次单旋
 *                    a再与新儿子进行一次单旋
 *                 两次单旋的结果下来与双旋的结果相同
 */

class AvlNode{
    private int value;
    private int height;
    public AvlNode left;
    public AvlNode right;

    public AvlNode(int value) {
        this.value = value;
        this.height = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

public class MyAvlTree {

    // 设置一个高度差，若左右子树差值超过该高度差，就不符合平衡树了
    private static final int ALLOWED_IMBALANCE = 1;

    // 设置平衡树的根节点
    public AvlNode root = null;

    /**
     * 返回当前root的高度
     * @param root  传入的根
     * @return  当前根的高度(就在AVLNode对象中存着)
     */
    private int height(AvlNode root){
        // 如果root为null，就返回-1
        // 否则返回root.height
        return root == null?-1:root.getHeight();
    }

    /**
     * 插入一个值为x的结点，返回经平衡操作后的新根节点
     * @param x 形参
     * @param root  原树的结点
     * @return  经过平衡化操作后的新根节点
     */
    public AvlNode insert(int x,AvlNode root){
        // 如果此时root为null了，说明当前x就应该插入到当前位置
        // 由于是在原本为null的地方插入，不需要考虑平衡的问题(只有一个根节点)
        // 因此直接返回该节点即可，不用进行"平衡化"
        if (root == null){
            return new AvlNode(x);
        }
        if (x<root.getValue()){
            root.left = insert(x,root.left);
        }else if(x>root.getValue()){
            root.right = insert(x,root.right);
        }else {
            // 若等于直接进入下面的return语句(平衡树Avl化即可)
            // do nothing
        }
        return balance(root);
    }

    /**
     * 将传入的root进行调平操作
     * @param root  当前节点
     * @return  调平后的根节点
     */
    private AvlNode balance(AvlNode root){
        if (root==null){
            return root;
        }
        if (height(root.left)-height(root.right)>ALLOWED_IMBALANCE){
            // 若左孩子的左子树的高度>=左孩子的右子树的高度
            // 说明此时新插入的结点插在了左孩子的左子树——符合第1种情况
            // 采用第一种单旋(左单旋)
            if (height(root.left.left)>=height(root.left.right)){
                root = rotateWithLeftChild(root);
            }else {
                // 若左孩子的左子树的高度<左孩子的右子树的高度
                // 说明此时新插入的结点插在了左孩子的右子树——符合第2种情况
                // 采用第一种双旋(左双旋)
                root = doubleWithLeftChild(root);
            }
        }else if(height(root.right)-height(root.left)>ALLOWED_IMBALANCE){
            if (height(root.right.right)>=height(root.right.left)){
                root = rotateWithRightChild(root);
            }else {
                root = doubleWithRightChild(root);
            }
        }else {
            // do nothing
        }
        // 将root调整后更新其height
        root.setHeight(Math.max(height(root.left),height(root.right))+1);
        return root;
    }

    /**
     * 采用"左单旋"实现第1种情况
     * @param k2 传入的根节点
     * @return  返回单旋后的根节点
     */
    private AvlNode rotateWithLeftChild(AvlNode k2){
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.setHeight(Math.max(height(k2.left),height(k2.right))+1);
        // 现在k1.right=k2，所以需要比较k1.left的高度与k2.height高度即可
        k1.setHeight(Math.max(height(k1.left),k2.getHeight()+1));
        return k1;
    }

    /**
     * 采用"右单旋"实现第4种情况
     * @param k2 传入的根节点
     * @return  返回单旋后的根节点
     */
    private AvlNode rotateWithRightChild(AvlNode k2){
        AvlNode k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.setHeight(Math.max(height(k2.left),height(k2.right))+1);
        // 现在k1.left=k2，所以需要比较k1.right的高度与k2.height高度即可
        k1.setHeight(Math.max(k2.getHeight(),height(k1.right))+1);
        return k1;
    }

    /**
     * 采用"左双旋"实现第2种情况
     * @param k2 传入的根节点
     * @return  返回双旋后的根节点
     */
    private AvlNode doubleWithLeftChild(AvlNode k2){
        // 先左儿子与孙子进行右单旋，结果为该节点的左儿子
        k2.left = rotateWithRightChild(k2.left);
        // 返回的结点应该是：当前节点与左儿子的左单旋
        return rotateWithLeftChild(k2);
    }

    /**
     * 采用"右双旋"实现第3种情况
     * @param k2 传入的根节点
     * @return  返回双旋后的根节点
     */
    private AvlNode doubleWithRightChild(AvlNode k2){
        // 先右儿子与孙子(右儿子的左儿子)进行左单旋，结果为该节点的右儿子
        k2.right = rotateWithLeftChild(k2.right);
        // 返回的结点应该是：当前节点与右儿子的右单旋
        return rotateWithRightChild(k2);
    }

    /**
     * 前序遍历
     * @param root  根节点
     */
    public void preOrder(AvlNode root){
        if (root==null){
            return;
        }
        System.out.print(root.getValue()+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public AvlNode remove(int x,AvlNode root){
        if (root == null){
            return root;
        }
        if (x<root.getValue()){
            root.left = remove(x,root.left);
        }else if(x>root.getValue()){
            root.right = remove(x,root.right);
        }else if (root.left!=null&&root.right!=null){
            root.setValue(finMin(root.right).getValue());
            root.right = remove(root.getValue(),root.right);
        }else {
            root = (root.left!=null)?root.left:root.right;
        }
        return balance(root);
    }

    private AvlNode finMin(AvlNode root) {
        if (root==null){
            return null;
        }else if (root.left==null){
            return root;
        }
        return finMin(root.left);
    }
}
