package com.atguigu.sort.QuickSort;

import java.util.Arrays;

/**
 * 荷兰国旗问题：
 * 给定一个整数数组
 * 要求把数组中小于K的元素放到数组的左边，大于K的元素放到数组的右边，
 * 等于K的元素放到数组的中间，最终返回一个整数数组，其中只有两个值，
 * 分别是等于K的数组部分的左右两个下标值。
 */
public class DutchFlagIssue {
    public static void main(String[] args) {
        int[] arr = {1,3,5,4,2,6,7,4,4,2,2,4,2,4};
        int[] partition = partition(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(partition));
    }
    public static int[] partition(int[] arr ,int  L ,int R ){
        if (L > R){
            return new int[]{-1,-1} ;
        }
        if (L == R) {
            return new int[]{L ,R } ;
        }

        int less = L-1 ; // <  右边界
        int right = R ;  // >  左边界
        int index = L ;  //只做一次index从0开始也对，但是递归调用就要是L
        while (index < right){
            if (arr[index] == arr[R]){  //使用arr[R] 最后让arr[R] 左边的数比他小，右边的数比他大
                index ++ ;
            }else if (arr[index] > arr[R]){
                swap(arr ,index , --right  );
            }else {
                swap(arr,index++,++less);
            }
        }
        //while循环结束之后，只有aar[R]的位置没有跟换
        swap(arr, right,R);
        return new int[]{less + 1 , right};
    }
    public static void swap(int[] arr , int l , int r){
        int temp = 0 ;
        arr[temp] =arr[l] ;
        arr[l] =arr[r] ;
        arr[r] = arr[temp];
    }
}
