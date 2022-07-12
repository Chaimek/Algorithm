package com.atguigu.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode-1  两数之和
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {1,2,33,4};
        int[] ints = towSum(nums, 5);
        System.out.println(Arrays.toString(ints));
    }
    public static int[] towSum(int[] nums , int target){
        HashMap<Integer , Integer> hashMap = new HashMap<>() ;
        int[]  res = new int[2];
        for (int i = 0; i < nums.length; i++) {
           int needNum = target - nums[i] ;
            Integer temp = hashMap.get(needNum);
            if (temp != null){
                res[0] = i;
                res[1] = temp ;
                return  res;
            }else {
                hashMap.put(nums[i],i);
            }
        }
        return null ;
    }

}
