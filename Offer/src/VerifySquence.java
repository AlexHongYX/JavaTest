package tree;

/**
 * Created by xiaoaxiao on 2019/10/10
 * Description: 判断当前数组是否为某二叉搜索树的后序遍历
 *  https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd?tpId=13&tqId=11176&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 *      取得数组长度，找到根节点
 *      找到数组中比根节点小的部分（在前面）
 *      剩下的部分都应该比根节点大（在后面）
 *      **如果在后面的部分出现了比根节点小的情况，说明不是一颗二叉搜索树**
 *      递归前面部分和后面部分，直到该部分元素只剩一个
 */
public class VerifySquence {
    public static boolean VerifySquenceOfBST(int [] sequence) {
        // 注意给一个空数组的情况
        if(sequence.length==0){
            return false;
        }
        return SquenceOfBST(sequence,0,sequence.length-1);
    }

    public static boolean SquenceOfBST(int[] sequence,int start,int end){
        // start>=end说明当前区间无元素或只有一个元素，直接返回true
        // start>end不能省略
        if(start>=end){
            return true;
        }
        int root = sequence[end];
        int i = start;
        for(;i<end;i++){
            if(sequence[i]>root){
                break;
            }
        }
        for(int j=i;j<end;j++){
            if(sequence[j]<root){
                return false;
            }
        }
        if(i==start){
            return SquenceOfBST(sequence,start,end-1);
        }else {
            return SquenceOfBST(sequence,start,i-1)&&SquenceOfBST(sequence,i,end-1);
        }
    }

    public static void main(String[] args) {
        int[] sequence = {4,6,7,5};
        if(VerifySquenceOfBST(sequence)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
