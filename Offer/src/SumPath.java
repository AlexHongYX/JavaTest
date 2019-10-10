package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by xiaoaxiao on 2019/10/10
 * Description: 路径之和为某个值的集合
 * https://www.nowcoder.com/practice/b736e784e3e34731af99065031301bca?tpId=13&tqId=11177&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *      不好算的是最后要将ArrayList集合中的ArrayList按照长度排序
 *          写一个比较器（自定义比较器/匿名比较器/lambda表达式）
 *          注意：自定义比较器要在该类中创建该比较器的对象
 */
public class SumPath{
    private static MyComparator myComparator = new MyComparator();

    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        findAllPath(root, target, resultList, list);
        // 将resultList中的内容进行排序，按照数组长度降序
//        Collections.sort(resultList,myComparator);
        Collections.sort(resultList,(ArrayList<Integer> o1,ArrayList<Integer> o2)->o2.size()-o1.size());
        return resultList;
    }

    private static int trueLength = 0;

    public static void findAllPath(TreeNode root, int target,
                                   ArrayList<ArrayList<Integer>> resultList, ArrayList<Integer> list){
        if(root == null){
            return ;
        }
        trueLength += root.val;
        list.add(root.val);
        if(root.left==null&&root.right==null&&trueLength==target){
            ArrayList<Integer> newList = new ArrayList<>();
            newList.addAll(list);
            resultList.add(newList);
        }
        findAllPath(root.left,target,resultList,list);
        findAllPath(root.right,target,resultList,list);
        trueLength -= root.val;
        list.remove(list.size()-1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode A = new TreeNode(5);
        TreeNode B = new TreeNode(12);
        TreeNode C = new TreeNode(4);
        TreeNode D = new TreeNode(7);

        root.left = A;
        root.right = B;
        A.left = C;
        A.right = D;

        ArrayList<ArrayList<Integer>> result = FindPath(root,22);
        System.out.println(result);

    }
}

class MyComparator implements Comparator<ArrayList<Integer>> {

    @Override
    public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
        return o1.size()-o2.size();
    }
}
