package com.atguigu.sort.QuickSort.MergeSort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 归并排序  ---->  分治思想
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {2,1,3,5};
//        int[] temp = new int[arr.length];
//        mergeSort(arr,0,arr.length-1,temp);
//        System.out.println(Arrays.toString(arr));

//        int[] arr= new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int)(Math.random()*8000000);
//        }

//        int[] temp = new int[arr.length];
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
//        String format1 = simpleDateFormat.format(date1);
//        System.out.println("排序前："+format1);
//
//        mergeSort(arr,0,arr.length-1,temp); //0.04
//
//        Date date2 = new Date();
//        String format2 = simpleDateFormat.format(date2);
//        System.out.println("排序后："+format2);
//        mergeSortNoRecursion(arr);
//        System.out.println(Arrays.toString(arr));
        int maxLen = 50000 ;
        int maxValue = 10000 ;
        int testCount = 10000 ;
        System.out.println("测试开始");
        for (int i = 0; i < testCount; i++) {
            int[] arr = randomArr(maxLen, maxValue);
            mergeSortNoRecursion(arr);
            if (!check(arr)){
                System.out.println("出错啦！");
                print(arr);
            }
        }
        System.out.println("测试结束！");
    }
    /**
     *  分加治：
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr,int left, int right,int[] temp){
        if (left < right){
            int mid = (left+right)/2;
            //向左分
            mergeSort(arr, left, mid, temp);
            //向右分
            mergeSort(arr, mid+1, right, temp);
            //当执行到这一步的时候，说明已经分完了
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     *  治：
     * @param arr   待排序的数组
     * @param left  开始时最左边下标的索引
     * @param mid   开始时左边数组的最后一个位置的下表，也就是右边数组的前一个
     * @param right  开始最右边下标的索引
     * @param temp    临时数组，
     */
    public static void merge(int[] arr , int left,int mid,int right,int[] temp){
        int l =left;
        int j = mid +1;
        int t = -1;  //指向temp数组中空位置的前一个,因为初始temp数组中没有元素，所以初始值是-1
        //(一)依次比较左边数组跟右边数组的数，依次存入到temp数组中，直到有一个数组为空时
        while (l <= mid && j <= right ){  //当两个数组都不为空时
            if(arr[l] < arr[j]){
                t++;
                temp[t] = arr[l] ;
                l++;

            }else {
                t++;
                temp[t] =arr[j];
                j++;
            }
        }

        //(二) 当上面执行完了，说明有一个数组为空了，那就把另外一个不为空的数组依次放入temp中
        //当左边数组剩余时
        while(l<=mid){
            t++;
            temp[t] = arr[l];
            l++;
        }
        while (j<=right){
            t++;
            temp[t] =arr[j];
            j++;
        }

        //(三)将temp数组中的数据拷贝到原来的数组
        t = -1 ;
        int templeft = left;
        while (templeft<=right){
             t++;
             arr[templeft]=temp[t];
             templeft++;
        }
    }

    /**
     * 非递归实现归并排序
     * @param arr
     */
    public static void  mergeSortNoRecursion(int[] arr){
        if (arr == null  || arr.length <2){
            return;
        }
        int N = arr.length ;
        int[] temp =  new int[N];
        int mergeSize = 1 ; //左边分组大小
        while ( mergeSize < N) {
            int L = 0 ;
            while ( L < N){
                //L ... M    M - L = mergeSize - 1 ；
                int M = L + mergeSize - 1 ;
                if(M >= N){
                    break;
                }
                int R = Math.min(M + mergeSize , N -1) ;
                merge(arr,L, M,R,temp);
                L = R + 1;
            }
            if (mergeSize > N / 2){
                break;
            }
            mergeSize <<= 1 ;
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
