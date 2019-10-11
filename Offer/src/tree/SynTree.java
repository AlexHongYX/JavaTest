package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description: 判断一棵二叉树是否为对称的
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}

public class SynTree {

    public static boolean isSymmetrical(TreeNode pRoot)
    {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        queue1.offer(pRoot);
        while(!queue1.isEmpty()){
            TreeNode node = queue1.poll();
            if(node!=null){
                queue1.offer(node.left);
                queue1.offer(node.right);
            }
            list1.add(node);
        }
        queue2.offer(pRoot);
        while(!queue2.isEmpty()){
            TreeNode node = queue2.poll();
            if(node!=null){
                queue2.offer(node.right);
                queue2.offer(node.left);
            }
            list2.add(node);
        }
        System.out.println(list1);
        System.out.println(list2);
        if (list1.size()!=list2.size()){
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            TreeNode node1 = list1.get(i);
            TreeNode node2 = list2.get(i);
            if(node1==null&&node2==null){
                continue;
            }else if(node1==null||node2==null){
                return false;
            }else {
                if(node1.val!=node2.val){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        TreeNode A = new TreeNode(6);
        TreeNode B = new TreeNode(10);
        TreeNode C = new TreeNode(5);
        TreeNode D = new TreeNode(7);
        TreeNode E = new TreeNode(9);
        TreeNode F = new TreeNode(11);

        root.left = A;
        root.right = B;
        A.left = C;
        A.right = D;

        B.right = F;

        System.out.println(isSymmetrical(root));
    }
}
