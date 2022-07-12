package com.atguigu.sort.QuickSort.MergeSort;

import java.util.Arrays;

public class MergeSort2 {
    public static void main(String[] args) {
        int maxLen = 50000 ;
        int maxValue = 10000 ;
        int testCount = 10000 ;
        System.out.println("测试开始");
        for (int i = 0; i < testCount; i++) {
            int[] arr = randomArr(maxLen, maxValue);
            mergeSort(arr,0,arr.length-1);
            if (!check(arr)){
                System.out.println("出错啦！");
                print(arr);
            }
        }
        System.out.println("测试结束！");
//        int[] arr ={1,5,4,6,2,3};
//        mergeSort(arr,0,arr.length-1);
    }
    public static void mergeSort(int[] arr , int L ,int R ){
        if (L < R){
            int mid = (L + R) / 2 ;
            mergeSort(arr,L,mid);
            mergeSort(arr,mid + 1,R);
            merge(arr,L,mid,R);
        }
    }
    public static void merge(int[] arr ,int L ,int mid ,int R ){
        int[] help = new int[R - L + 1];
        int i = L ;
        int j = mid + 1 ;
        int t = 0 ;
        while (i <= mid && j <= R){
            help[t++] =  arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid){
            help[t++] = arr[i++] ;
        }
        while (j <= R){
            help[t++] = arr[j++];
        }
        i = L ;
        t=0;
        while (i <= R){
            arr[i++] = help[t++];
        }
    }

    public static int[] randomArr(int maxLen , int maxValue){

        int len = 0 ;
        len = (int)(Math.random() * maxLen) ;
        int arr[] = new int[len];
        for (int i = 1; i < len; i++) {
            arr[i] =(int) (Math.random() * maxValue) ;
        }
        return arr;
    }
    public static boolean check(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]  < arr[i-1]){
                return false;
            }
        }
        return true;
    }
    public static void print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

}
