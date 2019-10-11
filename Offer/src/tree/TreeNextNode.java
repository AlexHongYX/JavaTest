package tree;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description: 二叉树中序遍历的下一个结点
 *  https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tqId=11210&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */

class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}

public class TreeNextNode {
    public static TreeLinkNode GetNext(TreeLinkNode pNode) {
        TreeLinkNode nextNode = null;
        // 当前为中间结点
        if (pNode.right != null) {
            nextNode = pNode.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
            return nextNode;
        }
        TreeLinkNode parent = pNode.next;
        if (parent==null){
            return null;
        }
        // 当前为左子节点
        if (pNode == parent.left) {
            return parent;
        } else {
            // 当前为右子节点
            // 看当前父节点是其父节点的右子节点还是左子节点
            // 左子节点：说明当前节点的父节点的父节点还没走，下一个就该走了
            // 右子节点：说明当前节点的父节点的父节点已经走过了，继续往上找
            TreeLinkNode node = parent.next;
            while(node!=null){
                if(parent==node.left){
                    break;
                }else{
                    parent = node;
                    node = parent.next;
                }
            }
            return node;
        }
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(8);
        TreeLinkNode A = new TreeLinkNode(6);
        TreeLinkNode B = new TreeLinkNode(10);
        TreeLinkNode C = new TreeLinkNode(5);
        TreeLinkNode D = new TreeLinkNode(7);
        TreeLinkNode E = new TreeLinkNode(9);
        TreeLinkNode F = new TreeLinkNode(11);

        root.left = A;
        root.right = B;
        A.next = root;
        B.next = root;
        A.left = C;
        A.right = D;
        B.left = E;
        B.right = F;
        C.next = A;
        D.next = A;
        E.next = B;
        F.next = B;

//        System.out.println(root);
        TreeLinkNode node = GetNext(D);
        System.out.println(node);
    }
}
