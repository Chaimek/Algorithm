package com.atguigu.tree;

import java.util.PriorityQueue;

public class CutGoldBars {
    public static int createHuffman(int[] arr){
        if ( arr == null || arr.length == 0){
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>() ;
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }
        int cur = 0 ;
        int res = 0 ;
        while (heap.size() > 1){
            cur = heap.poll() + heap.poll() ;
            res += cur ;
            heap.add(cur);
        }
        return heap.poll() ;
    }
}
