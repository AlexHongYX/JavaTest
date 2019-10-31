package tree;

import sun.reflect.generics.tree.Tree;

/**
 * Created by xiaoaxiao on 2019/10/31
 * Description: 判断一棵树是否是另一颗树的子结构
 *  https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tqId=11170&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class SubStructure {
    public static boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1==null || root2==null){
            return false;
        }
        if(isSame(root1,root2)){
            return true;
        }
        return HasSubtree(root1.left,root2)||HasSubtree(root1.right,root2);
    }

    public static boolean isSame(TreeNode root1,TreeNode root2){
        if(root1==null&&root2==null){
            return true;
        }
        if(root1!=null&&root2!=null&&root1.val==root2.val){
            return isSame(root1.left,root2.left)&&isSame(root1.right,root2.right);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(8);
        TreeNode t2 = new TreeNode(7);
        TreeNode t3 = new TreeNode(9);
        TreeNode root1 = new TreeNode(8);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(7);

        root1.left = t1;
        root1.right = t2;
        t1.left = t3;
        t1.right = t4;
        t2.left = t5;
        t2.right = t6;

        TreeNode root2 = new TreeNode(8);
        TreeNode t21 = new TreeNode(9);
        TreeNode t22 = new TreeNode(2);
        root2.left = t21;
        root2.right = t22;

        System.out.println(HasSubtree(root1,root2));

    }
}
