package day22;

/**
 * Created by xiaoaxiao on 2019/12/1
 * Description: 链式数字反向放置，求和，并以链表的形式返回
 */

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class LinkedPlus {
    public static void main(String[] args) {
        ListNode headA = new ListNode(5);
        ListNode A1 = new ListNode(6);
        ListNode A2 = new ListNode(9);
        headA.next = A1;
        A1.next = A2;

        ListNode headB = new ListNode(8);
        ListNode B1 = new ListNode(3);
        ListNode B2 = new ListNode(3);
        headB.next = B1;
        B1.next = B2;

        ListNode ret = plusAB(headA,headB);

        ListNode cur = ret;
        while (cur!=null){
            System.out.print(cur.val+" ");
            cur = cur.next;
        }

    }

    /**
     * 进行数位遍历即可，注意余数
     *
     * @param a
     * @param b
     * @return
     */
    public static ListNode plusAB(ListNode a, ListNode b) {
        /**
         * 定义十位上的数字
         */
        int decade = 0;
        ListNode head = null;
        ListNode tail = null;
        while (a != null || b != null) {
            int sum = 0;
            if (a!=null && b!=null){
                sum = a.val + b.val + decade;
            }else if(a != null){
                sum = a.val+decade;
            }else {
                sum = b.val+decade;

            }

            int val = sum % 10;
            decade = sum / 10;
            ListNode node = new ListNode(val);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            if (a != null){
                a = a.next;
            }
            if (b != null){
                b = b.next;
            }
        }

        if (decade!=0){
            ListNode node = new ListNode(decade);
            tail.next = node;
        }

        return head;
    }
}
