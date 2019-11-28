package test;

/**
 * Created by xiaoaxiao on 2019/11/26
 * Description:
 */

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}

public class huiwen {
    public static void main(String[] args) {

    }

    public ListNode ReverseList(ListNode head) {
        if(head == null){
            return null;
        }
        // 三指针法
        ListNode cur = head;
        ListNode prevNode = null;
        ListNode next = null;
        while(cur!=null){
            prevNode = cur;
            cur = cur.next;
            prevNode.next = next;
            next = prevNode;
        }
        return prevNode;
    }

    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null){
            return null;
        }
        // 先算一下当前链表的长度，看有没有倒数第k个结点
        int length = 0;
        for(ListNode cur = head;cur!=null;cur=cur.next){
            length++;
        }
        if(length<k){
            return null;
        }

        // 让前指针先走k步
        ListNode firstNode = head;
        for(int i=0;i<k;i++){
            firstNode = firstNode.next;
        }

        ListNode result = head;
        while(firstNode!=null){
            firstNode = firstNode.next;
            result = result.next;
        }

        return result;
    }
}
