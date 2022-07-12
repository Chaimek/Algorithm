package com.atguigu.sort.heapSort;

import java.util.Arrays;

public class HeapSort2 {

    public static void main(String[] args) {

        int maxLen = 10000 ;
        int maxValue = 20000 ;
        int testCount = 10000 ;
        System.out.println("测试开始");
        for (int i = 0; i < testCount; i++) {
            int[] arr = randomArr(maxLen, maxValue);
            heapSort(arr);
            if (!check(arr)){
                System.out.println("出错啦！");
                print(arr);
            }
        }
        System.out.println("测试结束！");
    }
    /**
     * 面试上只要写堆排序就写 heapSort1
     * @param arr
     */
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //只要变成大根堆，没必要这么写
//        for (int i = 0; i < arr.length; i++) { //  N * logN  ----> 一个数一个数的插入
//            heapInsert(arr , i );
//        }
        //优化
        for (int i = arr.length/ 2 - 1; i  >= 0  ; i-- ) {
            heapIfy(arr , i , arr.length);
        }
        int heapSize = arr.length ;
        swap(arr,0 , --heapSize);
        while (heapSize > 0 ){
            heapIfy(arr , 0 , heapSize );
            swap(arr, 0 , --heapSize);
        }
    }

    /**
     * 向上找
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr , int index){
        while (arr[index] >  arr[(index- 1) /2]){
            swap(arr,index , (index -1 ) / 2);
            index = (index- 1) /2 ;
        }
    }

    /**
     * 向下判断 ，使用这个方法有个前提，就是在 除了在 i 位置上可能不是大根堆，但他的子节点一定是大根堆 ，跟heapSort1的adjust()方法一样
     * @param arr
     * @param index
     * @param length
     */
    public static void heapIfy(int[] arr , int index , int length){
        int left = index * 2 + 1;
        while (left < length){ //在堆结构中用这个heapSize，普通的写堆排序，这个要当做参数传进来，length -> 堆大小
            int largest = left + 1 < length &&  arr[left + 1] > arr[left] ? left + 1 : left ;
            largest = arr[index] > arr[largest] ? index :largest ;
            if (largest == index){
                break;
            }
            swap(arr, index , largest) ;
            index = largest ;
            left = index * 2 + 1 ;
        }
    }

    public static void swap(int[] arr , int l,int r){
        int temp = 0 ;
        temp =arr[l];
        arr[l] =arr[r];
        arr[r] =temp ;
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
