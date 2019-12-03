package day23;

/**
 * Created by xiaoaxiao on 2019/12/3
 * Description: 二叉树是否平衡检查
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}

public class BalanceCheck {
    public static boolean isBalance(TreeNode root){
        if (root == null){
            return true;
        }
        if (Math.abs(getLength(root.left)-getLength(root.right))<=1){
            return isBalance(root.left)&&isBalance(root.right);
        }
        return false;
    }

    public static int getLength(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(getLength(root.left),getLength(root.right))+1;
    }
}
