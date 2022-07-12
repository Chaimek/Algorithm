package com.atguigu.sort.QuickSort.MergeSort;

import java.util.Arrays;

/**
 * 求小和问题：
 */
public class SmallSum {
    public static void main(String[] args) {
        int[] arr ={1,3,4,3,3,3,5};
        int i = smallSum(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(i);
    }
    public static int smallSum(int[] arr , int L ,int R ){
        if (L >= R) {
            return 0 ;
        }
        int mid = (L + R) / 2 ;
        return smallSum(arr,L,mid) +
        smallSum(arr,mid + 1,R) +
        mergeAndCountSmallSum(arr,L,mid,R);
//        int  ans = 0 ;
//        if (L < R){
//            int mid = (L + R) / 2 ;
//           ans = smallSum(arr,L,mid) + smallSum(arr,mid + 1,R) + mergeAndCountSmallSum(arr,L,mid,R);
//        }
//        return ans;
    }

    /**
     * 当你纠结 左边这个数与右边的数大小关系时（反之亦然） ： 改造merge()，统统想成，哪边比哪边大
     * @param arr
     * @param L
     * @param mid
     * @param R
     * @return
     */
    public static int mergeAndCountSmallSum(int[] arr ,int L ,int mid ,int R ){
        int[] help = new int[R - L + 1];
        int i = L ;
        int j = mid + 1 ;
        int t = 0 ;
        int ans = 0;
        while (i <= mid && j <= R){
            ans +=  arr[i] < arr[j] ? (R-j+1) * arr[i] : 0 ;
            /**
             * 关于等于号问题 ： 大的一方在相等时过：
             * 比如 ，这里求右边比当前数大的个数，右边大 ，相等时让右边数组的数拷贝到help数组中
             * 换句话说，要求右边比当前数大的个数，就要右边的数继续当前数比较，当前数不动，拷贝右边的数，直到找到右边第一个数比左边的数大
             *
             */
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
        return ans ;
    }


}
