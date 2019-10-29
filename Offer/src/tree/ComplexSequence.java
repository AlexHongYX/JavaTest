package tree;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/10/29
 * Description: Z字型层序遍历
 */

public class ComplexSequence {
    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(pRoot==null){
            return result;
        }

        // 使用栈代替队列，更好的实现奇偶行的分离
        Stack<TreeNode> stack = new Stack<>();
        stack.push(pRoot);

        // 使用row表示行号，为了区分奇偶行
        int row = 1;
        while(!stack.empty()){
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<TreeNode> treeList = new ArrayList<>();

            int count = stack.size();
            // 先将该层出栈的结果放在一个新的list中
            // 不能一边出栈一遍让其子节点入栈，这样就访问不到改成的其他节点了（被新入的结点挡住了）
            while(count!=0){
                TreeNode node = stack.pop();
                treeList.add(node);
                count--;
            }
            // 将该层的结点按顺序取出后再根据当前的行号对该行结点的左右子树进行入栈操作
            // 奇数行的结点先入左后入右——>由于是栈，所以该行的下一行（偶数行）的出栈顺序就是从右到左
            // 偶数行的结点先入右后入左——>该行的下一行（奇数行）的出栈顺序就是从左到右
            if(row%2==1){
                for(TreeNode node:treeList){
                    if(node.left!=null){
                        stack.push(node.left);
                    }
                    if(node.right!=null){
                        stack.push(node.right);
                    }
                }
            }else{
                for (TreeNode node:treeList){
                    if(node.right!=null){
                        stack.push(node.right);
                    }
                    if(node.left!=null){
                        stack.push(node.left);
                    }
                }
            }
            // 最后将保存结点的链表中的值取出来放在list
            for (TreeNode node:treeList){
                list.add(node.val);
            }
            // 将list加到result中
            result.add(list);
            // 将行号+1
            row++;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(8);
        TreeNode t2 = new TreeNode(6);
        TreeNode t3 = new TreeNode(10);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(7);
        TreeNode t6 = new TreeNode(9);
        TreeNode t7 = new TreeNode(11);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;

        ArrayList<ArrayList<Integer>> result = Print(t1);

        for(ArrayList<Integer> list:result){
            System.out.println(list);
        }

    }

}
