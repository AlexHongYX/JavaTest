package com.xiaoaxiao.array_test.three_num_sum;

import java.util.*;

/**
 * Created by xiaoaxiao on 2019/9/16
 * Description: 三个数之和等于0——LeetCode.15
 *              https://leetcode-cn.com/problems/3sum/
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            // 如果nums[i]>0，就直接跳出循环，因为对于已经排好序的数组，k>j>i，若最小的nums[i]都>0，则结果肯定都>0，因此之后的i都不会出现符合条件的三元组。
            if(nums[i]>0){
                break;
            }
            // 若i>0&&nums[i]==nums[i-1]，则此时无论这次循环(i=i)中出现什么情况(存在符合条件的三元组或不存在)，产生的结果都与上次循环(i=i-1)产生的结果一模一样，由于题目要求不要产生重复的三元组，因此直接跳过本次循环即可。
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                int ret = nums[i]+nums[j]+nums[k];
                if(ret==0){
                    result.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    // 由于排好序的数组中可能会出现相同的元素，因此，如果产生符合条件的三元组，将j一直往后+，直到+到多个重复元素的最后一个元素，将k一直-，直到-到多个重复元素的第一个。
                    while(j<k&&nums[j]==nums[j+1]){
                        j++;
                    }
                    while(j<k&&nums[k]==nums[k-1]){
                        k--;
                    }
                    // 此时的j处于多个重复元素的最后一个，若j++后，j下标之后的元素一定是>j下标所对应的值(排好序+本来j是多个重复元素的最后一个元素)，此时j下标之后的元素再+k下标对应的元素的结果肯定是>0的，因此，此处可以将j++，并且将k--。
                    j++;
                    k--;
                }else if(ret<0){
                    j++;
                }else{
                    k--;
                }
            }
        }
        return result;
    }
}
