package com.xiaoaxiao.binary_test.is_balance_tree;

import com.xiaoaxiao.binary_test.Node;

/**
 * Created by xiaoaxiao on 2019/8/6
 * Description: 在记录结点是否为平衡二叉树的同时记录其高度
 *              重点是：
 * 					一旦有一棵树不是平衡二叉树，则整个树就都不是平衡二叉树了
 * 				因此：
 * 					设置一个全局变量balance并初始化为true，若当前树是平衡二叉树则不对该
 * 					值做修改，一旦当前树不是平衡二叉树，则将balance设置为false，这样，
 * 					在数次递归中，一旦有一次balance的值变为false，最终最后的出口的balance
 * 					值肯定就是false，若全都是平衡二叉树，则最终返回balance的初始值true
 */

public class IsBalancePlus {

    // 将balance初始化为true
    private boolean balance = true;

    public boolean isBalanced(Node root) {
        int ret = getHeight(root);
        return balance;
    }

    public int getHeight(Node root){
        if(root == null){
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if(Math.abs(leftHeight-rightHeight)>1){
            balance = false;
        }

        return leftHeight > rightHeight ? leftHeight+1:rightHeight+1;
    }

}
