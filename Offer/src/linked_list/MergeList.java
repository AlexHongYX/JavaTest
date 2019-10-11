package linked_list;

/**
 * Created by xiaoaxiao on 2019/10/11
 * Description: 合并两个有序链表为非递减序列
 *  https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337?tpId=13&tqId=11169&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
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

public class MergeList {
    public static ListNode Merge(ListNode list1,ListNode list2) {
        ListNode newHead = null;
        ListNode newLast = null;
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        while(cur1!=null&&cur2!=null){
            if(cur1.val<cur2.val){
                if(newHead==null){
                    newHead = cur1;
                    newLast = cur1;
                }else{
                    newLast.next = cur1;
                    newLast = cur1;
                }
                cur1 = cur1.next;
            }else{
                if(newHead==null){
                    newHead = cur2;
                    newLast = cur2;
                }else{
                    newLast.next = cur2;
                    newLast = cur2;
                }
                cur2 = cur2.next;
            }
        }
        while(cur1!=null){
            if(newHead==null){
                newHead = cur1;
                newLast = cur1;
            }else{
                newLast.next = cur1;
                newLast = cur1;
            }
            cur1 = cur1.next;
        }
        while(cur2!=null){
            if(newHead==null){
                newHead = cur2;
                newLast = cur2;
            }else{
                newLast.next = cur2;
                newLast = cur2;
            }
            cur2 = cur2.next;
        }
        return newHead;
    }

    public static int countNumberOf1s(int n) {
        if(n<10){
            return 1;
        }
        int sum = 0;
        int t = 0;
        int count = 0;
        // 不要直接修改n，之后还需要求当前位的低位的值
        int re = n;
        while(re!=0){
            // t是当前位的值
            t = re%10;
            // 此时的n就是高位的值
            re = re/10;
            if(t>1){
                sum += (int)((re+1)*Math.pow(10,count));
            }else if(t==1){
                sum += (int)(re*Math.pow(10,count+1))+n%(int)(Math.pow(10,count));
            }else{
                sum += (int)(re*Math.pow(10,count));
            }

            count++;
        }
        return sum;
    }

    public static void main(String[] args) {
//        ListNode head1 = new ListNode(1);
//        ListNode n1 = new ListNode(3);
//        ListNode n2 = new ListNode(5);
//        head1.next = n1;
//        n1.next = n2;
//
//        ListNode head2 = new ListNode(2);
//        ListNode n3 = new ListNode(4);
//        ListNode n4 = new ListNode(6);
//        head2.next = n3;
//        n3.next = n4;
//
//        ListNode head = Merge(head1,head2);
//        for (ListNode cur=head;cur!=null;cur=cur.next){
//            System.out.println(cur);
//        }
        int count = countNumberOf1s(11);
        System.out.println(count);
    }
}
