package com.atguigu.slidingwindow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FixedWindow {


    public static int[] fixWRMax(int[] arr , int w){
        if (arr == null || arr.length == 0 ||  w  <  0 ){
            return  null ;
        }

        //LinkedList底层是双向链表， 调用方法不同，既是普通队列，也能是双端队列，只是调用方法不同,如果引用是 Queue 类型那就是普通队列
        //双端队列 ----> 里面存的是下标
        LinkedList<Integer> twoQueue = new LinkedList<>() ;
        int N = arr.length; ;
        //一共返回的结果
        int[]  res = new int[N - w + 1 ] ;
        //方便我们存数据
        int index = 0 ;
        for (int R = 0; R < N ; R++) {
            //什么时候需要弹出数据
            while ( !twoQueue.isEmpty()  &&   (arr[twoQueue.peekLast()] <= arr[R])) {
                twoQueue.pollLast();
            }
            twoQueue.addLast(R);
            //固定窗口 L == R-w
            if ( (R-w) == twoQueue.peekFirst() ){ //如果相等，说明最大的位置过期了，要弹出
                twoQueue.pollFirst();
            }
            //当 R == w-1 ----> 窗口已经遍历w个数了，窗口形成
            if (R >= w-1){
                res[index++] = arr[twoQueue.peekFirst()];
            }

        }
        return res ;
    }

    public static void main(String[] args) {
        int[] arr   = {6,6,6,4,7,4};
        int[] ints = fixWRMax(arr, 3);
        System.out.println(Arrays.toString(ints));
    }

}
