package com.atguigu.binarysearchapplication;

import java.util.Arrays;

/**
 *
 *  局部最小问题：
 *      给定一个数组 ，无序且相邻两个数不等，假如某个数比左右两边都小 ， 现求返回一个局部最小值
 *      规定 ： 假如 只有一个数 就认为这个数是局部最小
 *             假如 arr[0] < arr[1]  --->  arr[0] 为局部最小
 *             假如 arr[n-1] <arr[n-2] -- >  arr[n-1] 为局部最小
 *
 *   使用二分法： 不一定要值有序，只要保证他在某种规则上有序就行了
 */
public class LocalMinimum {

    public static int localMinimumIndex(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int N = arr.length;

        if (arr.length == 1){
            return 0 ;
        }
        if (arr[0] < arr [1]){
            return 0;
        }
        if (arr[N-1] < arr[N-2] ){
            return N-1 ;
        }
        int L = 0 ;
        int R = N - 1 ;
        while (L < R - 1 ) { // 防止下标越界
            int mid = (L + R) / 2 ;
            if (arr[mid] < arr[mid -1] && arr[mid] < arr[mid+ 1]){
                return mid ;
            }else {
                //这里可能越界，要想不越界，要保证二分之后长度为3  --->  即 ：  while(L < R -1)
                if (arr[mid - 1] < arr[mid]){
                    R = mid - 1 ;
                }else {
                    L = mid + 1 ;
                }

            }
        }
        return arr[L] <arr[R] ? L : R;
    }

    public static int[] randomArr(int maxLen , int maxValue){

        int len = 0 ;
        len = (int)(Math.random() * maxLen) ;
        int arr[] = new int[len];
        for (int i = 1; i < len; i++) {
            arr[0] = (int) (Math.random() * maxValue) ;
            do {
                arr[i] =(int) (Math.random() * maxValue) ;
            }while (arr[i] == arr[i-1]);
        }
        return arr;
    }
    public static boolean check(int[] arr , int localMinimumIndex){
        if (arr.length == 0){
            return localMinimumIndex == -1 ;
        }
        int left = localMinimumIndex - 1 ;
        int right = localMinimumIndex + 1 ;
        boolean leftBigger = left >= 0 ? arr[left] > arr[localMinimumIndex] : true ;
        boolean rightBigger = right <arr.length ? arr[right] > arr[localMinimumIndex] : true ;
        return  leftBigger && rightBigger ;

    }
    public static void print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int maxLen = 10000 ;
        int maxValue = 10000 ;
        int testCount = 100000 ;
        System.out.println("测试开始");
        for (int i = 0; i < testCount; i++) {
            int[] arr = randomArr(maxLen, maxValue);
            int i1 = localMinimumIndex(arr);
            if (!check(arr,i1)){
                System.out.println("出错啦！");
                print(arr);
            }
        }
        System.out.println("测试结束！");
    }
}
