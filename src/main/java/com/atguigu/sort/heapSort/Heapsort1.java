package com.atguigu.sort.heapSort;

import java.util.Arrays;

public class Heapsort1 {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9,12,-1,3};
        heapSort(arr);
    }

    /**
     *  堆排序
     * @param  arr  待排序的数组
     */
    public static void heapSort(int[] arr){

        int temp = 0;
        /**
         * 第一步 ： 把二叉树变成大顶堆
         * 从最后一个非叶子节点i从下往上开始调整 （i = arr.length/2 - 1） ，然后 i-- ,因为i的前面全部是非叶子节点
         */
        for (int i = arr.length/2 - 1; i >= 0 ; i--) {
            heapIfy(arr,i, arr.length);
        }

        for (int i = arr.length-1; i > 0 ; i --) {
            /**
             * 第二步 ： 将根元素沉到底部
             */
            temp = arr[i] ;
            arr[i] = arr[0];
            arr[0] = temp ;
            /**
             * 第三步 ：每交换一次重新调整
             * 这里为什么敢直接从 0 开始 ？
             *     这里与第一步不同，第一步要从第一个非叶子节点开始，经过了第一步之后，就变成了大顶堆，第二步只是把根元素沉到底部，跟元素他的
             *     子树没有改变，相当于已经调整好了，只需要调整根节点
             */
            heapIfy(arr , 0  , i);
        }
        System.out.println(Arrays.toString(arr));

    }
    /**
     * 这个方法的功能 ： 把第i个节点以及他的子树变成大顶堆，使用这个方法有个前提，就是在 除了在 i 位置上可能不是大根堆，但他的子节点一定是大根堆
     * 大根堆 ：只要满足父节点比子节点大就行了，不是满足比左子树大比右子树小，这是二叉排序树，不是大顶堆
     * @param arr   要排序的数组
     * @param i   把第i个节点以及他的子树变成大顶堆
     * @param length  给数组里面多长的数组进行堆排序，避免已经排好的数又进行了堆排序
     */
    public static void heapIfy(int[] arr , int i , int length){
        int temp = arr[i] ;
        for (int k= 2 * i + 1; k < length ; k = k * 2 + 1) {
            if(k+1 < length && arr[k] < arr[k+1]){
                k++ ;
            }
            if (temp < arr[k]){
                arr[i] = arr[k];
                i = k ;
            }else {
                break;
            }
        }
        arr[i] = temp ;
    }
}
