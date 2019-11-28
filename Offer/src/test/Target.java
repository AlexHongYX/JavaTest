package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/11/26
 * Description:
 */
public class Target {

    public static void main(String[] args) {
        int[] nums = {5,7,9,10,13};
        int target = 7;
        List<int[]> list = getList(nums,target);
        for (int[] arr : list){
            System.out.println("["+arr[0]+","+arr[1]+"]");
        }
//        System.out.println(getList(nums, target));
    }

    public static List<int[]> getList(int[] nums, int target) {
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            int j = i+1;
            while (j!=i){
                if (nums[j]-nums[i]==target){
                    int[] arr = {nums[i],nums[j]};
                    list.add(arr);
                    break;
                }else {
                    j--;
                }
            }
        }

        if (list.size()==0){
            int[] arr = {-1,-1};
            list.add(arr);
        }

        return list;
    }
}
