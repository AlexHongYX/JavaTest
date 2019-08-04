package com.xiaoaxiao.binary_test.dao;

import com.xiaoaxiao.binary_test.Impl.ITree;
import com.xiaoaxiao.binary_test.Node;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/8/1
 * Description:
 */
public class BinaryTreeTest implements ITree {

    private int i = 0;

    // 根据字符串创建二叉树
    @Override
    public Node createTestTree(String s) {
        char c = s.charAt(i);
        i++;
        if (c == '#') {
            return null;
        }

        Node node = new Node(c);
        node.left = createTestTree(s);
        node.right = createTestTree(s);
        return node;
    }

    // 前序
    @Override
    public void binaryTreePrevOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data);
        binaryTreePrevOrder(root.left);
        binaryTreePrevOrder(root.right);
    }

    // 中序
    @Override
    public void binaryTreeInOrder(Node root) {
        if (root == null) {
            return;
        }
        binaryTreeInOrder(root.left);
        System.out.print(root.data);
        binaryTreeInOrder(root.right);
    }

    // 后序
    @Override
    public void binaryTreePostOrder(Node root) {
        if (root == null) {
            return;
        }
        binaryTreePostOrder(root.left);
        binaryTreePostOrder(root.right);
        System.out.print(root.data);
    }

    // 结点个数
    @Override
    public int getSize(Node root) {
        if (root == null) {
            return 0;
        }
        return getSize(root.left) + getSize(root.right) + 1;
    }

    // 叶子结点个数
    @Override
    public int getLeafSize(Node root) {

        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return getLeafSize(root.left) + getLeafSize(root.right);
    }

    @Override
    public int getKLevelSize(Node root, int k) {
        return 0;
    }

    @Override
    public Node find(Node root, char value) {

        if (root == null) {
            return null;
        }
        if (root.data == value) {
            return root;
        }
        // 先将ret设置为左子树中返回的值
        Node ret = find(root.left, value);
        // 若ret==null（左子树中没找到返回null）
        if (ret == null) {
            // 再去右子树中找
            ret = find(root.right, value);
        }
        // 若左子树中找到，就不会进上面的if语句，则返回的就是左子树中找到的结点
        // 若左子树中没找到，在右子树中找，若右子树中找到了就返回对应结点
        // 若左右子树都没找到就返回null
        return ret;
    }

    /**
     * 采用递归的思想，一棵树的高度=左右子树中最高的高度+1
     */
    @Override
    public int height(Node root) {
        if (root == null) {
            return 0;
        }


//        return Math.max(height(root.left),height(root.right))+1;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    @Override
    public void binaryTreePrevOrderNonR(Node root) {
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        Node top = null;
        // cur!=null每次cur变为top.right都要对cur再进行判断
        // 若cur不为空就继续找其最左下方的结点
        //
        // 而!stack.empty()表示，若cur为空了，而栈中还有结点，则遍历过程还没有结束
        // 只能说明一个结点已经完全走完了，该走其上一层的结点了
        // 再用top取得栈顶元素（上一层的结点），再访问top的右节点
        while (cur != null || !stack.empty()) {
            // 下面这个while循环只是为了走到该节点的最左下方（cur为null的时候）
            // 节点入栈操作
            while (cur != null) {
                stack.push(cur);
                System.out.print(cur.data + " ");
                cur = cur.left;
            }
            top = stack.pop();
            cur = top.right;
        }
        System.out.println();
    }

    @Override
    public void binaryTreeInOrderNonR(Node root) {
        // 创建一个栈，用来进行非递归遍历时存储相应的结点
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        Node top = null;

        // 只有当结点为null且栈以空才会结束
        while (cur != null||!stack.empty()){
            // 先找到该节点的最左下的结点，并将沿途经过的节点全部入栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 该结点已经被输出，没有继续留在栈顶的意义，必须要取出来
            top = stack.pop();
            System.out.print(top.data+" ");
            cur = top.right;
        }
        System.out.println();
    }

    // 后序遍历
    // 两个注意点
    //  1、在cur出栈后需要将cur设置为null
    //        由于cur=stack.pop()，因此下次再进入while循环时，cur!=null
    //        cur又移动到最左下，导致该程序进入死循环
    //      eg：
    //          L出栈，由于cur还指向L，下一次循环时，cur!=null，cur=L.left
    //          cur=stack.peek()->L，L又会入栈
    //  2、要设置一个preNode保存已经打印过的结点
    //        当L（D的右节点）打印过返回至D，而此时cur==D，再次进入if判断cur.right
    //        而cur.right!=null（cur为L），又会进入cur=cur.right，再次将L入栈
    //          导致死循环
    @Override
    public void binaryTreePostOrderNonR(Node root) {
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        Node preNode = null;
        while(cur != null || !stack.empty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            // 若cur.right==null，表示该节点右子节点为null，该节点可以出栈被打印
            // 还需要判断cur.right==preNode，判断该右子节点是否被打印过
            if (cur.right == null || cur.right == preNode){
                cur = stack.pop();
                System.out.print(cur.data+" ");
                preNode = cur;
                // 当该节点被打印了，将该节点设置为null
                cur = null;
            }else {
                cur = cur.right;
            }
        }
        System.out.println();
    }

//    LinkedList中add和offer的区别
    // 层序
    // 利用一个队列实现层序，将队头元素的不为空的左子树或右子树进行入队
    @Override
    public void binaryTreeLevelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Node cur = null;
        queue.offer(root);
        while (!queue.isEmpty()){
            cur = queue.poll();
            if (cur.right != null){
                queue.offer(cur.right);
            }
            if (cur.left != null){
                queue.offer(cur.left);
            }

            System.out.print(cur.data+" ");
        }
        System.out.println();
    }

    /**
     *  判断一棵二叉树是否为完全二叉树
     *      将根节点入队
     *      1、弹出队头元素，将队头元素的两个子节点入队（即便为null也入队）
     *      2、直到弹出的队头元素为null
     *      3、判断此时的队列中是否全为null
     *              ①全为null->完全二叉树
     *              ②有非null的结点->非完全二叉树
     */
    @Override
    public int binaryTreeComplete(Node root) {
        if (root == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node top = null;
        while (!queue.isEmpty()){
            top = queue.poll();
            if(top == null){
                break;
            }
            queue.offer(top.left);
            queue.offer(top.right);
        }
        // 最终返回的队列是不可能为空的
        // 因为除了已有的结点还存在一大堆null
//        if (queue.isEmpty()){
//            return 0;
//        }
        while (!queue.isEmpty()){
            top = queue.poll();
            if(top != null){
                return -1;
            }
        }
        return 0;
    }
}
